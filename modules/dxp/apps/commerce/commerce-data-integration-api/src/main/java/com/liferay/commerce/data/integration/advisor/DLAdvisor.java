/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.advisor;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.File;
import java.io.InputStream;

/**
 * @author guywandji
 */
public class DLAdvisor {

	public static DLFileEntry addOrUpdateFile(
			long folderId, long fileEntryId, String fileName,
			InputStream inStream, String mimeType)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		long groupId = serviceContext.getScopeGroupId();

		DLFileEntry fileEntry = null;

		if (fileEntryId == 0) {
			fileEntry = DLFileEntryLocalServiceUtil.addFileEntry(
				serviceContext.getUserId(), groupId, groupId, folderId,
				fileName, mimeType, fileName, fileName, null, 0, null, null,
				inStream, 0, serviceContext);
		}
		else {
			fileEntry = DLFileEntryLocalServiceUtil.updateFileEntry(
				serviceContext.getUserId(), fileEntryId, fileName, mimeType,
				fileName, fileName, "", true, 0L, null, null, inStream, 0L,
				serviceContext);
		}

		return fileEntry;
	}

	public static DLFileEntry addOrUpdateFile(
			long fileEntryId, String processName, File file)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Folder folder = _getOrCreateFolder(
			serviceContext.getScopeGroupId(), 0, "PROCESS_" + processName,
			serviceContext);

		String mimeType = MimeTypesUtil.getContentType(file);
		String sourceFileName = file.getName();
		long groupId = serviceContext.getScopeGroupId();
		DLFileEntry fileEntry = null;

		if (fileEntryId == 0) {
			fileEntry = DLFileEntryLocalServiceUtil.addFileEntry(
				serviceContext.getUserId(), groupId, groupId,
				folder.getFolderId(), sourceFileName, mimeType, sourceFileName,
				sourceFileName, null, 0, null, file, null, 0, serviceContext);
		}
		else {
			fileEntry = DLFileEntryLocalServiceUtil.updateFileEntry(
				serviceContext.getUserId(), fileEntryId, sourceFileName,
				mimeType, sourceFileName, sourceFileName, "", true, 0L, null,
				file, null, 0L, serviceContext);
		}

		return fileEntry;
	}

	private static Folder _getOrCreateFolder(
			long repositoryId, long parentFolderId, String folderName,
			ServiceContext serviceContext)
		throws PortalException {

		Folder folder = null;

		try {
			folder = DLAppLocalServiceUtil.getFolder(
				repositoryId, parentFolderId, folderName);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			folder = null;
		}

		if (folder == null) {
			folder = DLAppLocalServiceUtil.addFolder(
				serviceContext.getUserId(), repositoryId, parentFolderId,
				folderName, "", serviceContext);
		}

		return folder;
	}

	private static final Log _log = LogFactoryUtil.getLog(DLAdvisor.class);

}