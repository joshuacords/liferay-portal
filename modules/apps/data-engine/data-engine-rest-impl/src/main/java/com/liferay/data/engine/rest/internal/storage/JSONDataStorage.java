/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.storage;

import com.liferay.data.engine.field.type.FieldTypeTracker;
import com.liferay.data.engine.rest.dto.v1_0.DataRecord;
import com.liferay.data.engine.rest.dto.v1_0.DataRecordCollection;
import com.liferay.data.engine.rest.internal.dto.v1_0.util.DataDefinitionUtil;
import com.liferay.data.engine.rest.internal.dto.v1_0.util.DataRecordCollectionUtil;
import com.liferay.data.engine.rest.internal.dto.v1_0.util.DataRecordValuesUtil;
import com.liferay.data.engine.storage.DataStorage;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jeyvison Nascimento
 */
@Component(
	immediate = true, property = "data.storage.type=json",
	service = DataStorage.class
)
public class JSONDataStorage implements DataStorage {

	@Override
	public long delete(long dataStorageId) throws Exception {
		DDMContent ddmContent = _ddmContentLocalService.fetchDDMContent(
			dataStorageId);

		if (ddmContent != null) {
			_ddmContentLocalService.deleteDDMContent(ddmContent);
		}

		return dataStorageId;
	}

	@Override
	public Map<String, Object> get(long dataDefinitionId, long dataStorageId)
		throws Exception {

		DDMContent ddmContent = _ddmContentLocalService.getContent(
			dataStorageId);

		return DataRecordValuesUtil.toDataRecordValues(
			DataDefinitionUtil.toDataDefinition(
				_ddmStructureLocalService.getStructure(dataDefinitionId),
				_fieldTypeTracker),
			ddmContent.getData());
	}

	@Override
	public long save(
			long dataRecordCollectionId, Map<String, Object> dataRecordValues,
			long siteId)
		throws Exception {

		DataRecordCollection dataRecordCollection =
			DataRecordCollectionUtil.toDataRecordCollection(
				_ddlRecordSetLocalService.getRecordSet(dataRecordCollectionId));

		DDMContent ddmContent = _ddmContentLocalService.addContent(
			PrincipalThreadLocal.getUserId(), siteId,
			DataRecord.class.getName(), null,
			DataRecordValuesUtil.toJSON(
				DataDefinitionUtil.toDataDefinition(
					_ddmStructureLocalService.getStructure(
						dataRecordCollection.getDataDefinitionId()),
					_fieldTypeTracker),
				dataRecordValues),
			new ServiceContext() {
				{
					setScopeGroupId(siteId);
					setUserId(PrincipalThreadLocal.getUserId());
				}
			});

		return ddmContent.getPrimaryKey();
	}

	@Reference
	private DDLRecordSetLocalService _ddlRecordSetLocalService;

	@Reference
	private DDMContentLocalService _ddmContentLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private FieldTypeTracker _fieldTypeTracker;

}