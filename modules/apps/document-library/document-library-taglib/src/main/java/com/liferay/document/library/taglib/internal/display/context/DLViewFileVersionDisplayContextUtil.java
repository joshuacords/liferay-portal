/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.taglib.internal.display.context;

import com.liferay.document.library.display.context.DLDisplayContextProvider;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.portal.kernel.repository.model.FileVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = {})
public class DLViewFileVersionDisplayContextUtil {

	public static DLViewFileVersionDisplayContext
		getDLViewFileVersionDisplayContext(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FileVersion fileVersion) {

		return _dlViewFileVersionDisplayContextUtil._dlDisplayContextProvider.
			getDLViewFileVersionDisplayContext(
				httpServletRequest, httpServletResponse, fileVersion);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_dlViewFileVersionDisplayContextUtil = this;
	}

	@Deactivate
	protected void deactivate() {
		_dlViewFileVersionDisplayContextUtil = null;
	}

	private static DLViewFileVersionDisplayContextUtil
		_dlViewFileVersionDisplayContextUtil;

	@Reference
	private DLDisplayContextProvider _dlDisplayContextProvider;

}