/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.taglib;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.Base64ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author     Roberto Díaz
 * @author     Sergio González
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ItemSelectorRepositoryEntryBrowserReturnTypeUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetFirstAvailableExistingFileEntryReturnTypeFirst()
		throws Exception {

		List<ItemSelectorReturnType> itemSelectorReturnTypes =
			new ArrayList<>();

		URLItemSelectorReturnType existingFileEntryReturnType =
			new URLItemSelectorReturnType();

		itemSelectorReturnTypes.add(existingFileEntryReturnType);

		itemSelectorReturnTypes.add(new Base64ItemSelectorReturnType());

		ItemSelectorReturnType itemSelectorReturnType =
			ItemSelectorRepositoryEntryBrowserReturnTypeUtil.
				getFirstAvailableExistingFileEntryReturnType(
					itemSelectorReturnTypes);

		Assert.assertEquals(
			existingFileEntryReturnType, itemSelectorReturnType);
	}

	@Test
	public void testGetFirstAvailableExistingFileEntryReturnTypeSecond()
		throws Exception {

		List<ItemSelectorReturnType> itemSelectorReturnTypes =
			new ArrayList<>();

		URLItemSelectorReturnType existingFileEntryReturnType =
			new URLItemSelectorReturnType();

		itemSelectorReturnTypes.add(new Base64ItemSelectorReturnType());
		itemSelectorReturnTypes.add(existingFileEntryReturnType);

		ItemSelectorReturnType itemSelectorReturnType =
			ItemSelectorRepositoryEntryBrowserReturnTypeUtil.
				getFirstAvailableExistingFileEntryReturnType(
					itemSelectorReturnTypes);

		Assert.assertEquals(
			existingFileEntryReturnType, itemSelectorReturnType);
	}

	@Test
	public void testGetFirstAvailableMethodDoNotModifyOriginalList()
		throws Exception {

		List<ItemSelectorReturnType> itemSelectorReturnTypes =
			new ArrayList<>();

		itemSelectorReturnTypes.add(new Base64ItemSelectorReturnType());
		itemSelectorReturnTypes.add(new FileEntryItemSelectorReturnType());
		itemSelectorReturnTypes.add(new URLItemSelectorReturnType());

		ItemSelectorRepositoryEntryBrowserReturnTypeUtil.
			getFirstAvailableExistingFileEntryReturnType(
				itemSelectorReturnTypes);

		Assert.assertEquals(
			itemSelectorReturnTypes.toString(), 3,
			itemSelectorReturnTypes.size());
	}

}