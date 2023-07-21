/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for EntitlementDefinition. This utility wraps
 * <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionService
 * @generated
 */
public class EntitlementDefinitionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static EntitlementDefinition addEntitlementDefinition(
			long classNameId, String name, String description,
			String definition, int status)
		throws PortalException {

		return getService().addEntitlementDefinition(
			classNameId, name, description, definition, status);
	}

	public static EntitlementDefinition deleteEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		return getService().deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void synchronizeEntitlementDefinition(
			long entitlementDefinitionId)
		throws Exception {

		getService().synchronizeEntitlementDefinition(entitlementDefinitionId);
	}

	public static EntitlementDefinitionService getService() {
		return _service;
	}

	public static void setService(EntitlementDefinitionService service) {
		_service = service;
	}

	private static volatile EntitlementDefinitionService _service;

}