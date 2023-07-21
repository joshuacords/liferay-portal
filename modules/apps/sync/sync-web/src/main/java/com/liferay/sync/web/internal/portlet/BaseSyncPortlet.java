/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.sync.service.SyncDeviceLocalService;
import com.liferay.sync.web.internal.upgrade.SyncWebUpgrade;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
public abstract class BaseSyncPortlet extends MVCPortlet {

	public void deleteDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		syncDeviceLocalService.deleteSyncDevice(syncDeviceId);
	}

	public void updateDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		int status = ParamUtil.getInteger(actionRequest, "status");

		syncDeviceLocalService.updateStatus(syncDeviceId, status);
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_portal = portal;
	}

	@Reference(unbind = "-")
	protected void setSyncDeviceLocalService(
		SyncDeviceLocalService syncDeviceLocalService) {

		this.syncDeviceLocalService = syncDeviceLocalService;
	}

	@Reference(unbind = "-")
	protected void setSyncWebUpgrade(SyncWebUpgrade syncWebUpgrade) {
	}

	protected SyncDeviceLocalService syncDeviceLocalService;

	private Portal _portal;

}