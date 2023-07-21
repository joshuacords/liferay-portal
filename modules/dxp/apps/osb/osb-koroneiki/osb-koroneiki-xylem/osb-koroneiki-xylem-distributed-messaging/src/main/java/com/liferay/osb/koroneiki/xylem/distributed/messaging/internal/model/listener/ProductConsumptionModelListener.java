/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.StringPool;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.productconsumption.create",
		"remove.topic=koroneiki.productconsumption.delete",
		"update.topic=koroneiki.productconsumption.update"
	},
	service = ModelListener.class
)
public class ProductConsumptionModelListener
	extends BaseXylemModelListener<ProductConsumption> {

	@Override
	protected Callable<Message> getCallable(
			ProductConsumption productConsumption)
		throws Exception {

		Account account = _accountLocalService.getAccount(
			productConsumption.getAccountId());

		productConsumption.setAccountKey(account.getAccountKey());

		if (productConsumption.getProductPurchaseId() > 0) {
			ProductPurchase productPurchase =
				_productPurchaseLocalService.getProductPurchase(
					productConsumption.getProductPurchaseId());

			productConsumption.setProductPurchaseKey(
				productPurchase.getProductPurchaseKey());
		}
		else {
			productConsumption.setProductPurchaseKey(StringPool.BLANK);
		}

		return () -> messageFactory.create(productConsumption);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}