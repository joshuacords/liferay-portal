/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

/**
 * @author Jorge Ferrer
 * @author Dennis Ju
 * @author Brian Wing Shun Chan
 */
public class PortletListerFactoryUtil {

	public static PortletLister getPortletLister() {
		return getPortletListerFactory().getPortletLister();
	}

	public static PortletListerFactory getPortletListerFactory() {
		return _portletListerFactory;
	}

	public void setPortletListerFactory(
		PortletListerFactory portletListerFactory) {

		_portletListerFactory = portletListerFactory;
	}

	private static PortletListerFactory _portletListerFactory;

}