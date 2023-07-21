/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.memberships.web.internal.servlet.taglib.clay;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.BaseUserCard;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.model.User;
import com.liferay.site.memberships.web.internal.constants.SiteMembershipWebKeys;
import com.liferay.site.memberships.web.internal.servlet.taglib.util.UserActionDropdownItemsProvider;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Eudaldo Alonso
 */
public class UsersUserCard extends BaseUserCard {

	public UsersUserCard(
		User user, boolean showActions, RenderRequest renderRequest,
		RenderResponse renderResponse, RowChecker rowChecker) {

		super(user, renderRequest, rowChecker);

		_showActions = showActions;
		_renderResponse = renderResponse;
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		if (!_showActions) {
			return null;
		}

		try {
			UserActionDropdownItemsProvider userActionDropdownItemsProvider =
				new UserActionDropdownItemsProvider(
					user, renderRequest, _renderResponse);

			return userActionDropdownItemsProvider.getActionDropdownItems();
		}
		catch (Exception exception) {
		}

		return null;
	}

	@Override
	public String getDefaultEventHandler() {
		return SiteMembershipWebKeys.USER_DROPDOWN_DEFAULT_EVENT_HANDLER;
	}

	private final RenderResponse _renderResponse;
	private final boolean _showActions;

}