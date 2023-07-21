/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.writer.interceptor;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.vulcan.internal.jaxrs.extension.ExtendedEntity;
import com.liferay.portal.vulcan.internal.jaxrs.util.JAXRSExtensionContextUtil;

import java.io.IOException;

import javax.ws.rs.ext.WriterInterceptorContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * @author Javier de Arcos
 */
public class EntityExtensionWriterInterceptorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_entityExtensionWriterInterceptor =
			new EntityExtensionWriterInterceptor();
		_mockedWriterInterceptorContext = Mockito.mock(
			WriterInterceptorContext.class);

		ReflectionTestUtil.setFieldValue(
			_entityExtensionWriterInterceptor, "_providers",
			JAXRSExtensionContextUtil.getTestProviders());
	}

	@Test
	public void testAroundWriteWithExtensionContextWithExtendedType()
		throws IOException {

		JAXRSExtensionContextUtil.TestObject testObject =
			JAXRSExtensionContextUtil.getTestObject();

		ArgumentCaptor<ExtendedEntity> argumentCaptor = ArgumentCaptor.forClass(
			ExtendedEntity.class);

		Mockito.when(
			_mockedWriterInterceptorContext.getEntity()
		).thenReturn(
			testObject
		);

		Mockito.when(
			_mockedWriterInterceptorContext.getType()
		).thenReturn(
			(Class)testObject.getClass()
		);

		_entityExtensionWriterInterceptor.aroundWriteTo(
			_mockedWriterInterceptorContext);

		Mockito.verify(
			_mockedWriterInterceptorContext
		).setEntity(
			argumentCaptor.capture()
		);

		ExtendedEntity extendedEntity = argumentCaptor.getValue();

		Assert.assertEquals(testObject, extendedEntity.getEntity());
		Assert.assertEquals(
			JAXRSExtensionContextUtil.getTestExtendedProperties(),
			extendedEntity.getExtendedProperties());

		Mockito.verify(
			_mockedWriterInterceptorContext
		).setGenericType(
			Matchers.eq(ExtendedEntity.class)
		);

		Mockito.verify(
			_mockedWriterInterceptorContext
		).proceed();
	}

	@Test
	public void testAroundWriteWithExtensionContextWithNoExtendedType()
		throws IOException {

		Mockito.when(
			_mockedWriterInterceptorContext.getEntity()
		).thenReturn(
			new Object()
		);

		Mockito.when(
			_mockedWriterInterceptorContext.getType()
		).thenReturn(
			(Class)Object.class
		);

		_entityExtensionWriterInterceptor.aroundWriteTo(
			_mockedWriterInterceptorContext);

		Mockito.verify(
			_mockedWriterInterceptorContext, Mockito.never()
		).setEntity(
			Matchers.any()
		);

		Mockito.verify(
			_mockedWriterInterceptorContext, Mockito.never()
		).setGenericType(
			Matchers.any()
		);

		Mockito.verify(
			_mockedWriterInterceptorContext
		).proceed();
	}

	@Test
	public void testAroundWriteWithoutExtensionContextResolver()
		throws IOException {

		ReflectionTestUtil.setFieldValue(
			_entityExtensionWriterInterceptor, "_providers",
			JAXRSExtensionContextUtil.getNoContextResolverProviders());

		_entityExtensionWriterInterceptor.aroundWriteTo(
			_mockedWriterInterceptorContext);

		Mockito.verify(
			_mockedWriterInterceptorContext, Mockito.never()
		).setEntity(
			Matchers.any()
		);

		Mockito.verify(
			_mockedWriterInterceptorContext, Mockito.never()
		).setGenericType(
			Matchers.any()
		);

		Mockito.verify(
			_mockedWriterInterceptorContext
		).proceed();
	}

	private EntityExtensionWriterInterceptor _entityExtensionWriterInterceptor;
	private WriterInterceptorContext _mockedWriterInterceptorContext;

}