/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 */
public interface PollerResponse extends Serializable {

	public static final String POLLER_HINT_HIGH_CONNECTIVITY =
		"pollerHintHighConnectivity";

	public void close(
		Message message, PollerHeader pollerHeader, String portletId,
		String chunkId);

	public PollerHeader getPollerHeader();

	public String getPortletId();

	public boolean isEmpty();

	public void setParameter(String name, JSONArray value)
		throws PollerResponseClosedException;

	public void setParameter(String name, JSONObject value)
		throws PollerResponseClosedException;

	public void setParameter(String name, String value)
		throws PollerResponseClosedException;

	public JSONObject toJSONObject();

}