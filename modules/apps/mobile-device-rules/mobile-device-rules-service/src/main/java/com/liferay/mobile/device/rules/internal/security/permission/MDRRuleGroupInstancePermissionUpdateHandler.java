/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal.security.permission;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService;
import com.liferay.portal.kernel.security.permission.PermissionUpdateHandler;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gergely Mathe
 */
@Component(
	property = "model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroupInstance",
	service = PermissionUpdateHandler.class
)
public class MDRRuleGroupInstancePermissionUpdateHandler
	implements PermissionUpdateHandler {

	@Override
	public void updatedPermission(String primKey) {
		MDRRuleGroupInstance mdrRuleGroupInstance =
			_mdrRuleGroupInstanceLocalService.fetchMDRRuleGroupInstance(
				GetterUtil.getLong(primKey));

		if (mdrRuleGroupInstance == null) {
			return;
		}

		mdrRuleGroupInstance.setModifiedDate(new Date());

		_mdrRuleGroupInstanceLocalService.updateMDRRuleGroupInstance(
			mdrRuleGroupInstance);
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupInstanceLocalService(
		MDRRuleGroupInstanceLocalService mdrRuleGroupInstanceLocalService) {

		_mdrRuleGroupInstanceLocalService = mdrRuleGroupInstanceLocalService;
	}

	private MDRRuleGroupInstanceLocalService _mdrRuleGroupInstanceLocalService;

}