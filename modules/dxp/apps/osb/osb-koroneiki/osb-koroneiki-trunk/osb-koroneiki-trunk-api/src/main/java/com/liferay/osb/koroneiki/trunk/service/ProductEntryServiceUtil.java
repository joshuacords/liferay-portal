/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for ProductEntry. This utility wraps
 * <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryService
 * @generated
 */
public class ProductEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProductEntry addProductEntry(
			String name,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().addProductEntry(name, productFields);
	}

	public static ProductEntry deleteProductEntry(long productEntryId)
		throws PortalException {

		return getService().deleteProductEntry(productEntryId);
	}

	public static ProductEntry deleteProductEntry(String productEntryKey)
		throws PortalException {

		return getService().deleteProductEntry(productEntryKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<ProductEntry> getProductEntries(int start, int end)
		throws PortalException {

		return getService().getProductEntries(start, end);
	}

	public static List<ProductEntry> getProductEntries(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		return getService().getProductEntries(
			domain, entityName, entityId, start, end);
	}

	public static int getProductEntriesCount() throws PortalException {
		return getService().getProductEntriesCount();
	}

	public static int getProductEntriesCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return getService().getProductEntriesCount(
			domain, entityName, entityId);
	}

	public static ProductEntry getProductEntry(long productEntryId)
		throws PortalException {

		return getService().getProductEntry(productEntryId);
	}

	public static ProductEntry getProductEntry(String productEntryKey)
		throws PortalException {

		return getService().getProductEntry(productEntryKey);
	}

	public static ProductEntry getProductEntryByName(String name)
		throws PortalException {

		return getService().getProductEntryByName(name);
	}

	public static ProductEntry updateProductEntry(
			long productEntryId, String name,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().updateProductEntry(
			productEntryId, name, productFields);
	}

	public static ProductEntry updateProductEntry(
			String productEntryKey, String name,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().updateProductEntry(
			productEntryKey, name, productFields);
	}

	public static ProductEntryService getService() {
		return _service;
	}

	public static void setService(ProductEntryService service) {
		_service = service;
	}

	private static volatile ProductEntryService _service;

}