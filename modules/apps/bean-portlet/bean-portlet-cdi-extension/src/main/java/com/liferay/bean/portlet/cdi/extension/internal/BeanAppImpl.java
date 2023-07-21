/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bean.portlet.cdi.extension.internal;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public class BeanAppImpl implements BeanApp {

	public BeanAppImpl(
		String specVersion, String defaultNamespace, List<Event> events,
		Map<String, PublicRenderParameter> publicRenderParameters,
		Map<String, List<String>> containerRuntimeOptions,
		Set<String> customPortletModes,
		List<Map.Entry<Integer, String>> portletListeners) {

		_specVersion = specVersion;
		_defaultNamespace = defaultNamespace;
		_events = events;
		_publicRenderParameters = publicRenderParameters;
		_containerRuntimeOptions = containerRuntimeOptions;
		_customPortletModes = customPortletModes;
		_portletListeners = portletListeners;
	}

	@Override
	public Map<String, List<String>> getContainerRuntimeOptions() {
		return _containerRuntimeOptions;
	}

	@Override
	public Set<String> getCustomPortletModes() {
		return _customPortletModes;
	}

	@Override
	public String getDefaultNamespace() {
		return _defaultNamespace;
	}

	@Override
	public List<Event> getEvents() {
		return _events;
	}

	@Override
	public List<Map.Entry<Integer, String>> getPortletListeners() {
		return _portletListeners;
	}

	@Override
	public Map<String, PublicRenderParameter> getPublicRenderParameters() {
		return _publicRenderParameters;
	}

	@Override
	public String getSpecVersion() {
		return _specVersion;
	}

	private final Map<String, List<String>> _containerRuntimeOptions;
	private final Set<String> _customPortletModes;
	private final String _defaultNamespace;
	private final List<Event> _events;
	private final List<Map.Entry<Integer, String>> _portletListeners;
	private final Map<String, PublicRenderParameter> _publicRenderParameters;
	private final String _specVersion;

}