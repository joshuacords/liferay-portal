/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portletdisplaytemplate;

import com.liferay.dynamic.data.mapping.kernel.DDMTemplate;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Leonardo Barros
 */
public class PortletDisplayTemplateManagerUtil {

	public static DDMTemplate getDDMTemplate(
		long groupId, long classNameId, String displayStyle,
		boolean useDefault) {

		return _portletDisplayTemplateManager.getDDMTemplate(
			groupId, classNameId, displayStyle, useDefault);
	}

	public static String getDisplayStyle(String ddmTemplateKey) {
		return _portletDisplayTemplateManager.getDisplayStyle(ddmTemplateKey);
	}

	public static Map<String, TemplateVariableGroup> getTemplateVariableGroups(
		String language) {

		return _portletDisplayTemplateManager.getTemplateVariableGroups(
			language);
	}

	public static String renderDDMTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, DDMTemplate ddmTemplate,
			List<?> entries, Map<String, Object> contextObjects)
		throws Exception {

		return _portletDisplayTemplateManager.renderDDMTemplate(
			httpServletRequest, httpServletResponse, ddmTemplate, entries,
			contextObjects);
	}

	public static String renderDDMTemplate(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, long templateId,
			List<?> entries, Map<String, Object> contextObjects)
		throws Exception {

		return _portletDisplayTemplateManager.renderDDMTemplate(
			httpServletRequest, httpServletResponse, templateId, entries,
			contextObjects);
	}

	private static volatile PortletDisplayTemplateManager
		_portletDisplayTemplateManager =
			ServiceProxyFactory.newServiceTrackedInstance(
				PortletDisplayTemplateManager.class,
				PortletDisplayTemplateManagerUtil.class,
				"_portletDisplayTemplateManager", false);

}