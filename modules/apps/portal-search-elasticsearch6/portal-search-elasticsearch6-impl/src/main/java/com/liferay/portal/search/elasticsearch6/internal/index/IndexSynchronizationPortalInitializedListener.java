/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.index;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.search.elasticsearch6.spi.index.IndexRegistrar;
import com.liferay.portal.search.spi.index.IndexDefinition;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = {})
public class IndexSynchronizationPortalInitializedListener {

	@Activate
	public void activate() {
		_indexSynchronizer.synchronizeIndexes();

		_activated = true;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void addIndexDefinition(
		IndexDefinition indexDefinition, Map<String, Object> properties) {

		if (_activated) {
			_indexSynchronizer.synchronizeIndexDefinition(
				new IndexDefinitionData(indexDefinition, properties));
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void addIndexRegistrar(IndexRegistrar indexRegistrar) {
		if (_activated) {
			_indexSynchronizer.synchronizeIndexRegistrar(indexRegistrar);
		}
	}

	public void removeIndexDefinition(IndexDefinition indexDefinition) {
	}

	public void removeIndexRegistrar(IndexRegistrar indexRegistrar) {
	}

	@Reference(unbind = "-")
	public void setIndexSynchronizer(IndexSynchronizer indexSynchronizer) {
		_indexSynchronizer = indexSynchronizer;
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	public void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Portal is initialized and indexes will be synchronized");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IndexSynchronizationPortalInitializedListener.class);

	private boolean _activated;
	private IndexSynchronizer _indexSynchronizer;

}