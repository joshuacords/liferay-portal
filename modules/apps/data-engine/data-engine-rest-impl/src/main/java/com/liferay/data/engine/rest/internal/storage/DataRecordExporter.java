/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.storage;

import com.liferay.data.engine.field.type.FieldTypeTracker;
import com.liferay.data.engine.rest.dto.v1_0.DataDefinition;
import com.liferay.data.engine.rest.dto.v1_0.DataRecord;
import com.liferay.data.engine.rest.internal.dto.v1_0.util.DataDefinitionUtil;
import com.liferay.data.engine.rest.internal.dto.v1_0.util.DataRecordValuesUtil;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Jeyvison Nascimento
 */
public class DataRecordExporter {

	public DataRecordExporter(
		DDLRecordSetLocalService ddlRecordSetLocalService,
		FieldTypeTracker fieldTypeTracker) {

		_ddlRecordSetLocalService = ddlRecordSetLocalService;
		_fieldTypeTracker = fieldTypeTracker;
	}

	public String export(List<DataRecord> dataRecords) throws Exception {
		if (ListUtil.isEmpty(dataRecords)) {
			return StringPool.BLANK;
		}

		DataRecord dataRecord = dataRecords.get(0);

		DDLRecordSet ddlRecordSet = _ddlRecordSetLocalService.getRecordSet(
			dataRecord.getDataRecordCollectionId());

		DataDefinition dataDefinition = DataDefinitionUtil.toDataDefinition(
			ddlRecordSet.getDDMStructure(), _fieldTypeTracker);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Stream<DataRecord> stream = dataRecords.parallelStream();

		stream.map(
			record -> _toJSON(dataDefinition, record)
		).forEach(
			jsonArray::put
		);

		return jsonArray.toString();
	}

	private String _toJSON(
		DataDefinition dataDefinition, DataRecord dataRecord) {

		try {
			return DataRecordValuesUtil.toJSON(
				dataDefinition, dataRecord.getDataRecordValues());
		}
		catch (Exception exception) {
			return StringPool.BLANK;
		}
	}

	private final DDLRecordSetLocalService _ddlRecordSetLocalService;
	private final FieldTypeTracker _fieldTypeTracker;

}