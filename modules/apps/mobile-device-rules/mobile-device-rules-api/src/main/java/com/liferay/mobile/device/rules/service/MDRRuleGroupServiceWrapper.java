/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MDRRuleGroupService}.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupService
 * @generated
 */
public class MDRRuleGroupServiceWrapper
	implements MDRRuleGroupService, ServiceWrapper<MDRRuleGroupService> {

	public MDRRuleGroupServiceWrapper(MDRRuleGroupService mdrRuleGroupService) {
		_mdrRuleGroupService = mdrRuleGroupService;
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroup addRuleGroup(
			long groupId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupService.addRuleGroup(
			groupId, nameMap, descriptionMap, serviceContext);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroup copyRuleGroup(
			long ruleGroupId, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupService.copyRuleGroup(
			ruleGroupId, groupId, serviceContext);
	}

	@Override
	public void deleteRuleGroup(long ruleGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_mdrRuleGroupService.deleteRuleGroup(ruleGroupId);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroup fetchRuleGroup(
			long ruleGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupService.fetchRuleGroup(ruleGroupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mdrRuleGroupService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroup getRuleGroup(
			long ruleGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupService.getRuleGroup(ruleGroupId);
	}

	@Override
	public java.util.List<com.liferay.mobile.device.rules.model.MDRRuleGroup>
		getRuleGroups(long[] groupIds, int start, int end) {

		return _mdrRuleGroupService.getRuleGroups(groupIds, start, end);
	}

	@Override
	public int getRuleGroupsCount(long[] groupIds) {
		return _mdrRuleGroupService.getRuleGroupsCount(groupIds);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroup updateRuleGroup(
			long ruleGroupId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupService.updateRuleGroup(
			ruleGroupId, nameMap, descriptionMap, serviceContext);
	}

	@Override
	public MDRRuleGroupService getWrappedService() {
		return _mdrRuleGroupService;
	}

	@Override
	public void setWrappedService(MDRRuleGroupService mdrRuleGroupService) {
		_mdrRuleGroupService = mdrRuleGroupService;
	}

	private MDRRuleGroupService _mdrRuleGroupService;

}