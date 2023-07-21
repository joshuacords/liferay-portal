/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.identity.management.provider;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;

import java.util.List;

/**
 * @author Yuanyuan Huang
 */
public interface ContactIdentityProvider {

	public void addMembership(String groupId, String emailAddress)
		throws Exception;

	public Contact createContact(
			String emailAddress, String firstName, String middleName,
			String lastName)
		throws Exception;

	public Contact fetchContactByEmailAddress(String emailAddress, boolean sync)
		throws Exception;

	public Contact fetchContactBySessionId(String sessionId) throws Exception;

	public Contact fetchContactByUuid(String uuid) throws Exception;

	public Integer fetchContactStatusByEmailAddress(String emailAddress)
		throws Exception;

	public List<Contact> getGroupContacts(String groupId) throws Exception;

	public void removeMembership(String groupId, String emailAddress)
		throws Exception;

	public Contact syncContact(Contact contact) throws Exception;

}