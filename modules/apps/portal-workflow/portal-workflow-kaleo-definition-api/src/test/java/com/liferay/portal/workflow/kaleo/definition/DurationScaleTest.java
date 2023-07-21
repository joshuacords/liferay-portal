/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Péter Borkuti
 */
public class DurationScaleTest {

	public static final String[] SCALES = {
		"second", "millisecond", "minute", "hour", "day", "week", "month",
		"year"
	};

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test(expected = IllegalArgumentException.class)
	public void testParseInvalidScale() throws Exception {
		DurationScale.valueOf("random text");
	}

	@Test
	public void testParseValidScales() throws Exception {
		for (String scale : SCALES) {
			DurationScale durationScale = DurationScale.valueOf(
				StringUtil.toUpperCase(scale));

			Assert.assertEquals(scale, durationScale.getValue());
		}
	}

	@Test
	public void testScaleNum() throws Exception {
		DurationScale[] values = DurationScale.values();

		Assert.assertEquals(Arrays.toString(values), 8, values.length);
	}

}