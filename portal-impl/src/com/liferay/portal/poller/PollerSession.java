/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller;

import com.liferay.portal.kernel.poller.PollerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edward Han
 */
public class PollerSession {

	public PollerSession(String pollerSessionId) {
		_pollerSessionId = pollerSessionId;
	}

	public synchronized boolean beginPortletProcessing(
		PollerRequest pollerRequest, String responseId) {

		String portletId = pollerRequest.getPortletId();

		// Do not process a new request if there is a request already pending.
		// This prevents flooding the server in the event of slow receive
		// requests.

		if (_pendingResponseIds.containsKey(portletId)) {
			return false;
		}

		_pendingResponseIds.put(portletId, responseId);

		_pollerRequests.put(portletId, pollerRequest);

		return true;
	}

	public synchronized boolean completePortletProcessing(
		String portletId, String responseId) {

		String pendingResponseId = _pendingResponseIds.get(portletId);

		if (responseId.equals(pendingResponseId)) {
			_pendingResponseIds.remove(portletId);

			_pollerRequests.remove(portletId);
		}

		return _pendingResponseIds.isEmpty();
	}

	public String getPollerSessionId() {
		return _pollerSessionId;
	}

	private final Map<String, String> _pendingResponseIds = new HashMap<>();
	private final Map<String, PollerRequest> _pollerRequests = new HashMap<>();
	private final String _pollerSessionId;

}