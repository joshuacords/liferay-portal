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

		Role guestRole = _roleLocalService.getRole(
			roleSetContributorContext.getCompanyId(), RoleConstants.GUEST);

		if (parent == null) {
			if (roles.contains(guestRole)) {
				_assignGuestRoleIdSet(roleSetContributorContext);

				return;
			}

			_assignListAsIndividualRoleIdSets(
				roleSetContributorContext, child.getModelClassName(),
				GetterUtil.getLong(child.getPrimaryKeyObj()), roles);

			return;
		}

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

	private void _assignListAsIndividualRoleIdSets(
		RoleSetContributorContext roleSetContributorContext, String className,
		long classPK, List<Role> roles)
		throws PortalException {

		Set<Set<String>> roleIdSets = new HashSet<>();

		for (Role role : roles) {
			roleSetContributorContext.addViewPermissionRoleIdSet(
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

	private final boolean _checkParentAccess;
	private final UnsafeFunction<C, P, ? extends PortalException>
		_fetchParentUnsafeFunction;
	private final ModelResourcePermission<P> _parentModelResourcePermission;
	private final PortletResourcePermission _portletResourcePermission;

	private final ResourcePermissionLocalService _resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;

}
