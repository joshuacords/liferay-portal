/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceDataIntegrationProcessService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessService
 * @generated
 */
public class CommerceDataIntegrationProcessServiceWrapper
	implements CommerceDataIntegrationProcessService,
			   ServiceWrapper<CommerceDataIntegrationProcessService> {

	public CommerceDataIntegrationProcessServiceWrapper(
		CommerceDataIntegrationProcessService
			commerceDataIntegrationProcessService) {

		_commerceDataIntegrationProcessService =
			commerceDataIntegrationProcessService;
	}

	@Override
	public
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess addCommerceDataIntegrationProcess(
					long userId, String name, String type,
					com.liferay.portal.kernel.util.UnicodeProperties
						typeSettingsProperties)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessService.
			addCommerceDataIntegrationProcess(
				userId, name, type, typeSettingsProperties);
	}

	@Override
	public void deleteCommerceDataIntegrationProcess(
			long commerceDataIntegrationProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDataIntegrationProcessService.
			deleteCommerceDataIntegrationProcess(
				commerceDataIntegrationProcessId);
	}

	@Override
	public
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess fetchCommerceDataIntegrationProcess(
					long commerceDataIntegrationProcessId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessService.
			fetchCommerceDataIntegrationProcess(
				commerceDataIntegrationProcessId);
	}

	@Override
	public
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess getCommerceDataIntegrationProcess(
					long commerceDataIntegrationProcessId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessService.
			getCommerceDataIntegrationProcess(commerceDataIntegrationProcessId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess> getCommerceDataIntegrationProcesses(
					long companyId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessService.
			getCommerceDataIntegrationProcesses(companyId, start, end);
	}

	@Override
	public int getCommerceDataIntegrationProcessesCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessService.
			getCommerceDataIntegrationProcessesCount(companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDataIntegrationProcessService.
			getOSGiServiceIdentifier();
	}

	@Override
	public
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess updateCommerceDataIntegrationProcess(
					long commerceDataIntegrationProcessId, String name,
					com.liferay.portal.kernel.util.UnicodeProperties
						typeSettingsProperties)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessService.
			updateCommerceDataIntegrationProcess(
				commerceDataIntegrationProcessId, name, typeSettingsProperties);
	}

	@Override
	public
		com.liferay.commerce.data.integration.model.
			CommerceDataIntegrationProcess
					updateCommerceDataIntegrationProcessTrigger(
						long commerceDataIntegrationProcessId, boolean active,
						String cronExpression, int startDateMonth,
						int startDateDay, int startDateYear, int startDateHour,
						int startDateMinute, int endDateMonth, int endDateDay,
						int endDateYear, int endDateHour, int endDateMinute,
						boolean neverEnd)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDataIntegrationProcessService.
			updateCommerceDataIntegrationProcessTrigger(
				commerceDataIntegrationProcessId, active, cronExpression,
				startDateMonth, startDateDay, startDateYear, startDateHour,
				startDateMinute, endDateMonth, endDateDay, endDateYear,
				endDateHour, endDateMinute, neverEnd);
	}

	@Override
	public CommerceDataIntegrationProcessService getWrappedService() {
		return _commerceDataIntegrationProcessService;
	}

	@Override
	public void setWrappedService(
		CommerceDataIntegrationProcessService
			commerceDataIntegrationProcessService) {

		_commerceDataIntegrationProcessService =
			commerceDataIntegrationProcessService;
	}

	private CommerceDataIntegrationProcessService
		_commerceDataIntegrationProcessService;

}