/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.internal.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.reports.engine.ReportGenerationException;
import com.liferay.portal.reports.engine.ReportResultContainer;
import com.liferay.portal.reports.engine.console.service.EntryLocalService;
import com.liferay.portal.reports.engine.console.status.ReportStatus;

/**
 * @author Gavin Wan
 */
public class AdminMessageListener extends BaseMessageListener {

	public AdminMessageListener(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		ReportResultContainer reportResultContainer =
			(ReportResultContainer)message.getPayload();

		long entryId = GetterUtil.getLong(message.getResponseId());

		if (reportResultContainer.hasError()) {
			ReportGenerationException reportGenerationException =
				reportResultContainer.getReportGenerationException();

			_entryLocalService.updateEntryStatus(
				entryId, ReportStatus.ERROR,
				reportGenerationException.getMessage());
		}
		else {
			_entryLocalService.updateEntry(
				entryId, reportResultContainer.getReportName(),
				reportResultContainer.getResults());
		}
	}

	private final EntryLocalService _entryLocalService;

}