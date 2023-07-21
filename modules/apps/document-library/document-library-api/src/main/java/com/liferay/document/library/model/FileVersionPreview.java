/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.model;

import org.osgi.annotation.versioning.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the FileVersionPreview service. Represents a row in the &quot;FileVersionPreview&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FileVersionPreviewModel
 * @see com.liferay.document.library.model.impl.FileVersionPreviewImpl
 * @see com.liferay.document.library.model.impl.FileVersionPreviewModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.document.library.model.impl.FileVersionPreviewImpl")
@ProviderType
public interface FileVersionPreview extends FileVersionPreviewModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.document.library.model.impl.FileVersionPreviewImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FileVersionPreview, Long> FILE_VERSION_PREVIEW_ID_ACCESSOR =
		new Accessor<FileVersionPreview, Long>() {
			@Override
			public Long get(FileVersionPreview fileVersionPreview) {
				return fileVersionPreview.getFileVersionPreviewId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<FileVersionPreview> getTypeClass() {
				return FileVersionPreview.class;
			}
		};
}