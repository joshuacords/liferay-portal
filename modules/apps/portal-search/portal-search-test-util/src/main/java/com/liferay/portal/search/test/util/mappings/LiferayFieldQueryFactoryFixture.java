/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.mappings;

import com.liferay.portal.search.internal.analysis.SimpleKeywordTokenizer;
import com.liferay.portal.search.internal.query.QueriesImpl;
import com.liferay.portal.search.internal.query.field.AssetTagNamesFieldQueryBuilderFactory;
import com.liferay.portal.search.internal.query.field.DescriptionFieldQueryBuilder;
import com.liferay.portal.search.internal.query.field.FieldQueryBuilderFactoryImpl;
import com.liferay.portal.search.internal.query.field.FieldQueryFactoryImpl;
import com.liferay.portal.search.internal.query.field.SubstringFieldQueryBuilder;
import com.liferay.portal.search.internal.query.field.TitleFieldQueryBuilder;
import com.liferay.portal.search.query.field.FieldQueryFactory;
import com.liferay.portal.search.query.field.QueryPreProcessConfiguration;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class LiferayFieldQueryFactoryFixture {

	public LiferayFieldQueryFactoryFixture() {
		QueriesImpl queriesImpl = new QueriesImpl();
		SimpleKeywordTokenizer simpleKeywordTokenizer =
			new SimpleKeywordTokenizer();

		_descriptionFieldQueryBuilder = createDescriptionFieldQueryBuilder(
			simpleKeywordTokenizer, queriesImpl);
		_substringFieldQueryBuilder = createSubstringFieldQueryBuilder(
			simpleKeywordTokenizer, queriesImpl);
		_titleFieldQueryBuilder = createTitleFieldQueryBuilder(
			simpleKeywordTokenizer, queriesImpl);

		FieldQueryBuilderFactoryImpl fieldQueryBuilderFactoryImpl =
			new FieldQueryBuilderFactoryImpl() {
				{
					descriptionFieldQueryBuilder =
						_descriptionFieldQueryBuilder;
					queryPreProcessConfiguration = Mockito.mock(
						QueryPreProcessConfiguration.class);
					substringFieldQueryBuilder = _substringFieldQueryBuilder;
					titleFieldQueryBuilder = _titleFieldQueryBuilder;
				}
			};

		AssetTagNamesFieldQueryBuilderFactory
			assetTagNamesFieldQueryBuilderFactory =
				new AssetTagNamesFieldQueryBuilderFactory() {
					{
						titleQueryBuilder = _titleFieldQueryBuilder;
					}
				};

		_fieldQueryFactory = new FieldQueryFactoryImpl() {
			{
				addFieldQueryBuilderFactory(
					assetTagNamesFieldQueryBuilderFactory);
				addFieldQueryBuilderFactory(fieldQueryBuilderFactoryImpl);
				setDescriptionFieldQueryBuilder(_descriptionFieldQueryBuilder);
			}
		};
	}

	public FieldQueryFactory getFieldQueryFactory() {
		return _fieldQueryFactory;
	}

	protected static DescriptionFieldQueryBuilder
		createDescriptionFieldQueryBuilder(
			SimpleKeywordTokenizer simpleKeywordTokenizer,
			QueriesImpl queriesImpl) {

		return new DescriptionFieldQueryBuilder() {
			{
				keywordTokenizer = simpleKeywordTokenizer;
				queries = queriesImpl;
			}
		};
	}

	protected static SubstringFieldQueryBuilder
		createSubstringFieldQueryBuilder(
			SimpleKeywordTokenizer simpleKeywordTokenizer,
			QueriesImpl queriesImpl) {

		return new SubstringFieldQueryBuilder() {
			{
				keywordTokenizer = simpleKeywordTokenizer;
				queries = queriesImpl;
			}
		};
	}

	protected static TitleFieldQueryBuilder createTitleFieldQueryBuilder(
		SimpleKeywordTokenizer simpleKeywordTokenizer,
		QueriesImpl queriesImpl) {

		return new TitleFieldQueryBuilder() {
			{
				keywordTokenizer = simpleKeywordTokenizer;
				queries = queriesImpl;
			}
		};
	}

	private final DescriptionFieldQueryBuilder _descriptionFieldQueryBuilder;
	private final FieldQueryFactory _fieldQueryFactory;
	private final SubstringFieldQueryBuilder _substringFieldQueryBuilder;
	private final TitleFieldQueryBuilder _titleFieldQueryBuilder;

}