/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.google.docs.internal.display.context;

import com.liferay.document.library.display.context.DLFilePicker;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsConfigurationHelper;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class GoogleDocsDLFilePicker implements DLFilePicker {

	public GoogleDocsDLFilePicker(
			String namespace, String onFilePickCallback,
			ThemeDisplay themeDisplay)
		throws PortalException {

		_namespace = namespace;
		_onFilePickCallback = onFilePickCallback;

		_googleDocsConfigurationHelper = new GoogleDocsConfigurationHelper(
			themeDisplay.getCompanyId());
	}

	@Override
	public String getDescriptionFieldName() {
		return GoogleDocsConstants.DDM_FIELD_NAME_DESCRIPTION;
	}

	@Override
	public String getIconFieldName() {
		return GoogleDocsConstants.DDM_FIELD_NAME_ICON_URL;
	}

	@Override
	public String getJavaScript() throws PortalException {
		String templateId =
			"/com/liferay/document/library/google/docs/internal/display" +
				"/context/dependencies/google_file_picker.ftl";

		Class<?> clazz = getClass();

		URLTemplateResource templateResource = new URLTemplateResource(
			templateId, clazz.getResource(templateId));

		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_FTL, templateResource, false);

		template.put(
			"googleAppsAPIKey",
			_googleDocsConfigurationHelper.getGoogleAppsAPIKey());
		template.put(
			"googleClientId",
			_googleDocsConfigurationHelper.getGoogleClientId());
		template.put("htmlUtil", HtmlUtil.getHtml());
		template.put("namespace", _namespace);
		template.put("onFilePickCallback", _onFilePickCallback);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		return unsyncStringWriter.toString();
	}

	@Override
	public String getJavaScriptModuleName() {
		return "FilePicker";
	}

	@Override
	public String getOnClickCallback() {
		return "openPicker";
	}

	@Override
	public String getTitleFieldName() {
		return GoogleDocsConstants.DDM_FIELD_NAME_NAME;
	}

	private final GoogleDocsConfigurationHelper _googleDocsConfigurationHelper;
	private final String _namespace;
	private final String _onFilePickCallback;

}