/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.vldap.server.internal.directory.ldap.Directory;
import com.liferay.vldap.server.internal.directory.ldap.TopDirectory;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class TopBuilder extends SingleDirectoryBuilder {

	@Override
	protected Directory getDirectory() {
		return new TopDirectory();
	}

}