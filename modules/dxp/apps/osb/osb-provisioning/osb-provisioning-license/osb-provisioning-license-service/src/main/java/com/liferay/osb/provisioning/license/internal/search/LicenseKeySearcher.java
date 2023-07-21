/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.internal.search;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.search.BaseSearcher;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.provisioning.license.model.LicenseKey",
	service = BaseSearcher.class
)
public class LicenseKeySearcher extends BaseSearcher {

	public static final String CLASS_NAME = LicenseKey.class.getName();

	public LicenseKeySearcher() {
		setFilterSearch(true);
		setPermissionAware(false);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

}