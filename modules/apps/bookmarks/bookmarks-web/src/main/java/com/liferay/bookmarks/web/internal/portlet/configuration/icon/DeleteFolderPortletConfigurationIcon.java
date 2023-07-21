/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.web.internal.portlet.configuration.icon;

import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.web.internal.portlet.action.ActionUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS_ADMIN,
		"path=/bookmarks/view_folder"
	},
	service = PortletConfigurationIcon.class
)
public class DeleteFolderPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		String key = "delete";

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (isTrashEnabled(themeDisplay.getScopeGroupId())) {
			key = "move-to-recycle-bin";
		}

		return LanguageUtil.get(
			getResourceBundle(themeDisplay.getLocale()), key);
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		PortletURL deleteURL = _portal.getControlPanelPortletURL(
			portletRequest, BookmarksPortletKeys.BOOKMARKS_ADMIN,
			PortletRequest.ACTION_PHASE);

		deleteURL.setParameter(
			ActionRequest.ACTION_NAME, "/bookmarks/edit_folder");

		String cmd = Constants.DELETE;

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (isTrashEnabled(themeDisplay.getScopeGroupId())) {
			cmd = Constants.MOVE_TO_TRASH;
		}

		deleteURL.setParameter(Constants.CMD, cmd);

		BookmarksFolder folder = null;

		try {
			folder = ActionUtil.getFolder(portletRequest);
		}
		catch (Exception exception) {
			return null;
		}

		PortletURL parentFolderURL = _portal.getControlPanelPortletURL(
			portletRequest, BookmarksPortletKeys.BOOKMARKS_ADMIN,
			PortletRequest.RENDER_PHASE);

		long parentFolderId = folder.getParentFolderId();

		if (parentFolderId ==
				BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			parentFolderURL.setParameter(
				"mvcRenderCommandName", "/bookmarks/view");
		}
		else {
			parentFolderURL.setParameter(
				"mvcRenderCommandName", "/bookmarks/view_folder");
			parentFolderURL.setParameter(
				"folderId", String.valueOf(parentFolderId));
		}

		deleteURL.setParameter("redirect", parentFolderURL.toString());

		deleteURL.setParameter(
			"folderId", String.valueOf(folder.getFolderId()));

		return deleteURL.toString();
	}

	@Override
	public double getWeight() {
		return 100;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		try {
			BookmarksFolder folder = ActionUtil.getFolder(portletRequest);

			if (folder == null) {
				return false;
			}

			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (_bookmarksFolderModelResourcePermission.contains(
					themeDisplay.getPermissionChecker(), folder,
					ActionKeys.DELETE)) {

				return true;
			}
		}
		catch (Exception exception) {
		}

		return false;
	}

	protected boolean isTrashEnabled(long groupId) {
		try {
			if (_trashHelper.isTrashEnabled(groupId)) {
				return true;
			}
		}
		catch (Exception exception) {
		}

		return false;
	}

	@Reference(
		target = "(model.class.name=com.liferay.bookmarks.model.BookmarksFolder)"
	)
	private ModelResourcePermission<BookmarksFolder>
		_bookmarksFolderModelResourcePermission;

	@Reference
	private Portal _portal;

	@Reference
	private TrashHelper _trashHelper;

}