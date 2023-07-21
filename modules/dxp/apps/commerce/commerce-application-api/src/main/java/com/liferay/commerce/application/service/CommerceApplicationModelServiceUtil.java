/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.service;

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceApplicationModel. This utility wraps
 * <code>com.liferay.commerce.application.service.impl.CommerceApplicationModelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelService
 * @generated
 */
public class CommerceApplicationModelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.application.service.impl.CommerceApplicationModelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceApplicationModel addCommerceApplicationModel(
			long userId, long commerceApplicationBrandId, String name,
			String year)
		throws PortalException {

		return getService().addCommerceApplicationModel(
			userId, commerceApplicationBrandId, name, year);
	}

	public static void deleteCommerceApplicationModel(
			long commerceApplicationModelId)
		throws PortalException {

		getService().deleteCommerceApplicationModel(commerceApplicationModelId);
	}

	public static CommerceApplicationModel getCommerceApplicationModel(
			long commerceApplicationModelId)
		throws PortalException {

		return getService().getCommerceApplicationModel(
			commerceApplicationModelId);
	}

	public static List<CommerceApplicationModel> getCommerceApplicationModels(
		long commerceApplicationBrandId, int start, int end) {

		return getService().getCommerceApplicationModels(
			commerceApplicationBrandId, start, end);
	}

	public static List<CommerceApplicationModel>
		getCommerceApplicationModelsByCompanyId(
			long companyId, int start, int end) {

		return getService().getCommerceApplicationModelsByCompanyId(
			companyId, start, end);
	}

	public static int getCommerceApplicationModelsCount(
		long commerceApplicationBrandId) {

		return getService().getCommerceApplicationModelsCount(
			commerceApplicationBrandId);
	}

	public static int getCommerceApplicationModelsCountByCompanyId(
		long companyId) {

		return getService().getCommerceApplicationModelsCountByCompanyId(
			companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceApplicationModel updateCommerceApplicationModel(
			long commerceApplicationModelId, String name, String year)
		throws PortalException {

		return getService().updateCommerceApplicationModel(
			commerceApplicationModelId, name, year);
	}

	public static CommerceApplicationModelService getService() {
		return _service;
	}

	public static void setService(CommerceApplicationModelService service) {
		_service = service;
	}

	private static volatile CommerceApplicationModelService _service;

}