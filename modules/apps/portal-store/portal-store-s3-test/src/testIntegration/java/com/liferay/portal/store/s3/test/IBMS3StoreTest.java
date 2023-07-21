/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.store.s3.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.AssumeTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.store.test.util.BaseStoreTestCase;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Dictionary;

import javax.annotation.Generated;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Preston Crary
 * @author Manuel de la Peña
 */
@Generated("")
@RunWith(Arquillian.class)
public class IBMS3StoreTest extends BaseStoreTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new AssumeTestRule("assume"), new LiferayIntegrationTestRule());

	public static void assume() {
		String s3StoreClassName = "com.liferay.portal.store.s3.IBMS3Store";
		String dlStoreImpl = PropsUtil.get(PropsKeys.DL_STORE_IMPL);

		Assume.assumeTrue(
			StringBundler.concat(
				"Property \"", PropsKeys.DL_STORE_IMPL, "\" is not set to \"",
				s3StoreClassName, "\""),
			dlStoreImpl.equals(s3StoreClassName));
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		_configuration = _configurationAdmin.getConfiguration(
			"com.liferay.portal.store.s3.configuration.S3StoreConfiguration",
			StringPool.QUESTION);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("accessKey", "");
		properties.put("bucketName", "test");
		properties.put("httpClientMaxConnections", "50");
		properties.put("s3Region", "us-east-1");
		properties.put("s3StorageClass", "STANDARD");
		properties.put("secretKey", "");
		properties.put("tempDirCleanUpExpunge", "7");
		properties.put("tempDirCleanUpFrequency", "100");

		ConfigurationTestUtil.saveConfiguration(_configuration, properties);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		ConfigurationTestUtil.deleteConfiguration(_configuration);
	}

	@Override
	@Test
	public void testUpdateFileWithNewFileNameNoSuchFileException()
		throws Exception {

		updateFileShouldNotUpdateFile();
	}

	@Override
	@Test
	public void testUpdateFileWithNewRepositoryIdNoSuchFileException()
		throws Exception {

		updateFileShouldNotUpdateFile();
	}

	@Override
	protected Store getStore() {
		return _store;
	}

	protected void updateFileShouldNotUpdateFile() throws Exception {
		String fileName = RandomTestUtil.randomString();

		store.updateFile(
			companyId, repositoryId, fileName, RandomTestUtil.randomString());

		Assert.assertFalse(store.hasFile(companyId, repositoryId, fileName));
	}

	private static Configuration _configuration;

	@Inject
	private static ConfigurationAdmin _configurationAdmin;

	@Inject(
		filter = "store.type=com.liferay.portal.store.s3.IBMS3Store",
		type = Store.class
	)
	private Store _store;

}