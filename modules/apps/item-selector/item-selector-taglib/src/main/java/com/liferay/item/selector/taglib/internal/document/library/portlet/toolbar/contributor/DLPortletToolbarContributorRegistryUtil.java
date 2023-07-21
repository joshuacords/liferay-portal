/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.taglib.internal.document.library.portlet.toolbar.contributor;

import com.liferay.document.library.portlet.toolbar.contributor.DLPortletToolbarContributor;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alejandro Tardín
 */
@Component(
	immediate = true, service = DLPortletToolbarContributorRegistryUtil.class
)
public class DLPortletToolbarContributorRegistryUtil {

	public static DLPortletToolbarContributor getDLPortletToolbarContributor() {
		return _dlPortletToolbarContributor;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_dlPortletToolbarContributor =
			new AggregateDLPortletToolbarContributor();
		_serviceTrackerList = ServiceTrackerListFactory.open(
			bundleContext, DLPortletToolbarContributor.class);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerList.close();
	}

	private static DLPortletToolbarContributor _dlPortletToolbarContributor;

	private ServiceTrackerList
		<DLPortletToolbarContributor, DLPortletToolbarContributor>
			_serviceTrackerList;

	private class AggregateDLPortletToolbarContributor
		implements DLPortletToolbarContributor {

		@Override
		public List<Menu> getPortletTitleMenus(
			PortletRequest portletRequest, PortletResponse portletResponse) {

			List<Menu> menus = new ArrayList<>();

			_serviceTrackerList.forEach(
				dlPortletToolbarContributor -> menus.addAll(
					dlPortletToolbarContributor.getPortletTitleMenus(
						portletRequest, portletResponse)));

			return menus;
		}

	}

}