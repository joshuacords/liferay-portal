/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.map;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sampsa Sohlman
 * @author Manuel de la Peña
 * @author Péter Borkuti
 */
public class MapUtilWhenFilteringByPredicateTest {

	@Test
	public void testShouldAllowFilterBySuperType() {
		Map<String, Integer> inputMap = HashMapBuilder.put(
			"1", 1
		).put(
			"2", 2
		).put(
			"3", 3
		).put(
			"4", 4
		).put(
			"5", 5
		).build();

		Map<String, Integer> outputMap = MapUtil.filterByValues(
			inputMap, number -> (number.intValue() % 2) == 0);

		Assert.assertEquals(outputMap.toString(), 2, outputMap.size());
		Assert.assertEquals((Integer)2, outputMap.get("2"));
		Assert.assertEquals((Integer)4, outputMap.get("4"));
	}

	@Test
	public void testShouldAllowFilterBySuperTypeAndOutputToSupertype() {
		Map<String, Integer> inputMap = HashMapBuilder.put(
			"1", 1
		).put(
			"2", 2
		).put(
			"3", 3
		).put(
			"4", 4
		).put(
			"5", 5
		).build();

		HashMap<String, Number> outputMap = new HashMap<>();

		MapUtil.filter(
			inputMap, outputMap,
			entry -> {
				Integer i = (Integer)entry.getValue();

				if ((i.intValue() % 2) == 0) {
					return true;
				}

				return false;
			});

		Assert.assertEquals(outputMap.toString(), 2, outputMap.size());
		Assert.assertEquals(2, outputMap.get("2"));
		Assert.assertEquals(4, outputMap.get("4"));
	}

	@Test
	public void testShouldReturnMapFilteredByEven() {
		Map<String, String> inputMap = HashMapBuilder.put(
			"1", "one"
		).put(
			"2", "two"
		).put(
			"3", "three"
		).put(
			"4", "four"
		).put(
			"5", "five"
		).build();

		Map<String, String> outputMap = MapUtil.filter(
			inputMap,
			entry -> (GetterUtil.getInteger(entry.getKey()) % 2) == 0);

		Assert.assertEquals(outputMap.toString(), 2, outputMap.size());
		Assert.assertEquals("two", outputMap.get("2"));
		Assert.assertEquals("four", outputMap.get("4"));
	}

	@Test
	public void testShouldReturnMapFilteredByPrefix() {
		Map<String, String> inputMap = HashMapBuilder.put(
			"2", "two"
		).put(
			"4", "four"
		).put(
			"x1", "one"
		).put(
			"x3", "three"
		).put(
			"x5", "five"
		).build();

		Map<String, String> outputMap = MapUtil.filterByKeys(
			inputMap, key -> !key.startsWith("x"));

		Assert.assertEquals(outputMap.toString(), 2, outputMap.size());
		Assert.assertEquals("two", outputMap.get("2"));
		Assert.assertEquals("four", outputMap.get("4"));
	}

}