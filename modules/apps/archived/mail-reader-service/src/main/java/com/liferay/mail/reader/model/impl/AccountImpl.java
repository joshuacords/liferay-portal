/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.model.impl;

import com.liferay.mail.reader.internal.util.PasswordUtil;

/**
 * @author Scott Lee
 */
public class AccountImpl extends AccountBaseImpl {

	@Override
	public String getPasswordDecrypted() {
		return PasswordUtil.decrypt(getPassword());
	}

	@Override
	public void setPasswordDecrypted(String unencryptedPassword) {
		String encryptedPassword = PasswordUtil.encrypt(unencryptedPassword);

		setPassword(encryptedPassword);
	}

}