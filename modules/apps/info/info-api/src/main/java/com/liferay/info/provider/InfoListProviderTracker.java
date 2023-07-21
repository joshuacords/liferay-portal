/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.provider;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author     Eudaldo Alonso
 * @deprecated As of Mueller (7.2.x), moved to {@link
 *             com.liferay.info.list.provider.InfoListProviderTracker}
 */
@Deprecated
@ProviderType
public interface InfoListProviderTracker {

	public InfoListProvider getInfoListProvider(String className);

	public List<InfoListProvider> getInfoListProviders();

	public List<InfoListProvider> getInfoListProviders(Class<?> itemClass);

}