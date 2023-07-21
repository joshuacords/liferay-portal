/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.util;

import com.liferay.document.library.configuration.DLConfiguration;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.FileImpl;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Adolfo Pérez
 */
public class DLValidatorImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		DLValidatorImpl dlValidatorImpl = new DLValidatorImpl();

		_dlConfiguration = Mockito.mock(DLConfiguration.class);

		dlValidatorImpl.setDLConfiguration(_dlConfiguration);

		_dlValidator = dlValidatorImpl;

		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(FileImpl.getInstance());
	}

	@Test(expected = FileExtensionException.class)
	public void testInvalidExtension() throws Exception {
		_validateFileExtension("test.gıf");
	}

	@Test
	public void testValidLowerCaseExtension() throws Exception {
		_validateFileExtension("test.gif");
	}

	@Test
	public void testValidMixedCaseExtension() throws Exception {
		_validateFileExtension("test.GiF");
	}

	@Test
	public void testValidUpperCaseExtension() throws Exception {
		_validateFileExtension("test.GIF");
	}

	private void _validateFileExtension(String fileName)
		throws FileExtensionException {

		Mockito.when(
			_dlConfiguration.fileExtensions()
		).thenReturn(
			new String[] {".gif"}
		);

		_dlValidator.validateFileExtension(fileName);

		Mockito.when(
			_dlConfiguration.fileExtensions()
		).thenReturn(
			new String[] {"gif"}
		);

		_dlValidator.validateFileExtension(fileName);
	}

	private DLConfiguration _dlConfiguration;
	private DLValidator _dlValidator;

}