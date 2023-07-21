/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.lar;

import com.liferay.exportimport.kernel.lar.UserIdStrategy;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Bruno Farache
 */
public class CurrentUserIdStrategy implements UserIdStrategy {

	public CurrentUserIdStrategy(User user) {
		_user = user;
	}

	@Override
	public long getUserId(String userUuid) {
		if (Validator.isNull(userUuid)) {
			return _user.getUserId();
		}

		try {
			User user = UserLocalServiceUtil.getUserByUuidAndCompanyId(
				userUuid, _user.getCompanyId());

			return user.getUserId();
		}
		catch (Exception exception) {
			return _user.getUserId();
		}
	}

	private final User _user;

}