/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.OutputStream;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface OAuth {

	public String addParameters(String url, String... parameters)
		throws OAuthException;

	public void authorize(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException;

	public void formEncode(
			String token, String tokenSecret, OutputStream outputStream)
		throws OAuthException;

	public void generateAccessToken(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException;

	public void generateRequestToken(OAuthAccessor oAuthAccessor);

	public OAuthAccessor getOAuthAccessor(OAuthMessage oAuthMessage)
		throws PortalException;

	public OAuthConsumer getOAuthConsumer(OAuthMessage oAuthMessage)
		throws PortalException;

	public OAuthMessage getOAuthMessage(HttpServletRequest httpServletRequest);

	public OAuthMessage getOAuthMessage(
		HttpServletRequest httpServletRequest, String url);

	public OAuthMessage getOAuthMessage(PortletRequest portletRequest);

	public OAuthMessage getOAuthMessage(
		PortletRequest portletRequest, String url);

	public void handleException(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Exception exception,
			boolean sendBody)
		throws OAuthException;

	public String randomizeToken(String token);

	public void validateOAuthMessage(
			OAuthMessage oAuthMessage, OAuthAccessor oAuthAccessor)
		throws OAuthException;

}