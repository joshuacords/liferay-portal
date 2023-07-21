/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.collections;

import com.liferay.registry.ServiceReference;

/**
 * @author Carlos Sierra Andrés
 */
public interface ServiceReferenceMapper<K, S> {

	public void map(ServiceReference<S> serviceReference, Emitter<K> emitter);

	public interface Emitter<K> {

		public void emit(K key);

	}

}