/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.servlet.taglib.ui;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import java.util.Objects;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = "form.navigator.entry.order:Integer=90",
	service = FormNavigatorEntry.class
)
public class LayoutSEOFormNavigatorEntry extends BaseLayoutFormNavigatorEntry {

	@Override
	public String getCategoryKey() {
		return FormNavigatorConstants.CATEGORY_KEY_LAYOUT_SEO;
	}

	@Override
	public String getKey() {
		return "seo";
	}

	@Override
	public boolean isVisible(User user, Layout layout) {
		Group group = _groupLocalService.fetchGroup(layout.getGroupId());

		if ((group != null) && group.isLayoutPrototype()) {
			return false;
		}

		Layout draftLayout = layoutLocalService.fetchLayout(
			portal.getClassNameId(Layout.class), layout.getPlid());

		if ((Objects.equals(layout.getType(), LayoutConstants.TYPE_CONTENT) ||
			 Objects.equals(
				 layout.getType(), LayoutConstants.TYPE_ASSET_DISPLAY)) &&
			(draftLayout == null)) {

			return false;
		}

		return true;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.admin.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/layout/seo.jsp";
	}

	@Reference
	private GroupLocalService _groupLocalService;

}