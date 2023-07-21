/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.connection;

import java.io.IOException;

/**
 * @author André de Oliveira
 */
public interface PluginZipFactory {

	public PluginZip createPluginZip(String resourceName) throws IOException;

}