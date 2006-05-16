/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Mirth.
 *
 * The Initial Developer of the Original Code is
 * WebReach, Inc.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Gerald Bortis <geraldb@webreachinc.com>
 *
 * ***** END LICENSE BLOCK ***** */


package com.webreach.mirth.server.core.util.tests;

import junit.framework.TestCase;

import com.webreach.mirth.server.core.util.DatabaseConnection;
import com.webreach.mirth.server.services.ConfigurationService;
import com.webreach.mirth.server.services.ServiceException;

public class ConfigurationTest extends TestCase {
	private ConfigurationService configuration;
	private DatabaseConnection dbConnection;
	
	protected void setUp() throws Exception {
		super.setUp();
		configuration = new ConfigurationService();
		dbConnection = new DatabaseConnection();
		StringBuffer statement = new StringBuffer();
		statement.append("DROP SEQUENCE SEQ_CONFIGURATION IF EXISTS;");
		statement.append("CREATE SEQUENCE SEQ_CONFIGURATION START WITH 1 INCREMENT BY 1;");
		dbConnection.update(statement.toString());
		dbConnection.close();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dbConnection = new DatabaseConnection();
		dbConnection.update("DROP SEQUENCE SEQ_CONFIGURATION IF EXISTS;");
		dbConnection.close();
	}
	
	public void testGetProperties() {
		
	}
	
	public void testGetChannels() {

	}
	
	public void testGetTransports() {

	}
	
	public void testStore() {
		
	}
	
	public void testGetNextId() {
		int id1 = 0, id2 = 0, id3 = 0;
		try {
			id1 = configuration.getNextId();
			id2 = configuration.getNextId();
			id3 = configuration.getNextId();

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		assertEquals(id2 - 1, id1);
		assertEquals(id3 - 1, id2);
	}
}
