/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.util;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Eudaldo Alonso
 */
@ProviderType
public interface DL {

	public static final String MANUAL_CHECK_IN_REQUIRED =
		"manualCheckInRequired";

	public static final String MANUAL_CHECK_IN_REQUIRED_PATH =
		StringPool.SLASH + MANUAL_CHECK_IN_REQUIRED;

	public static final String OFFICE_EXTENSION = "officeExtension";

	public static final String OFFICE_EXTENSION_PATH =
		StringPool.SLASH + OFFICE_EXTENSION;

	public static final String WEBDAV_CHECK_IN_MODE = "webDAVCheckInMode";

	public int compareVersions(String version1, String version2);

	public String getAbsolutePath(PortletRequest portletRequest, long folderId)
		throws PortalException;

	public Set<String> getAllMediaGalleryMimeTypes();

	public String getDDMStructureKey(DLFileEntryType dlFileEntryType);

	public String getDDMStructureKey(String fileEntryTypeUuid);

	public String getDeprecatedDDMStructureKey(DLFileEntryType dlFileEntryType);

	public String getDeprecatedDDMStructureKey(long fileEntryTypeId);

	public String getDividedPath(long id);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getFileEntryControlPanelLink(
	 *             PortletRequest, long)}
	 */
	@Deprecated
	public String getDLFileEntryControlPanelLink(
			PortletRequest portletRequest, long fileEntryId)
		throws PortalException;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getFolderControlPanelLink(
	 *             PortletRequest, long)}
	 */
	@Deprecated
	public String getDLFolderControlPanelLink(
			PortletRequest portletRequest, long folderId)
		throws PortalException;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getDownloadURL(
	 *             FileEntry, FileVersion, ThemeDisplay, String)}
	 */
	@Deprecated
	public String getDownloadURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getDownloadURL(
	 *             FileEntry, FileVersion, ThemeDisplay, String, boolean,
	 *             boolean)}
	 */
	@Deprecated
	public String getDownloadURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString, boolean appendVersion, boolean absoluteURL);

	public Map<String, String> getEmailDefinitionTerms(
		PortletRequest portletRequest, String emailFromAddress,
		String emailFromName);

	public Map<String, String> getEmailFromDefinitionTerms(
		PortletRequest portletRequest, String emailFromAddress,
		String emailFromName);

	public List<FileEntry> getFileEntries(Hits hits);

	public String getFileEntryImage(
		FileEntry fileEntry, ThemeDisplay themeDisplay);

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public Set<Long> getFileEntryTypeSubscriptionClassPKs(long userId);

	public String getFileIcon(String extension);

	public String getFileIconCssClass(String extension);

	public String getGenericName(String extension);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getImagePreviewURL(
	 *             FileEntry, FileVersion, ThemeDisplay)}
	 */
	@Deprecated
	public String getImagePreviewURL(
			FileEntry fileEntry, FileVersion fileVersion,
			ThemeDisplay themeDisplay)
		throws Exception;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getImagePreviewURL(
	 *             FileEntry, FileVersion, ThemeDisplay, String, boolean,
	 *             boolean)}
	 */
	@Deprecated
	public String getImagePreviewURL(
			FileEntry fileEntry, FileVersion fileVersion,
			ThemeDisplay themeDisplay, String queryString,
			boolean appendVersion, boolean absoluteURL)
		throws PortalException;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getImagePreviewURL(
	 *             FileEntry, FileVersion, ThemeDisplay)}
	 */
	@Deprecated
	public String getImagePreviewURL(
			FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws Exception;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getPreviewURL(
	 *             FileEntry, FileVersion, ThemeDisplay, String)}
	 */
	@Deprecated
	public String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getPreviewURL(
	 *             FileEntry, FileVersion, ThemeDisplay, String, boolean,
	 *             boolean)}
	 */
	@Deprecated
	public String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString, boolean appendVersion, boolean absoluteURL);

	public <T> OrderByComparator<T> getRepositoryModelOrderByComparator(
		String orderByCol, String orderByType);

	public <T> OrderByComparator<T> getRepositoryModelOrderByComparator(
		String orderByCol, String orderByType, boolean orderByModel);

	public String getSanitizedFileName(String title, String extension);

	public String getTempFileId(long id, String version);

	public String getTempFileId(long id, String version, String languageId);

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #getThumbnailSrc(FileEntry, ThemeDisplay)}
	 */
	@Deprecated
	public String getThumbnailSrc(
			FileEntry fileEntry, DLFileShortcut dlFileShortcut,
			ThemeDisplay themeDisplay)
		throws Exception;

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #getThumbnailSrc(FileEntry, FileVersion, ThemeDisplay)}
	 */
	@Deprecated
	public String getThumbnailSrc(
			FileEntry fileEntry, FileVersion fileVersion,
			DLFileShortcut dlFileShortcut, ThemeDisplay themeDisplay)
		throws Exception;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getThumbnailSrc(
	 *             FileEntry, FileVersion, ThemeDisplay)}
	 */
	@Deprecated
	public String getThumbnailSrc(
			FileEntry fileEntry, FileVersion fileVersion,
			ThemeDisplay themeDisplay)
		throws Exception;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getThumbnailSrc(
	 *             FileEntry, ThemeDisplay)}
	 */
	@Deprecated
	public String getThumbnailSrc(
			FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws Exception;

	public String getThumbnailStyle();

	public String getThumbnailStyle(boolean max, int margin);

	public String getThumbnailStyle(
		boolean max, int margin, int height, int width);

	public String getTitleWithExtension(FileEntry fileEntry);

	public String getTitleWithExtension(String title, String extension);

	public String getUniqueFileName(
		long groupId, long folderId, String fileName);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getWebDavURL(
	 *             ThemeDisplay, Folder, FileEntry)}
	 */
	@Deprecated
	public String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry)
		throws PortalException;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getWebDavURL(
	 *             ThemeDisplay, Folder, FileEntry, boolean)}
	 */
	@Deprecated
	public String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry,
			boolean manualCheckInRequired)
		throws PortalException;

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             com.liferay.document.library.util.DLURLHelper#getWebDavURL(
	 *             ThemeDisplay, Folder, FileEntry, boolean, boolean)}
	 */
	@Deprecated
	public String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry,
			boolean manualCheckInRequired, boolean officeExtensionRequired)
		throws PortalException;

	public boolean hasWorkflowDefinitionLink(
			long companyId, long groupId, long folderId, long fileEntryTypeId)
		throws Exception;

	public boolean isAutoGeneratedDLFileEntryTypeDDMStructureKey(
		String ddmStructureKey);

	public boolean isOfficeExtension(String extension);

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             com.liferay.document.library.web.internal.util.DLSubscriptionUtil#isSubscribedToFileEntryType(
	 *             long, long, long, long)}
	 */
	@Deprecated
	public boolean isSubscribedToFileEntryType(
		long companyId, long groupId, long userId, long fileEntryTypeId);

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             com.liferay.document.library.web.internal.util.DLSubscriptionUtil#isSubscribedToFolder(
	 *             long, long, long, long)}
	 */
	@Deprecated
	public boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId)
		throws PortalException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             com.liferay.document.library.web.internal.util.DLSubscriptionUtil#isSubscribedToFolder(
	 *             long, long, long, long, boolean)}
	 */
	@Deprecated
	public boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId,
			boolean recursive)
		throws PortalException;

	public boolean isValidVersion(String version);

	public void startWorkflowInstance(
			long userId, DLFileVersion dlFileVersion, String syncEventType,
			ServiceContext serviceContext)
		throws PortalException;

}