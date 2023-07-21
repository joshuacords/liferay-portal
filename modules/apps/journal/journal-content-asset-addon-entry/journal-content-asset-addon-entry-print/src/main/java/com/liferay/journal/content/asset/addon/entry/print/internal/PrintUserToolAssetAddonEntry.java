/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.asset.addon.entry.print.internal;

import com.liferay.journal.content.asset.addon.entry.UserToolAssetAddonEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPAssetAddonEntry;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = UserToolAssetAddonEntry.class)
public class PrintUserToolAssetAddonEntry
	extends BaseJSPAssetAddonEntry implements UserToolAssetAddonEntry {

	@Override
	public String getIcon() {
		return "print";
	}

	@Override
	public String getJspPath() {
		return "/print.jsp";
	}

	@Override
	public String getKey() {
		return "enablePrint";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "print");
	}

	@Override
	public Double getWeight() {
		return 2.0;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.asset.addon.entry.print)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}