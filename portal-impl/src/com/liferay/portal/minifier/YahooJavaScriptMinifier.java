/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.minifier;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsValues;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

/**
 * @author     Carlos Sierra Andrés
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class YahooJavaScriptMinifier implements JavaScriptMinifier {

	@Override
	public String compress(String resourceName, String content) {
		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		try {
			JavaScriptCompressor javaScriptCompressor =
				new JavaScriptCompressor(
					new UnsyncStringReader(content),
					new JavaScriptErrorReporter());

			javaScriptCompressor.compress(
				unsyncStringWriter, PropsValues.YUI_COMPRESSOR_JS_LINE_BREAK,
				PropsValues.YUI_COMPRESSOR_JS_MUNGE,
				PropsValues.YUI_COMPRESSOR_JS_VERBOSE,
				PropsValues.YUI_COMPRESSOR_JS_PRESERVE_ALL_SEMICOLONS,
				PropsValues.YUI_COMPRESSOR_JS_DISABLE_OPTIMIZATIONS);
		}
		catch (Exception exception) {
			_log.error("Unable to minify JavaScript:\n" + content);

			unsyncStringWriter.append(content);
		}

		return unsyncStringWriter.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		YahooJavaScriptMinifier.class);

	private static class JavaScriptErrorReporter implements ErrorReporter {

		@Override
		public void error(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			if (line < 0) {
				_log.error(message);
			}
			else {
				_log.error(
					StringBundler.concat(
						line, ": ", lineOffset, ": ", message));
			}
		}

		@Override
		public EvaluatorException runtimeError(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			error(message, sourceName, line, lineSource, lineOffset);

			return new EvaluatorException(message);
		}

		@Override
		public void warning(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			if (!_log.isWarnEnabled()) {
				return;
			}

			if (line < 0) {
				_log.warn(message);
			}
			else {
				_log.warn(
					StringBundler.concat(
						line, ": ", lineOffset, ": ", message));
			}
		}

	}

}