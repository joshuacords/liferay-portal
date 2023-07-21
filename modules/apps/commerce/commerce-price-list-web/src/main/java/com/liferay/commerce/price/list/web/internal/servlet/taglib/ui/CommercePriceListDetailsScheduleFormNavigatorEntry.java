/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.web.internal.servlet.taglib.ui;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "form.navigator.entry.order:Integer=10",
	service = FormNavigatorEntry.class
)
public class CommercePriceListDetailsScheduleFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CommercePriceList> {

	@Override
	public String getCategoryKey() {
		return CommercePriceListFormNavigatorConstants.
			CATEGORY_KEY_COMMERCE_PRICE_LIST_DETAILS;
	}

	@Override
	public String getFormNavigatorId() {
		return CommercePriceListFormNavigatorConstants.
			FORM_NAVIGATOR_ID_COMMERCE_PRICE_LIST;
	}

	@Override
	public String getKey() {
		return "schedule";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "schedule");
	}

	@Override
	protected String getJspPath() {
		return "/price_list/schedule.jsp";
	}

}