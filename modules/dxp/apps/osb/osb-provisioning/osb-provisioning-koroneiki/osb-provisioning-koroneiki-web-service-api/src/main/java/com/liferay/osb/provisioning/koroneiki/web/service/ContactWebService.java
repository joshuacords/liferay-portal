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

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.search.FilterQuery;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface ContactWebService {

	public Contact addContact(
			String agentName, String agentUID, Contact contact)
		throws Exception;

	public void deleteContact(
			String agentName, String agentUID, String emailAddress)
		throws Exception;

	public Contact fetchContactByEmailAddress(String emailAddress)
		throws Exception;

	public Contact fetchContactByUuid(String uuid) throws Exception;

	public Contact getContactByEmailAddress(String emailAddress)
		throws Exception;

	public Contact getContactByUuid(String uuid) throws Exception;

	public List<Contact> getTeamContacts(String teamKey, int page, int pageSize)
		throws Exception;

	public List<Contact> search(
			String search, FilterQuery filterQuery, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, FilterQuery filterQuery)
		throws Exception;

	public Contact updateContactByEmailAddress(
			String agentName, String agentUID, String emailAddress,
			Contact contact)
		throws Exception;

	public Contact updateContactByUuid(
			String agentName, String agentUID, String uuid, Contact contact)
		throws Exception;

}