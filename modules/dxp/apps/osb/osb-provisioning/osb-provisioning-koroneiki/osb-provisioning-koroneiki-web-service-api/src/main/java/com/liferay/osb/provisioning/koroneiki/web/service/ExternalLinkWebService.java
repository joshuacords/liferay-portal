/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ExternalLinkWebService {

	public ExternalLink addAccountExternalLink(
			String agentName, String agentUID, String accountKey,
			ExternalLink externalLink)
		throws Exception;

	public ExternalLink addProductExternalLink(
			String agentName, String agentUID, String productKey,
			ExternalLink externalLink)
		throws Exception;

	public void deleteExternalLink(
			String agentName, String agentUID, String externalLinkKey)
		throws Exception;

	public List<ExternalLink> getExternalLinks(
			String accountKey, int page, int pageSize)
		throws Exception;

	public ExternalLink updateExternalLink(
			String agentName, String agentUID, String externalLinkKey,
			ExternalLink externalLink)
		throws Exception;

}