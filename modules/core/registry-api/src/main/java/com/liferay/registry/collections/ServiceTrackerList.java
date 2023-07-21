/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.collections;

import java.io.Closeable;

import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public interface ServiceTrackerList<S> extends Closeable, List<S> {

	public boolean add(S service, Map<String, Object> properties);

	@Override
	public void close();

}