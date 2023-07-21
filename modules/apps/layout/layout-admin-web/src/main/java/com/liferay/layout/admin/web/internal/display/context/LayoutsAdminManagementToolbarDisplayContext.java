/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class LayoutsAdminManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public LayoutsAdminManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest httpServletRequest,
			LayoutsAdminDisplayContext layoutsAdminDisplayContext)
		throws PortalException {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			layoutsAdminDisplayContext.getLayoutsSearchContainer());

		_layoutsAdminDisplayContext = layoutsAdminDisplayContext;
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData("action", "deleteSelectedPages");
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
		return "pagesManagementToolbar";
	}

	@Override
	public CreationMenu getCreationMenu() {
		try {
			return _getCreationMenu();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get creation menu", portalException);
			}
		}

		return null;
	}

	@Override
	public String getSearchActionURL() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"privateLayout",
			String.valueOf(_layoutsAdminDisplayContext.isPrivateLayout()));

		return portletURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "pages";
	}

	@Override
	public String getSearchFormName() {
		return "fm";
	}

	@Override
	public String getSortingOrder() {
		if (_layoutsAdminDisplayContext.isFirstColumn()) {
			return null;
		}

		if (_layoutsAdminDisplayContext.isSearch()) {
			return super.getSortingOrder();
		}

		return null;
	}

	@Override
	public Boolean isDisabled() {
		if (Objects.equals(
				_layoutsAdminDisplayContext.getDisplayStyle(),
				"miller-columns")) {

			return false;
		}

		return super.isDisabled();
	}

	@Override
	public Boolean isSelectable() {
		if (_layoutsAdminDisplayContext.isFirstColumn()) {
			return false;
		}

		return super.isSelectable();
	}

	@Override
	public Boolean isShowCreationMenu() {
		try {
			CreationMenu creationMenu = getCreationMenu();

			if (creationMenu.isEmpty()) {
				return false;
			}

			return _layoutsAdminDisplayContext.isShowAddRootLayoutButton();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return false;
	}

	@Override
	protected String[] getOrderByKeys() {
		if (_layoutsAdminDisplayContext.isFirstColumn()) {
			return null;
		}

		if (_layoutsAdminDisplayContext.isSearch()) {
			return new String[] {"create-date"};
		}

		return null;
	}

	private CreationMenu _getCreationMenu() throws PortalException {
		return new CreationMenu() {
			{
				long firstLayoutPageTemplateCollectionId =
					_layoutsAdminDisplayContext.
						getFirstLayoutPageTemplateCollectionId();

				Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();
				long selPlid = _layoutsAdminDisplayContext.getSelPlid();

				if (_layoutsAdminDisplayContext.isShowPublicPages() &&
					_layoutsAdminDisplayContext.isShowAddChildPageAction(
						selLayout) &&
					(!_layoutsAdminDisplayContext.isPrivateLayout() ||
					 _layoutsAdminDisplayContext.isFirstColumn() ||
					 !_layoutsAdminDisplayContext.hasLayouts())) {

					addPrimaryDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								_layoutsAdminDisplayContext.
									getSelectLayoutPageTemplateEntryURL(
										firstLayoutPageTemplateCollectionId,
										selPlid, false));
							dropdownItem.setLabel(_getLabel(false));
						});
				}

				if (_layoutsAdminDisplayContext.isShowPrivatePages() &&
					((_layoutsAdminDisplayContext.isShowAddChildPageAction(
						selLayout) &&
					  _layoutsAdminDisplayContext.isPrivateLayout()) ||
					 _layoutsAdminDisplayContext.isFirstColumn() ||
					 !_layoutsAdminDisplayContext.hasLayouts())) {

					addPrimaryDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								_layoutsAdminDisplayContext.
									getSelectLayoutPageTemplateEntryURL(
										firstLayoutPageTemplateCollectionId,
										selPlid, true));
							dropdownItem.setLabel(_getLabel(true));
						});
				}
			}
		};
	}

	private String _getLabel(boolean privateLayout) {
		Layout layout = _layoutsAdminDisplayContext.getSelLayout();

		if (layout != null) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)liferayPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			return LanguageUtil.format(
				request, "add-child-page-of-x",
				HtmlUtil.escape(layout.getName(themeDisplay.getLocale())));
		}

		if (_isSiteTemplate()) {
			return LanguageUtil.get(request, "add-site-template-page");
		}

		if (privateLayout) {
			return LanguageUtil.get(request, "private-page");
		}

		return LanguageUtil.get(request, "public-page");
	}

	private boolean _isSiteTemplate() {
		Group group = _layoutsAdminDisplayContext.getGroup();

		if (group == null) {
			return false;
		}

		long layoutSetPrototypeClassNameId =
			ClassNameLocalServiceUtil.getClassNameId(LayoutSetPrototype.class);

		if (layoutSetPrototypeClassNameId == group.getClassNameId()) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutsAdminManagementToolbarDisplayContext.class);

	private final LayoutsAdminDisplayContext _layoutsAdminDisplayContext;

}