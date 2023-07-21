/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.renderer.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class SoyJavaScriptRendererUtil {

	public static String getJavaScript(
		Map<String, Object> context, String id, String module) {

		return getJavaScript(context, id, module, true);
	}

	public static String getJavaScript(
		Map<String, Object> context, String id, String module,
		boolean wrapper) {

		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

		String contextString = jsonSerializer.serializeDeep(context);
		String wrapperString = jsonSerializer.serialize(wrapper);

		return StringUtil.replace(
			_JAVA_SCRIPT_TPL,
			new String[] {"$CONTEXT", "$ID", "$MODULE", "$WRAPPER"},
			new String[] {contextString, id, module, wrapperString});
	}

	private static final String _JAVA_SCRIPT_TPL;

	private static final Log _log = LogFactoryUtil.getLog(
		SoyJavaScriptRendererUtil.class);

	static {
		InputStream inputStream =
			SoyJavaScriptRendererUtil.class.getResourceAsStream(
				"dependencies/bootstrap.js.tpl");

		String js = StringPool.BLANK;

		try {
			js = StringUtil.read(inputStream);
		}
		catch (Exception exception) {
			_log.error("Unable to read template", exception);
		}

		_JAVA_SCRIPT_TPL = js;
	}

}