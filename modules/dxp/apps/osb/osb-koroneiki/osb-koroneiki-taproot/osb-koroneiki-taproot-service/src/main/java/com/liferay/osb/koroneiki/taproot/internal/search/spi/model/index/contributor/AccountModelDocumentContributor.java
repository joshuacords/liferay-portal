/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search.spi.model.index.contributor;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.AuditEntryLocalService;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.root.util.comparator.AuditEntryCreateDateComparator;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.FieldArray;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.taproot.model.Account",
	service = ModelDocumentContributor.class
)
public class AccountModelDocumentContributor
	implements ModelDocumentContributor<Account> {

	@Override
	public void contribute(Document document, Account account) {
		try {
			_contribute(document, account);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _contribute(Document document, Account account)
		throws PortalException {

		document.addKeyword(Field.COMPANY_ID, account.getCompanyId());
		document.addDate(Field.CREATE_DATE, account.getCreateDate());
		document.addText(Field.DESCRIPTION, account.getDescription());

		Date modifiedDate = account.getModifiedDate();

		List<AuditEntry> auditEntries = _auditEntryLocalService.getAuditEntries(
			_classNameLocalService.getClassNameId(Account.class),
			account.getAccountId(), 0, 1,
			new AuditEntryCreateDateComparator(false));

		if (!auditEntries.isEmpty()) {
			AuditEntry auditEntry = auditEntries.get(0);

			modifiedDate = auditEntry.getCreateDate();
		}

		document.addDate(Field.MODIFIED_DATE, modifiedDate);

		document.addText(Field.NAME, account.getName());
		document.addKeyword(
			Field.STATUS, StringUtil.toLowerCase(account.getStatus()));
		document.addKeyword(Field.USER_ID, account.getUserId());

		document.addKeyword("accountKey", account.getAccountKey());
		document.addText("code", account.getCode());
		document.addKeyword(
			"contactEmailAddress", account.getContactEmailAddress());
		document.addKeyword("dataRegion", account.getDataRegion());
		document.addKeyword("faxNumber", account.getFaxNumber());
		document.addKeyword("internal", account.isInternal());
		document.addKeyword("language", account.getLanguage());

		List<Account> childAccounts = account.getChildAccounts();

		if (!childAccounts.isEmpty()) {
			document.addKeyword("parent", true);
		}
		else {
			document.addKeyword("parent", false);
		}

		Account parentAccount = account.getParentAccount();

		if (parentAccount != null) {
			document.addKeyword(
				"parentAccountKey", parentAccount.getAccountKey());
		}

		document.addKeyword("phoneNumber", account.getPhoneNumber());
		document.addKeyword(
			"profileEmailAddress", account.getProfileEmailAddress());
		document.addKeyword("region", account.getRegion());
		document.addKeyword("tier", account.getTier());

		auditEntries = _auditEntryLocalService.getAuditEntries(
			_classNameLocalService.getClassNameId(Account.class),
			account.getAccountId(), 0, 1, null);

		if (!auditEntries.isEmpty()) {
			AuditEntry auditEntry = auditEntries.get(0);

			document.addKeyword("userUuid", auditEntry.getAgentUID());
		}

		document.addKeyword("website", account.getWebsite());

		document.addDateSortable(Field.CREATE_DATE, account.getCreateDate());
		document.addDateSortable(Field.MODIFIED_DATE, modifiedDate);
		document.addTextSortable(Field.NAME, account.getName());

		document.addTextSortable("code", account.getCode());
		document.addTextSortable("name", account.getName());
		document.addTextSortable("region", account.getRegion());
		document.addTextSortable("language", account.getLanguage());

		_contributeAccountFields(document, account);
		_contributeAccountNotes(document, account.getAccountId());
		_contributeAddresses(document, account.getAddresses());
		_contributeContacts(document, account.getAccountId());
		_contributeEntitlements(document, account.getAccountId());
		_contributeExternalLinks(document, account.getAccountId());
		_contributeProductPurchases(document, account.getAccountId());
		_contributeTeams(document, account.getAccountId());
	}

	private void _contributeAccountFields(Document document, Account account)
		throws PortalException {

		List<AccountField> accountFields = account.getAccountFields();

		for (AccountField accountField : accountFields) {
			document.addKeyword(
				"property_" + accountField.getName(), accountField.getValue());
		}
	}

	private void _contributeAccountNotes(Document document, long accountId)
		throws PortalException {

		Set<String> accountNoteGeneralContent = new HashSet<>();
		Set<String> accountNoteGeneralContentArchived = new HashSet<>();
		Set<String> accountNoteSalesContent = new HashSet<>();
		Set<String> accountNoteSalesContentArchived = new HashSet<>();

		List<AccountNote> accountNotes =
			_accountNoteLocalService.getAccountNotes(
				accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountNote accountNote : accountNotes) {
			String type = accountNote.getType();
			String status = accountNote.getStatus();

			if (type.equals(Note.Type.GENERAL.toString())) {
				if (status.equals(Note.Status.APPROVED.toString())) {
					accountNoteGeneralContent.add(accountNote.getContent());
				}
				else {
					accountNoteGeneralContentArchived.add(
						accountNote.getContent());
				}
			}
			else if (type.equals(Note.Type.SALES.toString())) {
				if (status.equals(Note.Status.APPROVED.toString())) {
					accountNoteSalesContent.add(accountNote.getContent());
				}
				else {
					accountNoteSalesContentArchived.add(
						accountNote.getContent());
				}
			}
		}

		document.addKeyword(
			"accountNoteGeneralContent",
			ArrayUtil.toStringArray(accountNoteGeneralContent.toArray()));
		document.addKeyword(
			"accountNoteGeneralContentArchived",
			ArrayUtil.toStringArray(
				accountNoteGeneralContentArchived.toArray()));
		document.addKeyword(
			"accountNoteSalesContent",
			ArrayUtil.toStringArray(accountNoteSalesContent.toArray()));
		document.addKeyword(
			"accountNoteSalesContentArchived",
			ArrayUtil.toStringArray(accountNoteSalesContentArchived.toArray()));
	}

	private void _contributeAddresses(
			Document document, List<Address> addresses)
		throws PortalException {

		List<String> cities = new ArrayList<>();
		List<String> countries = new ArrayList<>();
		List<String> regions = new ArrayList<>();
		List<String> streets = new ArrayList<>();
		List<String> zips = new ArrayList<>();

		for (Address address : addresses) {
			cities.add(StringUtil.toLowerCase(address.getCity()));

			Country country = address.getCountry();

			if (country.getCountryId() > 0) {
				countries.add(StringUtil.toLowerCase(country.getName()));
			}

			Region region = address.getRegion();

			if (region.getRegionId() > 0) {
				regions.add(StringUtil.toLowerCase(region.getName()));
			}

			streets.add(StringUtil.toLowerCase(address.getStreet1()));

			if (Validator.isNotNull(address.getStreet2())) {
				streets.add(StringUtil.toLowerCase(address.getStreet2()));
			}

			if (Validator.isNotNull(address.getStreet3())) {
				streets.add(StringUtil.toLowerCase(address.getStreet3()));
			}

			if (Validator.isNotNull(address.getZip())) {
				zips.add(StringUtil.toLowerCase(address.getZip()));
			}
		}

		document.addTextSortable(
			"addressCities", cities.toArray(new String[0]));
		document.addTextSortable(
			"addressCountries", countries.toArray(new String[0]));
		document.addTextSortable(
			"addressRegions", regions.toArray(new String[0]));
		document.addTextSortable(
			"addressStreets", streets.toArray(new String[0]));
		document.addTextSortable("addressZips", zips.toArray(new String[0]));
	}

	private void _contributeContacts(Document document, long accountId)
		throws PortalException {

		Set<String> contactEmailAddresses = new HashSet<>();
		Set<String> contactUuidContactRoleKeys = new HashSet<>();
		Set<String> contactUuids = new HashSet<>();
		Set<String> customerContactEmailAddresses = new HashSet<>();
		Set<String> customerContactUuids = new HashSet<>();
		Set<String> workerContactEmailAddresses = new HashSet<>();
		Set<String> workerContactUuids = new HashSet<>();

		List<ContactAccountRole> contactAccountRoles =
			_contactAccountRoleLocalService.getContactAccountRolesByAccountId(
				accountId);

		for (ContactAccountRole contactAccountRole : contactAccountRoles) {
			Contact contact = _contactLocalService.getContact(
				contactAccountRole.getContactId());
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactAccountRole.getContactRoleId());

			contactEmailAddresses.add(contact.getEmailAddress());

			contactUuidContactRoleKeys.add(
				contact.getUuid() + StringPool.UNDERLINE +
					contactRole.getContactRoleKey());

			contactUuids.add(contact.getUuid());

			String type = contactRole.getType();

			if (type.equals(Type.ACCOUNT_CUSTOMER.toString())) {
				customerContactEmailAddresses.add(contact.getEmailAddress());
				customerContactUuids.add(contact.getUuid());
			}
			else if (type.equals(Type.ACCOUNT_WORKER.toString())) {
				workerContactEmailAddresses.add(contact.getEmailAddress());
				workerContactUuids.add(contact.getUuid());
			}
		}

		document.addKeyword(
			"contactEmailAddresses",
			ArrayUtil.toStringArray(contactEmailAddresses.toArray()));
		document.addKeyword(
			"contactUuidContactRoleKeys",
			ArrayUtil.toStringArray(contactUuidContactRoleKeys.toArray()));
		document.addKeyword(
			"contactUuids", ArrayUtil.toStringArray(contactUuids.toArray()));
		document.addKeyword(
			"customerContactEmailAddresses",
			ArrayUtil.toStringArray(customerContactEmailAddresses.toArray()));
		document.addKeyword(
			"customerContactUuids",
			ArrayUtil.toStringArray(customerContactUuids.toArray()));
		document.addKeyword(
			"workerContactEmailAddresses",
			ArrayUtil.toStringArray(workerContactEmailAddresses.toArray()));
		document.addKeyword(
			"workerContactUuids",
			ArrayUtil.toStringArray(workerContactUuids.toArray()));
	}

	private void _contributeEntitlements(Document document, long accountId)
		throws PortalException {

		Set<String> entitlementNames = new HashSet<>();

		List<Entitlement> entitlements =
			_entitlementLocalService.getEntitlements(
				Account.class.getName(), accountId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (Entitlement entitlement : entitlements) {
			entitlementNames.add(entitlement.getName());
		}

		document.addKeyword(
			"entitlements",
			ArrayUtil.toStringArray(entitlementNames.toArray()));
	}

	private void _contributeExternalLinks(Document document, long accountId)
		throws PortalException {

		Set<String> externalLinkDomains = new HashSet<>();
		Set<String> externalLinkEntityIds = new HashSet<>();
		Set<String> externalLinkEntityNames = new HashSet<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				Account.class.getName(), accountId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ExternalLink externalLink : externalLinks) {
			String entityName =
				externalLink.getDomain() + StringPool.UNDERLINE +
					externalLink.getEntityName();

			String entityId =
				entityName + StringPool.UNDERLINE + externalLink.getEntityId();

			externalLinkDomains.add(externalLink.getDomain());
			externalLinkEntityIds.add(entityId);
			externalLinkEntityNames.add(entityName);
		}

		document.addKeyword(
			"externalLinkDomains",
			ArrayUtil.toStringArray(externalLinkDomains.toArray()));
		document.addKeyword(
			"externalLinkEntityIds",
			ArrayUtil.toStringArray(externalLinkEntityIds.toArray()));
		document.addKeyword(
			"externalLinkEntityNames",
			ArrayUtil.toStringArray(externalLinkEntityNames.toArray()));
	}

	private void _contributeProductPurchases(Document document, long accountId)
		throws PortalException {

		Set<String> productEntryKeys = new HashSet<>();
		Set<String> productPurchaseExternalLinkDomains = new HashSet<>();
		Set<String> productPurchaseExternalLinkEntityIds = new HashSet<>();
		Set<String> productPurchaseExternalLinkEntityNames = new HashSet<>();

		List<ProductPurchase> productPurchases =
			_productPurchaseLocalService.getAccountProductPurchases(
				accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		document.add(_getProductPurchasesField(productPurchases));

		for (ProductPurchase productPurchase : productPurchases) {
			productEntryKeys.add(productPurchase.getProductEntryKey());

			List<ExternalLink> externalLinks =
				_externalLinkLocalService.getExternalLinks(
					ProductPurchase.class.getName(),
					productPurchase.getProductPurchaseId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (ExternalLink externalLink : externalLinks) {
				String entityName =
					externalLink.getDomain() + StringPool.UNDERLINE +
						externalLink.getEntityName();

				String entityId =
					entityName + StringPool.UNDERLINE +
						externalLink.getEntityId();

				productPurchaseExternalLinkDomains.add(
					externalLink.getDomain());
				productPurchaseExternalLinkEntityIds.add(entityId);
				productPurchaseExternalLinkEntityNames.add(entityName);
			}
		}

		document.addKeyword(
			"productEntryKeys",
			ArrayUtil.toStringArray(productEntryKeys.toArray()));
		document.addKeyword(
			"productPurchaseExternalLinkDomains",
			ArrayUtil.toStringArray(
				productPurchaseExternalLinkDomains.toArray()));
		document.addKeyword(
			"productPurchaseExternalLinkEntityIds",
			ArrayUtil.toStringArray(
				productPurchaseExternalLinkEntityIds.toArray()));
		document.addKeyword(
			"productPurchaseExternalLinkEntityNames",
			ArrayUtil.toStringArray(
				productPurchaseExternalLinkEntityNames.toArray()));
	}

	private void _contributeTeams(Document document, long accountId)
		throws PortalException {

		Set<String> assignedTeamKeyTeamRoleKeys = new HashSet<>();
		Set<String> assignedTeamKeyTeamRoleKeyContactUuidContactRoleKeys =
			new HashSet<>();

		List<TeamAccountRole> teamAccountRoles =
			_teamAccountRoleLocalService.getTeamAccountRolesByAccountId(
				accountId);

		for (TeamAccountRole teamAccountRole : teamAccountRoles) {
			Team team = teamAccountRole.getTeam();
			TeamRole teamRole = teamAccountRole.getTeamRole();

			String assignedTeamKeyTeamRoleKey =
				team.getTeamKey() + StringPool.UNDERLINE +
					teamRole.getTeamRoleKey();

			assignedTeamKeyTeamRoleKeys.add(assignedTeamKeyTeamRoleKey);

			List<ContactTeamRole> contactTeamRoles =
				_contactTeamRoleLocalService.getContactTeamRolesByTeamId(
					team.getTeamId());

			for (ContactTeamRole contactTeamRole : contactTeamRoles) {
				Contact contact = _contactLocalService.getContact(
					contactTeamRole.getContactId());
				ContactRole contactRole =
					_contactRoleLocalService.getContactRole(
						contactTeamRole.getContactRoleId());

				StringBundler sb = new StringBundler(5);

				sb.append(assignedTeamKeyTeamRoleKey);
				sb.append(StringPool.UNDERLINE);
				sb.append(contact.getUuid());
				sb.append(StringPool.UNDERLINE);
				sb.append(contactRole.getContactRoleKey());

				assignedTeamKeyTeamRoleKeyContactUuidContactRoleKeys.add(
					sb.toString());
			}
		}

		document.addKeyword(
			"assignedTeamKeyTeamRoleKeys",
			ArrayUtil.toStringArray(assignedTeamKeyTeamRoleKeys.toArray()));
		document.addKeyword(
			"assignedTeamKeyTeamRoleKeyContactUuidContactRoleKeys",
			ArrayUtil.toStringArray(
				assignedTeamKeyTeamRoleKeyContactUuidContactRoleKeys.
					toArray()));

		Set<String> teamsAssignedToAccountKeyTeamRoleKeys = new HashSet<>();

		List<Team> teams = _teamLocalService.getAccountTeams(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Team team : teams) {
			List<TeamAccountRole> curTeamAccountRoles =
				_teamAccountRoleLocalService.getTeamAccountRoles(
					team.getTeamId());

			for (TeamAccountRole teamAccountRole : curTeamAccountRoles) {
				Account account = teamAccountRole.getAccount();
				TeamRole teamRole = teamAccountRole.getTeamRole();

				teamsAssignedToAccountKeyTeamRoleKeys.add(
					account.getAccountKey() + StringPool.UNDERLINE +
						teamRole.getTeamRoleKey());
			}
		}

		document.addKeyword(
			"teamsAssignedToAccountKeyTeamRoleKeys",
			ArrayUtil.toStringArray(
				teamsAssignedToAccountKeyTeamRoleKeys.toArray()));
	}

	private FieldArray _getProductPurchasesField(
			List<ProductPurchase> productPurchases)
		throws PortalException {

		FieldArray fieldArray = new FieldArray("productPurchases");

		for (ProductPurchase productPurchase : productPurchases) {
			ProductEntry productEntry = productPurchase.getProductEntry();

			Field field = new Field(StringPool.BLANK);

			Field statusField = new Field(Field.STATUS);

			statusField.setValue(String.valueOf(productPurchase.getStatus()));

			field.addField(statusField);

			Date endDate = productPurchase.getEndDate();

			if (endDate == null) {
				endDate = _END_DATE_PERPETUAL;
			}

			Field endDateField = new Field("endDate");

			endDateField.setDates(new Date[] {endDate});
			endDateField.setValue(_dateFormat.format(endDate));

			field.addField(endDateField);

			Field productEntryKeyField = new Field("productEntryKey");

			productEntryKeyField.setValue(productEntry.getProductEntryKey());

			field.addField(productEntryKeyField);

			Date startDate = productPurchase.getStartDate();

			if (startDate == null) {
				startDate = _START_DATE_PERPETUAL;
			}

			Field startDateField = new Field("startDate");

			startDateField.setDates(new Date[] {startDate});
			startDateField.setValue(_dateFormat.format(startDate));

			field.addField(startDateField);

			fieldArray.addField(field);
		}

		return fieldArray;
	}

	private static final Date _END_DATE_PERPETUAL = new Date(4102444800000L);

	private static final String _INDEX_DATE_FORMAT_PATTERN = PropsUtil.get(
		PropsKeys.INDEX_DATE_FORMAT_PATTERN);

	private static final Date _START_DATE_PERPETUAL = new Date(0L);

	private static final Log _log = LogFactoryUtil.getLog(
		AccountModelDocumentContributor.class);

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactAccountRoleLocalService _contactAccountRoleLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

	private final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			_INDEX_DATE_FORMAT_PATTERN);

	@Reference
	private EntitlementLocalService _entitlementLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

	@Reference
	private TeamAccountRoleLocalService _teamAccountRoleLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private UserLocalService _userLocalService;

}