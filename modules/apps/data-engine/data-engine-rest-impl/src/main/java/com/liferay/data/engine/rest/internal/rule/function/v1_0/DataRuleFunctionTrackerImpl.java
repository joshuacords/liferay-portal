/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.rule.function.v1_0;

import com.liferay.data.engine.rule.function.DataRuleFunction;
import com.liferay.data.engine.rule.function.DataRuleFunctionTracker;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Jeyvison Nascimento
 */
@Component(immediate = true, service = DataRuleFunctionTracker.class)
public class DataRuleFunctionTrackerImpl implements DataRuleFunctionTracker {

	@Override
	public DataRuleFunction getDataRuleFunction(String name) {
		return _nameDataRuleFunctions.get(name);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addDataRuleFunction(
		DataRuleFunction dataRuleFunction, Map<String, Object> properties) {

		String name = MapUtil.getString(
			properties, "data.engine.rule.function.name");

		_nameDataRuleFunctions.put(name, dataRuleFunction);

		List<DataRuleFunction> dataRuleFunctions =
			_typeDataRuleFunctions.getOrDefault(
				MapUtil.getString(properties, "data.engine.rule.function.type"),
				new ArrayList<>());

		dataRuleFunctions.add(dataRuleFunction);

		_typeDataRuleFunctions.put(name, dataRuleFunctions);
	}

	@Deactivate
	protected void deactivate() {
		_nameDataRuleFunctions.clear();
		_typeDataRuleFunctions.clear();
	}

	protected void removeDataRuleFunction(
		DataRuleFunction dataRuleFunction, Map<String, Object> properties) {

		String type = MapUtil.getString(
			properties, "data.engine.rule.function.type");

		List<DataRuleFunction> dataRuleFunctions = _typeDataRuleFunctions.get(
			type);

		if (dataRuleFunctions != null) {
			dataRuleFunctions.remove(dataRuleFunction);

			if (dataRuleFunctions.isEmpty()) {
				_typeDataRuleFunctions.remove(type);
			}
		}
	}

	private final Map<String, DataRuleFunction> _nameDataRuleFunctions =
		new TreeMap<>();
	private final Map<String, List<DataRuleFunction>> _typeDataRuleFunctions =
		new TreeMap<>();

}