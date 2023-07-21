/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author     Eduardo García
 * @deprecated As of Judson (7.1.x), see {@link JournalServiceConfiguration}
 */
@Deprecated
public class JournalServiceConfigurationValues {

	public static final String[] CHAR_BLACKLIST =
		JournalServiceConfigurationUtil.getArray(
			JournalServiceConfigurationKeys.CHAR_BLACKLIST);

	public static final int JOURNAL_ARTICLE_CHECK_INTERVAL =
		GetterUtil.getInteger(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					JOURNAL_ARTICLE_CHECK_INTERVAL));

	public static final boolean JOURNAL_ARTICLE_COMMENTS_ENABLED =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					JOURNAL_ARTICLE_COMMENTS_ENABLED));

	public static final String[] JOURNAL_ARTICLE_CUSTOM_TOKENS =
		JournalServiceConfigurationUtil.getArray(
			JournalServiceConfigurationKeys.JOURNAL_ARTICLE_CUSTOM_TOKENS);

	public static boolean JOURNAL_ARTICLE_DATABASE_KEYWORD_SEARCH_CONTENT =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					JOURNAL_ARTICLE_DATABASE_KEYWORD_SEARCH_CONTENT));

	public static final boolean JOURNAL_ARTICLE_EXPIRE_ALL_VERSIONS =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					JOURNAL_ARTICLE_EXPIRE_ALL_VERSIONS));

	public static boolean JOURNAL_ARTICLE_INDEX_ALL_VERSIONS =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					JOURNAL_ARTICLE_INDEX_ALL_VERSIONS));

	public static final String JOURNAL_ARTICLE_STORAGE_TYPE =
		JournalServiceConfigurationUtil.get(
			JournalServiceConfigurationKeys.JOURNAL_ARTICLE_STORAGE_TYPE);

	public static final String JOURNAL_ARTICLE_TOKEN_PAGE_BREAK =
		JournalServiceConfigurationUtil.get(
			JournalServiceConfigurationKeys.JOURNAL_ARTICLE_TOKEN_PAGE_BREAK);

	public static final boolean JOURNAL_ARTICLE_VIEW_PERMISSION_CHECK_ENABLED =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					JOURNAL_ARTICLE_VIEW_PERMISSION_CHECK_ENABLED));

	public static final boolean JOURNAL_FOLDER_ICON_CHECK_COUNT =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					JOURNAL_FOLDER_ICON_CHECK_COUNT));

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String LAR_CREATION_STRATEGY =
		JournalServiceConfigurationUtil.get(
			JournalServiceConfigurationKeys.LAR_CREATION_STRATEGY);

	public static final Boolean PUBLISH_TO_LIVE_BY_DEFAULT =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.PUBLISH_TO_LIVE_BY_DEFAULT));

	public static final Boolean PUBLISH_VERSION_HISTORY_BY_DEFAULT =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					PUBLISH_VERSION_HISTORY_BY_DEFAULT));

	public static final boolean SYNC_CONTENT_SEARCH_ON_STARTUP =
		GetterUtil.getBoolean(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					SYNC_CONTENT_SEARCH_ON_STARTUP));

	public static final long TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID =
		GetterUtil.getLong(
			JournalServiceConfigurationUtil.get(
				JournalServiceConfigurationKeys.
					TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID));

	public static final String TERMS_OF_USE_JOURNAL_ARTICLE_ID =
		JournalServiceConfigurationUtil.get(
			JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_ID);

}