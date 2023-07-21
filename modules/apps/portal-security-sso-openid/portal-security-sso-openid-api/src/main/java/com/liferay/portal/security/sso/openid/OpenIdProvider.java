/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class OpenIdProvider {

	public String[] getAxSchema() {
		return _axSchema;
	}

	public Map<String, String> getAxTypes() {
		return _axTypes;
	}

	public String getName() {
		return _name;
	}

	public String getUrl() {
		return _url;
	}

	public void setAxSchema(String[] axSchema) {
		_axSchema = axSchema;
	}

	public void setAxTypes(String name, String value) {
		_axTypes.put(name, value);
	}

	public void setName(String name) {
		_name = name;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private String[] _axSchema;
	private final Map<String, String> _axTypes = new HashMap<>();
	private String _name;
	private String _url;

}