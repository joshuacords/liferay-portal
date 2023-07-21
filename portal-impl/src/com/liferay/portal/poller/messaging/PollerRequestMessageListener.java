/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.poller.PollerException;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.poller.PollerProcessorUtil;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class PollerRequestMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		PollerRequest pollerRequest = (PollerRequest)message.getPayload();

		PollerProcessor pollerProcessor =
			PollerProcessorUtil.getPollerProcessor(
				pollerRequest.getPortletId());

		if (pollerRequest.isReceiveRequest()) {
			PollerResponse pollerResponse = null;

			try {
				pollerResponse = pollerProcessor.receive(pollerRequest);
			}
			catch (PollerException pollerException) {
				_log.error(
					"Unable to receive poller request " + pollerRequest,
					pollerException);

				pollerResponse = pollerRequest.createPollerResponse();

				pollerResponse.setParameter(
					"pollerException", pollerException.getMessage());
			}
			finally {
				if (pollerResponse == null) {
					pollerResponse = pollerRequest.createPollerResponse();
				}

				pollerResponse.close(
					message, pollerRequest.getPollerHeader(),
					pollerRequest.getPortletId(), pollerRequest.getChunkId());
			}
		}
		else {
			try {
				pollerProcessor.send(pollerRequest);
			}
			catch (PollerException pollerException) {
				_log.error(
					"Unable to send poller request " + pollerRequest,
					pollerException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PollerRequestMessageListener.class);

}