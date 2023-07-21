/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ExternalLinkUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.root.service.ExternalLinkService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/external-link.properties",
	scope = ServiceScope.PROTOTYPE, service = ExternalLinkResource.class
)
public class ExternalLinkResourceImpl extends BaseExternalLinkResourceImpl {

	@Override
	public void deleteExternalLink(
			String agentName, String agentUID, String externalLinkKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_externalLinkService.deleteExternalLink(externalLinkKey);
	}

	@Override
	public Page<ExternalLink> getAccountAccountKeyExternalLinksPage(
			String accountKey, Pagination pagination)
		throws Exception {

		Account account = _accountLocalService.getAccount(accountKey);

		return getExternalLinksPage(
			Account.class, account.getAccountId(), pagination);
	}

	@Override
	public Page<ExternalLink> getContactByUuidContactUuidExternalLinksPage(
			String contactUuid, Pagination pagination)
		throws Exception {

		Contact contact = _contactLocalService.getContactByUuid(contactUuid);

		return getExternalLinksPage(
			Contact.class, contact.getContactId(), pagination);
	}

	@Override
	public Page<ExternalLink> getContactRoleContactRoleKeyExternalLinksPage(
			String contactRoleKey, Pagination pagination)
		throws Exception {

		ContactRole contactRole = _contactRoleLocalService.getContactRole(
			contactRoleKey);

		return getExternalLinksPage(
			ContactRole.class, contactRole.getContactRoleId(), pagination);
	}

	@Override
	public ExternalLink getExternalLink(String externalLinkKey)
		throws Exception {

		return ExternalLinkUtil.toExternalLink(
			_externalLinkService.getExternalLink(externalLinkKey));
	}

	@Override
	public Page<ExternalLink>
			getProductConsumptionProductConsumptionKeyExternalLinksPage(
				String productConsumptionKey, Pagination pagination)
		throws Exception {

		ProductConsumption productConsumption =
			_productConsumptionLocalService.getProductConsumption(
				productConsumptionKey);

		return getExternalLinksPage(
			ProductConsumption.class,
			productConsumption.getProductConsumptionId(), pagination);
	}

	@Override
	public Page<ExternalLink> getProductProductKeyExternalLinksPage(
			String productKey, Pagination pagination)
		throws Exception {

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		return getExternalLinksPage(
			ProductEntry.class, productEntry.getProductEntryId(), pagination);
	}

	@Override
	public Page<ExternalLink>
			getProductPurchaseProductPurchaseKeyExternalLinksPage(
				String productPurchaseKey, Pagination pagination)
		throws Exception {

		ProductPurchase productPurchase =
			_productPurchaseLocalService.getProductPurchase(productPurchaseKey);

		return getExternalLinksPage(
			ProductPurchase.class, productPurchase.getProductPurchaseId(),
			pagination);
	}

	@Override
	public Page<ExternalLink> getTeamTeamKeyExternalLinksPage(
			String teamKey, Pagination pagination)
		throws Exception {

		Team team = _teamLocalService.getTeam(teamKey);

		return getExternalLinksPage(Team.class, team.getTeamId(), pagination);
	}

	@Override
	public ExternalLink postAccountAccountKeyExternalLink(
			String agentName, String agentUID, String accountKey,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		Account account = _accountLocalService.getAccount(accountKey);

		return postExternalLink(
			Account.class, account.getAccountId(), externalLink);
	}

	@Override
	public ExternalLink postContactByUuidContactUuidExternalLink(
			String agentName, String agentUID, String contactUuid,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		Contact contact = _contactLocalService.getContactByUuid(contactUuid);

		return postExternalLink(
			Contact.class, contact.getContactId(), externalLink);
	}

	@Override
	public ExternalLink postContactRoleContactRoleKeyExternalLink(
			String agentName, String agentUID, String contactRoleKey,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		ContactRole contactRole = _contactRoleLocalService.getContactRole(
			contactRoleKey);

		return postExternalLink(
			ContactRole.class, contactRole.getContactRoleId(), externalLink);
	}

	@Override
	public ExternalLink postProductConsumptionProductConsumptionKeyExternalLink(
			String agentName, String agentUID, String productConsumptionKey,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		ProductConsumption productConsumption =
			_productConsumptionLocalService.getProductConsumption(
				productConsumptionKey);

		return postExternalLink(
			ProductConsumption.class,
			productConsumption.getProductConsumptionId(), externalLink);
	}

	@Override
	public ExternalLink postProductProductKeyExternalLink(
			String agentName, String agentUID, String productKey,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		return postExternalLink(
			ProductEntry.class, productEntry.getProductEntryId(), externalLink);
	}

	@Override
	public ExternalLink postProductPurchaseProductPurchaseKeyExternalLink(
			String agentName, String agentUID, String productPurchaseKey,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		ProductPurchase productPurchase =
			_productPurchaseLocalService.getProductPurchase(productPurchaseKey);

		return postExternalLink(
			ProductPurchase.class, productPurchase.getProductPurchaseId(),
			externalLink);
	}

	@Override
	public ExternalLink postTeamTeamKeyExternalLink(
			String agentName, String agentUID, String teamKey,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		Team team = _teamLocalService.getTeam(teamKey);

		return postExternalLink(Team.class, team.getTeamId(), externalLink);
	}

	@Override
	public ExternalLink putExternalLink(
			String agentName, String agentUID, String externalLinkKey,
			ExternalLink externalLink)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.root.model.ExternalLink curExternalLink =
			_externalLinkService.getExternalLink(externalLinkKey);

		return ExternalLinkUtil.toExternalLink(
			_externalLinkService.updateExternalLink(
				curExternalLink.getExternalLinkId(),
				externalLink.getEntityId()));
	}

	protected Page<ExternalLink> getExternalLinksPage(
			Class<?> clazz, long classPK, Pagination pagination)
		throws Exception {

		long classNameId = _classNameLocalService.getClassNameId(clazz);

		return Page.of(
			transform(
				_externalLinkService.getExternalLinks(
					classNameId, classPK, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ExternalLinkUtil::toExternalLink),
			pagination,
			_externalLinkService.getExternalLinksCount(classNameId, classPK));
	}

	protected ExternalLink postExternalLink(
			Class<?> clazz, long classPK, ExternalLink externalLink)
		throws Exception {

		return ExternalLinkUtil.toExternalLink(
			_externalLinkService.addExternalLink(
				_classNameLocalService.getClassNameId(clazz), classPK,
				externalLink.getDomain(), externalLink.getEntityName(),
				externalLink.getEntityId()));
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ExternalLinkService _externalLinkService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

}