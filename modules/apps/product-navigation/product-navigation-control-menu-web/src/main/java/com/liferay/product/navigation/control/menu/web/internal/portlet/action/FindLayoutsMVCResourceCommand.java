/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.control.menu.web.internal.portlet.action;

import com.liferay.layout.admin.kernel.util.LayoutAdminSearchUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.product.navigation.control.menu.web.internal.constants.ProductNavigationControlMenuPortletKeys;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ProductNavigationControlMenuPortletKeys.PRODUCT_NAVIGATION_CONTROL_MENU,
		"mvc.command.name=/control_menu/find_layouts"
	},
	service = MVCResourceCommand.class
)
public class FindLayoutsMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(resourceResponse);

		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		if (Validator.isNull(keywords)) {
			jsonObject.put(
				"layouts", JSONFactoryUtil.createJSONArray()
			).put(
				"totalCount", 0
			);

			ServletResponseUtil.write(
				httpServletResponse, jsonObject.toString());

			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Layout> layouts = LayoutAdminSearchUtil.getLayouts(
			themeDisplay.getScopeGroupId(), keywords,
			new String[] {
				LayoutConstants.TYPE_CONTENT, LayoutConstants.TYPE_EMBEDDED,
				LayoutConstants.TYPE_LINK_TO_LAYOUT,
				LayoutConstants.TYPE_FULL_PAGE_APPLICATION,
				LayoutConstants.TYPE_PANEL, LayoutConstants.TYPE_PORTLET,
				LayoutConstants.TYPE_URL
			},
			new int[] {WorkflowConstants.STATUS_ANY}, 0, 10, null);

		for (Layout layout : layouts) {
			StringBundler sb = new StringBundler(5);

			sb.append(layout.getName(themeDisplay.getLocale()));
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_PARENTHESIS);

			if (layout.isPrivateLayout()) {
				sb.append(
					LanguageUtil.get(themeDisplay.getLocale(), "private"));
			}
			else {
				sb.append(LanguageUtil.get(themeDisplay.getLocale(), "public"));
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);

			jsonArray.put(
				JSONUtil.put(
					"name", sb.toString()
				).put(
					"url", _portal.getLayoutFullURL(layout, themeDisplay)
				));
		}

		jsonObject.put("layouts", jsonArray);

		int totalCount = LayoutAdminSearchUtil.getLayoutsCount(
			themeDisplay.getScopeGroupId(), keywords,
			new String[] {
				LayoutConstants.TYPE_CONTENT, LayoutConstants.TYPE_EMBEDDED,
				LayoutConstants.TYPE_LINK_TO_LAYOUT,
				LayoutConstants.TYPE_FULL_PAGE_APPLICATION,
				LayoutConstants.TYPE_PANEL, LayoutConstants.TYPE_PORTLET,
				LayoutConstants.TYPE_URL
			},
			new int[] {WorkflowConstants.STATUS_ANY});

		jsonObject.put("totalCount", totalCount);

		ServletResponseUtil.write(httpServletResponse, jsonObject.toString());
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}