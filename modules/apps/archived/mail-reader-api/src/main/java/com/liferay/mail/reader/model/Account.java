/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Account service. Represents a row in the &quot;Mail_Account&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountModel
 * @generated
 */
@ImplementationClassName("com.liferay.mail.reader.model.impl.AccountImpl")
@ProviderType
public interface Account extends AccountModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.mail.reader.model.impl.AccountImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Account, Long> ACCOUNT_ID_ACCESSOR =
		new Accessor<Account, Long>() {

			@Override
			public Long get(Account account) {
				return account.getAccountId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Account> getTypeClass() {
				return Account.class;
			}

		};

	public String getPasswordDecrypted();

	public void setPasswordDecrypted(String unencryptedPassword);

}