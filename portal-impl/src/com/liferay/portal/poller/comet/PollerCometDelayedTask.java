/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller.comet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.UnknownChannelException;
import com.liferay.portal.kernel.poller.comet.CometRequest;
import com.liferay.portal.kernel.poller.comet.CometResponse;
import com.liferay.portal.kernel.poller.comet.CometSession;

import java.util.List;

/**
 * @author Edward Han
 */
public class PollerCometDelayedTask {

	public PollerCometDelayedTask(
		CometSession cometSession, JSONObject pollerResponseHeaderJSONObject) {

		_cometSession = cometSession;
		_pollerResponseHeaderJSONObject = pollerResponseHeaderJSONObject;
	}

	public void executeTask() throws Exception {
		CometRequest cometRequest = _cometSession.getCometRequest();

		try {
			List<NotificationEvent> notificationEvents =
				ChannelHubManagerUtil.getNotificationEvents(
					cometRequest.getCompanyId(), cometRequest.getUserId(),
					false);

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			if (_pollerResponseHeaderJSONObject != null) {
				jsonArray.put(_pollerResponseHeaderJSONObject);
			}

			for (NotificationEvent notificationEvent : notificationEvents) {
				jsonArray.put(notificationEvent.toJSONObject());
			}

			CometResponse cometResponse = _cometSession.getCometResponse();

			cometResponse.writeData(jsonArray.toString());

			ChannelHubManagerUtil.removeTransientNotificationEvents(
				cometRequest.getCompanyId(), cometRequest.getUserId(),
				notificationEvents);
		}
		catch (UnknownChannelException unknownChannelException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to complete processing because user session ended",
					unknownChannelException);
			}
		}
		finally {
			_cometSession.close();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PollerCometDelayedTask.class);

	private final CometSession _cometSession;
	private final JSONObject _pollerResponseHeaderJSONObject;

}