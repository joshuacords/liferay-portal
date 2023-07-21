/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.associated.data.web.internal.configuration;

import java.io.IOException;

import java.util.Optional;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(
	immediate = true, service = AnonymousUserConfigurationRetriever.class
)
public class AnonymousUserConfigurationRetriever {

	public Optional<Configuration> getOptional(long companyId)
		throws InvalidSyntaxException, IOException {

		String filterString = String.format(
			"(&(service.factoryPid=%s)(companyId=%s))", _getFactoryPid(),
			companyId);

		return _getOptional(filterString);
	}

	public Optional<Configuration> getOptional(long companyId, long userId)
		throws InvalidSyntaxException, IOException {

		String filterString = String.format(
			"(&(service.factoryPid=%s)(companyId=%s)(userId=%s))",
			_getFactoryPid(), String.valueOf(companyId),
			String.valueOf(userId));

		return _getOptional(filterString);
	}

	private String _getFactoryPid() {
		return AnonymousUserConfiguration.class.getName();
	}

	private Optional<Configuration> _getOptional(String filterString)
		throws InvalidSyntaxException, IOException {

		Configuration[] configurations = _configurationAdmin.listConfigurations(
			filterString);

		if (configurations == null) {
			return Optional.empty();
		}

		return Optional.of(configurations[0]);
	}

	@Reference
	private ConfigurationAdmin _configurationAdmin;

}