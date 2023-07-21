/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.upgrade.v1_0_2.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.test.util.CalendarTestUtil;
import com.liferay.calendar.test.util.CalendarUpgradeTestUtil;
import com.liferay.calendar.test.util.UpgradeDatabaseTestHelper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
public class UpgradeCalendarTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_upgradeDatabaseTestHelper =
			CalendarUpgradeTestUtil.getUpgradeDatabaseTestHelper();
		_upgradeProcess = CalendarUpgradeTestUtil.getServiceUpgradeStep(
			"com.liferay.calendar.internal.upgrade.v1_0_2.UpgradeCalendar");
		_user = UserTestUtil.addUser();
	}

	@After
	public void tearDown() throws Exception {
		_upgradeDatabaseTestHelper.close();
	}

	@Test
	public void testUpgradeCreatesCalendarTimeZoneId() throws Exception {
		_upgradeProcess.upgrade();

		assertHasColumn("timeZoneId");
	}

	@Test
	public void testUpgradeSetsSiteCalendarTimeZoneId() throws Exception {
		Calendar calendar = CalendarTestUtil.addCalendar(_group);

		_upgradeProcess.upgrade();

		assertCalendarTimeZoneId(
			calendar, PropsUtil.get(PropsKeys.COMPANY_DEFAULT_TIME_ZONE));
	}

	@Test
	public void testUpgradeSetsUserCalendarTimeZoneId() throws Exception {
		setUserTimeZoneId("Asia/Shangai");

		Calendar calendar = CalendarTestUtil.addCalendar(_user);

		_upgradeProcess.upgrade();

		assertCalendarTimeZoneId(calendar, "Asia/Shangai");
	}

	protected void assertCalendarTimeZoneId(
			Calendar calendar, String timeZoneId)
		throws PortalException {

		EntityCacheUtil.clearCache();

		calendar = CalendarLocalServiceUtil.getCalendar(
			calendar.getCalendarId());

		Assert.assertEquals(timeZoneId, calendar.getTimeZoneId());
	}

	protected void assertHasColumn(String columnName) throws Exception {
		Assert.assertTrue(
			_upgradeDatabaseTestHelper.hasColumn("Calendar", columnName));
	}

	protected void setUserTimeZoneId(String timeZoneId) {
		_user.setTimeZoneId(timeZoneId);

		UserLocalServiceUtil.updateUser(_user);
	}

	@DeleteAfterTestRun
	private Group _group;

	private UpgradeDatabaseTestHelper _upgradeDatabaseTestHelper;
	private UpgradeProcess _upgradeProcess;

	@DeleteAfterTestRun
	private User _user;

}