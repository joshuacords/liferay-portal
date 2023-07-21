/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.renderer.internal;

import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateManager;
import com.liferay.portal.template.soy.renderer.SoyRenderer;

import java.io.IOException;
import java.io.Writer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Iván Zaera Avellón
 */
@Component(immediate = true, service = SoyRenderer.class)
public class SoyRendererImpl implements SoyRenderer {

	public void renderSoy(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String templateNamespace,
			Map<String, ?> context)
		throws IOException, TemplateException {

		renderSoy(
			httpServletRequest, httpServletResponse.getWriter(),
			templateNamespace, context);
	}

	public void renderSoy(
			HttpServletRequest httpServletRequest, Writer writer,
			String templateNamespace, Map<String, ?> context)
		throws TemplateException {

		Template template = _getTemplate();

		template.putAll(context);

		template.put(TemplateConstants.NAMESPACE, templateNamespace);

		template.prepare(httpServletRequest);

		template.processTemplate(writer);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void addTemplateManager(TemplateManager templateManager) {
		String templateManagerName = templateManager.getName();

		if (templateManagerName.equals(TemplateConstants.LANG_TYPE_SOY)) {
			_templateManager = templateManager;
		}
	}

	protected void removeTemplateManager(TemplateManager templateManager) {
		String templateManagerName = templateManager.getName();

		if (templateManagerName.equals(TemplateConstants.LANG_TYPE_SOY)) {
			_templateManager = null;
		}
	}

	private Template _getTemplate() throws TemplateException {
		if (_templateManager == null) {
			throw new TemplateException(
				"Unable to find the Soy template manager");
		}

		return _templateManager.getTemplate(null, false);
	}

	private volatile TemplateManager _templateManager;

}