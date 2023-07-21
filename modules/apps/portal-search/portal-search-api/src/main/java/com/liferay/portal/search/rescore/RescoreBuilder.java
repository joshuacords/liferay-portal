/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.rescore;

import com.liferay.portal.search.query.Query;

/**
 * @author Bryan Engler
 */
public interface RescoreBuilder {

	public Rescore build();

	public RescoreBuilder query(Query query);

	public RescoreBuilder windowSize(Integer windowSize);

}