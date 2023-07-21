/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypes;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alexander Chow
 * @see    FileImplExtractTest
 */
public class MimeTypesImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(new FileImpl());

		MimeTypesImpl mimeTypesImpl = new MimeTypesImpl();

		mimeTypesImpl.afterPropertiesSet();

		_mimeTypes = mimeTypesImpl;
	}

	@Test
	public void testDoc() throws Exception {
		String validContentType = "application/msword";

		String contentTypeStream = getContentType("test.doc", true);
		String contentTypeName = getContentType("test.doc", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testDocx() throws Exception {
		String validContentType =
			"application" +
				"/vnd.openxmlformats-officedocument.wordprocessingml.document";

		String contentTypeStream = getContentType("test-2007.docx", true);
		String contentTypeName = getContentType("test-2007.docx", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);

		contentTypeStream = getContentType("test-2010.docx", true);
		contentTypeName = getContentType("test-2010.docx", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testHtml() throws Exception {
		String validContentType = "text/html";

		String contentTypeStream = getContentType("test.html", true);
		String contentTypeName = getContentType("test.html", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testJpg() throws Exception {
		String validContentType = "image/jpeg";

		String contentTypeStream = getContentType("test.jpg", true);
		String contentTypeName = getContentType("test.jpg", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testLar() throws Exception {
		String validContentType = "application/zip";

		String contentTypeStream = getContentType("test.lar", true);
		String contentTypeName = getContentType("test.lar", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testOdt() throws Exception {
		String validContentType = "application/vnd.oasis.opendocument.text";

		String contentTypeStream = getContentType("test.odt", true);
		String contentTypeName = getContentType("test.odt", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testPdf() throws Exception {
		String validContentType = "application/pdf";

		String contentTypeStream = getContentType("test.pdf", true);
		String contentTypeName = getContentType("test.pdf", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);

		contentTypeStream = getContentType("test-2010.pdf", true);
		contentTypeName = getContentType("test-2010.pdf", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testPpt() throws Exception {
		String validContentType = "application/vnd.ms-powerpoint";

		String contentTypeStream = getContentType("test.ppt", true);
		String contentTypeName = getContentType("test.ppt", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testPptx() throws Exception {
		String validContentType =
			"application/vnd.openxmlformats-officedocument.presentationml." +
				"presentation";

		String contentTypeStream = getContentType("test-2010.pptx", true);
		String contentTypeName = getContentType("test-2010.pptx", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testRtf() throws Exception {
		String validContentType = "application/rtf";

		String contentTypeStream = getContentType("test.rtf", true);
		String contentTypeName = getContentType("test.rtf", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testTxt() throws Exception {
		String validContentType = "text/plain";

		String contentTypeStream = getContentType("test.txt", true);
		String contentTypeName = getContentType("test.txt", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testXls() throws Exception {
		String validContentType = "application/vnd.ms-excel";

		String contentTypeStream = getContentType("test.xls", true);
		String contentTypeName = getContentType("test.xls", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testXlsx() throws Exception {
		String validContentType =
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

		String contentTypeStream = getContentType("test-2010.xlsx", true);
		String contentTypeName = getContentType("test-2010.xlsx", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	@Test
	public void testXml() throws Exception {
		String validContentType = "application/xml";

		String contentTypeStream = getContentType("test.xml", true);
		String contentTypeName = getContentType("test.xml", false);

		Assert.assertEquals(validContentType, contentTypeStream);
		Assert.assertEquals(validContentType, contentTypeName);
	}

	protected String getContentType(String fileName, boolean checkStream)
		throws Exception {

		if (checkStream) {
			InputStream inputStream = getInputStream(fileName);

			return _mimeTypes.getContentType(inputStream, fileName);
		}

		return _mimeTypes.getContentType(fileName);
	}

	protected InputStream getInputStream(String fileName) throws Exception {
		Class<?> clazz = getClass();

		return clazz.getResourceAsStream("dependencies/" + fileName);
	}

	private MimeTypes _mimeTypes;

}