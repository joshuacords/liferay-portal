/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessConfig;
import com.liferay.petra.process.ProcessException;
import com.liferay.petra.process.ProcessExecutor;
import com.liferay.portal.fabric.status.FabricStatus;
import com.liferay.portal.fabric.worker.FabricWorker;

import java.io.Serializable;

import java.util.Collection;

/**
 * @author Shuyang Zhou
 */
public interface FabricAgent extends ProcessExecutor {

	@Override
	public <T extends Serializable> FabricWorker<T> execute(
			ProcessConfig processConfig, ProcessCallable<T> processCallable)
		throws ProcessException;

	public FabricStatus getFabricStatus();

	public Collection<? extends FabricWorker<?>> getFabricWorkers();

}