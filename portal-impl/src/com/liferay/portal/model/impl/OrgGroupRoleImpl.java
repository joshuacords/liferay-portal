/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class OrgGroupRoleImpl extends OrgGroupRoleBaseImpl {

	@Override
	public boolean containsGroup(List<Group> groups) {
		if (groups == null) {
			return false;
		}

		for (Group group : groups) {
			if (group.getGroupId() == getGroupId()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsOrganization(List<Organization> organizations) {
		if (organizations == null) {
			return false;
		}

		for (Organization organization : organizations) {
			if (organization.getOrganizationId() == getOrganizationId()) {
				return true;
			}
		}

		return false;
	}

}