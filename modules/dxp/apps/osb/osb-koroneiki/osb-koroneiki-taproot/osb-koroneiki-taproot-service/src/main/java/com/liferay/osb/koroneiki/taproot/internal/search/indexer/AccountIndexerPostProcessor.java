/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search.indexer;

import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.NestedQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.taproot.model.Account",
	service = IndexerPostProcessor.class
)
public class AccountIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessFullQuery(
			BooleanQuery fullQuery, SearchContext searchContext)
		throws Exception {

		List<BooleanClause<Query>> booleanClauses = fullQuery.clauses();

		for (BooleanClause<Query> booleanClause : booleanClauses) {
			Query query = booleanClause.getClause();

			_processBooleanFilter(query.getPreBooleanFilter());
		}
	}

	private Query _getActiveQuery(String productEntryKey)
		throws ParseException {

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.addRequiredTerm(
			"productPurchases.productEntryKey", productEntryKey);
		booleanQuery.addRequiredTerm(
			"productPurchases.status", WorkflowConstants.STATUS_APPROVED);

		Date now = new Date();

		TermRangeQuery startRangeTermQuery = new TermRangeQueryImpl(
			"productPurchases.startDate", null, _dateFormat.format(now), true,
			true);

		booleanQuery.add(startRangeTermQuery, BooleanClauseOccur.MUST);

		TermRangeQuery endRangeTermQuery = new TermRangeQueryImpl(
			"productPurchases.endDate", _dateFormat.format(now), null, true,
			true);

		booleanQuery.add(endRangeTermQuery, BooleanClauseOccur.MUST);

		return new NestedQuery("productPurchases", booleanQuery);
	}

	private Query _getCancelledQuery(String productEntryKey)
		throws ParseException {

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.add(
			_getActiveQuery(productEntryKey), BooleanClauseOccur.MUST_NOT);
		booleanQuery.add(
			_getExpiredQuery(productEntryKey), BooleanClauseOccur.MUST_NOT);
		booleanQuery.add(
			_getUnactivatedQuery(productEntryKey), BooleanClauseOccur.MUST_NOT);

		BooleanQuery cancelledQuery = new BooleanQueryImpl();

		cancelledQuery.addRequiredTerm(
			"productPurchases.productEntryKey", productEntryKey);
		cancelledQuery.addRequiredTerm(
			"productPurchases.status", WorkflowConstants.STATUS_CANCELLED);

		booleanQuery.add(
			new NestedQuery("productPurchases", cancelledQuery),
			BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private Query _getExpiredQuery(String productEntryKey)
		throws ParseException {

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.add(
			_getActiveQuery(productEntryKey), BooleanClauseOccur.MUST_NOT);
		booleanQuery.add(
			_getUnactivatedQuery(productEntryKey), BooleanClauseOccur.MUST_NOT);

		BooleanQuery expiredQuery = new BooleanQueryImpl();

		expiredQuery.addRequiredTerm(
			"productPurchases.productEntryKey", productEntryKey);
		expiredQuery.addRequiredTerm(
			"productPurchases.status", WorkflowConstants.STATUS_APPROVED);

		Date now = new Date();

		TermRangeQuery endRangeTermQuery = new TermRangeQueryImpl(
			"productPurchases.endDate", null, _dateFormat.format(now), true,
			true);

		expiredQuery.add(endRangeTermQuery, BooleanClauseOccur.MUST);

		booleanQuery.add(
			new NestedQuery("productPurchases", expiredQuery),
			BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private Query _getUnactivatedQuery(String productEntryKey)
		throws ParseException {

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.add(
			_getActiveQuery(productEntryKey), BooleanClauseOccur.MUST_NOT);

		BooleanQuery unactivatedQuery = new BooleanQueryImpl();

		unactivatedQuery.addRequiredTerm(
			"productPurchases.productEntryKey", productEntryKey);
		unactivatedQuery.addRequiredTerm(
			"productPurchases.status", WorkflowConstants.STATUS_APPROVED);

		Date now = new Date();

		TermRangeQuery startRangeTermQuery = new TermRangeQueryImpl(
			"productPurchases.startDate", _dateFormat.format(now), null, true,
			true);

		unactivatedQuery.add(startRangeTermQuery, BooleanClauseOccur.MUST);

		booleanQuery.add(
			new NestedQuery("productPurchases", unactivatedQuery),
			BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private void _processBooleanClauses(
			BooleanFilter booleanFilter,
			List<BooleanClause<Filter>> booleanClauses,
			BooleanClauseOccur booleanClauseOccur)
		throws Exception {

		List<String> activeProductEntryKeys = new ArrayList<>();
		List<String> cancelledProductEntryKeys = new ArrayList<>();
		List<String> expiredProductEntryKeys = new ArrayList<>();
		List<String> unactivatedProductEntryKeys = new ArrayList<>();

		Iterator<BooleanClause<Filter>> iterator = booleanClauses.iterator();

		while (iterator.hasNext()) {
			BooleanClause<Filter> booleanClause = iterator.next();

			Filter filter = booleanClause.getClause();

			if (filter instanceof BooleanFilter) {
				_processBooleanFilter((BooleanFilter)filter);
			}
			else if (filter instanceof TermFilter) {
				TermFilter termFilter = (TermFilter)filter;

				String field = termFilter.getField();

				if (field.equals("activeProductKeys")) {
					activeProductEntryKeys.add(termFilter.getValue());

					iterator.remove();
				}
				else if (field.equals("cancelledProductKeys")) {
					cancelledProductEntryKeys.add(termFilter.getValue());

					iterator.remove();
				}
				else if (field.equals("expiredProductKeys")) {
					expiredProductEntryKeys.add(termFilter.getValue());

					iterator.remove();
				}
				else if (field.equals("unactivatedProductKeys")) {
					unactivatedProductEntryKeys.add(termFilter.getValue());

					iterator.remove();
				}
			}
		}

		for (String productEntryKey : activeProductEntryKeys) {
			booleanFilter.add(
				new QueryFilter(_getActiveQuery(productEntryKey)),
				booleanClauseOccur);
		}

		for (String productEntryKey : cancelledProductEntryKeys) {
			booleanFilter.add(
				new QueryFilter(_getCancelledQuery(productEntryKey)),
				booleanClauseOccur);
		}

		for (String productEntryKey : expiredProductEntryKeys) {
			booleanFilter.add(
				new QueryFilter(_getExpiredQuery(productEntryKey)),
				booleanClauseOccur);
		}

		for (String productEntryKey : unactivatedProductEntryKeys) {
			booleanFilter.add(
				new QueryFilter(_getUnactivatedQuery(productEntryKey)),
				booleanClauseOccur);
		}
	}

	private void _processBooleanFilter(BooleanFilter booleanFilter)
		throws Exception {

		if (booleanFilter != null) {
			_processBooleanClauses(
				booleanFilter, booleanFilter.getMustBooleanClauses(),
				BooleanClauseOccur.MUST);
			_processBooleanClauses(
				booleanFilter, booleanFilter.getMustNotBooleanClauses(),
				BooleanClauseOccur.MUST_NOT);
			_processBooleanClauses(
				booleanFilter, booleanFilter.getShouldBooleanClauses(),
				BooleanClauseOccur.SHOULD);
		}
	}

	private static final String _INDEX_DATE_FORMAT_PATTERN = PropsUtil.get(
		PropsKeys.INDEX_DATE_FORMAT_PATTERN);

	private final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			_INDEX_DATE_FORMAT_PATTERN);

}