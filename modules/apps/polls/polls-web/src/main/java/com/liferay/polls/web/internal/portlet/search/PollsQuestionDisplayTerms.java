/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.web.internal.portlet.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;

import javax.portlet.PortletRequest;

/**
 * @author Pedro Queiroz
 */
public class PollsQuestionDisplayTerms extends DisplayTerms {

	public PollsQuestionDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

}