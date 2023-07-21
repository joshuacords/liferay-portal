/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.io;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author     Bruno Basto
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             DDMFormFieldTypesSerializer}
 */
@Deprecated
@ProviderType
public interface DDMFormFieldTypesJSONSerializer {

	public String serialize(List<DDMFormFieldType> ddmFormFieldTypes)
		throws PortalException;

}