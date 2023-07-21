/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.model.listener;

import com.liferay.commerce.account.exception.CommerceAccountOrdersException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(immediate = true, service = ModelListener.class)
public class CommerceAccountModelListener
	extends BaseModelListener<CommerceAccount> {

	@Override
	public void onBeforeRemove(CommerceAccount commerceAccount) {
		int accountOrders =
			_commerceOrderLocalService.
				getCommerceOrdersCountByCommerceAccountId(
					commerceAccount.getCommerceAccountId());

		if (accountOrders > 0) {
			throw new CommerceAccountOrdersException();
		}
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

}