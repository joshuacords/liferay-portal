/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.transaction;

import com.liferay.portal.kernel.aop.AopMethodInvocation;
import com.liferay.portal.kernel.aop.ChainableMethodAdvice;
import com.liferay.portal.kernel.transaction.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.Map;

import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author Shuyang Zhou
 */
public class TransactionInterceptor extends ChainableMethodAdvice {

	public TransactionInterceptor(TransactionHandler transactionHandler) {
		_transactionHandler = transactionHandler;
	}

	@Override
	public TransactionAttributeAdapter createMethodContext(
		Class<?> targetClass, Method method,
		Map<Class<? extends Annotation>, Annotation> annotations) {

		Transactional transactional = (Transactional)annotations.get(
			Transactional.class);

		TransactionAttribute transactionAttribute =
			TransactionAttributeBuilder.build(transactional);

		if (transactionAttribute == null) {
			return null;
		}

		return new TransactionAttributeAdapter(transactionAttribute);
	}

	@Override
	public Object invoke(
			AopMethodInvocation aopMethodInvocation, Object[] arguments)
		throws Throwable {

		TransactionAttributeAdapter transactionAttributeAdapter =
			aopMethodInvocation.getAdviceMethodContext();

		TransactionStatusAdapter transactionStatusAdapter =
			_transactionHandler.start(transactionAttributeAdapter);

		Object returnValue = null;

		try {
			returnValue = aopMethodInvocation.proceed(arguments);
		}
		catch (Throwable throwable) {
			_transactionHandler.rollback(
				throwable, transactionAttributeAdapter,
				transactionStatusAdapter);
		}

		_transactionHandler.commit(
			transactionAttributeAdapter, transactionStatusAdapter);

		return returnValue;
	}

	private final TransactionHandler _transactionHandler;

}