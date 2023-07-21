/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.TeamUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.PhloemNestedFieldsContextThreadLocal;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.TeamEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
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
	properties = "OSGI-INF/liferay/rest/v1_0/team.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, TeamResource.class}
)
public class TeamResourceImpl
	extends BaseTeamResourceImpl
	implements EntityModelResource, NestedFieldSupport {

	@Override
	public void deleteTeam(String agentName, String agentUID, String teamKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_teamService.deleteTeam(teamKey);
	}

	@Override
	public void deleteTeamContactByEmailAddres(
			String agentName, String agentUID, String teamKey,
			String[] emailAddresses)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		List<Contact> contacts = new ArrayList<>();

		for (String emailAddress : emailAddresses) {
			contacts.add(
				_contactIdentityProvider.getContactByEmailAddress(
					emailAddress));
		}

		_deleteTeamContacts(contacts, teamKey);
	}

	@Override
	public void deleteTeamContactByEmailAddresEmailAddressRole(
			String agentName, String agentUID, String teamKey,
			String emailAddress, String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_deleteTeamContactRole(
			_contactIdentityProvider.getContactByEmailAddress(emailAddress),
			teamKey, contactRoleKeys);
	}

	@Override
	public void deleteTeamContactByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		List<Contact> contacts = new ArrayList<>();

		for (String contactUuid : contactUuids) {
			contacts.add(
				_contactIdentityProvider.getContactByUuid(contactUuid));
		}

		_deleteTeamContacts(contacts, teamKey);
	}

	@Override
	public void deleteTeamContactByUuidContactUuidRole(
			String agentName, String agentUID, String teamKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_deleteTeamContactRole(
			_contactIdentityProvider.getContactByUuid(contactUuid), teamKey,
			contactRoleKeys);
	}

	@Override
	public void deleteTeamTeamPermission(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateTeamPermission(teamKey, "delete", teamPermission);
	}

	@Override
	public Page<Team> getAccountAccountKeyAssignedTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		PhloemNestedFieldsContextThreadLocal.addContextName("assignedTeams");
		PhloemNestedFieldsContextThreadLocal.setContextValue(
			"accountKey", accountKey);

		return Page.of(
			transform(
				_teamService.getAccountAssignedTeams(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				TeamUtil::toTeam),
			pagination, _teamService.getAccountAssignedTeamsCount(accountKey));
	}

	@Override
	public Page<Team> getAccountAccountKeyTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_teamService.getAccountTeams(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				TeamUtil::toTeam),
			pagination, _teamService.getAccountTeamsCount(accountKey));
	}

	@NestedField(parentClass = Account.class, value = "assignedTeams")
	public List<Team> getAccountNestedFieldAssignedTeams(
			@NestedFieldId("key") String accountKey)
		throws Exception {

		PhloemNestedFieldsContextThreadLocal.addContextName("assignedTeams");
		PhloemNestedFieldsContextThreadLocal.setContextValue(
			"accountKey", accountKey);

		return transform(
			_teamService.getAccountAssignedTeams(
				accountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			team -> TeamUtil.toTeam(team));
	}

	@NestedField(parentClass = Contact.class, value = "teams")
	public List<Team> getContactNestedFieldTeams(
			@NestedFieldId("key") String contactUuid)
		throws Exception {

		Contact contact = _contactLocalService.getContactByUuid(contactUuid);

		return transform(contact.getTeams(), team -> TeamUtil.toTeam(team));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Team getTeam(String teamKey) throws Exception {
		return TeamUtil.toTeam(_teamService.getTeam(teamKey));
	}

	@Override
	public Page<Team> getTeamByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_teamService.getTeams(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				team -> TeamUtil.toTeam(team)),
			pagination,
			_teamService.getTeamsCount(domain, entityName, entityId));
	}

	@Override
	public Page<Team> getTeamsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.Team.class, search,
			pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> TeamUtil.toTeam(
				_teamLocalService.getTeam(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Team postAccountAccountKeyTeam(
			String agentName, String agentUID, String accountKey, Team team)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		return TeamUtil.toTeam(
			_teamService.addTeam(accountKey, team.getName()));
	}

	@Override
	public Team putTeam(
			String agentName, String agentUID, String teamKey, Team team)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		return TeamUtil.toTeam(
			_teamService.updateTeam(teamKey, team.getName()));
	}

	@Override
	public void putTeamContactByEmailAddres(
			String agentName, String agentUID, String teamKey,
			String[] emailAddresses)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		List<Contact> contacts = new ArrayList<>();

		for (String emailAddress : emailAddresses) {
			contacts.add(
				_contactIdentityProvider.getContactByEmailAddress(
					emailAddress));
		}

		_putTeamContacts(contacts, teamKey);
	}

	@Override
	public void putTeamContactByEmailAddresEmailAddressRole(
			String agentName, String agentUID, String teamKey,
			String emailAddress, String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_putTeamContactRole(
			_contactIdentityProvider.getContactByEmailAddress(emailAddress),
			teamKey, contactRoleKeys);
	}

	@Override
	public void putTeamContactByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		List<Contact> contacts = new ArrayList<>(contactUuids.length);

		for (String contactUuid : contactUuids) {
			contacts.add(
				_contactIdentityProvider.getContactByUuid(contactUuid));
		}

		_putTeamContacts(contacts, teamKey);
	}

	@Override
	public void putTeamContactByUuidContactUuidRole(
			String agentName, String agentUID, String teamKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_putTeamContactRole(
			_contactIdentityProvider.getContactByUuid(contactUuid), teamKey,
			contactRoleKeys);
	}

	@Override
	public void putTeamTeamPermission(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateTeamPermission(teamKey, "add", teamPermission);
	}

	private void _deleteTeamContactRole(
			Contact contact, String teamKey, String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		for (String contactRoleKey : contactRoleKeys) {
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactRoleKey);

			_contactTeamRoleService.deleteContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}
	}

	private void _deleteTeamContacts(List<Contact> contacts, String teamKey)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		for (Contact contact : contacts) {
			_contactTeamRoleService.deleteContactTeamRoles(
				contact.getContactId(), team.getTeamId());
		}
	}

	private void _putTeamContactRole(
			Contact contact, String teamKey, String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		for (String contactRoleKey : contactRoleKeys) {
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactRoleKey);

			_contactTeamRoleService.addContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}
	}

	private void _putTeamContacts(List<Contact> contacts, String teamKey)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		ContactRole contactRole = _contactRoleLocalService.getMemberContactRole(
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				TEAM.toString());

		for (Contact contact : contacts) {
			_contactTeamRoleService.addContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}
	}

	private void _updateTeamPermission(
			String teamKey, String operation, TeamPermission teamPermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		_teamPermission.check(
			PermissionThreadLocal.getPermissionChecker(), team,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(teamPermission.getAssignContact())) {
			actionIds.add(TaprootActionKeys.ASSIGN_CONTACT);
		}

		if (GetterUtil.getBoolean(teamPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(teamPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(teamPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(teamPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.taproot.model.Team.class.getName(),
			team.getTeamId(), teamPermission.getRoleNames(), actionIds);
	}

	private static final EntityModel _entityModel = new TeamEntityModel();

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleService _contactTeamRoleService;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.TeamPermission
		_teamPermission;

	@Reference
	private TeamService _teamService;

}