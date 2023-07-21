/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.action;

import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Edward Han
 * @author Máté Thurzó
 */
public class ActionHandlerManagerUtil {

	public static void applyActions(
			List<MDRAction> mdrActions, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		getActionHandlerManager().applyActions(
			mdrActions, httpServletRequest, httpServletResponse);
	}

	public static ActionHandler getActionHandler(String actionType) {
		return getActionHandlerManager().getActionHandler(actionType);
	}

	public static ActionHandlerManager getActionHandlerManager() {
		return _actionHandlerManagerUtil._getActionHandlerManager();
	}

	public static Collection<ActionHandler> getActionHandlers() {
		return getActionHandlerManager().getActionHandlers();
	}

	public static Collection<String> getActionHandlerTypes() {
		return getActionHandlerManager().getActionHandlerTypes();
	}

	public static void registerActionHandler(ActionHandler actionHandler) {
		getActionHandlerManager().registerActionHandler(actionHandler);
	}

	public static ActionHandler unregisterActionHandler(String actionType) {
		return getActionHandlerManager().unregisterActionHandler(actionType);
	}

	private ActionHandlerManagerUtil() {
		_serviceTracker = ServiceTrackerFactory.open(
			FrameworkUtil.getBundle(ActionHandlerManagerUtil.class),
			ActionHandlerManager.class);
	}

	private ActionHandlerManager _getActionHandlerManager() {
		return _serviceTracker.getService();
	}

	private static final ActionHandlerManagerUtil _actionHandlerManagerUtil =
		new ActionHandlerManagerUtil();

	private final ServiceTracker<ActionHandlerManager, ActionHandlerManager>
		_serviceTracker;

}