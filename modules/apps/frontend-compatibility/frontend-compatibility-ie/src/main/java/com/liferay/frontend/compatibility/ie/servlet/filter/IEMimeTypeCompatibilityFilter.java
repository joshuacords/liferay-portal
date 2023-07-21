/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.compatibility.ie.servlet.filter;

import com.liferay.portal.kernel.servlet.BrowserSniffer;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.servlet.filters.BasePortalFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {
		"servlet-context-name=",
		"servlet-filter-name=IE MimeType Compatibility Filter", "url-pattern=/*"
	},
	service = Filter.class
)
public class IEMimeTypeCompatibilityFilter extends BasePortalFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		return _browserSniffer.isIe(httpServletRequest);
	}

	@Override
	protected void processFilter(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws Exception {

		processFilter(
			IEMimeTypeCompatibilityFilter.class.getName(), httpServletRequest,
			new IEMimeTypeCompatibilityResponseWrapper(httpServletResponse),
			filterChain);
	}

	@Reference
	private BrowserSniffer _browserSniffer;

	private static class IEMimeTypeCompatibilityResponseWrapper
		extends HttpServletResponseWrapper {

		@Override
		public void setContentType(String contentType) {
			if (contentType.equals(ContentTypes.IMAGE_X_MS_BMP)) {
				contentType = ContentTypes.IMAGE_BMP;
			}

			super.setContentType(contentType);
		}

		private IEMimeTypeCompatibilityResponseWrapper(
			HttpServletResponse httpServletResponse) {

			super(httpServletResponse);
		}

	}

}