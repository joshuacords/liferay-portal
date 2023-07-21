/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.catalog;

import java.util.List;

/**
 * @author Marco Leo
 */
public interface CPCatalogEntry {

	public long getCPDefinitionId();

	public long getCProductId();

	public List<CPSku> getCPSkus();

	public String getDefaultImageFileUrl();

	public double getDepth();

	public String getDescription();

	public long getGroupId();

	public double getHeight();

	public String getMetaDescription(String languageId);

	public String getMetaKeywords(String languageId);

	public String getMetaTitle(String languageId);

	public String getName();

	public String getProductTypeName();

	public String getShortDescription();

	public String getUrl();

	public boolean isIgnoreSKUCombinations();

}