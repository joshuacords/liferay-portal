/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.renderer.internal;

import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.template.soy.renderer.ComponentDescriptor;
import com.liferay.portal.template.soy.renderer.SoyComponentRenderer;
import com.liferay.portal.template.soy.renderer.SoyRenderer;

import java.io.IOException;
import java.io.Writer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera Avellón
 */
@Component(immediate = true, service = SoyComponentRenderer.class)
public class SoyComponentRendererImpl implements SoyComponentRenderer {

	public void renderSoyComponent(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			ComponentDescriptor componentDescriptor, Map<String, ?> context)
		throws IOException, TemplateException {

		renderSoyComponent(
			httpServletRequest, httpServletResponse.getWriter(),
			componentDescriptor, context);
	}

	public void renderSoyComponent(
			HttpServletRequest httpServletRequest, Writer writer,
			ComponentDescriptor componentDescriptor, Map<String, ?> context)
		throws IOException, TemplateException {

		SoyComponentRendererHelper soyComponentRendererHelper =
			new SoyComponentRendererHelper(
				httpServletRequest, componentDescriptor, context, _portal,
				_soyRenderer);

		soyComponentRendererHelper.renderSoyComponent(writer);
	}

	@Reference
	private Portal _portal;

	@Reference
	private SoyRenderer _soyRenderer;

}