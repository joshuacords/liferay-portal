/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.onedrive.web.internal.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cristina González
 */
public class AccessTokenStore {

	public void add(long companyId, long userId, AccessToken accessToken) {
		Map<Long, AccessToken> companyAccessTokens =
			_accessTokenMap.computeIfAbsent(
				companyId, key -> new ConcurrentHashMap<>());

		companyAccessTokens.put(userId, accessToken);
	}

	public void delete(long companyId, long userId) {
		Map<Long, AccessToken> companyAccessTokens =
			_accessTokenMap.computeIfAbsent(
				companyId, key -> new ConcurrentHashMap<>());

		companyAccessTokens.remove(userId);
	}

	public Optional<AccessToken> getAccessTokenOptional(
		long companyId, long userId) {

		Map<Long, AccessToken> companyAccessTokens =
			_accessTokenMap.getOrDefault(companyId, new HashMap<>());

		return Optional.ofNullable(companyAccessTokens.get(userId));
	}

	private final Map<Long, Map<Long, AccessToken>> _accessTokenMap =
		new ConcurrentHashMap<>();

}