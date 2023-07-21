/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.reindexer;

import com.liferay.portal.search.spi.reindexer.BulkReindexer;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = BulkReindexersHolder.class)
public class BulkReindexersHolderImpl implements BulkReindexersHolder {

	@Override
	public BulkReindexer getBulkReindexer(String className) {
		return _get(className);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		service = BulkReindexer.class
	)
	protected void addBulkReindexer(
		BulkReindexer bulkReindexer, Map<?, ?> properties) {

		Object obj = properties.get("indexer.class.name");

		if (obj != null) {
			_put(obj.toString(), bulkReindexer);
		}
	}

	protected void removeBulkReindexer(
		BulkReindexer bulkReindexer, Map<?, ?> properties) {

		Object obj = properties.get("indexer.class.name");

		if (obj != null) {
			_remove(obj.toString());
		}
	}

	private synchronized BulkReindexer _get(String className) {
		return _map.get(className);
	}

	private synchronized void _put(
		String className, BulkReindexer bulkReindexer) {

		_map.put(className, bulkReindexer);
	}

	private synchronized void _remove(String className) {
		_map.remove(className);
	}

	private final Map<String, BulkReindexer> _map = new HashMap<>();

}