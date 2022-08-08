/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.rest.internal.security.auth.verifier;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.auth.ProvisioningContactThreadLocal;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

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
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "auth.verifier.ProvisioningAuthVerifier.urls.includes=/o/provisioning-rest/*",
	service = AuthVerifier.class
)
public class ProvisioningAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return ProvisioningAuthVerifier.class.getSimpleName();
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
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}

			HttpServletResponse httpServletResponse =
				accessControlContext.getResponse();

			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					httpServletResponse.getOutputStream())) {

				objectOutputStream.writeObject(exception);

				authVerifierResult.setState(
					AuthVerifierResult.State.INVALID_CREDENTIALS);
			}
			catch (IOException ioException) {
				_log.error(ioException, ioException);

				throw new AuthException(exception);
			}
		}

		return authVerifierResult;
	}

	protected String[] verify(HttpServletRequest httpServletRequest)
		throws Exception {

		String oktaSessionId = httpServletRequest.getHeader("Okta-Session-ID");

		if (Validator.isNull(oktaSessionId)) {
			_logRequest(httpServletRequest, null);

			return null;
		}

		Contact contact = _contactIdentityProvider.fetchContactBySessionId(
			oktaSessionId);

		if (contact != null) {
			if (!_ipAddresses.contains(httpServletRequest.getRemoteAddr())) {
				_logRequest(httpServletRequest, contact.getEmailAddress());

				_ipAddresses.add(httpServletRequest.getRemoteAddr());
			}

			ProvisioningContactThreadLocal.setContact(contact);

			long companyId = _portal.getCompanyId(httpServletRequest);

			long userId = _userLocalService.getDefaultUserId(companyId);

			String[] credentials = new String[2];

			credentials[0] = String.valueOf(userId);
			credentials[1] = StringPool.BLANK;

			return credentials;
		}

		return null;
	}

	private void _logRequest(
		HttpServletRequest httpServletRequest, String emailAddress) {

		if (_log.isInfoEnabled()) {
			StringBundler sb = new StringBundler(9);

			if (Validator.isNotNull(emailAddress)) {
				sb.append(emailAddress);
				sb.append(StringPool.BLANK);
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
		ProvisioningAuthVerifier.class);

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	private final Set<String> _ipAddresses = Collections.synchronizedSet(
		new HashSet<>());

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}