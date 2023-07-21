/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Organization {

	public Organization(long organizationId, String name, String path) {
		_organizationId = organizationId;
		_name = name;
		_path = path;
	}

	public String getName() {
		return _name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public String getPath() {
		return _path;
	}

	private final String _name;
	private final long _organizationId;
	private final String _path;

}