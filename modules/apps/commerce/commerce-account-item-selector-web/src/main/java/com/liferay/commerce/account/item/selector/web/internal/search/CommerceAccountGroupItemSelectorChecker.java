/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.item.selector.web.internal.search;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupItemSelectorChecker
	extends EmptyOnClickRowChecker {

	public CommerceAccountGroupItemSelectorChecker(
		RenderResponse renderResponse, long[] checkedCommerceAccountGroupIds) {

		super(renderResponse);

		_checkedCommerceAccountGroupIds = SetUtil.fromArray(
			checkedCommerceAccountGroupIds);
	}

	@Override
	public boolean isChecked(Object obj) {
		CommerceAccountGroup commerceAccountGroup = (CommerceAccountGroup)obj;

		return _checkedCommerceAccountGroupIds.contains(
			commerceAccountGroup.getCommerceAccountGroupId());
	}

	@Override
	public boolean isDisabled(Object obj) {
		return isChecked(obj);
	}

	private final Set<Long> _checkedCommerceAccountGroupIds;

}