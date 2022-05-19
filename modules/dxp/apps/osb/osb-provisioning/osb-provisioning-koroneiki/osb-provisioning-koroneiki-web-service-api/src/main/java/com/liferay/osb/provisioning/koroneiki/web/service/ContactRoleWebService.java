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