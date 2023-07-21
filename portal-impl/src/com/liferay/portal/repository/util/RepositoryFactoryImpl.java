/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.util;

import com.liferay.portal.kernel.repository.BaseRepository;

/**
 * @author     Mika Koivisto
 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
 *             ExternalRepositoryFactoryImpl}
 */
@Deprecated
public class RepositoryFactoryImpl implements RepositoryFactory {

	public RepositoryFactoryImpl(String className) {
		_externalRepositoryFactory = new ExternalRepositoryFactoryImpl(
			className);
	}

	public RepositoryFactoryImpl(String className, ClassLoader classLoader) {
		_externalRepositoryFactory = new ExternalRepositoryFactoryImpl(
			className, classLoader);
	}

	@Override
	public BaseRepository getInstance() throws Exception {
		return _externalRepositoryFactory.getInstance();
	}

	private final ExternalRepositoryFactory _externalRepositoryFactory;

}