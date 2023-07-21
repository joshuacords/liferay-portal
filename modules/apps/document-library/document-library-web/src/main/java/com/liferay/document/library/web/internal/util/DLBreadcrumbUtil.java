/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.util;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.web.internal.settings.DLPortletInstanceSettings;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class DLBreadcrumbUtil {

	public static void addPortletBreadcrumbEntries(
			FileEntry fileEntry, HttpServletRequest httpServletRequest,
			RenderResponse renderResponse)
		throws Exception {

		Folder folder = fileEntry.getFolder();

		if (folder.getFolderId() !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			addPortletBreadcrumbEntries(
				folder, httpServletRequest, renderResponse);
		}

		PortletURL portletURL = renderResponse.createRenderURL();

		FileEntry unescapedFileEntry = fileEntry.toUnescapedModel();

		portletURL.setParameter(
			"mvcRenderCommandName", "/document_library/view_file_entry");
		portletURL.setParameter(
			"fileEntryId", String.valueOf(fileEntry.getFileEntryId()));

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, unescapedFileEntry.getTitle(),
			portletURL.toString());
	}

	public static void addPortletBreadcrumbEntries(
			FileShortcut fileShortcut, HttpServletRequest httpServletRequest,
			RenderResponse renderResponse)
		throws Exception {

		Folder folder = fileShortcut.getFolder();

		if (folder.getFolderId() !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			addPortletBreadcrumbEntries(
				folder, httpServletRequest, renderResponse);
		}

		FileShortcut unescapedDLFileShortcut = fileShortcut.toUnescapedModel();

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/document_library/view_file_entry");
		portletURL.setParameter(
			"fileEntryId", String.valueOf(fileShortcut.getToFileEntryId()));

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, unescapedDLFileShortcut.getToTitle(),
			portletURL.toString());
	}

	public static void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest httpServletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/document_library/view");

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		DLPortletInstanceSettings dlPortletInstanceSettings =
			DLPortletInstanceSettings.getInstance(
				themeDisplay.getLayout(), portletDisplay.getId());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, themeDisplay.translate("home"),
			portletURL.toString(),
			HashMapBuilder.<String, Object>put(
				"direction-right", Boolean.TRUE.toString()
			).put(
				"folder-id", dlPortletInstanceSettings.getRootFolderId()
			).build());

		portletURL.setParameter(
			"mvcRenderCommandName", "/document_library/view_folder");

		addPortletBreadcrumbEntries(folder, httpServletRequest, portletURL);
	}

	public static void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest httpServletRequest,
			PortletURL portletURL)
		throws Exception {

		long rootFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		boolean ignoreRootFolder = ParamUtil.getBoolean(
			httpServletRequest, "ignoreRootFolder");

		if (!ignoreRootFolder) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			DLPortletInstanceSettings dlPortletInstanceSettings =
				DLPortletInstanceSettings.getInstance(
					themeDisplay.getLayout(), portletDisplay.getId());

			rootFolderId = dlPortletInstanceSettings.getRootFolderId();
		}

		List<Folder> ancestorFolders = Collections.emptyList();

		if ((folder != null) && (folder.getFolderId() != rootFolderId) &&
			!folder.isRoot()) {

			ancestorFolders = folder.getAncestors();

			int indexOfRootFolder = -1;

			for (int i = 0; i < ancestorFolders.size(); i++) {
				Folder ancestorFolder = ancestorFolders.get(i);

				if (rootFolderId == ancestorFolder.getFolderId()) {
					indexOfRootFolder = i;
				}
			}

			if (indexOfRootFolder > -1) {
				ancestorFolders = ancestorFolders.subList(0, indexOfRootFolder);
			}
		}

		Collections.reverse(ancestorFolders);

		for (Folder ancestorFolder : ancestorFolders) {
			portletURL.setParameter(
				"folderId", String.valueOf(ancestorFolder.getFolderId()));

			PortalUtil.addPortletBreadcrumbEntry(
				httpServletRequest, ancestorFolder.getName(),
				portletURL.toString(),
				HashMapBuilder.<String, Object>put(
					"direction-right", Boolean.TRUE.toString()
				).put(
					"folder-id", ancestorFolder.getFolderId()
				).build());
		}

		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (folder != null) {
			folderId = folder.getFolderId();
		}

		portletURL.setParameter("folderId", String.valueOf(folderId));

		if ((folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) &&
			(folderId != rootFolderId)) {

			Folder unescapedFolder = folder.toUnescapedModel();

			PortalUtil.addPortletBreadcrumbEntry(
				httpServletRequest, unescapedFolder.getName(),
				portletURL.toString(),
				HashMapBuilder.<String, Object>put(
					"direction-right", Boolean.TRUE.toString()
				).put(
					"folder-id", folderId
				).build());
		}
	}

	public static void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest httpServletRequest,
			RenderResponse renderResponse)
		throws Exception {

		String mvcRenderCommandName = ParamUtil.getString(
			httpServletRequest, "mvcRenderCommandName");

		PortletURL portletURL = renderResponse.createRenderURL();

		if (mvcRenderCommandName.equals("/document_library/select_folder")) {
			long groupId = ParamUtil.getLong(httpServletRequest, "groupId");
			boolean ignoreRootFolder = ParamUtil.getBoolean(
				httpServletRequest, "ignoreRootFolder");

			long selectedFolderId = ParamUtil.getLong(
				httpServletRequest, "selectedFolderId",
				ParamUtil.getLong(
					httpServletRequest, "folderId",
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));

			_addPortletBreadcrumbEntry(
				httpServletRequest, "mvcRenderCommandName",
				mvcRenderCommandName, groupId, ignoreRootFolder,
				selectedFolderId, portletURL);
		}
		else {
			long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

			if (folder != null) {
				folderId = folder.getFolderId();
			}

			if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				portletURL.setParameter(
					"mvcRenderCommandName", "/document_library/view_folder");
			}
			else {
				portletURL.setParameter(
					"mvcRenderCommandName", "/document_library/view");
			}
		}

		addPortletBreadcrumbEntries(folder, httpServletRequest, portletURL);
	}

	public static void addPortletBreadcrumbEntries(
			long folderId, HttpServletRequest httpServletRequest,
			RenderResponse renderResponse)
		throws Exception {

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			Folder folder = DLAppLocalServiceUtil.getFolder(folderId);

			if (folder.getFolderId() !=
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				addPortletBreadcrumbEntries(
					folder, httpServletRequest, renderResponse);
			}
		}
	}

	private static void _addPortletBreadcrumbEntry(
			HttpServletRequest httpServletRequest, String parameterName,
			String parameterValue, long groupId, boolean ignoreRootFolder,
			long selectedFolderId, PortletURL portletURL)
		throws WindowStateException {

		portletURL.setParameter(parameterName, parameterValue);
		portletURL.setParameter("groupId", String.valueOf(groupId));

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long scopeGroupId = themeDisplay.getScopeGroupId();

		if (scopeGroupId != groupId) {
			portletURL.setParameter(
				"folderId",
				String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));
		}

		portletURL.setParameter(
			"ignoreRootFolder", String.valueOf(ignoreRootFolder));
		portletURL.setParameter(
			"selectedFolderId", String.valueOf(selectedFolderId));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, themeDisplay.translate("home"),
			portletURL.toString());
	}

}