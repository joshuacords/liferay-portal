/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service.impl;

import com.liferay.mobile.device.rules.constants.MDRConstants;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.base.MDRRuleGroupServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Edward C. Han
 */
@Component(
	property = {
		"json.web.service.context.name=mdr",
		"json.web.service.context.path=MDRRuleGroup"
	},
	service = AopService.class
)
public class MDRRuleGroupServiceImpl extends MDRRuleGroupServiceBaseImpl {

	@Override
	public MDRRuleGroup addRuleGroup(
			long groupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_RULE_GROUP);

		return mdrRuleGroupLocalService.addRuleGroup(
			groupId, nameMap, descriptionMap, serviceContext);
	}

	@Override
	public MDRRuleGroup copyRuleGroup(
			long ruleGroupId, long groupId, ServiceContext serviceContext)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		MDRRuleGroup ruleGroup = getRuleGroup(ruleGroupId);

		_mdrRuleGroupModelResourcePermission.check(
			permissionChecker, ruleGroup, ActionKeys.VIEW);

		_portletResourcePermission.check(
			permissionChecker, groupId, ActionKeys.ADD_RULE_GROUP);

		return mdrRuleGroupLocalService.copyRuleGroup(
			ruleGroup, groupId, serviceContext);
	}

	@Override
	public void deleteRuleGroup(long ruleGroupId) throws PortalException {
		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);

		_mdrRuleGroupModelResourcePermission.check(
			getPermissionChecker(), ruleGroup, ActionKeys.DELETE);

		mdrRuleGroupLocalService.deleteRuleGroup(ruleGroup);
	}

	@Override
	public MDRRuleGroup fetchRuleGroup(long ruleGroupId)
		throws PortalException {

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.fetchByPrimaryKey(
			ruleGroupId);

		if (ruleGroup != null) {
			_mdrRuleGroupModelResourcePermission.check(
				getPermissionChecker(), ruleGroup, ActionKeys.VIEW);
		}

		return ruleGroup;
	}

	@Override
	public MDRRuleGroup getRuleGroup(long ruleGroupId) throws PortalException {
		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);

		_mdrRuleGroupModelResourcePermission.check(
			getPermissionChecker(), ruleGroup, ActionKeys.VIEW);

		return ruleGroup;
	}

	@Override
	public List<MDRRuleGroup> getRuleGroups(
		long[] groupIds, int start, int end) {

		return mdrRuleGroupPersistence.filterFindByGroupId(
			groupIds, start, end);
	}

	@Override
	public int getRuleGroupsCount(long[] groupIds) {
		return mdrRuleGroupPersistence.filterCountByGroupId(groupIds);
	}

	@Override
	public MDRRuleGroup updateRuleGroup(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException {

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);

		_mdrRuleGroupModelResourcePermission.check(
			getPermissionChecker(), ruleGroup, ActionKeys.UPDATE);

		return mdrRuleGroupLocalService.updateRuleGroup(
			ruleGroupId, nameMap, descriptionMap, serviceContext);
	}

	@Reference(
		target = "(model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroup)"
	)
	private ModelResourcePermission<MDRRuleGroup>
		_mdrRuleGroupModelResourcePermission;

	@Reference(target = "(resource.name=" + MDRConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}