/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.portlet;

import com.liferay.portal.kernel.portlet.PortletRequestModel;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author     Brian Wing Shun Chan
 * @author     Raymond Augé
 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
 *             PortletRequestModel}
 */
@Deprecated
public class PortletRequestUtil {

	public static String toXML(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		PortletRequestModel portletRequestModel = new PortletRequestModel(
			portletRequest, portletResponse);

		return portletRequestModel.toXML();
	}

}