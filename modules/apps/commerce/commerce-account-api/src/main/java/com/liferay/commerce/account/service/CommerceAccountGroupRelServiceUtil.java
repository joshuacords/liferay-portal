/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service;

import com.liferay.commerce.account.model.CommerceAccountGroupRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceAccountGroupRel. This utility wraps
 * <code>com.liferay.commerce.account.service.impl.CommerceAccountGroupRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupRelService
 * @generated
 */
public class CommerceAccountGroupRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.account.service.impl.CommerceAccountGroupRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceAccountGroupRel addCommerceAccountGroupRel(
			String className, long classPK, long commerceAccountGroupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceAccountGroupRel(
			className, classPK, commerceAccountGroupId, serviceContext);
	}

	public static void deleteCommerceAccountGroupRel(
			long commerceAccountGroupRelId)
		throws PortalException {

		getService().deleteCommerceAccountGroupRel(commerceAccountGroupRelId);
	}

	public static void deleteCommerceAccountGroupRels(
		String className, long classPK) {

		getService().deleteCommerceAccountGroupRels(className, classPK);
	}

	public static CommerceAccountGroupRel getCommerceAccountGroupRel(
			long commerceAccountGroupRelId)
		throws PortalException {

		return getService().getCommerceAccountGroupRel(
			commerceAccountGroupRelId);
	}

	public static List<CommerceAccountGroupRel> getCommerceAccountGroupRels(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceAccountGroupRel> orderByComparator)
		throws PortalException {

		return getService().getCommerceAccountGroupRels(
			commerceAccountGroupId, start, end, orderByComparator);
	}

	public static List<CommerceAccountGroupRel> getCommerceAccountGroupRels(
			String className, long classPK, int start, int end,
			OrderByComparator<CommerceAccountGroupRel> orderByComparator)
		throws PortalException {

		return getService().getCommerceAccountGroupRels(
			className, classPK, start, end, orderByComparator);
	}

	public static int getCommerceAccountGroupRelsCount(
			long commerceAccountGroupId)
		throws PortalException {

		return getService().getCommerceAccountGroupRelsCount(
			commerceAccountGroupId);
	}

	public static int getCommerceAccountGroupRelsCount(
			String className, long classPK)
		throws PortalException {

		return getService().getCommerceAccountGroupRelsCount(
			className, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceAccountGroupRelService getService() {
		return _service;
	}

	public static void setService(CommerceAccountGroupRelService service) {
		_service = service;
	}

	private static volatile CommerceAccountGroupRelService _service;

}