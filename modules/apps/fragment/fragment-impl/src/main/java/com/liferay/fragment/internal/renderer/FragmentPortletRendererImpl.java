/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.renderer;

import com.liferay.fragment.exception.FragmentEntryContentException;
import com.liferay.fragment.internal.constants.PortletFragmentEntryProcessorWebKeys;
import com.liferay.fragment.internal.display.context.PortletFragmentEntryProcessorDisplayContext;
import com.liferay.fragment.renderer.FragmentPortletRenderer;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.taglib.servlet.PipingServletResponse;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = FragmentPortletRenderer.class)
public class FragmentPortletRendererImpl implements FragmentPortletRenderer {

	@Override
	public String renderPortlet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String portletName,
			String instanceId, String defaultPreferences)
		throws PortalException {

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);

		PortletFragmentEntryProcessorDisplayContext
			portletFragmentEntryProcessorDisplayContext =
				new PortletFragmentEntryProcessorDisplayContext(
					defaultPreferences, instanceId, portletName);

		httpServletRequest.setAttribute(
			PortletFragmentEntryProcessorWebKeys.
				PORTLET_FRAGMENT_ENTRY_PROCESSOR_DISPLAY_CONTEXT,
			portletFragmentEntryProcessorDisplayContext);

		try {
			_jspRenderer.renderJSP(
				_servletContext, httpServletRequest, pipingServletResponse,
				"/portlet.jsp");
		}
		catch (IOException ioException) {
			throw new FragmentEntryContentException(ioException);
		}

		return unsyncStringWriter.toString();
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.fragment.impl)",
		unbind = "-"
	)
	private ServletContext _servletContext;

}