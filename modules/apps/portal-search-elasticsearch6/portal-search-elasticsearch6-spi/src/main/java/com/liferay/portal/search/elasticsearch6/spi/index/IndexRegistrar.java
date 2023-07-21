/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.spi.index;

import com.liferay.portal.search.elasticsearch6.spi.index.helper.IndexRegistrarHelper;

/**
 * @author André de Oliveira
 */
public interface IndexRegistrar {

	public void register(IndexRegistrarHelper indexRegistrarHelper);

}