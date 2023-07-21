/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ResourceBlock;
import com.liferay.portal.kernel.model.ResourceConstants;

/**
 * @author     Preston Crary
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ResourceBlockModelListener
	extends BaseModelListener<ResourceBlock> {

	@Override
	public void onAfterCreate(ResourceBlock resourceBlock) {
		_clearCache(resourceBlock);
	}

	@Override
	public void onAfterRemove(ResourceBlock resourceBlock) {
		_clearCache(resourceBlock);
	}

	@Override
	public void onAfterUpdate(ResourceBlock resourceBlock) {
		_clearCache(resourceBlock);
	}

	@Override
	public void onBeforeUpdate(ResourceBlock resourceBlock) {
	}

	private void _clearCache(ResourceBlock resourceBlock) {
		if (resourceBlock != null) {
			PermissionCacheUtil.clearResourcePermissionCache(
				ResourceConstants.SCOPE_INDIVIDUAL, resourceBlock.getName(),
				String.valueOf(resourceBlock.getPrimaryKey()));
		}
	}

}