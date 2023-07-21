/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.entry.processor.editable.internal.parser;

import com.liferay.fragment.entry.processor.editable.EditableFragmentEntryProcessor;
import com.liferay.fragment.entry.processor.editable.parser.EditableElementParser;
import com.liferay.fragment.entry.processor.editable.parser.util.EditableElementParserUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

import org.jsoup.nodes.Element;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, property = "type=text",
	service = EditableElementParser.class
)
public class TextEditableElementParser implements EditableElementParser {

	@Override
	public String getFieldTemplate() {
		return _TMPL_VALIDATE_TEXT_FIELD;
	}

	@Override
	public String getValue(Element element) {
		return element.html();
	}

	@Override
	public void replace(Element element, String value) {
		replace(element, value, null);
	}

	@Override
	public void replace(
		Element element, String value, JSONObject configJSONObject) {

		Element bodyElement = EditableElementParserUtil.getDocumentBody(value);

		if (configJSONObject == null) {
			element.html(bodyElement.html());

			return;
		}

		EditableElementParserUtil.addClass(
			element, configJSONObject, "text-", "textAlignment");
		EditableElementParserUtil.addClass(
			element, configJSONObject, "text-", "textColor");
		EditableElementParserUtil.addClass(
			element, configJSONObject, StringPool.BLANK, "textStyle");

		element.html(bodyElement.html());
	}

	private static final String _TMPL_VALIDATE_TEXT_FIELD = StringUtil.read(
		EditableFragmentEntryProcessor.class,
		"/META-INF/resources/fragment/entry/processor/editable" +
			"/text_field_template.tmpl");

}