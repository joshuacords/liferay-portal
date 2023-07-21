/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.model.impl;

import com.liferay.oauth.model.OAuthApplicationConstants;

/**
 * @author Ivica Cardic
 */
public class OAuthApplicationImpl extends OAuthApplicationBaseImpl {

	public OAuthApplicationImpl() {
	}

	@Override
	public String getAccessLevelLabel() {
		if (getAccessLevel() == OAuthApplicationConstants.ACCESS_READ) {
			return OAuthApplicationConstants.LABEL_ACCESS_READ;
		}

		return OAuthApplicationConstants.LABEL_ACCESS_WRITE;
	}

}