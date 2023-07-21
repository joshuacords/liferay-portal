/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.entry.processor.editable.internal.mapper;

import com.liferay.fragment.entry.processor.editable.mapper.EditableElementMapper;
import com.liferay.fragment.entry.processor.helper.FragmentEntryProcessorHelper;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true, property = "type=link",
	service = EditableElementMapper.class
)
public class LinkEditableElementMapper implements EditableElementMapper {

	@Override
	public void map(
			Element element, JSONObject configJSONObject,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws PortalException {

		String href = configJSONObject.getString("href");

		boolean assetDisplayPage =
			_fragmentEntryProcessorHelper.isAssetDisplayPage(
				fragmentEntryProcessorContext.getMode());

		boolean mapped = _fragmentEntryProcessorHelper.isMapped(
			configJSONObject);

		if (Validator.isNull(href) && !assetDisplayPage && !mapped) {
			return;
		}

		Element linkElement = new Element("a");

		Elements elements = element.children();

		Element firstChild = elements.first();

		boolean replaceLink = false;

		if ((firstChild != null) &&
			StringUtil.equalsIgnoreCase(firstChild.tagName(), "a")) {

			linkElement = firstChild;
			replaceLink = true;
		}

		if (configJSONObject.has("target")) {
			linkElement.attr("target", configJSONObject.getString("target"));
		}

		String mappedField = configJSONObject.getString("mappedField");

		if (mapped) {
			Object fieldValue = _fragmentEntryProcessorHelper.getMappedValue(
				configJSONObject, new HashMap<>(),
				fragmentEntryProcessorContext);

			if (fieldValue == null) {
				return;
			}

			linkElement.attr("href", fieldValue.toString());

			linkElement.html(replaceLink ? firstChild.html() : element.html());

			element.html(linkElement.outerHtml());
		}
		else if (Validator.isNotNull(href)) {
			linkElement.attr("href", href);

			linkElement.html(replaceLink ? firstChild.html() : element.html());

			element.html(linkElement.outerHtml());
		}
		else if (assetDisplayPage && Validator.isNotNull(mappedField)) {
			linkElement.attr("href", "${" + mappedField + "}");

			linkElement.html(replaceLink ? firstChild.html() : element.html());

			element.html(
				_fragmentEntryProcessorHelper.processTemplate(
					linkElement.outerHtml(), fragmentEntryProcessorContext));
		}
	}

	@Reference
	private FragmentEntryProcessorHelper _fragmentEntryProcessorHelper;

}