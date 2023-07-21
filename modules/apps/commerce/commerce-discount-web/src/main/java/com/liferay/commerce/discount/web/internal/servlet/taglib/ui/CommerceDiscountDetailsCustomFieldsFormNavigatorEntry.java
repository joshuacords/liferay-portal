/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.web.internal.servlet.taglib.ui;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.taglib.util.CustomAttributesUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	property = "form.navigator.entry.order:Integer=10",
	service = FormNavigatorEntry.class
)
public class CommerceDiscountDetailsCustomFieldsFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CommerceDiscount> {

	@Override
	public String getCategoryKey() {
		return "details";
	}

	@Override
	public String getFormNavigatorId() {
		return "commerce.discount.form";
	}

	@Override
	public String getKey() {
		return "custom-fields";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

	@Override
	public boolean isVisible(
		User user, CommerceDiscount cpDefinitionSpecificationOptionValue) {

		boolean hasCustomAttributesAvailable = false;

		try {
			long classPK = 0;

			if (cpDefinitionSpecificationOptionValue != null) {
				classPK =
					cpDefinitionSpecificationOptionValue.
						getCommerceDiscountId();
			}

			hasCustomAttributesAvailable =
				CustomAttributesUtil.hasCustomAttributes(
					user.getCompanyId(), CommerceDiscount.class.getName(),
					classPK, null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		return hasCustomAttributesAvailable;
	}

	@Override
	protected String getJspPath() {
		return "/discount/custom_fields.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDiscountDetailsCustomFieldsFormNavigatorEntry.class);

}