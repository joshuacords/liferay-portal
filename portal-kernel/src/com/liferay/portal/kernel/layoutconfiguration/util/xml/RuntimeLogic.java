/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.layoutconfiguration.util.xml;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class RuntimeLogic {

	public static final String CLOSE_2_TAG = "/>";

	public abstract String getClose1Tag();

	public String getClose2Tag() {
		return CLOSE_2_TAG;
	}

	public abstract String getOpenTag();

	public abstract String processXML(String xml) throws Exception;

}