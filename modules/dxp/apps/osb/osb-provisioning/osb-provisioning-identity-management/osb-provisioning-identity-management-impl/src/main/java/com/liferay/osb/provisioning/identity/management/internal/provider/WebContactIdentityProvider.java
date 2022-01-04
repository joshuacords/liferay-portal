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

package com.liferay.osb.provisioning.identity.management.internal.provider;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientFactory;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	immediate = true,
	property = {
		"api.token=", "error.email.address=", "host=", "port=", "protocol=",
		"provider=web"
	},
	service = ContactIdentityProvider.class
)
public class WebContactIdentityProvider implements ContactIdentityProvider {

	public Contact fetchContactByEmailAddress(String emailAddress)
		throws Exception {

		JSONObject jsonObject = _fetchUserJSONObject(emailAddress);

		if (jsonObject == null) {
			return null;
		}

		Contact contact = new Contact();

		contact.setEmailAddress(jsonObject.getString("emailAddress"));
		contact.setFirstName(jsonObject.getString("firstName"));
		contact.setLastName(jsonObject.getString("lastName"));
		contact.setMiddleName(jsonObject.getString("middleName"));
		contact.setUuid(jsonObject.getString("uuid"));

		return contact;
	}

	public Contact fetchContactBySessionId(String sessionId) throws Exception {
		throw new UnsupportedOperationException();
	}

	public Integer fetchContactStatusByEmailAddress(String emailAddress)
		throws Exception {

		JSONObject jsonObject = _fetchUserJSONObject(emailAddress);

		if (jsonObject == null) {
			return null;
		}

		return (Integer)jsonObject.get("status");
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_errorEmailAddress = String.valueOf(
			properties.get("error.email.address"));

		if (Validator.isNotNull(properties.get("host"))) {
			Map<String, Object> jsonWebServiceClientProperties =
				new HashMap<>();

			jsonWebServiceClientProperties.put(
				"headers",
				"Authorization=token " + properties.get("api.token"));
			jsonWebServiceClientProperties.put(
				"hostName", properties.get("host"));
			jsonWebServiceClientProperties.put(
				"hostPort", properties.get("port"));
			jsonWebServiceClientProperties.put(
				"protocol", properties.get("protocol"));

			_jsonWebServiceClient = _jsonWebServiceClientFactory.getInstance(
				jsonWebServiceClientProperties, false);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_jsonWebServiceClient != null) {
			_jsonWebServiceClient.destroy();
		}
	}

	private JSONObject _fetchUserJSONObject(String emailAddress)
		throws Exception {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("emailAddress", emailAddress);

		return _getToJSONObject(
			_URL_API_REST_USERS + "email_address", parameters);
	}

	private JSONObject _getToJSONObject(
			String url, Map<String, String> parameters)
		throws Exception {

		if (_jsonWebServiceClient == null) {
			return null;
		}

		try {
			String response = _jsonWebServiceClient.doGet(url, parameters);

			return _jsonFactory.createJSONObject(response);
		}
		catch (JSONWebServiceInvocationException
					jsonWebServiceInvocationException) {

			if (jsonWebServiceInvocationException.getStatus() ==
					HttpServletResponse.SC_NOT_FOUND) {

				return null;
			}

			_sendEmail(jsonWebServiceInvocationException, parameters);

			throw jsonWebServiceInvocationException;
		}
		catch (Exception exception) {
			_sendEmail(exception, parameters);

			throw exception;
		}
	}

	private void _sendEmail(
		Exception exception, Map<String, String> parameters) {

		if (Validator.isNull(_errorEmailAddress)) {
			return;
		}

		StringBundler sb = new StringBundler(5);

		if (parameters != null) {
			sb.append("<strong>Parameters: </strong><br />");
			sb.append(MapUtil.toString(parameters));
			sb.append("<br /><br />");
		}

		sb.append("<strong>Stack Trace:</strong><br />");

		sb.append(
			StringUtil.replace(
				StackTraceUtil.getStackTrace(exception), CharPool.NEW_LINE,
				"<br />"));

		try {
			InternetAddress from = new InternetAddress("no-reply@liferay.com");
			InternetAddress to = new InternetAddress(_errorEmailAddress);

			MailMessage mailMessage = new MailMessage(
				from, to, "Auto Generated Web API Error Message", sb.toString(),
				true);

			_mailService.sendEmail(mailMessage);
		}
		catch (AddressException addressException) {
			_log.error(addressException, addressException);
		}
	}

	private static final String _URL_API_REST_USERS = "/osb-entity-web/users/";

	private static final Log _log = LogFactoryUtil.getLog(
		WebContactIdentityProvider.class);

	private String _errorEmailAddress;

	@Reference
	private JSONFactory _jsonFactory;

	private JSONWebServiceClient _jsonWebServiceClient;

	@Reference
	private JSONWebServiceClientFactory _jsonWebServiceClientFactory;

	@Reference
	private MailService _mailService;

}