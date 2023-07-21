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
 * The extended model interface for the ContactRole service. Represents a row in the &quot;Koroneiki_ContactRole&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRoleModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.taproot.model.impl.ContactRoleImpl"
)
@ProviderType
public interface ContactRole extends ContactRoleModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactRoleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ContactRole, Long> CONTACT_ROLE_ID_ACCESSOR =
		new Accessor<ContactRole, Long>() {

			@Override
			public Long get(ContactRole contactRole) {
				return contactRole.getContactRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ContactRole> getTypeClass() {
				return ContactRole.class;
			}

		};
	public static final Accessor<ContactRole, String> NAME_ACCESSOR =
		new Accessor<ContactRole, String>() {

			@Override
			public String get(ContactRole contactRole) {
				return contactRole.getName();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ContactRole> getTypeClass() {
				return ContactRole.class;
			}

		};

	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks();

	public boolean isMember();

}