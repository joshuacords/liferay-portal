/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.query.field;

import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.query.field.FieldQueryBuilder;
import com.liferay.portal.search.query.field.FieldQueryBuilderFactory;
import com.liferay.portal.search.query.field.FieldQueryFactory;

import java.util.HashSet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(service = FieldQueryFactory.class)
public class FieldQueryFactoryImpl implements FieldQueryFactory {

	@Override
	public Query createQuery(
		String fieldName, String keywords, boolean like,
		boolean splitKeywords) {

		FieldQueryBuilder fieldQueryBuilder = getQueryBuilder(fieldName);

		return fieldQueryBuilder.build(fieldName, keywords);
	}

	@Reference(unbind = "-")
	public void setDescriptionFieldQueryBuilder(
		DescriptionFieldQueryBuilder descriptionFieldQueryBuilder) {

		_descriptionFieldQueryBuilder = descriptionFieldQueryBuilder;
	}

	public void setSearchEngineInformation(
		SearchEngineInformation searchEngineInformation) {

		_searchEngineInformation = searchEngineInformation;
	}

	@Reference(unbind = "-")
	public void setTitleFieldQueryBuilder(
		TitleFieldQueryBuilder titleFieldQueryBuilder) {

		_titleFieldQueryBuilder = titleFieldQueryBuilder;
	}

	public void unsetSearchEngineInformation(
		SearchEngineInformation searchEngineInformation) {

		_searchEngineInformation = null;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void addFieldQueryBuilderFactory(
		FieldQueryBuilderFactory fieldQueryBuilderFactory) {

		_fieldQueryBuilderFactories.add(fieldQueryBuilderFactory);
	}

	protected FieldQueryBuilder getDefaultQueryBuilder() {
		if (_searchEngineInformation != null) {
			String vendor = _searchEngineInformation.getVendorString();
			String version = _searchEngineInformation.getClientVersionString();

			if ((vendor.startsWith("Elasticsearch") &&
				 version.startsWith("6")) ||
				vendor.startsWith("Solr")) {

				return _titleFieldQueryBuilder;
			}
		}

		return _descriptionFieldQueryBuilder;
	}

	protected FieldQueryBuilder getQueryBuilder(String fieldName) {
		for (FieldQueryBuilderFactory fieldQueryBuilderFactory :
				_fieldQueryBuilderFactories) {

			FieldQueryBuilder fieldQueryBuilder =
				fieldQueryBuilderFactory.getQueryBuilder(fieldName);

			if (fieldQueryBuilder != null) {
				return fieldQueryBuilder;
			}
		}

		return getDefaultQueryBuilder();
	}

	protected void removeFieldQueryBuilderFactory(
		FieldQueryBuilderFactory fieldQueryBuilderFactory) {

		_fieldQueryBuilderFactories.remove(fieldQueryBuilderFactory);
	}

	private DescriptionFieldQueryBuilder _descriptionFieldQueryBuilder;
	private final HashSet<FieldQueryBuilderFactory>
		_fieldQueryBuilderFactories = new HashSet<>();

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile SearchEngineInformation _searchEngineInformation;

	private TitleFieldQueryBuilder _titleFieldQueryBuilder;

}