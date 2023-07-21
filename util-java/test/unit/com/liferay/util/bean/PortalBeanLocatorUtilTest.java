/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.bean;

import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.BeanLocatorException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Pastor
 */
@RunWith(PowerMockRunner.class)
public class PortalBeanLocatorUtilTest extends PowerMockito {

	@After
	public void tearDown() {
		PortalBeanLocatorUtil.setBeanLocator(null);
	}

	@Test
	public void testBeanLocatorHasNotBeenSet() {
		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					PortalBeanLocatorUtil.class.getName(), Level.SEVERE)) {

			try {
				PortalBeanLocatorUtil.locate("beanName");

				Assert.fail();
			}
			catch (BeanLocatorException beanLocatorException) {
				Assert.assertEquals(
					"BeanLocator is not set",
					beanLocatorException.getMessage());

				List<LogRecord> logRecords = captureHandler.getLogRecords();

				Assert.assertEquals(
					logRecords.toString(), 1, logRecords.size());

				LogRecord logRecord = logRecords.get(0);

				Assert.assertEquals(
					"BeanLocator is null", logRecord.getMessage());
			}
		}
	}

	@Test
	public void testLocateExistingBean() {
		when(
			_beanLocator.locate("existingBean")
		).thenReturn(
			new String("existingBean")
		);

		PortalBeanLocatorUtil.setBeanLocator(_beanLocator);

		String bean = (String)PortalBeanLocatorUtil.locate("existingBean");

		Assert.assertNotNull(bean);
		Assert.assertEquals("existingBean", bean);

		Mockito.verify(_beanLocator, Mockito.times(1));
	}

	@Test
	public void testLocateNonexistingBean() {
		when(
			_beanLocator.locate("nonExistingBean")
		).thenReturn(
			null
		);

		PortalBeanLocatorUtil.setBeanLocator(_beanLocator);

		String bean = (String)PortalBeanLocatorUtil.locate("nonExistingBean");

		Assert.assertNull(bean);

		Mockito.verify(_beanLocator, Mockito.times(1));
	}

	@Mock
	private BeanLocator _beanLocator;

}