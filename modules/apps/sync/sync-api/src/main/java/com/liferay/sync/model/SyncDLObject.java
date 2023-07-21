/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.TreeModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SyncDLObject service. Represents a row in the &quot;SyncDLObject&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectModel
 * @generated
 */
@ImplementationClassName("com.liferay.sync.model.impl.SyncDLObjectImpl")
@ProviderType
public interface SyncDLObject
	extends PersistedModel, SyncDLObjectModel, TreeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.sync.model.impl.SyncDLObjectImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SyncDLObject, Long>
		SYNC_DL_OBJECT_ID_ACCESSOR = new Accessor<SyncDLObject, Long>() {

			@Override
			public Long get(SyncDLObject syncDLObject) {
				return syncDLObject.getSyncDLObjectId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SyncDLObject> getTypeClass() {
				return SyncDLObject.class;
			}

		};

	@Override
	public String buildTreePath();

	public void setCreateDate(java.util.Date createDate);

	public void setModifiedDate(java.util.Date modifiedDate);

}