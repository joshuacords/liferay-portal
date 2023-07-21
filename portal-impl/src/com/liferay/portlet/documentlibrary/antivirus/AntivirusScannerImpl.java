/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.antivirus;

import com.liferay.document.library.kernel.antivirus.AntivirusScanner;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerWrapper;
import com.liferay.portal.kernel.util.InstancePool;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class AntivirusScannerImpl extends AntivirusScannerWrapper {

	public AntivirusScannerImpl() {
		super(null);
	}

	public void afterPropertiesSet() {
		if (Validator.isNull(PropsValues.DL_STORE_ANTIVIRUS_IMPL)) {
			return;
		}

		Registry registry = RegistryUtil.getRegistry();

		_serviceRegistration = registry.registerService(
			AntivirusScanner.class,
			(AntivirusScanner)InstancePool.get(
				PropsValues.DL_STORE_ANTIVIRUS_IMPL));
	}

	public void destroy() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Override
	public boolean isActive() {
		return _antivirusScanner.isActive();
	}

	@Override
	public void scan(byte[] bytes) throws AntivirusScannerException {
		_antivirusScanner.scan(bytes);
	}

	@Override
	public void scan(File file) throws AntivirusScannerException {
		_antivirusScanner.scan(file);
	}

	@Override
	public void scan(InputStream inputStream) throws AntivirusScannerException {
		_antivirusScanner.scan(inputStream);
	}

	private static volatile AntivirusScanner _antivirusScanner =
		ServiceProxyFactory.newServiceTrackedInstance(
			AntivirusScanner.class, AntivirusScannerImpl.class,
			"_antivirusScanner", false);

	private ServiceRegistration<AntivirusScanner> _serviceRegistration;

}