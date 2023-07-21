/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.background.task;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.DDMStructureIndexer;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = "background.task.executor.class.name=com.liferay.dynamic.data.mapping.internal.background.task.DDMStructureIndexerBackgroundTaskExecutor",
	service = BackgroundTaskExecutor.class
)
public class DDMStructureIndexerBackgroundTaskExecutor
	extends BaseBackgroundTaskExecutor {

	public static String getBackgroundTaskName(long structureId) {
		return _BACKGROUND_TASK_NAME_PREFIX + structureId;
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return this;
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		Number structureIdNumber = (Number)taskContextMap.get("structureId");

		long structureId = structureIdNumber.longValue();

		DDMStructureIndexer structureIndexer = getDDMStructureIndexer(
			structureId);

		List<Long> ddmStructureIds = getChildrenStructureIds(structureId);

		structureIndexer.reindexDDMStructures(ddmStructureIds);

		return BackgroundTaskResult.SUCCESS;
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		return null;
	}

	@Override
	public int getIsolationLevel() {
		return BackgroundTaskConstants.ISOLATION_LEVEL_TASK_NAME;
	}

	@Override
	public boolean isSerial() {
		return true;
	}

	protected void getChildrenStructureIds(
			List<Long> structureIds, long parentStructureId)
		throws PortalException {

		List<DDMStructure> structures =
			_ddmStructureLocalService.getChildrenStructures(parentStructureId);

		for (DDMStructure structure : structures) {
			structureIds.add(structure.getStructureId());

			getChildrenStructureIds(structureIds, structure.getStructureId());
		}
	}

	protected List<Long> getChildrenStructureIds(long structureId)
		throws PortalException {

		List<Long> structureIds = new ArrayList<>();

		getChildrenStructureIds(structureIds, structureId);

		structureIds.add(0, structureId);

		return structureIds;
	}

	protected DDMStructureIndexer getDDMStructureIndexer(long structureId)
		throws PortalException {

		DDMStructure structure = _ddmStructureLocalService.getStructure(
			structureId);

		return _ddmStructureIndexerTracker.getDDMStructureIndexer(
			structure.getClassName());
	}

	@Reference(unbind = "-")
	protected void setDDMStructureIndexerTracker(
		DDMStructureIndexerTracker ddmStructureIndexerTracker) {

		_ddmStructureIndexerTracker = ddmStructureIndexerTracker;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	private static final String _BACKGROUND_TASK_NAME_PREFIX =
		"DDMStructureIndexerBackgroundTaskExecutor-";

	private DDMStructureIndexerTracker _ddmStructureIndexerTracker;
	private DDMStructureLocalService _ddmStructureLocalService;

}