/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SyncDLFileVersionDiff service. Represents a row in the &quot;SyncDLFileVersionDiff&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.sync.model.impl.SyncDLFileVersionDiffImpl"
)
@ProviderType
public interface SyncDLFileVersionDiff
	extends PersistedModel, SyncDLFileVersionDiffModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.sync.model.impl.SyncDLFileVersionDiffImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SyncDLFileVersionDiff, Long>
		SYNC_DL_FILE_VERSION_DIFF_ID_ACCESSOR =
			new Accessor<SyncDLFileVersionDiff, Long>() {

				@Override
				public Long get(SyncDLFileVersionDiff syncDLFileVersionDiff) {
					return syncDLFileVersionDiff.getSyncDLFileVersionDiffId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<SyncDLFileVersionDiff> getTypeClass() {
					return SyncDLFileVersionDiff.class;
				}

			};

}