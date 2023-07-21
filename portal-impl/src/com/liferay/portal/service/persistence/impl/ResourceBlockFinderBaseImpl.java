/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.model.ResourceBlock;
import com.liferay.portal.kernel.service.persistence.ResourceBlockPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ResourceBlockFinderBaseImpl
	extends BasePersistenceImpl<ResourceBlock> {

	public ResourceBlockFinderBaseImpl() {
		setModelClass(ResourceBlock.class);
	}

	/**
	 * Returns the resource block persistence.
	 *
	 * @return the resource block persistence
	 */
	public ResourceBlockPersistence getResourceBlockPersistence() {
		return resourceBlockPersistence;
	}

	/**
	 * Sets the resource block persistence.
	 *
	 * @param resourceBlockPersistence the resource block persistence
	 */
	public void setResourceBlockPersistence(
		ResourceBlockPersistence resourceBlockPersistence) {

		this.resourceBlockPersistence = resourceBlockPersistence;
	}

	@BeanReference(type = ResourceBlockPersistence.class)
	protected ResourceBlockPersistence resourceBlockPersistence;

}