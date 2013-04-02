/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL
 * license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */

package com.mirth.connect.plugins.messagepruner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.text.DateFormatter;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import com.mirth.connect.client.core.Operations;
import com.mirth.connect.client.core.TaskConstants;
import com.mirth.connect.model.ExtensionPermission;
import com.mirth.connect.plugins.ServicePlugin;
import com.mirth.connect.util.PropertyLoader;

public class MessagePrunerService implements ServicePlugin {
    public static final String PLUGINPOINT = "Message Pruner";

    private Scheduler sched;
    private SchedulerFactory schedFact;
    private JobDetail jobDetail;
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public String getPluginPointName() {
        return PLUGINPOINT;
    }

    @Override
    public void start() {
        try {
            sched.start();
        } catch (Exception e) {
            logger.error("could not start message pruner", e);
        }
    }

    @Override
    public void stop() {
        try {
            sched.shutdown();
        } catch (Exception e) {
            logger.error("could not exit message pruner", e);
        }
    }

    @Override
    public void init(Properties properties) {
        jobDetail = new JobDetail("prunerJob", Scheduler.DEFAULT_GROUP, MessagePrunerJob.class);

        try {
            schedFact = new StdSchedulerFactory();
            sched = schedFact.getScheduler();
            sched.scheduleJob(jobDetail, createTrigger(properties));
        } catch (Exception e) {
            logger.error("error encountered in database pruner initialization", e);
        }
    }

    @Override
    public void update(Properties properties) {
        try {
            sched.deleteJob("prunerJob", Scheduler.DEFAULT_GROUP);
            sched.scheduleJob(jobDetail, createTrigger(properties));

            // for some reason, this does not work
            // sched.rescheduleJob("prunerJob", Scheduler.DEFAULT_GROUP,
            // createTrigger(properties));
        } catch (Exception e) {
            logger.error("could not reschedule the message pruner", e);
        }
    }

    @Override
    public Object invoke(String method, Object object, String sessionId) {
        return null;
    }

    @Override
    public Properties getDefaultProperties() {
        Properties properties = new Properties();
        properties.put("interval", "daily");
        properties.put("time", "12:00 AM");
        return properties;
    }

    @Override
    public ExtensionPermission[] getExtensionPermissions() {
        ExtensionPermission viewPermission = new ExtensionPermission(PLUGINPOINT, "View Settings", "Displays the Message Pruner settings.", new String[] { Operations.PLUGIN_PROPERTIES_GET.getName() }, new String[] { TaskConstants.SETTINGS_REFRESH });
        ExtensionPermission savePermission = new ExtensionPermission(PLUGINPOINT, "Save Settings", "Allows changing the Message Pruner settings.", new String[] { Operations.PLUGIN_PROPERTIES_SET.getName() }, new String[] { TaskConstants.SETTINGS_SAVE });

        return new ExtensionPermission[] { viewPermission, savePermission };
    }

    private Trigger createTrigger(Properties properties) throws ParseException {
        Trigger trigger = null;
        String interval = PropertyLoader.getProperty(properties, "interval");

        if (interval.equals("hourly"))
            trigger = TriggerUtils.makeHourlyTrigger();
        else {
            SimpleDateFormat timeDateFormat = new SimpleDateFormat("hh:mm aa");
            DateFormatter timeFormatter = new DateFormatter(timeDateFormat);

            String time = PropertyLoader.getProperty(properties, "time");
            Date timeDate = (Date) timeFormatter.stringToValue(time);
            Calendar timeCalendar = Calendar.getInstance();
            timeCalendar.setTime(timeDate);

            if (interval.equals("daily")) {
                trigger = TriggerUtils.makeDailyTrigger(timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE));
            } else if (interval.equals("weekly")) {
                SimpleDateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEE");
                DateFormatter dayFormatter = new DateFormatter(dayDateFormat);

                String dayOfWeek = PropertyLoader.getProperty(properties, "dayOfWeek");
                Date dayDate = (Date) dayFormatter.stringToValue(dayOfWeek);
                Calendar dayCalendar = Calendar.getInstance();
                dayCalendar.setTime(dayDate);

                trigger = TriggerUtils.makeWeeklyTrigger(dayCalendar.get(Calendar.DAY_OF_WEEK), timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE));
            } else if (interval.equals("monthly")) {
                SimpleDateFormat dayDateFormat = new SimpleDateFormat("DD");
                DateFormatter dayFormatter = new DateFormatter(dayDateFormat);

                String dayOfMonth = PropertyLoader.getProperty(properties, "dayOfMonth");
                Date dayDate = (Date) dayFormatter.stringToValue(dayOfMonth);
                Calendar dayCalendar = Calendar.getInstance();
                dayCalendar.setTime(dayDate);

                trigger = TriggerUtils.makeMonthlyTrigger(dayCalendar.get(Calendar.DAY_OF_MONTH), timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE));
            }
        }

        trigger.setStartTime(new Date());
        trigger.setName("prunerTrigger");
        trigger.setJobName("prunerJob");
        return trigger;
    }
}