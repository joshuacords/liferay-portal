/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.configuration;

/**
 * @author     Eduardo García
 * @deprecated As of Judson (7.1.x), see {@link JournalServiceConfiguration}
 */
@Deprecated
public class JournalServiceConfigurationKeys {

	public static final String CHAR_BLACKLIST = "char.blacklist";

	public static final String ERROR_TEMPLATE = "error.template";

	public static final String JOURNAL_ARTICLE_CHECK_INTERVAL =
		"journal.article.check.interval";

	public static final String JOURNAL_ARTICLE_COMMENTS_ENABLED =
		"journal.article.comments.enabled";

	public static final String JOURNAL_ARTICLE_CUSTOM_TOKEN_VALUE =
		"journal.article.custom.token.value";

	public static final String JOURNAL_ARTICLE_CUSTOM_TOKENS =
		"journal.article.custom.tokens";

	public static final String JOURNAL_ARTICLE_DATABASE_KEYWORD_SEARCH_CONTENT =
		"journal.article.database.keyword.search.content";

	public static final String JOURNAL_ARTICLE_EXPIRE_ALL_VERSIONS =
		"journal.article.expire.all.versions";

	public static final String JOURNAL_ARTICLE_INDEX_ALL_VERSIONS =
		"journal.articles.index.all.versions";

	public static final String JOURNAL_ARTICLE_STORAGE_TYPE =
		"journal.article.storage.type";

	public static final String JOURNAL_ARTICLE_TOKEN_PAGE_BREAK =
		"journal.article.token.page.break";

	public static final String JOURNAL_ARTICLE_VIEW_PERMISSION_CHECK_ENABLED =
		"journal.article.view.permission.check.enabled";

	public static final String JOURNAL_FOLDER_ICON_CHECK_COUNT =
		"journal.folder.icon.check.count";

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String LAR_CREATION_STRATEGY = "lar.creation.strategy";

	public static final String PUBLISH_TO_LIVE_BY_DEFAULT =
		"publish.to.live.by.default";

	public static final String PUBLISH_VERSION_HISTORY_BY_DEFAULT =
		"publish.version.history.by.default";

	public static final String SYNC_CONTENT_SEARCH_ON_STARTUP =
		"sync.content.search.on.startup";

	public static final String TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID =
		"terms.of.use.journal.article.group.id";

	public static final String TERMS_OF_USE_JOURNAL_ARTICLE_ID =
		"terms.of.use.journal.article.id";

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String TRANSFORMER_LISTENER = "transformer.listener";

}