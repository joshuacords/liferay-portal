/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.web.internal.upgrade.v1_0_0;

import com.liferay.adaptive.media.image.html.constants.AMImageHTMLConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alejandro Tardín
 */
public class UpgradeJournalArticleDataFileEntryId extends UpgradeProcess {

	public UpgradeJournalArticleDataFileEntryId(
		JournalArticleLocalService journalArticleLocalService) {

		_journalArticleLocalService = journalArticleLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try {
			ActionableDynamicQuery actionableDynamicQuery =
				_journalArticleLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setPerformActionMethod(
				(JournalArticle journalArticle) -> _upgradeJournalArticle(
					journalArticle));

			actionableDynamicQuery.performActions();
		}
		catch (PortalException portalException) {
			throw new UpgradeException(portalException);
		}
	}

	private String _upgradeContent(String content) throws DocumentException {
		Document document = SAXReaderUtil.read(content);

		XPath xPath = SAXReaderUtil.createXPath(
			"//dynamic-element[@type='text_area']");

		List<Node> ddmJournalArticleNodes = xPath.selectNodes(document);

		for (Node ddmJournalArticleNode : ddmJournalArticleNodes) {
			Element ddmJournalArticleElement = (Element)ddmJournalArticleNode;

			List<Element> dynamicContentElements =
				ddmJournalArticleElement.elements("dynamic-content");

			for (Element dynamicContentElement : dynamicContentElements) {
				String stringValue = dynamicContentElement.getStringValue();

				Matcher matcher = _dataFileEntryIdPattern.matcher(stringValue);

				String upgradedStringValue = matcher.replaceAll(
					AMImageHTMLConstants.ATTRIBUTE_NAME_FILE_ENTRY_ID +
						StringPool.EQUAL);

				if (!upgradedStringValue.equals(stringValue)) {
					dynamicContentElement.clearContent();

					dynamicContentElement.addCDATA(upgradedStringValue);
				}
			}
		}

		return document.asXML();
	}

	private void _upgradeJournalArticle(JournalArticle journalArticle)
		throws UpgradeException {

		try {
			String content = journalArticle.getContent();

			String upgradedContent = _upgradeContent(content);

			if (!content.equals(upgradedContent)) {
				journalArticle.setContent(upgradedContent);

				_journalArticleLocalService.updateJournalArticle(
					journalArticle);
			}
		}
		catch (DocumentException documentException) {
			throw new UpgradeException(documentException);
		}
	}

	private static final Pattern _dataFileEntryIdPattern = Pattern.compile(
		"data-fileEntryId=", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	private final JournalArticleLocalService _journalArticleLocalService;

}