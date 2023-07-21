/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.shindig.oauth;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import org.apache.shindig.common.crypto.BlobCrypter;
import org.apache.shindig.gadgets.oauth.OAuthFetcherConfig;
import org.apache.shindig.gadgets.oauth.OAuthModule;
import org.apache.shindig.gadgets.oauth.OAuthRequest;
import org.apache.shindig.gadgets.oauth.OAuthStore;

/**
 * @author Dennis Ju
 */
public class LiferayOAuthModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(
			BlobCrypter.class
		).annotatedWith(
			Names.named(OAuthFetcherConfig.OAUTH_STATE_CRYPTER)
		).toProvider(
			OAuthModule.OAuthCrypterProvider.class
		);
		bind(
			OAuthRequest.class
		).toProvider(
			OAuthModule.OAuthRequestProvider.class
		);
		bind(
			OAuthStore.class
		).toProvider(
			LiferayOAuthStoreProvider.class
		);
	}

}