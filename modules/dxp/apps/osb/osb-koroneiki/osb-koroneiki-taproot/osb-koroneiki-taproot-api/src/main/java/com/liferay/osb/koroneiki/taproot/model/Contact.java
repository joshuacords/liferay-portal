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
 * The extended model interface for the Contact service. Represents a row in the &quot;Koroneiki_Contact&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ContactModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.taproot.model.impl.ContactImpl"
)
@ProviderType
public interface Contact extends ContactModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Contact, Long> CONTACT_ID_ACCESSOR =
		new Accessor<Contact, Long>() {

			@Override
			public Long get(Contact contact) {
				return contact.getContactId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Contact> getTypeClass() {
				return Contact.class;
			}

		};

	public java.util.List<ContactRole> getContactRoles(
		long accountId, String contactRoleType);

	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements();

	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks();

	public String getFullName();

	public java.util.List<Team> getTeams()
		throws com.liferay.portal.kernel.exception.PortalException;

}