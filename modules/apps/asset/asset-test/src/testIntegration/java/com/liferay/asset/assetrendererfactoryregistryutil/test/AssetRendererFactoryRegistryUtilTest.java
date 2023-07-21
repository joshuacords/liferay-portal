/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.assetrendererfactoryregistryutil.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.test.util.asset.renderer.factory.TestAssetRendererFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PortalImpl;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Peter Fellwock
 */
@RunWith(Arquillian.class)
public class AssetRendererFactoryRegistryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGetAssetRendererFactories() {
		String className = TestAssetRendererFactory.class.getName();

		List<AssetRendererFactory<?>> assetRendererFactories =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactories(1);

		Stream<AssetRendererFactory<?>> assetRendererFactoriesStream =
			assetRendererFactories.stream();

		assetRendererFactoriesStream = assetRendererFactoriesStream.filter(
			assetRendererFactory -> {
				Class<?> clazz = assetRendererFactory.getClass();

				return className.equals(clazz.getName());
			});

		Assert.assertEquals(1, assetRendererFactoriesStream.count());
	}

	@Test
	public void testGetAssetRendererFactoryByClassName() {
		String className = TestAssetRendererFactory.class.getName();

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		Class<?> clazz = assetRendererFactory.getClass();

		Assert.assertEquals(className, clazz.getName());
	}

	@Test
	public void testGetAssetRendererFactoryByClassNameId() {
		PortalImpl portalImpl = new PortalImpl();

		long classNameId = portalImpl.getClassNameId(
			TestAssetRendererFactory.class);

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.
				getAssetRendererFactoryByClassNameId(classNameId);

		Class<?> clazz = assetRendererFactory.getClass();

		Assert.assertEquals(
			TestAssetRendererFactory.class.getName(), clazz.getName());
	}

	@Test
	public void testGetAssetRendererFactoryByType() {
		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByType(
				TestAssetRendererFactory.class.getName());

		Class<?> clazz = assetRendererFactory.getClass();

		Assert.assertEquals(
			TestAssetRendererFactory.class.getName(), clazz.getName());
	}

	@Test
	public void testGetClassNameIds1() {
		long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
			1);

		List<Long> classNameIdsList = ListUtil.fromArray(classNameIds);

		Assert.assertTrue(
			classNameIdsList.toString(),
			classNameIdsList.contains(Long.valueOf(1234567890)));
	}

	@Test
	public void testGetClassNameIds2() {
		long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
			1, true);

		List<Long> classNameIdsList = ListUtil.fromArray(classNameIds);

		Assert.assertTrue(
			classNameIdsList.toString(),
			classNameIdsList.contains(Long.valueOf(1234567890)));
	}

	@Test
	public void testGetClassNameIds3() {
		long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
			1, false);

		List<Long> classNameIdsList = ListUtil.fromArray(classNameIds);

		Assert.assertTrue(
			classNameIdsList.toString(),
			classNameIdsList.contains(Long.valueOf(1234567890)));
	}

}