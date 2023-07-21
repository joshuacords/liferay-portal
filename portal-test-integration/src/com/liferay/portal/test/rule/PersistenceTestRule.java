/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.rule;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ModelListenerRegistrationUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AbstractTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.test.ServiceTestUtil;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.runner.Description;

/**
 * @author Shuyang Zhou
 */
public class PersistenceTestRule extends AbstractTestRule<Object, Object> {

	public static final PersistenceTestRule INSTANCE =
		new PersistenceTestRule();

	@Override
	public void afterMethod(
		Description description, Object modelListeners, Object target) {

		Object instance = ReflectionTestUtil.getFieldValue(
			ModelListenerRegistrationUtil.class,
			"_modelListenerRegistrationUtil");

		CacheRegistryUtil.setActive(true);

		ReflectionTestUtil.setFieldValue(
			instance, "_modelListeners", modelListeners);
	}

	@Override
	public Object beforeClass(Description description) {
		return null;
	}

	@Override
	public Object beforeMethod(Description description, Object target)
		throws Exception {

		Object instance = ReflectionTestUtil.getFieldValue(
			ModelListenerRegistrationUtil.class,
			"_modelListenerRegistrationUtil");

		Object modelListeners = ReflectionTestUtil.getFieldValue(
			instance, "_modelListeners");

		ReflectionTestUtil.setFieldValue(
			instance, "_modelListeners",
			new ConcurrentHashMap<Class<?>, List<ModelListener<?>>>());

		CacheRegistryUtil.setActive(false);

		ServiceTestUtil.setUser(TestPropsValues.getUser());

		return modelListeners;
	}

	@Override
	protected void afterClass(Description description, Object object) {
	}

	private PersistenceTestRule() {
	}

}