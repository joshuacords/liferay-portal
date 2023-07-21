/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.search.FilterQuery;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface ContactRoleWebService {

	public ContactRole addContactRole(
			String agentName, String agentUID, ContactRole contactRole)
		throws Exception;

	public ContactRole fetchContactRole(String type, String name)
		throws Exception;

	public List<ContactRole> getAccountContactRoles(
			String accountKey, String emailAddress, int page, int pageSize)
		throws Exception;

	public List<ContactRole> getAccountCustomerContactRoles(
			String accountKey, String emailAddress, int page, int pageSize)
		throws Exception;

	public List<ContactRole> getAccountWorkerContactRoles(
			String accountKey, String emailAddress, int page, int pageSize)
		throws Exception;

	public ContactRole getContactRole(String contactRoleKey) throws Exception;

	public ContactRole getContactRole(String type, String name)
		throws Exception;

	public List<ContactRole> search(
			FilterQuery filterQuery, int page, int pageSize, String sortString)
		throws Exception;

}