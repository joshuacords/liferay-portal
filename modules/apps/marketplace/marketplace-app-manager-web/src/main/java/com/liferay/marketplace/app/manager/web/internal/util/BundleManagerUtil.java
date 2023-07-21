/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.app.manager.web.internal.util;

import com.liferay.marketplace.bundle.BundleManager;

import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true, service = {})
public class BundleManagerUtil {

	public static Bundle getBundle(String symbolicName, String version) {
		return _bundleManager.getBundle(symbolicName, version);
	}

	public static List<Bundle> getBundles() {
		return _bundleManager.getBundles();
	}

	@Reference(unbind = "-")
	protected void setBundleManger(BundleManager bundleManager) {
		_bundleManager = bundleManager;
	}

	private static BundleManager _bundleManager;

}