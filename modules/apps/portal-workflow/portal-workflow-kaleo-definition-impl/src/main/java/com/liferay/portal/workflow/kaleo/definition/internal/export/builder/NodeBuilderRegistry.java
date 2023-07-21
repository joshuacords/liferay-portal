/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.export.builder;

import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.NodeTypeDependentObjectRegistry;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = NodeBuilderRegistry.class)
public class NodeBuilderRegistry {

	public NodeBuilder<Node> getNodeBuilder(String nodeTypeString) {
		return _nodeBuilders.getNodeTypeDependentObjects(nodeTypeString);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addNodeValidator(
		NodeBuilder<Node> nodeBuilder, Map<String, Object> properties) {

		String nodeType = (String)properties.get("node.type");

		if (nodeType == null) {
			throw new IllegalArgumentException(
				"The property \"node.type\" is null");
		}

		_nodeBuilders.addNodeTypeDependentObject(nodeType, nodeBuilder);
	}

	protected void removeNodeValidator(
		NodeBuilder<Node> nodeBuilder, Map<String, Object> properties) {

		String nodeType = (String)properties.get("node.type");

		if (nodeType == null) {
			throw new IllegalArgumentException(
				"The property \"node.type\" is null");
		}

		_nodeBuilders.removeNodeTypeDependentObjects(nodeType);
	}

	private final NodeTypeDependentObjectRegistry<NodeBuilder<Node>>
		_nodeBuilders = new NodeTypeDependentObjectRegistry<>();

}