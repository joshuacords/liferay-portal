/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CProductLocalServiceUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionLinkImpl extends CPDefinitionLinkBaseImpl {

	public CPDefinitionLinkImpl() {
	}

	@Override
	public CPDefinition getCPDefinition() {
		return CPDefinitionLocalServiceUtil.fetchCPDefinition(
			getCPDefinitionId());
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public CPDefinition getCPDefinition1() {
		return getCPDefinition();
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public CPDefinition getCPDefinition2() {
		CProduct cProduct = getCProduct();

		if (cProduct == null) {
			return null;
		}

		return CPDefinitionLocalServiceUtil.fetchCPDefinition(
			cProduct.getPublishedCPDefinitionId());
	}

	@Override
	public CProduct getCProduct() {
		return CProductLocalServiceUtil.fetchCProduct(getCProductId());
	}

}