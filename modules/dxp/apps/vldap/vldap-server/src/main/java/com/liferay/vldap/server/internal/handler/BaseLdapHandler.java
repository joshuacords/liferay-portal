/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler;

import java.util.Arrays;
import java.util.List;

import org.apache.directory.api.ldap.model.message.LdapResult;
import org.apache.directory.api.ldap.model.message.Response;
import org.apache.directory.api.ldap.model.message.ResultCodeEnum;
import org.apache.directory.api.ldap.model.message.ResultResponse;
import org.apache.directory.api.ldap.model.message.ResultResponseRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public abstract class BaseLdapHandler implements LdapHandler {

	protected <T extends ResultResponseRequest> ResultResponse
		getResultResponse(T request) {

		return getResultResponse(request, ResultCodeEnum.SUCCESS);
	}

	protected <T extends ResultResponseRequest> ResultResponse
		getResultResponse(T request, ResultCodeEnum resultCode) {

		ResultResponse resultResponse = request.getResultResponse();

		LdapResult ldapResult = resultResponse.getLdapResult();

		ldapResult.setResultCode(resultCode);

		return resultResponse;
	}

	protected List<Response> toList(Response response) {
		return Arrays.asList(response);
	}

}