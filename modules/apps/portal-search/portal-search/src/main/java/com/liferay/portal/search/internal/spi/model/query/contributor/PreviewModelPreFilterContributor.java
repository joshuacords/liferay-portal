/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.spi.model.query.contributor;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.preview.PreviewableResolverUtil;
import com.liferay.portal.search.model.uid.UIDFactory;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(
	property = {"indexer.class.name=ALL", "indexer.clauses.mandatory=true"},
	service = ModelPreFilterContributor.class
)
public class PreviewModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		String className = modelSearchSettings.getClassName();

		Map<Serializable, Object> previewValues = getPreviewValues(className);

		if (MapUtil.isEmpty(previewValues)) {
			return;
		}

		TermsFilter uidTermsFilter = new TermsFilter(Field.UID);

		for (Serializable primaryKey : previewValues.keySet()) {
			uidTermsFilter.addValue(
				_uidFactory.getUID(
					className, primaryKey,
					CTConstants.CT_COLLECTION_ID_PRODUCTION));
		}

		booleanFilter.add(uidTermsFilter, BooleanClauseOccur.MUST_NOT);
	}

	protected static Map<Serializable, Object> getPreviewValues(
		String className) {

		if (PreviewableResolverUtil.getPreviewId() == null) {
			return null;
		}

		// TODO For Objects, className is
		// "com.liferay.object.model.ObjectDefinition#<id>" while the
		// previewable map key is ObjectEntry.class.
		// ObjectDefinitionDeployerImpl registers ObjectEntryLocalService under
		// that className, so this resolves to ObjectEntry.class, but that map
		// holds the entries of every object definition. Verify once
		// ObjectEntryLaunchEntryManager (LPD-115022) lands.

		PersistedModelLocalService persistedModelLocalService =
			PersistedModelLocalServiceRegistryUtil.
				getPersistedModelLocalService(className);

		if (persistedModelLocalService == null) {
			return null;
		}

		BasePersistence<?> basePersistence =
			persistedModelLocalService.getBasePersistence();

		return PreviewableResolverUtil.getPreviewableMap(
			basePersistence.getModelClass());
	}

	@Reference
	private UIDFactory _uidFactory;

}