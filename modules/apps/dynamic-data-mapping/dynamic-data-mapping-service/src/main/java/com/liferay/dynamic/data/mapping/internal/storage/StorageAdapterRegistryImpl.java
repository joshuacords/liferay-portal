/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.storage;

import com.liferay.dynamic.data.mapping.storage.StorageAdapter;
import com.liferay.dynamic.data.mapping.storage.StorageAdapterRegistry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = StorageAdapterRegistry.class)
public class StorageAdapterRegistryImpl implements StorageAdapterRegistry {

	@Override
	public StorageAdapter getDefaultStorageAdapter() {
		return _defaultStorageAdapter;
	}

	@Override
	public StorageAdapter getStorageAdapter(String storageType) {
		return _storageAdaptersMap.get(storageType);
	}

	@Override
	public Set<String> getStorageTypes() {
		return _storageAdaptersMap.keySet();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		service = StorageAdapter.class
	)
	public void setStorageAdapter(StorageAdapter storageAdapter) {
		_storageAdaptersMap.put(
			storageAdapter.getStorageType(), storageAdapter);
	}

	public void unsetStorageAdapter(StorageAdapter storageAdapter) {
		_storageAdaptersMap.remove(storageAdapter);
	}

	@Reference(
		target = "(component.name=com.liferay.dynamic.data.mapping.internal.storage.JSONStorageAdapter)"
	)
	private StorageAdapter _defaultStorageAdapter;

	private final Map<String, StorageAdapter> _storageAdaptersMap =
		new ConcurrentHashMap<>();

}