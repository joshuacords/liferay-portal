/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * @author     Carlos Sierra Andrés
 * @deprecated As of Judson (7.1.x), replaced by {@link PipingServletResponse}
 */
@Deprecated
public class JspWriterHttpServletResponse extends HttpServletResponseWrapper {

	public JspWriterHttpServletResponse(PageContext pageContext) {
		super((HttpServletResponse)pageContext.getResponse());

		_pageContext = pageContext;
	}

	@Override
	public ServletOutputStream getOutputStream() {
		return new ServletOutputStream() {

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void write(int b) throws IOException {
				JspWriter jspWriter = _pageContext.getOut();

				jspWriter.write(b);
			}

		};
	}

	@Override
	public PrintWriter getWriter() {
		return new PrintWriter(_pageContext.getOut(), true);
	}

	private final PageContext _pageContext;

}