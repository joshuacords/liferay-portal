/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.rule.group.action;

import com.liferay.mobile.device.rules.action.ActionHandler;
import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public abstract class BaseRedirectActionHandler implements ActionHandler {

	@Override
	public void applyAction(
			MDRAction mdrAction, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		String url = getURL(mdrAction, httpServletRequest, httpServletResponse);

		if (Validator.isNull(url)) {
			if (_log.isInfoEnabled()) {
				_log.info("URL is null");
			}

			return;
		}

		String requestURL = String.valueOf(httpServletRequest.getRequestURL());

		if (StringUtil.contains(requestURL, url)) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping redirect. Current URL contains redirect URL.");
			}

			return;
		}

		try {
			httpServletResponse.sendRedirect(url);
		}
		catch (IOException ioException) {
			throw new PortalException(
				"Unable to redirect to " + url, ioException);
		}
	}

	protected abstract String getURL(
			MDRAction mdrAction, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseRedirectActionHandler.class);

}