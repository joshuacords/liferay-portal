/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceBOMFolder. This utility wraps
 * <code>com.liferay.commerce.bom.service.impl.CommerceBOMFolderServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderService
 * @generated
 */
public class CommerceBOMFolderServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.bom.service.impl.CommerceBOMFolderServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceBOMFolder addCommerceBOMFolder(
			long userId, long parentCommerceBOMFolderId, String name,
			boolean logo, byte[] logoBytes)
		throws PortalException {

		return getService().addCommerceBOMFolder(
			userId, parentCommerceBOMFolderId, name, logo, logoBytes);
	}

	public static void deleteCommerceBOMFolder(long commerceBOMFolderId)
		throws PortalException {

		getService().deleteCommerceBOMFolder(commerceBOMFolderId);
	}

	public static CommerceBOMFolder getCommerceBOMFolder(
			long commerceBOMFolderId)
		throws PortalException {

		return getService().getCommerceBOMFolder(commerceBOMFolderId);
	}

	public static List<CommerceBOMFolder> getCommerceBOMFolders(
		long companyId, int start, int end) {

		return getService().getCommerceBOMFolders(companyId, start, end);
	}

	public static List<CommerceBOMFolder> getCommerceBOMFolders(
		long companyId, long parentCommerceBOMFolderId, int start, int end) {

		return getService().getCommerceBOMFolders(
			companyId, parentCommerceBOMFolderId, start, end);
	}

	public static int getCommerceBOMFoldersCount(long companyId) {
		return getService().getCommerceBOMFoldersCount(companyId);
	}

	public static int getCommerceBOMFoldersCount(
		long companyId, long parentCommerceBOMFolderId) {

		return getService().getCommerceBOMFoldersCount(
			companyId, parentCommerceBOMFolderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceBOMFolder updateCommerceBOMFolder(
			long commerceBOMFolderId, String name, boolean logo,
			byte[] logoBytes)
		throws PortalException {

		return getService().updateCommerceBOMFolder(
			commerceBOMFolderId, name, logo, logoBytes);
	}

	public static CommerceBOMFolderService getService() {
		return _service;
	}

	public static void setService(CommerceBOMFolderService service) {
		_service = service;
	}

	private static volatile CommerceBOMFolderService _service;

}