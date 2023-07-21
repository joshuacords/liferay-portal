/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.util;

import com.liferay.portal.kernel.oauth.OAuthException;

import net.oauth.SimpleOAuthValidator;

/**
 * @author Ivica Cardic
 */
public class DefaultOAuthValidator implements OAuthValidator {

	@Override
	public void validateOAuthMessage(
			OAuthMessage oAuthMessage, OAuthAccessor oAuthAccessor)
		throws OAuthException {

		try {
			_oAuthValidator.validateMessage(
				(net.oauth.OAuthMessage)oAuthMessage.getWrappedOAuthMessage(),
				(net.oauth.OAuthAccessor)
					oAuthAccessor.getWrappedOAuthAccessor());
		}
		catch (Exception exception) {
			throw new OAuthException(exception);
		}
	}

	private final net.oauth.OAuthValidator _oAuthValidator =
		new SimpleOAuthValidator();

}