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

package com.liferay.osb.koroneiki.phloem.rest.internal.graphql.query.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Country;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.CountryRegion;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AuditEntryResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactAccountViewResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.CountryRegionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.CountryResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.NoteResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.PostalAddressResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class Query {

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setAuditEntryResourceComponentServiceObjects(
		ComponentServiceObjects<AuditEntryResource>
			auditEntryResourceComponentServiceObjects) {

		_auditEntryResourceComponentServiceObjects =
			auditEntryResourceComponentServiceObjects;
	}

	public static void setContactResourceComponentServiceObjects(
		ComponentServiceObjects<ContactResource>
			contactResourceComponentServiceObjects) {

		_contactResourceComponentServiceObjects =
			contactResourceComponentServiceObjects;
	}

	public static void setContactAccountViewResourceComponentServiceObjects(
		ComponentServiceObjects<ContactAccountViewResource>
			contactAccountViewResourceComponentServiceObjects) {

		_contactAccountViewResourceComponentServiceObjects =
			contactAccountViewResourceComponentServiceObjects;
	}

	public static void setContactRoleResourceComponentServiceObjects(
		ComponentServiceObjects<ContactRoleResource>
			contactRoleResourceComponentServiceObjects) {

		_contactRoleResourceComponentServiceObjects =
			contactRoleResourceComponentServiceObjects;
	}

	public static void setCountryResourceComponentServiceObjects(
		ComponentServiceObjects<CountryResource>
			countryResourceComponentServiceObjects) {

		_countryResourceComponentServiceObjects =
			countryResourceComponentServiceObjects;
	}

	public static void setCountryRegionResourceComponentServiceObjects(
		ComponentServiceObjects<CountryRegionResource>
			countryRegionResourceComponentServiceObjects) {

		_countryRegionResourceComponentServiceObjects =
			countryRegionResourceComponentServiceObjects;
	}

	public static void setEntitlementDefinitionResourceComponentServiceObjects(
		ComponentServiceObjects<EntitlementDefinitionResource>
			entitlementDefinitionResourceComponentServiceObjects) {

		_entitlementDefinitionResourceComponentServiceObjects =
			entitlementDefinitionResourceComponentServiceObjects;
	}

	public static void setExternalLinkResourceComponentServiceObjects(
		ComponentServiceObjects<ExternalLinkResource>
			externalLinkResourceComponentServiceObjects) {

		_externalLinkResourceComponentServiceObjects =
			externalLinkResourceComponentServiceObjects;
	}

	public static void setNoteResourceComponentServiceObjects(
		ComponentServiceObjects<NoteResource>
			noteResourceComponentServiceObjects) {

		_noteResourceComponentServiceObjects =
			noteResourceComponentServiceObjects;
	}

	public static void setPostalAddressResourceComponentServiceObjects(
		ComponentServiceObjects<PostalAddressResource>
			postalAddressResourceComponentServiceObjects) {

		_postalAddressResourceComponentServiceObjects =
			postalAddressResourceComponentServiceObjects;
	}

	public static void setProductResourceComponentServiceObjects(
		ComponentServiceObjects<ProductResource>
			productResourceComponentServiceObjects) {

		_productResourceComponentServiceObjects =
			productResourceComponentServiceObjects;
	}

	public static void setProductConsumptionResourceComponentServiceObjects(
		ComponentServiceObjects<ProductConsumptionResource>
			productConsumptionResourceComponentServiceObjects) {

		_productConsumptionResourceComponentServiceObjects =
			productConsumptionResourceComponentServiceObjects;
	}

	public static void setProductPurchaseResourceComponentServiceObjects(
		ComponentServiceObjects<ProductPurchaseResource>
			productPurchaseResourceComponentServiceObjects) {

		_productPurchaseResourceComponentServiceObjects =
			productPurchaseResourceComponentServiceObjects;
	}

	public static void setProductPurchaseViewResourceComponentServiceObjects(
		ComponentServiceObjects<ProductPurchaseViewResource>
			productPurchaseViewResourceComponentServiceObjects) {

		_productPurchaseViewResourceComponentServiceObjects =
			productPurchaseViewResourceComponentServiceObjects;
	}

	public static void setTeamResourceComponentServiceObjects(
		ComponentServiceObjects<TeamResource>
			teamResourceComponentServiceObjects) {

		_teamResourceComponentServiceObjects =
			teamResourceComponentServiceObjects;
	}

	public static void setTeamRoleResourceComponentServiceObjects(
		ComponentServiceObjects<TeamRoleResource>
			teamRoleResourceComponentServiceObjects) {

		_teamRoleResourceComponentServiceObjects =
			teamRoleResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accounts(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the accounts. Results can be paginated, filtered, searched, and sorted."
	)
	public AccountPage accounts(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getAccountsPage(
					search,
					_filterBiFunction.apply(accountResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(accountResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account by the external link.")
	public AccountPage accountByExternalLinkDomainEntityNameEntity(
			@GraphQLName("domain") String domain,
			@GraphQLName("entityName") String entityName,
			@GraphQLName("entityId") String entityId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.
					getAccountByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {account(accountKey: ___){assignedTeams, code, contactEmailAddress, contacts, customerContacts, dataRegion, dateCreated, dateModified, description, entitlements, externalLinks, faxNumber, internal, key, language, logoId, name, parentAccountKey, phoneNumber, postalAddresses, productPurchases, profileEmailAddress, properties, region, status, tier, website, workerContacts}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account.")
	public Account account(@GraphQLName("accountKey") String accountKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.getAccount(accountKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountChildAccounts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's child accounts.")
	public AccountPage accountChildAccounts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getAccountChildAccountsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidAccounts(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact's accounts.")
	public AccountPage contactByUuidContactUuidAccounts(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getContactByUuidContactUuidAccountsPage(
					contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyAssignedAccounts(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team's assigned accounts.")
	public AccountPage teamTeamKeyAssignedAccounts(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getTeamTeamKeyAssignedAccountsPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyAuditEntries(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's audit history.")
	public AuditEntryPage accountAccountKeyAuditEntries(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getAccountAccountKeyAuditEntriesPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {auditEntry(auditEntryKey: ___){action, agentName, agentUID, auditSetId, dateCreated, description, field, fieldClassLabel, fieldClassPK, key, newValue, oldValue, summary}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the audit entry.")
	public AuditEntry auditEntry(
			@GraphQLName("auditEntryKey") String auditEntryKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> auditEntryResource.getAuditEntry(
				auditEntryKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRoleContactRoleKeyAuditEntries(contactRoleKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact role's audit history.")
	public AuditEntryPage contactRoleContactRoleKeyAuditEntries(
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getContactRoleContactRoleKeyAuditEntriesPage(
					contactRoleKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidAuditEntries(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact's audit history.")
	public AuditEntryPage contactByUuidContactUuidAuditEntries(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getContactByUuidContactUuidAuditEntriesPage(
					contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRoleTeamRoleKeyAuditEntries(page: ___, pageSize: ___, teamRoleKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team role's audit history.")
	public AuditEntryPage teamRoleTeamRoleKeyAuditEntries(
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getTeamRoleTeamRoleKeyAuditEntriesPage(
					teamRoleKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyAuditEntries(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team's audit history.")
	public AuditEntryPage teamTeamKeyAuditEntries(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getTeamTeamKeyAuditEntriesPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyContacts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's customer and worker contacts."
	)
	public ContactPage accountAccountKeyContacts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getAccountAccountKeyContactsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyCustomerContacts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's customer contacts.")
	public ContactPage accountAccountKeyCustomerContacts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getAccountAccountKeyCustomerContactsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyWorkerContacts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's worker contacts.")
	public ContactPage accountAccountKeyWorkerContacts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getAccountAccountKeyWorkerContactsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contacts(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the contacts. Results can be paginated, filtered, searched, and sorted."
	)
	public ContactPage contacts(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getContactsPage(
					search,
					_filterBiFunction.apply(contactResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(contactResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByEmailAddresEmailAddress(emailAddress: ___){accounts, contactRoles, dateCreated, dateModified, emailAddress, emailAddressVerified, entitlements, externalLinks, firstName, key, languageId, lastName, middleName, teams, uuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact.")
	public Contact contactByEmailAddresEmailAddress(
			@GraphQLName("emailAddress") String emailAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.getContactByEmailAddresEmailAddress(
					emailAddress));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuid(contactUuid: ___){accounts, contactRoles, dateCreated, dateModified, emailAddress, emailAddressVerified, entitlements, externalLinks, firstName, key, languageId, lastName, middleName, teams, uuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact.")
	public Contact contactByUuidContactUuid(
			@GraphQLName("contactUuid") String contactUuid)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.getContactByUuidContactUuid(
				contactUuid));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyContacts(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team's contacts.")
	public ContactPage teamTeamKeyContacts(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getTeamTeamKeyContactsPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidContactAccountViews(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the contact's accounts and associated contact roles."
	)
	public ContactAccountViewPage contactByUuidContactUuidContactAccountViews(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactAccountViewResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactAccountViewResource -> new ContactAccountViewPage(
				contactAccountViewResource.
					getContactByUuidContactUuidContactAccountViewsPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyContactByEmailAddresContactEmailAddressRoles(accountKey: ___, contactEmailAddress: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's contact's customer and worker contact roles."
	)
	public ContactRolePage
			accountAccountKeyContactByEmailAddresContactEmailAddressRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactEmailAddress") String contactEmailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
						accountKey, contactEmailAddress,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyContactByUuidContactUuidRoles(accountKey: ___, contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's contact's customer and worker contact roles."
	)
	public ContactRolePage accountAccountKeyContactByUuidContactUuidRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyContactByUuidContactUuidRolesPage(
						accountKey, contactUuid,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyCustomerContactByEmailAddresContactEmailAddressRoles(accountKey: ___, contactEmailAddress: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's contact's customer contact roles."
	)
	public ContactRolePage
			accountAccountKeyCustomerContactByEmailAddresContactEmailAddressRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactEmailAddress") String contactEmailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
						accountKey, contactEmailAddress,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyCustomerContactByUuidContactUuidRoles(accountKey: ___, contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's contact's customer contact roles."
	)
	public ContactRolePage
			accountAccountKeyCustomerContactByUuidContactUuidRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
						accountKey, contactUuid,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyWorkerContactByEmailAddresContactEmailAddressRoles(accountKey: ___, contactEmailAddress: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's contact's customer contact roles."
	)
	public ContactRolePage
			accountAccountKeyWorkerContactByEmailAddresContactEmailAddressRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactEmailAddress") String contactEmailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
						accountKey, contactEmailAddress,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyWorkerContactByUuidContactUuidRoles(accountKey: ___, contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's contact's customer contact roles."
	)
	public ContactRolePage accountAccountKeyWorkerContactByUuidContactUuidRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
						accountKey, contactUuid,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRoles(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the contact roles. Results can be paginated, filtered, searched, and sorted."
	)
	public ContactRolePage contactRoles(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.getContactRolesPage(
					search,
					_filterBiFunction.apply(contactRoleResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(contactRoleResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRole(contactRoleKey: ___){dateCreated, dateModified, description, externalLinks, key, name, system, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact role.")
	public ContactRole contactRole(
			@GraphQLName("contactRoleKey") String contactRoleKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.getContactRole(
				contactRoleKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRole(contactRoleName: ___, contactRoleType: ___){dateCreated, dateModified, description, externalLinks, key, name, system, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact role by name.")
	public ContactRole contactRole(
			@GraphQLName("contactRoleType") String contactRoleType,
			@GraphQLName("contactRoleName") String contactRoleName)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.getContactRole(
				contactRoleType, contactRoleName));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyContactByEmailAddresEmailAddressRoles(emailAddress: ___, page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team's contact's contact roles.")
	public ContactRolePage teamTeamKeyContactByEmailAddresEmailAddressRoles(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("emailAddress") String emailAddress,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
						teamKey, emailAddress, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyContactByUuidContactUuidRoles(contactUuid: ___, page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team's contact's contact roles.")
	public ContactRolePage teamTeamKeyContactByUuidContactUuidRoles(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getTeamTeamKeyContactByUuidContactUuidRolesPage(
						teamKey, contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {countries{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the countries.")
	public CountryPage countries() throws Exception {
		return _applyComponentServiceObjects(
			_countryResourceComponentServiceObjects,
			this::_populateResourceContext,
			countryResource -> new CountryPage(
				countryResource.getCountriesPage()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {countryRegions{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the country regions.")
	public CountryRegionPage countryRegions() throws Exception {
		return _applyComponentServiceObjects(
			_countryRegionResourceComponentServiceObjects,
			this::_populateResourceContext,
			countryRegionResource -> new CountryRegionPage(
				countryRegionResource.getCountryRegionsPage()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountEntitlementDefinitions(page: ___, pageSize: ___, search: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account entitlement definitions. Results can be paginated, filtered, searched, and sorted."
	)
	public EntitlementDefinitionPage accountEntitlementDefinitions(
			@GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource -> new EntitlementDefinitionPage(
				entitlementDefinitionResource.
					getAccountEntitlementDefinitionsPage(
						search, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactEntitlementDefinitions(page: ___, pageSize: ___, search: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the contact entitlement definitions."
	)
	public EntitlementDefinitionPage contactEntitlementDefinitions(
			@GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource -> new EntitlementDefinitionPage(
				entitlementDefinitionResource.
					getContactEntitlementDefinitionsPage(
						search, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {entitlementDefinition(entitlementDefinitionKey: ___){dateCreated, dateModified, definition, description, externalLinks, key, name, status}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the entitlement definition.")
	public EntitlementDefinition entitlementDefinition(
			@GraphQLName("entitlementDefinitionKey") String
				entitlementDefinitionKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.getEntitlementDefinition(
					entitlementDefinitionKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyExternalLinks(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's external links.")
	public ExternalLinkPage accountAccountKeyExternalLinks(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.getAccountAccountKeyExternalLinksPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRoleContactRoleKeyExternalLinks(contactRoleKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact role's external links.")
	public ExternalLinkPage contactRoleContactRoleKeyExternalLinks(
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.
					getContactRoleContactRoleKeyExternalLinksPage(
						contactRoleKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidExternalLinks(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contact's external links.")
	public ExternalLinkPage contactByUuidContactUuidExternalLinks(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.
					getContactByUuidContactUuidExternalLinksPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {externalLink(externalLinkKey: ___){dateCreated, domain, entityId, entityName, key, url}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the external link.")
	public ExternalLink externalLink(
			@GraphQLName("externalLinkKey") String externalLinkKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> externalLinkResource.getExternalLink(
				externalLinkKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumptionProductConsumptionKeyExternalLinks(page: ___, pageSize: ___, productConsumptionKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the product consumption's external links."
	)
	public ExternalLinkPage
			productConsumptionProductConsumptionKeyExternalLinks(
				@GraphQLName("productConsumptionKey") String
					productConsumptionKey,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.
					getProductConsumptionProductConsumptionKeyExternalLinksPage(
						productConsumptionKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchaseProductPurchaseKeyExternalLinks(page: ___, pageSize: ___, productPurchaseKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the product purchase's external links."
	)
	public ExternalLinkPage productPurchaseProductPurchaseKeyExternalLinks(
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.
					getProductPurchaseProductPurchaseKeyExternalLinksPage(
						productPurchaseKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productProductKeyExternalLinks(page: ___, pageSize: ___, productKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the product's external links.")
	public ExternalLinkPage productProductKeyExternalLinks(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.getProductProductKeyExternalLinksPage(
					productKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyExternalLinks(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team's external links.")
	public ExternalLinkPage teamTeamKeyExternalLinks(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.getTeamTeamKeyExternalLinksPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyNotes(accountKey: ___, page: ___, pageSize: ___, priority: ___, status: ___, type: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's notes.")
	public NotePage accountAccountKeyNotes(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("priority") Integer priority,
			@GraphQLName("status") String status,
			@GraphQLName("type") String type,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_noteResourceComponentServiceObjects,
			this::_populateResourceContext,
			noteResource -> new NotePage(
				noteResource.getAccountAccountKeyNotesPage(
					accountKey, priority, status, type,
					Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {note(noteKey: ___){content, creatorName, creatorUID, dateCreated, dateModified, format, key, modifierName, modifierUID, priority, status, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the note.")
	public Note note(@GraphQLName("noteKey") String noteKey) throws Exception {
		return _applyComponentServiceObjects(
			_noteResourceComponentServiceObjects,
			this::_populateResourceContext,
			noteResource -> noteResource.getNote(noteKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyPostalAddresses(accountKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's postal addresses.")
	public PostalAddressPage accountAccountKeyPostalAddresses(
			@GraphQLName("accountKey") String accountKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> new PostalAddressPage(
				postalAddressResource.getAccountAccountKeyPostalAddressesPage(
					accountKey)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {postalAddress(postalAddressId: ___){addressCountry, addressLocality, addressRegion, addressType, id, mailing, postalCode, primary, streetAddressLine1, streetAddressLine2, streetAddressLine3}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the postal address.")
	public PostalAddress postalAddress(
			@GraphQLName("postalAddressId") Long postalAddressId)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> postalAddressResource.getPostalAddress(
				postalAddressId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {products(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the products. Results can be paginated, filtered, searched, and sorted."
	)
	public ProductPage products(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> new ProductPage(
				productResource.getProductsPage(
					search,
					_filterBiFunction.apply(productResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(productResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the products by the external link.")
	public ProductPage productByExternalLinkDomainEntityNameEntity(
			@GraphQLName("domain") String domain,
			@GraphQLName("entityName") String entityName,
			@GraphQLName("entityId") String entityId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> new ProductPage(
				productResource.
					getProductByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productByNameProductName(productName: ___){dateCreated, dateModified, externalLinks, key, name, properties}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the product with the exact name.")
	public Product productByNameProductName(
			@GraphQLName("productName") String productName)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.getProductByNameProductName(
				productName));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {product(productKey: ___){dateCreated, dateModified, externalLinks, key, name, properties}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the product.")
	public Product product(@GraphQLName("productKey") String productKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.getProduct(productKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductConsumptions(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's product consumptions.")
	public ProductConsumptionPage accountAccountKeyProductConsumptions(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.
					getAccountAccountKeyProductConsumptionsPage(
						accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidProductConsumptions(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contacts product consumptions.")
	public ProductConsumptionPage contactByUuidContactUuidProductConsumptions(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.
					getContactByUuidContactUuidProductConsumptionsPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumptions(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the product consumptions. Results can be paginated, filtered, searched, and sorted."
	)
	public ProductConsumptionPage productConsumptions(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.getProductConsumptionsPage(
					search,
					_filterBiFunction.apply(
						productConsumptionResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						productConsumptionResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumptionByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the product consumptions by the external link."
	)
	public ProductConsumptionPage
			productConsumptionByExternalLinkDomainEntityNameEntity(
				@GraphQLName("domain") String domain,
				@GraphQLName("entityName") String entityName,
				@GraphQLName("entityId") String entityId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.
					getProductConsumptionByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumption(productConsumptionKey: ___){accountKey, dateCreated, endDate, externalLinks, key, productKey, productPurchaseKey, properties, startDate}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the product consumption.")
	public ProductConsumption productConsumption(
			@GraphQLName("productConsumptionKey") String productConsumptionKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.getProductConsumption(
					productConsumptionKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductPurchases(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's product purchases.")
	public ProductPurchasePage accountAccountKeyProductPurchases(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.
					getAccountAccountKeyProductPurchasesPage(
						accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidProductPurchases(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the contacts product purchases.")
	public ProductPurchasePage contactByUuidContactUuidProductPurchases(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.
					getContactByUuidContactUuidProductPurchasesPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchases(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the product purchases. Results can be paginated, filtered, searched, and sorted."
	)
	public ProductPurchasePage productPurchases(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.getProductPurchasesPage(
					search,
					_filterBiFunction.apply(
						productPurchaseResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						productPurchaseResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchaseByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the product purchases by the external link."
	)
	public ProductPurchasePage
			productPurchaseByExternalLinkDomainEntityNameEntity(
				@GraphQLName("domain") String domain,
				@GraphQLName("entityName") String entityName,
				@GraphQLName("entityId") String entityId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.
					getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchase(productPurchaseKey: ___){accountKey, dateCreated, endDate, externalLinks, key, originalEndDate, perpetual, product, productConsumptions, productKey, properties, quantity, startDate, status}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the product purchase.")
	public ProductPurchase productPurchase(
			@GraphQLName("productPurchaseKey") String productPurchaseKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.getProductPurchase(productPurchaseKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductProductKeyProductPurchaseView(accountKey: ___, productKey: ___){product, productConsumptions, productPurchases}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's product consumptions and product purchases based on product."
	)
	public ProductPurchaseView
			accountAccountKeyProductProductKeyProductPurchaseView(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("productKey") String productKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseViewResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseViewResource ->
				productPurchaseViewResource.
					getAccountAccountKeyProductProductKeyProductPurchaseView(
						accountKey, productKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchaseViews(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's products and related product consumptions and product purchases."
	)
	public ProductPurchaseViewPage productPurchaseViews(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseViewResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseViewResource -> new ProductPurchaseViewPage(
				productPurchaseViewResource.getProductPurchaseViewsPage(
					search,
					_filterBiFunction.apply(
						productPurchaseViewResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						productPurchaseViewResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyAssignedTeams(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the teams assigned to the account.")
	public TeamPage accountAccountKeyAssignedTeams(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getAccountAccountKeyAssignedTeamsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyTeams(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the account's teams.")
	public TeamPage accountAccountKeyTeams(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getAccountAccountKeyTeamsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teams(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the teams. Results can be paginated, filtered, searched, and sorted."
	)
	public TeamPage teams(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getTeamsPage(
					search, _filterBiFunction.apply(teamResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(teamResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team by the external link.")
	public TeamPage teamByExternalLinkDomainEntityNameEntity(
			@GraphQLName("domain") String domain,
			@GraphQLName("entityName") String entityName,
			@GraphQLName("entityId") String entityId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId,
					Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {team(teamKey: ___){account, accountKey, contacts, dateCreated, dateModified, externalLinks, key, name, system, teamRoles}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team.")
	public Team team(@GraphQLName("teamKey") String teamKey) throws Exception {
		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.getTeam(teamKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyAssignedTeamTeamKeyRoles(accountKey: ___, page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the roles of the team for the account."
	)
	public TeamRolePage accountAccountKeyAssignedTeamTeamKeyRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> new TeamRolePage(
				teamRoleResource.
					getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
						accountKey, teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRoles(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the team roles. Results can be paginated, filtered, searched, and sorted."
	)
	public TeamRolePage teamRoles(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> new TeamRolePage(
				teamRoleResource.getTeamRolesPage(
					search,
					_filterBiFunction.apply(teamRoleResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(teamRoleResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRole(teamRoleKey: ___){dateCreated, dateModified, description, key, name, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team role.")
	public TeamRole teamRole(@GraphQLName("teamRoleKey") String teamRoleKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.getTeamRole(teamRoleKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRole(teamRoleName: ___, teamRoleType: ___){dateCreated, dateModified, description, key, name, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the team role by name.")
	public TeamRole teamRole(
			@GraphQLName("teamRoleType") String teamRoleType,
			@GraphQLName("teamRoleName") String teamRoleName)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.getTeamRole(
				teamRoleType, teamRoleName));
	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyCustomerContactsPageTypeExtension {

		public GetAccountAccountKeyCustomerContactsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's customer contacts."
		)
		public ContactPage accountKeyCustomerContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getAccountAccountKeyCustomerContactsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Product.class)
	public class GetProductProductKeyExternalLinksPageTypeExtension {

		public GetProductProductKeyExternalLinksPageTypeExtension(
			Product product) {

			_product = product;
		}

		@GraphQLField(description = "Retrieves the product's external links.")
		public ExternalLinkPage productKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.getProductProductKeyExternalLinksPage(
						_product.getKey(), Pagination.of(page, pageSize))));
		}

		private Product _product;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyProductPurchasesPageTypeExtension {

		public GetAccountAccountKeyProductPurchasesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's product purchases."
		)
		public ProductPurchasePage accountKeyProductPurchases(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productPurchaseResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productPurchaseResource -> new ProductPurchasePage(
					productPurchaseResource.
						getAccountAccountKeyProductPurchasesPage(
							_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyAssignedTeamTeamKeyRolesPageTypeExtension {

		public GetAccountAccountKeyAssignedTeamTeamKeyRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the roles of the team for the account."
		)
		public TeamRolePage accountKeyAssignedTeamTeamKeyRoles(
				@GraphQLName("teamKey") String teamKey,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_teamRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				teamRoleResource -> new TeamRolePage(
					teamRoleResource.
						getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
							_account.getKey(), teamKey,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyAssignedTeamsPageTypeExtension {

		public GetAccountAccountKeyAssignedTeamsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the teams assigned to the account."
		)
		public TeamPage accountKeyAssignedTeams(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_teamResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				teamResource -> new TeamPage(
					teamResource.getAccountAccountKeyAssignedTeamsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyNotesPageTypeExtension {

		public GetAccountAccountKeyNotesPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField(description = "Retrieves the account's notes.")
		public NotePage accountKeyNotes(
				@GraphQLName("priority") Integer priority,
				@GraphQLName("status") String status,
				@GraphQLName("type") String type,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_noteResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				noteResource -> new NotePage(
					noteResource.getAccountAccountKeyNotesPage(
						_account.getKey(), priority, status, type,
						Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageTypeExtension {

		public GetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's contact's customer and worker contact roles."
		)
		public ContactRolePage
				accountKeyContactByEmailAddresContactEmailAddressRoles(
					@GraphQLName("contactEmailAddress") String
						contactEmailAddress,
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
							_account.getKey(), contactEmailAddress,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyPostalAddressesPageTypeExtension {

		public GetAccountAccountKeyPostalAddressesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(description = "Retrieves the account's postal addresses.")
		public PostalAddressPage accountKeyPostalAddresses() throws Exception {
			return _applyComponentServiceObjects(
				_postalAddressResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				postalAddressResource -> new PostalAddressPage(
					postalAddressResource.
						getAccountAccountKeyPostalAddressesPage(
							_account.getKey())));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyWorkerContactsPageTypeExtension {

		public GetAccountAccountKeyWorkerContactsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(description = "Retrieves the account's worker contacts.")
		public ContactPage accountKeyWorkerContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getAccountAccountKeyWorkerContactsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyTeamsPageTypeExtension {

		public GetAccountAccountKeyTeamsPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField(description = "Retrieves the account's teams.")
		public TeamPage accountKeyTeams(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_teamResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				teamResource -> new TeamPage(
					teamResource.getAccountAccountKeyTeamsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyExternalLinksPageTypeExtension {

		public GetTeamTeamKeyExternalLinksPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField(description = "Retrieves the team's external links.")
		public ExternalLinkPage teamKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.getTeamTeamKeyExternalLinksPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidAccountsPageTypeExtension {

		public GetContactByUuidContactUuidAccountsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField(description = "Retrieves the contact's accounts.")
		public AccountPage byUuidContactUuidAccounts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> new AccountPage(
					accountResource.getContactByUuidContactUuidAccountsPage(
						_contact.getUuid(), Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageTypeExtension {

		public GetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's contact's customer contact roles."
		)
		public ContactRolePage
				accountKeyWorkerContactByEmailAddresContactEmailAddressRoles(
					@GraphQLName("contactEmailAddress") String
						contactEmailAddress,
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
							_account.getKey(), contactEmailAddress,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyWorkerContactByUuidContactUuidRolesPageTypeExtension {

		public GetAccountAccountKeyWorkerContactByUuidContactUuidRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's contact's customer contact roles."
		)
		public ContactRolePage accountKeyWorkerContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
							_account.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyExternalLinksPageTypeExtension {

		public GetAccountAccountKeyExternalLinksPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(description = "Retrieves the account's external links.")
		public ExternalLinkPage accountKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.getAccountAccountKeyExternalLinksPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyAuditEntriesPageTypeExtension {

		public GetAccountAccountKeyAuditEntriesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(description = "Retrieves the account's audit history.")
		public AuditEntryPage accountKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.getAccountAccountKeyAuditEntriesPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyProductConsumptionsPageTypeExtension {

		public GetAccountAccountKeyProductConsumptionsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's product consumptions."
		)
		public ProductConsumptionPage accountKeyProductConsumptions(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productConsumptionResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productConsumptionResource -> new ProductConsumptionPage(
					productConsumptionResource.
						getAccountAccountKeyProductConsumptionsPage(
							_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Team.class)
	public class
		GetTeamTeamKeyContactByEmailAddresEmailAddressRolesPageTypeExtension {

		public GetTeamTeamKeyContactByEmailAddresEmailAddressRolesPageTypeExtension(
			Team team) {

			_team = team;
		}

		@GraphQLField(
			description = "Retrieves the team's contact's contact roles."
		)
		public ContactRolePage teamKeyContactByEmailAddresEmailAddressRoles(
				@GraphQLName("emailAddress") String emailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
							_team.getKey(), emailAddress,
							Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyAssignedAccountsPageTypeExtension {

		public GetTeamTeamKeyAssignedAccountsPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField(description = "Retrieves the team's assigned accounts.")
		public AccountPage teamKeyAssignedAccounts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> new AccountPage(
					accountResource.getTeamTeamKeyAssignedAccountsPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyContactsPageTypeExtension {

		public GetAccountAccountKeyContactsPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's customer and worker contacts."
		)
		public ContactPage accountKeyContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getAccountAccountKeyContactsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountChildAccountsPageTypeExtension {

		public GetAccountChildAccountsPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField(description = "Retrieves the account's child accounts.")
		public AccountPage childAccounts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> new AccountPage(
					accountResource.getAccountChildAccountsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Contact.class)
	public class
		GetContactByUuidContactUuidContactAccountViewsPageTypeExtension {

		public GetContactByUuidContactUuidContactAccountViewsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField(
			description = "Retrieves the contact's accounts and associated contact roles."
		)
		public ContactAccountViewPage byUuidContactUuidContactAccountViews(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactAccountViewResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactAccountViewResource -> new ContactAccountViewPage(
					contactAccountViewResource.
						getContactByUuidContactUuidContactAccountViewsPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyContactByUuidContactUuidRolesPageTypeExtension {

		public GetAccountAccountKeyContactByUuidContactUuidRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's contact's customer and worker contact roles."
		)
		public ContactRolePage accountKeyContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyContactByUuidContactUuidRolesPage(
							_account.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyProductProductKeyProductPurchaseViewTypeExtension {

		public GetAccountAccountKeyProductProductKeyProductPurchaseViewTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's product consumptions and product purchases based on product."
		)
		public ProductPurchaseView
				accountKeyProductProductKeyProductPurchaseView(
					@GraphQLName("productKey") String productKey)
			throws Exception {

			return _applyComponentServiceObjects(
				_productPurchaseViewResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productPurchaseViewResource ->
					productPurchaseViewResource.
						getAccountAccountKeyProductProductKeyProductPurchaseView(
							_account.getKey(), productKey));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(TeamRole.class)
	public class GetTeamRoleTeamRoleKeyAuditEntriesPageTypeExtension {

		public GetTeamRoleTeamRoleKeyAuditEntriesPageTypeExtension(
			TeamRole teamRole) {

			_teamRole = teamRole;
		}

		@GraphQLField(description = "Retrieves the team role's audit history.")
		public AuditEntryPage teamRoleKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.getTeamRoleTeamRoleKeyAuditEntriesPage(
						_teamRole.getKey(), Pagination.of(page, pageSize))));
		}

		private TeamRole _teamRole;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyContactByUuidContactUuidRolesPageTypeExtension {

		public GetTeamTeamKeyContactByUuidContactUuidRolesPageTypeExtension(
			Team team) {

			_team = team;
		}

		@GraphQLField(
			description = "Retrieves the team's contact's contact roles."
		)
		public ContactRolePage teamKeyContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getTeamTeamKeyContactByUuidContactUuidRolesPage(
							_team.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(ProductConsumption.class)
	public class
		GetProductConsumptionProductConsumptionKeyExternalLinksPageTypeExtension {

		public GetProductConsumptionProductConsumptionKeyExternalLinksPageTypeExtension(
			ProductConsumption productConsumption) {

			_productConsumption = productConsumption;
		}

		@GraphQLField(
			description = "Retrieves the product consumption's external links."
		)
		public ExternalLinkPage productConsumptionKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.
						getProductConsumptionProductConsumptionKeyExternalLinksPage(
							_productConsumption.getKey(),
							Pagination.of(page, pageSize))));
		}

		private ProductConsumption _productConsumption;

	}

	@GraphQLTypeExtension(Contact.class)
	public class
		GetContactByUuidContactUuidProductConsumptionsPageTypeExtension {

		public GetContactByUuidContactUuidProductConsumptionsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField(
			description = "Retrieves the contacts product consumptions."
		)
		public ProductConsumptionPage byUuidContactUuidProductConsumptions(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productConsumptionResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productConsumptionResource -> new ProductConsumptionPage(
					productConsumptionResource.
						getContactByUuidContactUuidProductConsumptionsPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyAuditEntriesPageTypeExtension {

		public GetTeamTeamKeyAuditEntriesPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField(description = "Retrieves the team's audit history.")
		public AuditEntryPage teamKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.getTeamTeamKeyAuditEntriesPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(ContactRole.class)
	public class GetContactRoleContactRoleKeyExternalLinksPageTypeExtension {

		public GetContactRoleContactRoleKeyExternalLinksPageTypeExtension(
			ContactRole contactRole) {

			_contactRole = contactRole;
		}

		@GraphQLField(
			description = "Retrieves the contact role's external links."
		)
		public ExternalLinkPage contactRoleKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.
						getContactRoleContactRoleKeyExternalLinksPage(
							_contactRole.getKey(),
							Pagination.of(page, pageSize))));
		}

		private ContactRole _contactRole;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidExternalLinksPageTypeExtension {

		public GetContactByUuidContactUuidExternalLinksPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField(description = "Retrieves the contact's external links.")
		public ExternalLinkPage byUuidContactUuidExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.
						getContactByUuidContactUuidExternalLinksPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyContactsPageTypeExtension {

		public GetTeamTeamKeyContactsPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField(description = "Retrieves the team's contacts.")
		public ContactPage teamKeyContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getTeamTeamKeyContactsPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(ContactRole.class)
	public class GetContactRoleContactRoleKeyAuditEntriesPageTypeExtension {

		public GetContactRoleContactRoleKeyAuditEntriesPageTypeExtension(
			ContactRole contactRole) {

			_contactRole = contactRole;
		}

		@GraphQLField(
			description = "Retrieves the contact role's audit history."
		)
		public AuditEntryPage contactRoleKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.
						getContactRoleContactRoleKeyAuditEntriesPage(
							_contactRole.getKey(),
							Pagination.of(page, pageSize))));
		}

		private ContactRole _contactRole;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidAuditEntriesPageTypeExtension {

		public GetContactByUuidContactUuidAuditEntriesPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField(description = "Retrieves the contact's audit history.")
		public AuditEntryPage byUuidContactUuidAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.
						getContactByUuidContactUuidAuditEntriesPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageTypeExtension {

		public GetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's contact's customer contact roles."
		)
		public ContactRolePage
				accountKeyCustomerContactByEmailAddresContactEmailAddressRoles(
					@GraphQLName("contactEmailAddress") String
						contactEmailAddress,
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
							_account.getKey(), contactEmailAddress,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(ProductPurchase.class)
	public class
		GetProductPurchaseProductPurchaseKeyExternalLinksPageTypeExtension {

		public GetProductPurchaseProductPurchaseKeyExternalLinksPageTypeExtension(
			ProductPurchase productPurchase) {

			_productPurchase = productPurchase;
		}

		@GraphQLField(
			description = "Retrieves the product purchase's external links."
		)
		public ExternalLinkPage productPurchaseKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.
						getProductPurchaseProductPurchaseKeyExternalLinksPage(
							_productPurchase.getKey(),
							Pagination.of(page, pageSize))));
		}

		private ProductPurchase _productPurchase;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidProductPurchasesPageTypeExtension {

		public GetContactByUuidContactUuidProductPurchasesPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField(description = "Retrieves the contacts product purchases.")
		public ProductPurchasePage byUuidContactUuidProductPurchases(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productPurchaseResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productPurchaseResource -> new ProductPurchasePage(
					productPurchaseResource.
						getContactByUuidContactUuidProductPurchasesPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyCustomerContactByUuidContactUuidRolesPageTypeExtension {

		public GetAccountAccountKeyCustomerContactByUuidContactUuidRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField(
			description = "Retrieves the account's contact's customer contact roles."
		)
		public ContactRolePage accountKeyCustomerContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
							_account.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLName("AccountPage")
	public class AccountPage {

		public AccountPage(Page accountPage) {
			actions = accountPage.getActions();

			items = accountPage.getItems();
			lastPage = accountPage.getLastPage();
			page = accountPage.getPage();
			pageSize = accountPage.getPageSize();
			totalCount = accountPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Account> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("AuditEntryPage")
	public class AuditEntryPage {

		public AuditEntryPage(Page auditEntryPage) {
			actions = auditEntryPage.getActions();

			items = auditEntryPage.getItems();
			lastPage = auditEntryPage.getLastPage();
			page = auditEntryPage.getPage();
			pageSize = auditEntryPage.getPageSize();
			totalCount = auditEntryPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<AuditEntry> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ContactPage")
	public class ContactPage {

		public ContactPage(Page contactPage) {
			actions = contactPage.getActions();

			items = contactPage.getItems();
			lastPage = contactPage.getLastPage();
			page = contactPage.getPage();
			pageSize = contactPage.getPageSize();
			totalCount = contactPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Contact> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ContactAccountViewPage")
	public class ContactAccountViewPage {

		public ContactAccountViewPage(Page contactAccountViewPage) {
			actions = contactAccountViewPage.getActions();

			items = contactAccountViewPage.getItems();
			lastPage = contactAccountViewPage.getLastPage();
			page = contactAccountViewPage.getPage();
			pageSize = contactAccountViewPage.getPageSize();
			totalCount = contactAccountViewPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<ContactAccountView> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ContactRolePage")
	public class ContactRolePage {

		public ContactRolePage(Page contactRolePage) {
			actions = contactRolePage.getActions();

			items = contactRolePage.getItems();
			lastPage = contactRolePage.getLastPage();
			page = contactRolePage.getPage();
			pageSize = contactRolePage.getPageSize();
			totalCount = contactRolePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<ContactRole> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("CountryPage")
	public class CountryPage {

		public CountryPage(Page countryPage) {
			actions = countryPage.getActions();

			items = countryPage.getItems();
			lastPage = countryPage.getLastPage();
			page = countryPage.getPage();
			pageSize = countryPage.getPageSize();
			totalCount = countryPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Country> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("CountryRegionPage")
	public class CountryRegionPage {

		public CountryRegionPage(Page countryRegionPage) {
			actions = countryRegionPage.getActions();

			items = countryRegionPage.getItems();
			lastPage = countryRegionPage.getLastPage();
			page = countryRegionPage.getPage();
			pageSize = countryRegionPage.getPageSize();
			totalCount = countryRegionPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<CountryRegion> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("EntitlementDefinitionPage")
	public class EntitlementDefinitionPage {

		public EntitlementDefinitionPage(Page entitlementDefinitionPage) {
			actions = entitlementDefinitionPage.getActions();

			items = entitlementDefinitionPage.getItems();
			lastPage = entitlementDefinitionPage.getLastPage();
			page = entitlementDefinitionPage.getPage();
			pageSize = entitlementDefinitionPage.getPageSize();
			totalCount = entitlementDefinitionPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<EntitlementDefinition> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ExternalLinkPage")
	public class ExternalLinkPage {

		public ExternalLinkPage(Page externalLinkPage) {
			actions = externalLinkPage.getActions();

			items = externalLinkPage.getItems();
			lastPage = externalLinkPage.getLastPage();
			page = externalLinkPage.getPage();
			pageSize = externalLinkPage.getPageSize();
			totalCount = externalLinkPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<ExternalLink> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("NotePage")
	public class NotePage {

		public NotePage(Page notePage) {
			actions = notePage.getActions();

			items = notePage.getItems();
			lastPage = notePage.getLastPage();
			page = notePage.getPage();
			pageSize = notePage.getPageSize();
			totalCount = notePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Note> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("PostalAddressPage")
	public class PostalAddressPage {

		public PostalAddressPage(Page postalAddressPage) {
			actions = postalAddressPage.getActions();

			items = postalAddressPage.getItems();
			lastPage = postalAddressPage.getLastPage();
			page = postalAddressPage.getPage();
			pageSize = postalAddressPage.getPageSize();
			totalCount = postalAddressPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<PostalAddress> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductPage")
	public class ProductPage {

		public ProductPage(Page productPage) {
			actions = productPage.getActions();

			items = productPage.getItems();
			lastPage = productPage.getLastPage();
			page = productPage.getPage();
			pageSize = productPage.getPageSize();
			totalCount = productPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Product> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductConsumptionPage")
	public class ProductConsumptionPage {

		public ProductConsumptionPage(Page productConsumptionPage) {
			actions = productConsumptionPage.getActions();

			items = productConsumptionPage.getItems();
			lastPage = productConsumptionPage.getLastPage();
			page = productConsumptionPage.getPage();
			pageSize = productConsumptionPage.getPageSize();
			totalCount = productConsumptionPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<ProductConsumption> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductPurchasePage")
	public class ProductPurchasePage {

		public ProductPurchasePage(Page productPurchasePage) {
			actions = productPurchasePage.getActions();

			items = productPurchasePage.getItems();
			lastPage = productPurchasePage.getLastPage();
			page = productPurchasePage.getPage();
			pageSize = productPurchasePage.getPageSize();
			totalCount = productPurchasePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<ProductPurchase> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductPurchaseViewPage")
	public class ProductPurchaseViewPage {

		public ProductPurchaseViewPage(Page productPurchaseViewPage) {
			actions = productPurchaseViewPage.getActions();

			items = productPurchaseViewPage.getItems();
			lastPage = productPurchaseViewPage.getLastPage();
			page = productPurchaseViewPage.getPage();
			pageSize = productPurchaseViewPage.getPageSize();
			totalCount = productPurchaseViewPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<ProductPurchaseView> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TeamPage")
	public class TeamPage {

		public TeamPage(Page teamPage) {
			actions = teamPage.getActions();

			items = teamPage.getItems();
			lastPage = teamPage.getLastPage();
			page = teamPage.getPage();
			pageSize = teamPage.getPageSize();
			totalCount = teamPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Team> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TeamRolePage")
	public class TeamRolePage {

		public TeamRolePage(Page teamRolePage) {
			actions = teamRolePage.getActions();

			items = teamRolePage.getItems();
			lastPage = teamRolePage.getLastPage();
			page = teamRolePage.getPage();
			pageSize = teamRolePage.getPageSize();
			totalCount = teamRolePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<TeamRole> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextAcceptLanguage(_acceptLanguage);
		accountResource.setContextCompany(_company);
		accountResource.setContextHttpServletRequest(_httpServletRequest);
		accountResource.setContextHttpServletResponse(_httpServletResponse);
		accountResource.setContextUriInfo(_uriInfo);
		accountResource.setContextUser(_user);
		accountResource.setGroupLocalService(_groupLocalService);
		accountResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(AuditEntryResource auditEntryResource)
		throws Exception {

		auditEntryResource.setContextAcceptLanguage(_acceptLanguage);
		auditEntryResource.setContextCompany(_company);
		auditEntryResource.setContextHttpServletRequest(_httpServletRequest);
		auditEntryResource.setContextHttpServletResponse(_httpServletResponse);
		auditEntryResource.setContextUriInfo(_uriInfo);
		auditEntryResource.setContextUser(_user);
		auditEntryResource.setGroupLocalService(_groupLocalService);
		auditEntryResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(ContactResource contactResource)
		throws Exception {

		contactResource.setContextAcceptLanguage(_acceptLanguage);
		contactResource.setContextCompany(_company);
		contactResource.setContextHttpServletRequest(_httpServletRequest);
		contactResource.setContextHttpServletResponse(_httpServletResponse);
		contactResource.setContextUriInfo(_uriInfo);
		contactResource.setContextUser(_user);
		contactResource.setGroupLocalService(_groupLocalService);
		contactResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			ContactAccountViewResource contactAccountViewResource)
		throws Exception {

		contactAccountViewResource.setContextAcceptLanguage(_acceptLanguage);
		contactAccountViewResource.setContextCompany(_company);
		contactAccountViewResource.setContextHttpServletRequest(
			_httpServletRequest);
		contactAccountViewResource.setContextHttpServletResponse(
			_httpServletResponse);
		contactAccountViewResource.setContextUriInfo(_uriInfo);
		contactAccountViewResource.setContextUser(_user);
		contactAccountViewResource.setGroupLocalService(_groupLocalService);
		contactAccountViewResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			ContactRoleResource contactRoleResource)
		throws Exception {

		contactRoleResource.setContextAcceptLanguage(_acceptLanguage);
		contactRoleResource.setContextCompany(_company);
		contactRoleResource.setContextHttpServletRequest(_httpServletRequest);
		contactRoleResource.setContextHttpServletResponse(_httpServletResponse);
		contactRoleResource.setContextUriInfo(_uriInfo);
		contactRoleResource.setContextUser(_user);
		contactRoleResource.setGroupLocalService(_groupLocalService);
		contactRoleResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(CountryResource countryResource)
		throws Exception {

		countryResource.setContextAcceptLanguage(_acceptLanguage);
		countryResource.setContextCompany(_company);
		countryResource.setContextHttpServletRequest(_httpServletRequest);
		countryResource.setContextHttpServletResponse(_httpServletResponse);
		countryResource.setContextUriInfo(_uriInfo);
		countryResource.setContextUser(_user);
		countryResource.setGroupLocalService(_groupLocalService);
		countryResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			CountryRegionResource countryRegionResource)
		throws Exception {

		countryRegionResource.setContextAcceptLanguage(_acceptLanguage);
		countryRegionResource.setContextCompany(_company);
		countryRegionResource.setContextHttpServletRequest(_httpServletRequest);
		countryRegionResource.setContextHttpServletResponse(
			_httpServletResponse);
		countryRegionResource.setContextUriInfo(_uriInfo);
		countryRegionResource.setContextUser(_user);
		countryRegionResource.setGroupLocalService(_groupLocalService);
		countryRegionResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			EntitlementDefinitionResource entitlementDefinitionResource)
		throws Exception {

		entitlementDefinitionResource.setContextAcceptLanguage(_acceptLanguage);
		entitlementDefinitionResource.setContextCompany(_company);
		entitlementDefinitionResource.setContextHttpServletRequest(
			_httpServletRequest);
		entitlementDefinitionResource.setContextHttpServletResponse(
			_httpServletResponse);
		entitlementDefinitionResource.setContextUriInfo(_uriInfo);
		entitlementDefinitionResource.setContextUser(_user);
		entitlementDefinitionResource.setGroupLocalService(_groupLocalService);
		entitlementDefinitionResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			ExternalLinkResource externalLinkResource)
		throws Exception {

		externalLinkResource.setContextAcceptLanguage(_acceptLanguage);
		externalLinkResource.setContextCompany(_company);
		externalLinkResource.setContextHttpServletRequest(_httpServletRequest);
		externalLinkResource.setContextHttpServletResponse(
			_httpServletResponse);
		externalLinkResource.setContextUriInfo(_uriInfo);
		externalLinkResource.setContextUser(_user);
		externalLinkResource.setGroupLocalService(_groupLocalService);
		externalLinkResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(NoteResource noteResource)
		throws Exception {

		noteResource.setContextAcceptLanguage(_acceptLanguage);
		noteResource.setContextCompany(_company);
		noteResource.setContextHttpServletRequest(_httpServletRequest);
		noteResource.setContextHttpServletResponse(_httpServletResponse);
		noteResource.setContextUriInfo(_uriInfo);
		noteResource.setContextUser(_user);
		noteResource.setGroupLocalService(_groupLocalService);
		noteResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			PostalAddressResource postalAddressResource)
		throws Exception {

		postalAddressResource.setContextAcceptLanguage(_acceptLanguage);
		postalAddressResource.setContextCompany(_company);
		postalAddressResource.setContextHttpServletRequest(_httpServletRequest);
		postalAddressResource.setContextHttpServletResponse(
			_httpServletResponse);
		postalAddressResource.setContextUriInfo(_uriInfo);
		postalAddressResource.setContextUser(_user);
		postalAddressResource.setGroupLocalService(_groupLocalService);
		postalAddressResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(ProductResource productResource)
		throws Exception {

		productResource.setContextAcceptLanguage(_acceptLanguage);
		productResource.setContextCompany(_company);
		productResource.setContextHttpServletRequest(_httpServletRequest);
		productResource.setContextHttpServletResponse(_httpServletResponse);
		productResource.setContextUriInfo(_uriInfo);
		productResource.setContextUser(_user);
		productResource.setGroupLocalService(_groupLocalService);
		productResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			ProductConsumptionResource productConsumptionResource)
		throws Exception {

		productConsumptionResource.setContextAcceptLanguage(_acceptLanguage);
		productConsumptionResource.setContextCompany(_company);
		productConsumptionResource.setContextHttpServletRequest(
			_httpServletRequest);
		productConsumptionResource.setContextHttpServletResponse(
			_httpServletResponse);
		productConsumptionResource.setContextUriInfo(_uriInfo);
		productConsumptionResource.setContextUser(_user);
		productConsumptionResource.setGroupLocalService(_groupLocalService);
		productConsumptionResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			ProductPurchaseResource productPurchaseResource)
		throws Exception {

		productPurchaseResource.setContextAcceptLanguage(_acceptLanguage);
		productPurchaseResource.setContextCompany(_company);
		productPurchaseResource.setContextHttpServletRequest(
			_httpServletRequest);
		productPurchaseResource.setContextHttpServletResponse(
			_httpServletResponse);
		productPurchaseResource.setContextUriInfo(_uriInfo);
		productPurchaseResource.setContextUser(_user);
		productPurchaseResource.setGroupLocalService(_groupLocalService);
		productPurchaseResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			ProductPurchaseViewResource productPurchaseViewResource)
		throws Exception {

		productPurchaseViewResource.setContextAcceptLanguage(_acceptLanguage);
		productPurchaseViewResource.setContextCompany(_company);
		productPurchaseViewResource.setContextHttpServletRequest(
			_httpServletRequest);
		productPurchaseViewResource.setContextHttpServletResponse(
			_httpServletResponse);
		productPurchaseViewResource.setContextUriInfo(_uriInfo);
		productPurchaseViewResource.setContextUser(_user);
		productPurchaseViewResource.setGroupLocalService(_groupLocalService);
		productPurchaseViewResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(TeamResource teamResource)
		throws Exception {

		teamResource.setContextAcceptLanguage(_acceptLanguage);
		teamResource.setContextCompany(_company);
		teamResource.setContextHttpServletRequest(_httpServletRequest);
		teamResource.setContextHttpServletResponse(_httpServletResponse);
		teamResource.setContextUriInfo(_uriInfo);
		teamResource.setContextUser(_user);
		teamResource.setGroupLocalService(_groupLocalService);
		teamResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(TeamRoleResource teamRoleResource)
		throws Exception {

		teamRoleResource.setContextAcceptLanguage(_acceptLanguage);
		teamRoleResource.setContextCompany(_company);
		teamRoleResource.setContextHttpServletRequest(_httpServletRequest);
		teamRoleResource.setContextHttpServletResponse(_httpServletResponse);
		teamRoleResource.setContextUriInfo(_uriInfo);
		teamRoleResource.setContextUser(_user);
		teamRoleResource.setGroupLocalService(_groupLocalService);
		teamRoleResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<AuditEntryResource>
		_auditEntryResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactResource>
		_contactResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactAccountViewResource>
		_contactAccountViewResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;
	private static ComponentServiceObjects<CountryResource>
		_countryResourceComponentServiceObjects;
	private static ComponentServiceObjects<CountryRegionResource>
		_countryRegionResourceComponentServiceObjects;
	private static ComponentServiceObjects<EntitlementDefinitionResource>
		_entitlementDefinitionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ExternalLinkResource>
		_externalLinkResourceComponentServiceObjects;
	private static ComponentServiceObjects<NoteResource>
		_noteResourceComponentServiceObjects;
	private static ComponentServiceObjects<PostalAddressResource>
		_postalAddressResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductConsumptionResource>
		_productConsumptionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductPurchaseResource>
		_productPurchaseResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductPurchaseViewResource>
		_productPurchaseViewResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamResource>
		_teamResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamRoleResource>
		_teamRoleResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}