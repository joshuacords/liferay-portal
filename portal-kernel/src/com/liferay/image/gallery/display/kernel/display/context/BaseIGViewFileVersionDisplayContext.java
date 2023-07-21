/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.image.gallery.display.kernel.display.context;

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
public class BaseIGViewFileVersionDisplayContext
	extends BaseIGDisplayContext<IGViewFileVersionDisplayContext>
	implements IGViewFileVersionDisplayContext {

	public BaseIGViewFileVersionDisplayContext(
		UUID uuid, IGViewFileVersionDisplayContext parentIGDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, FileVersion fileVersion) {

		super(
			uuid, parentIGDisplayContext, httpServletRequest,
			httpServletResponse);

		this.fileVersion = fileVersion;
	}

	@Override
	public Menu getMenu() throws PortalException {
		return parentDisplayContext.getMenu();
	}

	@Override
	public List<MenuItem> getMenuItems() throws PortalException {
		return parentDisplayContext.getMenuItems();
	}

	protected FileVersion fileVersion;

}