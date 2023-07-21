/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
public class CPCompareHelperUtil {

	public static void addCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException {

		CPCompareHelper cpCompareHelper = _serviceTracker.getService();

		cpCompareHelper.addCompareProduct(
			groupId, commerceAccountId, cpDefinitionId, httpSession);
	}

	public static List<Long> getCPDefinitionIds(
			long groupId, long commerceAccountId, HttpSession httpSession)
		throws PortalException {

		CPCompareHelper cpCompareHelper = _serviceTracker.getService();

		return cpCompareHelper.getCPDefinitionIds(
			groupId, commerceAccountId, httpSession);
	}

	public static void removeCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException {

		CPCompareHelper cpCompareHelper = _serviceTracker.getService();

		cpCompareHelper.removeCompareProduct(
			groupId, commerceAccountId, cpDefinitionId, httpSession);
	}

	public static void setCPDefinitionIds(
		long groupId, List<Long> cpDefinitionIds, HttpSession httpSession) {

		CPCompareHelper cpCompareHelper = _serviceTracker.getService();

		cpCompareHelper.setCPDefinitionIds(
			groupId, cpDefinitionIds, httpSession);
	}

	private static final ServiceTracker<?, CPCompareHelper> _serviceTracker =
		ServiceTrackerFactory.open(
			FrameworkUtil.getBundle(CPCompareHelperUtil.class),
			CPCompareHelper.class);

}