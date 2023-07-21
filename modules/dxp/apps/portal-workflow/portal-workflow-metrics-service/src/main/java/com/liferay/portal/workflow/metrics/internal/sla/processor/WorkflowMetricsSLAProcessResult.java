/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.sla.processor;

import com.liferay.portal.workflow.metrics.sla.processor.WorkfowMetricsSLAStatus;

import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;

/**
 * @author Rafael Praxedes
 */
public class WorkflowMetricsSLAProcessResult {

	public long getCompanyId() {
		return _companyId;
	}

	public long getElapsedTime() {
		return _elapsedTime;
	}

	public long getInstanceId() {
		return _instanceId;
	}

	public LocalDateTime getLastCheckLocalDateTime() {
		return _lastCheckLocalDateTime;
	}

	public LocalDateTime getOverdueLocalDateTime() {
		return _overdueLocalDateTime;
	}

	public long getProcessId() {
		return _processId;
	}

	public long getRemainingTime() {
		return _remainingTime;
	}

	public long getSLADefinitionId() {
		return _slaDefinitionId;
	}

	public List<WorkflowMetricsSLATaskResult>
		getWorkflowMetricsSLATaskResults() {

		return _workflowMetricsSLATaskResults;
	}

	public WorkfowMetricsSLAStatus getWorkfowMetricsSLAStatus() {
		return _workfowMetricsSLAStatus;
	}

	public boolean isOnTime() {
		return _onTime;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setElapsedTime(long elapsedTime) {
		_elapsedTime = elapsedTime;
	}

	public void setInstanceId(long instanceId) {
		_instanceId = instanceId;
	}

	public void setLastCheckLocalDateTime(
		LocalDateTime lastCheckLocalDateTime) {

		_lastCheckLocalDateTime = lastCheckLocalDateTime;
	}

	public void setOnTime(boolean onTime) {
		_onTime = onTime;
	}

	public void setOverdueLocalDateTime(LocalDateTime overdueLocalDateTime) {
		_overdueLocalDateTime = overdueLocalDateTime;
	}

	public void setProcessId(long processId) {
		_processId = processId;
	}

	public void setRemainingTime(long remainingTime) {
		_remainingTime = remainingTime;
	}

	public void setSLADefinitionId(long slaDefinitionId) {
		_slaDefinitionId = slaDefinitionId;
	}

	public void setWorkflowMetricsSLATaskResults(
		List<WorkflowMetricsSLATaskResult> workflowMetricsSLATaskResults) {

		_workflowMetricsSLATaskResults = workflowMetricsSLATaskResults;
	}

	public void setWorkfowMetricsSLAStatus(
		WorkfowMetricsSLAStatus workfowMetricsSLAStatus) {

		_workfowMetricsSLAStatus = workfowMetricsSLAStatus;
	}

	private long _companyId;
	private long _elapsedTime;
	private long _instanceId;
	private LocalDateTime _lastCheckLocalDateTime;
	private boolean _onTime;
	private LocalDateTime _overdueLocalDateTime;
	private long _processId;
	private long _remainingTime;
	private long _slaDefinitionId;
	private List<WorkflowMetricsSLATaskResult> _workflowMetricsSLATaskResults =
		Collections.emptyList();
	private WorkfowMetricsSLAStatus _workfowMetricsSLAStatus;

}