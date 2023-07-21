/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.instances.initializer;

import com.liferay.portal.instances.exception.InitializationException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Ivica Cardic
 */
@ProviderType
public interface PortalInstanceInitializer {

	public String getKey();

	public void initialize(String webId, String virtualHostname, String mx)
		throws InitializationException;

	public boolean isActive();

}