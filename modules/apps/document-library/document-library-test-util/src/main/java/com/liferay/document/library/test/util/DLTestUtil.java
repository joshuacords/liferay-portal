/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.test.util;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestDataConstants;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ContentTypes;

import java.io.ByteArrayInputStream;

/**
 * @author Adolfo Pérez
 */
public class DLTestUtil {

	public static DLFileEntry addDLFileEntry(long dlFolderId) throws Exception {
		DLFolder dlFolder = DLFolderLocalServiceUtil.fetchDLFolder(dlFolderId);

		byte[] bytes = TestDataConstants.TEST_BYTE_ARRAY;

		return DLFileEntryLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), dlFolder.getGroupId(),
			dlFolder.getRepositoryId(), dlFolder.getFolderId(),
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT, null,
			null, new ByteArrayInputStream(bytes), bytes.length,
			ServiceContextTestUtil.getServiceContext(dlFolder.getGroupId()));
	}

	public static DLFolder addDLFolder(long groupId) throws Exception {
		return addDLFolder(
			groupId, ServiceContextTestUtil.getServiceContext(groupId));
	}

	public static DLFolder addDLFolder(
			long groupId, boolean deleteExisting, ServiceContext serviceContext)
		throws Exception {

		return addDLFolder(
			groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, deleteExisting,
			serviceContext);
	}

	public static DLFolder addDLFolder(
			long groupId, long parentFolderId, boolean deleteExisting,
			ServiceContext serviceContext)
		throws Exception {

		String name = RandomTestUtil.randomString();

		if (deleteExisting) {
			try {
				DLFolder folder = DLFolderLocalServiceUtil.getFolder(
					groupId, parentFolderId, name);

				DLFolderLocalServiceUtil.deleteFolder(folder.getFolderId());
			}
			catch (NoSuchFolderException noSuchFolderException) {
			}
		}

		return DLFolderLocalServiceUtil.addFolder(
			TestPropsValues.getUserId(), groupId, groupId, false,
			parentFolderId, name, StringPool.BLANK, false, serviceContext);
	}

	public static DLFolder addDLFolder(
			long groupId, ServiceContext serviceContext)
		throws Exception {

		return addDLFolder(groupId, true, serviceContext);
	}

}