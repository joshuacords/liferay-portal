/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.web.internal.portlet;

import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.sync.constants.SyncPortletKeys;
import com.liferay.sync.model.SyncDevice;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-sync-devices",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=My Sync Devices",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/devices.jsp",
		"javax.portlet.name=" + SyncPortletKeys.SYNC_DEVICES_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SyncDevicesPortlet extends BaseSyncPortlet {

	@Override
	public void deleteDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		checkSyncDevice(syncDeviceId, themeDisplay.getUserId());

		super.deleteDevice(actionRequest, actionResponse);
	}

	@Override
	public void updateDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		checkSyncDevice(syncDeviceId, themeDisplay.getUserId());

		super.updateDevice(actionRequest, actionResponse);
	}

	protected void checkSyncDevice(long syncDeviceId, long userId)
		throws Exception {

		SyncDevice syncDevice = syncDeviceLocalService.getSyncDevice(
			syncDeviceId);

		if (userId != syncDevice.getUserId()) {
			throw new PrincipalException();
		}
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.sync.web)(&(release.schema.version>=1.0.0)(!(release.schema.version>=2.0.0))))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

}