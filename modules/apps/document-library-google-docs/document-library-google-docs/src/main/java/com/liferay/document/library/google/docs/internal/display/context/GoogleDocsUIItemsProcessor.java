/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.google.docs.internal.display.context;

import com.liferay.document.library.display.context.DLUIItemKeys;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsConstants;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsMetadataHelper;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.ToolbarItem;
import com.liferay.portal.kernel.servlet.taglib.ui.UIItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLToolbarItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLUIItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Iván Zaera
 */
public class GoogleDocsUIItemsProcessor {

	public GoogleDocsUIItemsProcessor(
		HttpServletRequest httpServletRequest,
		GoogleDocsMetadataHelper googleDocsMetadataHelper) {

		_httpServletRequest = httpServletRequest;
		_googleDocsMetadataHelper = googleDocsMetadataHelper;
	}

	public void processMenuItems(List<MenuItem> menuItems) {
		_removeUnsupportedUIItems(menuItems);

		URLMenuItem urlMenuItem = _insertEditInGoogleURLUIItem(
			new URLMenuItem(), menuItems);

		urlMenuItem.setMethod("GET");
	}

	public void processToolbarItems(List<ToolbarItem> toolbarItems) {
		_removeUnsupportedUIItems(toolbarItems);

		_insertEditInGoogleURLUIItem(new URLToolbarItem(), toolbarItems);
	}

	private int _getIndex(List<? extends UIItem> uiItems, String key) {
		for (int i = 0; i < uiItems.size(); i++) {
			UIItem uiItem = uiItems.get(i);

			if (key.equals(uiItem.getKey())) {
				return i;
			}
		}

		return -1;
	}

	private <T extends URLUIItem> T _insertEditInGoogleURLUIItem(
		T urlUIItem, List<? super T> urlUIItems) {

		if (!_googleDocsMetadataHelper.containsField(
				GoogleDocsConstants.DDM_FIELD_NAME_URL)) {

			return urlUIItem;
		}

		int index = _getIndex(
			(List<? extends UIItem>)urlUIItems, DLUIItemKeys.EDIT);

		if (index == -1) {
			index = 0;
		}

		urlUIItem.setKey(GoogleDocsUIItemKeys.EDIT_IN_GOOGLE);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(), getClass());

		String message = LanguageUtil.get(
			resourceBundle, "edit-in-google-docs");

		urlUIItem.setLabel(message);

		urlUIItem.setTarget("_blank");

		String editURL = _googleDocsMetadataHelper.getFieldValue(
			GoogleDocsConstants.DDM_FIELD_NAME_URL);

		urlUIItem.setURL(editURL);

		urlUIItems.add(index, urlUIItem);

		return urlUIItem;
	}

	private void _removeUIItem(List<? extends UIItem> uiItems, String key) {
		int index = _getIndex(uiItems, key);

		if (index != -1) {
			uiItems.remove(index);
		}
	}

	private void _removeUnsupportedUIItems(List<? extends UIItem> uiItems) {
		_removeUIItem(uiItems, DLUIItemKeys.CANCEL_CHECKOUT);
		_removeUIItem(uiItems, DLUIItemKeys.CHECKIN);
		_removeUIItem(uiItems, DLUIItemKeys.CHECKOUT);
		_removeUIItem(uiItems, DLUIItemKeys.DOWNLOAD);
		_removeUIItem(uiItems, DLUIItemKeys.OPEN_IN_MS_OFFICE);
	}

	private final GoogleDocsMetadataHelper _googleDocsMetadataHelper;
	private final HttpServletRequest _httpServletRequest;

}