/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.worker;

import com.liferay.petra.process.PathHolder;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessConfig;
import com.liferay.petra.process.ProcessException;
import com.liferay.util.SerializableUtil;

import java.io.Serializable;

import java.nio.file.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class NettyFabricWorkerConfig<T extends Serializable>
	implements Serializable {

	public NettyFabricWorkerConfig(
		long id, ProcessConfig processConfig,
		ProcessCallable<T> processCallable, Map<Path, Path> inputPathMap) {

		if (processConfig == null) {
			throw new NullPointerException("Process config is null");
		}

		if (processCallable == null) {
			throw new NullPointerException("Process callable is null");
		}

		if (inputPathMap == null) {
			throw new NullPointerException("Input path map is null");
		}

		_id = id;
		_processConfig = processConfig;
		_processCallable = new NettyFabricWorkerProcessCallable<>(
			processCallable);

		_inputPathHolderMap = new HashMap<>();

		for (Map.Entry<Path, Path> entry : inputPathMap.entrySet()) {
			_inputPathHolderMap.put(
				new PathHolder(entry.getKey()),
				new PathHolder(entry.getValue()));
		}
	}

	public long getId() {
		return _id;
	}

	public Map<Path, Path> getInputPathMap() {
		Map<Path, Path> inputPathMap = new HashMap<>();

		for (Map.Entry<PathHolder, PathHolder> entry :
				_inputPathHolderMap.entrySet()) {

			PathHolder keyPathHolder = entry.getKey();
			PathHolder valuePathHolder = entry.getValue();

			inputPathMap.put(
				keyPathHolder.getPath(), valuePathHolder.getPath());
		}

		return inputPathMap;
	}

	public ProcessCallable<T> getProcessCallable() {
		return _processCallable;
	}

	public ProcessConfig getProcessConfig() {
		return _processConfig;
	}

	private static final long serialVersionUID = 1L;

	private final long _id;
	private final Map<PathHolder, PathHolder> _inputPathHolderMap;
	private final ProcessCallable<T> _processCallable;
	private final ProcessConfig _processConfig;

	private static class NettyFabricWorkerProcessCallable
		<T extends Serializable>
			implements ProcessCallable<T> {

		public NettyFabricWorkerProcessCallable(
			ProcessCallable<T> processCallable) {

			_data = SerializableUtil.serialize(processCallable);
			_toString = processCallable.toString();
		}

		@Override
		public T call() throws ProcessException {
			ProcessCallable<T> processCallable =
				(ProcessCallable<T>)SerializableUtil.deserialize(_data);

			return processCallable.call();
		}

		@Override
		public String toString() {
			return _toString;
		}

		private static final long serialVersionUID = 1L;

		private final byte[] _data;
		private final String _toString;

	}

}