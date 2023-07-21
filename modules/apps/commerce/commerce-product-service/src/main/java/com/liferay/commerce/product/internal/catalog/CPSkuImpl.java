/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.catalog;

import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.model.CPInstance;

import java.math.BigDecimal;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSkuImpl implements CPSku {

	public CPSkuImpl(CPInstance cpInstance) {
		_cpInstance = cpInstance;
	}

	@Override
	public long getCPInstanceId() {
		return _cpInstance.getCPInstanceId();
	}

	@Override
	public String getCPInstanceUuid() {
		return _cpInstance.getCPInstanceUuid();
	}

	@Override
	public String getExternalReferenceCode() {
		return _cpInstance.getExternalReferenceCode();
	}

	@Override
	public String getGtin() {
		return _cpInstance.getGtin();
	}

	@Override
	public String getManufacturerPartNumber() {
		return _cpInstance.getManufacturerPartNumber();
	}

	@Override
	public BigDecimal getPrice() {
		return _cpInstance.getPrice();
	}

	@Override
	public BigDecimal getPromoPrice() {
		return _cpInstance.getPromoPrice();
	}

	@Override
	public String getSku() {
		return _cpInstance.getSku();
	}

	@Override
	public boolean isPublished() {
		return _cpInstance.isPublished();
	}

	@Override
	public boolean isPurchasable() {
		return _cpInstance.isPurchasable();
	}

	private final CPInstance _cpInstance;

}