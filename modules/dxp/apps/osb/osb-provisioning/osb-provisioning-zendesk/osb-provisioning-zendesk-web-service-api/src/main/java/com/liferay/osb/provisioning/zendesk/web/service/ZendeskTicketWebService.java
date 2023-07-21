/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.web.service.search.SearchHits;
import com.liferay.osb.provisioning.zendesk.web.service.search.ZendeskTicketQuery;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Set;

/**
 * @author Kyle Bischof
 */
@ProviderType
public interface ZendeskTicketWebService {

	public void createZendeskTicket(ZendeskTicket zendeskTicket)
		throws PortalException;

	public List<ZendeskTicket> getZendeskTickets(Set<String> criteria)
		throws PortalException;

	public SearchHits<ZendeskTicket> search(
			ZendeskTicketQuery zendeskTicketQuery)
		throws PortalException;

	public void updateZendeskTickets(List<ZendeskTicket> zendeskTickets)
		throws PortalException;

}