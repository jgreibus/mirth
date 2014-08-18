/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.io.RuntimeIOException;

import com.mirth.connect.client.core.Operation;
import com.mirth.connect.client.core.Operations;
import com.mirth.connect.model.DatabaseTask;
import com.mirth.connect.model.ServerEventContext;
import com.mirth.connect.model.converters.ObjectXMLSerializer;
import com.mirth.connect.server.controllers.ControllerFactory;
import com.mirth.connect.server.controllers.DatabaseTaskController;

public class DatabaseTaskServlet extends MirthServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // MIRTH-1745
        response.setCharacterEncoding("UTF-8");

        if (!isUserLoggedIn(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            try {
                DatabaseTaskController databaseTaskController = ControllerFactory.getFactory().createDatabaseTaskController();
                ObjectXMLSerializer serializer = ObjectXMLSerializer.getInstance();
                PrintWriter out = response.getWriter();
                Operation operation = Operations.getOperation(request.getParameter("op"));
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                ServerEventContext context = new ServerEventContext();
                context.setUserId(getCurrentUserId(request));

                if (operation.equals(Operations.DATABASE_TASKS_GET)) {
                    if (!isUserAuthorized(request, null)) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    } else {
                        serializer.serialize(databaseTaskController.getDatabaseTasks(), out);
                    }
                } else if (operation.equals(Operations.DATABASE_TASK_RUN)) {
                    DatabaseTask databaseTask = serializer.deserialize((String) request.getParameter("databaseTask"), DatabaseTask.class);
                    parameterMap.put("databaseTask", databaseTask);

                    if (!isUserAuthorized(request, parameterMap)) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    } else {
                        String result = databaseTaskController.runDatabaseTask(databaseTask);
                        if (StringUtils.isNotBlank(result)) {
                            out.write(result);
                        }
                    }
                } else if (operation.equals(Operations.DATABASE_TASK_CANCEL)) {
                    DatabaseTask databaseTask = serializer.deserialize((String) request.getParameter("databaseTask"), DatabaseTask.class);
                    parameterMap.put("databaseTask", databaseTask);

                    if (!isUserAuthorized(request, parameterMap)) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    } else {
                        databaseTaskController.cancelDatabaseTask(databaseTask);
                    }
                }
            } catch (RuntimeIOException rio) {
                logger.debug(rio);
            } catch (Throwable t) {
                logger.error(ExceptionUtils.getStackTrace(t));
                throw new ServletException(t);
            }
        }
    }
}
