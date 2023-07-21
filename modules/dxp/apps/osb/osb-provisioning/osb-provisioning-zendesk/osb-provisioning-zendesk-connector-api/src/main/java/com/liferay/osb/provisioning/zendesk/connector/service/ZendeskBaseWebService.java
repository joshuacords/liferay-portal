/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.connector.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

/**
 * @author Kyle Bischof
 */
public interface ZendeskBaseWebService {

	public JSONObject delete(String url, Map<String, String> parameters)
		throws PortalException;

	public JSONObject delete(String endpoint, String json)
		throws PortalException;

	public JSONObject get(String url, Map<String, String> parameters)
		throws PortalException;

	public JSONObject get(String endpoint, String json) throws PortalException;

	public JSONObject post(
			String endpoint, Map<String, String> params, String fileName,
			byte[] bytes)
		throws PortalException;

	public JSONObject post(String endpoint, String json) throws PortalException;

	public JSONObject put(String endpoint, String json) throws PortalException;

	public JSONObject send(ZendeskRequest zendeskRequest)
		throws PortalException;

}