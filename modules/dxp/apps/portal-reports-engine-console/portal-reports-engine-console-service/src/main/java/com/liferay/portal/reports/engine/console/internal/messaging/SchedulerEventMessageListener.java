/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.internal.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.reports.engine.console.service.EntryLocalService;

/**
 * @author Gavin Wan
 */
public class SchedulerEventMessageListener extends BaseMessageListener {

	public SchedulerEventMessageListener(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long entryId = message.getLong("entryId");
		String reportName = message.getString("reportName");

		_entryLocalService.generateReport(entryId, reportName);
	}

	private final EntryLocalService _entryLocalService;

}