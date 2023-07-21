/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.productpurchase.create",
		"remove.topic=koroneiki.productpurchase.delete",
		"update.topic=koroneiki.productpurchase.update"
	},
	service = ModelListener.class
)
public class ProductPurchaseModelListener
	extends BaseXylemModelListener<ProductPurchase> {

	@Override
	protected Callable<Message> getCallable(ProductPurchase productPurchase)
		throws Exception {

		Account account = _accountLocalService.getAccount(
			productPurchase.getAccountId());

		productPurchase.setAccountKey(account.getAccountKey());

		return () -> messageFactory.create(productPurchase);
	}

	@Reference
	private AccountLocalService _accountLocalService;

}