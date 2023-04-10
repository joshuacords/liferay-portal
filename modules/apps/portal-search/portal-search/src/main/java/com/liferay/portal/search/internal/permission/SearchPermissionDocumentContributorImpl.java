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

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchResourceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.FieldArray;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.SortedArrayList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.internal.SearchPermissionFieldContributorRegistry;
import com.liferay.portal.search.permission.SearchPermissionDocumentContributor;
import com.liferay.portal.search.spi.model.permission.SearchPermissionFieldContributor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
@Component(service = SearchPermissionDocumentContributor.class)
public class SearchPermissionDocumentContributorImpl
	implements SearchPermissionDocumentContributor {

	@Override
	public void addPermissionFields(long companyId, Document document) {
		long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

		String className = document.get(Field.ENTRY_CLASS_NAME);
		String resourcePrimKey = document.get(Field.ENTRY_CLASS_PK);
		String entryClassPK = document.get("id");

		if (Validator.isNull(className) && Validator.isNull(resourcePrimKey)) {
			className = document.get(Field.ROOT_ENTRY_CLASS_NAME);
			resourcePrimKey = document.get(Field.ROOT_ENTRY_CLASS_PK);
		}

		boolean relatedEntry = GetterUtil.getBoolean(
			document.get(Field.RELATED_ENTRY));

		if (relatedEntry) {
			long classNameId = GetterUtil.getLong(
				document.get(Field.CLASS_NAME_ID));

			if (classNameId > 0) {
				className = _portal.getClassName(classNameId);
				resourcePrimKey = document.get(Field.CLASS_PK);
			}
		}

		addPermissionFields(
			companyId, groupId, className, resourcePrimKey, entryClassPK,
			document);
	}

	@Override
	public void addPermissionFields(
		long companyId, long groupId, String className, String resourcePrimKey,
		String entryClassPK, Document document) {

		Indexer<?> indexer = _indexerRegistry.nullSafeGetIndexer(className);

		if (!indexer.isPermissionAware()) {
			return;
		}

		String viewActionId = document.get(Field.VIEW_ACTION_ID);

		if (Validator.isNull(viewActionId)) {
			viewActionId = ActionKeys.VIEW;
		}

		_addPermissionFields(
			companyId, groupId, className, resourcePrimKey, entryClassPK,
			viewActionId, document);
	}

	private void _addPermissionFields(
		long companyId, long groupId, String className, String resourcePrimKey,
		String entryClassPK, String viewActionId, Document document) {

		for (SearchPermissionFieldContributor searchPermissionFieldContributor :
				_searchPermissionFieldContributorRegistry.
					getSearchPermissionFieldContributors()) {

			searchPermissionFieldContributor.contribute(
				document, className, GetterUtil.getLong(resourcePrimKey));
		}

		SearchPermissionFields searchPermissionFields = null;

		try {
			searchPermissionFields =
				_searchPermissionFieldsFactory.createSearchPermissionFields(
					companyId, groupId, className, resourcePrimKey,
					entryClassPK, _getPermissionName(document, className),
					viewActionId);
		}
		catch (NoSuchResourceException noSuchResourceException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchResourceException);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to get permission fields for class name ",
						_getPermissionName(document, className),
						" and class PK ", resourcePrimKey),
					exception);
			}
		}

		if (searchPermissionFields != null) {
			document.addKeyword(
				Field.ROLE_ID, searchPermissionFields.getRoleIds());
			document.addKeyword(
				Field.GROUP_ROLE_ID, searchPermissionFields.getGroupRoleIds());

			if (searchPermissionFields.getInheritedRoleIdCombinations() != null) {
				_addInheritedRoleIdCombinations(document, searchPermissionFields);
			}
		}
	}

	private void _addInheritedRoleIdCombinations(
		Document document, SearchPermissionFields searchPermissionFields) {
		//FieldArray lowerFieldArray = new FieldArray("");

		com.liferay.portal.kernel.search.Field lowerFieldArray =
			new com.liferay.portal.kernel.search.Field(StringPool.BLANK);

		List<Field> fields = new ArrayList<>();

		fields.add(
			new com.liferay.portal.kernel.search.Field(
				"roleIds", new String[] {"222", "333"}));

		fields.add(
			new com.liferay.portal.kernel.search.Field(
				"requiredMatches", "2"));

		fields.forEach(lowerFieldArray::addField);

		FieldArray fieldArray = new FieldArray("inheritedRoleIdArray");

		fieldArray.addField(lowerFieldArray);

		document.add(fieldArray);
	}

	private void _addInheritedRoleIdCombinations1(
		Document document, SearchPermissionFields searchPermissionFields) {
		FieldArray fieldArray = new FieldArray("inheritedRoleIdArray");

		com.liferay.portal.kernel.search.Field nestedRoleCombinationsField =
			new com.liferay.portal.kernel.search.Field(StringPool.BLANK);

		String[] inheritedRoleIdCombinations = {"11111", "22222"};

		List<Field> fields = new ArrayList<>();

		fields.add(
			new com.liferay.portal.kernel.search.Field(
				"roleIds", inheritedRoleIdCombinations));

		fields.add(
			new com.liferay.portal.kernel.search.Field(
				"requiredMatches", "2"));

		fields.forEach(nestedRoleCombinationsField::addField);

		fieldArray.addField(nestedRoleCombinationsField);

		document.add(fieldArray);
	}

	private String _getPermissionName(Document document, String defaultValue) {
		String resourcePermissionName = document.get("resourcePermissionName");

		if (Validator.isNull(resourcePermissionName)) {
			return defaultValue;
		}

		return resourcePermissionName;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchPermissionDocumentContributorImpl.class);

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private SearchPermissionFieldContributorRegistry
		_searchPermissionFieldContributorRegistry;

	@Reference
	private SearchPermissionFieldsFactory _searchPermissionFieldsFactory;

}