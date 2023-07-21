/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upload;

import com.liferay.portal.kernel.test.util.DependenciesTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.FastDateFormatFactoryImpl;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.util.MimeTypesImpl;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class LiferayFileItemTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws IOException {
		FastDateFormatFactoryUtil fastDateFormatFactoryUtil =
			new FastDateFormatFactoryUtil();

		fastDateFormatFactoryUtil.setFastDateFormatFactory(
			new FastDateFormatFactoryImpl());

		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(new FileImpl());

		MimeTypesUtil mimeTypesUtil = new MimeTypesUtil();

		mimeTypesUtil.setMimeTypes(new MimeTypesImpl());

		_liferayFileItemFactory = new LiferayFileItemFactory(
			FileUtil.createTempFolder());
	}

	@Test
	public void testCreateItem() {
		String fieldName = RandomTestUtil.randomString();
		String fileName = RandomTestUtil.randomString();

		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			fieldName, RandomTestUtil.randomString(), false, fileName);

		Assert.assertEquals(fieldName, liferayFileItem.getFieldName());
		Assert.assertEquals(fileName, liferayFileItem.getFullFileName());
		Assert.assertFalse(liferayFileItem.isFormField());
	}

	@Test
	public void testGetContentTypeFromInvalidFile() throws IOException {
		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), false,
			RandomTestUtil.randomString());

		Assert.assertNotNull(liferayFileItem);

		liferayFileItem.getOutputStream();

		Assert.assertEquals(
			ContentTypes.APPLICATION_OCTET_STREAM,
			liferayFileItem.getContentType());
	}

	@Test
	public void testGetContentTypeFromRealFile() throws Exception {
		File file = DependenciesTestUtil.getDependencyAsFile(
			getClass(), "LiferayFileItem.txt");

		String contentType = Files.probeContentType(file.toPath());

		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), contentType, false, file.getName());

		Assert.assertNotNull(liferayFileItem);

		liferayFileItem.getOutputStream();

		Assert.assertEquals(contentType, liferayFileItem.getContentType());
	}

	@Test
	public void testGetEncodedStringAfterCreateItem() {
		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), false,
			RandomTestUtil.randomString());

		Assert.assertNotNull(liferayFileItem);
		Assert.assertNull(liferayFileItem.getEncodedString());
	}

	@Test
	public void testGetFileNameExtension() {
		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), false,
			RandomTestUtil.randomString() + ".txt");

		Assert.assertEquals("txt", liferayFileItem.getFileNameExtension());
	}

	@Test
	public void testGetFileNameExtensionWithNullValue() {
		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), false,
			"theFile");

		Assert.assertEquals("", liferayFileItem.getFileNameExtension());
	}

	@Test
	public void testSetStringRequiresCharacterEncoding() throws Exception {
		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), false,
			RandomTestUtil.randomString() + ".txt");

		liferayFileItem.getOutputStream();

		liferayFileItem.setString("UTF-8");

		Assert.assertEquals("", liferayFileItem.getEncodedString());
	}

	@Test(expected = NullPointerException.class)
	public void testSetStringWithoutOutputStream() {
		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), false,
			RandomTestUtil.randomString() + ".txt");

		Assert.assertNotNull(liferayFileItem);

		liferayFileItem.setString(RandomTestUtil.randomString());
	}

	@Test
	public void testWriteRequiresCallingGetOutputStream() throws Exception {
		LiferayFileItem liferayFileItem = _liferayFileItemFactory.createItem(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), false,
			RandomTestUtil.randomString() + ".txt");

		liferayFileItem.getOutputStream();

		liferayFileItem.write(FileUtil.createTempFile());

		liferayFileItem.setString("UTF-8");

		Assert.assertEquals("", liferayFileItem.getEncodedString());
	}

	private static LiferayFileItemFactory _liferayFileItemFactory;

}