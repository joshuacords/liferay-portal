/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.node;

import com.liferay.portal.workflow.kaleo.definition.NodeTypeDependentObjectRegistry;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = NodeExecutorFactory.class)
public class NodeExecutorFactory {

	public NodeExecutor getNodeExecutor(String nodeTypeString) {
		return _nodeExecutors.getNodeTypeDependentObjects(nodeTypeString);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addNodeExporter(
		NodeExecutor nodeExecutor, Map<String, Object> properties) {

		String nodeType = (String)properties.get("node.type");

		if (nodeType == null) {
			throw new IllegalArgumentException(
				"The property \"node.type\" is null");
		}

		_nodeExecutors.addNodeTypeDependentObject(nodeType, nodeExecutor);
	}

	protected void removeNodeExporter(
		NodeExecutor nodeExecutor, Map<String, Object> properties) {

		String nodeType = (String)properties.get("node.type");

		if (nodeType == null) {
			throw new IllegalArgumentException(
				"The property \"node.type\" is null");
		}

		_nodeExecutors.removeNodeTypeDependentObjects(nodeType);
	}

	private final NodeTypeDependentObjectRegistry<NodeExecutor> _nodeExecutors =
		new NodeTypeDependentObjectRegistry<>();

}