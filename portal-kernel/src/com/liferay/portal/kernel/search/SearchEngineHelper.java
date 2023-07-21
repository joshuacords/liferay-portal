/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import java.util.Collection;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public interface SearchEngineHelper {

	public static final String GENERIC_ENGINE_ID = "GENERIC_ENGINE";

	public static final String SYSTEM_ENGINE_ID = "SYSTEM_ENGINE";

	public void flushQueuedSearchEngine();

	public void flushQueuedSearchEngine(String searchEngineId);

	public Collection<Long> getCompanyIds();

	public String getDefaultSearchEngineId();

	public String[] getEntryClassNames();

	public SearchEngine getSearchEngine(String searchEngineId);

	public String getSearchEngineId(Collection<Document> documents);

	public String getSearchEngineId(Document document);

	public Set<String> getSearchEngineIds();

	public Collection<SearchEngine> getSearchEngines();

	public SearchEngine getSearchEngineSilent(String searchEngineId);

	public String getSearchReaderDestinationName(String searchEngineId);

	public String getSearchWriterDestinationName(String searchEngineId);

	public void initialize(long companyId);

	public void removeCompany(long companyId);

	public SearchEngine removeSearchEngine(String searchEngineId);

	public void setDefaultSearchEngineId(String defaultSearchEngineId);

	public void setQueueCapacity(int queueCapacity);

	public void setSearchEngine(
		String searchEngineId, SearchEngine searchEngine);

}