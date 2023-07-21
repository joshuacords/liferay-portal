/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v1_1_8;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Matthew Chan
 */
public class UpgradeJournalArticle extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select id_, content from JournalArticle where content like " +
					"?")) {

			ps1.setString(1, "%type=\"radio\"%");

			ResultSet rs1 = ps1.executeQuery();

			while (rs1.next()) {
				long id = rs1.getLong("id_");

				String content = rs1.getString("content");

				content = _convertRadioDynamicElements(content);

				try (PreparedStatement ps2 =
						AutoBatchPreparedStatementUtil.concurrentAutoBatch(
							connection,
							"update JournalArticle set content = ? where id_ " +
								"= ?")) {

					ps2.setString(1, content);
					ps2.setLong(2, id);

					ps2.executeUpdate();
				}
			}
		}
	}

	private String _convertRadioDynamicElements(String content)
		throws Exception {

		Document contentDocument = SAXReaderUtil.read(content);

		contentDocument = contentDocument.clone();

		XPath xPath = SAXReaderUtil.createXPath(
			"//dynamic-element[@type='radio']");

		List<Node> nodes = xPath.selectNodes(contentDocument);

		for (Node node : nodes) {
			Element element = (Element)node;

			List<Element> dynamicContentElements = element.elements(
				"dynamic-content");

			for (Element dynamicContentElement : dynamicContentElements) {
				String data = String.valueOf(dynamicContentElement.getData());

				data = _removeUnusedChars(data);

				dynamicContentElement.clearContent();

				dynamicContentElement.addCDATA(data);
			}
		}

		return contentDocument.formattedString();
	}

	private String _removeUnusedChars(String data) {
		if ((data != null) && (data.length() > 3)) {
			int start = 0;
			int end = data.length() - 1;

			if ((data.charAt(start) == '[') && (data.charAt(end) == ']') &&
				(data.charAt(start + 1) == '"') &&
				(data.charAt(end - 1) == '"')) {

				data = data.substring(start + 2, end - 1);
			}
		}

		return data;
	}

}