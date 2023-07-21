/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.admin.web.internal.application.list;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.search.web.application.list.constants.SearchPanelCategoryKeys;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	property = {
		"panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL,
		"panel.category.order:Integer=900"
	},
	service = PanelCategory.class
)
public class SearchPanelCategory extends BasePanelCategory {

	@Override
	public String getKey() {
		return SearchPanelCategoryKeys.CONTROL_PANEL_SEARCH;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "search");
	}

}