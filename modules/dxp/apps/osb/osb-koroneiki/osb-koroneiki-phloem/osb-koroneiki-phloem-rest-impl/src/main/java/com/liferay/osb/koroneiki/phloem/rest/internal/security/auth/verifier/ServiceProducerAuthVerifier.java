/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.security.auth.verifier;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.osb.koroneiki.scion.service.AuthenticationTokenLocalService;
import com.liferay.osb.koroneiki.scion.service.ServiceProducerLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "auth.verifier.ServiceProducerAuthVerifier.urls.includes=/o/koroneiki-rest/*",
	service = AuthVerifier.class
)
public class ServiceProducerAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return ServiceProducerAuthVerifier.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		try {
			String[] credentials = verify(accessControlContext.getRequest());

			if (credentials != null) {
				authVerifierResult.setPassword(credentials[1]);
				authVerifierResult.setPasswordBasedAuthentication(true);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(credentials[0]));
			}
		}
		catch (AuthException authException) {
			if (_log.isDebugEnabled()) {
				_log.debug(authException, authException);
			}

			HttpServletResponse httpServletResponse =
				accessControlContext.getResponse();

			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					httpServletResponse.getOutputStream())) {

				objectOutputStream.writeObject(authException);

				authVerifierResult.setState(
					AuthVerifierResult.State.INVALID_CREDENTIALS);
			}
			catch (IOException ioException) {
				_log.error(ioException, ioException);

				throw authException;
			}
		}

		return authVerifierResult;
	}

	protected String[] verify(HttpServletRequest httpServletRequest)
		throws AuthException {

		String apiToken = httpServletRequest.getHeader("API_Token");

		if (Validator.isNull(apiToken)) {
			_logRequest(httpServletRequest, null);

			return null;
		}

		String digest = DigesterUtil.digestBase64(Digester.SHA_256, apiToken);

		AuthenticationToken authenticationToken =
			_authenticationTokenLocalService.fetchAuthenticationToken(
				digest, WorkflowConstants.STATUS_APPROVED);

		if (authenticationToken == null) {
			_logRequest(httpServletRequest, null);

			return null;
		}

		ServiceProducer serviceProducer =
			_serviceProducerLocalService.fetchServiceProducer(
				authenticationToken.getServiceProducerId());

		if (serviceProducer == null) {
			return null;
		}

		if (!_ipAddresses.contains(httpServletRequest.getRemoteAddr())) {
			_logRequest(httpServletRequest, serviceProducer.getName());

			_ipAddresses.add(httpServletRequest.getRemoteAddr());
		}

		String[] credentials = new String[2];

		credentials[0] = String.valueOf(
			serviceProducer.getAuthorizationUserId());
		credentials[1] = StringPool.BLANK;

		return credentials;
	}

	private void _logRequest(
		HttpServletRequest httpServletRequest, String system) {

		if (_log.isInfoEnabled()) {
			StringBundler sb = new StringBundler(9);

			if (Validator.isNotNull(system)) {
				sb.append(system);
				sb.append(StringPool.SPACE);
			}

			sb.append(httpServletRequest.getRemoteAddr());
			sb.append(StringPool.SPACE);
			sb.append(httpServletRequest.getMethod());
			sb.append(StringPool.SPACE);
			sb.append(httpServletRequest.getRequestURI());

			if (Validator.isNotNull(httpServletRequest.getQueryString())) {
				sb.append(StringPool.QUESTION);
				sb.append(httpServletRequest.getQueryString());
			}

			_log.info(sb.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceProducerAuthVerifier.class);

	@Reference
	private AuthenticationTokenLocalService _authenticationTokenLocalService;

	private final Set<String> _ipAddresses = Collections.synchronizedSet(
		new HashSet<>());

	@Reference
	private ServiceProducerLocalService _serviceProducerLocalService;

}