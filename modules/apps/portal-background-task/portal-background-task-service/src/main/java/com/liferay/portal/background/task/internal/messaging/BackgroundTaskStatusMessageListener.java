/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.internal.messaging;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskStatusMessageListener extends BaseMessageListener {

	public BackgroundTaskStatusMessageListener(
		long backgroundTaskId,
		BackgroundTaskStatusMessageTranslator
			backgroundTaskStatusMessageTranslator,
		BackgroundTaskStatusRegistry backgroundTaskStatusRegistry) {

		_backgroundTaskId = backgroundTaskId;
		_backgroundTaskStatusMessageTranslator =
			backgroundTaskStatusMessageTranslator;
		_backgroundTaskStatusRegistry = backgroundTaskStatusRegistry;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long backgroundTaskId = message.getLong(
			BackgroundTaskConstants.BACKGROUND_TASK_ID);

		if (backgroundTaskId != _backgroundTaskId) {
			return;
		}

		BackgroundTaskStatus backgroundTaskStatus =
			_backgroundTaskStatusRegistry.getBackgroundTaskStatus(
				backgroundTaskId);

		if (backgroundTaskStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Unable to locate status for background task ",
						backgroundTaskId, " to process ", message));
			}

			return;
		}

		_backgroundTaskStatusMessageTranslator.translate(
			backgroundTaskStatus, message);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BackgroundTaskStatusMessageListener.class);

	private final long _backgroundTaskId;
	private final BackgroundTaskStatusMessageTranslator
		_backgroundTaskStatusMessageTranslator;
	private final BackgroundTaskStatusRegistry _backgroundTaskStatusRegistry;

}