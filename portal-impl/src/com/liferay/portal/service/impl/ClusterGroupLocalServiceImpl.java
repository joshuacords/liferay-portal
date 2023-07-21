/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.model.ClusterGroup;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.base.ClusterGroupLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ClusterGroupLocalServiceImpl
	extends ClusterGroupLocalServiceBaseImpl {

	@Override
	public ClusterGroup addClusterGroup(
		String name, List<String> clusterNodeIds) {

		long clusterGroupId = counterLocalService.increment();

		ClusterGroup clusterGroup = clusterGroupPersistence.create(
			clusterGroupId);

		clusterGroup.setName(name);
		clusterGroup.setClusterNodeIds(StringUtil.merge(clusterNodeIds));

		return clusterGroupPersistence.update(clusterGroup);
	}

	@Override
	public ClusterGroup addWholeClusterGroup(String name) {
		long clusterGroupId = counterLocalService.increment();

		ClusterGroup clusterGroup = clusterGroupPersistence.create(
			clusterGroupId);

		clusterGroup.setName(name);
		clusterGroup.setWholeCluster(true);

		return clusterGroupPersistence.update(clusterGroup);
	}

}