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
 * The extended model interface for the CommerceAccountUserRel service. Represents a row in the &quot;CommerceAccountUserRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CommerceAccountUserRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.account.model.impl.CommerceAccountUserRelImpl"
)
@ProviderType
public interface CommerceAccountUserRel
	extends CommerceAccountUserRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.account.model.impl.CommerceAccountUserRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceAccountUserRel, Long>
		COMMERCE_ACCOUNT_ID_ACCESSOR =
			new Accessor<CommerceAccountUserRel, Long>() {

				@Override
				public Long get(CommerceAccountUserRel commerceAccountUserRel) {
					return commerceAccountUserRel.getCommerceAccountId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceAccountUserRel> getTypeClass() {
					return CommerceAccountUserRel.class;
				}

			};
	public static final Accessor<CommerceAccountUserRel, Long>
		COMMERCE_ACCOUNT_USER_ID_ACCESSOR =
			new Accessor<CommerceAccountUserRel, Long>() {

				@Override
				public Long get(CommerceAccountUserRel commerceAccountUserRel) {
					return commerceAccountUserRel.getCommerceAccountUserId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceAccountUserRel> getTypeClass() {
					return CommerceAccountUserRel.class;
				}

			};

	public com.liferay.portal.kernel.model.User getUser()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<com.liferay.portal.kernel.model.UserGroupRole>
			getUserGroupRoles()
		throws com.liferay.portal.kernel.exception.PortalException;

}