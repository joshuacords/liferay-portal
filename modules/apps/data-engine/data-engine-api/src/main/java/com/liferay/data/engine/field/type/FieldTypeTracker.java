/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.field.type;

import java.util.Collection;
import java.util.Map;

/**
 * @author Leonardo Barros
 */
public interface FieldTypeTracker {

	public FieldType getFieldType(String type);

	public Map<String, Object> getFieldTypeProperties(String type);

	public Collection<FieldType> getFieldTypes();

}