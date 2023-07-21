/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Amos Fong
 */
public class ProductPurchaseUtil {

	public static ProductPurchase toProductPurchase(
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase
				productPurchase)
		throws Exception {

		return new ProductPurchase() {
			{
				accountKey = productPurchase.getAccountKey();
				dateCreated = productPurchase.getCreateDate();
				endDate = productPurchase.getEndDate();
				externalLinks = TransformUtil.transformToArray(
					productPurchase.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				key = productPurchase.getProductPurchaseKey();
				originalEndDate = productPurchase.getOriginalEndDate();

				if ((productPurchase.getEndDate() == null) &&
					(productPurchase.getEndDate() == null)) {

					perpetual = Boolean.TRUE;
				}
				else {
					perpetual = Boolean.FALSE;
				}

				product = ProductUtil.toProduct(
					productPurchase.getProductEntry());
				productKey = productPurchase.getProductEntryKey();
				properties = productPurchase.getProductFieldsMap();
				quantity = productPurchase.getQuantity();
				startDate = productPurchase.getStartDate();
				status = Status.create(productPurchase.getStatusLabel());
			}
		};
	}

}