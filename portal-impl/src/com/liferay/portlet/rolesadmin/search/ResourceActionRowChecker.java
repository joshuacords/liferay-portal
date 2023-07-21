/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.rolesadmin.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;

import javax.portlet.PortletResponse;

/**
 * @author Jorge Ferrer
 * @author Connor McKay
 */
public class ResourceActionRowChecker extends RowChecker {

	public ResourceActionRowChecker(PortletResponse portletResponse) {
		super(portletResponse);
	}

	@Override
	public boolean isChecked(Object obj) {
		try {
			return doIsChecked(obj);
		}
		catch (Exception exception) {
			return false;
		}
	}

	protected boolean doIsChecked(Object obj) throws Exception {
		Object[] objArray = (Object[])obj;

		Role role = (Role)objArray[0];
		String actionId = (String)objArray[1];
		String resourceName = (String)objArray[2];
		Integer scope = (Integer)objArray[4];

		return ResourcePermissionLocalServiceUtil.hasScopeResourcePermission(
			role.getCompanyId(), resourceName, scope, role.getRoleId(),
			actionId);
	}

}