/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public interface PricingNavigationItemRegistry {

	public List<NavigationItem> getNavigationItems(
			PortletRequest portletRequest)
		throws PortalException;

}