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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = {"api.token=", "host=", "provider=okta"},
	service = ContactIdentityProvider.class
)
public class OktaContactIdentityProvider implements ContactIdentityProvider {

	public Contact fetchContactByEmailAddress(String emailAddress)
		throws Exception {

		Contact contact = _contactWebService.fetchContactByEmailAddress(
			emailAddress);

		if (contact == null) {
			String response = _sendRequest(_URL_API_REST_USERS + emailAddress);

			JSONObject jsonObject = _jsonFactory.createJSONObject(response);

			if (jsonObject.has("errorCode")) {
				return null;
			}

			JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

			contact = new Contact();

			contact.setEmailAddress(emailAddress);
			contact.setFirstName(profileJSONObject.getString("firstName"));
			contact.setLastName(profileJSONObject.getString("lastName"));
			contact.setMiddleName(profileJSONObject.getString("middleName"));
			contact.setUuid(profileJSONObject.getString("uuid"));
		}

		return contact;
	}

	public Contact fetchContactBySessionId(String sessionId) throws Exception {
		String emailAddress = _portalCache.get(sessionId);

		if (emailAddress == StringPool.BLANK) {
			return null;
		}

		if (emailAddress == null) {
			String response = _sendRequest(_URL_API_GET_SESSION + sessionId);

			if (Validator.isNotNull(response)) {
				JSONObject jsonObject = _jsonFactory.createJSONObject(response);

				emailAddress = jsonObject.getString("login");
			}
		}

		if (Validator.isNotNull(emailAddress)) {
			_portalCache.put(sessionId, emailAddress);

			return _contactWebService.fetchContactByEmailAddress(emailAddress);
		}

		_portalCache.put(sessionId, StringPool.BLANK);

		return null;
	}

	public Integer fetchContactStatusByEmailAddress(String emailAddress)
		throws Exception {

		String response = _sendRequest(_URL_API_REST_USERS + emailAddress);

		JSONObject jsonObject = _jsonFactory.createJSONObject(response);

		if (jsonObject.has("errorCode")) {
			return null;
		}

		String status = jsonObject.getString("status");

		if (Validator.isNotNull(status) && status.equals("ACTIVE")) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		return WorkflowConstants.STATUS_INACTIVE;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_apiToken = String.valueOf(properties.get("api.token"));
		_host = String.valueOf(properties.get("host"));

		_portalCache = (PortalCache<String, String>)_multiVMPool.getPortalCache(
			OktaContactIdentityProvider.class.getName());
	}

	@Deactivate
	protected void deactivate() {
		_multiVMPool.removePortalCache(
			OktaContactIdentityProvider.class.getName());
	}

	private String _sendRequest(String endpoint) throws Exception {
		Http.Options options = new Http.Options();

		options.addHeader("Authorization", "SSWS " + _apiToken);
		options.addHeader("Content-Type", "application/json");

		StringBundler sb = new StringBundler(3);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(_host);
		sb.append(endpoint);

		options.setLocation(sb.toString());

		String response = StringPool.BLANK;

		byte[] bytes = _http.URLtoByteArray(options);

		if (bytes != null) {
			response = new String(bytes);
		}

		return response;
	}

	private static final String _URL_API_GET_SESSION = "/api/v1/sessions/";

	private static final String _URL_API_REST_USERS = "/api/v1/users/";

	private String _apiToken;

	@Reference
	private ContactWebService _contactWebService;

	private String _host;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private MultiVMPool _multiVMPool;

	private PortalCache<String, String> _portalCache;

}