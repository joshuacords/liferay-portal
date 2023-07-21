/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.ResourceBlock;
import com.liferay.portal.kernel.service.ResourceBlockLocalServiceUtil;

/**
 * @author     Alberto Chaparro
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ResourceBlockTestUtil {

	public static ResourceBlock addResourceBlock(long groupId, String name)
		throws Exception {

		long resourceBlockId = CounterLocalServiceUtil.increment(
			ResourceBlock.class.getName());

		ResourceBlock resourceBlock =
			ResourceBlockLocalServiceUtil.createResourceBlock(resourceBlockId);

		resourceBlock.setCompanyId(TestPropsValues.getCompanyId());
		resourceBlock.setGroupId(groupId);
		resourceBlock.setName(name);
		resourceBlock.setPermissionsHash(RandomTestUtil.randomString());
		resourceBlock.setReferenceCount(0);

		return ResourceBlockLocalServiceUtil.addResourceBlock(resourceBlock);
	}

	public static ResourceBlock addResourceBlock(String name) throws Exception {
		return addResourceBlock(RandomTestUtil.nextLong(), name);
	}

}