/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.kernel.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Balázs Sáfrány-Kovalik
 * @deprecated As of Mueller (7.2.x)
 */
@Deprecated
public class LayoutAdminSearchUtil {

	/**
	 * Returns a range of all the layouts belonging to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  userId the primary key of the user
	 * @param  privateLayout whether the layout is private to the group
	 * @param  keywords keywords
	 * @param  types layout types
	 * @param statuses the statuses
	 * @param  start the lower bound of the range of layouts
	 * @param  end the upper bound of the range of layouts (not inclusive)
	 * @param  orderByComparator the comparator to order the layouts
	 * @return the matching layouts
	 */
	public static List<Layout> getLayouts(
			long groupId, long userId, boolean privateLayout, String keywords,
			String[] types, int[] statuses, int start, int end,
			OrderByComparator<Layout> orderByComparator)
		throws PortalException {

		if (Validator.isNull(keywords)) {
			return LayoutLocalServiceUtil.getLayouts(
				groupId, privateLayout, start, end, orderByComparator);
		}

		Indexer<Layout> indexer = IndexerRegistryUtil.getIndexer(
			Layout.class.getName());

		Hits hits = indexer.search(
			_buildSearchContext(
				groupId, userId, privateLayout, keywords, types, statuses,
				start, end, orderByComparator));

		List<Document> documents = hits.toList();

		List<Layout> layouts = new ArrayList<>(documents.size());

		for (Document document : documents) {
			Layout layout = LayoutLocalServiceUtil.fetchLayout(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));

			if (layout == null) {
				indexer.delete(layout);

				continue;
			}

			layouts.add(layout);
		}

		return layouts;
	}

	/**
	 * Returns a range of all the layouts belonging to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  keywords keywords
	 * @param  types layout types
	 * @param  statuses the statuses
	 * @param  start the lower bound of the range of layouts
	 * @param  end the upper bound of the range of layouts (not inclusive)
	 * @param  orderByComparator the comparator to order the layouts
	 * @return the matching layouts
	 */
	public static List<Layout> getLayouts(
			long groupId, String keywords, String[] types, int[] statuses,
			int start, int end, OrderByComparator<Layout> orderByComparator)
		throws PortalException {

		if (Validator.isNull(keywords)) {
			return LayoutLocalServiceUtil.getLayouts(
				groupId, start, end, orderByComparator);
		}

		Indexer<Layout> indexer = IndexerRegistryUtil.getIndexer(
			Layout.class.getName());

		Hits hits = indexer.search(
			_buildSearchContext(
				groupId, null, keywords, types, statuses, start, end,
				orderByComparator));

		List<Document> documents = hits.toList();

		List<Layout> layouts = new ArrayList<>(documents.size());

		for (Document document : documents) {
			Layout layout = LayoutLocalServiceUtil.fetchLayout(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));

			if (layout == null) {
				indexer.delete(layout);

				continue;
			}

			layouts.add(layout);
		}

		return layouts;
	}

	/**
	 * Returns the number of layouts belonging to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  userId the primary key of the user
	 * @param  privateLayout whether the layout is private to the group
	 * @param  keywords keywords
	 * @param  types layout types
	 * @param  statuses the statuses
	 * @return the number of matching layouts
	 */
	public static int getLayoutsCount(
			long groupId, long userId, boolean privateLayout, String keywords,
			String[] types, int[] statuses)
		throws PortalException {

		if (Validator.isNull(keywords)) {
			return LayoutLocalServiceUtil.getLayoutsCount(
				groupId, privateLayout);
		}

		Indexer<Layout> indexer = IndexerRegistryUtil.getIndexer(
			Layout.class.getName());

		Hits hits = indexer.search(
			_buildSearchContext(
				groupId, userId, privateLayout, keywords, types, statuses,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null));

		return hits.getLength();
	}

	/**
	 * Returns the number of layouts belonging to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  keywords keywords
	 * @param  types layout types
	 * @param  statuses the statuses
	 * @return the number of matching layouts
	 */
	public static int getLayoutsCount(
			long groupId, String keywords, String[] types, int[] statuses)
		throws PortalException {

		if (Validator.isNull(keywords)) {
			return LayoutLocalServiceUtil.getLayoutsCount(groupId);
		}

		Indexer<Layout> indexer = IndexerRegistryUtil.getIndexer(
			Layout.class.getName());

		Hits hits = indexer.search(
			_buildSearchContext(
				groupId, null, keywords, types, statuses, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null));

		return hits.getLength();
	}

	private static SearchContext _buildSearchContext(
			long groupId, Boolean privateLayout, String keywords,
			String[] types, int[] statuses, int start, int end,
			OrderByComparator<Layout> orderByComparator)
		throws PortalException {

		return _buildSearchContext(
			groupId, 0, privateLayout, keywords, types, statuses, start, end,
			orderByComparator);
	}

	private static SearchContext _buildSearchContext(
			long groupId, long userId, Boolean privateLayout, String keywords,
			String[] types, int[] statuses, int start, int end,
			OrderByComparator<Layout> orderByComparator)
		throws PortalException {

		SearchContext searchContext = new SearchContext();

		if (ArrayUtil.isNotEmpty(statuses)) {
			searchContext.setAttribute(Field.STATUS, statuses);
		}

		searchContext.setAttribute(Field.TITLE, keywords);
		searchContext.setAttribute(Field.TYPE, types);
		searchContext.setAttribute("paginationType", "more");

		if (privateLayout != null) {
			searchContext.setAttribute(
				"privateLayout", String.valueOf(privateLayout));
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		searchContext.setCompanyId(group.getCompanyId());

		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {groupId});
		searchContext.setKeywords(keywords);
		searchContext.setStart(start);

		if (orderByComparator != null) {
			searchContext.setSorts(_getSortFromComparator(orderByComparator));
		}

		if (userId > 0) {
			searchContext.setUserId(userId);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return searchContext;
	}

	private static Sort _getSortFromComparator(
		OrderByComparator<Layout> orderByComparator) {

		String[] fields = orderByComparator.getOrderByFields();

		if (ArrayUtil.contains(fields, "score")) {
			return new Sort(null, Sort.SCORE_TYPE, false);
		}

		boolean reverse = !orderByComparator.isAscending();
		String field = fields[0];

		return new Sort(field, Sort.LONG_TYPE, reverse);
	}

}