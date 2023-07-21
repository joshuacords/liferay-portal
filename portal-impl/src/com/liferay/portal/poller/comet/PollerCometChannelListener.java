/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller.comet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.ChannelListener;
import com.liferay.portal.kernel.notifications.UnknownChannelException;
import com.liferay.portal.kernel.poller.comet.CometRequest;
import com.liferay.portal.kernel.poller.comet.CometSession;

/**
 * @author Edward Han
 */
public class PollerCometChannelListener implements ChannelListener {

	public PollerCometChannelListener(
		CometSession cometSession, JSONObject pollerResponseHeaderJSONObject) {

		_cometSession = cometSession;
		_pollerResponseHeaderJSONObject = pollerResponseHeaderJSONObject;
	}

	@Override
	public void channelListenerRemoved(long channelId) {
	}

	@Override
	public void notificationEventsAvailable(long channelId) {
		sendProcessMessage();
	}

	protected void sendProcessMessage() {
		CometRequest cometRequest = _cometSession.getCometRequest();

		try {
			ChannelHubManagerUtil.unregisterChannelListener(
				cometRequest.getCompanyId(), cometRequest.getUserId(), this);
		}
		catch (UnknownChannelException unknownChannelException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(unknownChannelException, unknownChannelException);
			}
		}
		catch (ChannelException channelException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to unregister channel listener", channelException);
			}
		}

		PollerCometDelayedTask pollerCometDelayedTask =
			new PollerCometDelayedTask(
				_cometSession, _pollerResponseHeaderJSONObject);

		PollerCometDelayedJobUtil.addPollerCometDelayedTask(
			pollerCometDelayedTask);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PollerCometChannelListener.class);

	private final CometSession _cometSession;
	private final JSONObject _pollerResponseHeaderJSONObject;

}