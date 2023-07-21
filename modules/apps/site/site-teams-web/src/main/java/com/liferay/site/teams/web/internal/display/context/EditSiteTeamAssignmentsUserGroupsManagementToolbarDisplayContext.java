/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.teams.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class EditSiteTeamAssignmentsUserGroupsManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public EditSiteTeamAssignmentsUserGroupsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest,
		EditSiteTeamAssignmentsUserGroupsDisplayContext
			editSiteTeamAssignmentsUserGroupsDisplayContext) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			editSiteTeamAssignmentsUserGroupsDisplayContext.
				getUserGroupSearchContainer());

		_editSiteTeamAssignmentsUserGroupsDisplayContext =
			editSiteTeamAssignmentsUserGroupsDisplayContext;
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData("action", "deleteUserGroups");
						dropdownItem.setIcon("times-circle");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "delete"));
						dropdownItem.setQuickAction(true);
					});
			}
		};
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	@Override
	public String getComponentId() {
		return "editTeamAssignemntsUserGroupsWebManagementToolbar";
	}

	@Override
	public CreationMenu getCreationMenu() {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			PortletURL selectUserGroupURL =
				liferayPortletResponse.createRenderURL();

			selectUserGroupURL.setParameter(
				"mvcPath", "/select_user_groups.jsp");
			selectUserGroupURL.setParameter(
				"redirect", themeDisplay.getURLCurrent());
			selectUserGroupURL.setParameter(
				"teamId",
				String.valueOf(
					_editSiteTeamAssignmentsUserGroupsDisplayContext.
						getTeamId()));
			selectUserGroupURL.setWindowState(LiferayWindowState.POP_UP);

			String title = LanguageUtil.format(
				request, "add-new-user-group-to-x",
				_editSiteTeamAssignmentsUserGroupsDisplayContext.getTeamName());

			return new CreationMenu() {
				{
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.putData("action", "selectUserGroup");
							dropdownItem.putData(
								"selectUserGroupURL",
								selectUserGroupURL.toString());
							dropdownItem.putData("title", title);
							dropdownItem.setLabel(
								LanguageUtil.get(request, "add"));
						});
				}
			};
		}
		catch (Exception exception) {
			return null;
		}
	}

	@Override
	public String getDefaultEventHandler() {
		return "editTeamAssignmentsUserGroupsManagementToolbarDefaultEventHandler";
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "userGroups";
	}

	@Override
	public Boolean isShowCreationMenu() {
		return true;
	}

	@Override
	protected String getDisplayStyle() {
		return _editSiteTeamAssignmentsUserGroupsDisplayContext.
			getDisplayStyle();
	}

	@Override
	protected String[] getDisplayViews() {
		return new String[] {"list", "descriptive"};
	}

	@Override
	protected String[] getNavigationKeys() {
		return new String[] {"all"};
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"name", "description"};
	}

	private final EditSiteTeamAssignmentsUserGroupsDisplayContext
		_editSiteTeamAssignmentsUserGroupsDisplayContext;

}