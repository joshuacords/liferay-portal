/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionLinkException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alessio Antonio Rendina
 */
public class RelatedProductUtil {

	public static CPDefinitionLink upsertCPDefinitionLink(
			CPDefinitionLinkService cpDefinitionLinkService,
			CPDefinitionService cpDefinitionService,
			RelatedProduct relatedProduct, long cpDefinitionId,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			CPDefinitionLink cpDefinitionLink =
				cpDefinitionLinkService.getCPDefinitionLink(
					relatedProduct.getId());

			return cpDefinitionLinkService.updateCPDefinitionLink(
				relatedProduct.getId(),
				GetterUtil.get(
					relatedProduct.getPriority(),
					cpDefinitionLink.getPriority()),
				serviceContext);
		}
		catch (NoSuchCPDefinitionLinkException nscpdle) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find relatedProduct with ID: " +
						relatedProduct.getId());
			}
		}

		CPDefinition cpDefinition = null;

		if (Validator.isNotNull(
				relatedProduct.getProductExternalReferenceCode())) {

			cpDefinition =
				cpDefinitionService.
					fetchCPDefinitionByCProductExternalReferenceCode(
						serviceContext.getCompanyId(),
						relatedProduct.getProductExternalReferenceCode());

			if (cpDefinition == null) {
				throw new NoSuchCPDefinitionException(
					"Unable to find Product with externalReferenceCode: " +
						relatedProduct.getProductExternalReferenceCode());
			}
		}
		else {
			cpDefinition = cpDefinitionService.fetchCPDefinitionByCProductId(
				relatedProduct.getProductId());

			if (cpDefinition == null) {
				throw new NoSuchCPDefinitionException(
					"Unable to find Product with ID: " +
						relatedProduct.getProductId());
			}
		}

		return cpDefinitionLinkService.addCPDefinitionLink(
			cpDefinitionId, cpDefinition.getCProductId(),
			GetterUtil.get(relatedProduct.getPriority(), 0D),
			relatedProduct.getType(), serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RelatedProductUtil.class);

}