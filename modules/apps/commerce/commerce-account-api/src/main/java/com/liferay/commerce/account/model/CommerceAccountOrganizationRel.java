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
 * The extended model interface for the CommerceAccountOrganizationRel service. Represents a row in the &quot;CommerceAccountOrganizationRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CommerceAccountOrganizationRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelImpl"
)
@ProviderType
public interface CommerceAccountOrganizationRel
	extends CommerceAccountOrganizationRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceAccountOrganizationRel, Long>
		COMMERCE_ACCOUNT_ID_ACCESSOR =
			new Accessor<CommerceAccountOrganizationRel, Long>() {

				@Override
				public Long get(
					CommerceAccountOrganizationRel
						commerceAccountOrganizationRel) {

					return commerceAccountOrganizationRel.
						getCommerceAccountId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceAccountOrganizationRel> getTypeClass() {
					return CommerceAccountOrganizationRel.class;
				}

			};
	public static final Accessor<CommerceAccountOrganizationRel, Long>
		ORGANIZATION_ID_ACCESSOR =
			new Accessor<CommerceAccountOrganizationRel, Long>() {

				@Override
				public Long get(
					CommerceAccountOrganizationRel
						commerceAccountOrganizationRel) {

					return commerceAccountOrganizationRel.getOrganizationId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceAccountOrganizationRel> getTypeClass() {
					return CommerceAccountOrganizationRel.class;
				}

			};

	public com.liferay.portal.kernel.model.Organization getOrganization()
		throws com.liferay.portal.kernel.exception.PortalException;

}