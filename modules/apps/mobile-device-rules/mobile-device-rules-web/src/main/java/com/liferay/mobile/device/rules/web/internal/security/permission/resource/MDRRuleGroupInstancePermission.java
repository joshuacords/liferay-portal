/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.security.permission.resource;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class MDRRuleGroupInstancePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long ruleGroupInstanceId,
			String actionId)
		throws PortalException {

		return _mdrRuleGroupInstanceModelResourcePermission.contains(
			permissionChecker, ruleGroupInstanceId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			MDRRuleGroupInstance ruleGroupInstance, String actionId)
		throws PortalException {

		return _mdrRuleGroupInstanceModelResourcePermission.contains(
			permissionChecker, ruleGroupInstance, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroupInstance)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<MDRRuleGroupInstance> modelResourcePermission) {

		_mdrRuleGroupInstanceModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<MDRRuleGroupInstance>
		_mdrRuleGroupInstanceModelResourcePermission;

}