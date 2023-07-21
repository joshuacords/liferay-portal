/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.taglib.servlet.taglib;

import com.liferay.data.engine.taglib.servlet.taglib.base.BaseDataLayoutBuilderTag;
import com.liferay.data.engine.taglib.servlet.taglib.util.DataLayoutTaglibUtil;

import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @author Jeyvison Nascimento
 * @author Leonardo Barros
 */
public class DataLayoutBuilderTag extends BaseDataLayoutBuilderTag {

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();

		Set<Locale> availableLocales = DataLayoutTaglibUtil.getAvailableLocales(
			getDataLayoutId(), request);

		setNamespacedAttribute(
			request, "availableLocales",
			availableLocales.toArray(new Locale[0]));
		setNamespacedAttribute(
			request, "dataLayout",
			DataLayoutTaglibUtil.getDataLayoutJSONObject(
				availableLocales, getDataLayoutId(), request,
				(HttpServletResponse)pageContext.getResponse()));

		setNamespacedAttribute(
			request, "dataLayoutBuilderModule",
			DataLayoutTaglibUtil.resolveModule(
				"data-engine-taglib/data_layout_builder/js" +
					"/DataLayoutBuilder.es"));
		setNamespacedAttribute(
			request, "fieldTypes",
			DataLayoutTaglibUtil.getFieldTypesJSONArray(request));
		setNamespacedAttribute(
			request, "fieldTypesModules",
			DataLayoutTaglibUtil.resolveFieldTypesModules());

		return result;
	}

}