/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoDefinitionService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionService
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class KaleoDefinitionServiceWrapper
	implements KaleoDefinitionService, ServiceWrapper<KaleoDefinitionService> {

	public KaleoDefinitionServiceWrapper(
		KaleoDefinitionService kaleoDefinitionService) {

		_kaleoDefinitionService = kaleoDefinitionService;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
			getKaleoDefinitions(int start, int end) {

		return _kaleoDefinitionService.getKaleoDefinitions(start, end);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
			getKaleoDefinitions(long companyId, int start, int end) {

		return _kaleoDefinitionService.getKaleoDefinitions(
			companyId, start, end);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _kaleoDefinitionService.getOSGiServiceIdentifier();
	}

	@Override
	public KaleoDefinitionService getWrappedService() {
		return _kaleoDefinitionService;
	}

	@Override
	public void setWrappedService(
		KaleoDefinitionService kaleoDefinitionService) {

		_kaleoDefinitionService = kaleoDefinitionService;
	}

	private KaleoDefinitionService _kaleoDefinitionService;

}