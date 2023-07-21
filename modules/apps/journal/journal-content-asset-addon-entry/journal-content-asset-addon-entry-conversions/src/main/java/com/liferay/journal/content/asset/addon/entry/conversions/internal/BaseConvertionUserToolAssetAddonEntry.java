/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.asset.addon.entry.conversions.internal;

import com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil;
import com.liferay.journal.content.asset.addon.entry.UserToolAssetAddonEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPAssetAddonEntry;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julio Camarero
 */
public abstract class BaseConvertionUserToolAssetAddonEntry
	extends BaseJSPAssetAddonEntry implements UserToolAssetAddonEntry {

	public abstract String getExtension();

	@Override
	public String getJspPath() {
		return "/conversions.jsp";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.format(
			resourceBundle, "download-as-x",
			StringUtil.toUpperCase(getExtension()));
	}

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		httpServletRequest.setAttribute("extension", getExtension());

		super.include(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean isEnabled() {
		if (!DocumentConversionUtil.isEnabled()) {
			return false;
		}

		if (!ArrayUtil.contains(
				DocumentConversionUtil.getConversions("html"),
				getExtension())) {

			return false;
		}

		return super.isEnabled();
	}

}