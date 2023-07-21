/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax;

import java.math.BigDecimal;

/**
 * @author Marco Leo
 */
public class CommerceTaxCalculateRequest {

	public long getChannelGroupId() {
		return _channelGroupId;
	}

	public long getCommerceBillingAddressId() {
		return _commerceBillingAddressId;
	}

	public long getCommerceShippingAddressId() {
		return _commerceShippingAddressId;
	}

	public long getCommerceTaxMethodId() {
		return _commerceTaxMethodId;
	}

	public BigDecimal getPrice() {
		return _price;
	}

	public long getTaxCategoryId() {
		return _taxCategoryId;
	}

	public boolean isIncludeTax() {
		return _includeTax;
	}

	public boolean isPercentage() {
		return _percentage;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public boolean isWithTaxAmount() {
		return _includeTax;
	}

	public void setChannelGroupId(long channelGroupId) {
		_channelGroupId = channelGroupId;
	}

	public void setCommerceBillingAddressId(long commerceBillingAddressId) {
		_commerceBillingAddressId = commerceBillingAddressId;
	}

	public void setCommerceShippingAddressId(long commerceShippingAddressId) {
		_commerceShippingAddressId = commerceShippingAddressId;
	}

	public void setCommerceTaxMethodId(long commerceTaxMethodId) {
		_commerceTaxMethodId = commerceTaxMethodId;
	}

	public void setIncludeTax(boolean includeTax) {
		_includeTax = includeTax;
	}

	public void setPercentage(boolean percentage) {
		_percentage = percentage;
	}

	public void setPrice(BigDecimal price) {
		_price = price;
	}

	public void setTaxCategoryId(long taxCategoryId) {
		_taxCategoryId = taxCategoryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public void setWithTaxAmount(boolean includeTax) {
		_includeTax = includeTax;
	}

	private long _channelGroupId;
	private long _commerceBillingAddressId;
	private long _commerceShippingAddressId;
	private long _commerceTaxMethodId;
	private boolean _includeTax;
	private boolean _percentage;
	private BigDecimal _price;
	private long _taxCategoryId;

}