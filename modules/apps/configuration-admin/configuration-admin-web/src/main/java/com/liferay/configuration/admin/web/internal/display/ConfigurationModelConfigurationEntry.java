/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.web.internal.display;

import com.liferay.configuration.admin.web.internal.model.ConfigurationModel;
import com.liferay.configuration.admin.web.internal.util.ResourceBundleLoaderProvider;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Jorge Ferrer
 */
public class ConfigurationModelConfigurationEntry
	implements ConfigurationEntry {

	public ConfigurationModelConfigurationEntry(
		ConfigurationModel configurationModel, Locale locale,
		ResourceBundleLoaderProvider resourceBundleLoaderProvider) {

		_configurationModel = configurationModel;
		_locale = locale;
		_resourceBundleLoaderProvider = resourceBundleLoaderProvider;
	}

	@Override
	public boolean equals(Object obj) {
		ConfigurationEntry configurationEntry = (ConfigurationEntry)obj;

		return Objects.equals(getKey(), configurationEntry.getKey());
	}

	@Override
	public String getCategory() {
		return _configurationModel.getCategory();
	}

	public ConfigurationModel getConfigurationModel() {
		return _configurationModel;
	}

	@Override
	public String getEditURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"factoryPid", _configurationModel.getFactoryPid());

		if (_configurationModel.isFactory() &&
			!_configurationModel.isCompanyFactory()) {

			portletURL.setParameter(
				"mvcRenderCommandName", "/view_factory_instances");
		}
		else {
			portletURL.setParameter(
				"mvcRenderCommandName", "/edit_configuration");
			portletURL.setParameter("pid", _configurationModel.getID());
		}

		return portletURL.toString();
	}

	@Override
	public String getKey() {
		return _configurationModel.getID();
	}

	@Override
	public String getName() {
		ResourceBundleLoader curResourceBundleLoader =
			_resourceBundleLoaderProvider.getResourceBundleLoader(
				_configurationModel.getBundleSymbolicName());

		ResourceBundle curComponentResourceBundle =
			curResourceBundleLoader.loadResourceBundle(_locale);

		String curConfigurationModelName;

		if (curComponentResourceBundle != null) {
			curConfigurationModelName = LanguageUtil.get(
				curComponentResourceBundle, _configurationModel.getName());
		}
		else {
			curConfigurationModelName = _configurationModel.getName();
		}

		return curConfigurationModelName;
	}

	@Override
	public String getScope() {
		return _configurationModel.getScope();
	}

	@Override
	public int hashCode() {
		return Objects.hash(_configurationModel);
	}

	private final ConfigurationModel _configurationModel;
	private final Locale _locale;
	private final ResourceBundleLoaderProvider _resourceBundleLoaderProvider;

}