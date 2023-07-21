/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.jsonwebservice;

import java.lang.reflect.Method;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceActionsManagerUtil {

	public static Set<String> getContextNames() {
		return _jsonWebServiceActionsManager.getContextNames();
	}

	public static JSONWebServiceAction getJSONWebServiceAction(
			HttpServletRequest httpServletRequest)
		throws NoSuchJSONWebServiceException {

		return getJSONWebServiceActionsManager().getJSONWebServiceAction(
			httpServletRequest);
	}

	public static JSONWebServiceAction getJSONWebServiceAction(
			HttpServletRequest httpServletRequest, String path, String method,
			Map<String, Object> parameterMap)
		throws NoSuchJSONWebServiceException {

		return getJSONWebServiceActionsManager().getJSONWebServiceAction(
			httpServletRequest, path, method, parameterMap);
	}

	public static JSONWebServiceActionMapping getJSONWebServiceActionMapping(
		String signature) {

		return getJSONWebServiceActionsManager().getJSONWebServiceActionMapping(
			signature);
	}

	public static List<JSONWebServiceActionMapping>
		getJSONWebServiceActionMappings(String contextName) {

		return _jsonWebServiceActionsManager.getJSONWebServiceActionMappings(
			contextName);
	}

	public static int getJSONWebServiceActionsCount(String contextName) {
		return getJSONWebServiceActionsManager().getJSONWebServiceActionsCount(
			contextName);
	}

	public static JSONWebServiceActionsManager
		getJSONWebServiceActionsManager() {

		return _jsonWebServiceActionsManager;
	}

	public static JSONWebServiceNaming getJSONWebServiceNaming() {
		return getJSONWebServiceActionsManager().getJSONWebServiceNaming();
	}

	public static void registerJSONWebServiceAction(
		String contextName, String contextPath, Class<?> actionClass,
		Method actionMethod, String path, String method) {

		getJSONWebServiceActionsManager().registerJSONWebServiceAction(
			contextName, contextPath, actionClass, actionMethod, path, method);
	}

	public static void registerJSONWebServiceAction(
		String contextName, String contextPath, Object actionObject,
		Class<?> actionClass, Method actionMethod, String path, String method) {

		getJSONWebServiceActionsManager().registerJSONWebServiceAction(
			contextName, contextPath, actionObject, actionClass, actionMethod,
			path, method);
	}

	public static int registerServletContext(ServletContext servletContext) {
		return getJSONWebServiceActionsManager().registerServletContext(
			servletContext);
	}

	public static int unregisterJSONWebServiceActions(Object actionObject) {
		return getJSONWebServiceActionsManager().
			unregisterJSONWebServiceActions(actionObject);
	}

	public static int unregisterJSONWebServiceActions(String contextPath) {
		return getJSONWebServiceActionsManager().
			unregisterJSONWebServiceActions(contextPath);
	}

	public static int unregisterServletContext(ServletContext servletContext) {
		return getJSONWebServiceActionsManager().unregisterServletContext(
			servletContext);
	}

	public void setJSONWebServiceActionsManager(
		JSONWebServiceActionsManager jsonWebServiceActionsManager) {

		_jsonWebServiceActionsManager = jsonWebServiceActionsManager;
	}

	private static JSONWebServiceActionsManager _jsonWebServiceActionsManager;

}