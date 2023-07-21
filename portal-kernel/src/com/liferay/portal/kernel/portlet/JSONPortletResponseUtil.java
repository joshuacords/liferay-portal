/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class JSONPortletResponseUtil {

	public static void writeJSON(
			PortletRequest portletRequest, MimeResponse mimeResponse,
			Object jsonObj)
		throws IOException {

		mimeResponse.setContentType(_getContentType(portletRequest));

		PortletResponseUtil.write(mimeResponse, jsonObj.toString());

		mimeResponse.flushBuffer();
	}

	public static void writeJSON(
			PortletRequest portletRequest, PortletResponse portletResponse,
			Object jsonObj)
		throws IOException {

		HttpServletResponse httpServletResponse =
			PortalUtil.getHttpServletResponse(portletResponse);

		httpServletResponse.setContentType(_getContentType(portletRequest));

		ServletResponseUtil.write(httpServletResponse, jsonObj.toString());

		httpServletResponse.flushBuffer();
	}

	private static String _getContentType(PortletRequest portletRequest) {
		String contentType = ContentTypes.APPLICATION_JSON;

		if (BrowserSnifferUtil.isIe(
				PortalUtil.getHttpServletRequest(portletRequest))) {

			contentType = ContentTypes.TEXT_PLAIN;
		}

		return contentType;
	}

}