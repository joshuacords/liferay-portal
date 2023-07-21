/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Michael C. Han
 */
public class OpenIdServiceException extends PortalException {

	public OpenIdServiceException(String msg) {
		super(msg);
	}

	public OpenIdServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public static class AssociationException extends OpenIdServiceException {

		public AssociationException(String msg) {
			super(msg);
		}

		public AssociationException(String msg, Throwable cause) {
			super(msg, cause);
		}

	}

	public static class ConsumerException extends OpenIdServiceException {

		public ConsumerException(String msg) {
			super(msg);
		}

		public ConsumerException(String msg, Throwable cause) {
			super(msg, cause);
		}

	}

	public static class DiscoveryException extends OpenIdServiceException {

		public DiscoveryException(String msg) {
			super(msg);
		}

		public DiscoveryException(String msg, Throwable cause) {
			super(msg, cause);
		}

	}

	public static class MessageException extends OpenIdServiceException {

		public MessageException(String msg) {
			super(msg);
		}

		public MessageException(String msg, Throwable cause) {
			super(msg, cause);
		}

	}

}