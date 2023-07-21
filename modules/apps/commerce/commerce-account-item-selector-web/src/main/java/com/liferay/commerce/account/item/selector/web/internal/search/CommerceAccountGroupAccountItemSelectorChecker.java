/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.item.selector.web.internal.search;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.commerce.account.service.CommerceAccountGroupCommerceAccountRelLocalService;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;

import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupAccountItemSelectorChecker
	extends EmptyOnClickRowChecker {

	public CommerceAccountGroupAccountItemSelectorChecker(
		RenderResponse renderResponse,
		CommerceAccountGroup commerceAccountGroup,
		CommerceAccountGroupCommerceAccountRelLocalService
			commerceAccountGroupCommerceAccountRelLocalService) {

		super(renderResponse);

		_commerceAccountGroup = commerceAccountGroup;
		_commerceAccountGroupCommerceAccountRelLocalService =
			commerceAccountGroupCommerceAccountRelLocalService;
	}

	@Override
	public boolean isChecked(Object obj) {
		if (_commerceAccountGroup == null) {
			return false;
		}

		CommerceAccount commerceAccount = (CommerceAccount)obj;

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				_commerceAccountGroupCommerceAccountRelLocalService.
					fetchCommerceAccountGroupCommerceAccountRel(
						_commerceAccountGroup.getCommerceAccountGroupId(),
						commerceAccount.getCommerceAccountId());

		if (commerceAccountGroupCommerceAccountRel == null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isDisabled(Object obj) {
		return isChecked(obj);
	}

	private final CommerceAccountGroup _commerceAccountGroup;
	private final CommerceAccountGroupCommerceAccountRelLocalService
		_commerceAccountGroupCommerceAccountRelLocalService;

}