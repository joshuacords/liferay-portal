/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.search.FilterQuery;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface AccountWebService {

	public Account addAccount(
			String agentName, String agentUID, Account account)
		throws Exception;

	public void assignContactRolesByEmailAddress(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public void assignContactRolesByUuid(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void assignTeamRoles(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception;

	public Account fetchAccount(String accountKey) throws Exception;

	public Account getAccount(String accountKey) throws Exception;

	public List<Account> getAccounts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception;

	public long getContactAccountsCount(String contactUuid) throws Exception;

	public List<Account> search(
			String search, FilterQuery filterQuery, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, FilterQuery filterQuery)
		throws Exception;

	public void unassignContactRolesByEmailAddress(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public void unassignContactRolesByUuid(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void unassignCustomerContact(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress)
		throws Exception;

	public void unassignTeamRoles(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception;

	public void unassignWorkerContact(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress)
		throws Exception;

	public Account updateAccount(
			String agentName, String agentUID, String accountKey,
			Account account)
		throws Exception;

}