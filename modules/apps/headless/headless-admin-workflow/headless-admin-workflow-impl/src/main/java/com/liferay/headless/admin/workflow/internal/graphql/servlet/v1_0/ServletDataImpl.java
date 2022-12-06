/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.admin.workflow.internal.graphql.servlet.v1_0;

import com.liferay.headless.admin.workflow.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.headless.admin.workflow.internal.graphql.query.v1_0.Query;
import com.liferay.headless.admin.workflow.internal.resource.v1_0.WorkflowLogResourceImpl;
import com.liferay.headless.admin.workflow.internal.resource.v1_0.WorkflowTaskResourceImpl;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowLogResource;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowTaskResource;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

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
 * @author Javier Gamarra
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setWorkflowTaskResourceComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects);

		Query.setWorkflowLogResourceComponentServiceObjects(
			_workflowLogResourceComponentServiceObjects);
		Query.setWorkflowTaskResourceComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "Liferay.Headless.Admin.Workflow";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/headless-admin-workflow-graphql/v1_0";
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
						"mutation#createWorkflowTaskAssignToMe",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class,
							"postWorkflowTaskAssignToMe"));
					put(
						"mutation#createWorkflowTaskAssignToUser",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class,
							"postWorkflowTaskAssignToUser"));
					put(
						"mutation#createWorkflowTaskChangeTransition",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class,
							"postWorkflowTaskChangeTransition"));
					put(
						"mutation#createWorkflowTaskUpdateDueDate",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class,
							"postWorkflowTaskUpdateDueDate"));

					put(
						"query#workflowLog",
						new ObjectValuePair<>(
							WorkflowLogResourceImpl.class, "getWorkflowLog"));
					put(
						"query#workflowTaskWorkflowLogs",
						new ObjectValuePair<>(
							WorkflowLogResourceImpl.class,
							"getWorkflowTaskWorkflowLogsPage"));
					put(
						"query#roleWorkflowTasks",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class,
							"getRoleWorkflowTasksPage"));
					put(
						"query#workflowTasksAssignedToMe",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class,
							"getWorkflowTasksAssignedToMePage"));
					put(
						"query#workflowTasksAssignedToMyRoles",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class,
							"getWorkflowTasksAssignedToMyRolesPage"));
					put(
						"query#workflowTask",
						new ObjectValuePair<>(
							WorkflowTaskResourceImpl.class, "getWorkflowTask"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<WorkflowTaskResource>
		_workflowTaskResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<WorkflowLogResource>
		_workflowLogResourceComponentServiceObjects;

}