/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
public abstract class BaseMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		try {
			if (!isParseMessage(message)) {
				if (_log.isDebugEnabled()) {
					_log.debug("Skip Parsing Message: " + message.getPayload());
				}

				return;
			}

			try {
				JSONObject jsonObject = jsonFactory.createJSONObject(
					(String)message.getPayload());

				doParse(jsonObject);
			}
			catch (JSONException jsonException) {
				JSONArray jsonArray = jsonFactory.createJSONArray(
					(String)message.getPayload());

				for (int i = 0; i < jsonArray.length(); i++) {
					doParse(jsonArray.getJSONObject(i));
				}
			}
		}
		catch (Exception exception) {
			try {
				handleError(
					message.getDestinationName(), (String)message.getPayload(),
					new Exception[] {exception});
			}
			catch (PortalException portalException) {
				_log.error(message);

				_log.error(portalException, portalException);
			}
		}
		finally {
			postParseMessage(message);
		}
	}

	protected abstract void doParse(JSONObject jsonObject) throws Exception;

	protected void handleError(
			String routingKey, String message, Exception[] exceptions)
		throws PortalException {

		for (Exception exception : exceptions) {
			_log.error(message, exception);
		}
	}

	protected boolean isParseMessage(Message message) {
		return true;
	}

	protected void postParseMessage(Message message) {
	}

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected ZendeskTicketWebService zendeskTicketWebService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageSubscriber.class);

}