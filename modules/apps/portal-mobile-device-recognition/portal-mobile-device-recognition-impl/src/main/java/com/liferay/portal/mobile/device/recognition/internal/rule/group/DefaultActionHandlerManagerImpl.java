/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.mobile.device.recognition.internal.rule.group;

import com.liferay.mobile.device.rules.action.ActionHandler;
import com.liferay.mobile.device.rules.action.ActionHandlerManager;
import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Edward C. Han
 */
@Component(immediate = true, service = ActionHandlerManager.class)
public class DefaultActionHandlerManagerImpl implements ActionHandlerManager {

	@Override
	public void applyActions(
			List<MDRAction> mdrActions, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		for (MDRAction mdrAction : mdrActions) {
			applyAction(mdrAction, httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public ActionHandler getActionHandler(String actionType) {
		return _deviceActionHandlers.get(actionType);
	}

	@Override
	public Collection<ActionHandler> getActionHandlers() {
		return Collections.unmodifiableCollection(
			_deviceActionHandlers.values());
	}

	@Override
	public Collection<String> getActionHandlerTypes() {
		return _deviceActionHandlers.keySet();
	}

	@Override
	public void registerActionHandler(ActionHandler actionHandler) {
		addActionHandler(actionHandler);
	}

	@Override
	public ActionHandler unregisterActionHandler(String actionType) {
		return _deviceActionHandlers.remove(actionType);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addActionHandler(ActionHandler actionHandler) {
		ActionHandler oldActionHandler = _deviceActionHandlers.put(
			actionHandler.getType(), actionHandler);

		if ((oldActionHandler != null) && _log.isWarnEnabled()) {
			_log.warn(
				"Replacing existing action handler type " +
					actionHandler.getType());
		}
	}

	protected void applyAction(
			MDRAction mdrAction, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		ActionHandler actionHandler = _deviceActionHandlers.get(
			mdrAction.getType());

		if (actionHandler != null) {
			actionHandler.applyAction(
				mdrAction, httpServletRequest, httpServletResponse);
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"No action handler registered for type " + mdrAction.getType());
		}
	}

	protected void removeActionHandler(ActionHandler actionHandler) {
		_deviceActionHandlers.remove(actionHandler.getType());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultActionHandlerManagerImpl.class);

	private final Map<String, ActionHandler> _deviceActionHandlers =
		new HashMap<>();

}