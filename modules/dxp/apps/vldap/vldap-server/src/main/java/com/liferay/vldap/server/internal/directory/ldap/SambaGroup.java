/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

/**
 * @author Minhchau Dang
 */
public class SambaGroup {

	public SambaGroup(String name, String sambaSID, String gidNumber) {
		_name = name;
		_sambaSID = sambaSID;
		_gidNumber = gidNumber;
	}

	public String getGIDNumber() {
		return _gidNumber;
	}

	public String getName() {
		return _name;
	}

	public String getSambaSID() {
		return _sambaSID;
	}

	private final String _gidNumber;
	private final String _name;
	private final String _sambaSID;

}