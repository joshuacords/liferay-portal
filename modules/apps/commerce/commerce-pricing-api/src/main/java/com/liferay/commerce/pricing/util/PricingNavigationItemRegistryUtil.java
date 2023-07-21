/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.portlet.PortletRequest;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Riccardo Alberti
 */
public class PricingNavigationItemRegistryUtil {

	public static List<NavigationItem> getNavigationItems(
			PortletRequest portletRequest)
		throws PortalException {

		PricingNavigationItemRegistry pricingNavigationItemRegistry =
			_serviceTracker.getService();

		return pricingNavigationItemRegistry.getNavigationItems(portletRequest);
	}

	private static final ServiceTracker<?, PricingNavigationItemRegistry>
		_serviceTracker = ServiceTrackerFactory.open(
			FrameworkUtil.getBundle(PricingNavigationItemRegistryUtil.class),
			PricingNavigationItemRegistry.class);

}