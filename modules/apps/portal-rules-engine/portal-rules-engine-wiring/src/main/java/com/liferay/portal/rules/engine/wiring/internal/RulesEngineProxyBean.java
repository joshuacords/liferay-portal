/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.rules.engine.wiring.internal;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.rules.engine.Fact;
import com.liferay.portal.rules.engine.Query;
import com.liferay.portal.rules.engine.RulesEngine;
import com.liferay.portal.rules.engine.RulesResourceRetriever;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class RulesEngineProxyBean extends BaseProxyBean implements RulesEngine {

	@Override
	public void add(
		String domainName, RulesResourceRetriever rulesResourceRetriever) {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsRuleDomain(String domainName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void execute(
		RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, ?> execute(
		RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
		Query query) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void execute(String domainName, List<Fact<?>> facts) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, ?> execute(
		String domainName, List<Fact<?>> facts, Query query) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(String domainName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(
		String domainName, RulesResourceRetriever rulesResourceRetriever) {

		throw new UnsupportedOperationException();
	}

}