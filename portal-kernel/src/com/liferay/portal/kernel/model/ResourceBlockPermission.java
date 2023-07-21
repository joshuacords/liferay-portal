/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ResourceBlockPermission service. Represents a row in the &quot;ResourceBlockPermission&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockPermissionModel
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
@ImplementationClassName(
	"com.liferay.portal.model.impl.ResourceBlockPermissionImpl"
)
@ProviderType
public interface ResourceBlockPermission
	extends PermissionedModel, ResourceBlockPermissionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.model.impl.ResourceBlockPermissionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ResourceBlockPermission, Long>
		RESOURCE_BLOCK_PERMISSION_ID_ACCESSOR =
			new Accessor<ResourceBlockPermission, Long>() {

				@Override
				public Long get(
					ResourceBlockPermission resourceBlockPermission) {

					return resourceBlockPermission.
						getResourceBlockPermissionId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ResourceBlockPermission> getTypeClass() {
					return ResourceBlockPermission.class;
				}

			};

}