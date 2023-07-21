/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.vldap.server.internal.directory.FilterConstraint;
import com.liferay.vldap.server.internal.directory.SearchBase;
import com.liferay.vldap.server.internal.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DirectoryBuilder {

	public void addDirectoryBuilder(DirectoryBuilder directoryBuilder) {
		_directoryBuilders.add(directoryBuilder);
	}

	public List<Directory> buildDirectories(
			SearchBase searchBase, List<FilterConstraint> filterConstraints)
		throws Exception {

		return new ArrayList<>();
	}

	public List<Directory> buildDirectories(
		SearchBase searchBase, List<FilterConstraint> filterConstraints,
		boolean subtree) {

		if (_built) {
			return Collections.emptyList();
		}

		List<Directory> directories = null;

		try {
			directories = buildDirectories(searchBase, filterConstraints);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		if (subtree) {
			for (DirectoryBuilder directoryBuilder : _directoryBuilders) {
				directories.addAll(
					directoryBuilder.buildDirectories(
						searchBase, filterConstraints, subtree));
			}
		}

		_built = true;

		return directories;
	}

	public List<DirectoryBuilder> getDirectoryBuilders() {
		return _directoryBuilders;
	}

	public boolean isValidAttribute(String attributeId, String value) {
		return attributeValidator.isValidAttribute(attributeId, value);
	}

	public boolean isValidFilterConstraint(FilterConstraint filterConstraint) {
		Map<String, String> map = filterConstraint.getMap();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (!isValidAttribute(entry.getKey(), entry.getValue())) {
				return false;
			}
		}

		return true;
	}

	protected final AttributeValidator attributeValidator =
		new AttributeValidator();

	private static final Log _log = LogFactoryUtil.getLog(
		DirectoryBuilder.class);

	private boolean _built;
	private final List<DirectoryBuilder> _directoryBuilders = new ArrayList<>();

}