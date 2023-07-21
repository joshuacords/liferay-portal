/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.impl;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.osb.koroneiki.scion.service.base.AuthenticationTokenLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.scion.model.AuthenticationToken",
	service = AopService.class
)
public class AuthenticationTokenLocalServiceImpl
	extends AuthenticationTokenLocalServiceBaseImpl {

	public AuthenticationToken addAuthenticationToken(
			long userId, long serviceProducerId, String name, String token)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long authenticationTokenId = counterLocalService.increment();

		AuthenticationToken authenticationToken =
			authenticationTokenPersistence.create(authenticationTokenId);

		authenticationToken.setCompanyId(user.getCompanyId());
		authenticationToken.setUserId(userId);
		authenticationToken.setServiceProducerId(serviceProducerId);
		authenticationToken.setName(name);
		authenticationToken.setPrefix(token.substring(0, 6));

		authenticationToken.setDigest(
			DigesterUtil.digestBase64(Digester.SHA_256, token));

		authenticationToken.setStatus(WorkflowConstants.STATUS_APPROVED);

		authenticationToken = authenticationTokenPersistence.update(
			authenticationToken);

		// Resources

		resourceLocalService.addResources(
			authenticationToken.getCompanyId(), 0, userId,
			AuthenticationToken.class.getName(),
			authenticationToken.getAuthenticationTokenId(), false, false,
			false);

		return authenticationToken;
	}

	@Override
	public AuthenticationToken deleteAuthenticationToken(
			AuthenticationToken authenticationToken)
		throws PortalException {

		// Resources

		resourceLocalService.deleteResource(
			authenticationToken.getCompanyId(),
			AuthenticationToken.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			authenticationToken.getAuthenticationTokenId());

		return authenticationTokenPersistence.remove(authenticationToken);
	}

	@Override
	public AuthenticationToken deleteAuthenticationToken(
			long authenticationTokenId)
		throws PortalException {

		AuthenticationToken authenticationToken =
			authenticationTokenPersistence.findByPrimaryKey(
				authenticationTokenId);

		return deleteAuthenticationToken(authenticationToken);
	}

	public AuthenticationToken fetchAuthenticationToken(
		String digest, int status) {

		return authenticationTokenPersistence.fetchByD_S(digest, status);
	}

	public List<AuthenticationToken> getAuthenticationTokens(
		long serviceProducerId, int start, int end) {

		return authenticationTokenPersistence.findByServiceProducerId(
			serviceProducerId, start, end);
	}

	public int getAuthenticationTokensCount(long serviceProducerId) {
		return authenticationTokenPersistence.countByServiceProducerId(
			serviceProducerId);
	}

	public AuthenticationToken updateAuthenticationToken(
			long authenticationTokenId, String name)
		throws PortalException {

		AuthenticationToken authenticationToken =
			authenticationTokenPersistence.findByPrimaryKey(
				authenticationTokenId);

		authenticationToken.setName(name);

		return authenticationTokenPersistence.update(authenticationToken);
	}

	public AuthenticationToken updateStatus(
			long authenticationTokenId, int status)
		throws PortalException {

		AuthenticationToken authenticationToken =
			authenticationTokenPersistence.findByPrimaryKey(
				authenticationTokenId);

		authenticationToken.setStatus(status);

		return authenticationTokenPersistence.update(authenticationToken);
	}

}