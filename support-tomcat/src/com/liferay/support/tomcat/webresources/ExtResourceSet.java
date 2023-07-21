/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.webresources;

import java.io.File;
import java.io.InputStream;

import java.util.Arrays;
import java.util.Set;

import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.util.ResourceSet;
import org.apache.catalina.webresources.AbstractFileResourceSet;
import org.apache.catalina.webresources.EmptyResource;
import org.apache.catalina.webresources.FileResource;

/**
 * @author Shuyang Zhou
 */
public class ExtResourceSet extends AbstractFileResourceSet {

	public ExtResourceSet() {
		super("/");
	}

	@Override
	public WebResource getResource(String path) {
		checkPath(path);

		String webAppMount = getWebAppMount();

		WebResourceRoot webResourceRoot = getRoot();

		if (!path.startsWith(webAppMount)) {
			return new EmptyResource(webResourceRoot, path);
		}

		File file = file(path.substring(webAppMount.length()), false);

		if ((file == null) || !file.exists() || file.isDirectory()) {
			return new EmptyResource(webResourceRoot, path);
		}

		return new FileResource(
			webResourceRoot, path, file, true, getManifest());
	}

	@Override
	public String[] list(String path) {
		checkPath(path);

		if (!path.equals(getWebAppMount())) {
			return EMPTY_STRING_ARRAY;
		}

		File extBaseFile = getFileBase();

		if (!extBaseFile.exists()) {
			return EMPTY_STRING_ARRAY;
		}

		String[] extFileNames = extBaseFile.list();

		Arrays.sort(extFileNames);

		return extFileNames;
	}

	@Override
	public Set<String> listWebAppPaths(String path) {
		checkPath(path);

		String webAppMount = getWebAppMount();

		ResourceSet<String> resourceSet = new ResourceSet<>();

		if (path.startsWith(webAppMount)) {
			File extBaseFile = getFileBase();

			File[] files = extBaseFile.listFiles();

			if (files != null) {
				if (path.charAt(path.length() - 1) != '/') {
					path = path.concat("/");
				}

				for (File file : files) {
					if (file.isFile()) {
						resourceSet.add(path.concat(file.getName()));
					}
				}
			}
		}

		resourceSet.setLocked(true);

		return resourceSet;
	}

	@Override
	public boolean mkdir(String path) {
		return false;
	}

	@Override
	public boolean write(String path, InputStream is, boolean overwrite) {
		return false;
	}

	@Override
	protected void checkType(File file) {
		if (file.exists() && !file.isDirectory()) {
			throw new IllegalArgumentException(file + " is not a directory");
		}
	}

}