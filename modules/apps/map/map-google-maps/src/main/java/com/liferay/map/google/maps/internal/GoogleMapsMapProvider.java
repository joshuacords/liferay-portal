/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.map.google.maps.internal;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.map.BaseJSPMapProvider;
import com.liferay.map.MapProvider;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = MapProvider.class)
public class GoogleMapsMapProvider extends BaseJSPMapProvider {

	@Override
	public String getConfigurationJspPath() {
		return "/configuration.jsp";
	}

	@Override
	public String getHelpMessage() {
		return null;
	}

	@Override
	public String getJspPath() {
		return "/view.jsp";
	}

	@Override
	public String getKey() {
		return "GoogleMaps";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "google-maps");
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.map.google.maps)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected void prepareRequest(HttpServletRequest httpServletRequest) {
		String resolvedModuleName = _npmResolver.resolveModuleName(
			"map-google-maps/js/MapGoogleMaps.es");

		httpServletRequest.setAttribute(
			"liferay-map:map:bootstrapRequire",
			resolvedModuleName + " as MapGoogleMaps");
	}

	@Reference
	private NPMResolver _npmResolver;

}