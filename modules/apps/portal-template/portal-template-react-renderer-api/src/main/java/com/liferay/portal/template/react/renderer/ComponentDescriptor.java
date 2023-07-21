/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.react.renderer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chema Balsas
 */
public class ComponentDescriptor {

	public ComponentDescriptor(String module) {
		this(module, null, null, false);
	}

	public ComponentDescriptor(String module, String componentId) {
		this(module, componentId, null, false);
	}

	public ComponentDescriptor(
		String module, String componentId, Collection<String> dependencies) {

		this(module, componentId, dependencies, false);
	}

	public ComponentDescriptor(
		String module, String componentId, Collection<String> dependencies,
		boolean positionInLine) {

		_module = module;
		_componentId = componentId;

		if (dependencies != null) {
			_dependencies.addAll(dependencies);
		}

		_positionInLine = positionInLine;
	}

	public String getComponentId() {
		return _componentId;
	}

	public Set<String> getDependencies() {
		return _dependencies;
	}

	public String getModule() {
		return _module;
	}

	public boolean isPositionInLine() {
		return _positionInLine;
	}

	private String _componentId;
	private final Set<String> _dependencies = new HashSet<>();
	private String _module;
	private boolean _positionInLine;

}