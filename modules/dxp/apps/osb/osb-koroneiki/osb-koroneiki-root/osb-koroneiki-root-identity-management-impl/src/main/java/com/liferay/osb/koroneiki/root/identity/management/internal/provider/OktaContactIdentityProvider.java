/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.identity.management.internal.provider;

import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.exception.ContactEmailAddressException;
import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactException;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
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

		Contact contact = _contactLocalService.fetchContactByEmailAddress(
			emailAddress);

		if (contact == null) {
			contact = _importContactByEmailAddress(emailAddress);
		}

		return contact;
	}

	public Contact fetchContactByUuid(String uuid) throws Exception {
		Contact contact = _contactLocalService.fetchContactByUuid(uuid);

		if (contact == null) {
			contact = _importContactByUuid(uuid);
		}

		return contact;
	}

	public Contact getContactByEmailAddress(String emailAddress)
		throws Exception {

		Contact contact = fetchContactByEmailAddress(emailAddress);

		if (contact == null) {
			throw new NoSuchContactException();
		}

		return contact;
	}

	public Contact getContactByUuid(String uuid) throws Exception {
		Contact contact = fetchContactByUuid(uuid);

		if (contact == null) {
			throw new NoSuchContactException();
		}

		return contact;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_apiToken = String.valueOf(properties.get("api.token"));
		_host = String.valueOf(properties.get("host"));
	}

	private long _getDefaultUserId() throws PortalException {
		if (_defaultUserId <= 0) {
			long companyId = _portalInstancesLocalService.getDefaultCompanyId();

			User user = _userLocalService.getDefaultUser(companyId);

			_defaultUserId = user.getUserId();
		}

		return _defaultUserId;
	}

	private Contact _importContactByEmailAddress(String emailAddress)
		throws Exception {

		String response = _sendRequest(_URL_API_REST_USERS + emailAddress);

		JSONObject jsonObject = _jsonFactory.createJSONObject(response);

		if (jsonObject.has("errorCode")) {
			return null;
		}

		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		try {
			return _contactLocalService.addContact(
				profileJSONObject.getString("uuid"), _getDefaultUserId(),
				profileJSONObject.getString("firstName"),
				profileJSONObject.getString("middleName"),
				profileJSONObject.getString("lastName"), emailAddress,
				LocaleUtil.toLanguageId(LocaleUtil.US),
				_isEmailAddressVerified(jsonObject));
		}
		catch (Exception exception) {
			if (exception instanceof ConstraintViolationException ||
				exception instanceof ContactEmailAddressException) {

				return fetchContactByEmailAddress(emailAddress);
			}

			_log.error(exception, exception);
		}

		return null;
	}

	private Contact _importContactByUuid(String uuid) throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append(_URL_API_REST_USERS);
		sb.append("?search=");
		sb.append(_http.encodePath("profile.uuid eq \"" + uuid + "\""));

		String response = _sendRequest(sb.toString());

		JSONArray jsonArray = _jsonFactory.createJSONArray(response);

		if (jsonArray.length() <= 0) {
			return null;
		}

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		try {
			return _contactLocalService.addContact(
				uuid, _getDefaultUserId(),
				profileJSONObject.getString("firstName"),
				profileJSONObject.getString("middleName"),
				profileJSONObject.getString("lastName"),
				profileJSONObject.getString("email"),
				LocaleUtil.toLanguageId(LocaleUtil.US),
				_isEmailAddressVerified(jsonObject));
		}
		catch (Exception exception) {
			if (exception instanceof ConstraintViolationException ||
				exception instanceof ContactEmailAddressException) {

				return fetchContactByUuid(uuid);
			}

			_log.error(exception, exception);
		}

		return null;
	}

	private boolean _isEmailAddressVerified(JSONObject jsonObject)
		throws Exception {

		String status = jsonObject.getString("status");

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

	private static final String[] _STATUSES_VERIFIED = {
		"ACTIVE", "LOCKED_OUT", "PASSWORD_EXPIRED", "RECOVERY", "SUSPENDED"
	};

	private static final String _URL_API_REST_USERS = "/api/v1/users/";

	private static final Log _log = LogFactoryUtil.getLog(
		OktaContactIdentityProvider.class);

	private String _apiToken;

	@Reference
	private ContactLocalService _contactLocalService;

	private long _defaultUserId;
	private String _host;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	@Reference
	private UserLocalService _userLocalService;

}