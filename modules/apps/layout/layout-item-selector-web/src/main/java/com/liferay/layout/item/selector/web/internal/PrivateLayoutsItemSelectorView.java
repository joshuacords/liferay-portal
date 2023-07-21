/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.item.selector.web.internal;

import com.liferay.item.selector.ItemSelectorView;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "view=private",
	service = {ItemSelectorView.class, PrivateLayoutsItemSelectorView.class}
)
public class PrivateLayoutsItemSelectorView
	extends BaseLayoutsItemSelectorView {

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public String getTitle(Locale locale) {
		return ResourceBundleUtil.getString(
			_portal.getResourceBundle(locale), "private-pages");
	}

	@Override
	public boolean isPrivateLayout() {
		return true;
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		Group group = themeDisplay.getScopeGroup();

		if (group.getPrivateLayoutsPageCount() <= 0) {
			return false;
		}

		return true;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.item.selector.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference
	private Portal _portal;

	private ServletContext _servletContext;

}