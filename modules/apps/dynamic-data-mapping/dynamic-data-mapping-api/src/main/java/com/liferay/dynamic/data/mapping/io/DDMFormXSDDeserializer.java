/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.io;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author     Pablo Carvalho
 * @deprecated As of Judson (7.1.x), replaced by {@link DDMFormDeserializer}
 */
@Deprecated
@ProviderType
public interface DDMFormXSDDeserializer {

	public DDMForm deserialize(String serializedDDMForm) throws PortalException;

}