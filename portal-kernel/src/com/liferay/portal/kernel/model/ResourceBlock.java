/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ResourceBlock service. Represents a row in the &quot;ResourceBlock&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockModel
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
@ImplementationClassName("com.liferay.portal.model.impl.ResourceBlockImpl")
@ProviderType
public interface ResourceBlock extends PermissionedModel, ResourceBlockModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.model.impl.ResourceBlockImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ResourceBlock, Long>
		RESOURCE_BLOCK_ID_ACCESSOR = new Accessor<ResourceBlock, Long>() {

			@Override
			public Long get(ResourceBlock resourceBlock) {
				return resourceBlock.getResourceBlockId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ResourceBlock> getTypeClass() {
				return ResourceBlock.class;
			}

		};

}