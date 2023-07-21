/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.core.dto.v1_0.converter;

import java.util.Locale;
import java.util.Optional;

import javax.ws.rs.core.UriInfo;

/**
 * @author Alessio Antonio Rendina
 * @deprecated As of Athanasius (7.3.x), replaced by {@link
 * 					 com.liferay.portal.vulcan.dto.converter.DTOConverterContext}
 */
@Deprecated
public interface DTOConverterContext {

	public Object getCompositeResourcePrimKey();

	public Locale getLocale();

	public long getResourcePrimKey();

	public Optional<UriInfo> getUriInfoOptional();

}