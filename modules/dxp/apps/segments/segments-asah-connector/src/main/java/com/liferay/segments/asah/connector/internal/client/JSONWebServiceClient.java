/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.client;

import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

/**
 * @author David Arques
 */
public interface JSONWebServiceClient {

	public String doDelete(
		String url, Map<String, String> parameters,
		Map<String, String> headers);

	public String doGet(
		String url, MultivaluedMap<String, Object> parameters,
		Map<String, String> headers);

	public <T> void doPatch(String url, T object, Map<String, String> headers);

	public <T, V> V doPost(
		Class<V> clazz, String url, T object, Map<String, String> headers);

	public <T> void doPut(String url, T object, Map<String, String> headers);

	public String getBaseURI();

	public void setBaseURI(String baseURI);

}