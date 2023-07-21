/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.asset.tag.stats.exception.NoSuchTagStatsException}
 */
@Deprecated
public class NoSuchTagStatsException extends NoSuchModelException {

	public NoSuchTagStatsException() {
	}

	public NoSuchTagStatsException(String msg) {
		super(msg);
	}

	public NoSuchTagStatsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchTagStatsException(Throwable cause) {
		super(cause);
	}

}