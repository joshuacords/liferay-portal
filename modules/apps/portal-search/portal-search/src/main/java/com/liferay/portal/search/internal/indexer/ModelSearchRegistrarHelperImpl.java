/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.internal.indexer;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.search.configuration.IndexWriterHelperConfiguration;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchConfigurator;
import com.liferay.portal.search.spi.model.registrar.ModelSearchDefinition;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.registrar.contributor.ModelSearchDefinitionContributor;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(
	configurationPid = "com.liferay.portal.search.configuration.IndexWriterHelperConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = ModelSearchRegistrarHelper.class)
public class ModelSearchRegistrarHelperImpl
	implements ModelSearchRegistrarHelper {

	@Override
	public ServiceRegistration<?> register(
		Class<? extends BaseModel<?>> clazz, BundleContext bundleContext,
		ModelSearchDefinitionContributor modelSearchDefinitionContributor) {

		String className = clazz.getName();

		ModelSearchDefinitionImpl modelSearchDefinitionImpl =
			new ModelSearchDefinitionImpl(className);

		_modelSearchDefinitionImpls.add(modelSearchDefinitionImpl);

//		_modelSearchDefinitionImpl.setSearchEngineId(_searchEngineId);

		modelSearchDefinitionContributor.contribute(modelSearchDefinitionImpl);

		return bundleContext.registerService(
			ModelSearchConfigurator.class,
			new ModelSearchConfiguratorImpl<>(
				bundleContext,
				modelSearchDefinitionImpl._modelIndexWriterContributor,
				modelSearchDefinitionImpl._modelVisibilityContributor,
				modelSearchDefinitionImpl._modelSearchSettingsImpl,
				modelSearchDefinitionImpl._modelSummaryContributor),
			new Hashtable<>(
				Collections.singletonMap("indexer.class.name", className)));
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		IndexWriterHelperConfiguration indexWriterHelperConfiguration =
			ConfigurableUtil.createConfigurable(
				IndexWriterHelperConfiguration.class, properties);

		String _searchEngineId =
			indexWriterHelperConfiguration.indexSearchEngineId();

//		for (ModelSearchDefinitionImpl modelSearchDefinitionImpl : _modelSearchDefinitionImpls) {
//			modelSearchDefinitionImpl.setSearchEngineId(_searchEngineId);
//		}

		for (Indexer indexer : indexerRegistry.getIndexers()) {
			indexer.getSearchEngineId()
		}
	}

	@Reference
	protected IndexerRegistry indexerRegistry;

	private List<ModelSearchDefinitionImpl> _modelSearchDefinitionImpls =
		new LinkedList<>();

	//private String _searchEngineId = SearchEngineHelper.SYSTEM_ENGINE_ID;

	private class ModelSearchDefinitionImpl implements ModelSearchDefinition {

		public ModelSearchDefinitionImpl(String className) {
			_modelSearchSettingsImpl = new ModelSearchSettingsImpl(className);
		}

		@Override
		public void setDefaultSelectedFieldNames(
			String... defaultSelectedFieldNames) {

			_modelSearchSettingsImpl.setDefaultSelectedFieldNames(
				defaultSelectedFieldNames);
		}

		@Override
		public void setDefaultSelectedLocalizedFieldNames(
			String... defaultSelectedLocalizedFieldNames) {

			_modelSearchSettingsImpl.setDefaultSelectedLocalizedFieldNames(
				defaultSelectedLocalizedFieldNames);
		}

		@Override
		public void setModelIndexWriteContributor(
			ModelIndexerWriterContributor<?> modelIndexWriterContributor) {

			_modelIndexWriterContributor = modelIndexWriterContributor;
		}

		@Override
		public void setModelSummaryContributor(
			ModelSummaryContributor modelSummaryContributor) {

			_modelSummaryContributor = modelSummaryContributor;
		}

		@Override
		public void setModelVisibilityContributor(
			ModelVisibilityContributor modelVisibilityContributor) {

			_modelVisibilityContributor = modelVisibilityContributor;
		}

		public void setSearchEngineId(String searchEngineId) {
			_modelSearchSettingsImpl.setSearchEngineId(searchEngineId);
		}

		@Override
		public void setSearchResultPermissionFilterSuppressed(
			boolean searchResultPermissionFilterSuppressed) {

			_modelSearchSettingsImpl.setSearchResultPermissionFilterSuppressed(
				searchResultPermissionFilterSuppressed);
		}

		@Override
		public void setSelectAllLocales(boolean selectAllLocales) {
			_modelSearchSettingsImpl.setSelectAllLocales(selectAllLocales);
		}

		private ModelIndexerWriterContributor<?> _modelIndexWriterContributor;
		private final ModelSearchSettingsImpl _modelSearchSettingsImpl;
		private ModelSummaryContributor _modelSummaryContributor;
		private ModelVisibilityContributor _modelVisibilityContributor;

	}

}