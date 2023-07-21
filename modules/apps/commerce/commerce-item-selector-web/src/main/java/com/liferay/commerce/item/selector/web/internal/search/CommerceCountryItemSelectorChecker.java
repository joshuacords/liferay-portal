/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.item.selector.web.internal.search;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCountryItemSelectorChecker extends EmptyOnClickRowChecker {

	public CommerceCountryItemSelectorChecker(
		RenderResponse renderResponse, long[] checkedCommerceCountryIds) {

		super(renderResponse);

		_checkedCommerceCountryIds = SetUtil.fromArray(
			checkedCommerceCountryIds);
	}

	@Override
	public boolean isChecked(Object obj) {
		CommerceCountry commerceCountry = (CommerceCountry)obj;

		return _checkedCommerceCountryIds.contains(
			commerceCountry.getCommerceCountryId());
	}

	@Override
	public boolean isDisabled(Object obj) {
		return isChecked(obj);
	}

	private final Set<Long> _checkedCommerceCountryIds;

}