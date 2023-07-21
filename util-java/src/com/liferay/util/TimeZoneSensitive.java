/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.property.XProperty;

/**
 * @author Samuel Kong
 */
public class TimeZoneSensitive extends XProperty {

	public static final String PROPERTY_NAME = "X-LIFERAY-TIMEZONE-SENSITIVE";

	public TimeZoneSensitive() {
		super(PROPERTY_NAME);
	}

	public TimeZoneSensitive(ParameterList list, String value) {
		super(PROPERTY_NAME, list, value);
	}

	public TimeZoneSensitive(String value) {
		super(PROPERTY_NAME, value);
	}

	public void setValue(boolean value) {
		setValue(value ? "TRUE" : "FALSE");
	}

}