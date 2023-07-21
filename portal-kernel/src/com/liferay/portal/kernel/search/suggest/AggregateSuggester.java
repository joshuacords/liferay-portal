/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.suggest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author     Michael C. Han
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class AggregateSuggester implements Suggester {

	public AggregateSuggester(String name, String value) {
		_name = name;
		_value = value;
	}

	@Override
	public <T> T accept(SuggesterVisitor<T> suggesterVisitor) {
		return suggesterVisitor.visit(this);
	}

	public void addSuggester(Suggester suggester) {
		_suggesters.put(suggester.getName(), suggester);
	}

	@Override
	public String getName() {
		return _name;
	}

	public Map<String, Suggester> getSuggesters() {
		return Collections.unmodifiableMap(_suggesters);
	}

	public String getValue() {
		return _value;
	}

	private final String _name;
	private final Map<String, Suggester> _suggesters = new HashMap<>();
	private final String _value;

}