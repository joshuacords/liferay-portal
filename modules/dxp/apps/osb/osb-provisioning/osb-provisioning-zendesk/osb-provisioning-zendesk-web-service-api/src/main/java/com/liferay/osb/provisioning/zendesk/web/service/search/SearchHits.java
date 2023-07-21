/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.web.service.search;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface SearchHits<T> {

	public int getCount();

	public int getNextPage();

	public List<T> getResults();

	public boolean hasNextPage();

	public void setCount(int count);

	public void setNextPage(int nextPage);

	public void setResults(List<T> results);

}