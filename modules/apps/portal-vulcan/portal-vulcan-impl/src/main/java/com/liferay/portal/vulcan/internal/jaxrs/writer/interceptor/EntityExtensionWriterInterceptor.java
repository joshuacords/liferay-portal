/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.writer.interceptor;

import com.liferay.portal.vulcan.internal.jaxrs.extension.ExtendedEntity;
import com.liferay.portal.vulcan.jaxrs.context.ExtensionContext;

import java.io.IOException;

import java.util.Optional;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * @author Javier de Arcos
 */
@Provider
public class EntityExtensionWriterInterceptor implements WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext)
		throws IOException {

		Optional.ofNullable(
			_providers.getContextResolver(
				ExtensionContext.class, writerInterceptorContext.getMediaType())
		).map(
			contextResolver -> contextResolver.getContext(
				writerInterceptorContext.getType())
		).ifPresent(
			extensionContext -> _extendEntity(
				extensionContext, writerInterceptorContext)
		);

		writerInterceptorContext.proceed();
	}

	private void _extendEntity(
		ExtensionContext extensionContext,
		WriterInterceptorContext writerInterceptorContext) {

		writerInterceptorContext.setEntity(
			ExtendedEntity.extend(
				writerInterceptorContext.getEntity(),
				extensionContext.getExtendedProperties(
					writerInterceptorContext.getEntity()),
				extensionContext.getFilteredPropertyKeys(
					writerInterceptorContext.getEntity())));

		writerInterceptorContext.setGenericType(ExtendedEntity.class);
	}

	@Context
	private Providers _providers;

}