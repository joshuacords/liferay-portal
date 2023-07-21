/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.plugin;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Jorge Ferrer
 */
public class RepositoryReport implements Serializable {

	public static final String SUCCESS = "success";

	public void addError(
		String repositoryURL, PluginPackageException pluginPackageException) {

		StringBundler sb = new StringBundler(2);

		if (Validator.isNotNull(pluginPackageException.getMessage())) {
			sb.append(pluginPackageException.getMessage());
		}

		if (pluginPackageException.getCause() != null) {
			Throwable cause = pluginPackageException.getCause();

			if (Validator.isNotNull(cause.getMessage())) {
				sb.append(cause.getMessage());
			}
		}

		if (sb.index() == 0) {
			sb.append(pluginPackageException.toString());
		}

		_reportMap.put(repositoryURL, sb.toString());
	}

	public void addSuccess(String repositoryURL) {
		_reportMap.put(repositoryURL, SUCCESS);
	}

	public Set<String> getRepositoryURLs() {
		return _reportMap.keySet();
	}

	public String getState(String repositoryURL) {
		return _reportMap.get(repositoryURL);
	}

	@Override
	public String toString() {
		Set<String> repositoryURLs = getRepositoryURLs();

		if (repositoryURLs.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(repositoryURLs.size() * 3);

		for (String repositoryURL : repositoryURLs) {
			sb.append(repositoryURL);
			sb.append(": ");
			sb.append(_reportMap.get(repositoryURL));
		}

		return sb.toString();
	}

	private final Map<String, String> _reportMap = new TreeMap<>();

}