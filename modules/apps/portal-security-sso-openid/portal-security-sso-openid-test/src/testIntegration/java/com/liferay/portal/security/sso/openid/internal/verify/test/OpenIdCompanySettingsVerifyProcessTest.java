/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.internal.verify.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.openid.constants.LegacyOpenIdPropsKeys;
import com.liferay.portal.security.sso.openid.constants.OpenIdConfigurationKeys;
import com.liferay.portal.security.sso.openid.constants.OpenIdConstants;
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
public class OpenIdCompanySettingsVerifyProcessTest
	extends BaseCompanySettingsVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected void doVerify(
		PortletPreferences portletPreferences, Settings settings) {

		Assert.assertTrue(
			Validator.isNull(
				portletPreferences.getValue(
					LegacyOpenIdPropsKeys.OPENID_AUTH_ENABLED,
					StringPool.BLANK)));
		Assert.assertEquals(
			StringPool.TRUE,
			settings.getValue(
				OpenIdConfigurationKeys.AUTH_ENABLED, StringPool.FALSE));
	}

	@Override
	protected String getSettingsId() {
		return OpenIdConstants.SERVICE_NAME;
	}

	@Override
	protected String getVerifyProcessName() {
		return "com.liferay.portal.security.sso.openid";
	}

	@Override
	protected void populateLegacyProperties(UnicodeProperties properties) {
		properties.put(LegacyOpenIdPropsKeys.OPENID_AUTH_ENABLED, "true");
	}

}