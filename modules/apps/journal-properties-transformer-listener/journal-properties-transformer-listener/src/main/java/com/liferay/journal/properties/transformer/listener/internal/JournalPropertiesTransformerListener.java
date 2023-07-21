/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.properties.transformer.listener.internal;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.constants.JournalTransformerListenerKeys;
import com.liferay.journal.util.JournalHelper;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.templateparser.TransformerListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = false,
	property = "javax.portlet.name=" + JournalPortletKeys.JOURNAL,
	service = TransformerListener.class
)
public class JournalPropertiesTransformerListener
	extends BaseTransformerListener {

	@Override
	public String onOutput(
		String output, String languageId, Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		return replace(output, languageId, tokens);
	}

	@Override
	public String onScript(
		String script, Document document, String languageId,
		Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		return replace(script, languageId, tokens);
	}

	/**
	 * Replace the properties in a given string with their values fetched from
	 * the template GLOBAL-PROPERTIES.
	 *
	 * @return the processed string
	 */
	protected String replace(
		String s, String languageId, Map<String, String> tokens) {

		String templateId = tokens.get("template_id");

		if ((templateId == null) ||
			((templateId != null) && templateId.equals(_GLOBAL_PROPERTIES))) {

			// Return the original string if no template ID is specified or if
			// the template ID is GLOBAL-PROPERTIES to prevent an infinite loop.

			return s;
		}

		Properties properties = new Properties();

		try {
			Map<String, String> newTokens = new HashMap<>();

			MapUtil.copy(tokens, newTokens);

			newTokens.put("template_id", _GLOBAL_PROPERTIES);

			long articleGroupId = GetterUtil.getLong(
				tokens.get("article_group_id"));

			String script = _journalHelper.getTemplateScript(
				articleGroupId, _GLOBAL_PROPERTIES, newTokens, languageId);

			PropertiesUtil.load(properties, script);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}

		if (properties.isEmpty()) {
			return s;
		}

		String[] escapedKeys = new String[properties.size()];
		String[] escapedValues = new String[properties.size()];

		String[] keys = new String[properties.size()];
		String[] values = new String[properties.size()];

		String[] tempEscapedKeys = new String[properties.size()];
		String[] tempEscapedValues = new String[properties.size()];

		int counter = 0;

		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();

			String escapedKey = StringBundler.concat(
				StringPool.AT, StringPool.AT, key, StringPool.AT,
				StringPool.AT);

			String actualKey = StringPool.AT + key + StringPool.AT;

			String tempEscapedKey =
				JournalTransformerListenerKeys.TEMP_ESCAPED_AT_OPEN + key +
					JournalTransformerListenerKeys.TEMP_ESCAPED_AT_CLOSE;

			escapedKeys[counter] = escapedKey;
			escapedValues[counter] = tempEscapedKey;

			keys[counter] = actualKey;
			values[counter] = value;

			tempEscapedKeys[counter] = tempEscapedKey;
			tempEscapedValues[counter] = actualKey;

			counter++;
		}

		s = StringUtil.replace(s, escapedKeys, escapedValues);

		s = StringUtil.replace(s, keys, values);

		s = StringUtil.replace(s, tempEscapedKeys, tempEscapedValues);

		return s;
	}

	private static final String _GLOBAL_PROPERTIES = "GLOBAL-PROPERTIES";

	private static final Log _log = LogFactoryUtil.getLog(
		JournalPropertiesTransformerListener.class);

	@Reference
	private JournalHelper _journalHelper;

}