/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.jaxrs.context;

import java.util.Map;
import java.util.Set;

/**
 * @author Javier de Arcos
 */
public abstract class EntityExtensionContext<T> implements ExtensionContext {

	public abstract Map<String, Object> getEntityExtendedProperties(T entity);

	public abstract Set<String> getEntityFilteredPropertyKeys(T entity);

	@Override
	public Map<String, Object> getExtendedProperties(Object object) {
		T entity = _parseObjectToEntity(object);

		return getEntityExtendedProperties(entity);
	}

	@Override
	public Set<String> getFilteredPropertyKeys(Object object) {
		T entity = _parseObjectToEntity(object);

		return getEntityFilteredPropertyKeys(entity);
	}

	private T _parseObjectToEntity(Object object) {
		try {
			T entity = (T)object;

			if (entity == null) {
				throw new IllegalArgumentException(
					"Invalid object type " + object.getClass());
			}

			return entity;
		}
		catch (ClassCastException classCastException) {
			throw new IllegalArgumentException(
				"Invalid object type " + object.getClass(), classCastException);
		}
	}

}