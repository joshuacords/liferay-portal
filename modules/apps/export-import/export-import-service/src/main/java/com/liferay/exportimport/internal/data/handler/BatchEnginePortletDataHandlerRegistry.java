/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.data.handler;

import com.liferay.batch.engine.BatchEngineExportTaskExecutor;
import com.liferay.batch.engine.BatchEngineImportTaskExecutor;
import com.liferay.batch.engine.BatchEngineTaskItemDelegateRegistry;
import com.liferay.batch.engine.service.BatchEngineExportTaskService;
import com.liferay.batch.engine.service.BatchEngineImportTaskService;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.vulcan.batch.engine.ExportImportVulcanBatchEngineTaskItemDelegate;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.feature.flag.FeatureFlagListener;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.batch.engine.VulcanBatchEngineTaskItemDelegate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Alejandro Tardín
 */
@Component(service = {})
public class BatchEnginePortletDataHandlerRegistry {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceRegistration = bundleContext.registerService(
			FeatureFlagListener.class,
			(companyId, featureFlagKey, enabled) -> {
				if (enabled) {
					_serviceTrackers.put(
						companyId,
						ServiceTrackerFactory.open(
							bundleContext,
							"(batch.engine.task.item.delegate=true)",
							new VulcanBatchEngineTaskItemDelegateServiceTrackerCustomizer(
								bundleContext, companyId)));
				}
				else {
					ServiceTracker
						<VulcanBatchEngineTaskItemDelegate,
						 ServiceRegistration<PortletDataHandler>>
							serviceTracker = _serviceTrackers.remove(companyId);

					if (serviceTracker != null) {
						serviceTracker.close();
					}
				}
			},
			MapUtil.singletonDictionary("feature.flag.key", "LPD-35914"));
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();

