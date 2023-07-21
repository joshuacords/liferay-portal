/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.select;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRequestParameterRetriever;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=select",
	service = DDMFormFieldValueRequestParameterRetriever.class
)
public class SelectDDMFormFieldValueRequestParameterRetriever
	implements DDMFormFieldValueRequestParameterRetriever {

	@Override
	public String get(
		HttpServletRequest httpServletRequest, String ddmFormFieldParameterName,
		String defaultDDMFormFieldParameterValue) {

		String[] parameterValues = ParamUtil.getParameterValues(
			httpServletRequest, ddmFormFieldParameterName,
			getDefaultDDMFormFieldParameterValues(
				defaultDDMFormFieldParameterValue));

		return jsonFactory.serialize(parameterValues);
	}

	protected String[] getDefaultDDMFormFieldParameterValues(
		String defaultDDMFormFieldParameterValue) {

		if (Validator.isNull(defaultDDMFormFieldParameterValue) ||
			Objects.equals(defaultDDMFormFieldParameterValue, "[]")) {

			return GetterUtil.DEFAULT_STRING_VALUES;
		}

		try {
			return jsonFactory.looseDeserialize(
				defaultDDMFormFieldParameterValue, String[].class);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}

			return StringUtil.split(defaultDDMFormFieldParameterValue);
		}
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		SelectDDMFormFieldValueRequestParameterRetriever.class);

}