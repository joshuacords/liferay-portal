/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer.internal.servlet.taglib;

import com.liferay.dynamic.data.mapping.form.renderer.internal.servlet.taglib.helper.BaseDDMFormFieldTypesDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bruno Basto
 */
@Component(immediate = true, service = DynamicInclude.class)
public class DDMFormFieldTypesDynamicInclude
	extends BaseDDMFormFieldTypesDynamicInclude {

	public static final String LIFERAY_SHARED_DDM_FORM_FIELD_TYPES_INCLUDED =
		"LIFERAY_SHARED_DDM_FORM_FIELD_TYPES_INCLUDED";

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (themeDisplay.isAjax()) {
			include(httpServletResponse);

			httpServletRequest.removeAttribute(
				LIFERAY_SHARED_DDM_FORM_FIELD_TYPES_INCLUDED);
		}
		else {
			httpServletRequest.setAttribute(
				LIFERAY_SHARED_DDM_FORM_FIELD_TYPES_INCLUDED, Boolean.TRUE);
		}
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			DDMFormFieldTypesDynamicInclude.class.getName());
	}

}