/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.util;

import com.liferay.portal.kernel.repository.BaseRepository;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author     Mika Koivisto
 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
 *             ExternalRepositoryFactoryUtil}
 */
@Deprecated
public class RepositoryFactoryUtil {

	public static BaseRepository getInstance(String className)
		throws Exception {

		RepositoryFactory repositoryFactory = _repositoryFactories.get(
			className);

		BaseRepository baseRepository = null;

		if (repositoryFactory != null) {
			baseRepository = repositoryFactory.getInstance();
		}

		if (baseRepository != null) {
			return baseRepository;
		}

		throw new RepositoryException(
			"Repository with class name " + className + " is unavailable");
	}

	public static String[] getRepositoryClassNames() {
		Set<String> classNames = _repositoryFactories.keySet();

		return classNames.toArray(new String[0]);
	}

	public static void registerRepositoryFactory(
		String className, RepositoryFactory repositoryFactory) {

		_repositoryFactories.put(className, repositoryFactory);
	}

	public static void unregisterRepositoryFactory(String className) {
		_repositoryFactories.remove(className);
	}

	private static final ConcurrentMap<String, RepositoryFactory>
		_repositoryFactories =
			new ConcurrentHashMap<String, RepositoryFactory>() {
				{
					ClassLoader classLoader =
						PortalClassLoaderUtil.getClassLoader();

					for (String className : PropsValues.DL_REPOSITORY_IMPL) {
						RepositoryFactory repositoryFactory =
							new RepositoryFactoryImpl(className, classLoader);

						put(className, repositoryFactory);
					}
				}
			};

}