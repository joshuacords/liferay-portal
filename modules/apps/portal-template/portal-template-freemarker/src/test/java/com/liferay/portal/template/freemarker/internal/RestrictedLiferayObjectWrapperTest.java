/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.freemarker.internal;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import freemarker.ext.beans.InvalidPropertyException;
import freemarker.ext.beans.SimpleMethodModel;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.hamcrest.CoreMatchers;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Tina Tian
 */
public class RestrictedLiferayObjectWrapperTest
	extends BaseObjectWrapperTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testConstructor() {
		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					RestrictedLiferayObjectWrapper.class.getName(),
					Level.INFO)) {

			Assert.assertEquals(
				Collections.singletonList("com.liferay.package.name"),
				ReflectionTestUtil.getFieldValue(
					new RestrictedLiferayObjectWrapper(
						null, new String[] {"com.liferay.package.name"}, null),
					"_restrictedPackageNames"));

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Assert.assertEquals(logRecords.toString(), 1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				"Unable to find restricted class com.liferay.package.name. " +
					"Registering as a package.",
				logRecord.getMessage());
		}

		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					RestrictedLiferayObjectWrapper.class.getName(),
					Level.OFF)) {

			Assert.assertEquals(
				Collections.singletonList("com.liferay.package.name"),
				ReflectionTestUtil.getFieldValue(
					new RestrictedLiferayObjectWrapper(
						null, new String[] {"com.liferay.package.name"}, null),
					"_restrictedPackageNames"));

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Assert.assertEquals(logRecords.toString(), 0, logRecords.size());
		}
	}

	@Test
	public void testIsRestricted() {
		Assert.assertFalse(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(null, null, null),
				TestLiferayObject.class));

		Assert.assertFalse(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					new String[] {TestLiferayObject.class.getName()},
					new String[] {TestLiferayObject.class.getName()}, null),
				TestLiferayObject.class));

		Assert.assertFalse(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					null, new String[] {"java.lang.String"}, null),
				TestLiferayObject.class));

		Assert.assertFalse(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					null, new String[] {"com.liferay.portal.cache"}, null),
				TestLiferayObject.class));

		Assert.assertTrue(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					null, new String[] {TestLiferayObject.class.getName()},
					null),
				TestLiferayObject.class));

		Assert.assertTrue(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					null,
					new String[] {"com.liferay.portal.template.freemarker"},
					null),
				TestLiferayObject.class));

		Assert.assertTrue(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					null, new String[] {"com.liferay.portal.*"}, null),
				TestLiferayObject.class));

		Assert.assertFalse(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					null,
					new String[] {"com.liferay.portal.template.freemarker"},
					null),
				byte.class));

		Assert.assertFalse(
			_isRestricted(
				new RestrictedLiferayObjectWrapper(
					null,
					new String[] {"com.liferay.portal.template.freemarker"},
					null),
				byte.class));
	}

	@Test
	public void testIsRestrictedWithNoContextClassloader() {
		Thread thread = Thread.currentThread();

		ClassLoader contextClassLoader = thread.getContextClassLoader();

		thread.setContextClassLoader(null);

		try {
			Assert.assertFalse(
				_isRestricted(
					new RestrictedLiferayObjectWrapper(
						new String[] {TestLiferayObject.class.getName()},
						new String[] {TestLiferayObject.class.getName()}, null),
					TestLiferayObject.class));
		}
		finally {
			thread.setContextClassLoader(contextClassLoader);
		}
	}

	@Test
	public void testRestrictedClass() throws Exception {
		RestrictedLiferayObjectWrapper restrictedLiferayObjectWrapper =
			new RestrictedLiferayObjectWrapper(
				null, new String[] {TestLiferayMethodObject.class.getName()},
				null);

		TemplateModel templateModel = restrictedLiferayObjectWrapper.wrap(
			new TestLiferayMethodObject("test"));

		Assert.assertThat(
			templateModel,
			CoreMatchers.instanceOf(LiferayFreeMarkerStringModel.class));

		LiferayFreeMarkerStringModel liferayFreeMarkerStringModel =
			(LiferayFreeMarkerStringModel)templateModel;

		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "name");
		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "Name");
		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "getName");
		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "getname");
		_testRestrictedMethodNames(
			liferayFreeMarkerStringModel, "generateName");
	}

	@Test
	public void testRestrictedMethodNames() throws Exception {
		RestrictedLiferayObjectWrapper restrictedLiferayObjectWrapper =
			new RestrictedLiferayObjectWrapper(
				null, null,
				new String[] {
					TestLiferayMethodObject.class.getName() + "#getName"
				});

		TemplateModel templateModel = restrictedLiferayObjectWrapper.wrap(
			new TestLiferayMethodObject("test"));

		Assert.assertThat(
			templateModel,
			CoreMatchers.instanceOf(LiferayFreeMarkerStringModel.class));

		LiferayFreeMarkerStringModel liferayFreeMarkerStringModel =
			(LiferayFreeMarkerStringModel)templateModel;

		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "name");
		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "Name");
		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "getName");
		_testRestrictedMethodNames(liferayFreeMarkerStringModel, "getname");

		SimpleMethodModel simpleMethodModel =
			(SimpleMethodModel)liferayFreeMarkerStringModel.get("generate");

		TemplateModel resultTemplateModel =
			(TemplateModel)simpleMethodModel.exec(
				Collections.singletonList(new SimpleScalar("generate")));

		Assert.assertEquals("test-generate", resultTemplateModel.toString());
	}

	@Test
	public void testRestrictedMethodNamesIncorrectSyntax() {
		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					RestrictedLiferayObjectWrapper.class.getName(),
					Level.INFO)) {

			String methodName =
				TestLiferayMethodObject.class.getName() + ".getName";

			new RestrictedLiferayObjectWrapper(
				null, null, new String[] {methodName});

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Assert.assertEquals(logRecords.toString(), 1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				StringBundler.concat(
					"\"", methodName, "\" does not match format ",
					"\"className#methodName\""),
				logRecord.getMessage());
		}
	}

	@Test
	public void testWrap() throws Exception {
		testWrap(new RestrictedLiferayObjectWrapper(null, null, null));
		testWrap(
			new RestrictedLiferayObjectWrapper(
				new String[] {StringPool.STAR}, null, null));
		testWrap(
			new RestrictedLiferayObjectWrapper(
				new String[] {StringPool.STAR},
				new String[] {LiferayObjectWrapper.class.getName()}, null));
		testWrap(
			new RestrictedLiferayObjectWrapper(
				new String[] {StringPool.BLANK}, null, null));
		testWrap(
			new RestrictedLiferayObjectWrapper(
				null, new String[] {StringPool.BLANK}, null));
		testWrap(
			new RestrictedLiferayObjectWrapper(
				new String[] {StringPool.BLANK},
				new String[] {StringPool.BLANK}, null));

		testWrap(
			new RestrictedLiferayObjectWrapper(
				new String[] {StringPool.BLANK},
				new String[] {StringPool.BLANK},
				new String[] {StringPool.BLANK}));

		testWrap(
			new RestrictedLiferayObjectWrapper(
				new String[] {StringPool.BLANK},
				new String[] {StringPool.BLANK},
				new String[] {
					TestLiferayMethodObject.class.getName() + "#getName"
				}));
	}

	public class TestLiferayMethodObject {

		public String generate(String postfix) {
			return _name + StringPool.DASH + postfix;
		}

		public String getName() {
			return _name;
		}

		public void setName(String name) {
			_name = name;
		}

		@Override
		public String toString() {
			return _name;
		}

		private TestLiferayMethodObject(String name) {
			_name = name;
		}

		private String _name;

	}

	private boolean _isRestricted(
		RestrictedLiferayObjectWrapper restrictedLiferayObjectWrapper,
		Class<?> targetClass) {

		return ReflectionTestUtil.invoke(
			restrictedLiferayObjectWrapper, "_isRestricted",
			new Class<?>[] {Class.class}, targetClass);
	}

	private void _testRestrictedMethodNames(
		LiferayFreeMarkerStringModel liferayFreeMarkerStringModel, String key) {

		try {
			liferayFreeMarkerStringModel.get(key);

			Assert.assertNull("Should throw TemplateModelException for " + key);
		}
		catch (TemplateModelException templateModelException) {
			Assert.assertSame(
				InvalidPropertyException.class,
				templateModelException.getClass());

			Assert.assertEquals(
				StringBundler.concat(
					"Denied access to method or field ", key, " of ",
					TestLiferayMethodObject.class),
				templateModelException.getMessage());
		}
	}

}