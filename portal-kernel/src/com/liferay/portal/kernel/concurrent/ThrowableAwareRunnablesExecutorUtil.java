/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.concurrent;

import com.liferay.portal.kernel.exception.BulkException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ThrowableAwareRunnablesExecutorUtil {

	public static void execute(
			Collection<? extends ThrowableAwareRunnable>
				throwableAwareRunnables)
		throws Exception {

		ExecutorService executorService = Executors.newFixedThreadPool(
			throwableAwareRunnables.size());

		List<Callable<Object>> jobs = new ArrayList<>(
			throwableAwareRunnables.size());

		for (ThrowableAwareRunnable throwableAwareRunnable :
				throwableAwareRunnables) {

			jobs.add(Executors.callable(throwableAwareRunnable));
		}

		try {
			List<Future<Object>> futures = executorService.invokeAll(jobs);

			for (Future<Object> future : futures) {
				future.get();
			}
		}
		finally {
			executorService.shutdown();
		}

		List<Throwable> throwables = new ArrayList<>();

		for (ThrowableAwareRunnable throwableAwareRunnable :
				throwableAwareRunnables) {

			if (throwableAwareRunnable.hasException()) {
				throwables.add(throwableAwareRunnable.getThrowable());
			}
		}

		if (!throwables.isEmpty()) {
			throw new BulkException(throwables);
		}
	}

}