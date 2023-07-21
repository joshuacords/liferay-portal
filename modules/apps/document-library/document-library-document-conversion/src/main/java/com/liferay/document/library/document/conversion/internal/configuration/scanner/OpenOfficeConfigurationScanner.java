/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.document.conversion.internal.configuration.scanner;

import com.liferay.document.library.document.conversion.internal.configuration.OpenOfficeConfiguration;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Matthew Tambara
 */
@Component(
	configurationPid = "com.liferay.document.library.document.conversion.internal.configuration.OpenOfficeConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = {}
)
public class OpenOfficeConfigurationScanner {

	@Activate
	protected void activate(Map<String, Object> properties) {
		OpenOfficeConfiguration openOfficeConfiguration =
			ConfigurableUtil.createConfigurable(
				OpenOfficeConfiguration.class, properties);

		String host = openOfficeConfiguration.serverHost();

		if (_log.isWarnEnabled() && Validator.isNotNull(host) &&
			!host.equals(_LOCALHOST_IP) && !host.startsWith(_LOCALHOST)) {

			StringBundler sb = new StringBundler(3);

			sb.append("Conversion using a remote OpenOffice instance is not ");
			sb.append("fully supported. Please use a local instance to ");
			sb.append("prevent any limitations and problems.");

			_log.warn(sb.toString());
		}
	}

	private static final String _LOCALHOST = "localhost";

	private static final String _LOCALHOST_IP = "127.0.0.1";

	private static final Log _log = LogFactoryUtil.getLog(
		OpenOfficeConfigurationScanner.class);

}