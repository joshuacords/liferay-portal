/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.EntitlementDefinitionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionService;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/entitlement-definition.properties",
	scope = ServiceScope.PROTOTYPE,
	service = EntitlementDefinitionResource.class
)
public class EntitlementDefinitionResourceImpl
	extends BaseEntitlementDefinitionResourceImpl {

	@Override
	public void deleteEntitlementDefinition(
			String agentName, String agentUID, String entitlementDefinitionKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			entitlementDefinition =
				_entitlementDefinitionLocalService.getEntitlementDefinition(
					entitlementDefinitionKey);

		_entitlementDefinitionService.deleteEntitlementDefinition(
			entitlementDefinition.getEntitlementDefinitionId());
	}

	@Override
	public Page<EntitlementDefinition> getAccountEntitlementDefinitionsPage(
			String search, Pagination pagination)
		throws Exception {

		long classNameId = _classNameLocalService.getClassNameId(Account.class);

		search = GetterUtil.getString(search);
		search = StringUtil.quote(search, StringPool.PERCENT);

		return Page.of(
			transform(
				_entitlementDefinitionLocalService.search(
					classNameId, search, pagination.getStartPosition(),
					pagination.getEndPosition()),
				entitlementDefinition ->
					EntitlementDefinitionUtil.toEntitlementDefinition(
						entitlementDefinition)),
			pagination,
			_entitlementDefinitionLocalService.searchCount(
				classNameId, search));
	}

	@Override
	public Page<EntitlementDefinition> getContactEntitlementDefinitionsPage(
			String search, Pagination pagination)
		throws Exception {

		long classNameId = _classNameLocalService.getClassNameId(Contact.class);

		search = GetterUtil.getString(search);
		search = StringUtil.quote(search, StringPool.PERCENT);

		return Page.of(
			transform(
				_entitlementDefinitionLocalService.search(
					classNameId, search, pagination.getStartPosition(),
					pagination.getEndPosition()),
				entitlementDefinition ->
					EntitlementDefinitionUtil.toEntitlementDefinition(
						entitlementDefinition)),
			pagination,
			_entitlementDefinitionLocalService.searchCount(
				classNameId, search));
	}

	@Override
	public EntitlementDefinition getEntitlementDefinition(
			String entitlementDefinitionKey)
		throws Exception {

		return EntitlementDefinitionUtil.toEntitlementDefinition(
			_entitlementDefinitionLocalService.getEntitlementDefinition(
				entitlementDefinitionKey));
	}

	@Override
	public EntitlementDefinition postAccountEntitlementDefinition(
			String agentName, String agentUID,
			EntitlementDefinition entitlementDefinition)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		int status = WorkflowConstants.STATUS_APPROVED;

		EntitlementDefinition.Status entitlementDefinitionStatus =
			entitlementDefinition.getStatus();

		if (entitlementDefinitionStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				entitlementDefinitionStatus.toString());
		}

		return EntitlementDefinitionUtil.toEntitlementDefinition(
			_entitlementDefinitionService.addEntitlementDefinition(
				_classNameLocalService.getClassNameId(Account.class),
				entitlementDefinition.getName(),
				entitlementDefinition.getDescription(),
				entitlementDefinition.getDefinition(), status));
	}

	@Override
	public EntitlementDefinition postContactEntitlementDefinition(
			String agentName, String agentUID,
			EntitlementDefinition entitlementDefinition)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		int status = WorkflowConstants.STATUS_APPROVED;

		EntitlementDefinition.Status entitlementDefinitionStatus =
			entitlementDefinition.getStatus();

		if (entitlementDefinitionStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				entitlementDefinitionStatus.toString());
		}

		return EntitlementDefinitionUtil.toEntitlementDefinition(
			_entitlementDefinitionService.addEntitlementDefinition(
				_classNameLocalService.getClassNameId(Contact.class),
				entitlementDefinition.getName(),
				entitlementDefinition.getDescription(),
				entitlementDefinition.getDefinition(), status));
	}

	@Override
	public void postEntitlementDefinitionSynchronize(
			String agentName, String agentUID, String entitlementDefinitionKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			entitlementDefinition =
				_entitlementDefinitionLocalService.getEntitlementDefinition(
					entitlementDefinitionKey);

		_entitlementDefinitionService.synchronizeEntitlementDefinition(
			entitlementDefinition.getEntitlementDefinitionId());
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

	@Reference
	private EntitlementDefinitionService _entitlementDefinitionService;

}