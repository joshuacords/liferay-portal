/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.web.internal.background.task;

import com.liferay.adaptive.media.constants.AMOptimizeImagesBackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageSender;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.messaging.Message;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = {})
public class OptimizeImagesStatusMessageSenderUtil {

	public static void sendStatusMessage(
		String phase, long companyId, String configurationEntryUuid) {

		_optimizeImagesStatusMessageSenderUtil._sendStatusMessage(
			phase, companyId, configurationEntryUuid);
	}

	@Activate
	protected void activate() {
		_optimizeImagesStatusMessageSenderUtil = this;
	}

	private void _sendStatusMessage(
		String phase, long companyId, String configurationEntryUuid) {

		Message message = new Message();

		message.put(
			BackgroundTaskConstants.BACKGROUND_TASK_ID,
			BackgroundTaskThreadLocal.getBackgroundTaskId());
		message.put(
			AMOptimizeImagesBackgroundTaskConstants.COMPANY_ID, companyId);
		message.put(
			AMOptimizeImagesBackgroundTaskConstants.CONFIGURATION_ENTRY_UUID,
			configurationEntryUuid);
		message.put(AMOptimizeImagesBackgroundTaskConstants.PHASE, phase);
		message.put("status", BackgroundTaskConstants.STATUS_IN_PROGRESS);

		_backgroundTaskStatusMessageSender.sendBackgroundTaskStatusMessage(
			message);
	}

	private static OptimizeImagesStatusMessageSenderUtil
		_optimizeImagesStatusMessageSenderUtil;

	@Reference
	private BackgroundTaskStatusMessageSender
		_backgroundTaskStatusMessageSender;

}