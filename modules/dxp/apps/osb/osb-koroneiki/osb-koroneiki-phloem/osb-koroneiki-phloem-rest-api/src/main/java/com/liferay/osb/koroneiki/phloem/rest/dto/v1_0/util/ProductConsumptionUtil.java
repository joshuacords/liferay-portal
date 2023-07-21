/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Amos Fong
 */
public class ProductConsumptionUtil {

	public static ProductConsumption toProductConsumption(
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				productConsumption)
		throws Exception {

		return new ProductConsumption() {
			{
				accountKey = productConsumption.getAccountKey();
				dateCreated = productConsumption.getCreateDate();
				endDate = productConsumption.getEndDate();
				externalLinks = TransformUtil.transformToArray(
					productConsumption.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				key = productConsumption.getProductConsumptionKey();
				productKey = productConsumption.getProductEntryKey();
				productPurchaseKey = productConsumption.getProductPurchaseKey();
				properties = productConsumption.getProductFieldsMap();
				startDate = productConsumption.getStartDate();
			}
		};
	}

}