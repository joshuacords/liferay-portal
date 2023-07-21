/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.search.aggregation.AggregationResult;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.FilterAggregation;
import com.liferay.portal.search.aggregation.bucket.FilterAggregationResult;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregationResult;
import com.liferay.portal.search.aggregation.metrics.AvgAggregationResult;
import com.liferay.portal.search.aggregation.metrics.ValueCountAggregationResult;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermsQuery;
import com.liferay.portal.search.sort.FieldSort;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Task;
import com.liferay.portal.workflow.metrics.rest.internal.odata.entity.v1_0.TaskEntityModel;
import com.liferay.portal.workflow.metrics.rest.internal.resource.helper.ResourceHelper;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.TaskResource;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;
import com.liferay.portal.workflow.metrics.sla.processor.WorkfowMetricsSLAStatus;

import java.text.DateFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Rafael Praxedes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/task.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {EntityModelResource.class, TaskResource.class}
)
public class TaskResourceImpl
	extends BaseTaskResourceImpl implements EntityModelResource {

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	@Override
	public Page<Task> getProcessTasksPage(
			Long processId, Boolean completed, Date dateEnd, Date dateStart,
			String key, Pagination pagination, Sort[] sorts)
		throws Exception {

		String latestProcessVersion = _resourceHelper.getLatestProcessVersion(
			contextCompany.getCompanyId(), processId);

		FieldSort fieldSort = _toFieldSort(sorts);

		Map<String, Bucket> taskBuckets = _getTaskBuckets(
			GetterUtil.getBoolean(completed), dateEnd, dateStart, fieldSort,
			key, pagination, processId, latestProcessVersion);

		Map<String, Task> tasksMap = _getTasksMap(
			key, processId, taskBuckets.keySet(), latestProcessVersion);

		long count = tasksMap.size();

		if (count > 0) {
			if (pagination == null) {
				return Page.of(tasksMap.values());
			}

			return Page.of(
				_getTasks(
					GetterUtil.getBoolean(completed), fieldSort, pagination,
					processId, taskBuckets, tasksMap),
				pagination, count);
		}

		return Page.of(Collections.emptyList());
	}

	private BooleanQuery _createBooleanQuery(
		boolean completed, long processId) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addMustNotQueryClauses(_queries.term("tokenId", 0));

		return booleanQuery.addMustQueryClauses(
			_queries.term("completed", completed),
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("processId", processId));
	}

	private BooleanQuery _createBooleanQuery(long processId, String version) {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		return booleanQuery.addMustQueryClauses(
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("processId", processId),
			_queries.term("version", version));
	}

	private BooleanQuery _createFilterBooleanQuery(
		boolean completed, String key, long processId, String version) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		if (Validator.isNotNull(key)) {
			booleanQuery.addMustQueryClauses(
				_queries.wildcard("taskName", "*" + key + "*"));
		}

		booleanQuery.addShouldQueryClauses(
			_createBooleanQuery(completed, processId));

		return booleanQuery.addShouldQueryClauses(
			_createBooleanQuery(processId, version));
	}

	private BooleanQuery _createFilterBooleanQuery(
		String key, long processId, Set<String> taskNames, String version) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		if (Validator.isNotNull(key)) {
			booleanQuery.addMustQueryClauses(
				_queries.wildcard("name", "*" + key + "*"));
		}

		if (!taskNames.isEmpty()) {
			TermsQuery termsQuery = _queries.terms("name");

			termsQuery.addValues(taskNames.toArray(new Object[0]));

			booleanQuery.addShouldQueryClauses(termsQuery);
		}

		return booleanQuery.addShouldQueryClauses(
			_createBooleanQuery(processId, version));
	}

	private BooleanQuery _createNodesBooleanQuery(
		String key, long processId, Set<String> taskNames, String version) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addFilterQueryClauses(
			_createFilterBooleanQuery(key, processId, taskNames, version));

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("type", "TASK"));
	}

	private BooleanQuery _createSLATaskResultsBooleanQuery(
		boolean completed, long processId, Set<String> taskNames) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		if (completed) {
			booleanQuery.addMustNotQueryClauses(
				_queries.term(
					"status", WorkfowMetricsSLAStatus.RUNNING.name()));
		}
		else {
			booleanQuery.addMustNotQueryClauses(
				_queries.term(
					"status", WorkfowMetricsSLAStatus.COMPLETED.name()),
				_queries.term(
					"status", WorkfowMetricsSLAStatus.EXPIRED.name()));
		}

		TermsQuery termsQuery = _queries.terms("taskName");

		termsQuery.addValues(taskNames.toArray(new Object[0]));

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("processId", processId), termsQuery);
	}

	private Task _createTask(String taskName) {
		return new Task() {
			{
				breachedInstanceCount = 0L;
				durationAvg = 0L;
				instanceCount = 0L;
				key = taskName;
				name = _language.get(
					_resourceHelper.getResourceBundle(
						contextAcceptLanguage.getPreferredLocale()),
					taskName);
				onTimeInstanceCount = 0L;
				overdueInstanceCount = 0L;
			}
		};
	}

	private BooleanQuery _createTokensBooleanQuery(
		boolean completed, String key, long processId, String version) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addFilterQueryClauses(
			_createFilterBooleanQuery(completed, key, processId, version));

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("deleted", Boolean.FALSE));
	}

	private String _format(Date date) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		try {
			return dateFormat.format(date);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}

			return null;
		}
	}

	private TermsAggregationResult _getSLATermsAggregationResult(
		boolean completed, FieldSort fieldSort, Pagination pagination,
		long processId, Set<String> taskNames) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		TermsAggregation termsAggregation = _aggregations.terms(
			"taskName", "taskName");

		FilterAggregation breachedFilterAggregation = _aggregations.filter(
			"breached", _resourceHelper.createMustNotBooleanQuery());

		breachedFilterAggregation.addChildAggregation(
			_resourceHelper.createBreachedScriptedMetricAggregation());

		FilterAggregation onTimeFilterAggregation = _aggregations.filter(
			"onTime", _resourceHelper.createMustNotBooleanQuery());

		onTimeFilterAggregation.addChildAggregation(
			_resourceHelper.createOnTimeScriptedMetricAggregation());

		FilterAggregation overdueFilterAggregation = _aggregations.filter(
			"overdue", _resourceHelper.createMustNotBooleanQuery());

		overdueFilterAggregation.addChildAggregation(
			_resourceHelper.createOverdueScriptedMetricAggregation());

		termsAggregation.addChildrenAggregations(
			breachedFilterAggregation, onTimeFilterAggregation,
			overdueFilterAggregation);

		if ((fieldSort != null) && (pagination != null) &&
			(_isOrderByOnTimeInstanceCount(fieldSort.getField()) ||
			 _isOrderByOverdueInstanceCount(fieldSort.getField()))) {

			termsAggregation.addPipelineAggregation(
				_resourceHelper.createBucketSortPipelineAggregation(
					fieldSort, pagination));
		}

		termsAggregation.setSize(taskNames.size());

		searchSearchRequest.addAggregation(termsAggregation);

		searchSearchRequest.setIndexNames(
			_slaTaskResultWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));
		searchSearchRequest.setQuery(
			_createSLATaskResultsBooleanQuery(completed, processId, taskNames));

		SearchSearchResponse searchSearchResponse =
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest);

		Map<String, AggregationResult> aggregationResultsMap =
			searchSearchResponse.getAggregationResultsMap();

		return (TermsAggregationResult)aggregationResultsMap.get("taskName");
	}

	private Map<String, Bucket> _getTaskBuckets(
		boolean completed, Date dateEnd, Date dateStart, FieldSort fieldSort,
		String key, Pagination pagination, long processId, String version) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		TermsAggregation termsAggregation = _aggregations.terms(
			"taskName", "taskName");

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addMustQueryClauses(_queries.term("completed", completed));

		if (completed && (dateEnd != null) && (dateStart != null)) {
			booleanQuery.addMustQueryClauses(
				_queries.rangeTerm(
					"completionDate", true, true, _format(dateStart),
					_format(dateEnd)));
		}

		FilterAggregation filterAggregation = _aggregations.filter(
			"countFilter",
			booleanQuery.addMustNotQueryClauses(
				_queries.term("instanceId", "0")));

		filterAggregation.addChildrenAggregations(
			_aggregations.avg("durationAvg", "duration"),
			_aggregations.valueCount("instanceCount", "instanceId"));

		termsAggregation.addChildrenAggregations(filterAggregation);

		if ((fieldSort != null) && (pagination != null) &&
			(_isOrderByDurationAvg(fieldSort.getField()) ||
			 _isOrderByInstanceCount(fieldSort.getField()))) {

			termsAggregation.addPipelineAggregation(
				_resourceHelper.createBucketSortPipelineAggregation(
					fieldSort, pagination));
		}

		termsAggregation.setSize(10000);

		searchSearchRequest.addAggregation(termsAggregation);

		searchSearchRequest.setIndexNames(
			_tokenWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));
		searchSearchRequest.setQuery(
			_createTokensBooleanQuery(completed, key, processId, version));

		SearchSearchResponse searchSearchResponse =
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest);

		Map<String, AggregationResult> aggregationResultsMap =
			searchSearchResponse.getAggregationResultsMap();

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)aggregationResultsMap.get("taskName");

		Collection<Bucket> buckets = termsAggregationResult.getBuckets();

		Stream<Bucket> stream = buckets.stream();

		return stream.collect(
			LinkedHashMap::new,
			(map, bucket) -> map.put(bucket.getKey(), bucket), Map::putAll);
	}

	private Collection<Task> _getTasks(
		boolean completed, FieldSort fieldSort, Pagination pagination,
		long processId, Map<String, Bucket> taskBuckets,
		Map<String, Task> tasksMap) {

		List<Task> tasks = new LinkedList<>();

		TermsAggregationResult slaTermsAggregationResult =
			_getSLATermsAggregationResult(
				completed, fieldSort, pagination, processId, tasksMap.keySet());

		if (_isOrderByDurationAvg(fieldSort.getField()) ||
			_isOrderByInstanceCount(fieldSort.getField())) {

			taskBuckets.forEach(
				(key, bucket) -> {
					Task task = tasksMap.remove(key);

					_populateTaskWithSLAMetrics(
						slaTermsAggregationResult.getBucket(key), task);
					_setDurationAvg(bucket, task);
					_setInstanceCount(bucket, task);

					tasks.add(task);
				});
		}
		else {
			for (Bucket bucket : slaTermsAggregationResult.getBuckets()) {
				Task task = tasksMap.remove(bucket.getKey());

				_populateTaskWithSLAMetrics(bucket, task);
				_setDurationAvg(taskBuckets.get(bucket.getKey()), task);
				_setInstanceCount(taskBuckets.get(bucket.getKey()), task);

				tasks.add(task);
			}
		}

		if (tasks.size() > pagination.getPageSize()) {
			return tasks.subList(0, tasks.size() - 1);
		}

		return tasks;
	}

	private Map<String, Task> _getTasksMap(
		String key, long processId, Set<String> taskNames, String version) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		TermsAggregation termsAggregation = _aggregations.terms("name", "name");

		termsAggregation.setSize(10000);

		searchSearchRequest.addAggregation(termsAggregation);

		searchSearchRequest.setIndexNames(
			_nodeWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));
		searchSearchRequest.setQuery(
			_createNodesBooleanQuery(key, processId, taskNames, version));

		SearchSearchResponse searchSearchResponse =
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest);

		Map<String, AggregationResult> aggregationResultsMap =
			searchSearchResponse.getAggregationResultsMap();

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)aggregationResultsMap.get("name");

		Collection<Bucket> buckets = termsAggregationResult.getBuckets();

		Stream<Bucket> stream = buckets.stream();

		return stream.map(
			Bucket::getKey
		).map(
			this::_createTask
		).sorted(
			Comparator.comparing(Task::getName)
		).collect(
			LinkedHashMap::new, (map, task) -> map.put(task.getKey(), task),
			Map::putAll
		);
	}

	private boolean _isOrderByDurationAvg(String fieldName) {
		if (StringUtil.equals(fieldName, "durationAvg") ||
			StringUtil.equals(fieldName, "countFilter>durationAvg")) {

			return true;
		}

		return false;
	}

	private boolean _isOrderByInstanceCount(String fieldName) {
		if (StringUtil.equals(fieldName, "instanceCount") ||
			StringUtil.equals(fieldName, "countFilter>instanceCount")) {

			return true;
		}

		return false;
	}

	private boolean _isOrderByOnTimeInstanceCount(String fieldName) {
		if (StringUtil.equals(fieldName, "onTimeInstanceCount") ||
			StringUtil.equals(fieldName, "onTime>instanceCount.value")) {

			return true;
		}

		return false;
	}

	private boolean _isOrderByOverdueInstanceCount(String fieldName) {
		if (StringUtil.equals(fieldName, "overdueInstanceCount") ||
			StringUtil.equals(fieldName, "overdue>instanceCount.value")) {

			return true;
		}

		return false;
	}

	private void _populateTaskWithSLAMetrics(Bucket bucket, Task task) {
		_setBreachedInstanceCount(bucket, task);
		_setOnTimeInstanceCount(bucket, task);
		_setOverdueInstanceCount(bucket, task);
	}

	private void _setBreachedInstanceCount(Bucket bucket, Task task) {
		if (bucket == null) {
			return;
		}

		task.setBreachedInstanceCount(
			_resourceHelper.getBreachedInstanceCount(bucket));
	}

	private void _setDurationAvg(Bucket bucket, Task task) {
		if (bucket == null) {
			return;
		}

		FilterAggregationResult filterAggregationResult =
			(FilterAggregationResult)bucket.getChildAggregationResult(
				"countFilter");

		AvgAggregationResult avgAggregationResult =
			(AvgAggregationResult)
				filterAggregationResult.getChildAggregationResult(
					"durationAvg");

		task.setDurationAvg(
			GetterUtil.getLong(avgAggregationResult.getValue()));
	}

	private void _setInstanceCount(Bucket bucket, Task task) {
		if (bucket == null) {
			return;
		}

		FilterAggregationResult filterAggregationResult =
			(FilterAggregationResult)bucket.getChildAggregationResult(
				"countFilter");

		ValueCountAggregationResult valueCountAggregationResult =
			(ValueCountAggregationResult)
				filterAggregationResult.getChildAggregationResult(
					"instanceCount");

		task.setInstanceCount(
			GetterUtil.getLong(valueCountAggregationResult.getValue()));
	}

	private void _setOnTimeInstanceCount(Bucket bucket, Task task) {
		if (bucket == null) {
			return;
		}

		task.setOnTimeInstanceCount(
			_resourceHelper.getOnTimeInstanceCount(bucket));
	}

	private void _setOverdueInstanceCount(Bucket bucket, Task task) {
		if (bucket == null) {
			return;
		}

		task.setOverdueInstanceCount(
			_resourceHelper.getOverdueInstanceCount(bucket));
	}

	private FieldSort _toFieldSort(Sort[] sorts) {
		Sort sort = new Sort("instanceCount", false);

		if (sorts != null) {
			sort = sorts[0];
		}

		String fieldName = sort.getFieldName();

		if (_isOrderByDurationAvg(fieldName)) {
			fieldName = "countFilter>durationAvg";
		}
		else if (_isOrderByInstanceCount(fieldName)) {
			fieldName = "countFilter>instanceCount";
		}
		else if (_isOrderByOnTimeInstanceCount(fieldName) ||
				 _isOrderByOverdueInstanceCount(fieldName)) {

			fieldName =
				StringUtil.extractFirst(fieldName, "InstanceCount") +
					">instanceCount.value";
		}

		FieldSort fieldSort = _sorts.field(fieldName);

		fieldSort.setSortOrder(
			sort.isReverse() ? SortOrder.DESC : SortOrder.ASC);

		return fieldSort;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TaskResourceImpl.class);

	private static final EntityModel _entityModel = new TaskEntityModel();

	@Reference
	private Aggregations _aggregations;

	@Reference
	private Language _language;

	@Reference(target = "(workflow.metrics.index.entity.name=node)")
	private WorkflowMetricsIndexNameBuilder
		_nodeWorkflowMetricsIndexNameBuilder;

	@Reference
	private Queries _queries;

	@Reference
	private ResourceHelper _resourceHelper;

	@Reference
	private SearchRequestExecutor _searchRequestExecutor;

	@Reference(target = "(workflow.metrics.index.entity.name=sla-task-result)")
	private WorkflowMetricsIndexNameBuilder
		_slaTaskResultWorkflowMetricsIndexNameBuilder;

	@Reference
	private Sorts _sorts;

	@Reference(target = "(workflow.metrics.index.entity.name=token)")
	private WorkflowMetricsIndexNameBuilder
		_tokenWorkflowMetricsIndexNameBuilder;

}