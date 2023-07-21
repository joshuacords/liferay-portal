/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MDRRuleService}.
 *
 * @author Edward C. Han
 * @see MDRRuleService
 * @generated
 */
public class MDRRuleServiceWrapper
	implements MDRRuleService, ServiceWrapper<MDRRuleService> {

	public MDRRuleServiceWrapper(MDRRuleService mdrRuleService) {
		_mdrRuleService = mdrRuleService;
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRule addRule(
			long ruleGroupId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleService.addRule(
			ruleGroupId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRule addRule(
			long ruleGroupId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			com.liferay.portal.kernel.util.UnicodeProperties typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleService.addRule(
			ruleGroupId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public void deleteRule(long ruleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_mdrRuleService.deleteRule(ruleId);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRule fetchRule(long ruleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleService.fetchRule(ruleId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mdrRuleService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRule getRule(long ruleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleService.getRule(ruleId);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRule updateRule(
			long ruleId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleService.updateRule(
			ruleId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRRule updateRule(
			long ruleId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			com.liferay.portal.kernel.util.UnicodeProperties
				typeSettingsProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrRuleService.updateRule(
			ruleId, nameMap, descriptionMap, type, typeSettingsProperties,
			serviceContext);
	}

	@Override
	public MDRRuleService getWrappedService() {
		return _mdrRuleService;
	}

	@Override
	public void setWrappedService(MDRRuleService mdrRuleService) {
		_mdrRuleService = mdrRuleService;
	}

	private MDRRuleService _mdrRuleService;

}