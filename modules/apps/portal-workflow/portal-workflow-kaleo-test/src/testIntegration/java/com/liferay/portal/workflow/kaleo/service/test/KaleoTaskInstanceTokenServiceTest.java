/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jorge Díaz
 */
@RunWith(Arquillian.class)
public class KaleoTaskInstanceTokenServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testSearchCount() throws Exception {
		User user = null;

		try {
			user = UserTestUtil.addUser();

			RoleLocalServiceUtil.clearUserRoles(user.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(TestPropsValues.getCompanyId());
			serviceContext.setUserId(user.getUserId());

			int count = KaleoTaskInstanceTokenLocalServiceUtil.searchCount(
				RandomTestUtil.randomString(), RandomTestUtil.randomStrings(10),
				false, true, serviceContext);

			Assert.assertEquals(0, count);
		}
		finally {
			UserLocalServiceUtil.deleteUser(user);
		}
	}

}