/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for ProductConsumption. This utility wraps
 * <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductConsumptionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionService
 * @generated
 */
public class ProductConsumptionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductConsumptionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProductConsumption addProductConsumption(
			long accountId, long productEntryId, long productPurchaseId,
			java.util.Date startDate, java.util.Date endDate,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().addProductConsumption(
			accountId, productEntryId, productPurchaseId, startDate, endDate,
			productFields);
	}

	public static ProductConsumption addProductConsumption(
			String accountKey, String productEntryKey,
			String productPurchaseKey, java.util.Date startDate,
			java.util.Date endDate,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().addProductConsumption(
			accountKey, productEntryKey, productPurchaseKey, startDate, endDate,
			productFields);
	}

	public static ProductConsumption deleteProductConsumption(
			long productConsumptionId)
		throws PortalException {

		return getService().deleteProductConsumption(productConsumptionId);
	}

	public static ProductConsumption deleteProductConsumption(
			long accountId, long productEntryId)
		throws PortalException {

		return getService().deleteProductConsumption(accountId, productEntryId);
	}

	public static ProductConsumption deleteProductConsumption(
			String productConsumptionKey)
		throws PortalException {

		return getService().deleteProductConsumption(productConsumptionKey);
	}

	public static List<ProductConsumption> getAccountProductConsumptions(
			long accountId, int start, int end)
		throws PortalException {

		return getService().getAccountProductConsumptions(
			accountId, start, end);
	}

	public static List<ProductConsumption> getAccountProductConsumptions(
			String accountKey, int start, int end)
		throws PortalException {

		return getService().getAccountProductConsumptions(
			accountKey, start, end);
	}

	public static int getAccountProductConsumptionsCount(long accountId)
		throws PortalException {

		return getService().getAccountProductConsumptionsCount(accountId);
	}

	public static int getAccountProductConsumptionsCount(String accountKey)
		throws PortalException {

		return getService().getAccountProductConsumptionsCount(accountKey);
	}

	public static List<ProductConsumption>
			getAccountProductEntryProductConsumptions(
				long accountId, long productEntryId)
		throws PortalException {

		return getService().getAccountProductEntryProductConsumptions(
			accountId, productEntryId);
	}

	public static List<ProductConsumption> getContactProductConsumptions(
			long contactId, int start, int end)
		throws PortalException {

		return getService().getContactProductConsumptions(
			contactId, start, end);
	}

	public static int getContactProductConsumptionsCount(long contactId)
		throws PortalException {

		return getService().getContactProductConsumptionsCount(contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ProductConsumption getProductConsumption(
			long productConsumptionId)
		throws PortalException {

		return getService().getProductConsumption(productConsumptionId);
	}

	public static ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws PortalException {

		return getService().getProductConsumption(productConsumptionKey);
	}

	public static List<ProductConsumption> getProductConsumptions(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		return getService().getProductConsumptions(
			domain, entityName, entityId, start, end);
	}

	public static int getProductConsumptionsCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return getService().getProductConsumptionsCount(
			domain, entityName, entityId);
	}

	public static ProductConsumptionService getService() {
		return _service;
	}

	public static void setService(ProductConsumptionService service) {
		_service = service;
	}

	private static volatile ProductConsumptionService _service;

}