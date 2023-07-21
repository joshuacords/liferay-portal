/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.portal.search.spi.index.IndexDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author André de Oliveira
 */
@Component(service = IndexDefinitionsHolder.class)
public class IndexDefinitionsHolderImpl implements IndexDefinitionsHolder {

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void addIndexDefinition(
		IndexDefinition indexDefinition, Map<String, Object> properties) {

		_list.add(new IndexDefinitionData(indexDefinition, properties));
	}

	@Override
	public void drainTo(Collection<IndexDefinitionData> collection) {
		collection.addAll(_list);

		_list.clear();
	}

	public void removeIndexDefinition(IndexDefinition indexDefinition) {
	}

	private final List<IndexDefinitionData> _list =
		new CopyOnWriteArrayList<>();

}