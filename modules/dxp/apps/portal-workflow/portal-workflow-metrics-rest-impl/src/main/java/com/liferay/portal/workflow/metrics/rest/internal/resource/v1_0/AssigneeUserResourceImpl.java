/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregationResult;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.AssigneeUser;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.AssigneeUserResource;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Inácio Nery
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/assignee-user.properties",
	scope = ServiceScope.PROTOTYPE, service = AssigneeUserResource.class
)
public class AssigneeUserResourceImpl extends BaseAssigneeUserResourceImpl {

	@Override
	public Page<AssigneeUser> getProcessAssigneeUsersPage(Long processId)
		throws Exception {

		return Page.of(_getAssigneeUsers(processId));
	}

	private Collection<AssigneeUser> _getAssigneeUsers(long processId)
		throws Exception {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		TermsAggregation termsAggregation = _aggregations.terms(
			"assigneeId", "assigneeId");

		termsAggregation.setSize(10000);

		searchSearchRequest.addAggregation(termsAggregation);

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addMustNotQueryClauses(_queries.term("tokenId", 0));

		searchSearchRequest.setIndexNames(
			_tokenWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));
		searchSearchRequest.setQuery(
			booleanQuery.addMustQueryClauses(
				_queries.term("companyId", contextCompany.getCompanyId()),
				_queries.term("completed", Boolean.FALSE),
				_queries.term("deleted", Boolean.FALSE),
				_queries.term("processId", processId)));

		return Stream.of(
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest)
		).map(
			SearchSearchResponse::getAggregationResultsMap
		).map(
			aggregationResultsMap ->
				(TermsAggregationResult)aggregationResultsMap.get("assigneeId")
		).map(
			TermsAggregationResult::getBuckets
		).flatMap(
			Collection::stream
		).map(
			Bucket::getKey
		).map(
			GetterUtil::getLong
		).map(
			this::_toAssigneeUser
		).filter(
			Objects::nonNull
		).sorted(
			Comparator.comparing(
				AssigneeUser::getName, String::compareToIgnoreCase)
		).collect(
			Collectors.toCollection(LinkedList::new)
		);
	}

	private AssigneeUser _toAssigneeUser(long userId) {
		try {
			User user = _userService.getUserById(userId);

			return new AssigneeUser() {
				{
					id = user.getUserId();
					name = user.getFullName();

					setImage(
						() -> {
							if (user.getPortraitId() == 0) {
								return null;
							}

							ThemeDisplay themeDisplay = new ThemeDisplay() {
								{
									setPathImage(_portal.getPathImage());
								}
							};

							return user.getPortraitURL(themeDisplay);
						});
				}
			};
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException, portalException);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssigneeUserResourceImpl.class);

	@Reference
	private Aggregations _aggregations;

	@Reference
	private Portal _portal;

	@Reference
	private Queries _queries;

	@Reference
	private SearchRequestExecutor _searchRequestExecutor;

	@Reference(target = "(workflow.metrics.index.entity.name=token)")
	private WorkflowMetricsIndexNameBuilder
		_tokenWorkflowMetricsIndexNameBuilder;

	@Reference
	private UserService _userService;

}