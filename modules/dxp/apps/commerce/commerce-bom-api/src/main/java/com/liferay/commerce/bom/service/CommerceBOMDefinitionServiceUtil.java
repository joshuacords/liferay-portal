/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceBOMDefinition. This utility wraps
 * <code>com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionService
 * @generated
 */
public class CommerceBOMDefinitionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.bom.service.impl.CommerceBOMDefinitionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceBOMDefinition addCommerceBOMDefinition(
			long userId, long commerceBOMFolderId, long cpAttachmentFileEntryId,
			String name, String friendlyUrl)
		throws PortalException {

		return getService().addCommerceBOMDefinition(
			userId, commerceBOMFolderId, cpAttachmentFileEntryId, name,
			friendlyUrl);
	}

	public static void deleteCommerceBOMDefinition(long commerceBOMDefinitionId)
		throws PortalException {

		getService().deleteCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	public static CommerceBOMDefinition getCommerceBOMDefinition(
			long commerceBOMDefinitionId)
		throws PortalException {

		return getService().getCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	public static List<CommerceBOMDefinition> getCommerceBOMDefinitions(
		long commerceBOMFolderId, int start, int end) {

		return getService().getCommerceBOMDefinitions(
			commerceBOMFolderId, start, end);
	}

	public static int getCommerceBOMDefinitionsCount(long commerceBOMFolderId) {
		return getService().getCommerceBOMDefinitionsCount(commerceBOMFolderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceBOMDefinition updateCommerceBOMDefinition(
			long commerceBOMDefinitionId, long cpAttachmentFileEntryId,
			String name)
		throws PortalException {

		return getService().updateCommerceBOMDefinition(
			commerceBOMDefinitionId, cpAttachmentFileEntryId, name);
	}

	public static CommerceBOMDefinitionService getService() {
		return _service;
	}

	public static void setService(CommerceBOMDefinitionService service) {
		_service = service;
	}

	private static volatile CommerceBOMDefinitionService _service;

}