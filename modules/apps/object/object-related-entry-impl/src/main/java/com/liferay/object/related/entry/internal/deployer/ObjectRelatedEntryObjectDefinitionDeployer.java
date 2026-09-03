/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.deployer;

import com.liferay.object.deployer.ObjectDefinitionDeployer;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.related.entry.internal.helper.ObjectRelatedEntryHelper;
import com.liferay.object.related.entry.internal.search.ObjectEntryRelatedEntryIndexer;
import com.liferay.object.related.entry.internal.search.spi.model.index.contributor.ObjectEntryRelatedEntryModelDocumentContributor;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.search.RelatedEntryIndexer;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(service = ObjectDefinitionDeployer.class)
public class ObjectRelatedEntryObjectDefinitionDeployer
	implements ObjectDefinitionDeployer {

	@Override
	public List<ServiceRegistration<?>> deploy(
		ObjectDefinition objectDefinition) {

		if (!_objectRelatedEntryHelper.isRelatedEntryObjectDefinition(
				objectDefinition)) {

			return Collections.emptyList();
		}

		return ListUtil.fromArray(
			_bundleContext.registerService(
				(Class<ModelDocumentContributor<?>>)
					(Class<?>)ModelDocumentContributor.class,
				new ObjectEntryRelatedEntryModelDocumentContributor(
					_objectRelatedEntryHelper, _portal),
				HashMapDictionaryBuilder.<String, Object>put(
					"indexer.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				RelatedEntryIndexer.class,
				new ObjectEntryRelatedEntryIndexer(
					objectDefinition, _objectEntryLocalService,
					_objectRelatedEntryHelper),
				HashMapDictionaryBuilder.<String, Object>put(
					"related.entry.indexer.class.name",
					objectDefinition.getClassName()
				).build()));
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private BundleContext _bundleContext;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectRelatedEntryHelper _objectRelatedEntryHelper;

	@Reference
	private Portal _portal;

}