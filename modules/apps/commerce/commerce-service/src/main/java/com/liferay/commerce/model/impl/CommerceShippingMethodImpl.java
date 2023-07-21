/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShippingMethodImpl extends CommerceShippingMethodBaseImpl {

	public CommerceShippingMethodImpl() {
	}

	@Override
	public String getImageURL(ThemeDisplay themeDisplay) {
		if (getImageId() <= 0) {
			return null;
		}

		StringBundler sb = new StringBundler(5);

		sb.append(themeDisplay.getPathImage());
		sb.append("/shipping/method?img_id=");
		sb.append(getImageId());
		sb.append("&t=");
		sb.append(WebServerServletTokenUtil.getToken(getImageId()));

		return sb.toString();
	}

}