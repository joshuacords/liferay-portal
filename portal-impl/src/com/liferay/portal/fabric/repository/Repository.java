/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.repository;

import com.liferay.petra.concurrent.AsyncBroker;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.portal.fabric.netty.fileserver.FileResponse;

import java.nio.file.Path;

import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public interface Repository<T> {

	public void dispose(boolean delete);

	public AsyncBroker<Path, FileResponse> getAsyncBroker();

	public NoticeableFuture<Path> getFile(
		T t, Path remoteFilePath, Path localFilePath, boolean deleteAfterFetch);

	public NoticeableFuture<Map<Path, Path>> getFiles(
		T t, Map<Path, Path> pathMap, boolean deleteAfterFetch);

	public Path getRepositoryPath();

}