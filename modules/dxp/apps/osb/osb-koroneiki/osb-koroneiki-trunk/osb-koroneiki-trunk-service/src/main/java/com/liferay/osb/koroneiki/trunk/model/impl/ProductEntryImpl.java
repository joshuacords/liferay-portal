/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class ProductEntryImpl extends ProductEntryBaseImpl {

	public ProductEntryImpl() {
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			ProductEntry.class.getName(), getProductEntryId(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<ProductField> getProductFields() {
		return ProductFieldLocalServiceUtil.getProductFields(
			ProductEntry.class.getName(), getProductEntryId());
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

}