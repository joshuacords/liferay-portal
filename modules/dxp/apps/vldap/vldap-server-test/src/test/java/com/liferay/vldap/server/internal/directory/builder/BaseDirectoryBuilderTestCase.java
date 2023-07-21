/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.vldap.server.internal.BaseVLDAPTestCase;
import com.liferay.vldap.server.internal.directory.FilterConstraint;
import com.liferay.vldap.server.internal.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public abstract class BaseDirectoryBuilderTestCase extends BaseVLDAPTestCase {

	protected void doTestBuildDirectories() throws Exception {
		doTestBuildDirectoriesWithDefaultFilterConstraints();
		doTestBuildDirectoriesWithInvalidFilterConstraints();
		doTestBuildDirectoriesWithNullFilterConstraints();
	}

	protected void doTestBuildDirectoriesWithDefaultFilterConstraints()
		throws Exception {

		List<Directory> directories = directoryBuilder.buildDirectories(
			searchBase, Arrays.asList(new FilterConstraint()));

		Directory directory = directories.get(0);

		Assert.assertNotNull(directory);
	}

	protected void doTestBuildDirectoriesWithInvalidFilterConstraints()
		throws Exception {

		List<FilterConstraint> filterConstraints = new ArrayList<>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("test", "test");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = directoryBuilder.buildDirectories(
			searchBase, filterConstraints);

		Assert.assertTrue(directories.isEmpty());
	}

	protected void doTestBuildDirectoriesWithNullFilterConstraints()
		throws Exception {

		List<Directory> directories = directoryBuilder.buildDirectories(
			searchBase, new ArrayList<FilterConstraint>());

		Directory directory = directories.get(0);

		Assert.assertNotNull(directory);
	}

	protected void doTestValidAttributes(String name, String... values) {
		for (String value : values) {
			Assert.assertTrue(directoryBuilder.isValidAttribute(name, value));
		}
	}

	protected DirectoryBuilder directoryBuilder;

}