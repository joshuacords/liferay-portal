/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.rules.engine;

import com.liferay.osgi.util.ServiceTrackerFactory;

import java.util.List;
import java.util.Map;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author     Michael C. Han
 * @author     Raymond Augé
 * @deprecated As of Wilberforce (7.0.x)
 */
@Deprecated
public class RulesEngineUtil {

	public static void add(
			String domainName, RulesResourceRetriever rulesResourceRetriever)
		throws RulesEngineException {

		getRulesEngine().add(domainName, rulesResourceRetriever);
	}

	public static boolean containsRuleDomain(String domainName)
		throws RulesEngineException {

		return getRulesEngine().containsRuleDomain(domainName);
	}

	public static void execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts)
		throws RulesEngineException {

		getRulesEngine().execute(rulesResourceRetriever, facts);
	}

	public static Map<String, ?> execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			Query query)
		throws RulesEngineException {

		return getRulesEngine().execute(rulesResourceRetriever, facts, query);
	}

	public static void execute(String domainName, List<Fact<?>> facts)
		throws RulesEngineException {

		getRulesEngine().execute(domainName, facts);
	}

	public static Map<String, ?> execute(
			String domainName, List<Fact<?>> facts, Query query)
		throws RulesEngineException {

		return getRulesEngine().execute(domainName, facts, query);
	}

	public static RulesEngine getRulesEngine() {
		return _serviceTracker.getService();
	}

	public static void remove(String domainName) throws RulesEngineException {
		getRulesEngine().remove(domainName);
	}

	public static void update(
			String domainName, RulesResourceRetriever rulesResourceRetriever)
		throws RulesEngineException {

		getRulesEngine().update(domainName, rulesResourceRetriever);
	}

	private static final ServiceTracker<RulesEngine, RulesEngine>
		_serviceTracker = ServiceTrackerFactory.open(
			FrameworkUtil.getBundle(RulesEngineUtil.class), RulesEngine.class);

}