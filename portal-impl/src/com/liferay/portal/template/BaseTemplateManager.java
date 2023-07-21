/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template;

import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateManager;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.TemplateResourceLoader;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public abstract class BaseTemplateManager implements TemplateManager {

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void addContextObjects(
		Map<String, Object> contextObjects,
		Map<String, Object> newContextObjects) {

		for (Map.Entry<String, Object> entry : newContextObjects.entrySet()) {
			String variableName = entry.getKey();

			if (contextObjects.containsKey(variableName)) {
				continue;
			}

			Object object = entry.getValue();

			if (object instanceof Class) {
				addStaticClassSupport(
					contextObjects, variableName, (Class<?>)object);
			}
			else {
				contextObjects.put(variableName, object);
			}
		}
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void addStaticClassSupport(
		Map<String, Object> contextObjects, String variableName,
		Class<?> variableClass) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void addTaglibApplication(
		Map<String, Object> contextObjects, String applicationName,
		ServletContext servletContext) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void addTaglibFactory(
		Map<String, Object> contextObjects, String taglibLiferayHash,
		ServletContext servletContext) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void addTaglibRequest(
		Map<String, Object> contextObjects, String applicationName,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void addTaglibSupport(
		Map<String, Object> contextObjects,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void addTaglibTheme(
		Map<String, Object> contextObjects, String themeName,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
	}

	@Override
	public String[] getRestrictedVariables() {
		return new String[0];
	}

	@Override
	public Template getTemplate(
		TemplateResource templateResource, boolean restricted) {

		return doGetTemplate(
			templateResource, restricted, getHelperUtilities(restricted));
	}

	public void setTemplateContextHelper(
		TemplateContextHelper templateContextHelper) {

		this.templateContextHelper = templateContextHelper;
	}

	public void setTemplateResourceLoader(
		TemplateResourceLoader templateResourceLoader) {

		this.templateResourceLoader = templateResourceLoader;
	}

	protected abstract Template doGetTemplate(
		TemplateResource templateResource, boolean restricted,
		Map<String, Object> helperUtilities);

	protected Map<String, Object> getHelperUtilities(boolean restricted) {
		return templateContextHelper.getHelperUtilities(
			getTemplateControlContextClassLoader(), restricted);
	}

	protected ClassLoader getTemplateControlContextClassLoader() {
		TemplateControlContext templateControlContext =
			templateContextHelper.getTemplateControlContext();

		return templateControlContext.getClassLoader();
	}

	protected TemplateContextHelper templateContextHelper;
	protected TemplateResourceLoader templateResourceLoader;

}