		for (ServiceTracker
				<VulcanBatchEngineTaskItemDelegate,
				 ServiceRegistration<PortletDataHandler>> serviceTracker :
					_serviceTrackers.values()) {

			serviceTracker.close();
		}
	}

	@Reference
	private BatchEngineExportTaskExecutor _batchEngineExportTaskExecutor;

	@Reference
	private BatchEngineExportTaskService _batchEngineExportTaskService;

	@Reference
	private BatchEngineImportTaskExecutor _batchEngineImportTaskExecutor;

	@Reference
	private BatchEngineImportTaskService _batchEngineImportTaskService;

	@Reference
	private BatchEngineTaskItemDelegateRegistry
		_batchEngineTaskItemDelegateRegistry;

	@Reference
	private CompanyLocalService _companyLocalService;

	private volatile ServiceRegistration<FeatureFlagListener>
		_serviceRegistration;
	private final Map
		<Long,
		 ServiceTracker
			 <VulcanBatchEngineTaskItemDelegate,
			  ServiceRegistration<PortletDataHandler>>> _serviceTrackers =
				new ConcurrentHashMap<>();

	@Reference
	private UserLocalService _userLocalService;

	private class VulcanBatchEngineTaskItemDelegateServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<VulcanBatchEngineTaskItemDelegate,
			 ServiceRegistration<PortletDataHandler>> {

		public VulcanBatchEngineTaskItemDelegateServiceTrackerCustomizer(
			BundleContext bundleContext, long companyId) {

			_bundleContext = bundleContext;
			_companyId = companyId;
		}

		@Override
		public ServiceRegistration<PortletDataHandler> addingService(
			ServiceReference<VulcanBatchEngineTaskItemDelegate>
				serviceReference) {

			VulcanBatchEngineTaskItemDelegate<?>
				vulcanBatchEngineTaskItemDelegate = _bundleContext.getService(
					serviceReference);

			if (!(vulcanBatchEngineTaskItemDelegate instanceof
					ExportImportVulcanBatchEngineTaskItemDelegate<?>
						exportImportVulcanBatchEngineTaskItemDelegate)) {

				return null;
			}

			ExportImportVulcanBatchEngineTaskItemDelegate.ExportImportDescriptor
				exportImportDescriptor =
					exportImportVulcanBatchEngineTaskItemDelegate.
						getExportImportDescriptor();

			String portletId = exportImportDescriptor.getPortletId();

			if (Validator.isNull(portletId)) {
				return null;
			}

			BatchEnginePortletDataHandler
				previousBatchEnginePortletDataHandler =
					_batchEnginePortletDataHandlers.get(portletId);

			BatchEnginePortletDataHandler batchEnginePortletDataHandler =
				previousBatchEnginePortletDataHandler;

			if (previousBatchEnginePortletDataHandler == null) {
				batchEnginePortletDataHandler =
					new BatchEnginePortletDataHandler(
						_batchEngineExportTaskExecutor,
						_batchEngineExportTaskService,
						_batchEngineImportTaskExecutor,
						_batchEngineImportTaskService,
						_batchEngineTaskItemDelegateRegistry,
						_companyLocalService, portletId, _userLocalService);

				_batchEnginePortletDataHandlers.put(
					portletId, batchEnginePortletDataHandler);
			}

			batchEnginePortletDataHandler.
				registerExportImportVulcanBatchEngineTaskItemDelegate(
					GetterUtil.getObject(
						(String)serviceReference.getProperty(
							"batch.engine.task.item.delegate.class.name"),
						() -> (String)serviceReference.getProperty(
							"batch.engine.entity.class.name")),
					exportImportDescriptor,
					(String)serviceReference.getProperty(
						"batch.engine.task.item.delegate.name"));

			if (previousBatchEnginePortletDataHandler != null) {
				return _serviceRegistrations.get(portletId);
			}

			ServiceRegistration<PortletDataHandler> serviceRegistration =
				_bundleContext.registerService(
					PortletDataHandler.class, batchEnginePortletDataHandler,
					HashMapDictionaryBuilder.<String, Object>put(
						"batch.engine.task.item.delegate.item.class.name",
						exportImportDescriptor.getItemClassName()
					).put(
						"company.id", () -> _companyId
					).put(
						"jakarta.portlet.name", portletId
					).put(
						"service.ranking", Integer.MAX_VALUE
					).build());

			_serviceRegistrations.put(portletId, serviceRegistration);

			return serviceRegistration;
		}

		@Override
		public void modifiedService(
			ServiceReference<VulcanBatchEngineTaskItemDelegate>
				serviceReference,
			ServiceRegistration<PortletDataHandler> serviceRegistration) {

			removedService(serviceReference, serviceRegistration);

			addingService(serviceReference);
		}

		@Override
		public void removedService(
			ServiceReference<VulcanBatchEngineTaskItemDelegate>
				serviceReference,
			ServiceRegistration<PortletDataHandler> serviceRegistration) {

			VulcanBatchEngineTaskItemDelegate<?>
				vulcanBatchEngineTaskItemDelegate = _bundleContext.getService(
					serviceReference);

			if (!(vulcanBatchEngineTaskItemDelegate instanceof
					ExportImportVulcanBatchEngineTaskItemDelegate<?>
						exportImportVulcanBatchEngineTaskItemDelegate)) {

				return;
			}

			ExportImportVulcanBatchEngineTaskItemDelegate.ExportImportDescriptor
				exportImportDescriptor =
					exportImportVulcanBatchEngineTaskItemDelegate.
						getExportImportDescriptor();

			String portletId = exportImportDescriptor.getPortletId();

			if (Validator.isNull(portletId)) {
				return;
			}

			BatchEnginePortletDataHandler batchEnginePortletDataHandler =
				_batchEnginePortletDataHandlers.get(portletId);

			if (batchEnginePortletDataHandler == null) {
				return;
			}

			String className = GetterUtil.getObject(
				(String)serviceReference.getProperty(
					"batch.engine.task.item.delegate.class.name"),
				() -> (String)serviceReference.getProperty(
					"batch.engine.entity.class.name"));

			String taskItemDelegateName = (String)serviceReference.getProperty(
				"batch.engine.task.item.delegate.name");

			batchEnginePortletDataHandler.
				unregisterExportImportVulcanBatchEngineTaskItemDelegate(
					className, taskItemDelegateName);

			if (batchEnginePortletDataHandler.getClassNames().length == 0) {
				serviceRegistration.unregister();
				_batchEnginePortletDataHandlers.remove(portletId);
			}
		}

		private final Map<String, BatchEnginePortletDataHandler>
			_batchEnginePortletDataHandlers = new HashMap<>();
		private final BundleContext _bundleContext;
		private final long _companyId;
		private final Map<String, ServiceRegistration<PortletDataHandler>>
			_serviceRegistrations = new HashMap<>();

	}

}