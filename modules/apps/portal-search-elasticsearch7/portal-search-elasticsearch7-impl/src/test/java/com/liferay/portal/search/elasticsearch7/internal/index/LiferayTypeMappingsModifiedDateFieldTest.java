/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.IndexName;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PropsImpl;

import java.util.Collections;
import java.util.Date;

import org.elasticsearch.index.mapper.MapperParsingException;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

/**
 * @author Bryan Engler
 */
public class LiferayTypeMappingsModifiedDateFieldTest {

	@ClassRule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		PropsUtil.setProps(new PropsImpl());

		Class<?> clazz = getClass();

		_liferayIndexFixture = new LiferayIndexFixture(
			clazz.getSimpleName(), new IndexName(testName.getMethodName()));

		_liferayIndexFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_liferayIndexFixture.tearDown();
	}

	@Test
	public void testDate() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(
			"failed to parse date field [1970-01-18T12:08:26.556Z] with " +
				"format [yyyyMMddHHmmss]");

		index(new Date(1512506556L));
	}

	@Test
	public void testLong() throws Exception {
		index(20171115050402L);

		_liferayIndexFixture.assertType("modified", "date");
	}

	@Test
	public void testLongMalformed() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(
			"failed to parse date field [1512506556] with format " +
				"[yyyyMMddHHmmss]");

		index(1512506556L);
	}

	@Test
	public void testString() throws Exception {
		index("20171115050402");

		_liferayIndexFixture.assertType("modified", "date");
	}

	@Test
	public void testStringMalformed() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(
			"failed to parse date field [2017-11-15 05:04:02] with format " +
				"[yyyyMMddHHmmss]");

		index("2017-11-15 05:04:02");
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Rule
	public TestName testName = new TestName();

	protected void index(Object value) throws Exception {
		try {
			_liferayIndexFixture.index(
				Collections.singletonMap(Field.MODIFIED_DATE, value));
		}
		catch (MapperParsingException mapperParsingException) {
			throw (Exception)mapperParsingException.getCause();
		}
	}

	private LiferayIndexFixture _liferayIndexFixture;

}