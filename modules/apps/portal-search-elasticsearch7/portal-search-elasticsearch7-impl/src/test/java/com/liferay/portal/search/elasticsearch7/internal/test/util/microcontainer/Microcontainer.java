/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.test.util.microcontainer;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author André de Oliveira
 */
public interface Microcontainer {

	public void deploy(Object... components);

	public void start();

	public <T> void wire(
		Class<T> clazz, BiConsumer<T, Map>... referencePropsBiConsumers);

	public <T> void wire(Class<T> clazz, Consumer<T>... referenceConsumers);

}