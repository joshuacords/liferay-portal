/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.highlight;

import com.liferay.portal.search.query.Query;

import java.util.Collection;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
@ProviderType
public interface HighlightBuilder {

	public HighlightBuilder addFieldConfig(FieldConfig fieldConfig);

	public Highlight build();

	public HighlightBuilder fieldConfigs(Collection<FieldConfig> fieldConfigs);

	public HighlightBuilder forceSource(Boolean forceSource);

	public HighlightBuilder fragmenter(String fragmenter);

	public HighlightBuilder fragmentSize(Integer fragmentSize);

	public HighlightBuilder highlighterType(String highlighterType);

	public HighlightBuilder highlightFilter(Boolean highlightFilter);

	public HighlightBuilder highlightQuery(Query highlightQuery);

	public HighlightBuilder numOfFragments(Integer numOfFragments);

	public HighlightBuilder postTags(String... postTags);

	public HighlightBuilder preTags(String... preTags);

	public HighlightBuilder requireFieldMatch(Boolean requireFieldMatch);

}