/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductSerDes;
import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.service.ProductBundleProductsLocalService;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=koroneiki.product.delete",
	service = ProductMessageSubscriber.class
)
public class ProductMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		Product product = ProductSerDes.toDTO(jsonObject.toString());

		List<ProductBundleProducts> productBundleProducts =
			_productBundleProductsLocalService.getProductBundleProducts(
				product.getKey());

		for (ProductBundleProducts productBundleProduct :
				productBundleProducts) {

			long productBundleId = productBundleProduct.getProductBundleId();

			_productBundleProductsLocalService.deleteProductBundleProducts(
				productBundleId, product.getKey());

			int count =
				_productBundleProductsLocalService.
					getProductBundleProductsCount(productBundleId);

			if (count == 0) {
				_productBundleLocalService.deleteProductBundle(productBundleId);
			}
		}
	}

	@Reference
	private ProductBundleLocalService _productBundleLocalService;

	@Reference
	private ProductBundleProductsLocalService
		_productBundleProductsLocalService;

}