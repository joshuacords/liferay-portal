/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.service.impl;

import com.liferay.batch.engine.model.BatchEngineImportTask;
import com.liferay.batch.engine.service.base.BatchEngineImportTaskLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(
	property = "model.class.name=com.liferay.batch.engine.model.BatchEngineImportTask",
	service = AopService.class
)
public class BatchEngineImportTaskLocalServiceImpl
	extends BatchEngineImportTaskLocalServiceBaseImpl {

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public BatchEngineImportTask addBatchEngineImportTask(
		long companyId, long userId, long batchSize, String callbackURL,
		String className, byte[] content, String contentType,
		String executeStatus, Map<String, String> fieldNameMappingMap,
		String operation, Map<String, Serializable> parameters,
		String taskItemDelegateName) {

		BatchEngineImportTask batchEngineImportTask =
			batchEngineImportTaskPersistence.create(
				counterLocalService.increment(
					BatchEngineImportTask.class.getName()));

		batchEngineImportTask.setCompanyId(companyId);
		batchEngineImportTask.setUserId(userId);
		batchEngineImportTask.setBatchSize(batchSize);
		batchEngineImportTask.setCallbackURL(callbackURL);
		batchEngineImportTask.setClassName(className);
		batchEngineImportTask.setContent(
			new OutputBlob(
				new UnsyncByteArrayInputStream(content), content.length));
		batchEngineImportTask.setContentType(contentType);
		batchEngineImportTask.setExecuteStatus(executeStatus);

		if ((fieldNameMappingMap != null) && !fieldNameMappingMap.isEmpty()) {
			batchEngineImportTask.setFieldNameMapping((Map)fieldNameMappingMap);
		}

		batchEngineImportTask.setOperation(operation);

		if ((parameters != null) && !parameters.isEmpty()) {
			batchEngineImportTask.setParameters(parameters);
		}

		batchEngineImportTask.setTaskItemDelegateName(taskItemDelegateName);

		return batchEngineImportTaskPersistence.update(batchEngineImportTask);
	}

	@Override
	public List<BatchEngineImportTask> getBatchEngineImportTasks(
		String executeStatus) {

		return batchEngineImportTaskPersistence.findByExecuteStatus(
			executeStatus);
	}

}