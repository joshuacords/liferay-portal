/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.customer.web.service.internal;

import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.customer.web.service.internal.configuration.CustomerConfiguration;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientFactory;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.customer.web.service.internal.configuration.CustomerConfiguration",
	immediate = true, service = AccountEntryWebService.class
)
public class AccountEntryWebServiceImpl implements AccountEntryWebService {

	public AccountEntry fetchAccountEntry(String koroneikiAccountKey)
		throws Exception {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("koroneikiAccountKey", koroneikiAccountKey);

		String response = null;

		try {
			response = _jsonWebServiceClient.doGet(
				_URL_API_JSONWS_FETCH_ACCOUNT_ENTRY, parameters);
		}
		catch (JSONWebServiceException jsonWebServiceException) {
			_log.error(jsonWebServiceException, jsonWebServiceException);

			throw jsonWebServiceException;
		}

		if (response == null) {
			return null;
		}

		return new AccountEntry(_jsonFactory.createJSONObject(response));
	}

	public String getAccountAttachmentURL(long accountAttachmentId)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append(_accountAttachmentURL);
		sb.append("?accountAttachmentId=");
		sb.append(accountAttachmentId);
		sb.append("&apiToken=");
		sb.append(_getAccountAttachmentApiToken());

		return sb.toString();
	}

	public String getUpdateAccountAttachmentURL() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append(_accountAttachmentURL);
		sb.append("?apiToken=");
		sb.append(_getAccountAttachmentApiToken());

		return sb.toString();
	}

	public void syncToZendesk(String koroneikiAccountKey) throws Exception {
		Map<String, String> parameters = new HashMap<>();

		parameters.put("koroneikiAccountKey", koroneikiAccountKey);

		try {
			_jsonWebServiceClient.doPost(
				_URL_API_JSONWS_SYNC_TO_ZENDESK, parameters);
		}
		catch (JSONWebServiceException jsonWebServiceException) {
			_log.error(jsonWebServiceException, jsonWebServiceException);

			throw jsonWebServiceException;
		}
	}

	public void updateInstructions(
			String koroneikiAccountKey, String instructions)
		throws Exception {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("instructions", instructions);
		parameters.put("koroneikiAccountKey", koroneikiAccountKey);

		try {
			_jsonWebServiceClient.doPost(
				_URL_API_JSONWS_UPDATE_INSTRUCTIONS, parameters);
		}
		catch (JSONWebServiceException jsonWebServiceException) {
			_log.error(jsonWebServiceException, jsonWebServiceException);

			throw jsonWebServiceException;
		}
	}

	public void updateLanguageId(String koroneikiAccountKey, String languageId)
		throws Exception {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("koroneikiAccountKey", koroneikiAccountKey);
		parameters.put("languageId", languageId);

		try {
			_jsonWebServiceClient.doPost(
				_URL_API_JSONWS_UPDATE_LANGUAGE_ID, parameters);
		}
		catch (JSONWebServiceException jsonWebServiceException) {
			_log.error(jsonWebServiceException, jsonWebServiceException);

			throw jsonWebServiceException;
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		CustomerConfiguration customerConfiguration =
			ConfigurableUtil.createConfigurable(
				CustomerConfiguration.class, properties);

		StringBundler sb = new StringBundler(6);

		sb.append(customerConfiguration.scheme());
		sb.append("://");
		sb.append(customerConfiguration.host());
		sb.append(":");
		sb.append(customerConfiguration.port());
		sb.append("/o/account-attachment");

		_accountAttachmentURL = sb.toString();

		Map<String, Object> jsonWebServicePproperties = new HashMap<>();

		jsonWebServicePproperties.put(
			"headers", "OSB_API_Token=" + customerConfiguration.apiToken());
		jsonWebServicePproperties.put("hostName", customerConfiguration.host());
		jsonWebServicePproperties.put(
			"hostPort", String.valueOf(customerConfiguration.port()));
		jsonWebServicePproperties.put(
			"protocol", customerConfiguration.scheme());

		_jsonWebServiceClient = _jsonWebServiceClientFactory.getInstance(
			jsonWebServicePproperties, false);
	}

	@Deactivate
	protected void deactivate() {
		if (_jsonWebServiceClient != null) {
			_jsonWebServiceClient.destroy();
		}
	}

	private String _getAccountAttachmentApiToken() throws Exception {
		try {
			return _jsonWebServiceClient.doGet(
				_URL_ACCOUNT_ATTACHMENT_TOKEN + "?cmd=token",
				new HashMap<String, String>());
		}
		catch (JSONWebServiceException jsonWebServiceException) {
			_log.error(jsonWebServiceException, jsonWebServiceException);

			throw jsonWebServiceException;
		}
	}

	private static final String _URL_ACCOUNT_ATTACHMENT_TOKEN =
		"/o/account-attachment";

	private static final String _URL_API_JSONWS =
		"/api/jsonws/osb.accountentry";

	private static final String _URL_API_JSONWS_FETCH_ACCOUNT_ENTRY =
		_URL_API_JSONWS + "/fetch-koroneiki-account-entry";

	private static final String _URL_API_JSONWS_SYNC_TO_ZENDESK =
		_URL_API_JSONWS + "/sync-to-zendesk";

	private static final String _URL_API_JSONWS_UPDATE_INSTRUCTIONS =
		_URL_API_JSONWS + "/update-instructions";

	private static final String _URL_API_JSONWS_UPDATE_LANGUAGE_ID =
		_URL_API_JSONWS + "/update-language-id";

	private static final Log _log = LogFactoryUtil.getLog(
		AccountEntryWebServiceImpl.class);

	private String _accountAttachmentURL;

	@Reference
	private JSONFactory _jsonFactory;

	private JSONWebServiceClient _jsonWebServiceClient;

	@Reference
	private JSONWebServiceClientFactory _jsonWebServiceClientFactory;

}