/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.web.internal.servlet.taglib;

import com.liferay.analytics.web.internal.constants.AnalyticsWebKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.servlet.taglib.BaseJSPDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = DynamicInclude.class)
public class AnalyticsTopHeadJSPDynamicInclude extends BaseJSPDynamicInclude {

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!_isAnalyticsTrackingEnabled(httpServletRequest, themeDisplay)) {
			return;
		}

		httpServletRequest.setAttribute(
			AnalyticsWebKeys.ANALYTICS_CLIENT_CHANNEL_ID,
			_getLiferayAnalyticsChannelId(httpServletRequest, themeDisplay));

		httpServletRequest.setAttribute(
			AnalyticsWebKeys.ANALYTICS_CLIENT_CONFIG,
			_serialize(
				HashMapBuilder.put(
					"dataSourceId",
					_getLiferayAnalyticsDataSourceId(themeDisplay.getCompany())
				).put(
					"endpointUrl",
					_getLiferayAnalyticsEndpointURL(themeDisplay.getCompany())
				).put(
					"projectId",
					_getLiferayAnalyticsProjectId(themeDisplay.getCompany())
				).build()));

		httpServletRequest.setAttribute(
			AnalyticsWebKeys.ANALYTICS_CLIENT_GROUP_IDS,
			_serialize(
				PrefsPropsUtil.getStringArray(
					themeDisplay.getCompanyId(), "liferayAnalyticsGroupIds",
					StringPool.COMMA)));

		super.include(httpServletRequest, httpServletResponse, key);
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register(
			"/html/common/themes/top_head.jsp#post");
	}

	@Override
	protected String getJspPath() {
		return "/dynamic_include/top_head.jsp";
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.analytics.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private String _getLiferayAnalyticsChannelId(
		HttpServletRequest httpServletRequest, ThemeDisplay themeDisplay) {

		Layout layout = themeDisplay.getLayout();

		Group group = layout.getGroup();

		if (Objects.equals(group.getGroupKey(), "Forms")) {
			Group refererGroup = _groupLocalService.fetchGroup(
				GetterUtil.getLong(
					httpServletRequest.getAttribute("refererGroupId")));

			if (refererGroup != null) {
				return refererGroup.getTypeSettingsProperty(
					"analyticsChannelId");
			}
		}

		return group.getTypeSettingsProperty("analyticsChannelId");
	}

	private String _getLiferayAnalyticsDataSourceId(Company company) {
		return PrefsPropsUtil.getString(
			company.getCompanyId(), "liferayAnalyticsDataSourceId");
	}

	private String _getLiferayAnalyticsEndpointURL(Company company) {
		return PrefsPropsUtil.getString(
			company.getCompanyId(), "liferayAnalyticsEndpointURL");
	}

	private String _getLiferayAnalyticsProjectId(Company company) {
		return PrefsPropsUtil.getString(
			company.getCompanyId(), "liferayAnalyticsProjectId");
	}

	private boolean _isAnalyticsTrackingEnabled(
		HttpServletRequest httpServletRequest, ThemeDisplay themeDisplay) {

		Layout layout = themeDisplay.getLayout();

		if (layout == null) {
			return false;
		}

		if (layout.isTypeControlPanel()) {
			return false;
		}

		Company company = themeDisplay.getCompany();

		if (Validator.isNull(_getLiferayAnalyticsDataSourceId(company)) ||
			Validator.isNull(_getLiferayAnalyticsEndpointURL(company))) {

			return false;
		}

		if (Objects.equals(
				httpServletRequest.getRequestURI(), "/c/portal/api/jsonws")) {

			return false;
		}

		String[] liferayAnalyticsGroupIds = PrefsPropsUtil.getStringArray(
			company.getCompanyId(), "liferayAnalyticsGroupIds",
			StringPool.COMMA);

		if (_isSharedFormEnabled(
				liferayAnalyticsGroupIds, layout.getGroup(),
				httpServletRequest)) {

			return true;
		}

		Group group = layout.getGroup();

		boolean liferayAnalyticsEnableAllGroupIds = PrefsPropsUtil.getBoolean(
			company.getCompanyId(), "liferayAnalyticsEnableAllGroupIds");

		if (liferayAnalyticsEnableAllGroupIds ||
			ArrayUtil.contains(
				liferayAnalyticsGroupIds, String.valueOf(group.getGroupId()))) {

			return true;
		}

		return false;
	}

	private boolean _isSharedFormEnabled(
		String[] liferayAnalyticsGroupIds, Group group,
		HttpServletRequest httpServletRequest) {

		if (Objects.equals(group.getGroupKey(), "Forms")) {
			return ArrayUtil.contains(
				liferayAnalyticsGroupIds,
				String.valueOf(
					httpServletRequest.getAttribute("refererGroupId")));
		}

		return false;
	}

	private String _serialize(Map<String, String> map) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}

		return jsonObject.toString();
	}

	private String _serialize(Object[] array) {
		JSONArray jsonArray = _jsonFactory.createJSONArray(array);

		return jsonArray.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AnalyticsTopHeadJSPDynamicInclude.class);

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}