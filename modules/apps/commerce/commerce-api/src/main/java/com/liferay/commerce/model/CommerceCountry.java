/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceCountry service. Represents a row in the &quot;CommerceCountry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceCountryModel
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.model.impl.CommerceCountryImpl")
@ProviderType
public interface CommerceCountry extends CommerceCountryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.model.impl.CommerceCountryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceCountry, Long>
		COMMERCE_COUNTRY_ID_ACCESSOR = new Accessor<CommerceCountry, Long>() {

			@Override
			public Long get(CommerceCountry commerceCountry) {
				return commerceCountry.getCommerceCountryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceCountry> getTypeClass() {
				return CommerceCountry.class;
			}

		};

	public java.util.List<CommerceRegion> getCommerceRegions();

}