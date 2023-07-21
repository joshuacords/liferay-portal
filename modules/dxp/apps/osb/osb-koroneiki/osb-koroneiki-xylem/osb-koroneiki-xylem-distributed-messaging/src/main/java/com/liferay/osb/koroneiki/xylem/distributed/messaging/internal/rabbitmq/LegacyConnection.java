/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.BaseConnection;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"errorEmailAddress=", "host=", "password=", "port=", "username=",
		"useSSL="
	},
	service = LegacyConnection.class
)
public class LegacyConnection extends BaseConnection {

	@Activate
	@Override
	protected void activate(Map<String, Object> properties) {
		super.activate(properties);

		_errorEmailAddress = GetterUtil.getString(
			properties.get("error.email.address"));
	}

	@Override
	protected void handleConnectionError(Exception exception) {
		super.handleConnectionError(exception);

		if (Validator.isNull(_errorEmailAddress)) {
			return;
		}

		StringBundler sb = new StringBundler(2);

		sb.append("Could not connect to RabbitMQ<br /><br />");
		sb.append(
			StringUtil.replace(
				StackTraceUtil.getStackTrace(exception), CharPool.NEW_LINE,
				"<br />"));

		try {
			InternetAddress from = new InternetAddress("no-reply@liferay.com");
			InternetAddress to = new InternetAddress(_errorEmailAddress);

			String mailSubject = "Auto Generated RabbitMQ Error Message";

			MailMessage mailMessage = new MailMessage(
				from, to, mailSubject, sb.toString(), true);

			_mailService.sendEmail(mailMessage);
		}
		catch (AddressException addressException) {
			_log.error(addressException, addressException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LegacyConnection.class);

	private String _errorEmailAddress;

	@Reference
	private MailService _mailService;

}