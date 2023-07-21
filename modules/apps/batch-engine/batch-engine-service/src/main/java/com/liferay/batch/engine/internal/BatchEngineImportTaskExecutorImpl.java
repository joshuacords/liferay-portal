/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal;

import com.liferay.batch.engine.BatchEngineImportTaskExecutor;
import com.liferay.batch.engine.BatchEngineTaskContentType;
import com.liferay.batch.engine.BatchEngineTaskExecuteStatus;
import com.liferay.batch.engine.BatchEngineTaskOperation;
import com.liferay.batch.engine.configuration.BatchEngineTaskConfiguration;
import com.liferay.batch.engine.internal.item.BatchEngineTaskItemDelegateExecutor;
import com.liferay.batch.engine.internal.item.BatchEngineTaskItemDelegateExecutorFactory;
import com.liferay.batch.engine.internal.reader.BatchEngineImportTaskItemReader;
import com.liferay.batch.engine.internal.reader.BatchEngineImportTaskItemReaderFactory;
import com.liferay.batch.engine.internal.reader.BatchEngineImportTaskItemReaderUtil;
import com.liferay.batch.engine.model.BatchEngineImportTask;
import com.liferay.batch.engine.service.BatchEngineImportTaskLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	configurationPid = "com.liferay.batch.engine.configuration.BatchEngineTaskConfiguration",
	service = BatchEngineImportTaskExecutor.class
)
public class BatchEngineImportTaskExecutorImpl
	implements BatchEngineImportTaskExecutor {

	@Override
	public void execute(BatchEngineImportTask batchEngineImportTask) {
		try {
			batchEngineImportTask.setExecuteStatus(
				BatchEngineTaskExecuteStatus.STARTED.toString());
			batchEngineImportTask.setStartTime(new Date());

			_batchEngineImportTaskLocalService.updateBatchEngineImportTask(
				batchEngineImportTask);

			BatchEngineTaskExecutorUtil.execute(
				() -> _importItems(batchEngineImportTask),
				_userLocalService.getUser(batchEngineImportTask.getUserId()));

			_updateBatchEngineImportTask(
				BatchEngineTaskExecuteStatus.COMPLETED, batchEngineImportTask,
				null);
		}
		catch (Throwable t) {
			_log.error(
				"Unable to update batch engine import task " +
					batchEngineImportTask,
				t);

			_updateBatchEngineImportTask(
				BatchEngineTaskExecuteStatus.FAILED, batchEngineImportTask,
				t.getMessage());
		}
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		BatchEngineTaskConfiguration batchEngineTaskConfiguration =
			ConfigurableUtil.createConfigurable(
				BatchEngineTaskConfiguration.class, properties);

		_batchEngineImportTaskItemReaderFactory =
			new BatchEngineImportTaskItemReaderFactory(
				GetterUtil.getString(
					batchEngineTaskConfiguration.csvFileColumnDelimiter(),
					StringPool.COMMA));

		_batchEngineTaskItemDelegateExecutorFactory =
			new BatchEngineTaskItemDelegateExecutorFactory(
				_batchEngineTaskMethodRegistry, null, null, null);
	}

	private void _commitItems(
			BatchEngineImportTask batchEngineImportTask,
			BatchEngineTaskItemDelegateExecutor
				batchEngineTaskItemDelegateExecutor,
			List<Object> items)
		throws Throwable {

		TransactionInvokerUtil.invoke(
			_transactionConfig,
			() -> {
				batchEngineTaskItemDelegateExecutor.saveItems(
					BatchEngineTaskOperation.valueOf(
						batchEngineImportTask.getOperation()),
					items);

				_batchEngineImportTaskLocalService.updateBatchEngineImportTask(
					batchEngineImportTask);

				return null;
			});
	}

	private void _importItems(BatchEngineImportTask batchEngineImportTask)
		throws Throwable {

		try (BatchEngineImportTaskItemReader batchEngineImportTaskItemReader =
				_batchEngineImportTaskItemReaderFactory.create(
					BatchEngineTaskContentType.valueOf(
						batchEngineImportTask.getContentType()),
					_batchEngineImportTaskLocalService.openContentInputStream(
						batchEngineImportTask.getBatchEngineImportTaskId()));
			BatchEngineTaskItemDelegateExecutor
				batchEngineTaskItemDelegateExecutor =
					_batchEngineTaskItemDelegateExecutorFactory.create(
						batchEngineImportTask.getTaskItemDelegateName(),
						batchEngineImportTask.getClassName(),
						_companyLocalService.getCompany(
							batchEngineImportTask.getCompanyId()),
						batchEngineImportTask.getParameters(),
						_userLocalService.getUser(
							batchEngineImportTask.getUserId()))) {

			List<Object> items = new ArrayList<>();

			Class<?> itemClass = _batchEngineTaskMethodRegistry.getItemClass(
				batchEngineImportTask.getClassName());

			Map<String, Object> fieldNameValueMap = null;

			while ((fieldNameValueMap =
						batchEngineImportTaskItemReader.read()) != null) {

				if (Thread.interrupted()) {
					throw new InterruptedException();
				}

				items.add(
					BatchEngineImportTaskItemReaderUtil.convertValue(
						itemClass,
						BatchEngineImportTaskItemReaderUtil.mapFieldNames(
							batchEngineImportTask.getFieldNameMapping(),
							fieldNameValueMap)));

				if (items.size() == batchEngineImportTask.getBatchSize()) {
					_commitItems(
						batchEngineImportTask,
						batchEngineTaskItemDelegateExecutor, items);

					items.clear();
				}
			}

			if (!items.isEmpty()) {
				_commitItems(
					batchEngineImportTask, batchEngineTaskItemDelegateExecutor,
					items);
			}
		}
	}

	private void _updateBatchEngineImportTask(
		BatchEngineTaskExecuteStatus batchEngineTaskExecuteStatus,
		BatchEngineImportTask batchEngineImportTask, String errorMessage) {

		batchEngineImportTask.setEndTime(new Date());
		batchEngineImportTask.setErrorMessage(errorMessage);
		batchEngineImportTask.setExecuteStatus(
			batchEngineTaskExecuteStatus.toString());

		_batchEngineImportTaskLocalService.updateBatchEngineImportTask(
			batchEngineImportTask);

		BatchEngineTaskCallbackUtil.sendCallback(
			batchEngineImportTask.getCallbackURL(),
			batchEngineImportTask.getExecuteStatus(),
			batchEngineImportTask.getBatchEngineImportTaskId());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BatchEngineImportTaskExecutorImpl.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRES_NEW, new Class<?>[] {Exception.class});

	private BatchEngineImportTaskItemReaderFactory
		_batchEngineImportTaskItemReaderFactory;

	@Reference
	private BatchEngineImportTaskLocalService
		_batchEngineImportTaskLocalService;

	private BatchEngineTaskItemDelegateExecutorFactory
		_batchEngineTaskItemDelegateExecutorFactory;

	@Reference
	private BatchEngineTaskMethodRegistry _batchEngineTaskMethodRegistry;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private UserLocalService _userLocalService;

}