/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.agent;

import java.io.File;
import java.io.Serializable;

import java.nio.file.Path;

/**
 * @author Shuyang Zhou
 */
public class NettyFabricAgentConfig implements Serializable {

	public NettyFabricAgentConfig(File repositoryFolder) {
		if (repositoryFolder == null) {
			throw new NullPointerException("Repository folder is null");
		}

		_repositoryFolder = repositoryFolder;
	}

	public Path getRepositoryPath() {
		return _repositoryFolder.toPath();
	}

	private static final long serialVersionUID = 1L;

	private final File _repositoryFolder;

}