/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.service.impl;

import com.liferay.marketplace.exception.ModuleNamespaceException;
import com.liferay.marketplace.model.App;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.base.ModuleLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
@Component(
	property = "module.class.name=com.liferay.marketplace.model.Module",
	service = AopService.class
)
public class ModuleLocalServiceImpl extends ModuleLocalServiceBaseImpl {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link #addModule(long,
	 *             String, String, String)}
	 */
	@Deprecated
	@Override
	public Module addModule(
			long userId, long appId, String bundleSymbolicName,
			String bundleVersion, String contextName)
		throws PortalException {

		return addModule(appId, bundleSymbolicName, bundleVersion, contextName);
	}

	@Override
	public Module addModule(
			long appId, String bundleSymbolicName, String bundleVersion,
			String contextName)
		throws PortalException {

		Module module = fetchModule(
			appId, bundleSymbolicName, bundleVersion, contextName);

		if (module != null) {
			return module;
		}

		App app = appPersistence.findByPrimaryKey(appId);

		validate(bundleSymbolicName, contextName);

		long moduleId = counterLocalService.increment();

		module = modulePersistence.create(moduleId);

		module.setModuleId(moduleId);

		module.setCompanyId(app.getCompanyId());
		module.setAppId(appId);
		module.setBundleSymbolicName(bundleSymbolicName);
		module.setBundleVersion(bundleVersion);
		module.setContextName(contextName);

		return modulePersistence.update(module);
	}

	@Override
	public void deleteModules(long appId) {
		modulePersistence.removeByAppId(appId);
	}

	@Override
	public Module fetchModule(
		long appId, String bundleSymbolicName, String bundleVersion,
		String contextName) {

		if (Validator.isNotNull(bundleSymbolicName)) {
			return modulePersistence.fetchByA_BSN_BV(
				appId, bundleSymbolicName, bundleVersion);
		}
		else if (Validator.isNotNull(contextName)) {
			return modulePersistence.fetchByA_CN(appId, contextName);
		}

		return null;
	}

	@Override
	public List<Module> getModules(long appId) {
		return modulePersistence.findByAppId(appId);
	}

	protected void validate(String bundleSymbolicName, String contextName)
		throws PortalException {

		if (Validator.isNull(bundleSymbolicName) &&
			Validator.isNull(contextName)) {

			throw new ModuleNamespaceException();
		}
	}

}