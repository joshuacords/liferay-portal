/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.sla.processor;

import com.liferay.portal.workflow.metrics.sla.processor.WorkfowMetricsSLAStatus;

import java.time.LocalDateTime;

/**
 * @author Rafael Praxedes
 */
public class WorkflowMetricsSLATaskResult {

	public long getCompanyId() {
		return _companyId;
	}

	public long getInstanceId() {
		return _instanceId;
	}

	public LocalDateTime getLastCheckLocalDateTime() {
		return _lastCheckLocalDateTime;
	}

	public long getProcessId() {
		return _processId;
	}

	public long getSLADefinitionId() {
		return _slaDefinitionId;
	}

	public long getTaskId() {
		return _taskId;
	}

	public String getTaskName() {
		return _taskName;
	}

	public long getTokenId() {
		return _tokenId;
	}

	public WorkfowMetricsSLAStatus getWorkfowMetricsSLAStatus() {
		return _workfowMetricsSLAStatus;
	}

	public boolean isBreached() {
		return _breached;
	}

	public boolean isOnTime() {
		return _onTime;
	}

	public void setBreached(boolean breached) {
		_breached = breached;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public void setProcessId(long processId) {
		_processId = processId;
	}

	public void setSLADefinitionId(long slaDefinitionId) {
		_slaDefinitionId = slaDefinitionId;
	}

	public void setTaskId(long taskId) {
		_taskId = taskId;
	}

	public void setTaskName(String taskName) {
		_taskName = taskName;
	}

	public void setTokenId(long tokenId) {
		_tokenId = tokenId;
	}

	public void setWorkfowMetricsSLAStatus(
		WorkfowMetricsSLAStatus workfowMetricsSLAStatus) {

		_workfowMetricsSLAStatus = workfowMetricsSLAStatus;
	}

	private boolean _breached;
	private long _companyId;
	private long _instanceId;
	private LocalDateTime _lastCheckLocalDateTime;
	private boolean _onTime;
	private long _processId;
	private long _slaDefinitionId;
	private long _taskId;
	private String _taskName;
	private long _tokenId;
	private WorkfowMetricsSLAStatus _workfowMetricsSLAStatus;

}