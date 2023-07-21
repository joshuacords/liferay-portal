/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NodeTypeDependentObjectRegistry<T> {

	public void addNodeTypeDependentObject(
		String nodeTypeString, T nodeTypeDependentObject) {

		NodeType nodeType = NodeType.valueOf(nodeTypeString);

		_nodeTypeDependentObjects.put(nodeType, nodeTypeDependentObject);
	}

	public T getNodeTypeDependentObjects(NodeType nodeType) {
		T nodeTypeDependentObject = _nodeTypeDependentObjects.get(nodeType);

		if (nodeTypeDependentObject == null) {
			throw new IllegalArgumentException("Invalid node type " + nodeType);
		}

		return nodeTypeDependentObject;
	}

	public T getNodeTypeDependentObjects(String nodeTypeString) {
		NodeType nodeType = NodeType.valueOf(nodeTypeString);

		return getNodeTypeDependentObjects(nodeType);
	}

	public T removeNodeTypeDependentObjects(String nodeTypeString) {
		NodeType nodeType = NodeType.valueOf(nodeTypeString);

		return _nodeTypeDependentObjects.remove(nodeType);
	}

	public void setNodeTypeDependentObjects(
		Map<String, T> nodeTypeDependentObjects) {

		for (Map.Entry<String, T> entry : nodeTypeDependentObjects.entrySet()) {
			NodeType nodeType = NodeType.valueOf(entry.getKey());

			_nodeTypeDependentObjects.put(nodeType, entry.getValue());
		}
	}

	private final Map<NodeType, T> _nodeTypeDependentObjects = new HashMap<>();

}