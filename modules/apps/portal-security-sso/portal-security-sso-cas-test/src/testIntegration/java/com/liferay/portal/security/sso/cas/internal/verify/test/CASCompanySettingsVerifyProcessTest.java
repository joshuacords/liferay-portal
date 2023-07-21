/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.cas.internal.verify.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.cas.constants.CASConfigurationKeys;
import com.liferay.portal.security.sso.cas.constants.CASConstants;
import com.liferay.portal.security.sso.cas.constants.LegacyCASPropsKeys;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.verify.test.util.BaseCompanySettingsVerifyProcessTestCase;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Brian Greenwald
 */
@RunWith(Arquillian.class)
public class CASCompanySettingsVerifyProcessTest
	extends BaseCompanySettingsVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected void doVerify(
		PortletPreferences portletPreferences, Settings settings) {

		for (String key : LegacyCASPropsKeys.CAS_KEYS) {
			Assert.assertTrue(
				Validator.isNull(
					portletPreferences.getValue(key, StringPool.BLANK)));
		}

		Assert.assertTrue(
			GetterUtil.getBoolean(
				settings.getValue(
					CASConfigurationKeys.AUTH_ENABLED, StringPool.FALSE)));
		Assert.assertTrue(
			GetterUtil.getBoolean(
				settings.getValue(
					CASConfigurationKeys.IMPORT_FROM_LDAP, StringPool.FALSE)));
		Assert.assertEquals(
			"http://test.com/cas/login/url",
			settings.getValue(
				CASConfigurationKeys.LOGIN_URL, StringPool.BLANK));
		Assert.assertTrue(
			GetterUtil.getBoolean(
				settings.getValue(
					CASConfigurationKeys.LOGOUT_ON_SESSION_EXPIRATION,
					StringPool.FALSE)));
		Assert.assertEquals(
			"http://test.com/cas/logout/url",
			settings.getValue(
				CASConfigurationKeys.LOGOUT_URL, StringPool.BLANK));
		Assert.assertEquals(
			"http://test.com/cas/no/such/user/redirect/url",
			settings.getValue(
				CASConfigurationKeys.NO_SUCH_USER_REDIRECT_URL,
				StringPool.BLANK));
		Assert.assertEquals(
			"http://test.com/cas/server/name",
			settings.getValue(
				CASConfigurationKeys.SERVER_NAME, StringPool.BLANK));
		Assert.assertEquals(
			"http://test.com/cas/server/url",
			settings.getValue(
				CASConfigurationKeys.SERVER_URL, StringPool.BLANK));
		Assert.assertEquals(
			"http://test.com/cas/service/url",
			settings.getValue(
				CASConfigurationKeys.SERVICE_URL, StringPool.BLANK));
	}

	@Override
	protected String getSettingsId() {
		return CASConstants.SERVICE_NAME;
	}

	@Override
	protected String getVerifyProcessName() {
		return "com.liferay.portal.security.sso.cas";
	}

	@Override
	protected void populateLegacyProperties(UnicodeProperties properties) {
		properties.put(LegacyCASPropsKeys.CAS_AUTH_ENABLED, StringPool.TRUE);
		properties.put(
			LegacyCASPropsKeys.CAS_IMPORT_FROM_LDAP, StringPool.TRUE);
		properties.put(
			LegacyCASPropsKeys.CAS_LOGIN_URL, "http://test.com/cas/login/url");
		properties.put(
			LegacyCASPropsKeys.CAS_LOGOUT_ON_SESSION_EXPIRATION,
			StringPool.TRUE);
		properties.put(
			LegacyCASPropsKeys.CAS_LOGOUT_URL,
			"http://test.com/cas/logout/url");
		properties.put(
			LegacyCASPropsKeys.CAS_NO_SUCH_USER_REDIRECT_URL,
			"http://test.com/cas/no/such/user/redirect/url");
		properties.put(
			LegacyCASPropsKeys.CAS_SERVER_NAME,
			"http://test.com/cas/server/name");
		properties.put(
			LegacyCASPropsKeys.CAS_SERVER_URL,
			"http://test.com/cas/server/url");
		properties.put(
			LegacyCASPropsKeys.CAS_SERVICE_URL,
			"http://test.com/cas/service/url");
	}

}