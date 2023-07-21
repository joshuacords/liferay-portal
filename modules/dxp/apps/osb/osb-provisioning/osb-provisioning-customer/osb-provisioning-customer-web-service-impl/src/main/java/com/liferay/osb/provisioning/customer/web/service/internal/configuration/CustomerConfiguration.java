/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.customer.web.service.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Amos Fong
 */
@ExtendedObjectClassDefinition(generateUI = false)
@Meta.OCD(
	id = "com.liferay.osb.provisioning.customer.web.service.internal.configuration.CustomerConfiguration"
)
public interface CustomerConfiguration {

	@Meta.AD(deflt = "localhost", required = false)
	public String host();

	@Meta.AD(required = false)
	public String apiToken();

	@Meta.AD(deflt = "8080", required = false)
	public int port();

	@Meta.AD(deflt = "http", required = false)
	public String scheme();

}