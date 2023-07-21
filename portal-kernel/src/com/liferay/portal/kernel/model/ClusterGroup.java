/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ClusterGroup service. Represents a row in the &quot;ClusterGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ClusterGroupModel
 * @deprecated
 * @generated
 */
@Deprecated
@ImplementationClassName("com.liferay.portal.model.impl.ClusterGroupImpl")
@ProviderType
public interface ClusterGroup extends ClusterGroupModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.model.impl.ClusterGroupImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ClusterGroup, Long> CLUSTER_GROUP_ID_ACCESSOR =
		new Accessor<ClusterGroup, Long>() {

			@Override
			public Long get(ClusterGroup clusterGroup) {
				return clusterGroup.getClusterGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ClusterGroup> getTypeClass() {
				return ClusterGroup.class;
			}

		};

	public String[] getClusterNodeIdsArray();

}