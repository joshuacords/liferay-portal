/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.fileserver;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.process.PathHolder;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.nio.file.Path;

/**
 * @author Shuyang Zhou
 */
public class FileRequest implements Serializable {

	public FileRequest(
		Path path, long lastModifiedTime, boolean deleteAfterFetch) {

		_lastModifiedTime = lastModifiedTime;
		_deleteAfterFetch = deleteAfterFetch;

		_pathHolder = new PathHolder(path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileRequest)) {
			return false;
		}

		FileRequest fileRequest = (FileRequest)obj;

		if ((_deleteAfterFetch == fileRequest._deleteAfterFetch) &&
			(_lastModifiedTime == fileRequest._lastModifiedTime) &&
			_pathHolder.equals(fileRequest._pathHolder)) {

			return true;
		}

		return false;
	}

	public long getLastModifiedTime() {
		return _lastModifiedTime;
	}

	public Path getPath() {
		return _pathHolder.getPath();
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _deleteAfterFetch);

		hash = HashUtil.hash(hash, _lastModifiedTime);
		hash = HashUtil.hash(hash, _pathHolder);

		return hash;
	}

	public boolean isDeleteAfterFetch() {
		return _deleteAfterFetch;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{deleteAfterFetch=");
		sb.append(_deleteAfterFetch);
		sb.append(", lastModifiedTime=");
		sb.append(_lastModifiedTime);
		sb.append(", pathHolder=");
		sb.append(_pathHolder);
		sb.append("}");

		return sb.toString();
	}

	private static final long serialVersionUID = 1L;

	private final boolean _deleteAfterFetch;
	private final long _lastModifiedTime;
	private final PathHolder _pathHolder;

}