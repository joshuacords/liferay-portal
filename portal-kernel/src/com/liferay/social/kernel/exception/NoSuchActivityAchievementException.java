/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.kernel.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchActivityAchievementException extends NoSuchModelException {

	public NoSuchActivityAchievementException() {
	}

	public NoSuchActivityAchievementException(String msg) {
		super(msg);
	}

	public NoSuchActivityAchievementException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchActivityAchievementException(Throwable cause) {
		super(cause);
	}

}