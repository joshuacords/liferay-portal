/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.engine;

import com.liferay.portal.search.engine.NodeInformation;
import com.liferay.portal.search.engine.NodeInformationBuilder;

/**
 * @author Bryan Engler
 */
public class NodeInformationImpl implements NodeInformation {

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getVersion() {
		return _version;
	}

	protected NodeInformationImpl(NodeInformationImpl nodeInformationImpl) {
		_name = nodeInformationImpl._name;
		_version = nodeInformationImpl._version;
	}

	protected void setName(String name) {
		_name = name;
	}

	protected void setVersion(String version) {
		_version = version;
	}

	protected static class Builder implements NodeInformationBuilder {

		@Override
		public NodeInformation build() {
			return new NodeInformationImpl(_nodeInformationImpl);
		}

		@Override
		public void name(String name) {
			_nodeInformationImpl.setName(name);
		}

		@Override
		public void version(String version) {
			_nodeInformationImpl.setVersion(version);
		}

		private final NodeInformationImpl _nodeInformationImpl =
			new NodeInformationImpl();

	}

	private NodeInformationImpl() {
	}

	private String _name;
	private String _version;

}