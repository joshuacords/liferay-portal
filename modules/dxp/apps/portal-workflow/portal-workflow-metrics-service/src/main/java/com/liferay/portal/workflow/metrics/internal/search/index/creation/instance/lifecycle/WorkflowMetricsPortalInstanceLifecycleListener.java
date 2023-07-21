/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.search.index.creation.instance.lifecycle;

import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.workflow.metrics.internal.search.index.InstanceWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.internal.search.index.NodeWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.internal.search.index.ProcessWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.internal.search.index.SLAProcessResultWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.internal.search.index.SLATaskResultWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.internal.search.index.TokenWorkflowMetricsIndexer;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class WorkflowMetricsPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		if (Objects.isNull(_searchEngineAdapter)) {
			return;
		}

		_instanceWorkflowMetricsIndexer.createIndex(company.getCompanyId());
		_nodeWorkflowMetricsIndexer.createIndex(company.getCompanyId());
		_processWorkflowMetricsIndexer.createIndex(company.getCompanyId());
		_slaProcessResultWorkflowMetricsIndexer.createIndex(
			company.getCompanyId());
		_slaTaskResultWorkflowMetricsIndexer.createIndex(
			company.getCompanyId());
		_tokenWorkflowMetricsIndexer.createIndex(company.getCompanyId());
	}

	@Override
	public void portalInstanceUnregistered(Company company) throws Exception {
		if (Objects.isNull(_searchEngineAdapter)) {
			return;
		}

		_instanceWorkflowMetricsIndexer.removeIndex(company.getCompanyId());
		_nodeWorkflowMetricsIndexer.removeIndex(company.getCompanyId());
		_processWorkflowMetricsIndexer.removeIndex(company.getCompanyId());
		_slaProcessResultWorkflowMetricsIndexer.removeIndex(
			company.getCompanyId());
		_slaTaskResultWorkflowMetricsIndexer.removeIndex(
			company.getCompanyId());
		_tokenWorkflowMetricsIndexer.removeIndex(company.getCompanyId());
	}

	@Reference
	private InstanceWorkflowMetricsIndexer _instanceWorkflowMetricsIndexer;

	@Reference
	private NodeWorkflowMetricsIndexer _nodeWorkflowMetricsIndexer;

	@Reference
	private ProcessWorkflowMetricsIndexer _processWorkflowMetricsIndexer;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(search.engine.impl=Elasticsearch)"
	)
	private volatile SearchEngineAdapter _searchEngineAdapter;

	@Reference
	private SLAProcessResultWorkflowMetricsIndexer
		_slaProcessResultWorkflowMetricsIndexer;

	@Reference
	private SLATaskResultWorkflowMetricsIndexer
		_slaTaskResultWorkflowMetricsIndexer;

	@Reference
	private TokenWorkflowMetricsIndexer _tokenWorkflowMetricsIndexer;

}