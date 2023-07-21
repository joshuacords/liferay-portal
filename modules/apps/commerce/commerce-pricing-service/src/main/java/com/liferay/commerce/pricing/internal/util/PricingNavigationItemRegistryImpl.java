/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.internal.util;

import com.liferay.commerce.pricing.internal.util.comparator.PricingNavigationItemServiceWrapperOrderComparator;
import com.liferay.commerce.pricing.util.PricingNavigationItem;
import com.liferay.commerce.pricing.util.PricingNavigationItemRegistry;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.portlet.PortletRequest;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = PricingNavigationItemRegistry.class)
public class PricingNavigationItemRegistryImpl
	implements PricingNavigationItemRegistry {

	@Override
	public List<NavigationItem> getNavigationItems(
			PortletRequest portletRequest)
		throws PortalException {

		List<NavigationItem> navigationItems = new ArrayList<>();

		List<ServiceWrapper<PricingNavigationItem>>
			pricingNavigationItemServiceWrappers = ListUtil.fromCollection(
				_serviceTrackerMap.values());

		Collections.sort(
			pricingNavigationItemServiceWrappers,
			_pricingNavigationItemServiceWrapperOrderComparator);

		for (ServiceWrapper<PricingNavigationItem>
				pricingNavigationItemServiceWrapper :
					pricingNavigationItemServiceWrappers) {

			PricingNavigationItem pricingNavigationItem =
				pricingNavigationItemServiceWrapper.getService();

			NavigationItem navigationItem =
				pricingNavigationItem.getNavigationItem(portletRequest);

			if (navigationItem == null) {
				continue;
			}

			navigationItems.add(navigationItem);
		}

		return navigationItems;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, PricingNavigationItem.class,
			"commerce.pricing.navigation.item.key",
			ServiceTrackerCustomizerFactory.
				<PricingNavigationItem>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Comparator<ServiceWrapper<PricingNavigationItem>>
		_pricingNavigationItemServiceWrapperOrderComparator =
			new PricingNavigationItemServiceWrapperOrderComparator();

	private ServiceTrackerMap<String, ServiceWrapper<PricingNavigationItem>>
		_serviceTrackerMap;

}