/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.shindig.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.util.Validator;

import org.apache.shindig.config.ContainerConfigException;
import org.apache.shindig.config.JsonContainerConfig;
import org.apache.shindig.expressions.Expressions;

/**
 * @author Michael Young
 */
@Singleton
public class LiferayJsonContainerConfig extends JsonContainerConfig {

	@Inject
	public LiferayJsonContainerConfig(
			@Named("shindig.containers.default") String containers,
			Expressions expressions)
		throws ContainerConfigException {

		super(containers, null, null, expressions);
	}

	@Override
	public String getString(String container, String property) {
		String value = super.getString(container, property);

		if (Validator.isNotNull(value)) {
			value = ShindigUtil.transformURL(value);
		}

		return value;
	}

}