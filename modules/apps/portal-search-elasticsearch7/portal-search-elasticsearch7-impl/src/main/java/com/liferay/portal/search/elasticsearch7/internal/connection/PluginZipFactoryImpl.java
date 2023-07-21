/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.portal.search.elasticsearch7.internal.util.ResourceUtil;

import java.io.IOException;

/**
 * @author André de Oliveira
 */
public class PluginZipFactoryImpl implements PluginZipFactory {

	@Override
	public PluginZip createPluginZip(String resourceName) {
		try {
			return new PluginZipImpl(
				ResourceUtil.getResourceAsTempFile(getClass(), resourceName));
		}
		catch (IOException ioException) {
			throw new RuntimeException(
				"Unable to write temporary plugin zip file for resource " +
					resourceName,
				ioException);
		}
	}

}