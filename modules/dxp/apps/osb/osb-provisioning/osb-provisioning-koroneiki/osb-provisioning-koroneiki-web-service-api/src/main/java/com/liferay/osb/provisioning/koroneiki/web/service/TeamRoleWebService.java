/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface TeamRoleWebService {

	public TeamRole getTeamRole(String type, String name) throws Exception;

	public List<TeamRole> getTeamRoles(
			String accountKey, String teamKey, int page, int pageSize)
		throws Exception;

}