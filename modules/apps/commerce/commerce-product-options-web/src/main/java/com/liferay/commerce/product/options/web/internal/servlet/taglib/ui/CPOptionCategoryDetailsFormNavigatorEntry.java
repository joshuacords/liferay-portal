/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.options.web.internal.servlet.taglib.ui;

import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "form.navigator.entry.order:Integer=100",
	service = FormNavigatorEntry.class
)
public class CPOptionCategoryDetailsFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CPOptionCategory> {

	@Override
	public String getCategoryKey() {
		return CPOptionCategoryFormNavigatorConstants.
			CATEGORY_KEY_COMMERCE_PRODUCT_OPTION_CATEGORY_DETAILS;
	}

	@Override
	public String getFormNavigatorId() {
		return CPOptionCategoryFormNavigatorConstants.
			FORM_NAVIGATOR_ID_COMMERCE_PRODUCT_OPTION_CATEGORY;
	}

	@Override
	public String getKey() {
		return "details";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "details");
	}

	@Override
	protected String getJspPath() {
		return "/option_category/details.jsp";
	}

}