/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.test.util;

import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.text.SimpleDateFormat;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class DocumentFixture {

	public void setUp() {
		setUpFastDateFormatFactoryUtil();
		setUpPropsUtil();
	}

	public void tearDown() {
		tearDownFastDateFormatFactoryUtil();
		tearDownPropsUtil();
	}

	protected void setUpFastDateFormatFactoryUtil() {
		_fastDateFormatFactory =
			FastDateFormatFactoryUtil.getFastDateFormatFactory();

		FastDateFormatFactoryUtil fastDateFormatFactoryUtil =
			new FastDateFormatFactoryUtil();

		FastDateFormatFactory fastDateFormatFactory = Mockito.mock(
			FastDateFormatFactory.class);

		Mockito.when(
			fastDateFormatFactory.getSimpleDateFormat("yyyyMMddHHmmss")
		).thenReturn(
			new SimpleDateFormat("yyyyMMddHHmmss")
		);

		fastDateFormatFactoryUtil.setFastDateFormatFactory(
			fastDateFormatFactory);
	}

	protected void setUpPropsUtil() {
		_props = PropsUtil.getProps();

		props = PropsTestUtil.setProps(
			HashMapBuilder.<String, Object>put(
				PropsKeys.INDEX_DATE_FORMAT_PATTERN, "yyyyMMddHHmmss"
			).put(
				PropsKeys.INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_ENABLED,
				"true"
			).put(
				PropsKeys.
					INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_SCORES_THRESHOLD,
				"50"
			).put(
				PropsKeys.INDEX_SEARCH_HIGHLIGHT_FRAGMENT_SIZE, "80"
			).put(
				PropsKeys.INDEX_SEARCH_HIGHLIGHT_REQUIRE_FIELD_MATCH, "true"
			).put(
				PropsKeys.INDEX_SEARCH_HIGHLIGHT_SNIPPET_SIZE, "3"
			).put(
				PropsKeys.INDEX_SEARCH_QUERY_INDEXING_ENABLED, "true"
			).put(
				PropsKeys.INDEX_SEARCH_QUERY_INDEXING_THRESHOLD, "50"
			).put(
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_ENABLED, "true"
			).put(
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_MAX, "yyyyMMddHHmmss"
			).put(
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_SCORES_THRESHOLD, "0"
			).put(
				PropsKeys.INDEX_SEARCH_SCORING_ENABLED, "true"
			).put(
				PropsKeys.INDEX_SORTABLE_TEXT_FIELDS_TRUNCATED_LENGTH, "255"
			).build());
	}

	protected void tearDownFastDateFormatFactoryUtil() {
		FastDateFormatFactoryUtil fastDateFormatFactoryUtil =
			new FastDateFormatFactoryUtil();

		fastDateFormatFactoryUtil.setFastDateFormatFactory(
			_fastDateFormatFactory);

		_fastDateFormatFactory = null;
	}

	protected void tearDownPropsUtil() {
		PropsUtil.setProps(_props);

		_props = null;

		props = null;
	}

	protected Props props;

	private FastDateFormatFactory _fastDateFormatFactory;
	private Props _props;

}