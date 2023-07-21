/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntitlementDefinitionService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionService
 * @generated
 */
public class EntitlementDefinitionServiceWrapper
	implements EntitlementDefinitionService,
			   ServiceWrapper<EntitlementDefinitionService> {

	public EntitlementDefinitionServiceWrapper(
		EntitlementDefinitionService entitlementDefinitionService) {

		_entitlementDefinitionService = entitlementDefinitionService;
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			addEntitlementDefinition(
				long classNameId, String name, String description,
				String definition, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionService.addEntitlementDefinition(
			classNameId, name, description, definition, status);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			deleteEntitlementDefinition(long entitlementDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionService.deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entitlementDefinitionService.getOSGiServiceIdentifier();
	}

	@Override
	public void synchronizeEntitlementDefinition(long entitlementDefinitionId)
		throws Exception {

		_entitlementDefinitionService.synchronizeEntitlementDefinition(
			entitlementDefinitionId);
	}

	@Override
	public EntitlementDefinitionService getWrappedService() {
		return _entitlementDefinitionService;
	}

	@Override
	public void setWrappedService(
		EntitlementDefinitionService entitlementDefinitionService) {

		_entitlementDefinitionService = entitlementDefinitionService;
	}

	private EntitlementDefinitionService _entitlementDefinitionService;

}