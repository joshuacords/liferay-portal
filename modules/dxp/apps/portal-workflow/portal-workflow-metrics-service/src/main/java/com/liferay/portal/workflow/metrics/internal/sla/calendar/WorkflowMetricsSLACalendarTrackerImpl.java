/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.sla.calendar;

import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.workflow.metrics.sla.calendar.WorkflowMetricsSLACalendar;
import com.liferay.portal.workflow.metrics.sla.calendar.WorkflowMetricsSLACalendarTracker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = false, service = WorkflowMetricsSLACalendarTracker.class)
public class WorkflowMetricsSLACalendarTrackerImpl
	implements WorkflowMetricsSLACalendarTracker {

	@Override
	public WorkflowMetricsSLACalendar getWorkflowMetricsSLACalendar(
		String key) {

		return _workflowMetricsSLACalendars.getOrDefault(
			key, _defaultWorkflowMetricsSLACalendar);
	}

	@Override
	public Map<String, String> getWorkflowMetricsSLACalendarTitles(
		Locale locale) {

		return Stream.of(
			_workflowMetricsSLACalendars.entrySet()
		).flatMap(
			Set::stream
		).collect(
			Collectors.toMap(
				entry -> entry.getKey(),
				entry -> {
					WorkflowMetricsSLACalendar workflowMetricsSLACalendar =
						entry.getValue();

					return workflowMetricsSLACalendar.getTitle(locale);
				})
		);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addWorkflowMetricsSLACalendar(
		WorkflowMetricsSLACalendar workflowMetricsSLACalendar,
		Map<String, Object> properties) {

		String key = MapUtil.getString(properties, "sla.calendar.key");

		_workflowMetricsSLACalendars.put(key, workflowMetricsSLACalendar);
	}

	@Deactivate
	protected void deactivate() {
		_workflowMetricsSLACalendars.clear();
	}

	protected void removeWorkflowMetricsSLACalendar(
		WorkflowMetricsSLACalendar workflowMetricsSLACalendar,
		Map<String, Object> properties) {

		String key = MapUtil.getString(properties, "sla.calendar.key");

		_workflowMetricsSLACalendars.remove(key);
	}

	@Reference(target = "(sla.calendar.key=default)")
	private WorkflowMetricsSLACalendar _defaultWorkflowMetricsSLACalendar;

	private final Map<String, WorkflowMetricsSLACalendar>
		_workflowMetricsSLACalendars = new HashMap<>();

}