/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.group.admin.web.internal.search;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;

import javax.portlet.PortletResponse;

/**
 * @author Alec Sloan
 */
public class CommerceAccountGroupChecker extends EmptyOnClickRowChecker {

	public CommerceAccountGroupChecker(PortletResponse portletResponse) {
		super(portletResponse);
	}

	@Override
	public boolean isDisabled(Object object) {
		CommerceAccountGroup commerceAccountGroup =
			(CommerceAccountGroup)object;

		return commerceAccountGroup.isSystem();
	}

}