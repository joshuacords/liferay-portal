/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.service;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for DLFileEntry. This utility wraps
 * <code>com.liferay.portlet.documentlibrary.service.impl.DLFileEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryService
 * @generated
 */
public class DLFileEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.documentlibrary.service.impl.DLFileEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static DLFileEntry addFileEntry(
			long groupId, long repositoryId, long folderId,
			String sourceFileName, String mimeType, String title,
			String description, String changeLog, long fileEntryTypeId,
			Map<String, com.liferay.dynamic.data.mapping.kernel.DDMFormValues>
				ddmFormValuesMap,
			java.io.File file, InputStream is, long size,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addFileEntry(
			groupId, repositoryId, folderId, sourceFileName, mimeType, title,
			description, changeLog, fileEntryTypeId, ddmFormValuesMap, file, is,
			size, serviceContext);
	}

	public static com.liferay.document.library.kernel.model.DLFileVersion
			cancelCheckOut(long fileEntryId)
		throws PortalException {

		return getService().cancelCheckOut(fileEntryId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 #checkInFileEntry(long, DLVersionNumberIncrease, String,
	 ServiceContext)}
	 */
	@Deprecated
	public static void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().checkInFileEntry(
			fileEntryId, major, changeLog, serviceContext);
	}

	public static void checkInFileEntry(
			long fileEntryId,
			com.liferay.document.library.kernel.model.DLVersionNumberIncrease
				dlVersionNumberIncrease,
			String changeLog,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().checkInFileEntry(
			fileEntryId, dlVersionNumberIncrease, changeLog, serviceContext);
	}

	public static void checkInFileEntry(
			long fileEntryId, String lockUuid,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().checkInFileEntry(fileEntryId, lockUuid, serviceContext);
	}

	public static DLFileEntry checkOutFileEntry(
			long fileEntryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().checkOutFileEntry(fileEntryId, serviceContext);
	}

	public static DLFileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().checkOutFileEntry(
			fileEntryId, owner, expirationTime, serviceContext);
	}

	public static DLFileEntry copyFileEntry(
			long groupId, long repositoryId, long fileEntryId,
			long destFolderId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().copyFileEntry(
			groupId, repositoryId, fileEntryId, destFolderId, serviceContext);
	}

	public static void deleteFileEntry(long fileEntryId)
		throws PortalException {

		getService().deleteFileEntry(fileEntryId);
	}

	public static void deleteFileEntry(
			long groupId, long folderId, String title)
		throws PortalException {

		getService().deleteFileEntry(groupId, folderId, title);
	}

	public static void deleteFileVersion(long fileEntryId, String version)
		throws PortalException {

		getService().deleteFileVersion(fileEntryId, version);
	}

	public static DLFileEntry fetchFileEntryByImageId(long imageId)
		throws PortalException {

		return getService().fetchFileEntryByImageId(imageId);
	}

	public static InputStream getFileAsStream(long fileEntryId, String version)
		throws PortalException {

		return getService().getFileAsStream(fileEntryId, version);
	}

	public static InputStream getFileAsStream(
			long fileEntryId, String version, boolean incrementCounter)
		throws PortalException {

		return getService().getFileAsStream(
			fileEntryId, version, incrementCounter);
	}

	public static List<DLFileEntry> getFileEntries(
			long groupId, long folderId, int status, int start, int end,
			OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getFileEntries(
			groupId, folderId, status, start, end, obc);
	}

	public static List<DLFileEntry> getFileEntries(
			long groupId, long folderId, int start, int end,
			OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getFileEntries(groupId, folderId, start, end, obc);
	}

	public static List<DLFileEntry> getFileEntries(
			long groupId, long folderId, long fileEntryTypeId, int start,
			int end, OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getFileEntries(
			groupId, folderId, fileEntryTypeId, start, end, obc);
	}

	public static List<DLFileEntry> getFileEntries(
			long groupId, long folderId, String[] mimeTypes, int status,
			int start, int end, OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getFileEntries(
			groupId, folderId, mimeTypes, status, start, end, obc);
	}

	public static List<DLFileEntry> getFileEntries(
			long groupId, long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getFileEntries(
			groupId, folderId, mimeTypes, start, end, obc);
	}

	public static int getFileEntriesCount(long groupId, long folderId) {
		return getService().getFileEntriesCount(groupId, folderId);
	}

	public static int getFileEntriesCount(
		long groupId, long folderId, int status) {

		return getService().getFileEntriesCount(groupId, folderId, status);
	}

	public static int getFileEntriesCount(
		long groupId, long folderId, long fileEntryTypeId) {

		return getService().getFileEntriesCount(
			groupId, folderId, fileEntryTypeId);
	}

	public static int getFileEntriesCount(
		long groupId, long folderId, String[] mimeTypes) {

		return getService().getFileEntriesCount(groupId, folderId, mimeTypes);
	}

	public static int getFileEntriesCount(
		long groupId, long folderId, String[] mimeTypes, int status) {

		return getService().getFileEntriesCount(
			groupId, folderId, mimeTypes, status);
	}

	public static DLFileEntry getFileEntry(long fileEntryId)
		throws PortalException {

		return getService().getFileEntry(fileEntryId);
	}

	public static DLFileEntry getFileEntry(
			long groupId, long folderId, String title)
		throws PortalException {

		return getService().getFileEntry(groupId, folderId, title);
	}

	public static DLFileEntry getFileEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getFileEntryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.lock.Lock getFileEntryLock(
		long fileEntryId) {

		return getService().getFileEntryLock(fileEntryId);
	}

	public static int getFoldersFileEntriesCount(
		long groupId, List<Long> folderIds, int status) {

		return getService().getFoldersFileEntriesCount(
			groupId, folderIds, status);
	}

	public static List<DLFileEntry> getGroupFileEntries(
			long groupId, long userId, long rootFolderId, int start, int end,
			OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getGroupFileEntries(
			groupId, userId, rootFolderId, start, end, obc);
	}

	public static List<DLFileEntry> getGroupFileEntries(
			long groupId, long userId, long repositoryId, long rootFolderId,
			String[] mimeTypes, int status, int start, int end,
			OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getGroupFileEntries(
			groupId, userId, repositoryId, rootFolderId, mimeTypes, status,
			start, end, obc);
	}

	public static List<DLFileEntry> getGroupFileEntries(
			long groupId, long userId, long rootFolderId, String[] mimeTypes,
			int status, int start, int end, OrderByComparator<DLFileEntry> obc)
		throws PortalException {

		return getService().getGroupFileEntries(
			groupId, userId, rootFolderId, mimeTypes, status, start, end, obc);
	}

	public static int getGroupFileEntriesCount(
			long groupId, long userId, long rootFolderId)
		throws PortalException {

		return getService().getGroupFileEntriesCount(
			groupId, userId, rootFolderId);
	}

	public static int getGroupFileEntriesCount(
			long groupId, long userId, long repositoryId, long rootFolderId,
			String[] mimeTypes, int status)
		throws PortalException {

		return getService().getGroupFileEntriesCount(
			groupId, userId, repositoryId, rootFolderId, mimeTypes, status);
	}

	public static int getGroupFileEntriesCount(
			long groupId, long userId, long rootFolderId, String[] mimeTypes,
			int status)
		throws PortalException {

		return getService().getGroupFileEntriesCount(
			groupId, userId, rootFolderId, mimeTypes, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static boolean hasFileEntryLock(long fileEntryId)
		throws PortalException {

		return getService().hasFileEntryLock(fileEntryId);
	}

	public static boolean isFileEntryCheckedOut(long fileEntryId)
		throws PortalException {

		return getService().isFileEntryCheckedOut(fileEntryId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static boolean isKeepFileVersionLabel(
			long fileEntryId, boolean majorVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().isKeepFileVersionLabel(
			fileEntryId, majorVersion, serviceContext);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #isKeepFileVersionLabel(long, boolean, ServiceContext)}
	 */
	@Deprecated
	public static boolean isKeepFileVersionLabel(
			long fileEntryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().isKeepFileVersionLabel(fileEntryId, serviceContext);
	}

	public static DLFileEntry moveFileEntry(
			long fileEntryId, long newFolderId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().moveFileEntry(
			fileEntryId, newFolderId, serviceContext);
	}

	public static com.liferay.portal.kernel.lock.Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException {

		return getService().refreshFileEntryLock(
			lockUuid, companyId, expirationTime);
	}

	public static void revertFileEntry(
			long fileEntryId, String version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().revertFileEntry(fileEntryId, version, serviceContext);
	}

	public static com.liferay.portal.kernel.search.Hits search(
			long groupId, long creatorUserId, int status, int start, int end)
		throws PortalException {

		return getService().search(groupId, creatorUserId, status, start, end);
	}

	public static com.liferay.portal.kernel.search.Hits search(
			long groupId, long creatorUserId, long folderId, String[] mimeTypes,
			int status, int start, int end)
		throws PortalException {

		return getService().search(
			groupId, creatorUserId, folderId, mimeTypes, status, start, end);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 #updateFileEntry(long, String, String, String, String,
	 String, DLVersionNumberIncrease, long, Map, File,
	 InputStream, long, ServiceContext)}
	 */
	@Deprecated
	public static DLFileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, long fileEntryTypeId,
			Map<String, com.liferay.dynamic.data.mapping.kernel.DDMFormValues>
				ddmFormValuesMap,
			java.io.File file, InputStream is, long size,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateFileEntry(
			fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, fileEntryTypeId, ddmFormValuesMap, file,
			is, size, serviceContext);
	}

	public static DLFileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			com.liferay.document.library.kernel.model.DLVersionNumberIncrease
				dlVersionNumberIncrease,
			long fileEntryTypeId,
			Map<String, com.liferay.dynamic.data.mapping.kernel.DDMFormValues>
				ddmFormValuesMap,
			java.io.File file, InputStream is, long size,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateFileEntry(
			fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, dlVersionNumberIncrease, fileEntryTypeId,
			ddmFormValuesMap, file, is, size, serviceContext);
	}

	public static DLFileEntry updateStatus(
			long userId, long fileVersionId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		return getService().updateStatus(
			userId, fileVersionId, status, serviceContext, workflowContext);
	}

	public static boolean verifyFileEntryCheckOut(
			long fileEntryId, String lockUuid)
		throws PortalException {

		return getService().verifyFileEntryCheckOut(fileEntryId, lockUuid);
	}

	public static boolean verifyFileEntryLock(long fileEntryId, String lockUuid)
		throws PortalException {

		return getService().verifyFileEntryLock(fileEntryId, lockUuid);
	}

	public static DLFileEntryService getService() {
		return _service;
	}

	public static void setService(DLFileEntryService service) {
		_service = service;
	}

	private static volatile DLFileEntryService _service;

}