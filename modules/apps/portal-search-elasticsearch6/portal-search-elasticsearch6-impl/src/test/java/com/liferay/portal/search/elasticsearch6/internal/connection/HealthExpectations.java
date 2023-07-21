/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.connection;

import com.liferay.petra.string.StringBundler;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;

/**
 * @author André de Oliveira
 */
public class HealthExpectations {

	public HealthExpectations() {
	}

	public HealthExpectations(ClusterHealthResponse clusterHealthResponse) {
		_activePrimaryShards = clusterHealthResponse.getActivePrimaryShards();
		_activeShards = clusterHealthResponse.getActiveShards();
		_numberOfDataNodes = clusterHealthResponse.getNumberOfDataNodes();
		_numberOfNodes = clusterHealthResponse.getNumberOfNodes();
		_status = clusterHealthResponse.getStatus();
		_timedOut = clusterHealthResponse.isTimedOut();
		_unassignedShards = clusterHealthResponse.getUnassignedShards();
	}

	public int getActivePrimaryShards() {
		return _activePrimaryShards;
	}

	public int getActiveShards() {
		return _activeShards;
	}

	public int getNumberOfDataNodes() {
		return _numberOfDataNodes;
	}

	public int getNumberOfNodes() {
		return _numberOfNodes;
	}

	public ClusterHealthStatus getStatus() {
		return _status;
	}

	public int getUnassignedShards() {
		return _unassignedShards;
	}

	public boolean isTimedOut() {
		return _timedOut;
	}

	public void setActivePrimaryShards(int activePrimaryShards) {
		_activePrimaryShards = activePrimaryShards;
	}

	public void setActiveShards(int activeShards) {
		_activeShards = activeShards;
	}

	public void setNumberOfDataNodes(int numberOfDataNodes) {
		_numberOfDataNodes = numberOfDataNodes;
	}

	public void setNumberOfNodes(int numberOfNodes) {
		_numberOfNodes = numberOfNodes;
	}

	public void setStatus(ClusterHealthStatus status) {
		_status = status;
	}

	public void setTimedOut(boolean timedOut) {
		_timedOut = timedOut;
	}

	public void setUnassignedShards(int unassignedShards) {
		_unassignedShards = unassignedShards;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{activePrimaryShards=");
		sb.append(_activePrimaryShards);
		sb.append(", activeShards=");
		sb.append(_activeShards);
		sb.append(", numberOfDataNodes=");
		sb.append(_numberOfDataNodes);
		sb.append(", numberOfNodes=");
		sb.append(_numberOfNodes);
		sb.append(", status=");
		sb.append(_status);
		sb.append(", timedOut=");
		sb.append(_timedOut);
		sb.append(", unassignedShards=");
		sb.append(_unassignedShards);
		sb.append("}");

		return sb.toString();
	}

	private int _activePrimaryShards;
	private int _activeShards;
	private int _numberOfDataNodes;
	private int _numberOfNodes;
	private ClusterHealthStatus _status;
	private boolean _timedOut;
	private int _unassignedShards;

}