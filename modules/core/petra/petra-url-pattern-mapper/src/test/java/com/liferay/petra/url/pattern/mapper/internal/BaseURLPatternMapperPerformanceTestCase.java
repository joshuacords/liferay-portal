/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.url.pattern.mapper.internal;

import com.liferay.petra.url.pattern.mapper.URLPatternMapper;

import java.util.BitSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Arthur Chan
 */
public abstract class BaseURLPatternMapperPerformanceTestCase
	extends BaseURLPatternMapperTestCase {

	@Test
	public void testConsumeValues() {
		URLPatternMapper<Integer> urlPatternMapper = createURLPatternMapper(
			createValues());

		long start = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			for (String urlPath : expectedURLPatternIndexesMap.keySet()) {
				urlPatternMapper.consumeValues(
					__ -> {
					},
					urlPath);
			}
		}

		long end = System.currentTimeMillis();

		long delta = end - start;

		System.out.println("Iterated 100 thousand times in " + delta + " ms");

		Assert.assertTrue(delta < 10000);
	}

	@Test
	public void testConsumeValuesOrdered() {

		// Emulate filter chain in liferay-web.xml

		BitSet bitSet = new BitSet(128);

		URLPatternMapper<Integer> urlPatternMapper = createURLPatternMapper(
			createValues());

		long start = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			for (String urlPath : expectedURLPatternIndexesMap.keySet()) {
				urlPatternMapper.consumeValues(bitSet::set, urlPath);

				for (int j = bitSet.nextSetBit(0); j >= 0;
					 j = bitSet.nextSetBit(j + 1)) {
				}
			}
		}

		long end = System.currentTimeMillis();

		long delta = end - start;

		System.out.println("Iterated 100 thousand times in " + delta + " ms");

		Assert.assertTrue(delta < 10000);
	}

	@Test
	public void testGetValue() {
		URLPatternMapper<Integer> urlPatternMapper = createURLPatternMapper(
			createValues());

		long start = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			for (String urlPath : expectedURLPatternIndexesMap.keySet()) {
				urlPatternMapper.getValue(urlPath);
			}
		}

		long end = System.currentTimeMillis();

		long delta = end - start;

		System.out.println("Iterated 100 thousand times in " + delta + " ms");

		Assert.assertTrue(delta < 4000);
	}

	@Test
	public void testGetValues() {
		URLPatternMapper<Integer> urlPatternMapper = createURLPatternMapper(
			createValues());

		long start = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			for (String urlPath : expectedURLPatternIndexesMap.keySet()) {
				urlPatternMapper.getValues(urlPath);
			}
		}

		long end = System.currentTimeMillis();

		long delta = end - start;

		System.out.println("Iterated 100 thousand times in " + delta + " ms");

		Assert.assertTrue(delta < 10000);
	}

}