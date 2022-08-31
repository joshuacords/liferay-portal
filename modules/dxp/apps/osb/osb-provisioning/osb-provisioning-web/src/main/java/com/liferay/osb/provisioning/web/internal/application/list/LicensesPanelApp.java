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

package com.liferay.osb.provisioning.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.RoleConstants;
import com.liferay.osb.provisioning.web.internal.application.list.constants.ProvisioningPanelCategoryKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=20",
		"panel.category.key=" + ProvisioningPanelCategoryKeys.CONTROL_PANEL_PROVISIONING
	},
	service = PanelApp.class
)
public class LicensesPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return ProvisioningPortletKeys.LICENSES;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_ACCOUNT_WORKER, false) ||
			_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_ADMIN, false) ||
			_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_CONTACT_WORKER, false) ||
			_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_WATCHER, false) ||
			_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_WORKER, false)) {

			return true;
		}

		return false;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + ProvisioningPortletKeys.LICENSES + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

	@Reference
	private RoleLocalService _roleLocalService;

}