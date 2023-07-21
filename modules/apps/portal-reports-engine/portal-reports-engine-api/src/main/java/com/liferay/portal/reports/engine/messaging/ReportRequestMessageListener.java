/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.reports.engine.ReportDesignRetriever;
import com.liferay.portal.reports.engine.ReportEngine;
import com.liferay.portal.reports.engine.ReportGenerationException;
import com.liferay.portal.reports.engine.ReportRequest;
import com.liferay.portal.reports.engine.ReportResultContainer;

/**
 * @author Michael C. Han
 */
public class ReportRequestMessageListener extends BaseMessageListener {

	public ReportRequestMessageListener() {
	}

	public ReportRequestMessageListener(
		ReportEngine reportEngine,
		ReportResultContainer reportResultContainer) {

		_reportEngine = reportEngine;
		_reportResultContainer = reportResultContainer;
	}

	public void setReportEngine(ReportEngine reportEngine) {
		_reportEngine = reportEngine;
	}

	public void setReportResultContainer(
		ReportResultContainer reportResultContainer) {

		_reportResultContainer = reportResultContainer;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		ReportRequest reportRequest = (ReportRequest)message.getPayload();

		ReportDesignRetriever reportDesignRetriever =
			reportRequest.getReportDesignRetriever();

		ReportResultContainer reportResultContainer =
			_reportResultContainer.clone(reportDesignRetriever.getReportName());

		try {
			_reportEngine.execute(reportRequest, reportResultContainer);
		}
		catch (ReportGenerationException reportGenerationException) {
			_log.error("Unable to generate report", reportGenerationException);

			reportResultContainer.setReportGenerationException(
				reportGenerationException);
		}
		finally {
			Message responseMessage = MessageBusUtil.createResponseMessage(
				message);

			responseMessage.setPayload(reportResultContainer);

			MessageBusUtil.sendMessage(
				responseMessage.getDestinationName(), responseMessage);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReportRequestMessageListener.class);

	private ReportEngine _reportEngine;
	private ReportResultContainer _reportResultContainer;

}