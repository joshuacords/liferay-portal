/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.adapter.builder;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.adapter.StagedGroup;
import com.liferay.portal.kernel.model.adapter.builder.ModelAdapterBuilder;
import com.liferay.portal.model.adapter.impl.StagedGroupImpl;

/**
 * @author     Máté Thurzó
 * @deprecated As of Judson (7.1.x)
 */
@Deprecated
public class StagedGroupModelAdapterBuilder
	implements ModelAdapterBuilder<Group, StagedGroup> {

	@Override
	public StagedGroup build(Group group) {
		return new StagedGroupImpl(group);
	}

}