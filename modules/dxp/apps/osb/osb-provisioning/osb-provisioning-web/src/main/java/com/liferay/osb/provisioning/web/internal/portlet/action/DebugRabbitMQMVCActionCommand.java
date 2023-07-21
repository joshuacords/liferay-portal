/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ADMIN,
		"mvc.command.name=/admin/debug_rabbitmq"
	},
	service = MVCActionCommand.class
)
public class DebugRabbitMQMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String routingKey = ParamUtil.getString(
				actionRequest, "routingKey");
			String message = ParamUtil.getString(actionRequest, "message");
			String[] properties = StringUtil.split(
				ParamUtil.getString(actionRequest, "properties"),
				StringPool.NEW_LINE);

			message = StringUtil.replace(
				message, CharPool.NEW_LINE, StringPool.BLANK);

			Map<String, Object> propertiesMap = MapUtil.toLinkedHashMap(
				properties);

			_messageRouter.route(
				routingKey, new Message(routingKey, propertiesMap, message));

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DebugRabbitMQMVCActionCommand.class);

	@Reference
	private MessageRouter _messageRouter;

}