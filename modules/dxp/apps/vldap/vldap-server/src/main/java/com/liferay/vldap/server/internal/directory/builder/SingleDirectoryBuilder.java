/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.vldap.server.internal.directory.FilterConstraint;
import com.liferay.vldap.server.internal.directory.SearchBase;
import com.liferay.vldap.server.internal.directory.ldap.Directory;

import java.util.List;

/**
 * @author Andrew Betts
 */
public abstract class SingleDirectoryBuilder extends DirectoryBuilder {

	@Override
	public List<Directory> buildDirectories(
		SearchBase searchBase, List<FilterConstraint> filterConstraints) {

		return ListUtil.toList(getDirectory());
	}

	@Override
	public boolean isValidAttribute(String attributeId, String value) {
		return true;
	}

	protected abstract Directory getDirectory();

}