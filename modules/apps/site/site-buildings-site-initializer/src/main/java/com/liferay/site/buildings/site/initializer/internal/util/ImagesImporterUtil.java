/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.buildings.site.initializer.internal.util;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Eudaldo Alonso
 */
public class ImagesImporterUtil {

	public static List<FileEntry> importFile(
			long userId, long groupId, File file)
		throws Exception {

		List<FileEntry> fileEntries = new ArrayList<>();

		ZipFile zipFile = new ZipFile(file);

		Enumeration<? extends ZipEntry> enumeration = zipFile.entries();

		while (enumeration.hasMoreElements()) {
			ZipEntry zipEntry = enumeration.nextElement();

			if (zipEntry.isDirectory()) {
				continue;
			}

			String fileName = zipEntry.getName();

			byte[] bytes = null;

			try (InputStream is = zipFile.getInputStream(zipEntry)) {
				bytes = FileUtil.getBytes(is);
			}

			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
				userId, groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				fileName, MimeTypesUtil.getContentType(fileName), bytes,
				ServiceContextThreadLocal.getServiceContext());

			fileEntries.add(fileEntry);
		}

		return fileEntries;
	}

}