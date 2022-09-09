/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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