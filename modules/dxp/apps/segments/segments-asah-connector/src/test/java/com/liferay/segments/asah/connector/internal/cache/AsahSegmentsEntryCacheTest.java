/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author David Arques
 */
@RunWith(MockitoJUnitRunner.class)
public class AsahSegmentsEntryCacheTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		ReflectionTestUtil.setFieldValue(
			_asahSegmentsEntryCache, "_portalCache", _portalCache);
	}

	@Test
	public void testGetSegmentsEntryIds() {
		String userId = RandomTestUtil.randomString();

		long[] segmentsEntryIds = {
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong()
		};

		String cacheKey = _generateCacheKey(userId);

		Mockito.when(
			_portalCache.get(cacheKey)
		).thenReturn(
			segmentsEntryIds
		);

		Assert.assertArrayEquals(
			segmentsEntryIds,
			_asahSegmentsEntryCache.getSegmentsEntryIds(userId));

		Mockito.verify(
			_portalCache, Mockito.times(1)
		).get(
			Mockito.eq(cacheKey)
		);
	}

	@Test
	public void testPutSegmentsEntryIds() {
		String userId = RandomTestUtil.randomString();

		long[] segmentsEntryIds = {
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong()
		};

		String cacheKey = _generateCacheKey(userId);

		_asahSegmentsEntryCache.putSegmentsEntryIds(userId, segmentsEntryIds);

		Mockito.verify(
			_portalCache, Mockito.times(1)
		).put(
			cacheKey, segmentsEntryIds, 0
		);
	}

	private String _generateCacheKey(String userId) {
		return "segments-" + userId;
	}

	private final AsahSegmentsEntryCache _asahSegmentsEntryCache =
		new AsahSegmentsEntryCache();

	@Mock
	private PortalCache<String, long[]> _portalCache;

}