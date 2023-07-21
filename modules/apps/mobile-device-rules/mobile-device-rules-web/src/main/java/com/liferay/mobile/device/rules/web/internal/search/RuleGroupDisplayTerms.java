/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Edward Han
 */
public class RuleGroupDisplayTerms extends DisplayTerms {

	public static final String GROUP_ID = "searchGroupId";

	public static final String NAME = "searchName";

	public RuleGroupDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		_groupId = ParamUtil.getLong(portletRequest, GROUP_ID);
		_name = ParamUtil.getString(portletRequest, NAME);
	}

	public long getGroupId() {
		return _groupId;
	}

	public String getName() {
		return _name;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _groupId;
	private String _name;

}