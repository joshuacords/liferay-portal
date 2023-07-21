/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.web.internal.settings.definition;

import com.liferay.configuration.admin.definition.ConfigurationDDMFormDeclaration;
import com.liferay.users.admin.configuration.definition.UserFileUploadsConfigurationForm;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = "configurationPid=com.liferay.users.admin.configuration.UserFileUploadsConfiguration",
	service = ConfigurationDDMFormDeclaration.class
)
public class UserFileUploadsConfigurationDDMFormDeclaration
	implements ConfigurationDDMFormDeclaration {

	@Override
	public Class<?> getDDMFormClass() {
		return UserFileUploadsConfigurationForm.class;
	}

}