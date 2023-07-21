/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.loader.modules.extender.internal.resolution;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Object to hold the results of a resolution of a list of modules.
 *
 * @author Rodolfo Roza Miranda
 * @review
 */
public class BrowserModulesResolution {

	public BrowserModulesResolution(JSONFactory jsonFactory, boolean explain) {
		_jsonFactory = jsonFactory;

		if (explain) {
			_explanation = new ArrayList<>();
		}
	}

	public void addProcessedModuleName(String moduleName) {
		_processedModuleNames.add(moduleName);
	}

	public void addResolvedModuleName(String moduleName) {
		_resolvedModuleNames.add(moduleName);

		if (_explanation == null) {
			return;
		}

		StringBundler sb = new StringBundler(_explainIndentation);

		for (int i = 0; i < _explainIndentation; i++) {
			sb.append("  ");
		}

		sb.append(moduleName);

		_explanation.add(0, sb.toString());
	}

	public void dedentExplanation() {
		_explainIndentation--;
	}

	public List<String> getResolvedModuleNames() {
		return _resolvedModuleNames;
	}

	public void indentExplanation() {
		_explainIndentation++;
	}

	public boolean isProcessedModuleName(String moduleName) {
		return _processedModuleNames.contains(moduleName);
	}

	public void putDependenciesMap(
		String moduleName, Map<String, String> dependenciesMap) {

		_dependenciesMap.put(moduleName, dependenciesMap);
	}

	public void putMappedModuleName(
		String moduleName, String mappedModuleName, boolean exactMatch) {

		Object value = mappedModuleName;

		if (exactMatch) {
			Map<String, Object> map = HashMapBuilder.<String, Object>put(
				"exactMatch", true
			).put(
				"value", mappedModuleName
			).build();

			value = map;
		}

		_mappedModuleNamesMap.put(moduleName, value);
	}

	public void putModuleFlags(String moduleName, JSONObject flagsJSONObject) {
		_flagsJSONObjects.put(moduleName, flagsJSONObject);
	}

	public void putPath(String moduleName, String path) {
		_pathsMap.put(moduleName, path);
	}

	public String toJSON() {
		Map<String, Object> map = HashMapBuilder.<String, Object>put(
			"configMap", _mappedModuleNamesMap
		).build();

		if (_explanation != null) {
			map.put("explanation", _resolvedModuleNames);
		}

		map.put("moduleFlags", _flagsJSONObjects);
		map.put("moduleMap", _dependenciesMap);
		map.put("pathMap", _pathsMap);
		map.put("resolvedModules", _resolvedModuleNames);

		return _jsonFactory.looseSerializeDeep(map);
	}

	private final Map<String, Map<String, String>> _dependenciesMap =
		new HashMap<>();
	private int _explainIndentation;
	private List<String> _explanation;
	private final Map<String, JSONObject> _flagsJSONObjects = new HashMap<>();
	private final JSONFactory _jsonFactory;
	private final Map<String, Object> _mappedModuleNamesMap = new HashMap<>();
	private final Map<String, String> _pathsMap = new HashMap<>();
	private final Set<String> _processedModuleNames = new HashSet<>();
	private final List<String> _resolvedModuleNames = new ArrayList<>();

}