/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.storage;

import com.liferay.data.engine.storage.DataStorage;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Map;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Jeyvison Nascimento
 */
@Component(immediate = true, service = DataStorageTracker.class)
public class DataStorageTracker {

	public DataStorage getDataStorage(String dataStorageType) {
		return _dataStorages.get(dataStorageType);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addDataStorage(
		DataStorage dataStorage, Map<String, Object> properties) {

		String dataStorageType = MapUtil.getString(
			properties, "data.storage.type");

		_dataStorages.put(dataStorageType, dataStorage);
	}

	@Deactivate
	protected void deactivate() {
		_dataStorages.clear();
	}

	protected void removeDataStorage(
		DataStorage dataStorage, Map<String, Object> properties) {

		String dataStorageType = MapUtil.getString(
			properties, "data.storage.type");

		_dataStorages.remove(dataStorageType);
	}

	private final Map<String, DataStorage> _dataStorages = new TreeMap<>();

}