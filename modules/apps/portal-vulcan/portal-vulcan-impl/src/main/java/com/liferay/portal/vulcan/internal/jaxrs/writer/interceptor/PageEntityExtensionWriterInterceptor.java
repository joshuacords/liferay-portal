/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.writer.interceptor;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.internal.jaxrs.extension.ExtendedEntity;
import com.liferay.portal.vulcan.jaxrs.context.ExtensionContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.IOException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * @author Javier de Arcos
 */
@Provider
public class PageEntityExtensionWriterInterceptor implements WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext)
		throws IOException {

		if (Page.class.isAssignableFrom(writerInterceptorContext.getType())) {
			ParameterizedType parameterizedType =
				(ParameterizedType)writerInterceptorContext.getGenericType();

			Type entityType = parameterizedType.getActualTypeArguments()[0];

			Optional.ofNullable(
				_providers.getContextResolver(
					ExtensionContext.class,
					writerInterceptorContext.getMediaType())
			).map(
				contextResolver -> contextResolver.getContext((Class)entityType)
			).ifPresent(
				extensionContext -> _extendPageEntities(
					extensionContext, writerInterceptorContext)
			);
		}

		writerInterceptorContext.proceed();
	}

	private void _extendPageEntities(
		ExtensionContext extensionContext,
		WriterInterceptorContext writerInterceptorContext) {

		Page<?> page = (Page<?>)writerInterceptorContext.getEntity();

		Collection<?> items = page.getItems();

		Stream<?> stream = items.stream();

		List<ExtendedEntity> extendedEntities = stream.map(
			entity -> ExtendedEntity.extend(
				entity, extensionContext.getExtendedProperties(entity),
				extensionContext.getFilteredPropertyKeys(entity))
		).collect(
			Collectors.toList()
		);

		Pagination pagination = Pagination.of(
			GetterUtil.getInteger(page.getPage()),
			GetterUtil.getInteger(page.getPageSize()));

		writerInterceptorContext.setEntity(
			Page.of(
				page.getActions(), extendedEntities, pagination,
				page.getTotalCount()));

		writerInterceptorContext.setGenericType(
			new GenericType<Page<ExtendedEntity>>() {
			}.getType());
	}

	@Context
	private Providers _providers;

}