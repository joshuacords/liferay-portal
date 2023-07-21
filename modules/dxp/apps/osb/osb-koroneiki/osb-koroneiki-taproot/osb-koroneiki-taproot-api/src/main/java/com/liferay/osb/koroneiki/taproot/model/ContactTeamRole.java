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
 * The extended model interface for the ContactTeamRole service. Represents a row in the &quot;Koroneiki_ContactTeamRole&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.taproot.model.impl.ContactTeamRoleImpl"
)
@ProviderType
public interface ContactTeamRole extends ContactTeamRoleModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactTeamRoleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ContactTeamRole, Long> CONTACT_ID_ACCESSOR =
		new Accessor<ContactTeamRole, Long>() {

			@Override
			public Long get(ContactTeamRole contactTeamRole) {
				return contactTeamRole.getContactId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ContactTeamRole> getTypeClass() {
				return ContactTeamRole.class;
			}

		};
	public static final Accessor<ContactTeamRole, Long> TEAM_ID_ACCESSOR =
		new Accessor<ContactTeamRole, Long>() {

			@Override
			public Long get(ContactTeamRole contactTeamRole) {
				return contactTeamRole.getTeamId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ContactTeamRole> getTypeClass() {
				return ContactTeamRole.class;
			}

		};
	public static final Accessor<ContactTeamRole, Long>
		CONTACT_ROLE_ID_ACCESSOR = new Accessor<ContactTeamRole, Long>() {

			@Override
			public Long get(ContactTeamRole contactTeamRole) {
				return contactTeamRole.getContactRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ContactTeamRole> getTypeClass() {
				return ContactTeamRole.class;
			}

		};

	public Contact getContact()
		throws com.liferay.portal.kernel.exception.PortalException;

	public ContactRole getContactRole()
		throws com.liferay.portal.kernel.exception.PortalException;

	public Team getTeam()
		throws com.liferay.portal.kernel.exception.PortalException;

}