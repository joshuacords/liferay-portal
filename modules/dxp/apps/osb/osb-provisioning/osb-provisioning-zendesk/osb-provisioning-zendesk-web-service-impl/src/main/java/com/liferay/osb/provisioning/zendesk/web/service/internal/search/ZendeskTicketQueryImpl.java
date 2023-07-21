/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.web.service.internal.search;

import com.liferay.osb.provisioning.zendesk.web.service.search.ZendeskTicketQuery;

/**
 * @author Amos Fong
 */
public class ZendeskTicketQueryImpl
	extends QueryImpl implements ZendeskTicketQuery {

	public ZendeskTicketQueryImpl() {
		addCriterion("type:ticket");
	}

}