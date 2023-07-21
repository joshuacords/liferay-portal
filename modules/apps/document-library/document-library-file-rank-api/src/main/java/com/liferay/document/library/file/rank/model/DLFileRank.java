/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.file.rank.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the DLFileRank service. Represents a row in the &quot;DLFileRank&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileRankModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.document.library.file.rank.model.impl.DLFileRankImpl"
)
@ProviderType
public interface DLFileRank extends DLFileRankModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.document.library.file.rank.model.impl.DLFileRankImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DLFileRank, Long> FILE_RANK_ID_ACCESSOR =
		new Accessor<DLFileRank, Long>() {

			@Override
			public Long get(DLFileRank dlFileRank) {
				return dlFileRank.getFileRankId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DLFileRank> getTypeClass() {
				return DLFileRank.class;
			}

		};

}