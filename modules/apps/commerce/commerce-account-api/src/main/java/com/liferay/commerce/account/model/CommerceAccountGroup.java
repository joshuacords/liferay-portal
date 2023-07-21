/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceAccountGroup service. Represents a row in the &quot;CommerceAccountGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.account.model.impl.CommerceAccountGroupImpl"
)
@ProviderType
public interface CommerceAccountGroup
	extends CommerceAccountGroupModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceAccountGroup, Long>
		COMMERCE_ACCOUNT_GROUP_ID_ACCESSOR =
			new Accessor<CommerceAccountGroup, Long>() {

				@Override
				public Long get(CommerceAccountGroup commerceAccountGroup) {
					return commerceAccountGroup.getCommerceAccountGroupId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceAccountGroup> getTypeClass() {
					return CommerceAccountGroup.class;
				}

			};

}