/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class ClusterGroupImpl extends ClusterGroupBaseImpl {

	@Override
	public String[] getClusterNodeIdsArray() {
		return StringUtil.split(getClusterNodeIds());
	}

}