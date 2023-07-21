/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portletdisplaytemplate;

import com.liferay.dynamic.data.mapping.kernel.DDMTemplate;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Leonardo Barros
 */
@ProviderType
public interface PortletDisplayTemplateManager {

	public static final String DISPLAY_STYLE_PREFIX = "ddmTemplate_";

	public static final String ENTRIES = "entries";

	public DDMTemplate getDDMTemplate(
		long groupId, long classNameId, String displayStyle,
		boolean useDefault);

	public String getDisplayStyle(String ddmTemplateKey);

	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
		String language);

	public String renderDDMTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, DDMTemplate ddmTemplate,
			List<?> entries, Map<String, Object> contextObjects)
		throws Exception;

	public String renderDDMTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, long templateId,
			List<?> entries, Map<String, Object> contextObjects)
		throws Exception;

}