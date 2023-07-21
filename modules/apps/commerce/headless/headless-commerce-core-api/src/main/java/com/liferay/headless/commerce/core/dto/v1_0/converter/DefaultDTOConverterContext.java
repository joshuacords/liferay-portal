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
 * 					 com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext}
 */
@Deprecated
public class DefaultDTOConverterContext implements DTOConverterContext {

	public DefaultDTOConverterContext(Locale locale, long resourcePrimKey) {
		_locale = locale;
		_resourcePrimKey = resourcePrimKey;
	}

	public DefaultDTOConverterContext(
		Locale locale, long resourcePrimKey, UriInfo uriInfo) {

		_locale = locale;
		_resourcePrimKey = resourcePrimKey;
		_uriInfo = uriInfo;
	}

	public DefaultDTOConverterContext(
		Locale locale, Object compositeResourcePrimKey) {

		_locale = locale;
		_compositeResourcePrimKey = compositeResourcePrimKey;
	}

	public DefaultDTOConverterContext(
		Locale locale, Object compositeResourcePrimKey, UriInfo uriInfo) {

		_locale = locale;
		_compositeResourcePrimKey = compositeResourcePrimKey;
		_uriInfo = uriInfo;
	}

	@Override
	public Object getCompositeResourcePrimKey() {
		return _compositeResourcePrimKey;
	}

	@Override
	public Locale getLocale() {
		return _locale;
	}

	@Override
	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	@Override
	public Optional<UriInfo> getUriInfoOptional() {
		return Optional.ofNullable(_uriInfo);
	}

	private Object _compositeResourcePrimKey;
	private final Locale _locale;
	private long _resourcePrimKey;
	private UriInfo _uriInfo;

}