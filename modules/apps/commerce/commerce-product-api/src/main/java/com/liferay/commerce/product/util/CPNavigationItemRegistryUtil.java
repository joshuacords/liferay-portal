/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.portlet.PortletRequest;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Alessio Antonio Rendina
 */
public class CPNavigationItemRegistryUtil {

	public static List<NavigationItem> getNavigationItems(
			PortletRequest portletRequest)
		throws PortalException {

		CPNavigationItemRegistry cpNavigationItemRegistry =
			_serviceTracker.getService();

		return cpNavigationItemRegistry.getNavigationItems(portletRequest);
	}

	private static final ServiceTracker<?, CPNavigationItemRegistry>
		_serviceTracker = ServiceTrackerFactory.open(
			FrameworkUtil.getBundle(CPNavigationItemRegistryUtil.class),
			CPNavigationItemRegistry.class);

}