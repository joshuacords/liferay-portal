/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.resource;

import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Javier Gamarra
 */
public interface OpenAPIResource {

	public default Response getOpenAPI(
			Application application, HttpHeaders httpHeaders,
			Set<Class<?>> resourceClasses, ServletConfig servletConfig,
			String type, UriInfo uriInfo)
		throws Exception {

		return getOpenAPI(resourceClasses, type);
	}

	public Response getOpenAPI(
			HttpServletRequest httpServletRequest,
			Set<Class<?>> resourceClasses, String type, UriInfo uriInfo)
		throws Exception;

	public default Response getOpenAPI(
			Set<Class<?>> resourceClasses, String type)
		throws Exception {

		return null;
	}

	public default Response getOpenAPI(
			Set<Class<?>> resourceClasses, String type, UriInfo uriInfo)
		throws Exception {

		return getOpenAPI(resourceClasses, type, uriInfo);
	}

}