/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.model.listener;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.util.JournalContent;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.servlet.filters.cache.CacheUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Jon Steer
 * @author Raymond Augé
 */
@Component(immediate = true, service = ModelListener.class)
public class JournalArticleModelListener
	extends BaseModelListener<JournalArticle> {

	@Override
	public void onAfterRemove(JournalArticle journalArticle) {
		clearCache(journalArticle);
	}

	@Override
	public void onAfterUpdate(JournalArticle journalArticle) {
		clearCache(journalArticle);
	}

	protected void clearCache(JournalArticle journalArticle) {
		if (journalArticle == null) {
			return;
		}

		// Journal content

		_journalContent.clearCache(
			journalArticle.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getDDMTemplateKey());

		// Layout cache

		CacheUtil.clearCache(journalArticle.getCompanyId());
	}

	@Reference(unbind = "-")
	protected void setJournalContent(JournalContent journalContent) {
		_journalContent = journalContent;
	}

	private JournalContent _journalContent;

}