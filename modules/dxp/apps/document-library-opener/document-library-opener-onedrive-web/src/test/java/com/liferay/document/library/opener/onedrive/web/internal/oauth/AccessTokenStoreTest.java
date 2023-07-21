/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.onedrive.web.internal.oauth;

import com.github.scribejava.core.model.OAuth2AccessToken;

import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Optional;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Cristina González
 */
public class AccessTokenStoreTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testAdd() {
		AccessTokenStore accessTokenStore = new AccessTokenStore();

		AccessToken initialAccessToken = new AccessToken(
			new OAuth2AccessToken(RandomTestUtil.randomString()));

		long companyId = RandomTestUtil.randomInt();
		long userId = RandomTestUtil.randomInt();

		accessTokenStore.add(companyId, userId, initialAccessToken);

		Optional<AccessToken> accessTokenOptional =
			accessTokenStore.getAccessTokenOptional(companyId, userId);

		AccessToken actualAccessToken = accessTokenOptional.get();

		Assert.assertEquals(
			initialAccessToken.getAccessToken(),
			actualAccessToken.getAccessToken());
	}

	@Test
	public void testDelete() {
		AccessTokenStore accessTokenStore = new AccessTokenStore();

		AccessToken initialAccessToken = new AccessToken(
			new OAuth2AccessToken(RandomTestUtil.randomString()));

		long companyId = RandomTestUtil.randomInt();
		long userId = RandomTestUtil.randomInt();

		accessTokenStore.add(companyId, userId, initialAccessToken);

		accessTokenStore.delete(companyId, userId);

		Optional<AccessToken> accessTokenOptional =
			accessTokenStore.getAccessTokenOptional(companyId, userId);

		Assert.assertTrue(!accessTokenOptional.isPresent());
	}

	@Test
	public void testGetWithEmptyAccessTokenStore() {
		AccessTokenStore accessTokenStore = new AccessTokenStore();

		Optional<AccessToken> accessTokenOptional =
			accessTokenStore.getAccessTokenOptional(
				RandomTestUtil.randomInt(), RandomTestUtil.randomInt());

		Assert.assertTrue(!accessTokenOptional.isPresent());
	}

}