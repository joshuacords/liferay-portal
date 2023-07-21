/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.taglib.servlet.taglib;

import com.liferay.dynamic.data.mapping.form.builder.settings.DDMFormBuilderSettingsRequest;
import com.liferay.dynamic.data.mapping.form.builder.settings.DDMFormBuilderSettingsResponse;
import com.liferay.dynamic.data.mapping.form.taglib.servlet.taglib.base.BaseDDMFormBuilderTag;
import com.liferay.dynamic.data.mapping.form.taglib.servlet.taglib.util.DDMFormTaglibUtil;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rafael Praxedes
 */
public class DDMFormBuilderTag extends BaseDDMFormBuilderTag {

	public String getDDMFormBuilderContext(
		HttpServletRequest httpServletRequest) {

		return DDMFormTaglibUtil.getFormBuilderContext(
			GetterUtil.getLong(getDdmStructureId()),
			GetterUtil.getLong(getDdmStructureVersionId()), httpServletRequest);
	}

	protected DDMForm getDDMForm() {
		return DDMFormTaglibUtil.getDDMForm(
			GetterUtil.getLong(getDdmStructureId()),
			GetterUtil.getLong(getDdmStructureVersionId()));
	}

	protected DDMFormBuilderSettingsResponse getDDMFormBuilderSettings(
		HttpServletRequest httpServletRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return DDMFormTaglibUtil.getDDMFormBuilderSettings(
			DDMFormBuilderSettingsRequest.with(
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				getFieldSetClassNameId(), getDDMForm(),
				themeDisplay.getLocale()));
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		DDMFormBuilderSettingsResponse ddmFormBuilderSettingsResponse =
			getDDMFormBuilderSettings(httpServletRequest);

		setNamespacedAttribute(
			httpServletRequest, "dataProviderInstancesURL",
			ddmFormBuilderSettingsResponse.getDataProviderInstancesURL());
		setNamespacedAttribute(
			httpServletRequest, "dataProviderInstanceParameterSettingsURL",
			ddmFormBuilderSettingsResponse.
				getDataProviderInstanceParameterSettingsURL());
		setNamespacedAttribute(
			httpServletRequest, "evaluatorURL",
			ddmFormBuilderSettingsResponse.getFormContextProviderURL());
		setNamespacedAttribute(
			httpServletRequest, "fieldSets",
			ddmFormBuilderSettingsResponse.getFieldSets());
		setNamespacedAttribute(
			httpServletRequest, "fieldSetDefinitionURL",
			ddmFormBuilderSettingsResponse.getFieldSetDefinitionURL());
		setNamespacedAttribute(
			httpServletRequest, "fieldSettingsDDMFormContextURL",
			ddmFormBuilderSettingsResponse.getFieldSettingsDDMFormContextURL());

		setNamespacedAttribute(
			httpServletRequest, "formBuilderContext",
			getDDMFormBuilderContext(httpServletRequest));
		setNamespacedAttribute(
			httpServletRequest, "functionsMetadata",
			ddmFormBuilderSettingsResponse.getFunctionsMetadata());
		setNamespacedAttribute(
			httpServletRequest, "functionsURL",
			ddmFormBuilderSettingsResponse.getFunctionsURL());
		setNamespacedAttribute(
			httpServletRequest, "npmResolvedPackageName",
			DDMFormTaglibUtil.getNPMResolvedPackageName());
		setNamespacedAttribute(
			httpServletRequest, "rolesURL",
			ddmFormBuilderSettingsResponse.getRolesURL());
		setNamespacedAttribute(
			httpServletRequest, "serializedDDMFormRules",
			ddmFormBuilderSettingsResponse.getSerializedDDMFormRules());
	}

}