/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.test.util;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.bean.BeanPropertiesImpl;
import com.liferay.portal.kernel.bean.BeanProperties;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ResourceBundle;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author André de Oliveira
 */
public class DDMFixture {

	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		setUpBeanPropertiesUtil();
		setUpDDMStructureLocalServiceUtil();
		setUpLanguageUtil();

		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		setUpPortalClassLoaderUtil(classLoader);
		setUpResourceBundleUtil(classLoader);
	}

	public void tearDown() {
		tearDownBeanPropertiesUtil();
		tearDownLanguage();
		tearDownPortalClassLoaderUtil();
	}

	public void whenDDMStructureLocalServiceFetchStructure(
		DDMStructure ddmStructure) {

		Mockito.doReturn(
			ddmStructure
		).when(
			_ddmStructureLocalService
		).fetchStructure(
			ddmStructure.getStructureId()
		);
	}

	protected void setUpBeanPropertiesUtil() {
		_beanProperties = BeanPropertiesUtil.getBeanProperties();

		BeanPropertiesUtil beanPropertiesUtil = new BeanPropertiesUtil();

		beanPropertiesUtil.setBeanProperties(new BeanPropertiesImpl());
	}

	protected void setUpDDMStructureLocalServiceUtil() throws Exception {
		PowerMockito.spy(DDMStructureLocalServiceUtil.class);

		PowerMockito.doReturn(
			_ddmStructureLocalService
		).when(
			DDMStructureLocalServiceUtil.class, "getService"
		);
	}

	protected void setUpLanguageUtil() {
		_language = LanguageUtil.getLanguage();

		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(Mockito.mock(Language.class));
	}

	protected void setUpPortalClassLoaderUtil(ClassLoader classLoader) {
		_classLoader = PortalClassLoaderUtil.getClassLoader();

		PortalClassLoaderUtil.setClassLoader(classLoader);
	}

	protected void setUpResourceBundleUtil(ClassLoader classLoader) {
		PowerMockito.mockStatic(ResourceBundleUtil.class);

		ResourceBundle resourceBundle = Mockito.mock(ResourceBundle.class);

		PowerMockito.when(
			ResourceBundleUtil.getBundle(
				"content.Language", LocaleUtil.BRAZIL, classLoader)
		).thenReturn(
			resourceBundle
		);

		PowerMockito.when(
			ResourceBundleUtil.getBundle(
				"content.Language", LocaleUtil.US, classLoader)
		).thenReturn(
			resourceBundle
		);
	}

	protected void tearDownBeanPropertiesUtil() {
		BeanPropertiesUtil beanPropertiesUtil = new BeanPropertiesUtil();

		beanPropertiesUtil.setBeanProperties(_beanProperties);
	}

	protected void tearDownLanguage() {
		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(_language);
	}

	protected void tearDownPortalClassLoaderUtil() {
		PortalClassLoaderUtil.setClassLoader(_classLoader);
	}

	private BeanProperties _beanProperties;
	private ClassLoader _classLoader;

	@Mock
	private DDMStructureLocalService _ddmStructureLocalService;

	private Language _language;

}