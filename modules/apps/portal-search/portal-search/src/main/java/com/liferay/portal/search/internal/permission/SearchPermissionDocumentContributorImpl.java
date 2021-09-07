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

package com.liferay.portal.search.internal.permission;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.permission.SearchPermissionDocumentContributor;
import com.liferay.portal.search.spi.model.permission.SearchPermissionFieldContributor;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, service = SearchPermissionDocumentContributor.class
)
public class SearchPermissionDocumentContributorImpl
	implements SearchPermissionDocumentContributor {

	@Override
	public void addPermissionFields(long companyId, Document document) {
		long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

		String className = document.get(Field.ENTRY_CLASS_NAME);
		String classPK = document.get(Field.ENTRY_CLASS_PK);

		if (Validator.isNull(className) && Validator.isNull(classPK)) {
			className = document.get(Field.ROOT_ENTRY_CLASS_NAME);
			classPK = document.get(Field.ROOT_ENTRY_CLASS_PK);
		}

		boolean relatedEntry = GetterUtil.getBoolean(
			document.get(Field.RELATED_ENTRY));

		if (relatedEntry) {
			long classNameId = GetterUtil.getLong(
				document.get(Field.CLASS_NAME_ID));

			if (classNameId > 0) {
				className = _portal.getClassName(classNameId);
				classPK = document.get(Field.CLASS_PK);
			}
		}

		addPermissionFields(
			companyId, groupId, className, GetterUtil.getLong(classPK),
			document);
	}

	@Override
	public void addPermissionFields(
		long companyId, long groupId, String className, long classPK,
		Document document) {

		Indexer<?> indexer = _indexerRegistry.nullSafeGetIndexer(className);

		if (!indexer.isPermissionAware()) {
			return;
		}

		String viewActionId = document.get(Field.VIEW_ACTION_ID);

		if (Validator.isNull(viewActionId)) {
			viewActionId = ActionKeys.VIEW;
		}

		_addPermissionFields(
			companyId, groupId, className, classPK, viewActionId, document);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addSearchPermissionFieldContributor(
		SearchPermissionFieldContributor searchPermissionFieldContributor) {

		_searchPermissionFieldContributors.add(
			searchPermissionFieldContributor);
	}

	protected void removeSearchPermissionFieldContributor(
		SearchPermissionFieldContributor searchPermissionFieldContributor) {

		_searchPermissionFieldContributors.remove(
			searchPermissionFieldContributor);
	}

	private void _addPermissionFields(
		long companyId, long groupId, String className, long classPK,
		String viewActionId, Document document) {

		for (SearchPermissionFieldContributor searchPermissionFieldContributor :
				_searchPermissionFieldContributors) {

			searchPermissionFieldContributor.contribute(
				document, className, classPK);
		}

		SearchPermissionFields searchPermissionFields =
			_searchPermissionFieldsFactory.createSearchPermissionFields(
				companyId, groupId, className, classPK,
				_getPermissionName(document, className), viewActionId);

		if (searchPermissionFields != null) {
			document.addKeyword(
				Field.ROLE_ID, searchPermissionFields.getRoleIds());
			document.addKeyword(
				Field.GROUP_ROLE_ID, searchPermissionFields.getGroupRolesIds());
		}
	}

	private String _getPermissionName(Document document, String defaultValue) {
		String resourcePermissionName = document.get("resourcePermissionName");

		if (Validator.isNull(resourcePermissionName)) {
			return defaultValue;
		}

		return resourcePermissionName;
	}

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private Portal _portal;

	private final Collection<SearchPermissionFieldContributor>
		_searchPermissionFieldContributors = new CopyOnWriteArrayList<>();

	@Reference
	private SearchPermissionFieldsFactory _searchPermissionFieldsFactory;

}