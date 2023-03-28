/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.rest.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;
import com.liferay.portal.workflow.metrics.rest.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.portal.workflow.metrics.rest.internal.graphql.query.v1_0.Query;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.AssigneeUserResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.CalendarResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.InstanceResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.MetricResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.NodeResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.ProcessResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.SLAResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.TaskResourceImpl;
import com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0.TimeRangeResourceImpl;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.AssigneeUserResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.CalendarResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.InstanceResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.MetricResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.NodeResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.ProcessResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.SLAResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.TaskResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.TimeRangeResource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setSLAResourceComponentServiceObjects(
			_slaResourceComponentServiceObjects);

		Query.setAssigneeUserResourceComponentServiceObjects(
			_assigneeUserResourceComponentServiceObjects);
		Query.setCalendarResourceComponentServiceObjects(
			_calendarResourceComponentServiceObjects);
		Query.setInstanceResourceComponentServiceObjects(
			_instanceResourceComponentServiceObjects);
		Query.setMetricResourceComponentServiceObjects(
			_metricResourceComponentServiceObjects);
		Query.setNodeResourceComponentServiceObjects(
			_nodeResourceComponentServiceObjects);
		Query.setProcessResourceComponentServiceObjects(
			_processResourceComponentServiceObjects);
		Query.setSLAResourceComponentServiceObjects(
			_slaResourceComponentServiceObjects);
		Query.setTaskResourceComponentServiceObjects(
			_taskResourceComponentServiceObjects);
		Query.setTimeRangeResourceComponentServiceObjects(
			_timeRangeResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "Liferay.Portal.Workflow.Metrics.REST";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/portal-workflow-metrics-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createProcessSLA",
						new ObjectValuePair<>(
							SLAResourceImpl.class, "postProcessSLA"));
					put(
						"mutation#createProcessSLABatch",
						new ObjectValuePair<>(
							SLAResourceImpl.class, "postProcessSLABatch"));
					put(
						"mutation#deleteSLA",
						new ObjectValuePair<>(
							SLAResourceImpl.class, "deleteSLA"));
					put(
						"mutation#deleteSLABatch",
						new ObjectValuePair<>(
							SLAResourceImpl.class, "deleteSLABatch"));
					put(
						"mutation#updateSLA",
						new ObjectValuePair<>(SLAResourceImpl.class, "putSLA"));
					put(
						"mutation#updateSLABatch",
						new ObjectValuePair<>(
							SLAResourceImpl.class, "putSLABatch"));

					put(
						"query#processAssigneeUsers",
						new ObjectValuePair<>(
							AssigneeUserResourceImpl.class,
							"getProcessAssigneeUsersPage"));
					put(
						"query#calendars",
						new ObjectValuePair<>(
							CalendarResourceImpl.class, "getCalendarsPage"));
					put(
						"query#processInstances",
						new ObjectValuePair<>(
							InstanceResourceImpl.class,
							"getProcessInstancesPage"));
					put(
						"query#processInstance",
						new ObjectValuePair<>(
							InstanceResourceImpl.class, "getProcessInstance"));
					put(
						"query#processMetric",
						new ObjectValuePair<>(
							MetricResourceImpl.class, "getProcessMetric"));
					put(
						"query#processNodes",
						new ObjectValuePair<>(
							NodeResourceImpl.class, "getProcessNodesPage"));
					put(
						"query#processes",
						new ObjectValuePair<>(
							ProcessResourceImpl.class, "getProcessesPage"));
					put(
						"query#process",
						new ObjectValuePair<>(
							ProcessResourceImpl.class, "getProcess"));
					put(
						"query#processTitle",
						new ObjectValuePair<>(
							ProcessResourceImpl.class, "getProcessTitle"));
					put(
						"query#processSLAs",
						new ObjectValuePair<>(
							SLAResourceImpl.class, "getProcessSLAsPage"));
					put(
						"query#sLA",
						new ObjectValuePair<>(SLAResourceImpl.class, "getSLA"));
					put(
						"query#processTasks",
						new ObjectValuePair<>(
							TaskResourceImpl.class, "getProcessTasksPage"));
					put(
						"query#timeRanges",
						new ObjectValuePair<>(
							TimeRangeResourceImpl.class, "getTimeRangesPage"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<SLAResource>
		_slaResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AssigneeUserResource>
		_assigneeUserResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CalendarResource>
		_calendarResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<InstanceResource>
		_instanceResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<MetricResource>
		_metricResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<NodeResource>
		_nodeResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProcessResource>
		_processResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TaskResource>
		_taskResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TimeRangeResource>
		_timeRangeResourceComponentServiceObjects;

}