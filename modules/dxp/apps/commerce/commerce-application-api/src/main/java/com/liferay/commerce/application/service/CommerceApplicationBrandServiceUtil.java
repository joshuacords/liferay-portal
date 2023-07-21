/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.service;

import com.liferay.commerce.application.model.CommerceApplicationBrand;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceApplicationBrand. This utility wraps
 * <code>com.liferay.commerce.application.service.impl.CommerceApplicationBrandServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandService
 * @generated
 */
public class CommerceApplicationBrandServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.application.service.impl.CommerceApplicationBrandServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceApplicationBrand addCommerceApplicationBrand(
			long userId, String name, boolean logo, byte[] logoBytes)
		throws PortalException {

		return getService().addCommerceApplicationBrand(
			userId, name, logo, logoBytes);
	}

	public static void deleteCommerceApplicationBrand(
			long commerceApplicationBrandId)
		throws PortalException {

		getService().deleteCommerceApplicationBrand(commerceApplicationBrandId);
	}

	public static CommerceApplicationBrand getCommerceApplicationBrand(
			long commerceApplicationBrandId)
		throws PortalException {

		return getService().getCommerceApplicationBrand(
			commerceApplicationBrandId);
	}

	public static List<CommerceApplicationBrand> getCommerceApplicationBrands(
		long companyId, int start, int end) {

		return getService().getCommerceApplicationBrands(companyId, start, end);
	}

	public static int getCommerceApplicationBrandsCount(long companyId) {
		return getService().getCommerceApplicationBrandsCount(companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceApplicationBrand updateCommerceApplicationBrand(
			long commerceApplicationBrandId, String name, boolean logo,
			byte[] logoBytes)
		throws PortalException {

		return getService().updateCommerceApplicationBrand(
			commerceApplicationBrandId, name, logo, logoBytes);
	}

	public static CommerceApplicationBrandService getService() {
		return _service;
	}

	public static void setService(CommerceApplicationBrandService service) {
		_service = service;
	}

	private static volatile CommerceApplicationBrandService _service;

}