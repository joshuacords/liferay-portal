/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerRequest implements Serializable {

	public PollerRequest(
		PollerHeader pollerHeader, String portletId,
		Map<String, String> parameterMap, String chunkId,
		boolean receiveRequest) {

		_pollerHeader = pollerHeader;
		_portletId = portletId;
		_parameterMap = parameterMap;
		_chunkId = chunkId;
		_receiveRequest = receiveRequest;
	}

	public PollerResponse createPollerResponse() {
		return new DefaultPollerResponse();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PollerRequest)) {
			return false;
		}

		PollerRequest portletRequest = (PollerRequest)obj;

		if (Objects.equals(_portletId, portletRequest._portletId)) {
			return true;
		}

		return false;
	}

	public long getBrowserKey() {
		return _pollerHeader.getBrowserKey();
	}

	public String getChunkId() {
		return _chunkId;
	}

	public long getCompanyId() {
		return _pollerHeader.getCompanyId();
	}

	public Map<String, String> getParameterMap() {
		return _parameterMap;
	}

	public PollerHeader getPollerHeader() {
		return _pollerHeader;
	}

	public String getPortletId() {
		return _portletId;
	}

	public Set<String> getPortletIds() {
		Map<String, Boolean> portletIdsMap = _pollerHeader.getPortletIdsMap();

		return portletIdsMap.keySet();
	}

	public long getTimestamp() {
		return _pollerHeader.getTimestamp();
	}

	public long getUserId() {
		return _pollerHeader.getUserId();
	}

	@Override
	public int hashCode() {
		if (_portletId != null) {
			return _portletId.hashCode();
		}

		return 0;
	}

	public boolean isInitialRequest() {
		Map<String, Boolean> portletIdsMap = _pollerHeader.getPortletIdsMap();

		return portletIdsMap.get(_portletId);
	}

	public boolean isReceiveRequest() {
		return _receiveRequest;
	}

	public boolean isStartPolling() {
		return _pollerHeader.isStartPolling();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{chunkId=");
		sb.append(_chunkId);
		sb.append(", parameterMap=");
		sb.append(_parameterMap);
		sb.append(", pollerHeader=");
		sb.append(_pollerHeader);
		sb.append(", portletId=");
		sb.append(_portletId);
		sb.append(", receiveRequest=");
		sb.append(_receiveRequest);
		sb.append("}");

		return sb.toString();
	}

	private final String _chunkId;
	private final Map<String, String> _parameterMap;
	private final PollerHeader _pollerHeader;
	private final String _portletId;
	private final boolean _receiveRequest;

}