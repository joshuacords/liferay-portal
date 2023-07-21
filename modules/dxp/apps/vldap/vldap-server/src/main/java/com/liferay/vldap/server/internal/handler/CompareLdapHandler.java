/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.vldap.server.internal.directory.DirectoryTree;
import com.liferay.vldap.server.internal.directory.SearchBase;
import com.liferay.vldap.server.internal.directory.ldap.Directory;
import com.liferay.vldap.server.internal.handler.util.LdapHandlerContext;
import com.liferay.vldap.server.internal.util.PortletPropsValues;

import java.util.List;

import org.apache.directory.api.ldap.model.entry.Value;
import org.apache.directory.api.ldap.model.message.CompareRequest;
import org.apache.directory.api.ldap.model.message.Request;
import org.apache.directory.api.ldap.model.message.Response;
import org.apache.directory.api.ldap.model.message.ResultCodeEnum;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class CompareLdapHandler extends BaseLdapHandler {

	@Override
	public List<Response> messageReceived(
			Request request, IoSession ioSession,
			LdapHandlerContext ldapHandlerContext)
		throws PortalException {

		CompareRequest compareRequest = (CompareRequest)request;

		DirectoryTree directoryTree = new DirectoryTree();

		SearchBase searchBase = directoryTree.getSearchBase(
			compareRequest.getName(), PortletPropsValues.SEARCH_MAX_SIZE);

		if (searchBase == null) {
			return toList(
				getResultResponse(
					compareRequest, ResultCodeEnum.COMPARE_FALSE));
		}

		Directory directory = searchBase.getDirectory();

		if (directory == null) {
			return toList(
				getResultResponse(
					compareRequest, ResultCodeEnum.COMPARE_FALSE));
		}

		Value value = compareRequest.getAssertionValue();

		if (!directory.hasAttribute(
				compareRequest.getAttributeId(), value.getString())) {

			return toList(
				getResultResponse(
					compareRequest, ResultCodeEnum.COMPARE_FALSE));
		}

		return toList(
			getResultResponse(compareRequest, ResultCodeEnum.COMPARE_TRUE));
	}

}