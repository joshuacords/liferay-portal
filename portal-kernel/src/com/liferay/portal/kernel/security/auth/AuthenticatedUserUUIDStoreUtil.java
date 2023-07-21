/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.auth;

/**
 * @author     Michael C. Han
 * @author     Raymond Augé
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class AuthenticatedUserUUIDStoreUtil {

	public static boolean exists(String userUUID) {
		return getAuthenticatedUserUUIDStore().exists(userUUID);
	}

	public static AuthenticatedUserUUIDStore getAuthenticatedUserUUIDStore() {
		return _authenticatedUserUUIDStore;
	}

	public static boolean register(String userUUID) {
		return getAuthenticatedUserUUIDStore().register(userUUID);
	}

	public static boolean unregister(String userUUID) {
		return getAuthenticatedUserUUIDStore().unregister(userUUID);
	}

	public void setAuthenticatedUserUUIDStore(
		AuthenticatedUserUUIDStore authenticatedUserUUIDStore) {

		_authenticatedUserUUIDStore = authenticatedUserUUIDStore;
	}

	private static AuthenticatedUserUUIDStore _authenticatedUserUUIDStore;

}