/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.minifier;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.internal.minifier.MinifierThreadLocal;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Roberto Díaz
 */
public class MinifierUtil {

	public static String minifyCss(String content) {
		if (PropsValues.MINIFIER_ENABLED && MinifierThreadLocal.isEnabled()) {
			return _minifyCss(content);
		}

		return content;
	}

	public static String minifyJavaScript(String resourceName, String content) {
		if (PropsValues.MINIFIER_ENABLED && MinifierThreadLocal.isEnabled()) {
			return _minifyJavaScript(resourceName, content);
		}

		return content;
	}

	private static String _minifyCss(String content) {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		try {
			CSSCompressor cssCompressor = new CSSCompressor(
				new UnsyncStringReader(content));

			cssCompressor.compress(
				unsyncStringWriter, PropsValues.YUI_COMPRESSOR_CSS_LINE_BREAK);

			return unsyncStringWriter.toString();
		}
		catch (Exception exception) {
			_log.error("Unable to minify CSS:\n" + content, exception);

			unsyncStringWriter.append(content);

			return unsyncStringWriter.toString();
		}
		finally {
			if (_log.isDebugEnabled()) {
				int length = 0;

				if (content != null) {
					byte[] bytes = content.getBytes();

					length = bytes.length;
				}

				_log.debug(
					StringBundler.concat(
						"Minification for ", length, " bytes of CSS took ",
						stopWatch.getTime(), " ms"));
			}
		}
	}

	private static String _minifyJavaScript(
		String resourceName, String content) {

		JavaScriptMinifier javaScriptMinifier =
			_javaScriptMinifierServiceTracker.getService();

		if (javaScriptMinifier == null) {
			return content;
		}

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			return javaScriptMinifier.compress(resourceName, content);
		}
		finally {
			if (_log.isDebugEnabled()) {
				int length = 0;

				if (content != null) {
					byte[] bytes = content.getBytes();

					length = bytes.length;
				}

				_log.debug(
					StringBundler.concat(
						"Minification for ", length,
						" bytes of JavaScript in resource ", resourceName,
						" took ", stopWatch.getTime(), " ms"));
			}
		}
	}

	private MinifierUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(MinifierUtil.class);

	private static final ServiceTracker<JavaScriptMinifier, JavaScriptMinifier>
		_javaScriptMinifierServiceTracker;

	static {
		Registry registry = RegistryUtil.getRegistry();

		_javaScriptMinifierServiceTracker = registry.trackServices(
			JavaScriptMinifier.class);

		_javaScriptMinifierServiceTracker.open();
	}

}