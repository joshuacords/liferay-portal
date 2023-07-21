/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal;

import com.liferay.batch.engine.ItemClassRegistry;
import com.liferay.batch.engine.internal.item.BatchEngineTaskItemDelegateExecutorCreator;

/**
 * @author Ivica Cardic
 */
public interface BatchEngineTaskMethodRegistry extends ItemClassRegistry {

	public BatchEngineTaskItemDelegateExecutorCreator
		getBatchEngineTaskItemDelegateExecutorCreator(
			String itemClassName, String taskItemDelegateName);

}