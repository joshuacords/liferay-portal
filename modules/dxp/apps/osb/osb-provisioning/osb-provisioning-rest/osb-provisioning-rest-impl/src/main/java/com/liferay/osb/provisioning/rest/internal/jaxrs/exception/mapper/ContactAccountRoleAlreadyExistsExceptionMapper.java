/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.provisioning.koroneiki.exception.ContactAccountRoleAlreadyExistsException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code ContactAccountRoleAlreadyExistsException} to a {@code 409} error.
 *
 * @author Amos Fong
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Provisioning.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Provisioning.REST.ContactAccountRoleAlreadyExistsExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class ContactAccountRoleAlreadyExistsExceptionMapper
	extends BaseExceptionMapper<ContactAccountRoleAlreadyExistsException> {

	@Override
	protected Problem getProblem(
		ContactAccountRoleAlreadyExistsException
			contactAccountRoleAlreadyExistsException) {

		return new Problem(
			Response.Status.CONFLICT,
			"The contact is already assigned to the role on the account");
	}

}