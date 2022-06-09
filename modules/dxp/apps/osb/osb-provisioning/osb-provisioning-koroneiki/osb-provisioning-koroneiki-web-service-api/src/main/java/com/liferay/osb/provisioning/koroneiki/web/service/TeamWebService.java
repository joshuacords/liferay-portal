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

	public void assignContacts(
			String agentName, String agentUID, String teamKey,
			String[] contactEmailAddresses)
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

	public void unassignContacts(
			String agentName, String agentUID, String teamKey,
			String[] contactEmailAddresses)
		throws Exception;

	public Team updateTeam(
			String agentName, String agentUID, String teamKey, Team team)
		throws Exception;

}