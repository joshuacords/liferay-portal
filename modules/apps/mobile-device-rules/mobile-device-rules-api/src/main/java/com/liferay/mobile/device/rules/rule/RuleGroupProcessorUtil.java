/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.rule;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Collection;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Edward Han
 * @author Máté Thurzó
 */
public class RuleGroupProcessorUtil {

	public static MDRRuleGroupInstance evaluateRuleGroups(
		ThemeDisplay themeDisplay) {

		return getRuleGroupProcessor().evaluateRuleGroups(themeDisplay);
	}

	public static RuleGroupProcessor getRuleGroupProcessor() {
		return _ruleGroupProcessorUtil._getRuleGroupProcessor();
	}

	public static RuleHandler getRuleHandler(String ruleType) {
		return getRuleGroupProcessor().getRuleHandler(ruleType);
	}

	public static Collection<RuleHandler> getRuleHandlers() {
		return getRuleGroupProcessor().getRuleHandlers();
	}

	public static Collection<String> getRuleHandlerTypes() {
		return getRuleGroupProcessor().getRuleHandlerTypes();
	}

	public static void registerRuleHandler(RuleHandler ruleHandler) {
		getRuleGroupProcessor().registerRuleHandler(ruleHandler);
	}

	public static RuleHandler unregisterRuleHandler(String ruleType) {
		return getRuleGroupProcessor().unregisterRuleHandler(ruleType);
	}

	private RuleGroupProcessorUtil() {
		Bundle bundle = FrameworkUtil.getBundle(RuleGroupProcessorUtil.class);

		_bundleContext = bundle.getBundleContext();

		_serviceTracker = ServiceTrackerFactory.open(
			_bundleContext, RuleGroupProcessor.class,
			new RuleGroupProcessorServiceTrackerCustomizer());
	}

	private RuleGroupProcessor _getRuleGroupProcessor() {
		return _ruleGroupProcessor;
	}

	private static final RuleGroupProcessorUtil _ruleGroupProcessorUtil =
		new RuleGroupProcessorUtil();

	private final BundleContext _bundleContext;
	private RuleGroupProcessor _ruleGroupProcessor;
	private final ServiceTracker<RuleGroupProcessor, RuleGroupProcessor>
		_serviceTracker;

	private class RuleGroupProcessorServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<RuleGroupProcessor, RuleGroupProcessor> {

		@Override
		public RuleGroupProcessor addingService(
			ServiceReference<RuleGroupProcessor> serviceReference) {

			_ruleGroupProcessor = _bundleContext.getService(serviceReference);

			return _ruleGroupProcessor;
		}

		@Override
		public void modifiedService(
			ServiceReference<RuleGroupProcessor> serviceReference,
			RuleGroupProcessor ruleGroupProcessor) {

			removedService(serviceReference, ruleGroupProcessor);

			addingService(serviceReference);
		}

		@Override
		public void removedService(
			ServiceReference<RuleGroupProcessor> serviceReference,
			RuleGroupProcessor ruleGroupProcessor) {

			_bundleContext.ungetService(serviceReference);

			_ruleGroupProcessor = null;
		}

	}

}