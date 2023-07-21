/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.model.ResourceTypePermission;
import com.liferay.portal.kernel.service.persistence.ResourceTypePermissionPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @deprecated
 * @generated
 */
@Deprecated
public class ResourceTypePermissionFinderBaseImpl
	extends BasePersistenceImpl<ResourceTypePermission> {

	public ResourceTypePermissionFinderBaseImpl() {
		setModelClass(ResourceTypePermission.class);
	}

	/**
	 * Returns the resource type permission persistence.
	 *
	 * @return the resource type permission persistence
	 */
	public ResourceTypePermissionPersistence
		getResourceTypePermissionPersistence() {

		return resourceTypePermissionPersistence;
	}

	/**
	 * Sets the resource type permission persistence.
	 *
	 * @param resourceTypePermissionPersistence the resource type permission persistence
	 */
	public void setResourceTypePermissionPersistence(
		ResourceTypePermissionPersistence resourceTypePermissionPersistence) {

		this.resourceTypePermissionPersistence =
			resourceTypePermissionPersistence;
	}

	@BeanReference(type = ResourceTypePermissionPersistence.class)
	protected ResourceTypePermissionPersistence
		resourceTypePermissionPersistence;

}