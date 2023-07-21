/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.image.editor.integration.document.library.internal.display.context;

import com.liferay.document.library.display.context.BaseDLViewFileVersionDisplayContext;
import com.liferay.document.library.display.context.DLUIItemKeys;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.frontend.image.editor.integration.document.library.internal.display.context.logic.ImageEditorDLDisplayContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseUIItem;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.ToolbarItem;

import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ambrín Chaudhary
 */
public class ImageEditorDLViewFileVersionDisplayContext
	extends BaseDLViewFileVersionDisplayContext {

	public ImageEditorDLViewFileVersionDisplayContext(
		DLViewFileVersionDisplayContext parentDLDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, FileVersion fileVersion,
		ResourceBundle resourceBundle, DLURLHelper dlURLHelper) {

		super(
			_UUID, parentDLDisplayContext, httpServletRequest,
			httpServletResponse, fileVersion);

		_resourceBundle = resourceBundle;

		_imageEditorDLDisplayContextHelper =
			new ImageEditorDLDisplayContextHelper(
				fileVersion, httpServletRequest, dlURLHelper);
	}

	@Override
	public Menu getMenu() throws PortalException {
		Menu menu = super.getMenu();

		if (!_imageEditorDLDisplayContextHelper.isShowImageEditorAction()) {
			return menu;
		}

		_addEditWithImageEditorUIItem(
			menu.getMenuItems(),
			_imageEditorDLDisplayContextHelper.
				getJavacriptEditWithImageEditorMenuItem(_resourceBundle));

		return menu;
	}

	@Override
	public List<ToolbarItem> getToolbarItems() throws PortalException {
		List<ToolbarItem> toolbarItems = super.getToolbarItems();

		if (!_imageEditorDLDisplayContextHelper.isShowImageEditorAction()) {
			return toolbarItems;
		}

		return _addEditWithImageEditorUIItem(
			toolbarItems,
			_imageEditorDLDisplayContextHelper.
				getJavacriptEditWithImageEditorToolbarItem(_resourceBundle));
	}

	/**
	 * @see com.liferay.document.library.opener.google.drive.web.internal.display.context.DLOpenerGoogleDriveDLViewFileVersionDisplayContext#_addEditInGoogleDocsUIItem
	 * @see com.liferay.sharing.document.library.internal.display.context.SharingDLViewFileVersionDisplayContext#_addSharingUIItem
	 */
	private <T extends BaseUIItem> List<T> _addEditWithImageEditorUIItem(
		List<T> uiItems, T editWithImageEditorUIItem) {

		int i = 1;

		for (T uiItem : uiItems) {
			if (DLUIItemKeys.EDIT.equals(uiItem.getKey())) {
				break;
			}

			i++;
		}

		if (i >= uiItems.size()) {
			uiItems.add(editWithImageEditorUIItem);
		}
		else {
			uiItems.add(i, editWithImageEditorUIItem);
		}

		return uiItems;
	}

	private static final UUID _UUID = UUID.fromString(
		"ec0c6ec4-8671-4c9e-94a3-8c6bcca0437c");

	private final ImageEditorDLDisplayContextHelper
		_imageEditorDLDisplayContextHelper;
	private final ResourceBundle _resourceBundle;

}