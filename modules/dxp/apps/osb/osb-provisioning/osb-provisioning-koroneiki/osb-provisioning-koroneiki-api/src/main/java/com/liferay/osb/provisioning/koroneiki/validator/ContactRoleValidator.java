/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.validator;

/**
 * @author Kyle Bischof
 */
public interface ContactRoleValidator {

	public void validateAdminContactRoleUnassignment(
			String accountKey, String emailAddress)
		throws Exception;

}