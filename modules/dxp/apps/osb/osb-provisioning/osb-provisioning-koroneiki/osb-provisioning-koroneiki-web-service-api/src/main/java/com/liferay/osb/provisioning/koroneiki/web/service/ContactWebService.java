/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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