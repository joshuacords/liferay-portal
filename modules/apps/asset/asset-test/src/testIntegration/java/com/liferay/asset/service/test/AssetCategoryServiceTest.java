/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.asset.test.util.AssetTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author José Manuel Navarro
 */
@RunWith(Arquillian.class)
public class AssetCategoryServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testDeleteVocabularyAlsoUpdatesCategoriesTree()
		throws Exception {

		long groupId = _group.getGroupId();

		AssetVocabulary vocabulary1 = AssetTestUtil.addVocabulary(groupId);
		AssetVocabulary vocabulary2 = AssetTestUtil.addVocabulary(groupId);

		AssetCategory category1a = AssetTestUtil.addCategory(
			groupId, vocabulary1.getVocabularyId());

		assertLeftRightCategory(1, category1a);

		AssetCategory category1b = AssetTestUtil.addCategory(
			groupId, vocabulary1.getVocabularyId());

		assertLeftRightCategory(3, category1b);

		AssetCategory category1c = AssetTestUtil.addCategory(
			groupId, vocabulary1.getVocabularyId());

		assertLeftRightCategory(5, category1c);

		AssetCategory category2a = AssetTestUtil.addCategory(
			groupId, vocabulary2.getVocabularyId());

		assertLeftRightCategory(7, category2a);

		AssetCategory category2b = AssetTestUtil.addCategory(
			groupId, vocabulary2.getVocabularyId());

		assertLeftRightCategory(9, category2b);

		AssetCategory category2c = AssetTestUtil.addCategory(
			groupId, vocabulary2.getVocabularyId());

		assertLeftRightCategory(11, category2c);

		AssetVocabularyServiceUtil.deleteVocabulary(
			vocabulary1.getVocabularyId());

		int count = AssetCategoryServiceUtil.getVocabularyCategoriesCount(
			groupId, vocabulary1.getVocabularyId());

		Assert.assertEquals(0, count);

		count = AssetCategoryServiceUtil.getVocabularyCategoriesCount(
			groupId, vocabulary2.getVocabularyId());

		Assert.assertEquals(3, count);

		category2a = AssetCategoryServiceUtil.getCategory(
			category2a.getCategoryId());

		assertLeftRightCategory(1, category2a);

		category2b = AssetCategoryServiceUtil.getCategory(
			category2b.getCategoryId());

		assertLeftRightCategory(3, category2b);

		category2c = AssetCategoryServiceUtil.getCategory(
			category2c.getCategoryId());

		assertLeftRightCategory(5, category2c);
	}

	@Test
	public void testUniqueCategoryIdsWhenAddingCategoriesSimultaneously()
		throws Exception {

		long groupId = _group.getGroupId();

		AssetVocabulary vocabulary = AssetTestUtil.addVocabulary(groupId);

		List<Callable<Void>> callables = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			callables.add(
				() -> {
					AssetTestUtil.addCategory(
						groupId, vocabulary.getVocabularyId());

					return null;
				});
		}

		for (int i = 0; i < 2; i++) {
			callables.add(
				() -> {
					AssetCategory parentCategory = AssetTestUtil.addCategory(
						groupId, vocabulary.getVocabularyId());

					AssetTestUtil.addCategory(
						groupId, vocabulary.getVocabularyId(),
						parentCategory.getCategoryId());

					AssetTestUtil.addCategory(
						groupId, vocabulary.getVocabularyId(),
						parentCategory.getCategoryId());

					return null;
				});
		}

		Collections.shuffle(callables);

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		executorService.invokeAll(callables);

		executorService.shutdown();

		assertUniqueLeftRightCategories(
			9, 18,
			Arrays.asList(
				1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L,
				15L, 16L, 17L, 18L),
			AssetCategoryServiceUtil.getVocabularyCategories(
				vocabulary.getVocabularyId(), -1, -1, null));
	}

	@Test
	public void testUniqueCategoryIdsWhenAddingCategoryToBrokenTree()
		throws Exception {

		long groupId = _group.getGroupId();

		AssetVocabulary vocabulary = AssetTestUtil.addVocabulary(groupId);

		AssetCategory firstCategory = AssetTestUtil.addCategory(
			groupId, vocabulary.getVocabularyId());

		AssetTestUtil.addCategory(groupId, vocabulary.getVocabularyId());

		firstCategory.setLeftCategoryId(3);
		firstCategory.setRightCategoryId(4);

		AssetCategoryLocalServiceUtil.updateAssetCategory(firstCategory);

		AssetTestUtil.addCategory(groupId, vocabulary.getVocabularyId());

		assertUniqueLeftRightCategories(
			3, 6, Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L),
			AssetCategoryServiceUtil.getVocabularyCategories(
				vocabulary.getVocabularyId(), -1, -1, null));
	}

	@Test
	public void testUniqueCategoryIdsWhenAddingCategoryToBrokenTreeWithOverlap()
		throws Exception {

		long groupId = _group.getGroupId();

		AssetVocabulary vocabulary = AssetTestUtil.addVocabulary(groupId);

		AssetTestUtil.addCategory(groupId, vocabulary.getVocabularyId());

		AssetCategory overlappingCategory = AssetTestUtil.addCategory(
			groupId, vocabulary.getVocabularyId());

		overlappingCategory.setLeftCategoryId(2);
		overlappingCategory.setRightCategoryId(3);

		AssetCategoryLocalServiceUtil.updateAssetCategory(overlappingCategory);

		AssetTestUtil.addCategory(groupId, vocabulary.getVocabularyId());

		assertUniqueLeftRightCategories(
			3, 6, Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L),
			AssetCategoryServiceUtil.getVocabularyCategories(
				vocabulary.getVocabularyId(), -1, -1, null));
	}

	protected void assertLeftRightCategory(
			long expectedLeft, AssetCategory category)
		throws Exception {

		Assert.assertEquals(expectedLeft, category.getLeftCategoryId());
		Assert.assertEquals(expectedLeft + 1, category.getRightCategoryId());
	}

	protected void assertUniqueLeftRightCategories(
		long expectedCategoriesCount, long expectedLeftRightCategoryIdsCount,
		List<Long> expectedSortedLeftRightCategoryIds,
		List<AssetCategory> categories) {

		List<Long> actualSortedLeftRightCategoryIds = new ArrayList<>();

		for (AssetCategory category : categories) {
			actualSortedLeftRightCategoryIds.add(category.getLeftCategoryId());
			actualSortedLeftRightCategoryIds.add(category.getRightCategoryId());
		}

		Collections.sort(actualSortedLeftRightCategoryIds);

		Assert.assertEquals(expectedCategoriesCount, (int)categories.size());
		Assert.assertEquals(
			expectedLeftRightCategoryIdsCount,
			(long)actualSortedLeftRightCategoryIds.size());
		Assert.assertEquals(
			expectedSortedLeftRightCategoryIds.toString(),
			actualSortedLeftRightCategoryIds.toString());
	}

	@DeleteAfterTestRun
	private Group _group;

}