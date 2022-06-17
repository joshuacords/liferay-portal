/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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