/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.internal.spi.model.query.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.configuration.DefaultKeywordQueryConfiguration;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.configuration.DefaultKeywordQueryConfiguration",
	immediate = true,
	service = {
		DefaultKeywordQueryContributor.class, KeywordQueryContributor.class
	}
)
public class DefaultKeywordQueryContributor implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		String entryClassName = keywordQueryContributorHelper.getClassName();

		if (SetUtil.isNotEmpty(_disabledEntryClassNames) &&
			_disabledEntryClassNames.contains(entryClassName)) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					DefaultKeywordQueryContributor.class.getName() +
						" is disabled for " + entryClassName);
			}

			return;
		}

		if (Validator.isBlank(keywords)) {
			SearchContext searchContext =
				keywordQueryContributorHelper.getSearchContext();

			queryHelper.addSearchTerm(
				booleanQuery, searchContext, Field.DESCRIPTION, false);
			queryHelper.addSearchTerm(
				booleanQuery, searchContext, Field.TITLE, false);
			queryHelper.addSearchTerm(
				booleanQuery, searchContext, Field.USER_NAME, false);
		}
	}

	public int getMaximumKeywordSize() {
		return _maximumKeywordSize;
	}

	public String trimKeywords(String keywords) {
		if (keywords.length() < _maximumKeywordSize) {
			return keywords;
		}

		List<String> keywordList = ListUtil.fromString(
			keywords, StringPool.SPACE);

		for (int i = 0; i < keywordList.size(); i++) {
			String keyword = keywordList.get(i);

			if (keyword.length() > _maximumKeywordSize) {
				keywordList.set(
					i,
					StringUtil.shorten(
						keyword, _maximumKeywordSize, StringPool.BLANK));
			}
		}

		return ListUtil.toString(
			keywordList, StringPool.NULL, StringPool.SPACE);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		DefaultKeywordQueryConfiguration defaultKeywordQueryConfiguration =
			ConfigurableUtil.createConfigurable(
				DefaultKeywordQueryConfiguration.class, properties);

		_maximumKeywordSize =
			defaultKeywordQueryConfiguration.maximumKeywordSize();

		if (_maximumKeywordSize <= 0) {
			_maximumKeywordSize = 100;
		}

		_disabledEntryClassNames = SetUtil.fromArray(
			defaultKeywordQueryConfiguration.disabledEntryClassNames());
	}

	@Reference
	protected QueryHelper queryHelper;

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultKeywordQueryContributor.class);

	private volatile Set<String> _disabledEntryClassNames;
	private volatile int _maximumKeywordSize;

}