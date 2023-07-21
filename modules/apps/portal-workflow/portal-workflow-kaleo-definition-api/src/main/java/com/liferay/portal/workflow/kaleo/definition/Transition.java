/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

/**
 * @author Michael C. Han
 */
public class Transition {

	public Transition(
		String name, Node sourceNode, Node targetNode, boolean defaultValue) {

		_name = name;
		_sourceNode = sourceNode;
		_targetNode = targetNode;

		_default = defaultValue;
	}

	public String getName() {
		return _name;
	}

	public Node getSourceNode() {
		return _sourceNode;
	}

	public Node getTargetNode() {
		return _targetNode;
	}

	public Timer getTimer() {
		return _timer;
	}

	public boolean isDefault() {
		return _default;
	}

	public void setTimers(Timer timer) {
		_timer = timer;
	}

	private final boolean _default;
	private final String _name;
	private final Node _sourceNode;
	private final Node _targetNode;
	private Timer _timer;

}