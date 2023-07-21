/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.cluster;

import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterNode;

import java.net.InetAddress;
import java.net.NetworkInterface;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = ClusterSettingsContext.class)
public class ClusterExecutorClusterSettingsContext
	implements ClusterSettingsContext {

	@Override
	public String[] getHosts() {
		List<ClusterNode> clusterNodes = _clusterExecutor.getClusterNodes();

		String[] hosts = new String[clusterNodes.size()];

		for (int i = 0; i < hosts.length; i++) {
			ClusterNode clusterNode = clusterNodes.get(i);

			InetAddress bindInetAddress = clusterNode.getBindInetAddress();

			hosts[i] = bindInetAddress.getHostAddress();
		}

		return hosts;
	}

	@Override
	public InetAddress getLocalBindInetAddress() {
		return _clusterExecutor.getBindInetAddress();
	}

	@Override
	public NetworkInterface getLocalBindNetworkInterface() {
		return _clusterExecutor.getBindNetworkInterface();
	}

	@Override
	public boolean isClusterEnabled() {
		return _clusterExecutor.isEnabled();
	}

	@Reference
	private ClusterExecutor _clusterExecutor;

}