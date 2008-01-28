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

package com.webreach.mirth.connectors.js;

import java.util.Properties;

import com.webreach.mirth.model.ComponentProperties;

public class JavaScriptReaderProperties implements ComponentProperties
{
	public static final String name = "JavaScript Reader";
	
    public static final String DATATYPE = "DataType";
    public static final String JAVASCRIPT_HOST = "host";
    public static final String JAVASCRIPT_POLLING_TYPE = "pollingType";
    public static final String JAVASCRIPT_POLLING_TIME = "pollingTime";
    public static final String JAVASCRIPT_POLLING_FREQUENCY = "pollingFrequency";
    public static final String JAVASCRIPT_SCRIPT = "script";
 
    public Properties getDefaults()
    {
        Properties properties = new Properties();
        properties.put(DATATYPE, name);
        properties.put(JAVASCRIPT_HOST, "sink");
        properties.put(JAVASCRIPT_POLLING_FREQUENCY, "5000");
        properties.put(JAVASCRIPT_POLLING_TYPE, "interval");
        properties.put(JAVASCRIPT_POLLING_TIME, "12:00 AM");
        properties.put(JAVASCRIPT_SCRIPT, "");
        return properties;
    }
}
