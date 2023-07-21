/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class ProductPurchaseViewUtil {

	public static ProductPurchaseView toProductPurchaseView(
			ProductEntry productEntry,
			List<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				trunkProductConsumptions,
			List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				trunkProductPurchases)
		throws Exception {

		return new ProductPurchaseView() {
			{
				product = ProductUtil.toProduct(productEntry);
				productConsumptions = TransformUtil.transformToArray(
					trunkProductConsumptions,
					ProductConsumptionUtil::toProductConsumption,
					ProductConsumption.class);
				productPurchases = TransformUtil.transformToArray(
					trunkProductPurchases,
					ProductPurchaseUtil::toProductPurchase,
					ProductPurchase.class);
			}
		};
	}

}