/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.jaxrs.exception.mapper;

import com.liferay.commerce.pricing.exception.DuplicateCommercePricingClassCPDefinitionRelException;
import com.liferay.headless.commerce.core.exception.mapper.BaseExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 * @author Zoltán Takács
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog.DuplicateCommercePricingClassCPDefinitionRelExceptionMapper"
	},
	service = ExceptionMapper.class
)
@Provider
public class DuplicateCPDefinitionProductGroupExceptionMapper
	extends BaseExceptionMapper
		<DuplicateCommercePricingClassCPDefinitionRelException> {

	@Override
	public String getErrorDescription() {
		return "A product can occur only one time in a product group";
	}

	@Override
	public Response.Status getStatus() {
		return Response.Status.BAD_REQUEST;
	}

}