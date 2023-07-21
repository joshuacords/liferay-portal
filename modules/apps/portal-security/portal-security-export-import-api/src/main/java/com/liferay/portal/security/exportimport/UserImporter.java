/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.exportimport;

import com.liferay.portal.kernel.model.User;

/**
 * @author Michael C. Han
 */
public interface UserImporter {

	public long getLastImportTime() throws Exception;

	public User importUser(
			long ldapServerId, long companyId, String emailAddress,
			String screenName)
		throws Exception;

	public User importUser(
			long companyId, String emailAddress, String screenName)
		throws Exception;

	public User importUserByScreenName(long companyId, String screenName)
		throws Exception;

	public void importUsers() throws Exception;

	public void importUsers(long companyId) throws Exception;

	public void importUsers(long ldapServerId, long companyId) throws Exception;

}