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

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.provisioning.distributed.messaging.constants.GooglePubsubConstants;
import com.liferay.osb.provisioning.exception.ContactEmailAddressException;
import com.liferay.osb.provisioning.exception.ContactNameException;
import com.liferay.osb.provisioning.identity.management.constants.OktaConstants;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

	public void addMembership(String groupId, String emailAddress)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put(
			"action", "ADD"
		).put(
			"groupName", groupId
		).put(
			"login", emailAddress
		);

		_messagePublisher.publish(
			GooglePubsubConstants.TOPIC_OKTA_USER_GROUP_UPDATE,
			new Message(jsonObject.toString()));
	}

	public Contact createContact(
			String emailAddress, String firstName, String middleName,
			String lastName)
		throws Exception {

		if (Validator.isNull(emailAddress) ||
			!Validator.isEmailAddress(emailAddress)) {

			throw new ContactEmailAddressException();
		}

		if (Validator.isNull(firstName) || Validator.isNull(lastName)) {
			throw new ContactNameException();
		}

		Contact contact = _contactWebService.fetchContactByEmailAddress(
			emailAddress);

		if (contact == null) {
			contact = new Contact();

			contact.setEmailAddress(emailAddress);
			contact.setFirstName(firstName);
			contact.setLastName(lastName);
			contact.setMiddleName(middleName);
			contact.setUuid(PortalUUIDUtil.generate());

			contact = _contactWebService.addContact(
				StringPool.BLANK, StringPool.BLANK, contact);
		}

		String response = _sendRequest(_URL_API_REST_USERS + emailAddress);

		JSONObject jsonObject = _jsonFactory.createJSONObject(response);

		if (jsonObject.has("errorCode")) {
			_messagePublisher.publish(
				GooglePubsubConstants.TOPIC_OKTA_USER_CREATE,
				new Message(contact.toString()));
		}

		return contact;
	}

	public Contact fetchContactByEmailAddress(String emailAddress, boolean sync)
		throws Exception {

		Contact contact = _contactWebService.fetchContactByEmailAddress(
			emailAddress);

		if (contact == null) {
			String response = _sendRequest(_URL_API_REST_USERS + emailAddress);

			JSONObject jsonObject = _jsonFactory.createJSONObject(response);

			if (jsonObject.has("errorCode")) {
				return null;
			}

			contact = _toContact(jsonObject);

			if (sync) {
				contact = _contactWebService.addContact(
					StringPool.BLANK, StringPool.BLANK, contact);
			}
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

	public Contact fetchContactByUuid(String uuid) throws Exception {
		Contact contact = _contactWebService.fetchContactByUuid(uuid);

		if (contact == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_URL_API_REST_USERS);
			sb.append("?search=");
			sb.append(_http.encodePath("profile.uuid eq \"" + uuid + "\""));

			String response = _sendRequest(sb.toString());

			JSONArray jsonArray = _jsonFactory.createJSONArray(response);

			if (jsonArray.length() <= 0) {
				return null;
			}

			contact = _toContact(jsonArray.getJSONObject(0));
		}

		return contact;
	}

	public Integer fetchContactStatusByEmailAddress(String emailAddress)
		throws Exception {

		String response = _sendRequest(_URL_API_REST_USERS + emailAddress);

		JSONObject jsonObject = _jsonFactory.createJSONObject(response);

		if (jsonObject.has("errorCode")) {
			return null;
		}

		String status = jsonObject.getString("status");

		if (ArrayUtil.contains(_STATUSES_DEACTIVATED, status)) {
			return WorkflowConstants.STATUS_INACTIVE;
		}

		if (ArrayUtil.contains(_STATUSES_PENDING, status)) {
			return WorkflowConstants.STATUS_PENDING;
		}

		return WorkflowConstants.STATUS_APPROVED;
	}

	public List<Contact> getGroupContacts(String groupId) throws Exception {
		return _getAllContacts(groupId);
	}

	public void removeMembership(String groupId, String emailAddress)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put(
			"action", "REMOVE"
		).put(
			"groupName", groupId
		).put(
			"login", emailAddress
		);

		_messagePublisher.publish(
			GooglePubsubConstants.TOPIC_OKTA_USER_GROUP_UPDATE,
			new Message(jsonObject.toString()));
	}

	public Contact syncContact(Contact contact) throws Exception {
		String response = _sendRequest(
			_URL_API_REST_USERS + contact.getEmailAddress());

		JSONObject jsonObject = _jsonFactory.createJSONObject(response);

		if (jsonObject.has("errorCode")) {
			_messagePublisher.publish(
				GooglePubsubConstants.TOPIC_OKTA_USER_CREATE,
				new Message(contact.toString()));

			_syncGroups(contact, Collections.emptyList());
		}
		else {
			JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

			String uuid = profileJSONObject.getString("uuid");
			String firstName = profileJSONObject.getString("firstName");
			String lastName = profileJSONObject.getString("lastName");

			String status = jsonObject.getString("status");

			if ((Validator.isNotNull(uuid) &&
				 !uuid.equals(contact.getUuid())) ||
				(Validator.isNotNull(firstName) &&
				 !firstName.equals(contact.getFirstName())) ||
				(Validator.isNotNull(lastName) &&
				 !lastName.equals(contact.getLastName())) ||
				(_isEmailAddressVerified(status) &&
				 !contact.getEmailAddressVerified())) {

				contact.setUuid(uuid);
				contact.setFirstName(firstName);
				contact.setLastName(lastName);

				if (_isEmailAddressVerified(status)) {
					contact.setEmailAddressVerified(true);
				}

				String agentName = StringPool.BLANK;
				String agentUID = StringPool.BLANK;

				User user = _userLocalService.fetchUser(
					PrincipalThreadLocal.getUserId());

				if ((user != null) && !user.isDefaultUser()) {
					agentName = user.getFullName();
					agentUID = user.getUuid();
				}

				_contactWebService.updateContactByEmailAddress(
					agentName, agentUID, contact.getEmailAddress(), contact);
			}

			_syncGroups(contact, _getGroups(contact.getEmailAddress()));
		}

		return contact;
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

	private List<Contact> _getAllContacts(String groupId) throws Exception {
		List<Contact> contacts = new ArrayList<>();

		StringBundler sb = new StringBundler(6);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(_host);
		sb.append(_URL_API_REST_GROUPS);
		sb.append(groupId);
		sb.append(_URL_API_REST_GROUPS_USERS);
		sb.append("?limit=200");

		String requestURL = sb.toString();

		while (Validator.isNotNull(requestURL)) {
			URL url = new URL(requestURL);

			HttpURLConnection httpURLConnection =
				(HttpURLConnection)url.openConnection();

			httpURLConnection.setRequestProperty(
				"Authorization", "SSWS " + _apiToken);

			int responseCode = httpURLConnection.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						StringUtil.read(httpURLConnection.getErrorStream()));
				}

				throw new Exception("Server responded with " + responseCode);
			}

			String response = StringUtil.read(
				httpURLConnection.getInputStream());

			JSONArray jsonArray = _jsonFactory.createJSONArray(response);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				contacts.add(_toContact(jsonObject));
			}

			requestURL = _getNextURL(httpURLConnection.getHeaderFields());
		}

		return contacts;
	}

	private List<String> _getGroups(String emailAddress) throws Exception {
		String response = _sendRequest(
			_URL_API_REST_USERS + emailAddress + _URL_API_REST_USER_GROUPS);

		if (response.contains("errorCode")) {
			return Collections.emptyList();
		}

		List<String> groups = new ArrayList<>();

		JSONArray jsonArray = _jsonFactory.createJSONArray(response);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject groupJSONObject = jsonArray.getJSONObject(i);

			JSONObject groupProfileJSONObject = groupJSONObject.getJSONObject(
				"profile");

			groups.add(groupProfileJSONObject.getString("name"));
		}

		return groups;
	}

	private String _getNextURL(Map<String, List<String>> headerFields) {
		List<String> links = headerFields.get("link");

		for (String link : links) {
			String[] linkArray = StringUtil.split(link, StringPool.SEMICOLON);

			if (linkArray.length <= 1) {
				continue;
			}

			String relAttribute = StringUtil.trim(linkArray[1]);

			if (relAttribute.equals("rel=\"next\"")) {
				String url = linkArray[0];

				return url.substring(1, url.length() - 1);
			}
		}

		return null;
	}

	private boolean _isEmailAddressVerified(String status) {
		if (Validator.isNotNull(status) &&
			ArrayUtil.contains(_STATUSES_VERIFIED, status)) {

			return true;
		}

		return false;
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

	private void _syncGroups(Contact contact, List<String> groups)
		throws Exception {

		List<String> entitlements = new ArrayList<>();

		if (!ArrayUtil.isEmpty(contact.getEntitlements())) {
			for (Entitlement entitlement : contact.getEntitlements()) {
				entitlements.add(entitlement.getName());
			}
		}

		if (entitlements.contains(EntitlementConstants.CUSTOMER) &&
			!groups.contains(OktaConstants.GROUP_NAME_CUSTOMERS)) {

			addMembership(
				OktaConstants.GROUP_NAME_CUSTOMERS, contact.getEmailAddress());
		}
		else if (groups.contains(OktaConstants.GROUP_NAME_CUSTOMERS) &&
				 !entitlements.contains(EntitlementConstants.CUSTOMER)) {

			removeMembership(
				OktaConstants.GROUP_NAME_CUSTOMERS, contact.getEmailAddress());
		}

		if (entitlements.contains(EntitlementConstants.PARTNER) &&
			!groups.contains(OktaConstants.GROUP_NAME_PARTNERS)) {

			addMembership(
				OktaConstants.GROUP_NAME_PARTNERS, contact.getEmailAddress());
		}
		else if (groups.contains(OktaConstants.GROUP_NAME_PARTNERS) &&
				 !entitlements.contains(EntitlementConstants.PARTNER)) {

			removeMembership(
				OktaConstants.GROUP_NAME_PARTNERS, contact.getEmailAddress());
		}
	}

	private Contact _toContact(JSONObject jsonObject) {
		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		Contact contact = new Contact();

		contact.setEmailAddress(profileJSONObject.getString("email"));
		contact.setFirstName(profileJSONObject.getString("firstName"));
		contact.setLastName(profileJSONObject.getString("lastName"));
		contact.setMiddleName(profileJSONObject.getString("middleName"));
		contact.setUuid(profileJSONObject.getString("uuid"));

		if (_isEmailAddressVerified(jsonObject.getString("status"))) {
			contact.setEmailAddressVerified(true);
		}

		return contact;
	}

	private static final String[] _STATUSES_DEACTIVATED = {"DEPROVISIONED"};

	private static final String[] _STATUSES_PENDING = {"PROVISIONED", "STAGED"};

	private static final String[] _STATUSES_VERIFIED = {
		"ACTIVE", "LOCKED_OUT", "PASSWORD_EXPIRED", "RECOVERY", "SUSPENDED"
	};

	private static final String _URL_API_GET_SESSION = "/api/v1/sessions/";

	private static final String _URL_API_REST_GROUPS = "/api/v1/groups/";

	private static final String _URL_API_REST_GROUPS_USERS = "/users";

	private static final String _URL_API_REST_USER_GROUPS = "/groups";

	private static final String _URL_API_REST_USERS = "/api/v1/users/";

	private static final Log _log = LogFactoryUtil.getLog(
		OktaContactIdentityProvider.class);

	private String _apiToken;

	@Reference
	private ContactWebService _contactWebService;

	private String _host;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private MessagePublisher _messagePublisher;

	@Reference
	private MultiVMPool _multiVMPool;

	private PortalCache<String, String> _portalCache;

	@Reference
	private UserLocalService _userLocalService;

}