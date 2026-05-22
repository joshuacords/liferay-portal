/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.search.spi.model.result.contributor;

import com.liferay.object.constants.ObjectEntrySearchConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Joshua Cords
 */
public class ObjectEntryModelSummaryContributorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetSummaryUsesLocalizedObjectEntryContentSnippet() {
		String highlightedSnippet =
			_HIGHLIGHT_OPEN + "keyword" + _HIGHLIGHT_CLOSE;

		Document document = new DocumentImpl();

		document.addText(Field.ENTRY_CLASS_PK, "1234");
		document.addText(
			ObjectEntrySearchConstants.DEFAULT_LANGUAGE_ID,
			LocaleUtil.toLanguageId(LocaleUtil.US));
		document.addText(
			StringBundler.concat(
				Field.SNIPPET, StringPool.UNDERLINE,
				Field.getLocalizedName(
					LocaleUtil.US,
					ObjectEntrySearchConstants.OBJECT_ENTRY_CONTENT)),
			highlightedSnippet);

		ObjectEntryModelSummaryContributor contributor =
			new ObjectEntryModelSummaryContributor();

		Summary summary = contributor.getSummary(
			document, LocaleUtil.US, highlightedSnippet);

		Assert.assertEquals(highlightedSnippet, summary.getContent());
	}

	private static final String _HIGHLIGHT_CLOSE = "</liferay-hl>";

	private static final String _HIGHLIGHT_OPEN = "<liferay-hl>";

}