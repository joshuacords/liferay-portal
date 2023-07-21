/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalServiceUtil;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalServiceUtil;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class ProductConsumptionImpl extends ProductConsumptionBaseImpl {

	public ProductConsumptionImpl() {
	}

	public Account getAccount() throws PortalException {
		return AccountLocalServiceUtil.getAccount(getAccountId());
	}

	public String getAccountKey() throws PortalException {
		if (_accountKey != null) {
			return _accountKey;
		}

		Account account = getAccount();

		return account.getAccountKey();
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			ProductConsumption.class.getName(), getProductConsumptionId(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public ProductEntry getProductEntry() throws PortalException {
		return ProductEntryLocalServiceUtil.getProductEntry(
			getProductEntryId());
	}

	public String getProductEntryKey() throws PortalException {
		ProductEntry productEntry = getProductEntry();

		return productEntry.getProductEntryKey();
	}

	public List<ProductField> getProductFields() {
		return ProductFieldLocalServiceUtil.getProductFields(
			ProductConsumption.class.getName(), getProductConsumptionId());
	}

	public Map<String, String> getProductFieldsMap() {
		Map<String, String> productFieldsMap = new HashMap<>();

		List<ProductField> productFields = getProductFields();

		for (ProductField productField : productFields) {
			productFieldsMap.put(
				productField.getName(), productField.getValue());
		}

		return productFieldsMap;
	}

	public ProductPurchase getProductPurchase() throws PortalException {
		return ProductPurchaseLocalServiceUtil.fetchProductPurchase(
			getProductPurchaseId());
	}

	public String getProductPurchaseKey() throws PortalException {
		if (_productPurchaseKey != null) {
			return _productPurchaseKey;
		}

		ProductPurchase productPurchase = getProductPurchase();

		if (productPurchase != null) {
			return productPurchase.getProductPurchaseKey();
		}

		return StringPool.BLANK;
	}

	public boolean isPerpetual() {
		if (getStartDate() != null) {
			return false;
		}

		return true;
	}

	public void setAccountKey(String accountKey) {
		_accountKey = accountKey;
	}

	public void setProductPurchaseKey(String productPurchaseKey) {
		_productPurchaseKey = productPurchaseKey;
	}

	private String _accountKey;
	private String _productPurchaseKey;

}