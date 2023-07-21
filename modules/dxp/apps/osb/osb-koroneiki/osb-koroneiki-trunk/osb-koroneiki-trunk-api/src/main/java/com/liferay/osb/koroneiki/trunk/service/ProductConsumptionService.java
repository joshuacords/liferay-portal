/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
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
 * Provides the remote service interface for ProductConsumption. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ProductConsumptionService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductConsumptionServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the product consumption remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ProductConsumptionServiceUtil} if injection and service tracking are not available.
	 */
	public ProductConsumption addProductConsumption(
			long accountId, long productEntryId, long productPurchaseId,
			Date startDate, Date endDate, List<ProductField> productFields)
		throws PortalException;

	public ProductConsumption addProductConsumption(
			String accountKey, String productEntryKey,
			String productPurchaseKey, Date startDate, Date endDate,
			List<ProductField> productFields)
		throws PortalException;

	public ProductConsumption deleteProductConsumption(
			long productConsumptionId)
		throws PortalException;

	public ProductConsumption deleteProductConsumption(
			long accountId, long productEntryId)
		throws PortalException;

	public ProductConsumption deleteProductConsumption(
			String productConsumptionKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductConsumption> getAccountProductConsumptions(
			long accountId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductConsumption> getAccountProductConsumptions(
			String accountKey, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountProductConsumptionsCount(long accountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountProductConsumptionsCount(String accountKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductConsumption> getAccountProductEntryProductConsumptions(
			long accountId, long productEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductConsumption> getContactProductConsumptions(
			long contactId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getContactProductConsumptionsCount(long contactId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProductConsumption getProductConsumption(long productConsumptionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductConsumption> getProductConsumptions(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getProductConsumptionsCount(
			String domain, String entityName, String entityId)
		throws PortalException;

}