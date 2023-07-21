/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.capabilities;

import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Iván Zaera
 */
public interface WorkflowSupport {

	public void addFileEntry(
			long userId, FileEntry fileEntry, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #updateFileEntry(long, FileEntry, DLVersionNumberIncrease,
	 *             ServiceContext)}
	 */
	@Deprecated
	public void checkInFileEntry(
			long userId, FileEntry fileEntry, boolean majorVersion,
			ServiceContext serviceContext)
		throws PortalException;

	public default void checkInFileEntry(
			long userId, FileEntry fileEntry,
			DLVersionNumberIncrease dlVersionNumberIncrease,
			ServiceContext serviceContext)
		throws PortalException {

		boolean majorVersion = false;

		if (dlVersionNumberIncrease == DLVersionNumberIncrease.MAJOR) {
			majorVersion = true;
		}

		checkInFileEntry(userId, fileEntry, majorVersion, serviceContext);
	}

	public void revertFileEntry(
			long userId, FileEntry fileEntry, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #updateFileEntry(long, FileEntry, DLVersionNumberIncrease,
	 *             ServiceContext)}
	 */
	@Deprecated
	public void updateFileEntry(
			long userId, FileEntry fileEntry, boolean majorVersion,
			ServiceContext serviceContext)
		throws PortalException;

	public default void updateFileEntry(
			long userId, FileEntry fileEntry,
			DLVersionNumberIncrease dlVersionNumberIncrease,
			ServiceContext serviceContext)
		throws PortalException {

		boolean majorVersion = false;

		if (dlVersionNumberIncrease == DLVersionNumberIncrease.MAJOR) {
			majorVersion = true;
		}

		updateFileEntry(userId, fileEntry, majorVersion, serviceContext);
	}

}