/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.ckeditor.web.internal.editor.configuration;

import com.liferay.frontend.editor.ckeditor.web.internal.constants.CKEditorConstants;
import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

/**
 * @author Ambrín Chaudhary
 */
public class BaseCKEditorConfigContributor extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		jsonObject.put("allowedContent", Boolean.TRUE);

		String cssClasses = GetterUtil.getString(
			inputEditorTaglibAttributes.get(
				CKEditorConstants.ATTRIBUTE_NAMESPACE + ":cssClasses"));

		jsonObject.put(
			"bodyClass", "html-editor " + HtmlUtil.escape(cssClasses)
		).put(
			"contentsCss",
			JSONUtil.putAll(
				HtmlUtil.escape(
					PortalUtil.getStaticResourceURL(
						themeDisplay.getRequest(),
						themeDisplay.getPathThemeCss() + "/clay.css")),
				HtmlUtil.escape(
					PortalUtil.getStaticResourceURL(
						themeDisplay.getRequest(),
						themeDisplay.getPathThemeCss() + "/main.css")))
		).put(
			"contentsLangDirection",
			HtmlUtil.escapeJS(
				getContentsLanguageDir(inputEditorTaglibAttributes))
		);

		String contentsLanguageId = getContentsLanguageId(
			inputEditorTaglibAttributes);

		contentsLanguageId = StringUtil.replace(contentsLanguageId, "iw", "he");
		contentsLanguageId = StringUtil.replace(contentsLanguageId, '_', '-');

		jsonObject.put(
			"contentsLanguage", contentsLanguageId
		).put(
			"height", 265
		);

		String languageId = getLanguageId(themeDisplay);

		languageId = StringUtil.replace(languageId, "iw", "he");
		languageId = StringUtil.replace(languageId, '_', '-');

		jsonObject.put("language", languageId);

		boolean resizable = GetterUtil.getBoolean(
			(String)inputEditorTaglibAttributes.get(
				CKEditorConstants.ATTRIBUTE_NAMESPACE + ":resizable"));

		if (resizable) {
			jsonObject.put("resize_dir", "vertical");
		}

		jsonObject.put("resize_enabled", resizable);
	}

	protected boolean isShowSource(
		Map<String, Object> inputEditorTaglibAttributes) {

		return GetterUtil.getBoolean(
			inputEditorTaglibAttributes.get(
				CKEditorConstants.ATTRIBUTE_NAMESPACE + ":showSource"));
	}

}