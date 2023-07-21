/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.engine.fixed.model.impl;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPTaxCategoryLocalServiceUtil;
import com.liferay.commerce.service.CommerceCountryLocalServiceUtil;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.commerce.tax.service.CommerceTaxMethodLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceTaxFixedRateAddressRelImpl
	extends CommerceTaxFixedRateAddressRelBaseImpl {

	public CommerceTaxFixedRateAddressRelImpl() {
	}

	@Override
	public CommerceCountry getCommerceCountry() throws PortalException {
		if (getCommerceCountryId() > 0) {
			return CommerceCountryLocalServiceUtil.getCommerceCountry(
				getCommerceCountryId());
		}

		return null;
	}

	@Override
	public CommerceRegion getCommerceRegion() throws PortalException {
		if (getCommerceRegionId() > 0) {
			return CommerceRegionLocalServiceUtil.getCommerceRegion(
				getCommerceRegionId());
		}

		return null;
	}

	@Override
	public CommerceTaxMethod getCommerceTaxMethod() throws PortalException {
		if (getCommerceTaxMethodId() > 0) {
			return CommerceTaxMethodLocalServiceUtil.getCommerceTaxMethod(
				getCommerceTaxMethodId());
		}

		return null;
	}

	@Override
	public CPTaxCategory getCPTaxCategory() throws PortalException {
		return CPTaxCategoryLocalServiceUtil.getCPTaxCategory(
			getCPTaxCategoryId());
	}

}