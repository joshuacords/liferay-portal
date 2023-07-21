/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.search.internal.search.spi.model.index.contributor;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luan Maoski
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.polls.model.PollsQuestion",
	service = ModelDocumentContributor.class
)
public class PollsQuestionModelDocumentContributor
	implements ModelDocumentContributor<PollsQuestion> {

	@Override
	public void contribute(Document document, PollsQuestion pollsQuestion) {
		document.addDateSortable(
			Field.CREATE_DATE, pollsQuestion.getCreateDate());
		document.addText(Field.DESCRIPTION, getDescriptionField(pollsQuestion));
		document.addText(Field.TITLE, getTitleField(pollsQuestion));
	}

	protected String getDescriptionField(PollsQuestion pollsQuestion) {
		String[] availableLanguageIds = pollsQuestion.getAvailableLanguageIds();

		StringBundler sb = new StringBundler();

		for (String languageId : availableLanguageIds) {
			sb.append(pollsQuestion.getDescription(languageId));
			sb.append(StringPool.SPACE);
		}

		return sb.toString();
	}

	protected String getTitleField(PollsQuestion pollsQuestion) {
		String[] availableLanguageIds = pollsQuestion.getAvailableLanguageIds();

		StringBundler sb = new StringBundler();

		for (String languageId : availableLanguageIds) {
			sb.append(pollsQuestion.getTitle(languageId));
			sb.append(StringPool.SPACE);
		}

		return sb.toString();
	}

}