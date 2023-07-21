/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapperTracker;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.model.impl.PortletImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portlet.internal.FriendlyURLMapperTrackerImpl;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.Collections;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leon Chi
 */
public class FriendlyURLMapperTrackerImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetFriendlyURLMapper() throws Exception {
		RegistryUtil.setRegistry(new BasicRegistryImpl());

		Registry registry = RegistryUtil.getRegistry();

		Portlet portlet = new PortletImpl();

		portlet.setPortletClass(MVCPortlet.class.getName());
		portlet.setPortletId(_PORTLET_NAME);

		FriendlyURLMapperTracker friendlyURLMapperTracker =
			new FriendlyURLMapperTrackerImpl(portlet);

		FriendlyURLMapper friendlyURLMapper = ProxyFactory.newDummyInstance(
			FriendlyURLMapper.class);

		ServiceRegistration<FriendlyURLMapper> serviceRegistration =
			registry.registerService(
				FriendlyURLMapper.class, friendlyURLMapper,
				Collections.singletonMap("javax.portlet.name", _PORTLET_NAME));

		try {
			Assert.assertSame(
				friendlyURLMapper,
				friendlyURLMapperTracker.getFriendlyURLMapper());
		}
		finally {
			serviceRegistration.unregister();
		}
	}

	private static final String _PORTLET_NAME =
		"FriendlyURLMapperTrackerImplTest";

}