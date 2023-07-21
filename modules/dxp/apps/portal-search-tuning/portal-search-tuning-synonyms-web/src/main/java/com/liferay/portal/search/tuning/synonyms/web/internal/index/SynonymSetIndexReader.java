/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.synonyms.web.internal.index;

import com.liferay.portal.search.tuning.synonyms.web.internal.index.name.SynonymSetIndexName;

import java.util.List;
import java.util.Optional;

/**
 * @author Adam Brandizzi
 */
public interface SynonymSetIndexReader {

	public Optional<SynonymSet> fetchOptional(
		SynonymSetIndexName synonymSetIndexName, String id);

	public boolean isExists(SynonymSetIndexName synonymSetIndexName);

	public List<SynonymSet> search(SynonymSetIndexName synonymSetIndexName);

}