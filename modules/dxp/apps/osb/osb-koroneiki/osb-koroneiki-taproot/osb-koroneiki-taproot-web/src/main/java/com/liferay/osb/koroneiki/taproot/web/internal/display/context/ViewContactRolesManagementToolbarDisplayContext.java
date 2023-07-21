/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownGroupItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewContactRolesManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewContactRolesManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest,
		SearchContainer searchContainer) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer);
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	@Override
	public CreationMenu getCreationMenu() {
		String tabs1 = ParamUtil.getString(liferayPortletRequest, "tabs1");

		return new CreationMenu() {
			{
				if (tabs1.equals("account-worker")) {
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								liferayPortletResponse.createRenderURL(),
								"mvcRenderCommandName",
								"/contact_roles_admin/edit_contact_role",
								"redirect", currentURLObj.toString(), "type",
								ContactRole.Type.ACCOUNT_WORKER.toString());
							dropdownItem.setLabel(
								LanguageUtil.get(request, "add"));
						});
				}
				else if (tabs1.equals("team")) {
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								liferayPortletResponse.createRenderURL(),
								"mvcRenderCommandName",
								"/contact_roles_admin/edit_contact_role",
								"redirect", currentURLObj.toString(), "type",
								ContactRole.Type.TEAM.toString());
							dropdownItem.setLabel(
								LanguageUtil.get(request, "add"));
						});
				}
				else {
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								liferayPortletResponse.createRenderURL(),
								"mvcRenderCommandName",
								"/contact_roles_admin/edit_contact_role",
								"redirect", currentURLObj.toString(), "type",
								ContactRole.Type.ACCOUNT_CUSTOMER.toString());
							dropdownItem.setLabel(
								LanguageUtil.get(request, "add"));
						});
				}
			}
		};
	}

	@Override
	public List<DropdownItem> getFilterDropdownItems() {
		return DropdownItemList.of(
			() -> {
				DropdownGroupItem dropdownGroupItem = new DropdownGroupItem();

				dropdownGroupItem.setDropdownItems(_getOrderByDropdownItems());
				dropdownGroupItem.setLabel(
					LanguageUtil.get(request, "order-by"));

				return dropdownGroupItem;
			});
	}

	public List<NavigationItem> getNavigationItems() {
		String tabs1 = ParamUtil.getString(
			liferayPortletRequest, "tabs1", "account-customer");

		return NavigationItemList.of(
			() -> {
				NavigationItem navigationItem = new NavigationItem();

				navigationItem.setActive(tabs1.equals("account-customer"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"account-customer");
				navigationItem.setLabel(
					LanguageUtil.get(request, "account-customer"));

				return navigationItem;
			},
			() -> {
				NavigationItem navigationItem = new NavigationItem();

				navigationItem.setActive(tabs1.equals("account-worker"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"account-worker");
				navigationItem.setLabel(
					LanguageUtil.get(request, "account-worker"));

				return navigationItem;
			},
			() -> {
				NavigationItem navigationItem = new NavigationItem();

				navigationItem.setActive(tabs1.equals("team"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1", "team");
				navigationItem.setLabel(LanguageUtil.get(request, "team"));

				return navigationItem;
			});
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "contactRoleSearch";
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

	private List<DropdownItem> _getOrderByDropdownItems() {
		return DropdownItemList.of(
			() -> {
				DropdownItem dropdownItem = new DropdownItem();

				dropdownItem.setActive(Objects.equals(getOrderByCol(), "name"));
				dropdownItem.setHref(getPortletURL(), "orderByCol", "name");
				dropdownItem.setLabel(LanguageUtil.get(request, "name"));

				return dropdownItem;
			});
	}

}