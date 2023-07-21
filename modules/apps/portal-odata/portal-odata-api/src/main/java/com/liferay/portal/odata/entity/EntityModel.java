/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.odata.entity;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Models a <code>EntityModel</code>.
 *
 * @author Cristina González
 * @review
 */
public interface EntityModel {

	public static Map<String, EntityField> toEntityFieldsMap(
		EntityField... entityFields) {

		Map<String, EntityField> entityFieldsMap = new HashMap<>();

		for (EntityField entityField : entityFields) {
			entityFieldsMap.put(entityField.getName(), entityField);
		}

		return entityFieldsMap;
	}

	/**
	 * Returns a Map with all the entity fields used to create the EDM.
	 *
	 * @return the entity field map
	 * @review
	 */
	public Map<String, EntityField> getEntityFieldsMap();

	/**
	 * Returns the name of the single entity type used to create the EDM.
	 *
	 * @return the entity type name
	 * @review
	 */
	public default String getName() {
		Class<?> clazz = getClass();

		return StringUtil.replace(
			clazz.getName(), CharPool.PERIOD, CharPool.UNDERLINE);
	}

}