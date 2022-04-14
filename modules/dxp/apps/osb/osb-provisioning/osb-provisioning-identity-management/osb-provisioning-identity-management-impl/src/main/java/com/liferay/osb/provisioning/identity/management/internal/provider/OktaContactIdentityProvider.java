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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
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

			if (_isEmailAddressVerified(jsonObject.getString("status"))) {
				contact.setEmailAddressVerified(true);
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
		List<String> entitlements = new ArrayList<>();

		if (!ArrayUtil.isEmpty(contact.getEntitlements())) {
			for (Entitlement entitlement : contact.getEntitlements()) {
				entitlements.add(entitlement.getName());
			}
		}

		String response = _sendRequest(
			_URL_API_REST_USERS + contact.getEmailAddress());

		JSONObject jsonObject = _jsonFactory.createJSONObject(response);

		if (jsonObject.has("errorCode")) {
			_messagePublisher.publish(
				GooglePubsubConstants.TOPIC_OKTA_USER_CREATE,
				new Message(contact.toString()));
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

			List<String> groups = _getGroups(contact.getEmailAddress());

			if (groups.contains(OktaConstants.GROUP_NAME_CUSTOMERS) &&
				!entitlements.contains(EntitlementConstants.CUSTOMER)) {

				removeMembership(
					OktaConstants.GROUP_NAME_CUSTOMERS,
					contact.getEmailAddress());
			}

			if (groups.contains(OktaConstants.GROUP_NAME_PARTNERS) &&
				!entitlements.contains(EntitlementConstants.PARTNER)) {

				removeMembership(
					OktaConstants.GROUP_NAME_PARTNERS,
					contact.getEmailAddress());
			}
		}

		for (String entitlement : entitlements) {
			if (entitlement.equals(EntitlementConstants.CUSTOMER)) {
				addMembership(
					OktaConstants.GROUP_NAME_CUSTOMERS,
					contact.getEmailAddress());
			}
			else if (entitlement.equals(EntitlementConstants.PARTNER)) {
				addMembership(
					OktaConstants.GROUP_NAME_PARTNERS,
					contact.getEmailAddress());
			}
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

	private List<String> _getGroups(String emailAddress) throws Exception {
		List<String> groups = new ArrayList<>();

		String response = _sendRequest(
			_URL_API_REST_USERS + emailAddress + _URL_API_REST_GROUPS);

		JSONArray jsonArray = _jsonFactory.createJSONArray(response);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject groupJSONObject = jsonArray.getJSONObject(i);

			JSONObject groupProfileJSONObject = groupJSONObject.getJSONObject(
				"profile");

			groups.add(groupProfileJSONObject.getString("name"));
		}

		return groups;
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

	private static final String[] _STATUSES_DEACTIVATED = {"DEPROVISIONED"};

	private static final String[] _STATUSES_PENDING = {"PROVISIONED", "STAGED"};

	private static final String[] _STATUSES_VERIFIED = {
		"ACTIVE", "LOCKED_OUT", "PASSWORD_EXPIRED", "RECOVERY", "SUSPENDED"
	};

	private static final String _URL_API_GET_SESSION = "/api/v1/sessions/";

	private static final String _URL_API_REST_GROUPS = "/groups";

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
	private MessagePublisher _messagePublisher;

	@Reference
	private MultiVMPool _multiVMPool;

	private PortalCache<String, String> _portalCache;

	@Reference
	private UserLocalService _userLocalService;

}