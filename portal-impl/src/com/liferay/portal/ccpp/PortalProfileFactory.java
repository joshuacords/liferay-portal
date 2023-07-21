/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.ccpp;

import com.sun.ccpp.ProfileFactoryImpl;

import javax.ccpp.Profile;
import javax.ccpp.ProfileFactory;
import javax.ccpp.ValidationMode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalProfileFactory {

	public static Profile getCCPPProfile(
		HttpServletRequest httpServletRequest) {

		ProfileFactory profileFactory = ProfileFactory.getInstance();

		if (profileFactory == null) {
			profileFactory = ProfileFactoryImpl.getInstance();

			ProfileFactory.setInstance(profileFactory);
		}

		Profile profile = profileFactory.newProfile(
			httpServletRequest, ValidationMode.VALIDATIONMODE_NONE);

		if (profile == null) {
			profile = _profile;
		}

		return profile;
	}

	private static final Profile _profile = new EmptyProfile();

}