/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.servlet.taglib.util.OutputData;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Shuyang Zhou
 */
public class OutputTag extends PositionTagSupport {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getDataSB(ServletRequest, String)}
	 */
	@Deprecated
	public static com.liferay.portal.kernel.util.StringBundler getData(
		ServletRequest servletRequest, String webKey) {

		StringBundler petraSB = getDataSB(servletRequest, webKey);

		if (petraSB == null) {
			return null;
		}

		com.liferay.portal.kernel.util.StringBundler sb =
			new com.liferay.portal.kernel.util.StringBundler(
				petraSB.getStrings());

		sb.setIndex(petraSB.index());

		return sb;
	}

	public static StringBundler getDataSB(
		ServletRequest servletRequest, String webKey) {

		OutputData outputData = (OutputData)servletRequest.getAttribute(
			WebKeys.OUTPUT_DATA);

		if (outputData == null) {
			return null;
		}

		return outputData.getMergedDataSB(webKey);
	}

	public OutputTag(String stringBundlerKey) {
		_webKey = stringBundlerKey;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			if (_output) {
				String bodyContentString =
					getBodyContentAsStringBundler().toString();

				bodyContentString = _addAtrribute(
					bodyContentString, "link", "data-senna-track",
					"\"temporary\"");
				bodyContentString = _addAtrribute(
					bodyContentString, "script", "data-senna-track",
					"\"permanent\"");
				bodyContentString = _addAtrribute(
					bodyContentString, "style", "data-senna-track",
					"\"temporary\"");

				if (isPositionInLine()) {
					JspWriter jspWriter = pageContext.getOut();

					jspWriter.write(bodyContentString);
				}
				else {
					OutputData outputData = _getOutputData(
						pageContext.getRequest());

					outputData.addDataSB(
						_outputKey, _webKey,
						new StringBundler(bodyContentString));
				}
			}

			return EVAL_PAGE;
		}
		catch (Exception exception) {
			throw new JspException(exception);
		}
		finally {
			cleanUp();
		}
	}

	@Override
	public int doStartTag() {
		if (Validator.isNotNull(_outputKey)) {
			OutputData outputData = _getOutputData(pageContext.getRequest());

			if (!outputData.addOutputKey(_outputKey)) {
				_output = false;

				return SKIP_BODY;
			}
		}

		_output = true;

		return EVAL_BODY_BUFFERED;
	}

	public void setOutputKey(String outputKey) {
		_outputKey = outputKey;
	}

	private static OutputData _getOutputData(ServletRequest servletRequest) {
		OutputData outputData = (OutputData)servletRequest.getAttribute(
			WebKeys.OUTPUT_DATA);

		if (outputData == null) {
			outputData = new OutputData();

			servletRequest.setAttribute(WebKeys.OUTPUT_DATA, outputData);
		}

		return outputData;
	}

	private String _addAtrribute(
		String content, String tagName, String attributeName,
		String attributeValue) {

		int x = 0;
		int y = 0;

		while (x >= 0) {
			x = content.indexOf("<" + tagName, y);

			if (x < 0) {
				break;
			}

			y = content.indexOf(">", x);

			if (y < 0) {
				break;
			}

			String subcontent = content.substring(x, y);

			if (!subcontent.contains(attributeName)) {
				content = StringUtil.insert(
					content,
					StringBundler.concat(
						" ", attributeName, "=", attributeValue),
					x + tagName.length() + 1);
			}
		}

		return content;
	}

	private boolean _output;
	private String _outputKey;
	private final String _webKey;

}