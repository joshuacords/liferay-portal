/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.service.impl.UserLocalServiceImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Peter Fellwock
 */
public class AuthVerifierPipelineTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testVerifyRequest() throws PortalException {
		RegistryUtil.setRegistry(new BasicRegistryImpl());

		Registry registry = RegistryUtil.getRegistry();

		ReflectionTestUtil.setFieldValue(
			UserLocalServiceUtil.class, "_service",
			new UserLocalServiceImpl() {

				@Override
				public User fetchUser(long userId) {
					User user = new UserImpl();

					user.setStatus(WorkflowConstants.STATUS_APPROVED);

					return user;
				}

			});

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		authVerifierResult.setSettings(new HashMap<>());
		authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);

		ServiceRegistration<AuthVerifier> serviceRegistration =
			registry.registerService(
				AuthVerifier.class,
				(AuthVerifier)ProxyUtil.newProxyInstance(
					AuthVerifier.class.getClassLoader(),
					new Class<?>[] {AuthVerifier.class},
					(proxy, method, args) -> {
						if (Objects.equals(method.getName(), "verify")) {
							return authVerifierResult;
						}

						return null;
					}),
				Collections.singletonMap("urls.includes", _BASE_URL + "/*"));

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest(new MockServletContext());

		mockHttpServletRequest.setRequestURI(_BASE_URL + "/Hello");

		AccessControlContext accessControlContext = new AccessControlContext();

		accessControlContext.setRequest(mockHttpServletRequest);

		try {
			Assert.assertSame(
				authVerifierResult,
				AuthVerifierPipeline.verifyRequest(accessControlContext));
		}
		finally {
			serviceRegistration.unregister();
		}
	}

	private static final String _BASE_URL = "/TestAuthVerifier";

}