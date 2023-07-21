/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ContactRoleUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.PhloemNestedFieldsContextThreadLocal;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ContactRoleEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
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
	properties = "OSGI-INF/liferay/rest/v1_0/contact-role.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {ContactRoleResource.class, NestedFieldSupport.class}
)
public class ContactRoleResourceImpl
	extends BaseContactRoleResourceImpl
	implements EntityModelResource, NestedFieldSupport {

	@Override
	public void deleteContactRole(
			String agentName, String agentUID, String contactRoleKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_contactRoleService.deleteContactRole(contactRoleKey);
	}

	@Override
	public void deleteContactRoleContactRolePermission(
			String agentName, String agentUID, String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateContactRolePermission(
			contactRoleKey, "delete", contactRolePermission);
	}

	@NestedField("contactRoles")
	@Override
	public Page<ContactRole>
			getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey,
				@NestedFieldId("emailAddress") String contactEmailAddress,
				Pagination pagination)
		throws Exception {

		if (Validator.isNull(accountKey)) {
			accountKey =
				(String)PhloemNestedFieldsContextThreadLocal.getContextValue(
					"accountKey");
		}

		String[] contactRoleTypes = null;

		String parentContextName =
			PhloemNestedFieldsContextThreadLocal.getLastContextName();

		if (Validator.isNotNull(parentContextName) &&
			parentContextName.equals("customerContacts")) {

			contactRoleTypes = new String[] {
				ContactRole.Type.ACCOUNT_CUSTOMER.toString()
			};
		}
		else if (Validator.isNotNull(parentContextName) &&
				 parentContextName.equals("workerContacts")) {

			contactRoleTypes = new String[] {
				ContactRole.Type.ACCOUNT_WORKER.toString()
			};
		}
		else {
			contactRoleTypes = new String[] {
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRole.Type.ACCOUNT_WORKER.toString()
			};
		}

		return _getAccountContactRolesPage(
			_contactLocalService.getContactByEmailAddress(contactEmailAddress),
			accountKey, contactRoleTypes, pagination);
	}

	@Override
	public Page<ContactRole>
			getAccountAccountKeyContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception {

		return _getAccountContactRolesPage(
			_contactLocalService.getContactByUuid(contactUuid), accountKey,
			new String[] {
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRole.Type.ACCOUNT_WORKER.toString()
			},
			pagination);
	}

	@Override
	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception {

		return _getAccountContactRolesPage(
			_contactLocalService.getContactByEmailAddress(contactEmailAddress),
			accountKey,
			new String[] {ContactRole.Type.ACCOUNT_CUSTOMER.toString()},
			pagination);
	}

	@Override
	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception {

		return _getAccountContactRolesPage(
			_contactLocalService.getContactByUuid(contactUuid), accountKey,
			new String[] {ContactRole.Type.ACCOUNT_CUSTOMER.toString()},
			pagination);
	}

	@Override
	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey,
				@NestedFieldId("emailAddress") String contactEmailAddress,
				Pagination pagination)
		throws Exception {

		return _getAccountContactRolesPage(
			_contactLocalService.getContactByEmailAddress(contactEmailAddress),
			accountKey,
			new String[] {ContactRole.Type.ACCOUNT_WORKER.toString()},
			pagination);
	}

	@Override
	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception {

		return _getAccountContactRolesPage(
			_contactLocalService.getContactByUuid(contactUuid), accountKey,
			new String[] {ContactRole.Type.ACCOUNT_WORKER.toString()},
			pagination);
	}

	@Override
	public ContactRole getContactRole(String contactRoleKey) throws Exception {
		return ContactRoleUtil.toContactRole(
			_contactRoleService.getContactRole(contactRoleKey));
	}

	@Override
	public ContactRole getContactRoleByTypeContactRoleTypeByNameContactRoleName(
			String contactRoleType, String contactRoleName)
		throws Exception {

		return ContactRoleUtil.toContactRole(
			_contactRoleService.getContactRole(
				contactRoleName, contactRoleType));
	}

	@Override
	public ContactRole getContactRolesContactRoleTypeContactRoleName(
			String contactRoleType, String contactRoleName)
		throws Exception {

		return getContactRoleByTypeContactRoleTypeByNameContactRoleName(
			contactRoleType, contactRoleName);
	}

	@Override
	public Page<ContactRole> getContactRolesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.ContactRole.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ContactRoleUtil.toContactRole(
				_contactRoleLocalService.getContactRole(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Page<ContactRole>
			getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
				String teamKey, String emailAddress, Pagination pagination)
		throws Exception {

		return _getTeamContactRolesPage(
			_contactLocalService.getContactByEmailAddress(emailAddress),
			teamKey, pagination);
	}

	@Override
	public Page<ContactRole> getTeamTeamKeyContactByUuidContactUuidRolesPage(
			String teamKey, String contactUuid, Pagination pagination)
		throws Exception {

		return _getTeamContactRolesPage(
			_contactLocalService.getContactByUuid(contactUuid), teamKey,
			pagination);
	}

	@Override
	public ContactRole postContactRole(
			String agentName, String agentUID, ContactRole contactRole)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		ContactRole.Type contactRoleType = contactRole.getType();

		return ContactRoleUtil.toContactRole(
			_contactRoleService.addContactRole(
				contactRole.getName(), contactRole.getDescription(),
				contactRoleType.toString()));
	}

	@Override
	public ContactRole putContactRole(
			String agentName, String agentUID, String contactRoleKey,
			ContactRole contactRole)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.ContactRole curContactRole =
			_contactRoleLocalService.getContactRole(contactRoleKey);

		String description = GetterUtil.getString(
			contactRole.getDescription(), curContactRole.getDescription());

		return ContactRoleUtil.toContactRole(
			_contactRoleService.updateContactRole(
				curContactRole.getContactRoleId(), contactRole.getName(),
				description));
	}

	@Override
	public void putContactRoleContactRolePermission(
			String agentName, String agentUID, String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateContactRolePermission(
			contactRoleKey, "add", contactRolePermission);
	}

	private Page<ContactRole> _getAccountContactRolesPage(
			Contact contact, String accountKey, String[] contactRoleTypes,
			Pagination pagination)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		return Page.of(
			transform(
				_contactRoleService.getContactAccountContactRoles(
					account.getAccountId(), contact.getContactId(),
					contactRoleTypes, pagination.getStartPosition(),
					pagination.getEndPosition()),
				contactRole -> ContactRoleUtil.toContactRole(contactRole)),
			pagination,
			_contactRoleService.getContactAccountContactRolesCount(
				account.getAccountId(), contact.getContactId(),
				contactRoleTypes));
	}

	private Page<ContactRole> _getTeamContactRolesPage(
			Contact contact, String teamKey, Pagination pagination)
		throws PortalException {

		Team team = _teamLocalService.getTeam(teamKey);

		return Page.of(
			transform(
				_contactRoleService.getContactTeamContactRoles(
					team.getTeamId(), contact.getContactId(),
					new String[] {ContactRole.Type.TEAM.toString()},
					pagination.getStartPosition(), pagination.getEndPosition()),
				contactRole -> ContactRoleUtil.toContactRole(contactRole)),
			pagination,
			_contactRoleService.getContactTeamContactRolesCount(
				team.getTeamId(), contact.getContactId(),
				new String[] {ContactRole.Type.TEAM.toString()}));
	}

	private void _updateContactRolePermission(
			String contactRoleKey, String operation,
			ContactRolePermission contactRolePermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.ContactRole contactRole =
			_contactRoleLocalService.getContactRole(contactRoleKey);

		_contactRolePermission.check(
			PermissionThreadLocal.getPermissionChecker(), contactRole,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(contactRolePermission.getAssignContact())) {
			actionIds.add(TaprootActionKeys.ASSIGN_CONTACT);
		}

		if (GetterUtil.getBoolean(contactRolePermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(contactRolePermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(contactRolePermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(contactRolePermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.taproot.model.ContactRole.class.getName(),
			contactRole.getContactRoleId(),
			contactRolePermission.getRoleNames(), actionIds);
	}

	private static final EntityModel _entityModel =
		new ContactRoleEntityModel();

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.ContactRolePermission
		_contactRolePermission;

	@Reference
	private ContactRoleService _contactRoleService;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private TeamLocalService _teamLocalService;

}