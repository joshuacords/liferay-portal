/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.ml.embedding.text.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.search.ml.embedding.text.TextEmbeddingDocumentContributor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
public class TextEmbeddingContentHelperTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testAppendDelimiterBehavior() {
		TextEmbeddingContentHelper<TestBaseModel> textEmbeddingContentHelper =
			_createTextEmbeddingContentHelper();

		textEmbeddingContentHelper.appendToLocale("en_US", "alpha");

		Map<String, String> localizedContentMap =
			textEmbeddingContentHelper.getLocalizedContentMap();

		Assert.assertEquals("alpha", localizedContentMap.get("en_US"));

		textEmbeddingContentHelper.appendToLocale("en_US", "beta");

		localizedContentMap =
			textEmbeddingContentHelper.getLocalizedContentMap();

		Assert.assertEquals(
			StringBundler.concat("alpha", _DELIMITER, "beta"),
			localizedContentMap.get("en_US"));
	}

	@Test
	public void testAppendMethodsPopulateCorrectStringBundlers() {
		TextEmbeddingContentHelper<TestBaseModel> textEmbeddingContentHelper =
			_createTextEmbeddingContentHelper();

		textEmbeddingContentHelper.appendToAll("all");

		textEmbeddingContentHelper.appendToNonlocalized("nonlocalized");

		textEmbeddingContentHelper.appendToLocale("en_US", "localized_en_US");

		textEmbeddingContentHelper.appendToLocale("pt_BR", "localized_pt_BR");

		textEmbeddingContentHelper.appendToLocalizedAndNonlocalized(
			"en_US", "both_en_US_and_nonlocalized");

		textEmbeddingContentHelper.appendToLocalizedAndNonlocalized(
			"pt_BR", "both_pt_BR_and_nonlocalized");

		Map<String, String> localizedContentMap =
			textEmbeddingContentHelper.getLocalizedContentMap();

		Assert.assertEquals(
			StringBundler.concat(
				"all", _DELIMITER, "localized_en_US", _DELIMITER,
				"both_en_US_and_nonlocalized"),
			localizedContentMap.get("en_US"));
		Assert.assertEquals(
			StringBundler.concat(
				"all", _DELIMITER, "localized_pt_BR", _DELIMITER,
				"both_pt_BR_and_nonlocalized"),
			localizedContentMap.get("pt_BR"));

		Assert.assertEquals(
			StringBundler.concat(
				"all", _DELIMITER, "nonlocalized", _DELIMITER,
				"both_en_US_and_nonlocalized", _DELIMITER,
				"both_pt_BR_and_nonlocalized"),
			textEmbeddingContentHelper.getNonlocalizedContent());
	}

	private TextEmbeddingContentHelper<TestBaseModel>
		_createTextEmbeddingContentHelper() {

		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor =
			Mockito.mock(TextEmbeddingDocumentContributor.class);

		Mockito.doReturn(
			List.of("en_US", "pt_BR")
		).when(
			textEmbeddingDocumentContributor
		).getLanguageIds(
			Mockito.any()
		);

		return new TextEmbeddingContentHelper<>(
			1L, _DELIMITER, true, Mockito.mock(TestBaseModel.class), true, 10,
			textEmbeddingDocumentContributor);
	}

	private static final String _DELIMITER = StringPool.COMMA_AND_SPACE;

	private interface TestBaseModel extends BaseModel<TestBaseModel> {
	}

}