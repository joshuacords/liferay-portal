/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.web.service.search;

import java.util.Map;

/**
 * @author Amos Fong
 */
public interface Query {

	public void addCriterion(String criterion);

	public void addParameter(String key, String value);

	public int getPage();

	public Map<String, String> getParameters();

	public void setPage(int page);

	public void setSortBy(String sortBy);

	public void setSortOrder(boolean asc);

}