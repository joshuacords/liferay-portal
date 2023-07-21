/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.kernel.util;

import com.liferay.mail.kernel.model.Filter;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface Hook {

	public void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy);

	public void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress);

	public void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage);

	public void deleteEmailAddress(long companyId, long userId);

	public void deleteUser(long companyId, long userId);

	public void updateBlocked(
		long companyId, long userId, List<String> blocked);

	public void updateEmailAddress(
		long companyId, long userId, String emailAddress);

	public void updatePassword(long companyId, long userId, String password);

}