/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.service.test;

import com.liferay.portlet.usersadmin.util.OrganizationIndexer;

import org.junit.Assert;

/**
 * @author Michael C. Han
 */
public class TestAssetIndexer extends OrganizationIndexer {

	@Override
	public void reindex(String className, long classPK) {
		Assert.assertEquals(_className, className);
		Assert.assertEquals(_classPK, classPK);
	}

	public void setExpectedValues(String className, long classPK) {
		_className = className;
		_classPK = classPK;
	}

	private String _className;
	private long _classPK;

}