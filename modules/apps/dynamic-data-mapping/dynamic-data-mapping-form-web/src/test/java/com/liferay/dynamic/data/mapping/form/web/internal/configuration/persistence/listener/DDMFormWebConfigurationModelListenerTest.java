/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.web.internal.configuration.persistence.listener;

import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListenerException;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.util.PropsImpl;

import java.util.Dictionary;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Pedro Queiroz
 */
@PrepareForTest(ResourceBundleUtil.class)
@RunWith(PowerMockRunner.class)
public class DDMFormWebConfigurationModelListenerTest extends PowerMockito {

	@Before
	public void setUp() {
		_setUpDDMFormWebConfigurationModelListener();
		_setUpPropsUtil();
		_setUpResourceBundleUtil();
	}

	@Test(expected = ConfigurationModelListenerException.class)
	public void testNegativeAutosaveIntervalShouldThrowException()
		throws ConfigurationModelListenerException {

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("autosaveInterval", "-1");

		_ddmFormWebConfigurationModelListener.onBeforeSave(null, properties);
	}

	private void _setUpDDMFormWebConfigurationModelListener() {
		_ddmFormWebConfigurationModelListener =
			new DDMFormWebConfigurationModelListener();
	}

	private void _setUpPropsUtil() {
		PropsUtil.setProps(new PropsImpl());
	}

	private void _setUpResourceBundleUtil() {
		PowerMockito.mockStatic(ResourceBundleUtil.class);

		PowerMockito.when(
			ResourceBundleUtil.getBundle(
				Matchers.anyString(), Matchers.any(Locale.class),
				Matchers.any(ClassLoader.class))
		).thenReturn(
			ResourceBundleUtil.EMPTY_RESOURCE_BUNDLE
		);
	}

	private DDMFormWebConfigurationModelListener
		_ddmFormWebConfigurationModelListener;

}