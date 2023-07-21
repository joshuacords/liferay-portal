/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.TeamRoleUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.PhloemNestedFieldsContextThreadLocal;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.TeamRoleEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleService;
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
	properties = "OSGI-INF/liferay/rest/v1_0/team-role.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, TeamRoleResource.class}
)
public class TeamRoleResourceImpl
	extends BaseTeamRoleResourceImpl
	implements EntityModelResource, NestedFieldSupport {

	@Override
	public void deleteTeamRole(
			String agentName, String agentUID, String teamRoleKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_teamRoleService.deleteTeamRole(teamRoleKey);
	}

	@Override
	public void deleteTeamRoleTeamRolePermission(
			String agentName, String agentUID, String teamRoleKey,
			TeamRolePermission teamRolePermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateTeamRolePermission(teamRoleKey, "delete", teamRolePermission);
	}

	@NestedField("teamRoles")
	@Override
	public Page<TeamRole> getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
			String accountKey, @NestedFieldId("key") String teamKey,
			Pagination pagination)
		throws Exception {

		String parentContextName =
			PhloemNestedFieldsContextThreadLocal.getLastContextName();

		if (Validator.isNotNull(parentContextName) &&
			parentContextName.equals("assignedTeams")) {

			accountKey =
				(String)PhloemNestedFieldsContextThreadLocal.getContextValue(
					"accountKey");
		}

		Account account = _accountLocalService.getAccount(accountKey);

		Team team = _teamLocalService.getTeam(teamKey);

		return Page.of(
			transform(
				_teamRoleService.getTeamAccountTeamRoles(
					account.getAccountId(), team.getTeamId(),
					pagination.getStartPosition(), pagination.getEndPosition()),
				TeamRoleUtil::toTeamRole),
			pagination,
			_teamRoleService.getTeamAccountTeamRolesCount(
				account.getAccountId(), team.getTeamId()));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public TeamRole getTeamRole(String teamRoleKey) throws Exception {
		return TeamRoleUtil.toTeamRole(
			_teamRoleService.getTeamRole(teamRoleKey));
	}

	@Override
	public TeamRole getTeamRoleByTypeTeamRoleTypeByNameTeamRoleName(
			String teamRoleType, String teamRoleName)
		throws Exception {

		return TeamRoleUtil.toTeamRole(
			_teamRoleService.getTeamRole(teamRoleName, teamRoleType));
	}

	@Override
	public Page<TeamRole> getTeamRolesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.TeamRole.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> TeamRoleUtil.toTeamRole(
				_teamRoleLocalService.getTeamRole(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public TeamRole getTeamRolesTeamRoleTypeTeamRoleName(
			String teamRoleType, String teamRoleName)
		throws Exception {

		return getTeamRoleByTypeTeamRoleTypeByNameTeamRoleName(
			teamRoleType, teamRoleName);
	}

	@Override
	public TeamRole postTeamRole(
			String agentName, String agentUID, TeamRole teamRole)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		TeamRole.Type teamRoleType = teamRole.getType();

		return TeamRoleUtil.toTeamRole(
			_teamRoleService.addTeamRole(
				teamRole.getName(), teamRole.getDescription(),
				teamRoleType.toString()));
	}

	@Override
	public TeamRole putTeamRole(
			String agentName, String agentUID, String teamRoleKey,
			TeamRole teamRole)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		com.liferay.osb.koroneiki.taproot.model.TeamRole curTeamRole =
			_teamRoleLocalService.getTeamRole(teamRoleKey);

		String description = GetterUtil.getString(
			teamRole.getDescription(), curTeamRole.getDescription());

		return TeamRoleUtil.toTeamRole(
			_teamRoleService.updateTeamRole(
				curTeamRole.getTeamRoleId(), teamRole.getName(), description));
	}

	@Override
	public void putTeamRoleTeamRolePermission(
			String agentName, String agentUID, String teamRoleKey,
			TeamRolePermission teamRolePermission)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_updateTeamRolePermission(teamRoleKey, "add", teamRolePermission);
	}

	private void _updateTeamRolePermission(
			String teamRoleKey, String operation,
			TeamRolePermission teamRolePermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.TeamRole teamRole =
			_teamRoleLocalService.getTeamRole(teamRoleKey);

		_teamRolePermission.check(
			PermissionThreadLocal.getPermissionChecker(), teamRole,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(teamRolePermission.getAssignTeam())) {
			actionIds.add(TaprootActionKeys.ASSIGN_TEAM);
		}

		if (GetterUtil.getBoolean(teamRolePermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(teamRolePermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(teamRolePermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(teamRolePermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.taproot.model.TeamRole.class.getName(),
			teamRole.getTeamRoleId(), teamRolePermission.getRoleNames(),
			actionIds);
	}

	private static final EntityModel _entityModel = new TeamRoleEntityModel();

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.TeamRolePermission
		_teamRolePermission;

	@Reference
	private TeamRoleService _teamRoleService;

}