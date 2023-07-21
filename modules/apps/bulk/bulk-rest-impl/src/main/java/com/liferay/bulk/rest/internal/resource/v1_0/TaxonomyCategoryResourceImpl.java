/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bulk.rest.internal.resource.v1_0;

import com.liferay.bulk.rest.dto.v1_0.TaxonomyCategoryBulkSelection;
import com.liferay.bulk.rest.internal.selection.v1_0.DocumentBulkSelectionFactory;
import com.liferay.bulk.rest.resource.v1_0.TaxonomyCategoryResource;
import com.liferay.bulk.selection.BulkSelection;
import com.liferay.bulk.selection.BulkSelectionInputParameters;
import com.liferay.bulk.selection.BulkSelectionRunner;
import com.liferay.document.library.bulk.selection.EditCategoriesBulkSelectionAction;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.Serializable;

import java.util.HashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alejandro Tardín
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/taxonomy-category.properties",
	scope = ServiceScope.PROTOTYPE, service = TaxonomyCategoryResource.class
)
public class TaxonomyCategoryResourceImpl
	extends BaseTaxonomyCategoryResourceImpl {

	@Override
	public void patchTaxonomyCategoryBatch(
			TaxonomyCategoryBulkSelection taxonomyCategoryBulkSelection)
		throws Exception {

		_update(true, taxonomyCategoryBulkSelection);
	}

	@Override
	public void putTaxonomyCategoryBatch(
			TaxonomyCategoryBulkSelection documentSelection)
		throws Exception {

		_update(false, documentSelection);
	}

	private void _update(
			boolean append,
			TaxonomyCategoryBulkSelection taxonomyCategoryBulkSelection)
		throws PortalException {

		BulkSelection<?> bulkSelection = _documentBulkSelectionFactory.create(
			taxonomyCategoryBulkSelection.getDocumentBulkSelection());

		_bulkSelectionRunner.run(
			contextUser, bulkSelection.toAssetEntryBulkSelection(),
			_editCategoriesBulkSelectionAction,
			new HashMap<String, Serializable>() {
				{
					put(
						BulkSelectionInputParameters.ASSET_ENTRY_BULK_SELECTION,
						true);
					put("append", append);
					put(
						"toAddCategoryIds",
						taxonomyCategoryBulkSelection.
							getTaxonomyCategoryIdsToAdd());
					put(
						"toRemoveCategoryIds",
						taxonomyCategoryBulkSelection.
							getTaxonomyCategoryIdsToRemove());
				}
			});
	}

	@Reference
	private BulkSelectionRunner _bulkSelectionRunner;

	@Reference
	private DocumentBulkSelectionFactory _documentBulkSelectionFactory;

	@Reference
	private EditCategoriesBulkSelectionAction
		_editCategoriesBulkSelectionAction;

}