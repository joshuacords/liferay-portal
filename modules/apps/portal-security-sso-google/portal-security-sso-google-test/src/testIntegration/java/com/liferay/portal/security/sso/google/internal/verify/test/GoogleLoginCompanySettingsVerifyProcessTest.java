/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.google.internal.verify.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.google.constants.GoogleAuthorizationConfigurationKeys;
import com.liferay.portal.security.sso.google.constants.GoogleConstants;
import com.liferay.portal.security.sso.google.constants.LegacyGoogleLoginPropsKeys;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.verify.test.util.BaseCompanySettingsVerifyProcessTestCase;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Stian Sigvartsen
 */
@RunWith(Arquillian.class)
public class GoogleLoginCompanySettingsVerifyProcessTest
	extends BaseCompanySettingsVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected void doVerify(
		PortletPreferences portletPreferences, Settings settings) {

		for (String key : LegacyGoogleLoginPropsKeys.GOOGLE_LOGIN_KEYS) {
			Assert.assertTrue(
				Validator.isNull(
					portletPreferences.getValue(key, StringPool.BLANK)));
		}

		Assert.assertEquals(
			StringPool.TRUE,
			settings.getValue(
				GoogleAuthorizationConfigurationKeys.AUTH_ENABLED,
				StringPool.FALSE));
		Assert.assertEquals(
			"test_client_id",
			settings.getValue(
				GoogleAuthorizationConfigurationKeys.CLIENT_ID,
				StringPool.BLANK));
		Assert.assertEquals(
			"test_client_secret",
			settings.getValue(
				GoogleAuthorizationConfigurationKeys.CLIENT_SECRET,
				StringPool.BLANK));
	}

	@Override
	protected String getSettingsId() {
		return GoogleConstants.SERVICE_NAME;
	}

	@Override
	protected String getVerifyProcessName() {
		return "com.liferay.portal.security.sso.google";
	}

	@Override
	protected void populateLegacyProperties(UnicodeProperties properties) {
		properties.put(LegacyGoogleLoginPropsKeys.AUTH_ENABLED, "true");
		properties.put(LegacyGoogleLoginPropsKeys.CLIENT_ID, "test_client_id");
		properties.put(
			LegacyGoogleLoginPropsKeys.CLIENT_SECRET, "test_client_secret");
	}

}