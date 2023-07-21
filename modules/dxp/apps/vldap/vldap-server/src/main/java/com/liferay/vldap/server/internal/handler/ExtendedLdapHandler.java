/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler;

import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.vldap.server.internal.handler.util.LdapHandlerContext;
import com.liferay.vldap.server.internal.handler.util.LdapSslContextFactory;
import com.liferay.vldap.server.internal.util.OIDConstants;
import com.liferay.vldap.server.internal.util.VLDAPConstants;

import java.util.List;

import org.apache.directory.api.ldap.model.message.ExtendedRequest;
import org.apache.directory.api.ldap.model.message.ExtendedResponse;
import org.apache.directory.api.ldap.model.message.Request;
import org.apache.directory.api.ldap.model.message.Response;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class ExtendedLdapHandler extends BaseLdapHandler {

	@Override
	public List<Response> messageReceived(
		Request request, IoSession ioSession,
		LdapHandlerContext ldapHandlerContext) {

		ExtendedRequest extendedRequest = (ExtendedRequest)request;

		String oid = extendedRequest.getRequestName();

		if (oid.equals(OIDConstants.START_TLS)) {
			return handleStartTLS(extendedRequest, ioSession);
		}

		return null;
	}

	protected List<Response> handleStartTLS(
		ExtendedRequest extendedRequest, IoSession ioSession) {

		SslFilter sslFilter = new SslFilter(
			LdapSslContextFactory.getSSLContext(true));

		IoFilterChain ioFilterChain = ioSession.getFilterChain();

		ioFilterChain.addFirst("sslFilter", sslFilter);

		ExtendedResponse extendedResponse = (ExtendedResponse)getResultResponse(
			extendedRequest);

		extendedResponse.setResponseName(OIDConstants.START_TLS);

		extendedResponse.put(
			VLDAPConstants.SESSION_ATTRIBUTES,
			HashMapBuilder.<Object, Object>put(
				SslFilter.DISABLE_ENCRYPTION_ONCE, true
			).build());

		return toList(extendedResponse);
	}

}