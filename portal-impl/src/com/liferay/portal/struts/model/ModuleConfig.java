/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.struts.model;

import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.struts.ActionAdapter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceTrackerMap;
import com.liferay.registry.collections.ServiceTrackerMapFactory;
import com.liferay.registry.collections.ServiceTrackerMapFactoryUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class ModuleConfig {

	public void addActionForward(ActionForward actionForward) {
		_actionForwards.put(actionForward.getName(), actionForward);
	}

	public void addActionMapping(ActionMapping actionMapping) {
		_actionMappings.put(actionMapping.getPath(), actionMapping);
	}

	public ActionForward getActionForward(String name) {
		return _actionForwards.get(name);
	}

	public ActionMapping getActionMapping(String path) {
		ActionAdapter actionAdapter = _getActionAdaptor(path);

		if (actionAdapter != null) {
			return new ActionMapping(this, null, path, actionAdapter);
		}

		return _actionMappings.get(path);
	}

	private static ActionAdapter _getActionAdaptor(String path) {
		ActionAdapter actionAdapter = _actionAdaptors.getService(path);

		if (actionAdapter != null) {
			return actionAdapter;
		}

		for (String key : _actionAdaptors.keySet()) {
			if (path.startsWith(key)) {
				return _actionAdaptors.getService(key);
			}
		}

		return null;
	}

	private static final ServiceTrackerMap<String, ActionAdapter>
		_actionAdaptors;

	static {
		ServiceTrackerMapFactory serviceTrackerMapFactory =
			ServiceTrackerMapFactoryUtil.getServiceTrackerMapFactory();

		_actionAdaptors = serviceTrackerMapFactory.openSingleValueMap(
			StrutsAction.class, "path",
			new ServiceTrackerCustomizer<StrutsAction, ActionAdapter>() {

				@Override
				public ActionAdapter addingService(
					ServiceReference<StrutsAction> serviceReference) {

					Registry registry = RegistryUtil.getRegistry();

					return new ActionAdapter(
						registry.getService(serviceReference));
				}

				@Override
				public void modifiedService(
					ServiceReference<StrutsAction> serviceReference,
					ActionAdapter actionAdapter) {
				}

				@Override
				public void removedService(
					ServiceReference<StrutsAction> serviceReference,
					ActionAdapter actionAdapter) {

					Registry registry = RegistryUtil.getRegistry();

					registry.ungetService(serviceReference);
				}

			});
	}

	private final Map<String, ActionForward> _actionForwards = new HashMap<>();
	private final Map<String, ActionMapping> _actionMappings = new HashMap<>();

}