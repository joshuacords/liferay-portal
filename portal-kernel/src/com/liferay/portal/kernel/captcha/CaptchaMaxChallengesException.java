/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.captcha;

/**
 * @author     Jack Li
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class CaptchaMaxChallengesException extends CaptchaException {

	public CaptchaMaxChallengesException() {
	}

	public CaptchaMaxChallengesException(String msg) {
		super(msg);
	}

	public CaptchaMaxChallengesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CaptchaMaxChallengesException(Throwable cause) {
		super(cause);
	}

}