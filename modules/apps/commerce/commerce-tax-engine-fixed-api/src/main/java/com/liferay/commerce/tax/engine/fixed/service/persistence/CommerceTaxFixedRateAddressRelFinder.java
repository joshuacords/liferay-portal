/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.engine.fixed.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public interface CommerceTaxFixedRateAddressRelFinder {

	public
		com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel fetchByC_C_C_C_Z_First(
				long commerceTaxMethodId, long cpTaxCategoryId,
				long commerceCountryId, long commerceRegionId, String zip);

	public
		com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel fetchByC_C_C_Z_First(
				long commerceTaxMethodId, long commerceCountryId,
				long commerceRegionId, String zip);

	public java.util.List
		<com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel> findByC_C_C_Z(
				long commerceTaxMethodId, long commerceCountryId,
				long commerceRegionId, String zip);

	public java.util.List
		<com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel> findByC_C_C_Z(
				long commerceTaxMethodId, long commerceCountryId,
				long commerceRegionId, String zip, int start, int end);

	public java.util.List
		<com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel> findByC_C_C_C_Z(
				long commerceTaxMethodId, long cpTaxCategoryId,
				long commerceCountryId, long commerceRegionId, String zip,
				int start, int end);

}