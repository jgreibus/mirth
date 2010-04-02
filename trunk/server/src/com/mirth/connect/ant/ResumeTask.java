/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * http://www.mirthcorp.com
 *
 * The software in this package is published under the terms of the MPL
 * license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */

package com.mirth.connect.ant;

import org.apache.tools.ant.BuildException;

import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.model.ChannelStatus;
import com.mirth.connect.model.ChannelStatus.State;

/**
 * an ant task to resume all mirth channels
 * 
 * @author andrzej@coalese.com
 */

public class ResumeTask extends AbstractMirthTask {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */

	public void executeTask() throws BuildException {
		try {
			connectClient();
			commandResumeAll();
			disconnectClient();
		} catch (ClientException e) {
			throw (new BuildException("Mirth client exception caught: " + e.getMessage(), e));
		}
	}

	private void commandResumeAll() throws ClientException {
		for (ChannelStatus channel : client.getChannelStatusList()) {
			if (channel.getState().equals(State.PAUSED)) {
				client.resumeChannel(channel.getChannelId());
				System.out.println("Channel " + channel.getName() + " Resumed");
			}
		}
	}

}
