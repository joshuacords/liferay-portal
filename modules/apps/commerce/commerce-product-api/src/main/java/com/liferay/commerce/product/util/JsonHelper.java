/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 */
@ProviderType
public interface JsonHelper {

	public boolean equals(String json1, String json2);

	public String getFirstElementStringValue(String jsonArrayString);

	public JSONArray getJSONArray(String json) throws JSONException;

	public JSONArray getValueAsJSONArray(String key, JSONObject jsonObject);

	public boolean isArray(String json);

	public boolean isEmpty(String json);

	public JSONArray toJSONArray(Map<String, List<String>> keyValues);

}