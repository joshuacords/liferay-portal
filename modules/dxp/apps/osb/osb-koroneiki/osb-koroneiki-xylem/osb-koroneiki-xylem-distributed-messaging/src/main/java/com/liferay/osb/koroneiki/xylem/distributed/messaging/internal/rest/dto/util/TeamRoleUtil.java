/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;

/**
 * @author Kyle Bischof
 */
public class TeamRoleUtil {

	public static TeamRole toTeamRole(
			com.liferay.osb.koroneiki.taproot.model.TeamRole teamRole)
		throws Exception {

		return new TeamRole() {
			{
				dateCreated = teamRole.getCreateDate();
				dateModified = teamRole.getModifiedDate();
				description = teamRole.getDescription();
				key = teamRole.getTeamRoleKey();
				name = teamRole.getName();
				type = Type.create(teamRole.getType());
			}
		};
	}

}