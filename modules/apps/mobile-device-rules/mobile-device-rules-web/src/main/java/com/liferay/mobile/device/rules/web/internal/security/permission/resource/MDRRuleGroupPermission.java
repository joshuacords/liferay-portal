/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.security.permission.resource;

import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class MDRRuleGroupPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException {

		return _mdrRuleGroupModelResourcePermission.contains(
			permissionChecker, ruleGroupId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
			String actionId)
		throws PortalException {

		return _mdrRuleGroupModelResourcePermission.contains(
			permissionChecker, ruleGroup, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroup)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<MDRRuleGroup> modelResourcePermission) {

		_mdrRuleGroupModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<MDRRuleGroup>
		_mdrRuleGroupModelResourcePermission;

}