/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.comparator.UserScreenNameComparator;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public class UsersBuilderTest extends BaseDirectoryBuilderTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		directoryBuilder = new UsersBuilder();
	}

	@Test
	public void testBuildDirectories() throws Exception {
		User user = mock(User.class);

		when(
			user.getScreenName()
		).thenReturn(
			"testScreenName"
		);

		when(
			userLocalService.search(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyInt(), Mockito.any(LinkedHashMap.class),
				Mockito.anyBoolean(), Mockito.anyInt(), Mockito.anyInt(),
				Mockito.any(UserScreenNameComparator.class))
		).thenReturn(
			Arrays.asList(user)
		);

		doTestBuildDirectories();
	}

	@Test
	public void testValidAttributes() {
		doTestValidAttributes("objectclass", "organizationalUnit", "top", "*");
		doTestValidAttributes("ou", "Users", "*");
	}

}