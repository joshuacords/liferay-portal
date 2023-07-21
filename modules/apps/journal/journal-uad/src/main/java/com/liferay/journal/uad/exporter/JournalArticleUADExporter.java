/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.uad.exporter;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.user.associated.data.exporter.UADExporter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Balázs Sáfrány-Kovalik
 */
@Component(immediate = true, service = UADExporter.class)
public class JournalArticleUADExporter extends BaseJournalArticleUADExporter {

	@Override
	protected String toXmlString(JournalArticle journalArticle) {
		JournalArticle escapedJournalArticle =
			(JournalArticle)journalArticle.clone();

		escapedJournalArticle.setContent(
			StringUtil.replace(
				escapedJournalArticle.getContent(), "]]><",
				"]]]]><![CDATA[><"));

		return super.toXmlString(escapedJournalArticle);
	}

}