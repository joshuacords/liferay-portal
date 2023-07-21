/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface AuditEntryWebService {

	public List<AuditEntry> getAccountAuditEntries(
			String accountKey, int page, int pageSize)
		throws Exception;

	public long getAccountAuditEntriesCount(String accountKey) throws Exception;

	public Page<AuditEntry> postAccountAuditEntries(
			String agentName, String agentUID, String accountKey,
			AuditEntry[] auditEntries)
		throws Exception;

	public Page<AuditEntry> postContactAuditEntries(
			String agentName, String agentUID, String contactUuid,
			AuditEntry[] auditEntries)
		throws Exception;

}