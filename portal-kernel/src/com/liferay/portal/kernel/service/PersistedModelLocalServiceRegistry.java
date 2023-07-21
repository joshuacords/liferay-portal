/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public interface PersistedModelLocalServiceRegistry {

	public PersistedModelLocalService getPersistedModelLocalService(
		String className);

	public List<PersistedModelLocalService> getPersistedModelLocalServices();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public boolean isPermissionedModelLocalService(String className);

	public void register(
		String className,
		PersistedModelLocalService persistedModelLocalService);

	public void unregister(String className);

}