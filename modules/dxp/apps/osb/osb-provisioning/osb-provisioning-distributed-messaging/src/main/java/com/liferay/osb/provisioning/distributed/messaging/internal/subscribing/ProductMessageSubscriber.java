/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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