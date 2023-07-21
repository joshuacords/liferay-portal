/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.management;

import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.LoggedExceptionInInitializerError;
import com.liferay.portal.kernel.model.ClusterGroup;
import com.liferay.portal.kernel.service.ClusterGroupLocalServiceUtil;
import com.liferay.portal.kernel.util.MethodHandler;

import java.lang.reflect.Method;

/**
 * @author     Shuyang Zhou
 * @author     Raymond Augé
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class PortalManagerUtil {

	public static MethodHandler createManageActionMethodHandler(
		ManageAction<?> manageAction) {

		return new MethodHandler(_MANAGE_METHOD, manageAction);
	}

	public static PortalManager getPortalManager() {
		return _portalManager;
	}

	public static FutureClusterResponses manage(
			ClusterGroup clusterGroup, ManageAction<?> manageAction)
		throws ManageActionException {

		ManageAction<FutureClusterResponses> manageActionWrapper =
			new ClusterManageActionWrapper(clusterGroup, manageAction);

		return getPortalManager().manage(manageActionWrapper);
	}

	public static <T> T manage(ManageAction<T> manageAction)
		throws ManageActionException {

		return getPortalManager().manage(manageAction);
	}

	public static void manageAsync(
			ClusterNode clusterNode, ManageAction<?> manageAction)
		throws Exception {

		ClusterGroup clusterGroup =
			ClusterGroupLocalServiceUtil.createClusterGroup(0);

		clusterGroup.setClusterNodeIds(clusterNode.getClusterNodeId());

		manage(clusterGroup, manageAction);
	}

	public static <T> T manageSync(
			ClusterNode clusterNode, ManageAction<T> manageAction)
		throws Exception {

		ClusterGroup clusterGroup =
			ClusterGroupLocalServiceUtil.createClusterGroup(0);

		clusterGroup.setClusterNodeIds(clusterNode.getClusterNodeId());

		FutureClusterResponses futureClusterResponses = manage(
			clusterGroup, manageAction);

		ClusterNodeResponses clusterNodeResponses =
			futureClusterResponses.get();

		ClusterNodeResponse clusterNodeResponse =
			clusterNodeResponses.getClusterResponse(
				clusterNode.getClusterNodeId());

		return (T)clusterNodeResponse.getResult();
	}

	public void setPortalManager(PortalManager portalManager) {
		_portalManager = portalManager;
	}

	private static final Method _MANAGE_METHOD;

	private static PortalManager _portalManager;

	static {
		try {
			_MANAGE_METHOD = PortalManagerUtil.class.getDeclaredMethod(
				"manage", ManageAction.class);
		}
		catch (Exception exception) {
			throw new LoggedExceptionInInitializerError(exception);
		}
	}

}