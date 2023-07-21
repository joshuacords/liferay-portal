/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.search.FilterQuery;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface TeamWebService {

	public Team addTeam(
			String agentName, String agentUID, String accountKey, Team team)
		throws Exception;

	public void assignContactsByEmailAddress(
			String agentName, String agentUID, String teamKey,
			String[] contactEmailAddresses)
		throws Exception;

	public void assignContactsByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public void deleteTeam(String agentName, String agentUID, String teamKey)
		throws Exception;

	public Team getTeam(String teamKey) throws Exception;

	public List<Team> getTeams(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception;

	public List<Team> search(
			String search, FilterQuery filterQuery, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, FilterQuery filterQuery)
		throws Exception;

	public void unassignContactsByEmailAddress(
			String agentName, String agentUID, String teamKey,
			String[] contactEmailAddresses)
		throws Exception;

	public void unassignContactsByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public Team updateTeam(
			String agentName, String agentUID, String teamKey, Team team)
		throws Exception;

}