/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.web.service.internal.search;

import com.liferay.osb.provisioning.zendesk.web.service.search.Query;
import com.liferay.osb.provisioning.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.provisioning.zendesk.web.service.search.ZendeskTicketQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = QueryFactory.class)
public class QueryFactoryImpl implements QueryFactory {

	public Query createQuery() {
		return new QueryImpl();
	}

	public ZendeskTicketQuery createZendeskTicketQuery() {
		return new ZendeskTicketQueryImpl();
	}

}