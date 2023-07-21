/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.kernel.model;

import org.osgi.annotation.versioning.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TrashVersion service. Represents a row in the &quot;TrashVersion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TrashVersionModel
 * @see com.liferay.portlet.trash.model.impl.TrashVersionImpl
 * @see com.liferay.portlet.trash.model.impl.TrashVersionModelImpl
 * @deprecated As of Judson (7.1.x), replaced by {@link
com.liferay.trash.model.impl.TrashVersionImpl}
 * @generated
 */
@Deprecated
@ImplementationClassName("com.liferay.portlet.trash.model.impl.TrashVersionImpl")
@ProviderType
public interface TrashVersion extends TrashVersionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.trash.model.impl.TrashVersionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TrashVersion, Long> VERSION_ID_ACCESSOR = new Accessor<TrashVersion, Long>() {
			@Override
			public Long get(TrashVersion trashVersion) {
				return trashVersion.getVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TrashVersion> getTypeClass() {
				return TrashVersion.class;
			}
		};

	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties();

	public java.lang.String getTypeSettingsProperty(java.lang.String key);

	public java.lang.String getTypeSettingsProperty(java.lang.String key,
		java.lang.String defaultValue);

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties);
}