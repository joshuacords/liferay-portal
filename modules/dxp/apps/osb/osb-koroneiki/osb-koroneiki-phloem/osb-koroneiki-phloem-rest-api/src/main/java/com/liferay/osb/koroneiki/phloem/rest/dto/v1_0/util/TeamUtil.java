/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Amos Fong
 */
public class TeamUtil {

	public static Team toTeam(com.liferay.osb.koroneiki.taproot.model.Team team)
		throws Exception {

		return new Team() {
			{
				accountKey = team.getAccountKey();
				dateCreated = team.getCreateDate();
				dateModified = team.getModifiedDate();
				externalLinks = TransformUtil.transformToArray(
					team.getExternalLinks(), ExternalLinkUtil::toExternalLink,
					ExternalLink.class);
				key = team.getTeamKey();
				name = team.getName();
				system = team.getSystem();
			}
		};
	}

}