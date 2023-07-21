/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.type.controller.content.internal.product.navigation.control.menu;

import com.liferay.layout.content.page.editor.constants.ContentPageEditorWebKeys;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.type.controller.content.internal.controller.ContentLayoutTypeController;
import com.liferay.layout.util.LayoutCopyHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.control.menu.BaseProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;
import com.liferay.sites.kernel.util.SitesUtil;

import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.USER,
		"product.navigation.control.menu.entry.order:Integer=50"
	},
	service = ProductNavigationControlMenuEntry.class
)
public class EditLayoutModeProductNavigationControlMenuEntry
	extends BaseProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	@Override
	public String getIcon(HttpServletRequest httpServletRequest) {
		return "pencil";
	}

	@Override
	public String getIconCssClass(HttpServletRequest httpServletRequest) {
		return "icon-monospaced";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "edit");
	}

	@Override
	public String getURL(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		try {
			String redirect = themeDisplay.getURLCurrent();

			Layout layout = themeDisplay.getLayout();

			if ((layout.getClassPK() > 0) &&
				(_portal.getClassNameId(Layout.class) ==
					layout.getClassNameId())) {

				redirect = _portal.getLayoutFullURL(
					_layoutLocalService.getLayout(layout.getClassPK()),
					themeDisplay);
			}
			else {
				Layout draftLayout = _layoutLocalService.fetchLayout(
					_portal.getClassNameId(Layout.class), layout.getPlid());

				if (draftLayout == null) {
					ServiceContext serviceContext =
						ServiceContextFactory.getInstance(httpServletRequest);

					draftLayout = _layoutLocalService.addLayout(
						layout.getUserId(), layout.getGroupId(),
						layout.isPrivateLayout(), layout.getParentLayoutId(),
						_portal.getClassNameId(Layout.class), layout.getPlid(),
						layout.getNameMap(), layout.getTitleMap(),
						layout.getDescriptionMap(), layout.getKeywordsMap(),
						layout.getRobotsMap(), layout.getType(),
						layout.getTypeSettings(), true, true,
						Collections.emptyMap(), serviceContext);

					_layoutCopyHelper.copyLayout(layout, draftLayout);
				}

				redirect = _portal.getLayoutFullURL(draftLayout, themeDisplay);
			}

			redirect = _http.setParameter(
				redirect, "p_l_back_url", themeDisplay.getURLCurrent());
			redirect = _http.setParameter(redirect, "p_l_mode", Constants.EDIT);

			long segmentsExperienceId = ParamUtil.getLong(
				httpServletRequest, "segmentsExperienceId", -1);

			if (segmentsExperienceId != -1) {
				redirect = _http.setParameter(
					redirect, "segmentsExperienceId", segmentsExperienceId);
			}

			return redirect;
		}
		catch (Exception exception) {
		}

		return StringPool.BLANK;
	}

	@Override
	public boolean isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		String mode = ParamUtil.getString(
			httpServletRequest, "p_l_mode", Constants.VIEW);

		if (Objects.equals(mode, Constants.EDIT)) {
			return false;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!SitesUtil.isLayoutUpdateable(themeDisplay.getLayout())) {
			return false;
		}

		LayoutTypePortlet layoutTypePortlet =
			themeDisplay.getLayoutTypePortlet();

		LayoutTypeController layoutTypeController =
			layoutTypePortlet.getLayoutTypeController();

		if (layoutTypeController.isFullPageDisplayable()) {
			return false;
		}

		if (!(layoutTypeController instanceof ContentLayoutTypeController)) {
			return false;
		}

		String className = (String)httpServletRequest.getAttribute(
			ContentPageEditorWebKeys.CLASS_NAME);

		if (Objects.equals(
				className, LayoutPageTemplateEntry.class.getName())) {

			return false;
		}

		return LayoutPermissionUtil.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getLayout(),
			ActionKeys.UPDATE);
	}

	@Reference
	private Http _http;

	@Reference
	private LayoutCopyHelper _layoutCopyHelper;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}