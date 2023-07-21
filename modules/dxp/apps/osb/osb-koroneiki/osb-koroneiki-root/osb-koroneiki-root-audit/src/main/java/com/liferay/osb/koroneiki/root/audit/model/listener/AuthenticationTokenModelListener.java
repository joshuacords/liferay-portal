/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.osb.koroneiki.scion.service.AuthenticationTokenLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class AuthenticationTokenModelListener
	extends BaseAuditModelListener<AuthenticationToken> {

	@Override
	protected long getClassNameId(AuthenticationToken authenticationToken) {
		return classNameLocalService.getClassNameId(ServiceProducer.class);
	}

	@Override
	protected long getClassPK(AuthenticationToken authenticationToken) {
		return authenticationToken.getServiceProducerId();
	}

	@Override
	protected AuthenticationToken getModel(long classPK)
		throws PortalException {

		return _authenticationTokenLocalService.getAuthenticationToken(classPK);
	}

	@Reference
	private AuthenticationTokenLocalService _authenticationTokenLocalService;

}