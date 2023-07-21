/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AccountField service. Represents a row in the &quot;Koroneiki_AccountField&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountFieldModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldImpl"
)
@ProviderType
public interface AccountField extends AccountFieldModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountField, Long> ACCOUNT_FIELD_ID_ACCESSOR =
		new Accessor<AccountField, Long>() {

			@Override
			public Long get(AccountField accountField) {
				return accountField.getAccountFieldId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AccountField> getTypeClass() {
				return AccountField.class;
			}

		};

}