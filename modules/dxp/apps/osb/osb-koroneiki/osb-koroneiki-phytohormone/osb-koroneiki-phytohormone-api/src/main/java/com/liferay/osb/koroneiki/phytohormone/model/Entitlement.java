/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Entitlement service. Represents a row in the &quot;Koroneiki_Entitlement&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementImpl"
)
@ProviderType
public interface Entitlement extends EntitlementModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Entitlement, Long> ENTITLEMENT_ID_ACCESSOR =
		new Accessor<Entitlement, Long>() {

			@Override
			public Long get(Entitlement entitlement) {
				return entitlement.getEntitlementId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Entitlement> getTypeClass() {
				return Entitlement.class;
			}

		};

	public EntitlementDefinition getEntitlementDefinition()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getEntitlementDefinitionKey()
		throws com.liferay.portal.kernel.exception.PortalException;

	public void setEntitlementDefinitionKey(String entitlementDefinitionKey);

}