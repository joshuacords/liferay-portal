/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.dto.v1_0.util;

import com.liferay.data.engine.field.type.util.LocalizedValueUtil;
import com.liferay.data.engine.rest.dto.v1_0.DataLayout;
import com.liferay.data.engine.rest.dto.v1_0.DataLayoutColumn;
import com.liferay.data.engine.rest.dto.v1_0.DataLayoutPage;
import com.liferay.data.engine.rest.dto.v1_0.DataLayoutRow;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Objects;

/**
 * @author Jeyvison Nascimento
 */
public class DataLayoutUtil {

	public static DataLayout toDataLayout(String json) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

		return new DataLayout() {
			{
				dataLayoutPages = JSONUtil.toArray(
					jsonObject.getJSONArray("pages"),
					pageJSONObject -> _toDataLayoutPage(pageJSONObject),
					DataLayoutPage.class);
				paginationMode = jsonObject.getString("paginationMode");
			}
		};
	}

	public static String toJSON(DataLayout dataLayout) throws Exception {
		if (!Objects.equals(dataLayout.getPaginationMode(), "pagination") &&
			!Objects.equals(dataLayout.getPaginationMode(), "wizard")) {

			throw new Exception(
				"Pagination mode must be \"pagination\" or \"wizard\"");
		}

		return JSONUtil.put(
			"pages",
			JSONUtil.toJSONArray(
				dataLayout.getDataLayoutPages(),
				dataLayoutPage -> _toJSONObject(dataLayoutPage))
		).put(
			"paginationMode", dataLayout.getPaginationMode()
		).toString();
	}

	private static DataLayoutColumn _toDataLayoutColumn(JSONObject jsonObject) {
		return new DataLayoutColumn() {
			{
				columnSize = jsonObject.getInt("columnSize");
				fieldNames = JSONUtil.toStringArray(
					jsonObject.getJSONArray("fieldNames"));
			}
		};
	}

	private static DataLayoutPage _toDataLayoutPage(JSONObject jsonObject)
		throws Exception {

		return new DataLayoutPage() {
			{
				dataLayoutRows = JSONUtil.toArray(
					jsonObject.getJSONArray("rows"),
					rowJSONObject -> _toDataLayoutRow(rowJSONObject),
					DataLayoutRow.class);
				description = LocalizedValueUtil.toLocalizedValues(
					jsonObject.getJSONObject("description"));
				title = LocalizedValueUtil.toLocalizedValues(
					jsonObject.getJSONObject("title"));
			}
		};
	}

	private static DataLayoutRow _toDataLayoutRow(JSONObject jsonObject)
		throws Exception {

		return new DataLayoutRow() {
			{
				dataLayoutColumns = JSONUtil.toArray(
					jsonObject.getJSONArray("columns"),
					columnJSONObject -> _toDataLayoutColumn(columnJSONObject),
					DataLayoutColumn.class);
			}
		};
	}

	private static JSONObject _toJSONObject(DataLayoutColumn dataLayoutColumn)
		throws Exception {

		return JSONUtil.put(
			"columnSize", dataLayoutColumn.getColumnSize()
		).put(
			"fieldNames", JSONUtil.put(dataLayoutColumn.getFieldNames())
		);
	}

	private static JSONObject _toJSONObject(DataLayoutPage dataLayoutPage)
		throws Exception {

		if (MapUtil.isEmpty(dataLayoutPage.getTitle())) {
			throw new Exception("Title is required");
		}

		return JSONUtil.put(
			"description",
			LocalizedValueUtil.toJSONObject(dataLayoutPage.getDescription())
		).put(
			"rows",
			JSONUtil.toJSONArray(
				dataLayoutPage.getDataLayoutRows(),
				dataLayoutRow -> _toJSONObject(dataLayoutRow))
		).put(
			"title", LocalizedValueUtil.toJSONObject(dataLayoutPage.getTitle())
		);
	}

	private static JSONObject _toJSONObject(DataLayoutRow dataLayoutRow)
		throws Exception {

		return JSONUtil.put(
			"columns",
			JSONUtil.toJSONArray(
				dataLayoutRow.getDataLayoutColumns(),
				dataLayoutColumn -> _toJSONObject(dataLayoutColumn)));
	}

}