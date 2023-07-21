/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.internal;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;

import java.util.List;

/**
 * @author Tina Tian
 */
public class SchedulerEngineProxyBean
	extends BaseProxyBean implements SchedulerEngine {

	@Override
	public void delete(String groupName, StorageType storageType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(
		String jobName, String groupName, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public SchedulerResponse getScheduledJob(
		String jobName, String groupName, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<SchedulerResponse> getScheduledJobs() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<SchedulerResponse> getScheduledJobs(StorageType storageType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<SchedulerResponse> getScheduledJobs(
		String groupName, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void pause(String groupName, StorageType storageType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void pause(
		String jobName, String groupName, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void resume(String groupName, StorageType storageType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resume(
		String jobName, String groupName, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void schedule(
		Trigger trigger, String description, String destinationName,
		Message message, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void shutdown() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void suppressError(
		String jobName, String groupName, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void unschedule(String groupName, StorageType storageType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unschedule(
		String jobName, String groupName, StorageType storageType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Trigger trigger, StorageType storageType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void validateTrigger(Trigger trigger, StorageType storageType) {
		throw new UnsupportedOperationException();
	}

}