/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.tinymce.web.internal.editor.configuration;

import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ambrín Chaudhary
 */
@Component(
	property = "editor.name=tinymce_simple",
	service = EditorConfigContributor.class
)
public class TinyMCESimpleEditorConfigContributor
	extends BaseTinyMCEEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		super.populateConfigJSONObject(
			jsonObject, inputEditorTaglibAttributes, themeDisplay,
			requestBackedPortletURLFactory);

		String plugins = "contextmenu preview print";

		if (isShowSource(inputEditorTaglibAttributes)) {
			plugins += " code";
		}

		jsonObject.put("plugins", plugins);

		String toolbar =
			"bold italic underline | alignleft aligncenter alignright " +
				"alignjustify | ";

		if (isShowSource(inputEditorTaglibAttributes)) {
			toolbar += "code ";
		}

		toolbar += "preview print";

		jsonObject.put("toolbar", toolbar);
	}

	@Override
	protected ItemSelector getItemSelector() {
		return _itemSelector;
	}

	@Reference
	private ItemSelector _itemSelector;

}