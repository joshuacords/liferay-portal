/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a list resource block IDs and the actions that can be performed on
 * the resources in each.
 *
 * @author     Connor McKay
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ResourceBlockIdsBag implements Serializable {

	public void addResourceBlockId(long resourceBlockId, long actionIdsLong) {
		actionIdsLong |= getActionIds(resourceBlockId);

		_permissions.put(resourceBlockId, actionIdsLong);
	}

	public long getActionIds(long resourceBlockId) {
		Long oldActionIdsLong = _permissions.get(resourceBlockId);

		if (oldActionIdsLong == null) {
			oldActionIdsLong = 0L;
		}

		return oldActionIdsLong;
	}

	public List<Long> getResourceBlockIds(long actionIdsLong) {
		List<Long> resourceBlockIds = new ArrayList<>();

		for (Map.Entry<Long, Long> permission : _permissions.entrySet()) {
			if ((permission.getValue() & actionIdsLong) == actionIdsLong) {
				resourceBlockIds.add(permission.getKey());
			}
		}

		return resourceBlockIds;
	}

	public boolean hasResourceBlockId(
		long resourceBlockId, long actionIdsLong) {

		if ((getActionIds(resourceBlockId) & actionIdsLong) == actionIdsLong) {
			return true;
		}

		return false;
	}

	private final Map<Long, Long> _permissions = new HashMap<>();

}