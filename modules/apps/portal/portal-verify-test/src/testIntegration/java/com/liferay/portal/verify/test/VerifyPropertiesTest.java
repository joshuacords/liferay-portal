/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.test.log.CaptureAppender;
import com.liferay.portal.test.log.Log4JLoggerTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.verify.VerifyProperties;
import com.liferay.portal.verify.test.util.BaseVerifyProcessTestCase;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class VerifyPropertiesTest extends BaseVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testMigratedPortalKeys() throws Exception {
		String migratedPortalKey = getFirstPortalPropertyKey();

		String[][] originalMigratedPortalKeys = _setPropertyKeys(
			"_MIGRATED_PORTAL_KEYS",
			new String[][] {{migratedPortalKey, migratedPortalKey}});

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertEquals(
				loggingEvents.toString(), 1, loggingEvents.size());

			LoggingEvent loggingEvent = loggingEvents.get(0);

			Assert.assertEquals(
				StringBundler.concat(
					"Portal property \"", migratedPortalKey,
					"\" was migrated to the system property \"",
					migratedPortalKey, "\""),
				loggingEvent.getMessage());
		}
		finally {
			_setPropertyKeys(
				"_MIGRATED_PORTAL_KEYS", originalMigratedPortalKeys);
		}
	}

	@Test
	public void testMigratedSystemKeys() throws Exception {
		String migratedSystemKey = getFirstSystemPropertyKey();

		String[][] originalMigratedSystemKeys = _setPropertyKeys(
			"_MIGRATED_SYSTEM_KEYS",
			new String[][] {{migratedSystemKey, migratedSystemKey}});

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertEquals(
				loggingEvents.toString(), 1, loggingEvents.size());

			LoggingEvent loggingEvent = loggingEvents.get(0);

			Assert.assertEquals(
				StringBundler.concat(
					"System property \"", migratedSystemKey,
					"\" was migrated to the portal property \"",
					migratedSystemKey, "\""),
				loggingEvent.getMessage());
		}
		finally {
			_setPropertyKeys(
				"_MIGRATED_SYSTEM_KEYS", originalMigratedSystemKeys);
		}
	}

	@Test
	public void testModularizedPortalKeys() throws Exception {
		String modularizedPortalKey = getFirstPortalPropertyKey();

		String[][] originalModularizedPortalKeys = _setPropertyKeys(
			"_MODULARIZED_PORTAL_KEYS",
			new String[][] {
				{
					modularizedPortalKey, modularizedPortalKey,
					modularizedPortalKey
				}
			});

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertEquals(
				loggingEvents.toString(), 1, loggingEvents.size());

			LoggingEvent loggingEvent = loggingEvents.get(0);

			Assert.assertEquals(
				StringBundler.concat(
					"Portal property \"", modularizedPortalKey,
					"\" was modularized to ", modularizedPortalKey, " as \"",
					modularizedPortalKey, "\""),
				loggingEvent.getMessage());
		}
		finally {
			_setPropertyKeys(
				"_MODULARIZED_PORTAL_KEYS", originalModularizedPortalKeys);
		}
	}

	@Test
	public void testObsoletePortalKeys() throws Exception {
		String obsoletePortalKey = getFirstPortalPropertyKey();

		String[] originalObsoletePortalKeys = _setPropertyKeys(
			"_OBSOLETE_PORTAL_KEYS", new String[] {obsoletePortalKey});

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertEquals(
				loggingEvents.toString(), 1, loggingEvents.size());

			LoggingEvent loggingEvent = loggingEvents.get(0);

			Assert.assertEquals(
				"Portal property \"" + obsoletePortalKey + "\" is obsolete",
				loggingEvent.getMessage());
		}
		finally {
			_setPropertyKeys(
				"_OBSOLETE_PORTAL_KEYS", originalObsoletePortalKeys);
		}
	}

	@Test
	public void testObsoleteSystemKeys() throws Exception {
		String obsoleteSystemKey = getFirstSystemPropertyKey();

		String[] originalObsoleteSystemKeys = _setPropertyKeys(
			"_OBSOLETE_SYSTEM_KEYS", new String[] {obsoleteSystemKey});

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertEquals(
				loggingEvents.toString(), 1, loggingEvents.size());

			LoggingEvent loggingEvent = loggingEvents.get(0);

			Assert.assertEquals(
				"System property \"" + obsoleteSystemKey + "\" is obsolete",
				loggingEvent.getMessage());
		}
		finally {
			_setPropertyKeys(
				"_OBSOLETE_SYSTEM_KEYS", originalObsoleteSystemKeys);
		}
	}

	@Test
	public void testRenamedPortalKeys() throws Exception {
		String renamedPortalKey = getFirstPortalPropertyKey();

		String[][] originalRenamedPortalKeys = _setPropertyKeys(
			"_RENAMED_PORTAL_KEYS",
			new String[][] {new String[] {renamedPortalKey, renamedPortalKey}});

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertEquals(
				loggingEvents.toString(), 1, loggingEvents.size());

			LoggingEvent loggingEvent = loggingEvents.get(0);

			Assert.assertEquals(
				StringBundler.concat(
					"Portal property \"", renamedPortalKey,
					"\" was renamed to \"", renamedPortalKey, "\""),
				loggingEvent.getMessage());
		}
		finally {
			_setPropertyKeys("_RENAMED_PORTAL_KEYS", originalRenamedPortalKeys);
		}
	}

	@Test
	public void testRenamedSystemKeys() throws Exception {
		String renamedSystemKey = getFirstSystemPropertyKey();

		String[][] originalRenamedSystemKeys = _setPropertyKeys(
			"_RENAMED_SYSTEM_KEYS",
			new String[][] {new String[] {renamedSystemKey, renamedSystemKey}});

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertEquals(
				loggingEvents.toString(), 1, loggingEvents.size());

			LoggingEvent loggingEvent = loggingEvents.get(0);

			Assert.assertEquals(
				StringBundler.concat(
					"System property \"", renamedSystemKey,
					"\" was renamed to \"", renamedSystemKey, "\""),
				loggingEvent.getMessage());
		}
		finally {
			_setPropertyKeys("_RENAMED_SYSTEM_KEYS", originalRenamedSystemKeys);
		}
	}

	@Override
	@Test
	public void testVerify() throws Exception {
		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					VerifyProperties.class.getName(), Level.ERROR)) {

			doVerify();

			List<LoggingEvent> loggingEvents =
				captureAppender.getLoggingEvents();

			Assert.assertTrue(
				loggingEvents.toString(), loggingEvents.isEmpty());
		}
	}

	protected String getFirstPortalPropertyKey() {
		VerifyProperties verifyProperties = getVerifyProcess();

		Properties portalProperties = ReflectionTestUtil.invoke(
			verifyProperties, "loadPortalProperties", new Class<?>[0]);

		Set<String> propertyNames = portalProperties.stringPropertyNames();

		Assert.assertFalse(propertyNames.toString(), propertyNames.isEmpty());

		Iterator<String> iterator = propertyNames.iterator();

		return iterator.next();
	}

	protected String getFirstSystemPropertyKey() {
		Properties systemProperties = SystemProperties.getProperties();

		Set<String> propertyNames = systemProperties.stringPropertyNames();

		Assert.assertFalse(propertyNames.toString(), propertyNames.isEmpty());

		Iterator<String> iterator = propertyNames.iterator();

		return iterator.next();
	}

	@Override
	protected VerifyProperties getVerifyProcess() {
		return new VerifyProperties();
	}

	private <T> T _setPropertyKeys(String fieldName, T value) {
		T orignalValue = ReflectionTestUtil.getFieldValue(
			VerifyProperties.class, fieldName);

		ReflectionTestUtil.setFieldValue(
			VerifyProperties.class, fieldName, value);

		return orignalValue;
	}

}