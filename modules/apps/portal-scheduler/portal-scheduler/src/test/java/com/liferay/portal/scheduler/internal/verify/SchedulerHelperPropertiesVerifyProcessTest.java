/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.internal.verify;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.scheduler.internal.configuration.SchedulerEngineHelperConfiguration;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;
import java.util.Dictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Michael C. Han
 */
@RunWith(MockitoJUnitRunner.class)
public class SchedulerHelperPropertiesVerifyProcessTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNoVerify() throws Exception {
		SchedulerHelperPropertiesVerifyProcess
			schedulerHelperPropertiesVerifyProcess =
				new SchedulerHelperPropertiesVerifyProcess();

		schedulerHelperPropertiesVerifyProcess.props = PropsTestUtil.setProps(
			Collections.emptyMap());

		ConfigurationAdmin configurationAdmin = Mockito.mock(
			ConfigurationAdmin.class);

		schedulerHelperPropertiesVerifyProcess.configurationAdmin =
			configurationAdmin;

		Mockito.when(
			configurationAdmin.getConfiguration(
				SchedulerEngineHelperConfiguration.class.getName())
		).then(
			new Answer<Object>() {

				@Override
				public Object answer(InvocationOnMock invocationOnMock)
					throws Throwable {

					Assert.fail("No properties should have been verified");

					return null;
				}

			}
		);

		schedulerHelperPropertiesVerifyProcess.doVerify();
	}

	@Test
	public void testVerify() throws Exception {
		SchedulerHelperPropertiesVerifyProcess
			schedulerHelperPropertiesVerifyProcess =
				new SchedulerHelperPropertiesVerifyProcess();

		schedulerHelperPropertiesVerifyProcess.props = PropsTestUtil.setProps(
			SchedulerHelperPropertiesVerifyProcess.
				LEGACY_AUDIT_MESSAGE_SCHEDULER_JOB,
			"true");

		ConfigurationAdmin configurationAdmin = Mockito.mock(
			ConfigurationAdmin.class);

		schedulerHelperPropertiesVerifyProcess.configurationAdmin =
			configurationAdmin;

		Configuration configuration = Mockito.mock(Configuration.class);

		Mockito.when(
			configurationAdmin.getConfiguration(
				SchedulerEngineHelperConfiguration.class.getName(),
				StringPool.QUESTION)
		).thenReturn(
			configuration
		);

		schedulerHelperPropertiesVerifyProcess.doVerify();

		Mockito.verify(
			configuration
		).update(
			_argumentCaptor.capture()
		);

		Dictionary<String, Object> dictionary = _argumentCaptor.getValue();

		Assert.assertEquals(1, dictionary.size());

		Assert.assertEquals(
			Boolean.TRUE,
			dictionary.get(
				SchedulerHelperPropertiesVerifyProcess.
					AUDIT_SCHEDULER_JOB_ENABLED));
	}

	@Captor
	private ArgumentCaptor<Dictionary<String, Object>> _argumentCaptor;

}