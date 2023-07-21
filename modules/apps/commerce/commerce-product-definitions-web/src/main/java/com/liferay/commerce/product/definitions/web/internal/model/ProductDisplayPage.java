/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductDisplayPage {

	public ProductDisplayPage(
		String layout, long productDisplayPageId, String productName) {

		_layout = layout;
		_productDisplayPageId = productDisplayPageId;
		_productName = productName;
	}

	public String getLayout() {
		return _layout;
	}

	public long getProductDisplayPageId() {
		return _productDisplayPageId;
	}

	public String getProductName() {
		return _productName;
	}

	private final String _layout;
	private final long _productDisplayPageId;
	private final String _productName;

}