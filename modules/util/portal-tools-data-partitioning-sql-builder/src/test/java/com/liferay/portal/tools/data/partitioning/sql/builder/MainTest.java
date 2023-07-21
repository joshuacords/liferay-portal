/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder;

import com.beust.jcommander.ParameterException;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author Manuel de la Peña
 */
public class MainTest {

	@Test(expected = IllegalArgumentException.class)
	public void testValidateEmptyArguments() throws Exception {
		Main.main(new String[0]);
	}

	@Test(expected = ParameterException.class)
	public void testValidateInvalidCompanyId() throws Exception {
		String[] arguments = {
			"-C", "foo", "-O", "bar", "-P", "foo.properties", "-S", _SCHEMA_NAME
		};

		Main.main(arguments);
	}

	@Test(expected = ParameterException.class)
	public void testValidateInvalidOptionArguments() throws Exception {
		Main.main(new String[] {"-X", "foo"});
	}

	@Test(expected = ParameterException.class)
	public void testValidateNonexistingOutputDirectory() throws Exception {
		String[] arguments = {
			"-C", _COMPANY_ID, "-O", "foo", "-P", "foo.properties", "-S",
			_SCHEMA_NAME
		};

		Main.main(arguments);
	}

	@Test(expected = ParameterException.class)
	public void testValidateNonexistingPropertiesFile() throws Exception {
		String[] arguments = {
			"-C", _COMPANY_ID, "-O", "bar", "-P", "foo.properties", "-S",
			_SCHEMA_NAME
		};

		Main.main(arguments);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateNullArguments() throws Exception {
		Main.main(null);
	}

	@Test(expected = ParameterException.class)
	public void testValidateReadOnlyOutputDirectory() throws Exception {
		File readOnlyDir = temporaryFolder.newFolder();

		readOnlyDir.setReadable(false);
		readOnlyDir.setWritable(false);

		String[] arguments = {
			"-C", _COMPANY_ID, "-O", readOnlyDir.getAbsolutePath(), "-P",
			"foo.properties", "-S", _SCHEMA_NAME
		};

		Main.main(arguments);
	}

	@Test
	public void testValidateRequiredArguments() throws Exception {
		String[][] requiredArguments = {
			{"-C", ""}, {"-O", ""}, {"-P", ""}, {"-S", ""}
		};

		for (String[] requiredArgument : requiredArguments) {
			try {
				Main.main(requiredArgument);
			}
			catch (ParameterException parameterException) {
			}
		}
	}

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private static final String _COMPANY_ID = "20156";

	private static final String _SCHEMA_NAME = "lportal";

}