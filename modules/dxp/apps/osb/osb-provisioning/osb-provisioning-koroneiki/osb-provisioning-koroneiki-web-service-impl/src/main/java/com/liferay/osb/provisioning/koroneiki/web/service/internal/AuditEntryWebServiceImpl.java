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

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.AuditEntryResource;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = AuditEntryWebService.class
)
public class AuditEntryWebServiceImpl implements AuditEntryWebService {

	public List<AuditEntry> getAccountAuditEntries(
			String accountKey, int page, int pageSize)
		throws Exception {

		Page<AuditEntry> auditEntriesPage =
			_auditEntryResource.getAccountAccountKeyAuditEntriesPage(
				accountKey, Pagination.of(page, pageSize));

		if ((auditEntriesPage != null) &&
			(auditEntriesPage.getItems() != null)) {

			return new ArrayList<>(auditEntriesPage.getItems());
		}

		return Collections.emptyList();
	}

	public Page<AuditEntry> postAccountAuditEntries(
			String agentName, String agentUID, String accountKey,
			AuditEntry[] auditEntries)
		throws Exception {

		return _auditEntryResource.postAccountAccountKeyAuditEntriesPage(
			agentName, agentUID, accountKey, auditEntries);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		AuditEntryResource.Builder builder = AuditEntryResource.builder();

		_auditEntryResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private AuditEntryResource _auditEntryResource;

}