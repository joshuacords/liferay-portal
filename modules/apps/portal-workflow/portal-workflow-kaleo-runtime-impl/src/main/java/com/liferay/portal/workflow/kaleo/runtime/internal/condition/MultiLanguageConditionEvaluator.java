/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.condition;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.condition.ConditionEvaluator;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = ConditionEvaluator.class)
public class MultiLanguageConditionEvaluator implements ConditionEvaluator {

	@Override
	public String evaluate(
			KaleoCondition kaleoCondition, ExecutionContext executionContext)
		throws PortalException {

		String conditionEvaluatorKey = getConditionEvaluatorKey(
			kaleoCondition.getScriptLanguage(),
			StringUtil.trim(kaleoCondition.getScript()));

		ConditionEvaluator conditionEvaluator = _conditionEvaluators.get(
			conditionEvaluatorKey);

		if (conditionEvaluator == null) {
			throw new IllegalArgumentException(
				"No condition evaluator found for script language " +
					conditionEvaluatorKey);
		}

		return conditionEvaluator.evaluate(kaleoCondition, executionContext);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(scripting.language=*)"
	)
	protected void addConditionEvaluator(
		ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {

		String[] scriptingLanguages = getScriptingLanguages(
			conditionEvaluator, properties);

		for (String scriptingLanguage : scriptingLanguages) {
			String conditionEvaluatorKey = getConditionEvaluatorKey(
				scriptingLanguage, ClassUtil.getClassName(conditionEvaluator));

			_conditionEvaluators.put(conditionEvaluatorKey, conditionEvaluator);
		}
	}

	protected String getConditionEvaluatorKey(
		String language, String conditionEvaluatorClassName) {

		ScriptLanguage scriptLanguage = ScriptLanguage.parse(language);

		if (scriptLanguage.equals(ScriptLanguage.JAVA)) {
			return language + StringPool.COLON + conditionEvaluatorClassName;
		}

		return language;
	}

	protected String[] getScriptingLanguages(
		ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {

		Object value = properties.get("scripting.language");

		String[] scriptingLanguages = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		if (ArrayUtil.isEmpty(scriptingLanguages)) {
			throw new IllegalArgumentException(
				"The property \"scripting.language\" is invalid for " +
					ClassUtil.getClassName(conditionEvaluator));
		}

		return scriptingLanguages;
	}

	protected void removeConditionEvaluator(
		ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {

		String[] scriptingLanguages = getScriptingLanguages(
			conditionEvaluator, properties);

		for (String scriptingLanguage : scriptingLanguages) {
			String conditionEvaluatorKey = getConditionEvaluatorKey(
				scriptingLanguage, ClassUtil.getClassName(conditionEvaluator));

			_conditionEvaluators.remove(conditionEvaluatorKey);
		}
	}

	private final Map<String, ConditionEvaluator> _conditionEvaluators =
		new HashMap<>();

}