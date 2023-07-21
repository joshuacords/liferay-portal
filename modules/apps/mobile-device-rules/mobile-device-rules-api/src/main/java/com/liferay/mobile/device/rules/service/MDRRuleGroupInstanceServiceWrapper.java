/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MDRRuleGroupInstanceService}.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceService
 * @generated
 */
public class MDRRuleGroupInstanceServiceWrapper
	implements MDRRuleGroupInstanceService,
			   ServiceWrapper<MDRRuleGroupInstanceService> {

	public MDRRuleGroupInstanceServiceWrapper(
		MDRRuleGroupInstanceService mdrRuleGroupInstanceService) {

		_mdrRuleGroupInstanceService = mdrRuleGroupInstanceService;
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroupInstance
			addRuleGroupInstance(
				long groupId, String className, long classPK, long ruleGroupId,
				int priority,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupInstanceService.addRuleGroupInstance(
			groupId, className, classPK, ruleGroupId, priority, serviceContext);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroupInstance
			addRuleGroupInstance(
				long groupId, String className, long classPK, long ruleGroupId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupInstanceService.addRuleGroupInstance(
			groupId, className, classPK, ruleGroupId, serviceContext);
	}

	@Override
	public void deleteRuleGroupInstance(long ruleGroupInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_mdrRuleGroupInstanceService.deleteRuleGroupInstance(
			ruleGroupInstanceId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mdrRuleGroupInstanceService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List
		<com.liferay.mobile.device.rules.model.MDRRuleGroupInstance>
			getRuleGroupInstances(
				String className, long classPK, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.mobile.device.rules.model.MDRRuleGroupInstance>
						orderByComparator) {

		return _mdrRuleGroupInstanceService.getRuleGroupInstances(
			className, classPK, start, end, orderByComparator);
	}

	@Override
	public int getRuleGroupInstancesCount(String className, long classPK) {
		return _mdrRuleGroupInstanceService.getRuleGroupInstancesCount(
			className, classPK);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRuleGroupInstance
			updateRuleGroupInstance(long ruleGroupInstanceId, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleGroupInstanceService.updateRuleGroupInstance(
			ruleGroupInstanceId, priority);
	}

	@Override
	public MDRRuleGroupInstanceService getWrappedService() {
		return _mdrRuleGroupInstanceService;
	}

	@Override
	public void setWrappedService(
		MDRRuleGroupInstanceService mdrRuleGroupInstanceService) {

		_mdrRuleGroupInstanceService = mdrRuleGroupInstanceService;
	}

	private MDRRuleGroupInstanceService _mdrRuleGroupInstanceService;

}