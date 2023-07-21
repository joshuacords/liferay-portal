/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.container.request.filter;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.fields.NestedFieldsContext;
import com.liferay.portal.vulcan.fields.NestedFieldsContextThreadLocal;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.utils.JAXRSUtils;

/**
 * @author Ivica Cardic
 */
@Provider
public class NestedFieldsContainerRequestFilter
	implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext containerRequestContext)
		throws IOException {

		UriInfo uriInfo = containerRequestContext.getUriInfo();

		MultivaluedMap<String, String> queryParameters =
			uriInfo.getQueryParameters();

		String nestedFields = queryParameters.getFirst("nestedFields");

		if (Validator.isNotNull(nestedFields)) {
			List<String> fieldNames = Arrays.asList(
				nestedFields.split("\\s*,\\s*"));

			NestedFieldsContextThreadLocal.setNestedFieldsContext(
				new NestedFieldsContext(
					fieldNames, JAXRSUtils.getCurrentMessage(),
					uriInfo.getPathParameters(),
					_getResourceVersion(uriInfo.getPathSegments()),
					queryParameters));
		}
	}

	private String _getResourceVersion(List<PathSegment> pathSegments) {
		PathSegment pathSegment = pathSegments.get(0);

		return pathSegment.getPath();
	}

}