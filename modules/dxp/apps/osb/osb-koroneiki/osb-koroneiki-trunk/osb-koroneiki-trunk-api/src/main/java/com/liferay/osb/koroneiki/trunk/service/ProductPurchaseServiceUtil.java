/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for ProductPurchase. This utility wraps
 * <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseService
 * @generated
 */
public class ProductPurchaseServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProductPurchase addProductPurchase(
			long accountId, long productEntryId, java.util.Date startDate,
			java.util.Date endDate, java.util.Date originalEndDate,
			int quantity, int status,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().addProductPurchase(
			accountId, productEntryId, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	public static ProductPurchase addProductPurchase(
			String accountKey, String productEntryKey, java.util.Date startDate,
			java.util.Date endDate, java.util.Date originalEndDate,
			int quantity, int status,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().addProductPurchase(
			accountKey, productEntryKey, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	public static ProductPurchase deleteProductPurchase(long productPurchaseId)
		throws PortalException {

		return getService().deleteProductPurchase(productPurchaseId);
	}

	public static ProductPurchase deleteProductPurchase(
			String productPurchaseKey)
		throws PortalException {

		return getService().deleteProductPurchase(productPurchaseKey);
	}

	public static List<ProductPurchase> getAccountProductEntryProductPurchases(
			long accountId, long productEntryId, int start, int end)
		throws PortalException {

		return getService().getAccountProductEntryProductPurchases(
			accountId, productEntryId, start, end);
	}

	public static List<ProductPurchase> getAccountProductPurchases(
			long accountId, int start, int end)
		throws PortalException {

		return getService().getAccountProductPurchases(accountId, start, end);
	}

	public static List<ProductPurchase> getAccountProductPurchases(
			String accountKey, int start, int end)
		throws PortalException {

		return getService().getAccountProductPurchases(accountKey, start, end);
	}

	public static int getAccountProductPurchasesCount(long accountId)
		throws PortalException {

		return getService().getAccountProductPurchasesCount(accountId);
	}

	public static int getAccountProductPurchasesCount(String accountKey)
		throws PortalException {

		return getService().getAccountProductPurchasesCount(accountKey);
	}

	public static List<ProductPurchase> getContactProductPurchases(
			long contactId, int start, int end)
		throws PortalException {

		return getService().getContactProductPurchases(contactId, start, end);
	}

	public static int getContactProductPurchasesCount(long contactId)
		throws PortalException {

		return getService().getContactProductPurchasesCount(contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ProductPurchase getProductPurchase(long productPurchaseId)
		throws PortalException {

		return getService().getProductPurchase(productPurchaseId);
	}

	public static ProductPurchase getProductPurchase(String productPurchaseKey)
		throws PortalException {

		return getService().getProductPurchase(productPurchaseKey);
	}

	public static List<ProductPurchase> getProductPurchases(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		return getService().getProductPurchases(
			domain, entityName, entityId, start, end);
	}

	public static int getProductPurchasesCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return getService().getProductPurchasesCount(
			domain, entityName, entityId);
	}

	public static ProductPurchase updateProductPurchase(
			long productPurchaseId, java.util.Date startDate,
			java.util.Date endDate, java.util.Date originalEndDate,
			int quantity, int status,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().updateProductPurchase(
			productPurchaseId, startDate, endDate, originalEndDate, quantity,
			status, productFields);
	}

	public static ProductPurchaseService getService() {
		return _service;
	}

	public static void setService(ProductPurchaseService service) {
		_service = service;
	}

	private static volatile ProductPurchaseService _service;

}