/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.screens.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScreensDDMStructureVersionService}.
 *
 * @author José Manuel Navarro
 * @see ScreensDDMStructureVersionService
 * @generated
 */
public class ScreensDDMStructureVersionServiceWrapper
	implements ScreensDDMStructureVersionService,
			   ServiceWrapper<ScreensDDMStructureVersionService> {

	public ScreensDDMStructureVersionServiceWrapper(
		ScreensDDMStructureVersionService screensDDMStructureVersionService) {

		_screensDDMStructureVersionService = screensDDMStructureVersionService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getDDMStructureVersion(
			long structureId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensDDMStructureVersionService.getDDMStructureVersion(
			structureId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _screensDDMStructureVersionService.getOSGiServiceIdentifier();
	}

	@Override
	public ScreensDDMStructureVersionService getWrappedService() {
		return _screensDDMStructureVersionService;
	}

	@Override
	public void setWrappedService(
		ScreensDDMStructureVersionService screensDDMStructureVersionService) {

		_screensDDMStructureVersionService = screensDDMStructureVersionService;
	}

	private ScreensDDMStructureVersionService
		_screensDDMStructureVersionService;

}