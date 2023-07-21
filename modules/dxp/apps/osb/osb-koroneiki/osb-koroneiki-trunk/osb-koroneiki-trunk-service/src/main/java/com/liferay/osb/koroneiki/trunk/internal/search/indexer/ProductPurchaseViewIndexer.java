/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.search.indexer;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView;
import com.liferay.osb.koroneiki.trunk.model.view.impl.ProductPurchaseViewImpl;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.FieldArray;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.NestedQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;

import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	service = {Indexer.class, ProductPurchaseViewIndexer.class}
)
public class ProductPurchaseViewIndexer
	extends BaseIndexer<ProductPurchaseView> {

	public static final String CLASS_NAME = ProductPurchaseView.class.getName();

	public ProductPurchaseViewIndexer() {
		setDefaultSelectedFieldNames(
			"accountId", Field.COMPANY_ID, Field.ENTRY_CLASS_NAME,
			Field.ENTRY_CLASS_PK, "productEntryId", Field.UID);
		setPermissionAware(false);
		setStagingAware(false);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

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

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, "accountKey", false);
		addSearchTerm(searchQuery, searchContext, "entityIds", false);
		addSearchTerm(searchQuery, searchContext, "name", true);
		addSearchTerm(
			searchQuery, searchContext, "productConsumptionIds", false);
		addSearchTerm(searchQuery, searchContext, "productKey", false);
		addSearchTerm(searchQuery, searchContext, "productPurchaseIds", false);
	}

	@Override
	public void reindex(ProductPurchaseView productPurchaseView)
		throws SearchException {

		try {
			if (IndexWriterHelperUtil.isIndexReadOnly() ||
				IndexWriterHelperUtil.isIndexReadOnly(getClassName()) ||
				!isIndexerEnabled()) {

				return;
			}

			if (productPurchaseView == null) {
				return;
			}

			doReindex(productPurchaseView);
		}
		catch (SearchException searchException) {
			throw searchException;
		}
		catch (Exception exception) {
			throw new SearchException(exception);
		}
	}

	protected void addProductPurchasesFields(
		Document document, List<ProductPurchase> productPurchases) {

		Set<String> entityIds = new HashSet<>();
		Set<String> externalLinkDomains = new HashSet<>();
		Set<String> externalLinkEntityIds = new HashSet<>();
		Set<String> externalLinkEntityNames = new HashSet<>();

		FieldArray fieldArray = new FieldArray("productPurchases");

		for (ProductPurchase productPurchase : productPurchases) {
			Field field = new Field(StringPool.BLANK);

			Field statusField = new Field(Field.STATUS);

			statusField.setValue(String.valueOf(productPurchase.getStatus()));

			field.addField(statusField);

			Field perpetualField = new Field("perpetual");

			perpetualField.setValue(
				String.valueOf(productPurchase.isPerpetual()));

			field.addField(perpetualField);

			Date endDate = productPurchase.getEndDate();

			if (endDate != null) {
				Field endDateField = new Field("endDate");

				endDateField.setDates(new Date[] {endDate});
				endDateField.setValue(dateFormat.format(endDate));

				field.addField(endDateField);

				Field endDateSortableField = new Field("endDate_sortable");

				endDateSortableField.setNumeric(true);
				endDateSortableField.setNumericClass(Long.class);
				endDateSortableField.setValue(
					String.valueOf(endDate.getTime()));

				field.addField(endDateSortableField);
			}

			Date startDate = productPurchase.getStartDate();

			if (startDate != null) {
				Field startDateField = new Field("startDate");

				startDateField.setDates(new Date[] {startDate});
				startDateField.setValue(dateFormat.format(startDate));

				field.addField(startDateField);

				Field startDateSortableField = new Field("startDate_sortable");

				startDateSortableField.setNumeric(true);
				startDateSortableField.setNumericClass(Long.class);
				startDateSortableField.setValue(
					String.valueOf(startDate.getTime()));

				field.addField(startDateSortableField);
			}

			fieldArray.addField(field);

			List<ExternalLink> externalLinks =
				productPurchase.getExternalLinks();

			for (ExternalLink externalLink : externalLinks) {
				String entityName =
					externalLink.getDomain() + StringPool.UNDERLINE +
						externalLink.getEntityName();

				String entityId =
					entityName + StringPool.UNDERLINE +
						externalLink.getEntityId();

				entityIds.add(externalLink.getEntityId());

				externalLinkDomains.add(externalLink.getDomain());
				externalLinkEntityIds.add(entityId);
				externalLinkEntityNames.add(entityName);
			}
		}

		document.addKeyword(
			"entityIds", ArrayUtil.toStringArray(entityIds.toArray()));
		document.addKeyword(
			"externalLinkDomains",
			ArrayUtil.toStringArray(externalLinkDomains.toArray()));
		document.addKeyword(
			"externalLinkEntityIds",
			ArrayUtil.toStringArray(externalLinkEntityIds.toArray()));
		document.addKeyword(
			"externalLinkEntityNames",
			ArrayUtil.toStringArray(externalLinkEntityNames.toArray()));

		document.add(fieldArray);
	}

	@Override
	protected void doDelete(ProductPurchaseView productPurchaseView)
		throws Exception {

		Document document = new DocumentImpl();

		document.addUID(
			getClassName(), (String)productPurchaseView.getPrimaryKeyObj());

		IndexWriterHelperUtil.deleteDocument(
			getSearchEngineId(), productPurchaseView.getCompanyId(),
			document.get(Field.UID), isCommitImmediately());
	}

	@Override
	protected Document doGetDocument(ProductPurchaseView productPurchaseView)
		throws Exception {

		Document document = getBaseModelDocument(productPurchaseView);

		document.addKeyword(
			Field.COMPANY_ID, productPurchaseView.getCompanyId());

		Account account = _accountLocalService.getAccount(
			productPurchaseView.getAccountId());
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productPurchaseView.getProductEntryId());

		List<ProductPurchase> productPurchases =
			_productPurchaseLocalService.getAccountProductEntryProductPurchases(
				productPurchaseView.getAccountId(),
				productPurchaseView.getProductEntryId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		addProductPurchasesFields(document, productPurchases);

		document.addKeyword("accountId", account.getAccountId());
		document.addKeyword("accountKey", account.getAccountKey());
		document.addKeyword("name", productEntry.getName());
		document.addKeyword("perpetual", isPerpetual(productPurchases));
		document.addKeyword(
			"productConsumptionIds",
			getProductConsumptionIds(
				productPurchaseView.getAccountId(),
				productPurchaseView.getProductEntryId()));
		document.addKeyword("productEntryId", productEntry.getProductEntryId());
		document.addKeyword("productKey", productEntry.getProductEntryKey());
		document.addKeyword(
			"productPurchaseIds", getProductPurchaseIds(productPurchases));
		document.addNumberSortable(
			"provisionedCount",
			getProvisionedCount(
				productPurchaseView.getAccountId(),
				productPurchaseView.getProductEntryId()));
		document.addNumberSortable(
			"purchasedCount", getPurchasedCount(productPurchases));
		document.addKeyword("status", getStatus(productPurchases));
		document.addDate("supportLifeEndDate", getEndDate(productPurchases));
		document.addDate(
			"supportLifeStartDate", getStartDate(productPurchases));

		document.addTextSortable("name", productEntry.getName());

		_contributeContacts(document, account.getAccountId());

		List<ProductField> productFields = productEntry.getProductFields();

		for (ProductField productField : productFields) {
			document.addTextSortable(
				"property_" + productField.getName(), productField.getValue());
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			document.get("productEntryId"));

		return new Summary(productEntry.getName(), null);
	}

	@Override
	protected void doReindex(ProductPurchaseView productPurchaseView)
		throws Exception {

		int productConsumptionsCount =
			_productConsumptionLocalService.
				getAccountProductEntryProductConsumptionsCount(
					productPurchaseView.getAccountId(),
					productPurchaseView.getProductEntryId());
		int productPurchasesCount =
			_productPurchaseLocalService.
				getAccountProductEntryProductPurchasesCount(
					productPurchaseView.getAccountId(),
					productPurchaseView.getProductEntryId());

		if ((productConsumptionsCount > 0) || (productPurchasesCount > 0)) {
			IndexWriterHelperUtil.updateDocument(
				getSearchEngineId(), productPurchaseView.getCompanyId(),
				getDocument(productPurchaseView), isCommitImmediately());
		}
		else {
			doDelete(productPurchaseView);
		}
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Account account = _accountLocalService.getAccount(classPK);

		_reindexAccountProductPurchaseViews(account.getCompanyId(), account);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexProductPurchaseViews(companyId);
	}

	protected Document getBaseModelDocument(
		ProductPurchaseView productPurchaseView) {

		Document document = newDocument();

		String className = productPurchaseView.getModelClassName();
		String classPK = (String)productPurchaseView.getPrimaryKeyObj();

		document.addKeyword(Field.ENTRY_CLASS_NAME, className);
		document.addKeyword(Field.ENTRY_CLASS_PK, classPK);

		document.addUID(className, classPK);

		return document;
	}

	protected Date getEndDate(List<ProductPurchase> productPurchases) {
		Date endDate = null;

		for (ProductPurchase productPurchase : productPurchases) {
			Date curEndDate = productPurchase.getEndDate();

			if ((productPurchase.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) &&
				((endDate == null) ||
				 ((curEndDate != null) && curEndDate.after(endDate)))) {

				endDate = curEndDate;
			}
		}

		return endDate;
	}

	protected long[] getProductConsumptionIds(
		long accountId, long productEntryId) {

		List<ProductConsumption> productConsumptions =
			_productConsumptionLocalService.
				getAccountProductEntryProductConsumptions(
					accountId, productEntryId);

		Stream<ProductConsumption> productConsumptionsStream =
			productConsumptions.stream();

		return productConsumptionsStream.mapToLong(
			ProductConsumption::getProductConsumptionId
		).toArray();
	}

	protected long[] getProductPurchaseIds(
		List<ProductPurchase> productPurchases) {

		Stream<ProductPurchase> productPurchasesStream =
			productPurchases.stream();

		return productPurchasesStream.mapToLong(
			ProductPurchase::getProductPurchaseId
		).toArray();
	}

	protected int getProvisionedCount(long accountId, long productEntryId) {
		return _productConsumptionLocalService.
			getAccountProductEntryProductConsumptionsCount(
				accountId, productEntryId);
	}

	protected int getPurchasedCount(List<ProductPurchase> productPurchases) {
		int count = 0;

		for (ProductPurchase productPurchase : productPurchases) {
			count += productPurchase.getQuantity();
		}

		return count;
	}

	protected Date getStartDate(List<ProductPurchase> productPurchases) {
		Date startDate = null;

		for (ProductPurchase productPurchase : productPurchases) {
			Date curStartDate = productPurchase.getStartDate();

			if ((productPurchase.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) &&
				((startDate == null) ||
				 ((curStartDate != null) && curStartDate.before(startDate)))) {

				startDate = curStartDate;
			}
		}

		return startDate;
	}

	protected int getStatus(List<ProductPurchase> productPurchases) {
		if (productPurchases.isEmpty()) {
			return WorkflowConstants.STATUS_INACTIVE;
		}

		for (ProductPurchase productPurchase : productPurchases) {
			if (productPurchase.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) {

				return WorkflowConstants.STATUS_APPROVED;
			}
		}

		return WorkflowConstants.STATUS_CANCELLED;
	}

	protected boolean isPerpetual(List<ProductPurchase> productPurchases) {
		for (ProductPurchase productPurchase : productPurchases) {
			if (productPurchase.getStartDate() == null) {
				return true;
			}
		}

		return false;
	}

	protected void reindexProductPurchaseViews(long companyId)
		throws PortalException {

		List<Account> accounts = _accountLocalService.getAccounts(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Account account : accounts) {
			_reindexAccountProductPurchaseViews(companyId, account);
		}
	}

	protected static final String INDEX_DATE_FORMAT_PATTERN = PropsUtil.get(
		PropsKeys.INDEX_DATE_FORMAT_PATTERN);

	protected Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
		INDEX_DATE_FORMAT_PATTERN);

	private void _contributeContacts(Document document, long accountId)
		throws PortalException {

		Set<String> contactUuidContactRoleKeys = new HashSet<>();
		Set<String> contactUuids = new HashSet<>();
		Set<String> customerContactUuids = new HashSet<>();
		Set<String> workerContactUuids = new HashSet<>();

		List<ContactAccountRole> contactAccountRoles =
			_contactAccountRoleLocalService.getContactAccountRolesByAccountId(
				accountId);

		for (ContactAccountRole contactAccountRole : contactAccountRoles) {
			Contact contact = _contactLocalService.getContact(
				contactAccountRole.getContactId());
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactAccountRole.getContactRoleId());

			contactUuidContactRoleKeys.add(
				contact.getUuid() + StringPool.UNDERLINE +
					contactRole.getContactRoleKey());

			contactUuids.add(contact.getUuid());

			String type = contactRole.getType();

			if (type.equals(Type.ACCOUNT_CUSTOMER.toString())) {
				customerContactUuids.add(contact.getUuid());
			}
			else if (type.equals(Type.ACCOUNT_WORKER.toString())) {
				workerContactUuids.add(contact.getUuid());
			}
		}

		document.addKeyword(
			"contactUuidContactRoleKeys",
			ArrayUtil.toStringArray(contactUuidContactRoleKeys.toArray()));
		document.addKeyword(
			"contactUuids", ArrayUtil.toStringArray(contactUuids.toArray()));
		document.addKeyword(
			"customerContactUuids",
			ArrayUtil.toStringArray(customerContactUuids.toArray()));
		document.addKeyword(
			"workerContactUuids",
			ArrayUtil.toStringArray(workerContactUuids.toArray()));
	}

	private BooleanFilter _getActiveFilter() throws ParseException {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		BooleanFilter activeDateFilter = new BooleanFilter();

		BooleanQuery perpetualQuery = new BooleanQueryImpl();

		perpetualQuery.addRequiredTerm(
			"productPurchases.status", WorkflowConstants.STATUS_APPROVED);
		perpetualQuery.addRequiredTerm("productPurchases.perpetual", true);

		activeDateFilter.add(
			new QueryFilter(
				new NestedQuery("productPurchases", perpetualQuery)),
			BooleanClauseOccur.SHOULD);

		long now = System.currentTimeMillis();

		BooleanQuery rangeQuery = new BooleanQueryImpl();

		TermRangeQuery startDateQuery = new TermRangeQueryImpl(
			"productPurchases.startDate_sortable", "0", String.valueOf(now),
			true, true);

		rangeQuery.add(startDateQuery, BooleanClauseOccur.MUST);

		TermRangeQuery endDateQuery = new TermRangeQueryImpl(
			"productPurchases.endDate_sortable", String.valueOf(now),
			String.valueOf(now + (Time.YEAR * 1000)), true, true);

		rangeQuery.add(endDateQuery, BooleanClauseOccur.MUST);

		rangeQuery.addRequiredTerm(
			"productPurchases.status", WorkflowConstants.STATUS_APPROVED);

		activeDateFilter.add(
			new QueryFilter(new NestedQuery("productPurchases", rangeQuery)),
			BooleanClauseOccur.SHOULD);

		booleanFilter.add(activeDateFilter, BooleanClauseOccur.MUST);

		return booleanFilter;
	}

	private BooleanFilter _getCancelledFilter() {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_CANCELLED);

		return booleanFilter;
	}

	private BooleanFilter _getExpiredFilter() throws ParseException {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		RangeTermFilter rangeTermFilter = new RangeTermFilter(
			"supportLifeEndDate_sortable", true, true, "0",
			String.valueOf(System.currentTimeMillis()));

		booleanFilter.add(rangeTermFilter, BooleanClauseOccur.MUST);

		booleanFilter.add(_getActiveFilter(), BooleanClauseOccur.MUST_NOT);

		return booleanFilter;
	}

	private BooleanFilter _getUnactivatedFilter() throws ParseException {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		long now = System.currentTimeMillis();

		BooleanQuery rangeQuery = new BooleanQueryImpl();

		TermRangeQuery startDateQuery = new TermRangeQueryImpl(
			"productPurchases.startDate_sortable", String.valueOf(now),
			String.valueOf(now + (Time.YEAR * 100)), false, true);

		rangeQuery.add(startDateQuery, BooleanClauseOccur.MUST);

		booleanFilter.add(
			new QueryFilter(new NestedQuery("productPurchases", rangeQuery)),
			BooleanClauseOccur.MUST);

		booleanFilter.add(_getActiveFilter(), BooleanClauseOccur.MUST_NOT);
		booleanFilter.add(_getExpiredFilter(), BooleanClauseOccur.MUST_NOT);

		return booleanFilter;
	}

	private void _processBooleanClauses(
			BooleanFilter booleanFilter,
			List<BooleanClause<Filter>> booleanClauses,
			BooleanClauseOccur booleanClauseOccur)
		throws Exception {

		String state = null;

		Iterator<BooleanClause<Filter>> iterator = booleanClauses.iterator();

		List<String> states = new ArrayList<>();

		while (iterator.hasNext()) {
			BooleanClause<Filter> booleanClause = iterator.next();

			Filter filter = booleanClause.getClause();

			if (filter instanceof BooleanFilter) {
				_processBooleanFilter((BooleanFilter)filter);
			}
			else if (filter instanceof TermFilter) {
				TermFilter termFilter = (TermFilter)filter;

				String field = termFilter.getField();

				if (field.equals("state")) {
					state = termFilter.getValue();

					states.add(state);

					iterator.remove();
				}
			}
		}

		if (states.isEmpty()) {
			return;
		}

		for (String curState : states) {
			if (StringUtil.equalsIgnoreCase(curState, "active")) {
				booleanFilter.add(_getActiveFilter(), booleanClauseOccur);
			}
			else if (StringUtil.equalsIgnoreCase(curState, "cancelled")) {
				booleanFilter.add(_getCancelledFilter(), booleanClauseOccur);
			}
			else if (StringUtil.equalsIgnoreCase(curState, "expired")) {
				booleanFilter.add(_getExpiredFilter(), booleanClauseOccur);
			}
			else if (StringUtil.equalsIgnoreCase(curState, "unactivated")) {
				booleanFilter.add(_getUnactivatedFilter(), booleanClauseOccur);
			}
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

	private void _reindexAccountProductPurchaseViews(
			long companyId, Account account)
		throws PortalException {

		List<ProductPurchase> productPurchases =
			_productPurchaseLocalService.getAccountProductPurchases(
				account.getAccountId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Stream<ProductPurchase> productPurchaseStream =
			productPurchases.stream();

		Set<Long> productEntryIds = productPurchaseStream.map(
			ProductPurchase::getProductEntryId
		).collect(
			Collectors.toSet()
		);

		List<ProductConsumption> productConsumptions =
			_productConsumptionLocalService.getAccountProductConsumptions(
				account.getAccountId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Stream<ProductConsumption> productConsumptionStream =
			productConsumptions.stream();

		productEntryIds.addAll(
			productConsumptionStream.map(
				ProductConsumption::getProductEntryId
			).collect(
				Collectors.toSet()
			));

		for (long productEntryId : productEntryIds) {
			ProductPurchaseView productPurchaseView =
				new ProductPurchaseViewImpl();

			productPurchaseView.setCompanyId(companyId);
			productPurchaseView.setAccountId(account.getAccountId());
			productPurchaseView.setProductEntryId(productEntryId);

			reindex(productPurchaseView);
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ContactAccountRoleLocalService _contactAccountRoleLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}