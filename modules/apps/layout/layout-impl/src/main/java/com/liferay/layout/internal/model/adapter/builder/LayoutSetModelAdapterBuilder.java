/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.model.adapter.builder;

import com.liferay.layout.internal.model.adapter.StagedLayoutSetImpl;
import com.liferay.layout.set.model.adapter.StagedLayoutSet;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.adapter.builder.ModelAdapterBuilder;

import java.lang.reflect.Field;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = ModelAdapterBuilder.class)
public class LayoutSetModelAdapterBuilder
	implements ModelAdapterBuilder<StagedLayoutSet, LayoutSet> {

	@Override
	public LayoutSet build(StagedLayoutSet stagedLayoutSet) {
		Field layoutSetField = null;

		try {
			layoutSetField = ReflectionUtil.getDeclaredField(
				StagedLayoutSetImpl.class, "_layoutSet");

			return (LayoutSet)layoutSetField.get(stagedLayoutSet);
		}
		catch (Exception exception) {
			return null;
		}
	}

}