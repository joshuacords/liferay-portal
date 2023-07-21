/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;

import java.util.List;

/**
 * @author Chema Balsas
 */
public class NavigationBarTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayNavigationBar");
		setHydrate(true);
		setModuleBaseName("navigation-bar");

		return super.doStartTag();
	}

	public void setInverted(Boolean inverted) {
		putValue("inverted", inverted);
	}

	public void setNavigationItems(List<NavigationItem> navigationItems) {
		putValue("items", navigationItems);
	}

}