/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.asset.auto.tagger.tensorflow.internal.servlet.taglib;

import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.configuration.TensorFlowImageAssetAutoTagProviderCompanyConfiguration;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.servlet.taglib.BaseJSPDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(service = DynamicInclude.class)
public class EditConfigurationJSPDynamicInclude extends BaseJSPDynamicInclude {

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		try {
			TensorFlowImageAssetAutoTagProviderCompanyConfiguration
				tensorFlowImageAssetAutoTagProviderCompanyConfiguration =
					_configurationProvider.getCompanyConfiguration(
						TensorFlowImageAssetAutoTagProviderCompanyConfiguration.
							class,
						_portal.getCompanyId(httpServletRequest));

			httpServletRequest.setAttribute(
				TensorFlowImageAssetAutoTagProviderCompanyConfiguration.class.
					getName(),
				tensorFlowImageAssetAutoTagProviderCompanyConfiguration);
		}
		catch (ConfigurationException configurationException) {
			_log.error(configurationException, configurationException);
		}

		super.include(httpServletRequest, httpServletResponse, key);
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			StringBundler.concat(
				"com.liferay.configuration.admin.web#/edit_configuration.jsp#",
				"com.liferay.document.library.asset.auto.tagger.tensorflow.",
				"internal.configuration.TensorFlowImageAssetAutoTagProvider",
				"CompanyConfiguration#pre"));
	}

	@Override
	protected String getJspPath() {
		return "/dynamic_include/com.liferay.configuration.admin.web" +
			"/edit_configuration.jsp";
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.document.library.asset.auto.tagger.tensorflow)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditConfigurationJSPDynamicInclude.class);

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Portal _portal;

}