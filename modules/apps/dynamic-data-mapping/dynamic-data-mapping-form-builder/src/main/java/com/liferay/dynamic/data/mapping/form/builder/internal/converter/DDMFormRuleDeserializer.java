/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.DDMFormRule;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.DDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.DDMFormRuleCondition;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.AutoFillDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.CalculateDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.DefaultDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.JumpToPageDDMFormRuleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = DDMFormRuleDeserializer.class)
public class DDMFormRuleDeserializer {

	public List<DDMFormRule> deserialize(String rules) throws PortalException {
		JSONArray rulesJSONArray = _jsonFactory.createJSONArray(rules);

		List<DDMFormRule> ddmFormRules = new ArrayList<>(
			rulesJSONArray.length());

		for (int i = 0; i < rulesJSONArray.length(); i++) {
			DDMFormRule ddmFormRule = deserializeDDMFormRule(
				rulesJSONArray.getJSONObject(i));

			ddmFormRules.add(ddmFormRule);
		}

		return ddmFormRules;
	}

	protected DDMFormRule deserializeDDMFormRule(JSONObject ruleJSONObject) {
		DDMFormRule ddmFormRule = new DDMFormRule();

		List<DDMFormRuleAction> actions = deserializeDDMFormRuleActions(
			ruleJSONObject.getJSONArray("actions"));

		ddmFormRule.setDDMFormRuleActions(actions);

		List<DDMFormRuleCondition> conditions =
			deserializeDDMFormRuleConditions(
				ruleJSONObject.getJSONArray("conditions"));

		ddmFormRule.setDDMFormRuleConditions(conditions);

		ddmFormRule.setLogicalOperator(
			ruleJSONObject.getString("logical-operator"));

		return ddmFormRule;
	}

	protected <T extends DDMFormRuleAction> DDMFormRuleAction
		deserializeDDMFormRuleAction(
			JSONObject actionJSONObject, Class<T> targetClass) {

		JSONDeserializer<T> jsonDeserializer =
			_jsonFactory.createJSONDeserializer();

		return jsonDeserializer.deserialize(
			actionJSONObject.toString(), targetClass);
	}

	protected List<DDMFormRuleAction> deserializeDDMFormRuleActions(
		JSONArray actionsJSONArray) {

		List<DDMFormRuleAction> ddmFormRuleActions = new ArrayList<>();

		for (int i = 0; i < actionsJSONArray.length(); i++) {
			JSONObject actionJSONObject = actionsJSONArray.getJSONObject(i);

			String action = actionJSONObject.getString("action");

			Class<? extends DDMFormRuleAction> clazz =
				getDDMFormRuleActionClass(action);

			DDMFormRuleAction ddmFormRuleAction = deserializeDDMFormRuleAction(
				actionJSONObject, clazz);

			ddmFormRuleActions.add(ddmFormRuleAction);
		}

		return ddmFormRuleActions;
	}

	protected List<DDMFormRuleCondition> deserializeDDMFormRuleConditions(
		JSONArray conditionsJSONArray) {

		JSONDeserializer<DDMFormRuleCondition[]> jsonDeserializer =
			_jsonFactory.createJSONDeserializer();

		DDMFormRuleCondition[] ruleConditions = jsonDeserializer.deserialize(
			conditionsJSONArray.toString(), DDMFormRuleCondition[].class);

		return ListUtil.fromArray(ruleConditions);
	}

	protected Class<? extends DDMFormRuleAction> getDDMFormRuleActionClass(
		String action) {

		if (action.equals("auto-fill")) {
			return AutoFillDDMFormRuleAction.class;
		}
		else if (action.equals("calculate")) {
			return CalculateDDMFormRuleAction.class;
		}
		else if (action.equals("jump-to-page")) {
			return JumpToPageDDMFormRuleAction.class;
		}

		return DefaultDDMFormRuleAction.class;
	}

	@Reference
	private JSONFactory _jsonFactory;

}