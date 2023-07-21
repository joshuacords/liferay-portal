/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.google.docs.internal.display.context;

import com.liferay.document.library.google.docs.internal.util.GoogleDocsMetadataHelper;
import com.liferay.image.gallery.display.kernel.display.context.BaseIGViewFileVersionDisplayContext;
import com.liferay.image.gallery.display.kernel.display.context.IGViewFileVersionDisplayContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 */
public class GoogleDocsIGViewFileVersionDisplayContext
	extends BaseIGViewFileVersionDisplayContext
	implements IGViewFileVersionDisplayContext {

	public GoogleDocsIGViewFileVersionDisplayContext(
		IGViewFileVersionDisplayContext parentIGViewFileVersionDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, FileVersion fileVersion,
		GoogleDocsMetadataHelper googleDocsMetadataHelper) {

		super(
			_UUID, parentIGViewFileVersionDisplayContext, httpServletRequest,
			httpServletResponse, fileVersion);

		_googleDocsUIItemsProcessor = new GoogleDocsUIItemsProcessor(
			httpServletRequest, googleDocsMetadataHelper);
	}

	@Override
	public Menu getMenu() throws PortalException {
		Menu menu = super.getMenu();

		_googleDocsUIItemsProcessor.processMenuItems(menu.getMenuItems());

		return menu;
	}

	@Override
	public List<MenuItem> getMenuItems() throws PortalException {
		List<MenuItem> menuItems = super.getMenuItems();

		_googleDocsUIItemsProcessor.processMenuItems(menuItems);

		return menuItems;
	}

	private static final UUID _UUID = UUID.fromString(
		"D60D21C4-9626-4EDF-A658-336198DB4A34");

	private final GoogleDocsUIItemsProcessor _googleDocsUIItemsProcessor;

}