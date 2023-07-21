/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.messaging;

import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageStatus;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	property = {
		"destination.name=" + DestinationNames.LAYOUTS_REMOTE_PUBLISHER,
		"message.status.destination.name=" + DestinationNames.MESSAGE_BUS_MESSAGE_STATUS
	},
	service = LayoutsRemotePublisherMessageListener.class
)
public class LayoutsRemotePublisherMessageListener
	extends BasePublisherMessageListener {

	@Activate
	protected void activate(ComponentContext componentContext) {
		initialize(componentContext);
	}

	@Deactivate
	protected void deactivate() {
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	@Override
	protected void doReceive(Message message, MessageStatus messageStatus)
		throws PortalException {

		long exportImportConfigurationId = GetterUtil.getLong(
			message.getPayload());

		ExportImportConfiguration exportImportConfiguration =
			_exportImportConfigurationLocalService.
				fetchExportImportConfiguration(exportImportConfigurationId);

		if (exportImportConfiguration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to find export import configuration with ID " +
						exportImportConfigurationId);
			}

			return;
		}

		messageStatus.setPayload(exportImportConfiguration);

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		long userId = MapUtil.getLong(settingsMap, "userId");
		long sourceGroupId = MapUtil.getLong(settingsMap, "sourceGroupId");
		boolean privateLayout = MapUtil.getBoolean(
			settingsMap, "privateLayout");
		Map<Long, Boolean> layoutIdMap = (Map<Long, Boolean>)settingsMap.get(
			"layoutIdMap");
		Map<String, String[]> parameterMap =
			(Map<String, String[]>)settingsMap.get("parameterMap");
		String remoteAddress = MapUtil.getString(settingsMap, "remoteAddress");
		int remotePort = MapUtil.getInteger(settingsMap, "remotePort");
		String remotePathContext = MapUtil.getString(
			settingsMap, "remotePathContext");
		boolean secureConnection = MapUtil.getBoolean(
			settingsMap, "secureConnection");
		long targetGroupId = MapUtil.getLong(settingsMap, "targetGroupId");
		boolean remotePrivateLayout = MapUtil.getBoolean(
			settingsMap, "remotePrivateLayout");

		initThreadLocals(userId, parameterMap);

		User user = _userLocalService.getUserById(userId);

		CompanyThreadLocal.setCompanyId(user.getCompanyId());

		try {
			_staging.copyRemoteLayouts(
				sourceGroupId, privateLayout, layoutIdMap,
				exportImportConfiguration.getName(), parameterMap,
				remoteAddress, remotePort, remotePathContext, secureConnection,
				targetGroupId, remotePrivateLayout);
		}
		finally {
			resetThreadLocals();
		}
	}

	@Override
	protected Destination getDestination() {
		return _destination;
	}

	@Reference(
		target = "(destination.name=" + DestinationNames.LAYOUTS_REMOTE_PUBLISHER + ")",
		unbind = "-"
	)
	protected void setDestination(Destination destination) {
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.exportimport.service)(release.schema.version=1.0.1))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutsRemotePublisherMessageListener.class);

	@Reference(
		target = "(destination.name=" + DestinationNames.MESSAGE_BUS_MESSAGE_STATUS + ")"
	)
	private Destination _destination;

	@Reference
	private ExportImportConfigurationLocalService
		_exportImportConfigurationLocalService;

	@Reference
	private Staging _staging;

	@Reference
	private UserLocalService _userLocalService;

}