/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.action.executor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorManager;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true, service = ActionExecutorManager.class)
public class ActionExecutorManagerImpl implements ActionExecutorManager {

	@Override
	public void executeKaleoAction(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws PortalException {

		String actionExecutorKey = getActionExecutorKey(
			kaleoAction.getScriptLanguage(),
			StringUtil.trim(kaleoAction.getScript()));

		ActionExecutor actionExecutor = _actionExecutors.get(actionExecutorKey);

		if (actionExecutor == null) {
			throw new PortalException(
				"No action executor for " + actionExecutorKey);
		}

		actionExecutor.execute(kaleoAction, executionContext);
	}

	protected String getActionExecutorKey(
		String language, String actionExecutorClassName) {

		ScriptLanguage scriptLanguage = ScriptLanguage.parse(language);

		if (scriptLanguage.equals(ScriptLanguage.JAVA)) {
			return language + StringPool.COLON + actionExecutorClassName;
		}

		return language;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected synchronized void registerActionExecutor(
		ActionExecutor actionExecutor, Map<String, Object> properties) {

		Object value = properties.get(
			"com.liferay.portal.workflow.kaleo.runtime.action.executor." +
				"language");

		String[] languages = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		for (String language : languages) {
			String actionExecutorKey = getActionExecutorKey(
				language, ClassUtil.getClassName(actionExecutor));

			_actionExecutors.put(actionExecutorKey, actionExecutor);
		}
	}

	protected synchronized void unregisterActionExecutor(
		ActionExecutor actionExecutor, Map<String, Object> properties) {

		Object value = properties.get(
			"com.liferay.portal.workflow.kaleo.runtime.action.executor." +
				"language");

		String[] languages = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		for (String language : languages) {
			String actionExecutorKey = getActionExecutorKey(
				language, ClassUtil.getClassName(actionExecutor));

			_actionExecutors.remove(actionExecutorKey);
		}
	}

	private final Map<String, ActionExecutor> _actionExecutors =
		new ConcurrentHashMap<>();

}