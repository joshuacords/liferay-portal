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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ContactUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.PhloemNestedFieldsContextThreadLocal;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ContactEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/contact.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {ContactResource.class, NestedFieldSupport.class}
)
public class ContactResourceImpl
	extends BaseContactResourceImpl
	implements EntityModelResource, NestedFieldSupport {

	@Override
	public void deleteContactByEmailAddresEmailAddress(
			String agentName, String agentUID, String emailAddress)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByEmailAddress(emailAddress);

		_contactService.deleteContact(contact.getContactId());
	}

	@Override
	public void deleteContactByUuidContactUuid(
			String agentName, String agentUID, String contactUuid)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByUuid(contactUuid);

		_contactService.deleteContact(contact.getContactId());
	}

	@Override
	public void deleteContactByUuidContactUuidContactPermission(
			String agentName, String agentUID, String contactUuid,
			ContactPermission contactPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.fetchContactByUuid(contactUuid);

		_updateContactPermission(contact, "delete", contactPermission);
	}

	@Override
	public Page<Contact> getAccountAccountKeyContactsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_contactService.getAccountContacts(
					accountKey, null, pagination.getStartPosition(),
					pagination.getEndPosition()),
				contact -> ContactUtil.toContact(contact)),
			pagination,
			_contactService.getAccountContactsCount(accountKey, null));
	}

	@Override
	public Page<Contact> getAccountAccountKeyCustomerContactsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_contactService.getAccountContacts(
					accountKey, ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
					pagination.getStartPosition(), pagination.getEndPosition()),
				contact -> ContactUtil.toContact(contact)),
			pagination,
			_contactService.getAccountContactsCount(
				accountKey, ContactRole.Type.ACCOUNT_CUSTOMER.toString()));
	}

	@Override
	public Page<Contact> getAccountAccountKeyWorkerContactsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_contactService.getAccountContacts(
					accountKey, ContactRole.Type.ACCOUNT_WORKER.toString(),
					pagination.getStartPosition(), pagination.getEndPosition()),
				contact -> ContactUtil.toContact(contact)),
			pagination,
			_contactService.getAccountContactsCount(
				accountKey, ContactRole.Type.ACCOUNT_WORKER.toString()));
	}

	@NestedField(parentClass = Account.class, value = "customerContacts")
	public List<Contact> getAccountCustomerContacts(
			@NestedFieldId("key") String accountKey)
		throws Exception {

		PhloemNestedFieldsContextThreadLocal.addContextName("customerContacts");
		PhloemNestedFieldsContextThreadLocal.setContextValue(
			"accountKey", accountKey);

		return transform(
			_contactService.getAccountContacts(
				accountKey, ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			contact -> ContactUtil.toContact(contact));
	}

	@NestedField(parentClass = Account.class, value = "workerContacts")
	public List<Contact> getAccountWorkerContacts(
			@NestedFieldId("key") String accountKey)
		throws Exception {

		PhloemNestedFieldsContextThreadLocal.addContextName("workerContacts");
		PhloemNestedFieldsContextThreadLocal.setContextValue(
			"accountKey", accountKey);

		return transform(
			_contactService.getAccountContacts(
				accountKey, ContactRole.Type.ACCOUNT_WORKER.toString(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			contact -> ContactUtil.toContact(contact));
	}

	@Override
	public Contact getContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception {

		return ContactUtil.toContact(
			_contactService.getContactByEmailAddress(emailAddress));
	}

	@Override
	public Contact getContactByUuidContactUuid(String contactUuid)
		throws Exception {

		return ContactUtil.toContact(
			_contactService.getContactByUuid(contactUuid));
	}

	@Override
	public Page<Contact> getContactsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.Contact.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ContactUtil.toContact(
				_contactLocalService.getContact(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@NestedField(parentClass = Team.class, value = "contacts")
	public List<Contact> getTeamNestedFieldContacts(
			@NestedFieldId("key") String teamKey)
		throws Exception {

		return transform(
			_contactService.getTeamContacts(
				teamKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			contact -> ContactUtil.toContact(contact));
	}

	@Override
	public Page<Contact> getTeamTeamKeyContactsPage(
			String teamKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_contactService.getTeamContacts(
					teamKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				contact -> ContactUtil.toContact(contact)),
			pagination, _contactService.getTeamContactsCount(teamKey));
	}

	@Override
	public Contact postContact(
			String agentName, String agentUID, Contact contact)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		return ContactUtil.toContact(
			_contactService.addContact(
				contact.getUuid(), contact.getFirstName(),
				contact.getMiddleName(), contact.getLastName(),
				contact.getEmailAddress(), contact.getLanguageId(),
				GetterUtil.getBoolean(contact.getEmailAddressVerified())));
	}

	@Override
	public Contact putContactByEmailAddresEmailAddress(
			String agentName, String agentUID, String emailAddress,
			Contact contact)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_contactLocalService.getContactByEmailAddress(emailAddress);

		String uuid = GetterUtil.getString(
			contact.getUuid(), curContact.getUuid());
		String middleName = GetterUtil.getString(
			contact.getMiddleName(), curContact.getMiddleName());
		String languageId = GetterUtil.getString(
			contact.getLanguageId(), curContact.getLanguageId());
		boolean emailAddressVerified = GetterUtil.getBoolean(
			contact.getEmailAddressVerified(),
			curContact.getEmailAddressVerified());

		return ContactUtil.toContact(
			_contactService.updateContact(
				curContact.getContactId(), uuid, contact.getFirstName(),
				middleName, contact.getLastName(), contact.getEmailAddress(),
				languageId, emailAddressVerified));
	}

	@Override
	public Contact putContactByUuidContactUuid(
			String agentName, String agentUID, String contactUuid,
			Contact contact)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_contactLocalService.getContactByUuid(contactUuid);

		String middleName = GetterUtil.getString(
			contact.getMiddleName(), curContact.getMiddleName());
		String languageId = GetterUtil.getString(
			contact.getLanguageId(), curContact.getLanguageId());
		boolean emailAddressVerified = GetterUtil.getBoolean(
			contact.getEmailAddressVerified(),
			curContact.getEmailAddressVerified());

		return ContactUtil.toContact(
			_contactService.updateContact(
				curContact.getContactId(), curContact.getUuid(),
				contact.getFirstName(), middleName, contact.getLastName(),
				contact.getEmailAddress(), languageId, emailAddressVerified));
	}

	@Override
	public void putContactByUuidContactUuidContactPermission(
			String agentName, String agentUID, String contactUuid,
			ContactPermission contactPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.fetchContactByUuid(contactUuid);

		_updateContactPermission(contact, "add", contactPermission);
	}

	private void _updateContactPermission(
			com.liferay.osb.koroneiki.taproot.model.Contact contact,
			String operation, ContactPermission contactPermission)
		throws Exception {

		_contactPermission.check(
			PermissionThreadLocal.getPermissionChecker(), contact,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(contactPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(contactPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(contactPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(contactPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.taproot.model.Contact.class.getName(),
			contact.getContactId(), contactPermission.getRoleNames(),
			actionIds);
	}

	private static final EntityModel _entityModel = new ContactEntityModel();

	@Reference(target = "(provider=web)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.ContactPermission
		_contactPermission;

	@Reference
	private ContactService _contactService;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private TeamLocalService _teamLocalService;

}