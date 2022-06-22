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

package com.liferay.osb.provisioning.zendesk.connector.internal.service;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.provisioning.zendesk.connector.configuration.ZendeskConfiguration;
import com.liferay.osb.provisioning.zendesk.connector.internal.http.ZendeskHttpDelete;
import com.liferay.osb.provisioning.zendesk.connector.internal.http.ZendeskHttpGet;
import com.liferay.osb.provisioning.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.provisioning.zendesk.connector.service.ZendeskRequest;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.zendesk.connector.configuration.ZendeskConfiguration",
	immediate = true, service = ZendeskBaseWebService.class
)
public class ZendeskBaseWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements ZendeskBaseWebService {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	public JSONObject delete(String url, Map<String, String> parameters)
		throws PortalException {

		String response = null;

		try {
			response = doDelete(url, parameters, _authHeader);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (JSONWebServiceTransportException
					jsonWebServiceTransportException) {

			if (jsonWebServiceTransportException.getStatus() == 429) {
				try {
					Thread.sleep(_retryWaitTime);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return delete(url, parameters);
			}

			throw processedException(
				jsonWebServiceTransportException, url, parameters.toString(),
				response);
		}
		catch (Exception exception) {
			throw processedException(
				exception, url, parameters.toString(), response);
		}
	}

	public JSONObject delete(String endpoint, String json)
		throws PortalException {

		String response = null;

		try {
			ZendeskHttpDelete httpDelete = new ZendeskHttpDelete(endpoint);

			addHeaders(httpDelete, _headers);

			httpDelete.setEntity(getStringEntity(endpoint, json));

			response = execute(httpDelete);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (JSONWebServiceTransportException
					jsonWebServiceTransportException) {

			if (jsonWebServiceTransportException.getStatus() == 429) {
				try {
					Thread.sleep(_retryWaitTime);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return delete(endpoint, json);
			}

			throw processedException(
				jsonWebServiceTransportException, endpoint, json, response);
		}
		catch (Exception exception) {
			throw processedException(exception, endpoint, json, response);
		}
	}

	public JSONObject get(String url, Map<String, String> parameters)
		throws PortalException {

		String response = null;

		try {
			response = doGet(url, parameters, _authHeader);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (JSONWebServiceTransportException
					jsonWebServiceTransportException) {

			if (jsonWebServiceTransportException.getStatus() == 429) {
				try {
					Thread.sleep(_retryWaitTime);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return get(url, parameters);
			}

			throw processedException(
				jsonWebServiceTransportException, url, parameters.toString(),
				response);
		}
		catch (Exception exception) {
			throw processedException(
				exception, url, parameters.toString(), response);
		}
	}

	public JSONObject get(String endpoint, String json) throws PortalException {
		ZendeskHttpGet httpGet = new ZendeskHttpGet(endpoint);

		String response = null;

		try {
			addHeaders(httpGet, _headers);

			httpGet.setEntity(getStringEntity(endpoint, json));

			response = execute(httpGet);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (JSONWebServiceInvocationException
					jsonWebServiceInvocationException) {

			if (jsonWebServiceInvocationException.getStatus() ==
					HttpServletResponse.SC_NOT_FOUND) {

				throw new NoSuchModelException(
					jsonWebServiceInvocationException);
			}

			throw new PortalException(jsonWebServiceInvocationException);
		}
		catch (JSONWebServiceTransportException
					jsonWebServiceTransportException) {

			if (jsonWebServiceTransportException.getStatus() == 429) {
				try {
					Thread.sleep(_retryWaitTime);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return get(endpoint, json);
			}

			throw processedException(
				jsonWebServiceTransportException, endpoint, json, response);
		}
		catch (Exception exception) {
			throw processedException(exception, endpoint, json, response);
		}
	}

	public JSONObject post(
			String endpoint, Map<String, String> parameters, String fileName,
			byte[] bytes)
		throws PortalException {

		String response = null;

		try {
			HttpPost httpPost = new HttpPost(
				Http.HTTPS_WITH_SLASH + _domainName + endpoint);

			httpPost.addHeader("Authorization", _credentials);

			MultipartEntity multipartEntity = new MultipartEntity();

			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				multipartEntity.addPart(
					entry.getKey(), new StringBody(entry.getValue()));
			}

			ByteArrayBody byteArrayBody = new ByteArrayBody(
				bytes, MimeTypesUtil.getContentType(fileName), fileName);

			multipartEntity.addPart("file", byteArrayBody);

			httpPost.setEntity(multipartEntity);

			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponse = httpClient.execute(httpPost);

			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() == 429) {
				try {
					Thread.sleep(_retryWaitTime);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return post(endpoint, parameters, fileName, bytes);
			}

			response = EntityUtils.toString(httpResponse.getEntity());

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception exception) {
			throw processedException(
				exception, endpoint, parameters.toString(), response);
		}
	}

	public JSONObject post(String endpoint, String json)
		throws PortalException {

		String response = null;

		try {
			HttpPost httpPost = new HttpPost(endpoint);

			addHeaders(httpPost, _headers);

			httpPost.setEntity(getStringEntity(endpoint, json));

			response = execute(httpPost);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (JSONWebServiceTransportException
					jsonWebServiceTransportException) {

			if (jsonWebServiceTransportException.getStatus() == 429) {
				try {
					Thread.sleep(_retryWaitTime);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return post(endpoint, json);
			}

			throw processedException(
				jsonWebServiceTransportException, endpoint, json, response);
		}
		catch (Exception exception) {
			throw processedException(exception, endpoint, json, response);
		}
	}

	public JSONObject put(String endpoint, String json) throws PortalException {
		String response = null;

		try {
			HttpPut httpPut = new HttpPut(endpoint);

			addHeaders(httpPut, _headers);

			httpPut.setEntity(getStringEntity(endpoint, json));

			response = execute(httpPut);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (JSONWebServiceTransportException
					jsonWebServiceTransportException) {

			if (jsonWebServiceTransportException.getStatus() == 429) {
				try {
					Thread.sleep(_retryWaitTime);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return put(endpoint, json);
			}

			throw processedException(
				jsonWebServiceTransportException, endpoint, json, response);
		}
		catch (Exception exception) {
			throw processedException(exception, endpoint, json, response);
		}
	}

	public JSONObject send(ZendeskRequest zendeskRequest)
		throws PortalException {

		String method = zendeskRequest.getMethod();
		JSONObject body = zendeskRequest.getBody();

		if (method.equals("delete")) {
			if (zendeskRequest.hasParameters()) {
				return delete(
					zendeskRequest.getEndpoint(),
					zendeskRequest.getParameters());
			}
			else if (body != null) {
				return delete(zendeskRequest.getEndpoint(), body.toString());
			}
			else {
				return delete(
					zendeskRequest.getEndpoint(),
					new HashMap<String, String>());
			}
		}
		else if (method.equals("get")) {
			return get(
				zendeskRequest.getEndpoint(), zendeskRequest.getParameters());
		}
		else if (method.equals("post")) {
			return post(zendeskRequest.getEndpoint(), body.toString());
		}
		else if (method.equals("put")) {
			return put(zendeskRequest.getEndpoint(), body.toString());
		}
		else {
			throw new PortalException("Invalid Zendesk Request");
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		ZendeskConfiguration zendeskConfiguration =
			ConfigurableUtil.createConfigurable(
				ZendeskConfiguration.class, properties);

		_apiToken = zendeskConfiguration.apiToken();
		_domainName = zendeskConfiguration.domainName();
		_emailAddress = zendeskConfiguration.emailAddress();
		_errorEmailAddress = zendeskConfiguration.errorEmailAddress();
		_retryWaitTime = Long.valueOf(zendeskConfiguration.retryWaitTime());

		_credentials = _getCredentials();

		_authHeader = new HashMap<String, String>() {
			{
				put("Authorization", _credentials);
			}
		};

		_headers = new ArrayList<NameValuePair>() {
			{
				add(new BasicNameValuePair("Authorization", _credentials));
				add(new BasicNameValuePair("Content-Type", "application/json"));
			}
		};
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		setHostName(_domainName);
		setHostPort(Http.HTTPS_PORT);
		setProtocol(Http.HTTPS);

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();

		requestConfigBuilder.setCookieSpec(CookieSpecs.STANDARD);

		httpRequestBase.setConfig(requestConfigBuilder.build());

		return super.execute(httpRequestBase);
	}

	protected StringEntity getStringEntity(String endpoint, String json) {
		StringEntity stringEntity = new StringEntity(
			json, StandardCharsets.UTF_8);

		stringEntity.setContentType("application/json");

		return stringEntity;
	}

	protected PortalException processedException(
			Exception exception, String url, String data, String response)
		throws PortalException {

		if (exception instanceof
				JSONWebServiceTransportException.CommunicationFailure) {

			JSONWebServiceTransportException.CommunicationFailure
				communicationFailure =
					(JSONWebServiceTransportException.CommunicationFailure)
						exception;

			if ((communicationFailure.getStatus() == 422) &&
				url.contains("/identities/")) {

				sendEmail(exception, url, data);
			}
		}

		_log.error("Request failed for " + url);
		_log.error("Data: " + data);

		if (response != null) {
			_log.error("Error parsing response: " + response);
		}

		return new PortalException(exception);
	}

	protected void sendEmail(Exception exception, String url, String data) {
		StringBundler sb = new StringBundler(6);

		sb.append("<strong>URL</strong><br />");
		sb.append(url);
		sb.append("<br /><strong>Parameters: </strong><br />");
		sb.append(data);
		sb.append("<br /><br /><strong>Stack Trace:</strong><br />");

		sb.append(
			StringUtil.replace(
				StackTraceUtil.getStackTrace(exception), CharPool.NEW_LINE,
				"<br />"));

		try {
			InternetAddress from = new InternetAddress("no-reply@liferay.com");
			InternetAddress to = new InternetAddress(_errorEmailAddress);

			String mailSubject = "Auto Generated Zendesk API Error Message";

			MailMessage mailMessage = new MailMessage(
				from, to, mailSubject, sb.toString(), true);

			_mailService.sendEmail(mailMessage);
		}
		catch (AddressException addressException) {
			_log.error(addressException, addressException);
		}
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private String _getCredentials() {
		String zendeskCredentials = StringBundler.concat(
			_emailAddress, "/token:", _apiToken);

		return "Basic " + Base64.encode(zendeskCredentials.getBytes());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskBaseWebServiceImpl.class);

	private String _apiToken;
	private Map<String, String> _authHeader;
	private String _credentials;
	private String _domainName;
	private String _emailAddress;
	private String _errorEmailAddress;
	private List<NameValuePair> _headers;

	@Reference
	private MailService _mailService;

	private long _retryWaitTime;

}