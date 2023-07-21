/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cluster.multiple.internal;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cluster.ClusterEvent;
import com.liferay.portal.kernel.cluster.ClusterEventListener;
import com.liferay.portal.kernel.cluster.ClusterEventType;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

/**
 * @author Tina Tian
 */
public class DebuggingClusterEventListenerImpl implements ClusterEventListener {

	@Override
	public void processClusterEvent(ClusterEvent clusterEvent) {
		if (!_log.isInfoEnabled()) {
			return;
		}

		ClusterEventType clusterEventType = clusterEvent.getClusterEventType();

		List<ClusterNode> clusterNodes = clusterEvent.getClusterNodes();

		StringBundler sb = new StringBundler((clusterNodes.size() * 3) + 3);

		sb.append("Cluster event ");
		sb.append(clusterEventType);
		sb.append(StringPool.NEW_LINE);

		for (ClusterNode clusterNode : clusterNodes) {
			sb.append("Cluster node ");
			sb.append(clusterNode);
			sb.append(StringPool.NEW_LINE);
		}

		sb.setIndex(sb.index() - 1);

		_log.info(sb.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DebuggingClusterEventListenerImpl.class);

}