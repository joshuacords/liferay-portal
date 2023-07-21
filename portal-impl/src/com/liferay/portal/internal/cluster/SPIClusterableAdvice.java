/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.internal.cluster;

import com.liferay.portal.kernel.aop.AopMethodInvocation;
import com.liferay.portal.kernel.aop.ChainableMethodAdvice;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.cluster.ClusterableInvokerUtil;
import com.liferay.portal.kernel.nio.intraband.rpc.IntrabandRPCUtil;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIUtil;

import java.io.Serializable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
public class SPIClusterableAdvice extends ChainableMethodAdvice {

	@Override
	public Object before(
			AopMethodInvocation aopMethodInvocation, Object[] arguments)
		throws Throwable {

		Clusterable clusterable = aopMethodInvocation.getAdviceMethodContext();

		if (!clusterable.onMaster()) {
			return null;
		}

		SPI spi = SPIUtil.getSPI();

		Future<Serializable> futureResult = IntrabandRPCUtil.execute(
			spi.getRegistrationReference(),
			new MethodHandlerProcessCallable<Serializable>(
				ClusterableInvokerUtil.createMethodHandler(
					clusterable.acceptor(), aopMethodInvocation.getThis(),
					aopMethodInvocation.getMethod(), arguments)));

		Object result = futureResult.get();

		Method method = aopMethodInvocation.getMethod();

		Class<?> returnType = method.getReturnType();

		if (returnType == void.class) {
			result = nullResult;
		}

		return result;
	}

	@Override
	public Object createMethodContext(
		Class<?> targetClass, Method method,
		Map<Class<? extends Annotation>, Annotation> annotations) {

		return annotations.get(Clusterable.class);
	}

	@Override
	protected void afterReturning(
			AopMethodInvocation aopMethodInvocation, Object[] arguments,
			Object result)
		throws Throwable {

		Clusterable clusterable = aopMethodInvocation.getAdviceMethodContext();

		SPI spi = SPIUtil.getSPI();

		IntrabandRPCUtil.execute(
			spi.getRegistrationReference(),
			new MethodHandlerProcessCallable<Serializable>(
				ClusterableInvokerUtil.createMethodHandler(
					clusterable.acceptor(), aopMethodInvocation.getThis(),
					aopMethodInvocation.getMethod(), arguments)));
	}

}