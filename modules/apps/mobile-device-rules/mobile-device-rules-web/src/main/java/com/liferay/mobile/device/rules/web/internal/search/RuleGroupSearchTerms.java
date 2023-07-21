/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Edward Han
 */
public class RuleGroupSearchTerms extends RuleGroupDisplayTerms {

	public RuleGroupSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		setGroupId(DAOParamUtil.getLong(portletRequest, GROUP_ID));
		setName(DAOParamUtil.getString(portletRequest, NAME));
	}

}