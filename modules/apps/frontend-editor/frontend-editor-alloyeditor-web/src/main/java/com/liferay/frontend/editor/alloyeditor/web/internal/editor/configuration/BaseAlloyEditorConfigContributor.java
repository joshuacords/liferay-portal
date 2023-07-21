/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.alloyeditor.web.internal.editor.configuration;

import com.liferay.frontend.editor.alloyeditor.web.internal.constants.AlloyEditorConstants;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
public abstract class BaseAlloyEditorConfigContributor
	extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		jsonObject.put(
			"allowedContent", Boolean.TRUE
		).put(
			"contentsLangDirection",
			HtmlUtil.escapeJS(
				getContentsLanguageDir(inputEditorTaglibAttributes))
		).put(
			"contentsLanguage",
			StringUtil.replace(
				getContentsLanguageId(inputEditorTaglibAttributes), "iw_",
				"he_")
		).put(
			"disableNativeSpellChecker", Boolean.FALSE
		).put(
			"extraPlugins",
			"ae_autolink,ae_dragresize,ae_addimages,ae_imagealignment," +
				"ae_placeholder,ae_selectionregion,ae_tableresize," +
					"ae_tabletools,ae_uicore"
		).put(
			"imageScaleResize", "scale"
		).put(
			"language",
			StringUtil.replace(getLanguageId(themeDisplay), "iw_", "he_")
		).put(
			"removePlugins",
			"contextmenu,elementspath,floatingspace,image2,link,liststyle," +
				"resize,table,tabletools,toolbar"
		);

		String namespace = GetterUtil.getString(
			inputEditorTaglibAttributes.get(
				AlloyEditorConstants.ATTRIBUTE_NAMESPACE + ":namespace"));

		String name = GetterUtil.getString(
			inputEditorTaglibAttributes.get(
				AlloyEditorConstants.ATTRIBUTE_NAMESPACE + ":name"));

		name = namespace + name;

		jsonObject.put("srcNode", name);

		populateFileBrowserURL(
			jsonObject, requestBackedPortletURLFactory,
			name + "selectDocument");
	}

	protected void populateFileBrowserURL(
		JSONObject jsonObject,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory,
		String eventName) {

		ItemSelectorCriterion fileItemSelectorCriterion =
			new FileItemSelectorCriterion();

		fileItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new URLItemSelectorReturnType());

		LayoutItemSelectorCriterion layoutItemSelectorCriterion =
			new LayoutItemSelectorCriterion();

		layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new URLItemSelectorReturnType());
		layoutItemSelectorCriterion.setShowHiddenPages(true);

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, eventName,
			fileItemSelectorCriterion, layoutItemSelectorCriterion);

		jsonObject.put("documentBrowseLinkUrl", itemSelectorURL.toString());
	}

	@Reference(unbind = "-")
	protected void setItemSelector(ItemSelector itemSelector) {
		_itemSelector = itemSelector;
	}

	private ItemSelector _itemSelector;

}