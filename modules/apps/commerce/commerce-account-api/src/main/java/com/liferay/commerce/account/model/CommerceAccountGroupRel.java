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
 * The extended model interface for the CommerceAccountGroupRel service. Represents a row in the &quot;CommerceAccountGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.account.model.impl.CommerceAccountGroupRelImpl"
)
@ProviderType
public interface CommerceAccountGroupRel
	extends CommerceAccountGroupRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.account.model.impl.CommerceAccountGroupRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceAccountGroupRel, Long>
		COMMERCE_ACCOUNT_GROUP_REL_ID_ACCESSOR =
			new Accessor<CommerceAccountGroupRel, Long>() {

				@Override
				public Long get(
					CommerceAccountGroupRel commerceAccountGroupRel) {

					return commerceAccountGroupRel.
						getCommerceAccountGroupRelId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceAccountGroupRel> getTypeClass() {
					return CommerceAccountGroupRel.class;
				}

			};

	public CommerceAccountGroup getCommerceAccountGroup()
		throws com.liferay.portal.kernel.exception.PortalException;

}