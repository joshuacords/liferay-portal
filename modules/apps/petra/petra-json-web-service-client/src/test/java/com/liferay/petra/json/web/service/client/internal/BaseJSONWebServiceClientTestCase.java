/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.json.web.service.client.internal;

import com.liferay.petra.json.web.service.client.server.simulator.HTTPServerSimulator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public abstract class BaseJSONWebServiceClientTestCase {

	protected Map<String, Object> getBaseProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();

		properties.put("hostName", HTTPServerSimulator.HOST_ADDRESS);
		properties.put("hostPort", HTTPServerSimulator.HOST_PORT);
		properties.put("protocol", "http");

		return properties;
	}

}