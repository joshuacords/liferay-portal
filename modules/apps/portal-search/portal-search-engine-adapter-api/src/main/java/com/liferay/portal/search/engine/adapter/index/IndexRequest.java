/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.index;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface IndexRequest<T extends IndexResponse> {

	public T accept(IndexRequestExecutor indexRequestExecutor);

	public String[] getIndexNames();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement. This method
	 *             should not be in the parent interface.  Only certain
	 *             IndexRequests work with mappings.
	 */
	@Deprecated
	public String getMappingName();

}