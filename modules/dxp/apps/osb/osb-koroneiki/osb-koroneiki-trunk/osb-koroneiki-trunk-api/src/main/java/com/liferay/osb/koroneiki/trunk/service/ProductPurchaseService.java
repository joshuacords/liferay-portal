/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for ProductPurchase. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ProductPurchaseService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the product purchase remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ProductPurchaseServiceUtil} if injection and service tracking are not available.
	 */
	public ProductPurchase addProductPurchase(
			long accountId, long productEntryId, Date startDate, Date endDate,
			Date originalEndDate, int quantity, int status,
			List<ProductField> productFields)
		throws PortalException;

	public ProductPurchase addProductPurchase(
			String accountKey, String productEntryKey, Date startDate,
			Date endDate, Date originalEndDate, int quantity, int status,
			List<ProductField> productFields)
		throws PortalException;

	public ProductPurchase deleteProductPurchase(long productPurchaseId)
		throws PortalException;

	public ProductPurchase deleteProductPurchase(String productPurchaseKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductPurchase> getAccountProductEntryProductPurchases(
			long accountId, long productEntryId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductPurchase> getAccountProductPurchases(
			long accountId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductPurchase> getAccountProductPurchases(
			String accountKey, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountProductPurchasesCount(long accountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountProductPurchasesCount(String accountKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductPurchase> getContactProductPurchases(
			long contactId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getContactProductPurchasesCount(long contactId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProductPurchase getProductPurchase(long productPurchaseId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductPurchase> getProductPurchases(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getProductPurchasesCount(
			String domain, String entityName, String entityId)
		throws PortalException;

	public ProductPurchase updateProductPurchase(
			long productPurchaseId, Date startDate, Date endDate,
			Date originalEndDate, int quantity, int status,
			List<ProductField> productFields)
		throws PortalException;

}