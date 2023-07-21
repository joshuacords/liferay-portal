/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MDRActionService}.
 *
 * @author Edward C. Han
 * @see MDRActionService
 * @generated
 */
public class MDRActionServiceWrapper
	implements MDRActionService, ServiceWrapper<MDRActionService> {

	public MDRActionServiceWrapper(MDRActionService mdrActionService) {
		_mdrActionService = mdrActionService;
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRAction addAction(
			long ruleGroupInstanceId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrActionService.addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRAction addAction(
			long ruleGroupInstanceId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			com.liferay.portal.kernel.util.UnicodeProperties
				typeSettingsProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrActionService.addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type,
			typeSettingsProperties, serviceContext);
	}

	@Override
	public void deleteAction(long actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_mdrActionService.deleteAction(actionId);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRAction fetchAction(
			long actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrActionService.fetchAction(actionId);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRAction getAction(
			long actionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrActionService.getAction(actionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mdrActionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRAction updateAction(
			long actionId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			String typeSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrActionService.updateAction(
			actionId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public com.liferay.mobile.device.rules.model.MDRAction updateAction(
			long actionId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, String type,
			com.liferay.portal.kernel.util.UnicodeProperties
				typeSettingsProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _mdrActionService.updateAction(
			actionId, nameMap, descriptionMap, type, typeSettingsProperties,
			serviceContext);
	}

	@Override
	public MDRActionService getWrappedService() {
		return _mdrActionService;
	}

	@Override
	public void setWrappedService(MDRActionService mdrActionService) {
		_mdrActionService = mdrActionService;
	}

	private MDRActionService _mdrActionService;

}