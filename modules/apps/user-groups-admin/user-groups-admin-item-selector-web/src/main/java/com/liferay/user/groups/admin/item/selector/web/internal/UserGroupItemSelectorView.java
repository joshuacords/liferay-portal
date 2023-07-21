/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.groups.admin.item.selector.web.internal;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.user.groups.admin.item.selector.UserGroupItemSelectorCriterion;
import com.liferay.user.groups.admin.item.selector.web.internal.constants.UserGroupItemSelectorWebKeys;
import com.liferay.user.groups.admin.item.selector.web.internal.display.context.UserGroupItemSelectorViewDisplayContext;
import com.liferay.users.admin.kernel.util.UsersAdmin;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ItemSelectorView.class)
public class UserGroupItemSelectorView
	implements ItemSelectorView<UserGroupItemSelectorCriterion> {

	@Override
	public Class<UserGroupItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return UserGroupItemSelectorCriterion.class;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(
			_portal.getResourceBundle(locale), "user-groups");
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			UserGroupItemSelectorCriterion userGroupItemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		UserGroupItemSelectorViewDisplayContext
			userGroupItemSelectorViewDisplayContext =
				new UserGroupItemSelectorViewDisplayContext(
					_userGroupLocalService, _usersAdmin, httpServletRequest,
					portletURL, itemSelectedEventName);

		servletRequest.setAttribute(
			UserGroupItemSelectorWebKeys.
				USER_GROUP_ITEM_SELECTOR_DISPLAY_CONTEXT,
			userGroupItemSelectorViewDisplayContext);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(
				"/user_group_item_selector.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(new UUIDItemSelectorReturnType()));

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.user.groups.admin.item.selector.web)"
	)
	private ServletContext _servletContext;

	@Reference
	private UserGroupLocalService _userGroupLocalService;

	@Reference
	private UsersAdmin _usersAdmin;

}