/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.concurrent.RejectedExecutionHandler;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author Michael C. Han
 */
public class DestinationConfiguration implements Serializable {

	public static final String DESTINATION_TYPE_PARALLEL = "parallel";

	public static final String DESTINATION_TYPE_SERIAL = "serial";

	public static final String DESTINATION_TYPE_SYNCHRONOUS = "synchronous";

	public static DestinationConfiguration
		createParallelDestinationConfiguration(String destinationName) {

		return new DestinationConfiguration(
			DESTINATION_TYPE_PARALLEL, destinationName);
	}

	public static DestinationConfiguration createSerialDestinationConfiguration(
		String destinationName) {

		return new DestinationConfiguration(
			DESTINATION_TYPE_SERIAL, destinationName);
	}

	public static DestinationConfiguration
		createSynchronousDestinationConfiguration(String destinationName) {

		return new DestinationConfiguration(
			DESTINATION_TYPE_SYNCHRONOUS, destinationName);
	}

	public DestinationConfiguration(
		String destinationType, String destinationName) {

		_destinationType = destinationType;
		_destinationName = destinationName;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DestinationConfiguration)) {
			return false;
		}

		DestinationConfiguration destinationConfiguration =
			(DestinationConfiguration)object;

		if (Objects.equals(
				_destinationName, destinationConfiguration._destinationName)) {

			return true;
		}

		return false;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public String getDestinationType() {
		return _destinationType;
	}

	public int getMaximumQueueSize() {
		return _maximumQueueSize;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public RejectedExecutionHandler getRejectedExecutionHandler() {
		return _rejectedExecutionHandler;
	}

	public int getWorkersCoreSize() {
		return _workersCoreSize;
	}

	public int getWorkersMaxSize() {
		return _workersMaxSize;
	}

	@Override
	public int hashCode() {
		return _destinationName.hashCode();
	}

	public void setDestinationType(String destinationType) {
		_destinationType = destinationType;
	}

	public void setMaximumQueueSize(int maximumQueueSize) {
		_maximumQueueSize = maximumQueueSize;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setRejectedExecutionHandler(
		RejectedExecutionHandler rejectedExecutionHandler) {

		_rejectedExecutionHandler = rejectedExecutionHandler;
	}

	public void setWorkersCoreSize(int workersCoreSize) {
		_workersCoreSize = workersCoreSize;
	}

	public void setWorkersMaxSize(int workersMaxSize) {
		_workersMaxSize = workersMaxSize;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{_destinationName=");
		sb.append(_destinationName);
		sb.append(", _destinationType=");
		sb.append(_destinationType);
		sb.append(", _maximumQueueSize=");
		sb.append(_maximumQueueSize);
		sb.append(", _rejectedExecutionHandler=");
		sb.append(_rejectedExecutionHandler);
		sb.append(", _workersCoreSize=");
		sb.append(_workersCoreSize);
		sb.append(", _workersMaxSize=");
		sb.append(_workersMaxSize);
		sb.append("}");

		return sb.toString();
	}

	private static final int _WORKERS_CORE_SIZE = 2;

	private static final int _WORKERS_MAX_SIZE = 5;

	private final String _destinationName;
	private String _destinationType;
	private int _maximumQueueSize = Integer.MAX_VALUE;
	private RejectedExecutionHandler _rejectedExecutionHandler;
	private int _workersCoreSize = _WORKERS_CORE_SIZE;
	private int _workersMaxSize = _WORKERS_MAX_SIZE;

}