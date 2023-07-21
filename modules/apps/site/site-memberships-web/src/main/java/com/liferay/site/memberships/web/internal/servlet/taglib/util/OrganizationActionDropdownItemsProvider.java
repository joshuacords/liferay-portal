/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.memberships.web.internal.servlet.taglib.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class OrganizationActionDropdownItemsProvider {

	public OrganizationActionDropdownItemsProvider(
		Organization organization, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_organization = organization;
		_renderResponse = renderResponse;

		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<DropdownItem> getActionDropdownItems() throws Exception {
		return new DropdownItemList() {
			{
				if (GroupPermissionUtil.contains(
						_themeDisplay.getPermissionChecker(),
						_themeDisplay.getSiteGroupIdOrLiveGroupId(),
						ActionKeys.ASSIGN_MEMBERS)) {

					add(_getDeleteGroupOrganizationsActionUnsafeConsumer());
				}
			}
		};
	}

	private UnsafeConsumer<DropdownItem, Exception>
		_getDeleteGroupOrganizationsActionUnsafeConsumer() {

		PortletURL deleteGroupOrganizationsURL =
			_renderResponse.createActionURL();

		deleteGroupOrganizationsURL.setParameter(
			ActionRequest.ACTION_NAME, "deleteGroupOrganizations");
		deleteGroupOrganizationsURL.setParameter(
			"redirect", _themeDisplay.getURLCurrent());
		deleteGroupOrganizationsURL.setParameter(
			"groupId",
			String.valueOf(_themeDisplay.getSiteGroupIdOrLiveGroupId()));
		deleteGroupOrganizationsURL.setParameter(
			"removeOrganizationId",
			String.valueOf(_organization.getOrganizationId()));

		return dropdownItem -> {
			dropdownItem.putData("action", "deleteGroupOrganizations");
			dropdownItem.putData(
				"deleteGroupOrganizationsURL",
				deleteGroupOrganizationsURL.toString());
			dropdownItem.setLabel(
				LanguageUtil.get(_httpServletRequest, "remove-membership"));
		};
	}

	private final HttpServletRequest _httpServletRequest;
	private final Organization _organization;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}