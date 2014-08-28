/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.plugins.datatypes.edi;

import com.mirth.connect.donkey.util.DonkeyElement;
import com.mirth.connect.model.datatype.DataTypeProperties;

public class EDIDataTypeProperties extends DataTypeProperties {

    public EDIDataTypeProperties() {
        serializationProperties = new EDISerializationProperties();
    }

    @Override
    public void migrate3_0_1(DonkeyElement element) {}
    
}