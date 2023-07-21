/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.web.internal.display;

import com.liferay.dynamic.data.mapping.data.provider.display.DDMDataProviderDisplay;
import com.liferay.dynamic.data.mapping.data.provider.web.internal.constants.DDMDataProviderPortletKeys;
import com.liferay.dynamic.data.mapping.util.DDMDisplayTabItem;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lino Alves
 */
@Component(
	property = "javax.portlet.name=" + DDMDataProviderPortletKeys.DYNAMIC_DATA_MAPPING_DATA_PROVIDER,
	service = DDMDataProviderDisplay.class
)
public class DefaultDDMDataProviderDisplay implements DDMDataProviderDisplay {

	@Override
	public List<DDMDisplayTabItem> getDDMDisplayTabItems() {
		return Arrays.asList(getDefaultDDMDisplayTabItem());
	}

	@Override
	public DDMDisplayTabItem getDefaultDDMDisplayTabItem() {
		return new DDMDisplayTabItem() {

			@Override
			public String getTitle(
				LiferayPortletRequest liferayPortletRequest,
				LiferayPortletResponse liferayPortletResponse) {

				String scopeTitle = ParamUtil.getString(
					liferayPortletRequest, "scopeTitle");

				if (Validator.isNull(scopeTitle)) {
					return LanguageUtil.get(
						liferayPortletRequest.getHttpServletRequest(),
						"data-providers");
				}

				return scopeTitle;
			}

		};
	}

	@Override
	public String getPortletId() {
		return DDMDataProviderPortletKeys.DYNAMIC_DATA_MAPPING_DATA_PROVIDER;
	}

	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(locale, "data-providers");
	}

}