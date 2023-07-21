/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for ProductField. This utility wraps
 * <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductFieldServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProductFieldService
 * @generated
 */
public class ProductFieldServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductFieldServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProductField addProductField(
			long classNameId, long classPK, String name, String value)
		throws PortalException {

		return getService().addProductField(classNameId, classPK, name, value);
	}

	public static ProductField deleteProductField(long productFieldId)
		throws PortalException {

		return getService().deleteProductField(productFieldId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ProductField updateProductField(
			long productFieldId, String value)
		throws PortalException {

		return getService().updateProductField(productFieldId, value);
	}

	public static ProductFieldService getService() {
		return _service;
	}

	public static void setService(ProductFieldService service) {
		_service = service;
	}

	private static volatile ProductFieldService _service;

}