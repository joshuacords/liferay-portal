/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.portlet.shared.search;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.search.web.search.request.SearchSettings;

import java.util.Optional;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author André de Oliveira
 */
@ProviderType
public interface PortletSharedSearchSettings extends SearchSettings {

	public Optional<String> getParameter71(String name);

	public Optional<String> getParameterOptional(String name);

	public String[] getParameterValues(String name);

	public Optional<String[]> getParameterValues71(String name);

	public String getPortletId();

	public Optional<PortletPreferences> getPortletPreferences71();

	public Optional<PortletPreferences> getPortletPreferencesOptional();

	public RenderRequest getRenderRequest();

	public ThemeDisplay getThemeDisplay();

}