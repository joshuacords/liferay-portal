/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.rules.engine;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Vihang Pathak
 */
public interface RulesEngine {

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void add(
			String domainName, RulesResourceRetriever rulesResourceRetriever)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public boolean containsRuleDomain(String domainName)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.ASYNC)
	public void execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public Map<String, ?> execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			Query query)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.ASYNC)
	public void execute(String domainName, List<Fact<?>> facts)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public Map<String, ?> execute(
			String domainName, List<Fact<?>> facts, Query query)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void remove(String domainName) throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void update(
			String domainName, RulesResourceRetriever rulesResourceRetriever)
		throws RulesEngineException;

}