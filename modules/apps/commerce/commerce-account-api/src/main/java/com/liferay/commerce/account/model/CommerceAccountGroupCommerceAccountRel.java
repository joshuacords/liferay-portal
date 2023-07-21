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
 * The extended model interface for the CommerceAccountGroupCommerceAccountRel service. Represents a row in the &quot;CAccountGroupCAccountRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupCommerceAccountRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.account.model.impl.CommerceAccountGroupCommerceAccountRelImpl"
)
@ProviderType
public interface CommerceAccountGroupCommerceAccountRel
	extends CommerceAccountGroupCommerceAccountRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupCommerceAccountRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceAccountGroupCommerceAccountRel, Long>
		COMMERCE_ACCOUNT_GROUP_COMMERCE_ACCOUNT_REL_ID_ACCESSOR =
			new Accessor<CommerceAccountGroupCommerceAccountRel, Long>() {

				@Override
				public Long get(
					CommerceAccountGroupCommerceAccountRel
						commerceAccountGroupCommerceAccountRel) {

					return commerceAccountGroupCommerceAccountRel.
						getCommerceAccountGroupCommerceAccountRelId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceAccountGroupCommerceAccountRel>
					getTypeClass() {

					return CommerceAccountGroupCommerceAccountRel.class;
				}

			};

	public CommerceAccount getCommerceAccount()
		throws com.liferay.portal.kernel.exception.PortalException;

}