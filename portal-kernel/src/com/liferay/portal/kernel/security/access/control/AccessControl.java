/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.access.control;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;

import java.lang.annotation.Annotation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public interface AccessControl {

	public void initAccessControlContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, Map<String, Object> settings);

	public void initContextUser(long userId) throws AuthException;

	public AuthVerifierResult.State verifyRequest() throws PortalException;

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public AccessControlled NULL_ACCESS_CONTROLLED = new AccessControlled() {

		@Override
		public Class<? extends Annotation> annotationType() {
			return AccessControlled.class;
		}

		@Override
		public boolean guestAccessEnabled() {
			return false;
		}

		@Override
		public boolean hostAllowedValidationEnabled() {
			return false;
		}

	};

}