/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.exception.DuplicateCProductException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.base.CProductLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CProductLocalServiceImpl extends CProductLocalServiceBaseImpl {

	@Override
	public CProduct addCProduct(
			long groupId, long userId, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		if (Validator.isBlank(externalReferenceCode)) {
			externalReferenceCode = null;
		}

		validate(user.getCompanyId(), externalReferenceCode);

		CProduct cProduct = cProductLocalService.createCProduct(
			counterLocalService.increment());

		cProduct.setGroupId(groupId);
		cProduct.setCompanyId(user.getCompanyId());
		cProduct.setUserId(user.getUserId());
		cProduct.setUserName(user.getFullName());

		cProduct.setExternalReferenceCode(externalReferenceCode);
		cProduct.setLatestVersion(1);

		return cProductPersistence.update(cProduct);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CProduct deleteCProduct(CProduct cProduct) throws PortalException {

		// Commerce product definitions

		cpDefinitionLocalService.deleteCPDefinitions(
			cProduct.getCProductId(), WorkflowConstants.STATUS_ANY);

		// Commerce product definition links

		cpDefinitionLinkLocalService.deleteCPDefinitionLinksByCProductId(
			cProduct.getCProductId());

		// Commerce product

		cProductPersistence.remove(cProduct);

		return cProduct;
	}

	@Override
	public CProduct deleteCProduct(long cProductId) throws PortalException {
		CProduct cProduct = cProductPersistence.findByPrimaryKey(cProductId);

		return cProductLocalService.deleteCProduct(cProduct);
	}

	@Override
	public CProduct getCProductByCPInstanceUuid(String cpInstanceUuid)
		throws PortalException {

		CPInstance cpInstance =
			cpInstancePersistence.fetchByCPInstanceUuid_First(
				cpInstanceUuid, null);

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		return cpDefinition.getCProduct();
	}

	@Override
	public int increment(long cProductId) throws PortalException {
		CProduct cProduct = cProductLocalService.getCProduct(cProductId);

		cProduct.setLatestVersion(cProduct.getLatestVersion() + 1);

		cProduct = cProductPersistence.update(cProduct);

		return cProduct.getLatestVersion();
	}

	@Override
	public CProduct updateCProductExternalReferenceCode(
			long cProductId, String externalReferenceCode)
		throws PortalException {

		CProduct cProduct = cProductLocalService.getCProduct(cProductId);

		cProduct.setExternalReferenceCode(externalReferenceCode);

		cProduct = cProductPersistence.update(cProduct);

		reindexCPDefinition(cProduct.getPublishedCPDefinitionId());

		return cProduct;
	}

	@Override
	public CProduct updatePublishedCPDefinitionId(
			long cProductId, long publishedCPDefinitionId)
		throws PortalException {

		CProduct cProduct = cProductLocalService.getCProduct(cProductId);

		long originalPublishedCPDefinitionId =
			cProduct.getPublishedCPDefinitionId();

		if (originalPublishedCPDefinitionId == publishedCPDefinitionId) {
			return cProduct;
		}

		cProduct.setPublishedCPDefinitionId(publishedCPDefinitionId);

		cProduct = cProductPersistence.update(cProduct);

		reindexCPDefinition(originalPublishedCPDefinitionId);
		reindexCPDefinition(publishedCPDefinitionId);

		return cProduct;
	}

	protected void reindexCPDefinition(long cpDefinitionId)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(CPDefinition.class.getName(), cpDefinitionId);
	}

	protected void validate(long companyId, String externalReferenceCode)
		throws PortalException {

		if (Validator.isNull(externalReferenceCode)) {
			return;
		}

		CProduct cProduct = cProductPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);

		if (cProduct != null) {
			throw new DuplicateCProductException(
				"There is another commerce product with external reference " +
					"code " + externalReferenceCode);
		}
	}

}