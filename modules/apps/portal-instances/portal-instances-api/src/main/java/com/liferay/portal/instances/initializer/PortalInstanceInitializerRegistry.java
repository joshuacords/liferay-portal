/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.instances.initializer;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Ivica Cardic
 */
@ProviderType
public interface PortalInstanceInitializerRegistry {

	public PortalInstanceInitializer getPortalInstanceInitializer(String key);

	public List<PortalInstanceInitializer> getPortalInstanceInitializers();

	public List<PortalInstanceInitializer> getPortalInstanceInitializers(
		boolean activeOnly);

}