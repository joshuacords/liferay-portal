/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.portal.kernel.search.BaseSearcher;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.TeamRole",
	service = BaseSearcher.class
)
public class TeamRoleSearcher extends BaseSearcher {

	public static final String CLASS_NAME = TeamRole.class.getName();

	public TeamRoleSearcher() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

}