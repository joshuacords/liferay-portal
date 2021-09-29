package com.liferay.portal.search.spi.model.permission;

import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class DynamicInheritanceRoleSetContributor
		<C extends GroupedModel, P extends GroupedModel>
	implements SearchPermissionDefinition.RoleSetContributor<C> {

	public DynamicInheritanceRoleSetContributor(
		ModelResourcePermission<P> parentModelResourcePermission,
		UnsafeFunction<C, P, ? extends PortalException>
			fetchParentUnsafeFunction,
		boolean checkParentAccess,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService) {

		_parentModelResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission);
		_fetchParentUnsafeFunction = Objects.requireNonNull(
			fetchParentUnsafeFunction);
		_checkParentAccess = checkParentAccess;
		_parentDynamicInheritanceRoleSetContributor = this;


		_portletResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission.getPortletResourcePermission());
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
	}

	public DynamicInheritanceRoleSetContributor(
		ModelResourcePermission<P> parentModelResourcePermission,
		UnsafeFunction<C, P, ? extends PortalException>
			fetchParentUnsafeFunction,
		boolean checkParentAccess,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService, DynamicInheritanceRoleSetContributor
		parentDynamicInheritanceRoleSetContributor) {

		_parentModelResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission);
		_fetchParentUnsafeFunction = Objects.requireNonNull(
			fetchParentUnsafeFunction);
		_checkParentAccess = checkParentAccess;

		_parentDynamicInheritanceRoleSetContributor =
			parentDynamicInheritanceRoleSetContributor;

		_portletResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission.getPortletResourcePermission());
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
	}

	@Override
	public void apply(
		RoleSetContributorContext roleSetContributorContext, C child,
		long resourcePrimKey)
		throws PortalException {

		P parent = _fetchParentUnsafeFunction.apply(child);

		List<Role> roles = _resourcePermissionLocalService.getRoles(
			roleSetContributorContext.getCompanyId(), child.getModelClassName(),
			ResourceConstants.SCOPE_INDIVIDUAL,	Long.toString(resourcePrimKey),
			ActionKeys.VIEW);

		_assignRolesAsIndividualViewRoleIdSets(
			roleSetContributorContext, child.getModelClassName(),
			resourcePrimKey, roles);

		if (parent == null) {
			return;
		}

		if (_checkParentAccess) {
			_applyAccessRoles(parent, roleSetContributorContext);

			_parentDynamicInheritanceRoleSetContributor.apply(
				roleSetContributorContext, parent,
				Long.parseLong(String.valueOf(parent.getPrimaryKeyObj()))); //make sure PrimaryKeyObj works for all asset parents
		}
	}

	private void _applyAccessRoles(
		P parent, RoleSetContributorContext roleSetContributorContext)
		throws PortalException {

		List<Role> roles = _resourcePermissionLocalService.getRoles(
			roleSetContributorContext.getCompanyId(), parent.getModelClassName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(parent.getPrimaryKeyObj()), ActionKeys.ACCESS);

		_assignRolesAsIndividualAccessRoleIdSets(
			roleSetContributorContext, parent.getModelClassName(),
			GetterUtil.getLong(parent.getPrimaryKeyObj()), roles);

	}

	private void _assignGuestRoleIdSet(
		RoleSetContributorContext roleSetContributorContext)
		throws PortalException {

		Set<String> roleIds = new HashSet<>();

		Role guestRole = _roleLocalService.getRole(
			roleSetContributorContext.getCompanyId(), RoleConstants.GUEST);

		roleIds.add(String.valueOf(guestRole.getRoleId()));

		roleSetContributorContext.addViewPermissionRoleIdSet(roleIds);
	}

	private void _assignRolesAsIndividualViewRoleIdSets(
		RoleSetContributorContext roleSetContributorContext, String className,
		long classPK, List<Role> roles)
		throws PortalException {

		Set<String> roleIdSet = new HashSet<>();

		for (Role role : roles) {
			roleIdSet.add(_roleToRoleId(
				roleSetContributorContext.getCompanyId(),
				roleSetContributorContext.getGroupId(), className, classPK,
				role));
		}

		roleSetContributorContext.addViewPermissionRoleIdSet(roleIdSet);
	}

	private void _assignRolesAsIndividualAccessRoleIdSets(
		RoleSetContributorContext roleSetContributorContext, String className,
		long classPK, List<Role> roles)
		throws PortalException {

		Set<Set<String>> roleIdSets = new HashSet<>();

		for (Role role : roles) {
			roleSetContributorContext.addAccessPermissionRoleIdSet(
				_roleToRoleIdSet(roleSetContributorContext, className, classPK,
					role));
		}
	}

	private Set<String> _roleToRoleIdSet(
		RoleSetContributorContext roleSetContributorContext, String className,
		long classPK, Role role) throws PortalException {

		Set<String> roleIds = new HashSet<>();

		roleIds.add(
			_roleToRoleId(
				roleSetContributorContext.getCompanyId(),
				roleSetContributorContext.getGroupId(), className, classPK,
				role));

		return roleIds;
	}

	private String _roleToRoleId(
		long companyId, long groupId, String className, long classPK,
		Role role)
		throws PortalException {

		Role ownerRole = _roleLocalService.getRole(
			companyId, RoleConstants.OWNER);

		if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
			(role.getType() == RoleConstants.TYPE_SITE)) {

			return groupId + StringPool.DASH + role.getRoleId();
		}
		else if (_isOwnerRoleId(companyId, role.getRoleId())) {
			ResourcePermission resourcePermission =
				_resourcePermissionLocalService.getResourcePermission(
					companyId, className, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(classPK), ownerRole.getRoleId());

			return resourcePermission.getOwnerId() + StringPool.DASH +
				   role.getRoleId();
		}
		else {
			return String.valueOf(role.getRoleId());
		}
	}

	private boolean _isOwnerRoleId(long companyId, long roleId) {
		Role ownerRole = _roleLocalService.fetchRole(
			companyId, RoleConstants.OWNER);

		if ((ownerRole != null) && (roleId == ownerRole.getRoleId())) {
			return true;
		}

		return false;
	}

	private final DynamicInheritanceRoleSetContributor
		_parentDynamicInheritanceRoleSetContributor;
	private final boolean _checkParentAccess;
	private final UnsafeFunction<C, P, ? extends PortalException>
		_fetchParentUnsafeFunction;
	private final ModelResourcePermission<P> _parentModelResourcePermission;
	private final PortletResourcePermission _portletResourcePermission;

	private final ResourcePermissionLocalService _resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;

}
