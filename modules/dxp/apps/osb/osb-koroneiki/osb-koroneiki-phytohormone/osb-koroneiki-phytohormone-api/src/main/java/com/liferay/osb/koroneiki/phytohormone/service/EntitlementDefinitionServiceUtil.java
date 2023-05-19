/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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