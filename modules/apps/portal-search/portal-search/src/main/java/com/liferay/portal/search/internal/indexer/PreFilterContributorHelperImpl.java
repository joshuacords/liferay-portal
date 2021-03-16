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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.internal.SearchPermissionCheckerImpl.SearchPermissionContext;
import com.liferay.portal.search.permission.SearchPermissionFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = PreFilterContributorHelper.class)
public class PreFilterContributorHelperImpl
	implements PreFilterContributorHelper {

	@Override
	public void contribute(
		BooleanFilter booleanFilter,
		Map<String, Indexer<?>> entryClassNameIndexerMap,
		SearchContext searchContext) {

		_addPreFilters(booleanFilter, searchContext);

		BooleanFilter preFilterBooleanFilter = new BooleanFilter();

		for (Map.Entry<String, Indexer<?>> entry :
				entryClassNameIndexerMap.entrySet()) {

			String entryClassName = entry.getKey();
			Indexer<?> indexer = entry.getValue();

			preFilterBooleanFilter.add(
				_createPreFilterForEntryClassName(
					entryClassName, indexer, searchContext),
				BooleanClauseOccur.SHOULD);
		}

		if (searchContext.getAttribute("testMoveupRolesTermsFilter") != null) {
			//need to add permissions clause at higher level alongside companyId
			_addRolesTermsFilter(booleanFilter, searchContext);
		}

		if (preFilterBooleanFilter.hasClauses()) {
			booleanFilter.add(preFilterBooleanFilter, BooleanClauseOccur.MUST);
		}
	}

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		_addModelProvidedPreFilters(
			booleanFilter, modelSearchSettings, searchContext);
	}

	protected boolean shouldSuppressIndexerProvidedClauses(
		SearchContext searchContext) {

		return GetterUtil.getBoolean(
			searchContext.getAttribute(
				"search.full.query.suppress.indexer.provided.clauses"));
	}

	@Reference
	protected ModelPreFilterContributorsHolder modelPreFilterContributorsHolder;

	@Reference
	protected QueryPreFilterContributorsHolder queryPreFilterContributorsHolder;

	@Reference
	protected SearchPermissionChecker searchPermissionChecker;

	@Reference
	protected SearchPermissionFilterContributorsHolder
		searchPermissionFilterContributorsHolder;

	private void _addIndexerProvidedPreFilters(
		BooleanFilter booleanFilter, Indexer<?> indexer,
		SearchContext searchContext) {

		if (shouldSuppressIndexerProvidedClauses(searchContext)) {
			return;
		}

		try {
			indexer.postProcessContextBooleanFilter(
				booleanFilter, searchContext);

			for (IndexerPostProcessor indexerPostProcessor :
					indexer.getIndexerPostProcessors()) {

				indexerPostProcessor.postProcessContextBooleanFilter(
					booleanFilter, searchContext);
			}
		}
		catch (RuntimeException runtimeException) {
			throw runtimeException;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _addModelProvidedPreFilters(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		if (shouldSuppressIndexerProvidedClauses(searchContext)) {
			return;
		}

		modelPreFilterContributorsHolder.forEach(
			modelSearchSettings.getClassName(),
			modelPreFilterContributor -> modelPreFilterContributor.contribute(
				booleanFilter, modelSearchSettings, searchContext));
	}

	private void _addPermissionFilter(
		BooleanFilter booleanFilter, String entryClassName,
		SearchContext searchContext) {

		if (searchContext.getUserId() == 0) {
			return;
		}

		Optional<String> optional = _getParentEntryClassNameOptional(
			entryClassName);

		String permissionedEntryClassName = optional.orElse(entryClassName);

		searchPermissionChecker.getPermissionBooleanFilter(
			searchContext.getCompanyId(), searchContext.getGroupIds(),
			searchContext.getUserId(), permissionedEntryClassName,
			booleanFilter, searchContext);
	}

	private void _addPreFilters(
		BooleanFilter booleanFilter, SearchContext searchContext) {

		queryPreFilterContributorsHolder.forEach(
			queryPreFilterContributor -> queryPreFilterContributor.contribute(
				booleanFilter, searchContext));
	}

	private void _addRolesTermsFilter(
		BooleanFilter booleanFilter, SearchContext searchContext) {

		long userId = searchContext.getUserId();

		if (userId == 0) {
			return;
		}

		SearchPermissionContext searchPermissionContext = null;

		try {
			searchPermissionContext =
				(SearchPermissionContext)searchContext.getAttribute(
					"searchPermissionContext");
		}
		catch (Exception exception) {
			return;
		}

		TermsFilter groupRolesTermsFilter =
			searchPermissionContext._groupRolesTermsFilter;

		TermsFilter rolesTermsFilter =
			searchPermissionContext._rolesTermsFilter;

		if (!rolesTermsFilter.isEmpty()) {
			BooleanFilter permissionClauseFilter = new BooleanFilter();

			if (userId > 0) {
				//From SearchPermissionCheckerImpl._getPermissionFilter
				permissionClauseFilter.add(
					new TermFilter(Field.USER_ID, String.valueOf(userId)),
					BooleanClauseOccur.SHOULD);

				//From SharingEntrySearchPermissionFilterContributor
				TermsFilter termsFilter = new TermsFilter("sharedToUserId");

				termsFilter.addValue(String.valueOf(userId));

				_add(permissionClauseFilter, termsFilter);
			}

			_add(permissionClauseFilter, groupRolesTermsFilter);
			_add(permissionClauseFilter, rolesTermsFilter);

			_contributeUserSearchPermissionFilters(booleanFilter);

			booleanFilter.add(permissionClauseFilter, BooleanClauseOccur.MUST);
		}
	}

	private void _add(BooleanFilter booleanFilter, TermsFilter termsFilter) {
		if (!termsFilter.isEmpty()) {
			booleanFilter.add(termsFilter, BooleanClauseOccur.SHOULD);
		}
	}

	private void _contributeUserSearchPermissionFilters(
		BooleanFilter booleanFilter) {

		for (BooleanClause<Filter> clause :
				booleanFilter.getShouldBooleanClauses()) {

			if (clause.getClause() instanceof TermsFilter) {
				TermsFilter termsFilter = (TermsFilter)clause.getClause();

				String field = termsFilter.getField();

				if (field.equals(Field.ROLE_ID)) {
					TermsFilter roleIdsTermsFilter = new TermsFilter(
						Field.ROLE_IDS);

					roleIdsTermsFilter.addValues(termsFilter.getValues());

					booleanFilter.add(roleIdsTermsFilter);

					break;
				}
			}
		}
	}

	// From UserSearchPermissionFilterContributor
	private Filter _createPreFilterForEntryClassName(
		String entryClassName, Indexer<?> indexer,
		SearchContext searchContext) {

		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addTerm(
			Field.ENTRY_CLASS_NAME, entryClassName, BooleanClauseOccur.MUST);

		_addPermissionFilter(booleanFilter, entryClassName, searchContext);

		_addIndexerProvidedPreFilters(booleanFilter, indexer, searchContext);

		_addModelProvidedPreFilters(
			booleanFilter, _getModelSearchSettings(indexer), searchContext);

		return booleanFilter;
	}

	private ModelSearchSettings _getModelSearchSettings(Indexer<?> indexer) {
		ModelSearchSettingsImpl modelSearchSettingsImpl =
			new ModelSearchSettingsImpl(indexer.getClassName());

		modelSearchSettingsImpl.setStagingAware(indexer.isStagingAware());

		return modelSearchSettingsImpl;
	}

	private Optional<String> _getParentEntryClassNameOptional(
		String entryClassName) {

		Stream<SearchPermissionFilterContributor> stream =
			searchPermissionFilterContributorsHolder.getAll();

		List<SearchPermissionFilterContributor> list = stream.collect(
			Collectors.toList());

		for (SearchPermissionFilterContributor
				searchPermissionFilterContributor : list) {

			Optional<String> parentEntryClassNameOptional =
				searchPermissionFilterContributor.
					getParentEntryClassNameOptional(entryClassName);

			if ((parentEntryClassNameOptional != null) &&
				parentEntryClassNameOptional.isPresent()) {

				return parentEntryClassNameOptional;
			}
		}

		return Optional.empty();
	}

}