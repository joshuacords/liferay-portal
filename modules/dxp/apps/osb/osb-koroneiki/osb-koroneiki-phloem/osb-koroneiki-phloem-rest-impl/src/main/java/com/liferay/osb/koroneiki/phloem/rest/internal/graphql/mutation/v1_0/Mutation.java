/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.graphql.mutation.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchasePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AuditEntryResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.NoteResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.PostalAddressResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

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
public class Mutation {

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

	public static void setContactRoleResourceComponentServiceObjects(
		ComponentServiceObjects<ContactRoleResource>
			contactRoleResourceComponentServiceObjects) {

		_contactRoleResourceComponentServiceObjects =
			contactRoleResourceComponentServiceObjects;
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

	@GraphQLField
	public Account createAccount(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccount(
				agentName, agentUID, account));
	}

	@GraphQLField
	public boolean deleteAccount(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccount(
				agentName, agentUID, accountKey));

		return true;
	}

	@GraphQLField
	public Account updateAccount(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccount(
				agentName, agentUID, accountKey, account));
	}

	@GraphQLField
	public boolean deleteAccountAccountPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("accountPermission") AccountPermission
				accountPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountAccountPermission(
				agentName, agentUID, accountKey, accountPermission));

		return true;
	}

	@GraphQLField
	public boolean updateAccountAccountPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("accountPermission") AccountPermission
				accountPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountAccountPermission(
				agentName, agentUID, accountKey, accountPermission));

		return true;
	}

	@GraphQLField(
		description = "Unassigns roles from the team for the account."
	)
	public boolean deleteAccountAssignedTeamTeamKeyRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamRoleKeys") String[] teamRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountAssignedTeamTeamKeyRole(
					agentName, agentUID, accountKey, teamKey, teamRoleKeys));

		return true;
	}

	@GraphQLField(description = "Assigns roles to the team for the account.")
	public boolean updateAccountAssignedTeamTeamKeyRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamRoleKeys") String[] teamRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.putAccountAssignedTeamTeamKeyRole(
					agentName, agentUID, accountKey, teamKey, teamRoleKeys));

		return true;
	}

	@GraphQLField(
		description = "Unassigns roles from the contact for the account."
	)
	public boolean deleteAccountContactByEmailAddresContactEmailAddressRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddress") String contactEmailAddress,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.
					deleteAccountContactByEmailAddresContactEmailAddressRole(
						agentName, agentUID, accountKey, contactEmailAddress,
						contactRoleKeys));

		return true;
	}

	@GraphQLField(description = "Assigns roles to the contact for the account.")
	public boolean updateAccountContactByEmailAddresContactEmailAddressRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddress") String contactEmailAddress,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.
					putAccountContactByEmailAddresContactEmailAddressRole(
						agentName, agentUID, accountKey, contactEmailAddress,
						contactRoleKeys));

		return true;
	}

	@GraphQLField(
		description = "Unassigns roles from the contact for the account."
	)
	public boolean deleteAccountContactByUuidContactUuidRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountContactByUuidContactUuidRole(
					agentName, agentUID, accountKey, contactUuid,
					contactRoleKeys));

		return true;
	}

	@GraphQLField(description = "Assigns roles to the contact for the account.")
	public boolean updateAccountContactByUuidContactUuidRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.putAccountContactByUuidContactUuidRole(
					agentName, agentUID, accountKey, contactUuid,
					contactRoleKeys));

		return true;
	}

	@GraphQLField(description = "Unassigns customer contacts from the account.")
	public boolean deleteAccountCustomerContactByEmailAddres(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddresses") String[]
				contactEmailAddresses)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountCustomerContactByEmailAddres(
					agentName, agentUID, accountKey, contactEmailAddresses));

		return true;
	}

	@GraphQLField(description = "Unassigns contacts from the account.")
	public boolean deleteAccountCustomerContactByUuid(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountCustomerContactByUuid(
					agentName, agentUID, accountKey, contactUuids));

		return true;
	}

	@GraphQLField(description = "Unassigns worker contacts from the account.")
	public boolean deleteAccountWorkerContactByEmailAddres(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddresses") String[]
				contactEmailAddresses)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountWorkerContactByEmailAddres(
					agentName, agentUID, accountKey, contactEmailAddresses));

		return true;
	}

	@GraphQLField(description = "Unassigns contacts from the account.")
	public boolean deleteAccountWorkerContactByUuid(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountWorkerContactByUuid(
				agentName, agentUID, accountKey, contactUuids));

		return true;
	}

	@GraphQLField
	public java.util.Collection<AuditEntry>
			createAccountAccountKeyAuditEntriesPage(
				@GraphQLName("agentName") String agentName,
				@GraphQLName("agentUID") String agentUID,
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("auditEntries") AuditEntry[] auditEntries)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> {
				Page paginationPage =
					auditEntryResource.postAccountAccountKeyAuditEntriesPage(
						agentName, agentUID, accountKey, auditEntries);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	public java.util.Collection<AuditEntry>
			createContactByUuidContactUuidAuditEntriesPage(
				@GraphQLName("agentName") String agentName,
				@GraphQLName("agentUID") String agentUID,
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("auditEntries") AuditEntry[] auditEntries)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> {
				Page paginationPage =
					auditEntryResource.
						postContactByUuidContactUuidAuditEntriesPage(
							agentName, agentUID, contactUuid, auditEntries);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	public Contact createContact(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.postContact(
				agentName, agentUID, contact));
	}

	@GraphQLField
	public boolean deleteContactByEmailAddresEmailAddress(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("emailAddress") String emailAddress)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.deleteContactByEmailAddresEmailAddress(
					agentName, agentUID, emailAddress));

		return true;
	}

	@GraphQLField
	public Contact updateContactByEmailAddresEmailAddress(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("emailAddress") String emailAddress,
			@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.putContactByEmailAddresEmailAddress(
					agentName, agentUID, emailAddress, contact));
	}

	@GraphQLField
	public boolean deleteContactByUuidContactUuid(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactUuid") String contactUuid)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.deleteContactByUuidContactUuid(
				agentName, agentUID, contactUuid));

		return true;
	}

	@GraphQLField
	public Contact updateContactByUuidContactUuid(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.putContactByUuidContactUuid(
				agentName, agentUID, contactUuid, contact));
	}

	@GraphQLField
	public boolean deleteContactByUuidContactUuidContactPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.deleteContactByUuidContactUuidContactPermission(
					agentName, agentUID, contactUuid, contactPermission));

		return true;
	}

	@GraphQLField
	public boolean updateContactByUuidContactUuidContactPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.putContactByUuidContactUuidContactPermission(
					agentName, agentUID, contactUuid, contactPermission));

		return true;
	}

	@GraphQLField
	public ContactRole createContactRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactRole") ContactRole contactRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.postContactRole(
				agentName, agentUID, contactRole));
	}

	@GraphQLField
	public boolean deleteContactRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactRoleKey") String contactRoleKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.deleteContactRole(
				agentName, agentUID, contactRoleKey));

		return true;
	}

	@GraphQLField
	public ContactRole updateContactRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("contactRole") ContactRole contactRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.putContactRole(
				agentName, agentUID, contactRoleKey, contactRole));
	}

	@GraphQLField
	public boolean deleteContactRoleContactRolePermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("contactRolePermission") ContactRolePermission
				contactRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource ->
				contactRoleResource.deleteContactRoleContactRolePermission(
					agentName, agentUID, contactRoleKey,
					contactRolePermission));

		return true;
	}

	@GraphQLField
	public boolean updateContactRoleContactRolePermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("contactRolePermission") ContactRolePermission
				contactRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource ->
				contactRoleResource.putContactRoleContactRolePermission(
					agentName, agentUID, contactRoleKey,
					contactRolePermission));

		return true;
	}

	@GraphQLField
	public EntitlementDefinition createAccountEntitlementDefinition(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("entitlementDefinition") EntitlementDefinition
				entitlementDefinition)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.postAccountEntitlementDefinition(
					agentName, agentUID, entitlementDefinition));
	}

	@GraphQLField
	public EntitlementDefinition createContactEntitlementDefinition(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("entitlementDefinition") EntitlementDefinition
				entitlementDefinition)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.postContactEntitlementDefinition(
					agentName, agentUID, entitlementDefinition));
	}

	@GraphQLField
	public boolean deleteEntitlementDefinition(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("entitlementDefinitionKey") String
				entitlementDefinitionKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.deleteEntitlementDefinition(
					agentName, agentUID, entitlementDefinitionKey));

		return true;
	}

	@GraphQLField(
		description = "Immediately runs a synchronization of the entitlement definition."
	)
	public boolean createEntitlementDefinitionSynchronize(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("entitlementDefinitionKey") String
				entitlementDefinitionKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.
					postEntitlementDefinitionSynchronize(
						agentName, agentUID, entitlementDefinitionKey));

		return true;
	}

	@GraphQLField(description = "Adds an external link to the account.")
	public ExternalLink createAccountAccountKeyExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postAccountAccountKeyExternalLink(
					agentName, agentUID, accountKey, externalLink));
	}

	@GraphQLField(description = "Adds an external link to the contact role.")
	public ExternalLink createContactRoleContactRoleKeyExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postContactRoleContactRoleKeyExternalLink(
					agentName, agentUID, contactRoleKey, externalLink));
	}

	@GraphQLField(description = "Adds an external link to the contact.")
	public ExternalLink createContactByUuidContactUuidExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postContactByUuidContactUuidExternalLink(
					agentName, agentUID, contactUuid, externalLink));
	}

	@GraphQLField
	public boolean deleteExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("externalLinkKey") String externalLinkKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> externalLinkResource.deleteExternalLink(
				agentName, agentUID, externalLinkKey));

		return true;
	}

	@GraphQLField(
		description = "Updates the external link. Only the entityId field can be updated."
	)
	public ExternalLink updateExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("externalLinkKey") String externalLinkKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> externalLinkResource.putExternalLink(
				agentName, agentUID, externalLinkKey, externalLink));
	}

	@GraphQLField(
		description = "Adds an external link to the product consumption."
	)
	public ExternalLink
			createProductConsumptionProductConsumptionKeyExternalLink(
				@GraphQLName("agentName") String agentName,
				@GraphQLName("agentUID") String agentUID,
				@GraphQLName("productConsumptionKey") String
					productConsumptionKey,
				@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.
					postProductConsumptionProductConsumptionKeyExternalLink(
						agentName, agentUID, productConsumptionKey,
						externalLink));
	}

	@GraphQLField(
		description = "Adds an external link to the product purchase."
	)
	public ExternalLink createProductPurchaseProductPurchaseKeyExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.
					postProductPurchaseProductPurchaseKeyExternalLink(
						agentName, agentUID, productPurchaseKey, externalLink));
	}

	@GraphQLField(description = "Adds an external link to the product.")
	public ExternalLink createProductProductKeyExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productKey") String productKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postProductProductKeyExternalLink(
					agentName, agentUID, productKey, externalLink));
	}

	@GraphQLField(description = "Adds an external link to the team.")
	public ExternalLink createTeamTeamKeyExternalLink(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postTeamTeamKeyExternalLink(
					agentName, agentUID, teamKey, externalLink));
	}

	@GraphQLField
	public Note createAccountAccountKeyNote(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("note") Note note)
		throws Exception {

		return _applyComponentServiceObjects(
			_noteResourceComponentServiceObjects,
			this::_populateResourceContext,
			noteResource -> noteResource.postAccountAccountKeyNote(
				agentName, agentUID, accountKey, note));
	}

	@GraphQLField
	public boolean deleteNote(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("noteKey") String noteKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_noteResourceComponentServiceObjects,
			this::_populateResourceContext,
			noteResource -> noteResource.deleteNote(
				agentName, agentUID, noteKey));

		return true;
	}

	@GraphQLField
	public Note updateNote(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("noteKey") String noteKey,
			@GraphQLName("note") Note note)
		throws Exception {

		return _applyComponentServiceObjects(
			_noteResourceComponentServiceObjects,
			this::_populateResourceContext,
			noteResource -> noteResource.putNote(
				agentName, agentUID, noteKey, note));
	}

	@GraphQLField
	public PostalAddress createAccountAccountKeyPostalAddress(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("postalAddress") PostalAddress postalAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource ->
				postalAddressResource.postAccountAccountKeyPostalAddress(
					agentName, agentUID, accountKey, postalAddress));
	}

	@GraphQLField
	public boolean deletePostalAddress(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("postalAddressId") Long postalAddressId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> postalAddressResource.deletePostalAddress(
				agentName, agentUID, postalAddressId));

		return true;
	}

	@GraphQLField
	public PostalAddress updatePostalAddress(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("postalAddressId") Long postalAddressId,
			@GraphQLName("postalAddress") PostalAddress postalAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> postalAddressResource.putPostalAddress(
				agentName, agentUID, postalAddressId, postalAddress));
	}

	@GraphQLField
	public Product createProduct(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.postProduct(
				agentName, agentUID, product));
	}

	@GraphQLField
	public boolean deleteProduct(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productKey") String productKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.deleteProduct(
				agentName, agentUID, productKey));

		return true;
	}

	@GraphQLField
	public Product updateProduct(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productKey") String productKey,
			@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.putProduct(
				agentName, agentUID, productKey, product));
	}

	@GraphQLField
	public boolean deleteProductProductPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productKey") String productKey,
			@GraphQLName("productPermission") ProductPermission
				productPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.deleteProductProductPermission(
				agentName, agentUID, productKey, productPermission));

		return true;
	}

	@GraphQLField
	public boolean updateProductProductPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productKey") String productKey,
			@GraphQLName("productPermission") ProductPermission
				productPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.putProductProductPermission(
				agentName, agentUID, productKey, productPermission));

		return true;
	}

	@GraphQLField
	public ProductConsumption createAccountAccountKeyProductConsumption(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("productConsumption") ProductConsumption
				productConsumption)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.
					postAccountAccountKeyProductConsumption(
						agentName, agentUID, accountKey, productConsumption));
	}

	@GraphQLField
	public boolean deleteProductConsumption(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productConsumptionKey") String productConsumptionKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.deleteProductConsumption(
					agentName, agentUID, productConsumptionKey));

		return true;
	}

	@GraphQLField
	public boolean deleteProductConsumptionProductConsumptionPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productConsumptionKey") String productConsumptionKey,
			@GraphQLName("productConsumptionPermission")
				ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.
					deleteProductConsumptionProductConsumptionPermission(
						agentName, agentUID, productConsumptionKey,
						productConsumptionPermission));

		return true;
	}

	@GraphQLField
	public boolean updateProductConsumptionProductConsumptionPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productConsumptionKey") String productConsumptionKey,
			@GraphQLName("productConsumptionPermission")
				ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.
					putProductConsumptionProductConsumptionPermission(
						agentName, agentUID, productConsumptionKey,
						productConsumptionPermission));

		return true;
	}

	@GraphQLField
	public ProductPurchase createAccountAccountKeyProductPurchase(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("productPurchase") ProductPurchase productPurchase)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.postAccountAccountKeyProductPurchase(
					agentName, agentUID, accountKey, productPurchase));
	}

	@GraphQLField
	public boolean deleteProductPurchase(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productPurchaseKey") String productPurchaseKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.deleteProductPurchase(
					agentName, agentUID, productPurchaseKey));

		return true;
	}

	@GraphQLField
	public ProductPurchase updateProductPurchase(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("productPurchase") ProductPurchase productPurchase)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.putProductPurchase(
					agentName, agentUID, productPurchaseKey, productPurchase));
	}

	@GraphQLField
	public boolean deleteProductPurchaseProductPurchasePermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("productPurchasePermission") ProductPurchasePermission
				productPurchasePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.
					deleteProductPurchaseProductPurchasePermission(
						agentName, agentUID, productPurchaseKey,
						productPurchasePermission));

		return true;
	}

	@GraphQLField
	public boolean updateProductPurchaseProductPurchasePermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("productPurchasePermission") ProductPurchasePermission
				productPurchasePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.
					putProductPurchaseProductPurchasePermission(
						agentName, agentUID, productPurchaseKey,
						productPurchasePermission));

		return true;
	}

	@GraphQLField
	public Team createAccountAccountKeyTeam(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("team") Team team)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.postAccountAccountKeyTeam(
				agentName, agentUID, accountKey, team));
	}

	@GraphQLField
	public boolean deleteTeam(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeam(
				agentName, agentUID, teamKey));

		return true;
	}

	@GraphQLField
	public Team updateTeam(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("team") Team team)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeam(
				agentName, agentUID, teamKey, team));
	}

	@GraphQLField(description = "Unassigns contacts from the team.")
	public boolean deleteTeamContactByEmailAddres(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("emailAddresses") String[] emailAddresses)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamContactByEmailAddres(
				agentName, agentUID, teamKey, emailAddresses));

		return true;
	}

	@GraphQLField(description = "Assigns contacts to the team.")
	public boolean updateTeamContactByEmailAddres(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("emailAddresses") String[] emailAddresses)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamContactByEmailAddres(
				agentName, agentUID, teamKey, emailAddresses));

		return true;
	}

	@GraphQLField(
		description = "Unassigns roles from the contact for the team."
	)
	public boolean deleteTeamContactByEmailAddresEmailAddressRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("emailAddress") String emailAddress,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource ->
				teamResource.deleteTeamContactByEmailAddresEmailAddressRole(
					agentName, agentUID, teamKey, emailAddress,
					contactRoleKeys));

		return true;
	}

	@GraphQLField(description = "Assigns roles to the contact for the team.")
	public boolean updateTeamContactByEmailAddresEmailAddressRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("emailAddress") String emailAddress,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource ->
				teamResource.putTeamContactByEmailAddresEmailAddressRole(
					agentName, agentUID, teamKey, emailAddress,
					contactRoleKeys));

		return true;
	}

	@GraphQLField(description = "Unassigns contacts from the team.")
	public boolean deleteTeamContactByUuid(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamContactByUuid(
				agentName, agentUID, teamKey, contactUuids));

		return true;
	}

	@GraphQLField(description = "Assigns contacts to the team.")
	public boolean updateTeamContactByUuid(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamContactByUuid(
				agentName, agentUID, teamKey, contactUuids));

		return true;
	}

	@GraphQLField(
		description = "Unassigns roles from the contact for the team."
	)
	public boolean deleteTeamContactByUuidContactUuidRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamContactByUuidContactUuidRole(
				agentName, agentUID, teamKey, contactUuid, contactRoleKeys));

		return true;
	}

	@GraphQLField(description = "Assigns roles to the contact for the team.")
	public boolean updateTeamContactByUuidContactUuidRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamContactByUuidContactUuidRole(
				agentName, agentUID, teamKey, contactUuid, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean deleteTeamTeamPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamPermission") TeamPermission teamPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamTeamPermission(
				agentName, agentUID, teamKey, teamPermission));

		return true;
	}

	@GraphQLField
	public boolean updateTeamTeamPermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamPermission") TeamPermission teamPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamTeamPermission(
				agentName, agentUID, teamKey, teamPermission));

		return true;
	}

	@GraphQLField
	public TeamRole createTeamRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamRole") TeamRole teamRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.postTeamRole(
				agentName, agentUID, teamRole));
	}

	@GraphQLField
	public boolean deleteTeamRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamRoleKey") String teamRoleKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.deleteTeamRole(
				agentName, agentUID, teamRoleKey));

		return true;
	}

	@GraphQLField
	public TeamRole updateTeamRole(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("teamRole") TeamRole teamRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.putTeamRole(
				agentName, agentUID, teamRoleKey, teamRole));
	}

	@GraphQLField
	public boolean deleteTeamRoleTeamRolePermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("teamRolePermission") TeamRolePermission
				teamRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource ->
				teamRoleResource.deleteTeamRoleTeamRolePermission(
					agentName, agentUID, teamRoleKey, teamRolePermission));

		return true;
	}

	@GraphQLField
	public boolean updateTeamRoleTeamRolePermission(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("teamRolePermission") TeamRolePermission
				teamRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.putTeamRoleTeamRolePermission(
				agentName, agentUID, teamRoleKey, teamRolePermission));

		return true;
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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
	private static ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;
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
	private static ComponentServiceObjects<TeamResource>
		_teamResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamRoleResource>
		_teamRoleResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}