/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.ChannelListener;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.poller.PollerHeader;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			String content = getContent(httpServletRequest);

			if (content == null) {
				PortalUtil.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					new NoSuchLayoutException(), httpServletRequest,
					httpServletResponse);
			}
			else {
				httpServletResponse.setContentType(
					ContentTypes.TEXT_PLAIN_UTF8);

				ServletResponseUtil.write(
					httpServletResponse, content.getBytes(StringPool.UTF8));
			}
		}
		catch (Exception exception) {
			_log.error(exception.getMessage());

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception,
				httpServletRequest, httpServletResponse);
		}
	}

	protected String getContent(HttpServletRequest httpServletRequest)
		throws Exception {

		long userId = PortalUtil.getUserId(httpServletRequest);

		if (userId == 0) {
			return StringPool.BLANK;
		}

		String pollerRequestString = ParamUtil.getString(
			httpServletRequest, "pollerRequest");

		PollerHeader pollerHeader = PollerRequestHandlerUtil.getPollerHeader(
			pollerRequestString);

		if (pollerHeader == null) {
			return StringPool.BLANK;
		}

		if (userId != pollerHeader.getUserId()) {
			return StringPool.BLANK;
		}

		SynchronousPollerChannelListener synchronousPollerChannelListener =
			new SynchronousPollerChannelListener();

		long companyId = PortalUtil.getCompanyId(httpServletRequest);

		ChannelHubManagerUtil.getChannel(companyId, userId, true);

		ChannelHubManagerUtil.registerChannelListener(
			companyId, userId, synchronousPollerChannelListener);

		try {
			JSONObject pollerResponseHeaderJSONObject =
				PollerRequestHandlerUtil.processRequest(
					httpServletRequest, pollerRequestString);

			if (pollerResponseHeaderJSONObject == null) {
				return StringPool.BLANK;
			}

			return synchronousPollerChannelListener.getNotificationEvents(
				companyId, userId, pollerResponseHeaderJSONObject,
				PropsValues.POLLER_REQUEST_TIMEOUT);
		}
		finally {
			ChannelHubManagerUtil.unregisterChannelListener(
				companyId, userId, synchronousPollerChannelListener);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(PollerServlet.class);

	private static class SynchronousPollerChannelListener
		implements ChannelListener {

		@Override
		public void channelListenerRemoved(long channelId) {
			_countDownLatch.countDown();
		}

		public String getNotificationEvents(
				long companyId, long userId,
				JSONObject pollerResponseHeaderJSONObject, long timeout)
			throws ChannelException {

			try {
				_countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
			}
			catch (InterruptedException interruptedException) {
			}

			List<NotificationEvent> notificationEvents =
				ChannelHubManagerUtil.fetchNotificationEvents(
					companyId, userId, true);

			JSONArray jsonArray = JSONUtil.put(pollerResponseHeaderJSONObject);

			for (NotificationEvent notificationEvent : notificationEvents) {
				jsonArray.put(notificationEvent.toJSONObject());
			}

			return jsonArray.toString();
		}

		@Override
		public void notificationEventsAvailable(long channelId) {
			_countDownLatch.countDown();
		}

		private final CountDownLatch _countDownLatch = new CountDownLatch(1);

	}

}