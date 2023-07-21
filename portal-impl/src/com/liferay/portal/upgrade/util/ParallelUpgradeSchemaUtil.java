/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.util;

import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author Miguel Pastor
 */
public class ParallelUpgradeSchemaUtil {

	public static void execute(String... sqlFileNames) throws Exception {
		ExecutorService executorService =
			_portalExecutorManager.getPortalExecutor(
				ParallelUpgradeSchemaUtil.class.getName());

		List<Future<Void>> futures = new ArrayList<>(sqlFileNames.length);

		try {
			for (String sqlFileName : sqlFileNames) {
				futures.add(
					executorService.submit(
						new CallableSQLExecutor(sqlFileName)));
			}

			for (Future<Void> future : futures) {
				future.get();
			}
		}
		finally {
			executorService.shutdown();
		}
	}

	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, ParallelUpgradeSchemaUtil.class,
			"_portalExecutorManager", true);

	private static class CallableSQLExecutor implements Callable<Void> {

		@Override
		public Void call() throws Exception {
			DB db = DBManagerUtil.getDB();

			try (LoggingTimer loggingTimer = new LoggingTimer(_sqlFileName)) {
				db.runSQLTemplate(_sqlFileName, false);
			}

			return null;
		}

		private CallableSQLExecutor(String sqlFileName) {
			_sqlFileName = sqlFileName;
		}

		private final String _sqlFileName;

	}

}