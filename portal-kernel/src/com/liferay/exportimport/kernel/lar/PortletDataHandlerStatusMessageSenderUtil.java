/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.lar;

import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Michael C. Han
 */
public class PortletDataHandlerStatusMessageSenderUtil {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #_getPortletDataHandlerStatusMessageSender()}
	 */
	@Deprecated
	public static PortletDataHandlerStatusMessageSender
		getPortletDataHandlerStatusMessageSender() {

		return _getPortletDataHandlerStatusMessageSender();
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #sendStatusMessage(String, String[], ManifestSummary)}
	 */
	@Deprecated
	public static void sendStatusMessage(
		String messageType, ManifestSummary manifestSummary) {

		_getPortletDataHandlerStatusMessageSender().sendStatusMessage(
			messageType, manifestSummary);
	}

	public static void sendStatusMessage(
		String messageType, String portletId, ManifestSummary manifestSummary) {

		_getPortletDataHandlerStatusMessageSender().sendStatusMessage(
			messageType, portletId, manifestSummary);
	}

	public static void sendStatusMessage(
		String messageType, String[] portletIds,
		ManifestSummary manifestSummary) {

		_getPortletDataHandlerStatusMessageSender().sendStatusMessage(
			messageType, portletIds, manifestSummary);
	}

	public static <T extends StagedModel> void sendStatusMessage(
		String messageType, T stagedModel, ManifestSummary manifestSummary) {

		_getPortletDataHandlerStatusMessageSender().sendStatusMessage(
			messageType, stagedModel, manifestSummary);
	}

	private static PortletDataHandlerStatusMessageSender
		_getPortletDataHandlerStatusMessageSender() {

		return _dataHandlerStatusMessageSender;
	}

	private static volatile PortletDataHandlerStatusMessageSender
		_dataHandlerStatusMessageSender =
			ServiceProxyFactory.newServiceTrackedInstance(
				PortletDataHandlerStatusMessageSender.class,
				PortletDataHandlerStatusMessageSenderUtil.class,
				"_dataHandlerStatusMessageSender", false);

}