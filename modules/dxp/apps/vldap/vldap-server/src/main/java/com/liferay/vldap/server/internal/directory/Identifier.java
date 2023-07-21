/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory;

/**
 * @author Jonathan McCann
 */
public class Identifier {

	public Identifier(String rdnType, String rdnValue) {
		_rdnType = rdnType;
		_rdnValue = rdnValue;
	}

	public String getRdnType() {
		return _rdnType;
	}

	public String getRdnValue() {
		return _rdnValue;
	}

	private final String _rdnType;
	private final String _rdnValue;

}