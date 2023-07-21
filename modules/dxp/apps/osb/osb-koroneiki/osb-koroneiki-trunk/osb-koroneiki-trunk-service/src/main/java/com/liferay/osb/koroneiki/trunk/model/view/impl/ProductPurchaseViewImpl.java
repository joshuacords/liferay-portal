/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.view.impl;

import com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView;
import com.liferay.petra.string.StringPool;

import java.io.Serializable;

/**
 * @author Kyle Bischof
 */
public class ProductPurchaseViewImpl implements ProductPurchaseView {

	public ProductPurchaseViewImpl() {
	}

	public long getAccountId() {
		return _accountId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Class<?> getModelClass() {
		return ProductPurchaseView.class;
	}

	public String getModelClassName() {
		return ProductPurchaseView.class.getName();
	}

	public Serializable getPrimaryKeyObj() {
		return String.valueOf(_accountId) + StringPool.UNDERLINE +
			String.valueOf(_productEntryId);
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	private long _accountId;
	private long _companyId;
	private long _productEntryId;

}