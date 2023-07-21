/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Igor Beslic
 * @author Raymond Augé
 */
public class OAuthApplicationDisplayTerms extends DisplayTerms {

	public static final String NAME = "name";

	public static final String OAUTH_APPLICATION_ID = "oAuthApplicationId";

	public OAuthApplicationDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = ParamUtil.getString(portletRequest, NAME);
		oAuthApplicationId = ParamUtil.getInteger(
			portletRequest, OAUTH_APPLICATION_ID);
	}

	public String getName() {
		return name;
	}

	public long getOAuthApplicationId() {
		return oAuthApplicationId;
	}

	protected String name;
	protected long oAuthApplicationId;

}