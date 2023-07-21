/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.catalog;

import java.math.BigDecimal;

/**
 * @author Alessio Antonio Rendina
 */
public interface CPSku {

	public long getCPInstanceId();

	public String getCPInstanceUuid();

	public String getExternalReferenceCode();

	public String getGtin();

	public String getManufacturerPartNumber();

	public BigDecimal getPrice();

	public BigDecimal getPromoPrice();

	public String getSku();

	public boolean isPublished();

	public boolean isPurchasable();

}