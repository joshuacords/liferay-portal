/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.client.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.BundleActivator;

/**
 * @author Carlos Sierra Andrés
 */
@RunWith(Arquillian.class)
public class TokenCompanyTest extends BaseClientTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void test() throws Exception {
		getToken("oauthTestApplication", "myhost.xyz");

		getToken("oauthTestApplicationAllowed", "myhostallowed.xyz");

		Assert.assertEquals(
			"invalid_grant",
			getToken(
				"oauthTestApplicationDefault", "myhostdefaultuser.xyz",
				this::getClientCredentialsResponse, this::parseError));
	}

	public static class AnnotatedApplicationTestPreparatorBundleActivator
		extends BaseTestPreparatorBundleActivator {

		@Override
		protected void prepareTest() throws Exception {
			User user = addUser(createCompany("myhost"));

			createOAuth2Application(
				user.getCompanyId(), user, "oauthTestApplication");

			User adminUser = addAdminUser(createCompany("myhostallowed"));

			createOAuth2Application(
				adminUser.getCompanyId(), adminUser,
				"oauthTestApplicationAllowed");

			Company company = createCompany("myhostdefaultuser");

			createOAuth2Application(
				company.getCompanyId(), company.getDefaultUser(),
				"oauthTestApplicationDefault");
		}

	}

	@Override
	protected BundleActivator getBundleActivator() {
		return new AnnotatedApplicationTestPreparatorBundleActivator();
	}

}