/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.management;

import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ClusterGroup;
import com.liferay.portal.kernel.util.MethodHandler;

import java.util.Iterator;
import java.util.List;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class ClusterManageActionWrapper
	implements ManageAction<FutureClusterResponses> {

	public ClusterManageActionWrapper(
		ClusterGroup clusterGroup, ManageAction<?> manageAction) {

		_clusterGroup = clusterGroup;
		_manageAction = manageAction;
	}

	@Override
	public FutureClusterResponses action() throws ManageActionException {
		try {
			return doAction();
		}
		catch (SystemException systemException) {
			throw new ManageActionException(
				"Failed to execute cluster manage action", systemException);
		}
	}

	protected FutureClusterResponses doAction() throws ManageActionException {
		MethodHandler manageActionMethodHandler =
			PortalManagerUtil.createManageActionMethodHandler(_manageAction);

		ClusterRequest clusterRequest = null;

		if (_clusterGroup.isWholeCluster()) {
			clusterRequest = ClusterRequest.createMulticastRequest(
				manageActionMethodHandler);
		}
		else {
			verifyClusterGroup();

			clusterRequest = ClusterRequest.createUnicastRequest(
				manageActionMethodHandler,
				_clusterGroup.getClusterNodeIdsArray());
		}

		return ClusterExecutorUtil.execute(clusterRequest);
	}

	protected void verifyClusterGroup() throws ManageActionException {
		List<ClusterNode> clusterNodes = ClusterExecutorUtil.getClusterNodes();

		String[] requiredClusterNodesIds =
			_clusterGroup.getClusterNodeIdsArray();

		for (String requiredClusterNodeId : requiredClusterNodesIds) {
			boolean verified = false;

			Iterator<ClusterNode> itr = clusterNodes.iterator();

			while (itr.hasNext()) {
				ClusterNode clusterNode = itr.next();

				String clusterNodeId = clusterNode.getClusterNodeId();

				if (clusterNodeId.equals(requiredClusterNodeId)) {
					itr.remove();

					verified = true;

					break;
				}
			}

			if (!verified) {
				throw new ManageActionException(
					"Cluster node " + requiredClusterNodeId +
						" is not available");
			}
		}
	}

	private final ClusterGroup _clusterGroup;
	private final ManageAction<?> _manageAction;

}