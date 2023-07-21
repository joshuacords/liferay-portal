/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Amos Fong
 * @author Kyle Bischof
 */
public class ZendeskUser {

	public ZendeskUser() {
	}

	public String getEmail() {
		return _email;
	}

	public String getExternalId() {
		return _externalId;
	}

	public String getLocale() {
		return _locale;
	}

	public String getName() {
		return _name;
	}

	public Set<String> getTags() {
		return _tags;
	}

	public long getZendeskUserId() {
		return _zendeskUserId;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setExternalId(String externalId) {
		_externalId = externalId;
	}

	public void setLocale(String locale) {
		_locale = locale;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setTags(Set<String> tags) {
		_tags = tags;
	}

	public void setZendeskUserId(long zendeskUserId) {
		_zendeskUserId = zendeskUserId;
	}

	private String _email;
	private String _externalId;
	private String _locale;
	private String _name;
	private Set<String> _tags = new HashSet<>();
	private long _zendeskUserId;

}