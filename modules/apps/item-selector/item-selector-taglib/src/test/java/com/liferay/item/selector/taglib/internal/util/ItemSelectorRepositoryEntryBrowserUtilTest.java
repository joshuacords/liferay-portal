/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.taglib.internal.util;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.taglib.ItemSelectorRepositoryEntryBrowserReturnTypeUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Roberto Díaz
 */
@PrepareForTest(ItemSelectorRepositoryEntryBrowserReturnTypeUtil.class)
@RunWith(PowerMockRunner.class)
public class ItemSelectorRepositoryEntryBrowserUtilTest extends PowerMockito {

	@Test
	public void testGetItemSelectorReturnTypeClassNameWithoutResolver()
		throws Exception {

		String itemSelectorReturnTypeClassName =
			ItemSelectorRepositoryEntryBrowserUtil.
				getItemSelectorReturnTypeClassName(
					null, new TestItemSelectorReturnType());

		Class<TestItemSelectorReturnType> testItemSelectorReturnTypeClass =
			TestItemSelectorReturnType.class;

		Assert.assertEquals(
			testItemSelectorReturnTypeClass.getName(),
			itemSelectorReturnTypeClassName);
	}

	@Test
	public void testGetItemSelectorReturnTypeClassNameWithResolver()
		throws Exception {

		String itemSelectorReturnTypeClassName =
			ItemSelectorRepositoryEntryBrowserUtil.
				getItemSelectorReturnTypeClassName(
					new TestFileEntryItemSelectorReturnTypeResolver(),
					new TestItemSelectorReturnType());

		Class<FileEntryItemSelectorReturnType>
			fileEntryItemSelectorReturnTypeClass =
				FileEntryItemSelectorReturnType.class;

		Assert.assertEquals(
			fileEntryItemSelectorReturnTypeClass.getName(),
			itemSelectorReturnTypeClassName);
	}

	@Test
	public void testGetValueWithoutResolver() throws Exception {
		initMocks();

		FileEntry fileEntry = mock(FileEntry.class);
		ThemeDisplay themeDisplay = mock(ThemeDisplay.class);

		String value = ItemSelectorRepositoryEntryBrowserUtil.getValue(
			null, new FileEntryItemSelectorReturnType(), fileEntry,
			themeDisplay);

		Assert.assertEquals(
			"ItemSelectorRepositoryEntryBrowserReturnTypeUtilValue", value);
	}

	@Test
	public void testGetValueWithResolver() throws Exception {
		initMocks();

		FileEntry fileEntry = mock(FileEntry.class);
		ThemeDisplay themeDisplay = mock(ThemeDisplay.class);

		String value = ItemSelectorRepositoryEntryBrowserUtil.getValue(
			new TestFileEntryItemSelectorReturnTypeResolver(),
			new TestItemSelectorReturnType(), fileEntry, themeDisplay);

		Assert.assertEquals(
			"TestFileEntryItemSelectorReturnTypeResolverValue", value);
	}

	protected void initMocks() throws Exception {
		mockStatic(ItemSelectorRepositoryEntryBrowserReturnTypeUtil.class);

		when(
			ItemSelectorRepositoryEntryBrowserReturnTypeUtil.getValue(
				Mockito.any(FileEntryItemSelectorReturnType.class),
				Mockito.any(FileEntry.class), Mockito.any(ThemeDisplay.class))
		).thenReturn(
			"ItemSelectorRepositoryEntryBrowserReturnTypeUtilValue"
		);
	}

	private class TestFileEntryItemSelectorReturnTypeResolver
		implements ItemSelectorReturnTypeResolver
			<FileEntryItemSelectorReturnType, FileEntry> {

		public Class<FileEntryItemSelectorReturnType>
			getItemSelectorReturnTypeClass() {

			return FileEntryItemSelectorReturnType.class;
		}

		public Class<FileEntry> getModelClass() {
			return FileEntry.class;
		}

		public String getValue(FileEntry fileEntry, ThemeDisplay themeDisplay)
			throws Exception {

			return "TestFileEntryItemSelectorReturnTypeResolverValue";
		}

	}

	private class TestItemSelectorReturnType implements ItemSelectorReturnType {
	}

}