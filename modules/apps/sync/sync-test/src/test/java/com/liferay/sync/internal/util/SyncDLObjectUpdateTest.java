/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.util;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncDLObjectUpdateTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		ConfigurationFactoryUtil.setConfigurationFactory(
			new ConfigurationFactoryImpl());

		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());

		PortletClassLoaderUtil.setServletContextName("sync-web");
	}

	@Test
	public void testToString() {
		List<SyncDLObject> syncDLObjects = new ArrayList<>(3);

		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setDescription(
			"These values should be escaped: \", \\, \\b, \\f, \\n, \\r, \\t");
		syncDLObject.setLockExpirationDate(new Date());

		syncDLObjects.add(syncDLObject);
		syncDLObjects.add(syncDLObject);
		syncDLObjects.add(syncDLObject);

		SyncDLObjectUpdate syncDLObjectUpdate = new SyncDLObjectUpdate(
			syncDLObjects, syncDLObjects.size(), System.currentTimeMillis());

		String expectedJSON = JSONFactoryUtil.looseSerializeDeep(
			syncDLObjectUpdate);

		String actualJSON = syncDLObjectUpdate.toString();

		Assert.assertEquals(
			StringUtil.replace(expectedJSON, CharPool.SPACE, StringPool.BLANK),
			StringUtil.replace(actualJSON, CharPool.SPACE, StringPool.BLANK));
	}

}