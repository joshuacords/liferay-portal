/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.permission.contributor;

import com.liferay.depot.constants.DepotRolesConstants;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.UserBag;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.search.spi.model.permission.contributor.SearchPermissionFilterContributor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(service = SearchPermissionFilterContributor.class)
public class AssetLibraryMemberRoleSearchPermissionFilterContributor
	implements SearchPermissionFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, long companyId, long[] groupIds,
		long userId, PermissionChecker permissionChecker, String className) {

		if ((booleanFilter == null) || (permissionChecker == null)) {
			return;
		}

		try {
			_contribute(
				booleanFilter, companyId, permissionChecker.getUserBag());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}
	}

	private void _contribute(
			BooleanFilter booleanFilter, long companyId, UserBag userBag)
		throws Exception {

		if ((booleanFilter == null) || (userBag == null)) {
			return;
		}

		Role assetLibraryMemberRole = _roleLocalService.fetchRole(
			companyId, DepotRolesConstants.ASSET_LIBRARY_MEMBER);

		if (assetLibraryMemberRole == null) {
			return;
		}

		TermsFilter groupRolesTermsFilter = new TermsFilter(
			Field.GROUP_ROLE_ID);

		Set<Long> depotGroupIds = new HashSet<>();

		for (Group group : userBag.getGroups()) {
			if (group == null) {
				continue;
			}

			if (group.isDepot()) {
				depotGroupIds.add(group.getGroupId());
			}
			else if (group.isOrganization()) {
				List<Group> organizationGroups =
					_groupLocalService.getOrganizationGroups(
						group.getOrganizationId());

				for (Group organizationGroup : organizationGroups) {
					if (organizationGroup.isDepot()) {
						depotGroupIds.add(organizationGroup.getGroupId());
					}
				}
			}
		}

		for (long depotGroupId : depotGroupIds) {
			DepotEntry depotEntry = _depotEntryLocalService.getGroupDepotEntry(
				depotGroupId);

			if (depotEntry == null) {
				continue;
			}

			Group depotGroup = depotEntry.getGroup();

			groupRolesTermsFilter.addValue(
				StringBundler.concat(
					depotGroup.getGroupId(), StringPool.DASH,
					assetLibraryMemberRole.getRoleId()));
		}

		if (!groupRolesTermsFilter.isEmpty()) {
			booleanFilter.add(groupRolesTermsFilter, BooleanClauseOccur.SHOULD);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetLibraryMemberRoleSearchPermissionFilterContributor.class);

	@Reference
	private DepotEntryLocalService _depotEntryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}