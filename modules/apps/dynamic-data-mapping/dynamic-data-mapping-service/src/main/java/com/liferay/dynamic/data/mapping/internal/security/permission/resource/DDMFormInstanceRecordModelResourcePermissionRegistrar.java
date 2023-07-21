/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.security.permission.resource;

import com.liferay.dynamic.data.mapping.constants.DDMConstants;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = {})
public class DDMFormInstanceRecordModelResourcePermissionRegistrar {

	@Activate
	public void activate(BundleContext bundleContext) {
		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"model.class.name", DDMFormInstanceRecord.class.getName());

		_serviceRegistration = bundleContext.registerService(
			ModelResourcePermission.class,
			ModelResourcePermissionFactory.create(
				DDMFormInstanceRecord.class,
				DDMFormInstanceRecord::getFormInstanceRecordId,
				_ddmFormInstanceRecordLocalService::getDDMFormInstanceRecord,
				_portletResourcePermission,
				(modelResourcePermission, consumer) -> {
					consumer.accept(
						new DDMFormInstanceRecordAutosavedModelResourcePermissionLogic());
					consumer.accept(
						new DDMFormInstanceRecordInheritanceModelResourcePermissionLogic());
				}),
			properties);
	}

	@Deactivate
	public void deactivate() {
		_serviceRegistration.unregister();
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstance)"
	)
	private ModelResourcePermission<DDMFormInstance>
		_ddmFormInstanceModelResourcePermission;

	@Reference
	private DDMFormInstanceRecordLocalService
		_ddmFormInstanceRecordLocalService;

	@Reference(target = "(resource.name=" + DDMConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	private ServiceRegistration<ModelResourcePermission> _serviceRegistration;

	@Reference
	private StagingPermission _stagingPermission;

	private class DDMFormInstanceRecordAutosavedModelResourcePermissionLogic
		implements ModelResourcePermissionLogic<DDMFormInstanceRecord> {

		@Override
		public Boolean contains(
				PermissionChecker permissionChecker, String name,
				DDMFormInstanceRecord formInstanceRecord, String actionId)
			throws PortalException {

			if (!actionId.equals(ActionKeys.UPDATE)) {
				return null;
			}

			if ((formInstanceRecord.getStatus() ==
					WorkflowConstants.STATUS_DRAFT) &&
				(formInstanceRecord.getUserId() ==
					permissionChecker.getUserId())) {

				return true;
			}

			return null;
		}

	}

	private class DDMFormInstanceRecordInheritanceModelResourcePermissionLogic
		implements ModelResourcePermissionLogic<DDMFormInstanceRecord> {

		@Override
		public Boolean contains(
				PermissionChecker permissionChecker, String name,
				DDMFormInstanceRecord formInstanceRecord, String actionId)
			throws PortalException {

			return _ddmFormInstanceModelResourcePermission.contains(
				permissionChecker, formInstanceRecord.getFormInstance(),
				actionId);
		}

	}

}