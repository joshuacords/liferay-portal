/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.hits;

import java.util.Collection;
import java.util.stream.Stream;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Wade Cao
 */
@ProviderType
public interface SearchHitsBuilder {

	public SearchHitsBuilder addSearchHit(SearchHit searchHit);

	public SearchHitsBuilder addSearchHits(Collection<SearchHit> searchHits);

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #addSearchHits(Collection)}
	 */
	@Deprecated
	public SearchHitsBuilder addSearchHits(Stream<SearchHit> searchHitStream);

	public SearchHits build();

	public SearchHitsBuilder maxScore(float maxScore);

	public SearchHitsBuilder searchTime(long searchTime);

	public SearchHitsBuilder totalHits(long totalHits);

}