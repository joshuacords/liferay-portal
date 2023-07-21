/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.internal.search.spi.model.index.contributor;

import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Yuanyuan Huang
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.provisioning.model.ProductBundle",
	service = ModelDocumentContributor.class
)
public class ProductBundleModelDocumentContributor
	implements ModelDocumentContributor<ProductBundle> {

	@Override
	public void contribute(Document document, ProductBundle productBundle) {
		document.addText(Field.NAME, productBundle.getName());

		document.addTextSortable(Field.NAME, productBundle.getName());
	}

}