/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.license.service.impl;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.exception.DuplicateIPAddressException;
import com.liferay.osb.provisioning.license.exception.DuplicateMACAddressException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyActiveException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyDateException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyDescriptionException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyIPAddressException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyMACAddressException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyMaxClusterNodesException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyNameException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyOwnerException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyProductVersionException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyServerInfoException;
import com.liferay.osb.provisioning.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.provisioning.license.generator.KeyGenerator;
import com.liferay.osb.provisioning.license.helper.constants.LicenseServerId;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.helper.constants.LicenseVersion;
import com.liferay.osb.provisioning.license.helper.constants.ProductId;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.base.LicenseKeyLocalServiceBaseImpl;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.text.Format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.time.DateUtils;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.license.model.LicenseKey",
	service = AopService.class
)
public class LicenseKeyLocalServiceImpl extends LicenseKeyLocalServiceBaseImpl {

	public LicenseKey addLicenseKey(
			String userName, String userUuid, LicenseEntry licenseEntry,
			Product product, String accountKey, String productPurchaseKey,
			String accountName, String productVersion, long clusterId,
			String name, String owner, int maxClusterNodes, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			String sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws Exception {

		if (!complimentary) {
			product = _productWebService.getProduct(
				licenseEntry.getProductKey());
		}

		String licenseEntryType = licenseEntry.getType();

		int licenseVersion = LicenseVersion.getLicenseVersion(
			product.getName(), productVersion);

		validate(
			productVersion, name, owner, description, licenseEntryType,
			maxClusterNodes);

		List<String> serverIds = new ArrayList<>();

		String serverId = LicenseServerId.getServerId(licenseEntryType);

		if (Validator.isNotNull(serverId)) {
			serverIds.add(serverId);
		}

		return doAddLicenseKeyVersion3_4(
			new Date(), userName, userUuid, licenseEntry, product, accountKey,
			productPurchaseKey, accountName, licenseEntryType, licenseVersion,
			productVersion, clusterId, name, owner, maxClusterNodes, maxServers,
			maxHttpSessions, maxConcurrentUsers, maxUsers, sizing, description,
			hostNames, ipAddresses, macAddresses,
			serverIds.toArray(new String[0]), startDate, expirationDate,
			additionalInfo, complimentary, active);
	}

	public LicenseKey addLicenseKey(
			String userName, String userUuid, long licenseEntryId,
			String productKey, String accountKey, String productPurchaseKey,
			String accountName, String productVersion, long clusterId,
			String name, String owner, int maxClusterNodes, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			String sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, Date startDate,
			Date expirationDate, boolean complimentary, boolean active)
		throws Exception {

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			licenseEntryId);

		name = truncateText(name, accountName, 75);
		owner = truncateText(owner, accountName, 75);
		description = truncateText(description, accountName, 255);

		String licenseEntryType = licenseEntry.getType();

		if (licenseEntryType.equals(LicenseType.VIRTUAL_CLUSTER)) {
			maxClusterNodes = Math.max(1, maxClusterNodes);
		}

		return addLicenseKey(
			userName, userUuid, licenseEntry,
			_productWebService.getProduct(productKey), accountKey,
			productPurchaseKey, accountName, productVersion, clusterId, name,
			owner, maxClusterNodes, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, startDate, expirationDate,
			StringPool.BLANK, complimentary, active);
	}

	public LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productName, String productId,
			String productVersion, String owner, long maxUsers,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, Date startDate,
			Date expirationDate)
		throws Exception {

		Contact contact = _contactIdentityProvider.fetchContactByUuid(userUuid);

		String contactName = StringPool.BLANK;

		if (contact != null) {
			contactName = StringBundler.concat(
				contact.getFirstName(), StringPool.SPACE,
				contact.getLastName());
		}

		Date now = new Date();
		int licenseVersion = 3;

		productName = trimText(productName);
		owner = trimText(owner);
		description = trimText(description);
		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		validate(
			licenseEntryType, owner, description, hostName, ipAddresses,
			macAddresses);

		String key = _keyGenerator.generate(
			StringPool.BLANK, StringPool.BLANK, licenseEntryType,
			licenseVersion, productName, productId, productVersion, owner, 0, 0,
			0, 0, maxUsers, StringPool.BLANK, description, hostName,
			ipAddresses, macAddresses, new String[] {serverId}, startDate,
			expirationDate);

		long licenseKeyId = counterLocalService.increment();

		LicenseKey licenseKey = licenseKeyPersistence.create(licenseKeyId);

		licenseKey.setUserUuid(userUuid);
		licenseKey.setUserName(contactName);
		licenseKey.setCreateDate(now);
		licenseKey.setModifiedUserUuid(userUuid);
		licenseKey.setModifiedUserName(contactName);
		licenseKey.setModifiedDate(now);
		licenseKey.setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
		licenseKey.setLicenseEntryType(licenseEntryType);
		licenseKey.setLicenseVersion(licenseVersion);
		licenseKey.setProductName(productName);
		licenseKey.setProductId(productId);
		licenseKey.setProductVersion(productVersion);
		licenseKey.setOwner(owner);
		licenseKey.setMaxUsers(maxUsers);
		licenseKey.setDescription(description);
		licenseKey.setHostName(hostName);
		licenseKey.setIpAddresses(ipAddresses);
		licenseKey.setMacAddresses(macAddresses);
		licenseKey.setServerId(serverId);
		licenseKey.setKey(key);
		licenseKey.setStartDate(startDate);
		licenseKey.setExpirationDate(expirationDate);
		licenseKey.setComplimentary(false);
		licenseKey.setActive(true);

		return licenseKeyPersistence.update(licenseKey);
	}

	public LicenseKey addLicenseKey(
			String userName, String userUuid, String licenseEntryType,
			String productKey, String accountKey, String productPurchaseKey,
			String productVersion, String name, String owner,
			int maxClusterNodes, String sizing, String description,
			String hostName, String ipAddresses, String macAddresses,
			Date startDate, Date expirationDate, boolean complimentary,
			boolean active)
		throws Exception {

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			productKey, licenseEntryType);

		validate(
			licenseEntryType, productPurchaseKey, hostName, ipAddresses,
			macAddresses, startDate, expirationDate);

		Account account = _accountWebService.getAccount(accountKey);

		return addLicenseKey(
			userName, userUuid, licenseEntry,
			_productWebService.getProduct(productKey), accountKey,
			productPurchaseKey, account.getName(), productVersion, 0, name,
			owner, maxClusterNodes, 0, 0, 0, 0, sizing, description,
			new String[] {hostName}, new String[] {ipAddresses},
			new String[] {macAddresses}, startDate, expirationDate,
			StringPool.BLANK, complimentary, active);
	}

	public LicenseKey extendLicenseKey(
			String userName, String userUuid, long licenseKeyId,
			String productPurchaseKey, Date startDate, Date expirationDate)
		throws Exception {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		Product product = _productWebService.getProduct(
			licenseKey.getProductKey());
		LicenseEntry licenseEntry = licenseKey.getLicenseEntry();

		return doAddLicenseKeyVersion3_4(
			new Date(), userName, userUuid, licenseKey.getLicenseEntry(),
			product, licenseKey.getAccountKey(), productPurchaseKey,
			licenseKey.getAccountName(), licenseEntry.getType(),
			licenseKey.getLicenseVersion(), licenseKey.getProductVersion(),
			licenseKey.getClusterId(), licenseKey.getName(),
			licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
			licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
			licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
			licenseKey.getSizing(), licenseKey.getDescription(),
			new String[] {licenseKey.getHostName()},
			new String[] {licenseKey.getIpAddresses()},
			new String[] {licenseKey.getMacAddresses()},
			new String[] {licenseKey.getServerId()}, startDate, expirationDate,
			licenseKey.getAdditionalInfo(), licenseKey.isComplimentary(), true);
	}

	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return licenseKeyPersistence.findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return licenseKeyPersistence.countByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public LicenseKey getLicenseKeyByUuid(String uuid) throws PortalException {
		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByUuid(uuid);

		if (licenseKeys.isEmpty()) {
			throw new NoSuchLicenseKeyException("{uuid=" + uuid + "}");
		}

		return licenseKeys.get(0);
	}

	public List<LicenseKey> getLicenseKeys(String productId, String serverId) {
		return licenseKeyPersistence.findByPI_SI(productId, serverId);
	}

	public List<LicenseKey> getLicenseKeys(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public List<LicenseKey> getLicenseKeysByName(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator obc) {

		return licenseKeyPersistence.findByPN_SI_A(
			productName, serverId, active, start, end, obc);
	}

	@Indexable(type = IndexableType.REINDEX)
	public LicenseKey reindex(long licenseKeyId) throws PortalException {
		return licenseKeyPersistence.findByPrimaryKey(licenseKeyId);
	}

	public LicenseKey replaceLicenseKey(
			String userName, String userUuid, long licenseKeyId, Date startDate,
			Date expirationDate)
		throws Exception {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		if (Validator.isNotNull(licenseKey.getAssetReceiptLicenseUuid())) {
			if (!licenseKey.isActive()) {
				throw new LicenseKeyActiveException();
			}

			updateLicenseKey(userName, userUuid, licenseKeyId, false);

			return addLicenseKey(
				userUuid, licenseKey.getAssetReceiptLicenseUuid(),
				licenseKey.getLicenseEntryType(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxUsers(),
				licenseKey.getDescription(), licenseKey.getHostName(),
				licenseKey.getIpAddresses(), licenseKey.getMacAddresses(),
				licenseKey.getServerId(), startDate, expirationDate);
		}

		updateLicenseKey(
			userName, userUuid, licenseKeyId,
			licenseKey.getProductPurchaseKey(), licenseKey.isComplimentary(),
			false);

		Product product = _productWebService.getProduct(
			licenseKey.getProductKey());
		LicenseEntry licenseEntry = licenseKey.getLicenseEntry();

		return doAddLicenseKeyVersion3_4(
			new Date(), userName, userUuid, licenseKey.getLicenseEntry(),
			product, licenseKey.getAccountKey(),
			licenseKey.getProductPurchaseKey(), licenseKey.getAccountName(),
			licenseEntry.getType(), licenseKey.getLicenseVersion(),
			licenseKey.getProductVersion(), licenseKey.getClusterId(),
			licenseKey.getName(), licenseKey.getOwner(),
			licenseKey.getMaxClusterNodes(), licenseKey.getMaxServers(),
			licenseKey.getMaxHttpSessions(), licenseKey.getMaxConcurrentUsers(),
			licenseKey.getMaxUsers(), licenseKey.getSizing(),
			licenseKey.getDescription(),
			new String[] {licenseKey.getHostName()},
			new String[] {licenseKey.getIpAddresses()},
			new String[] {licenseKey.getMacAddresses()},
			new String[] {licenseKey.getServerId()}, startDate, expirationDate,
			licenseKey.getAdditionalInfo(), licenseKey.isComplimentary(), true);
	}

	public Hits search(
			long companyId, String createUserUuid, Date createDateGT,
			Date createDateLT, String modifiedUserUuid, Date modifiedDateGT,
			Date modifiedDateLT, String accountKey, String productPurchaseKey,
			String accountName, Date startDateGT, Date startDateLT,
			Long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT, Boolean active,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, Sort sort)
		throws Exception {

		Indexer<LicenseKey> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			LicenseKey.class);

		SearchContext searchContext = buildSearchContext(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch, start, end, sort);

		return indexer.search(searchContext);
	}

	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		Indexer<LicenseKey> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			LicenseKey.class);

		SearchContext searchContext = new SearchContext();

		searchContext.setAndSearch(false);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put("accountKey", keywords);
		attributes.put("accountName", keywords);
		attributes.put("createUserUuid", keywords);
		attributes.put("description", keywords);
		attributes.put("hostName", keywords);
		attributes.put("ipAddresses", keywords);
		attributes.put("key", keywords);
		attributes.put("macAddresses", keywords);
		attributes.put("modifiedUserUuid", keywords);
		attributes.put("owner", keywords);
		attributes.put("productId", keywords);
		attributes.put("productName", keywords);
		attributes.put("productPurchaseKey", keywords);
		attributes.put("productVersions", keywords);
		attributes.put("serverId", keywords);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setEnd(end);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		searchContext.setStart(start);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return indexer.search(searchContext);
	}

	public List<LicenseKey> search(
		String createUserUuid, Date createDateGT, Date createDateLT,
		String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
		String accountKey, String productPurchaseKey, String accountName,
		Date startDateGT, Date startDateLT, long[] licenseEntryIds,
		String[] productKeys, String productName, String productId,
		String[] productVersions, long[] clusterIds, String owner,
		String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key, Date expirationDateGT,
		Date expirationDateLT, LinkedHashMap<String, Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc) {

		return licenseKeyFinder.
			findByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
				createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
				modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
				accountName, startDateGT, startDateLT, licenseEntryIds,
				productKeys, productName, productId, productVersions,
				clusterIds, owner, description, hostName, ipAddress, macAddress,
				serverId, key, expirationDateGT, expirationDateLT, params,
				andSearch, start, end, obc);
	}

	public int searchCount(
			long companyId, String createUserUuid, Date createDateGT,
			Date createDateLT, String modifiedUserUuid, Date modifiedDateGT,
			Date modifiedDateLT, String accountKey, String productPurchaseKey,
			String accountName, Date startDateGT, Date startDateLT,
			Long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT, Boolean active,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		Indexer<LicenseKey> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			LicenseKey.class);

		SearchContext searchContext = buildSearchContext(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);

		return (int)indexer.searchCount(searchContext);
	}

	public int searchCount(
		String createUserUuid, Date createDateGT, Date createDateLT,
		String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
		String accountKey, String productPurchaseKey, String accountName,
		Date startDateGT, Date startDateLT, long[] licenseEntryIds,
		String[] productKeys, String productName, String productId,
		String[] productVersions, long[] clusterIds, String owner,
		String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key, Date expirationDateGT,
		Date expirationDateLT, LinkedHashMap<String, Object> params,
		boolean andSearch) {

		return licenseKeyFinder.
			countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
				createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
				modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
				accountName, startDateGT, startDateLT, licenseEntryIds,
				productKeys, productName, productId, productVersions,
				clusterIds, owner, description, hostName, ipAddress, macAddress,
				serverId, key, expirationDateGT, expirationDateLT, params,
				andSearch);
	}

	public LicenseKey updateLicenseKey(
			String userName, String userUuid, long licenseKeyId, boolean active)
		throws Exception {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		if (active && !licenseKey.isActive()) {
			if (!licenseKey.isComplimentary()) {
				_addProductConsumption(userName, userUuid, licenseKey);
			}
		}
		else if (!active && licenseKey.isActive()) {
			_deleteProductConsumption(userName, userUuid, licenseKey);
		}

		licenseKey.setModifiedUserUuid(userUuid);
		licenseKey.setModifiedUserName(userName);
		licenseKey.setModifiedDate(new Date());
		licenseKey.setActive(active);

		return licenseKeyPersistence.update(licenseKey);
	}

	public LicenseKey updateLicenseKey(
			String userName, String userUuid, long licenseKeyId,
			String productPurchaseKey, boolean complimentary, boolean active)
		throws Exception {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		String type = licenseKey.getLicenseEntryType();

		List<LicenseKey> clusterLicenseKeys = getClusterLicenseKeys(
			licenseKey, type);

		doUpdateLicenseKeyVersion3(
			new Date(), licenseKey, productPurchaseKey, clusterLicenseKeys,
			userName, userUuid, complimentary, active);

		return licenseKey;
	}

	protected static String trimText(String text) {

		// Copied from org.dom4j.tree.AbstractBranch.getTextTrim()

		StringBuffer textContent = new StringBuffer();

		StringTokenizer tokenizer = new StringTokenizer(text);

		while (tokenizer.hasMoreTokens()) {
			String str = tokenizer.nextToken();

			textContent.append(str);

			if (tokenizer.hasMoreTokens()) {
				textContent.append(" ");
			}
		}

		return textContent.toString();
	}

	protected SearchContext buildSearchContext(
		long companyId, String createUserUuid, Date createDateGT,
		Date createDateLT, String modifiedUserUuid, Date modifiedDateGT,
		Date modifiedDateLT, String accountKey, String productPurchaseKey,
		String accountName, Date startDateGT, Date startDateLT,
		Long[] licenseEntryIds, String[] productKeys, String productName,
		String productId, String[] productVersions, String owner,
		String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key, Date expirationDateGT,
		Date expirationDateLT, Boolean active,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		searchContext.setAndSearch(andSearch);

		Map<String, Serializable> attributes = new HashMap<>();

		List<BooleanClause<Query>> booleanClauses = new ArrayList<>();

		if (active != null) {
			BooleanQuery booleanQuery = new BooleanQueryImpl();

			booleanQuery.addExactTerm("active", active);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					booleanQuery, BooleanClauseOccur.MUST.getName()));
		}

		if (createDateGT != null) {
			TermRangeQuery createDateGTTermQuery = new TermRangeQueryImpl(
				Field.CREATE_DATE, _dateFormat.format(createDateGT), null, true,
				true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					createDateGTTermQuery, BooleanClauseOccur.MUST.getName()));
		}

		if (createDateLT != null) {
			TermRangeQuery createDateLTTermQuery = new TermRangeQueryImpl(
				Field.CREATE_DATE, null, _dateFormat.format(createDateLT), true,
				true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					createDateLTTermQuery, BooleanClauseOccur.MUST.getName()));
		}

		if (modifiedDateGT != null) {
			TermRangeQuery modifiedDateGTTermQuery = new TermRangeQueryImpl(
				Field.MODIFIED_DATE, _dateFormat.format(modifiedDateGT), null,
				true, true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					modifiedDateGTTermQuery,
					BooleanClauseOccur.MUST.getName()));
		}

		if (modifiedDateLT != null) {
			TermRangeQuery modifiedDateLTTermQuery = new TermRangeQueryImpl(
				Field.MODIFIED_DATE, null, _dateFormat.format(modifiedDateLT),
				true, true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					modifiedDateLTTermQuery,
					BooleanClauseOccur.MUST.getName()));
		}

		if (expirationDateGT != null) {
			TermRangeQuery expirationDateGTTermQuery = new TermRangeQueryImpl(
				Field.EXPIRATION_DATE, _dateFormat.format(expirationDateGT),
				null, true, true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					expirationDateGTTermQuery,
					BooleanClauseOccur.MUST.getName()));
		}

		if (expirationDateLT != null) {
			TermRangeQuery expirationDateLTTermQuery = new TermRangeQueryImpl(
				Field.EXPIRATION_DATE, null,
				_dateFormat.format(expirationDateLT), true, true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					expirationDateLTTermQuery,
					BooleanClauseOccur.MUST.getName()));
		}

		if (startDateGT != null) {
			TermRangeQuery startDateGTTermQuery = new TermRangeQueryImpl(
				"startDate", _dateFormat.format(startDateGT), null, true, true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					startDateGTTermQuery, BooleanClauseOccur.MUST.getName()));
		}

		if (startDateLT != null) {
			TermRangeQuery startDateLTTermQuery = new TermRangeQueryImpl(
				"startDate", null, _dateFormat.format(startDateLT), true, true);

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					startDateLTTermQuery, BooleanClauseOccur.MUST.getName()));
		}

		if (params.containsKey("accountKey")) {
			TermQuery accountKeyTermQuery = new TermQueryImpl(
				"accountKey",
				StringUtil.toLowerCase((String)params.get("accountKey")));

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					accountKeyTermQuery, BooleanClauseOccur.MUST.getName()));
		}

		if (params.containsKey("productKey")) {
			BooleanQuery booleanQuery = new BooleanQueryImpl();

			booleanQuery.addExactTerm(
				"productKey",
				StringUtil.toLowerCase((String)params.get("productKey")));

			booleanClauses.add(
				BooleanClauseFactoryUtil.create(
					booleanQuery, BooleanClauseOccur.MUST.getName()));
		}

		if (!booleanClauses.isEmpty()) {
			searchContext.setBooleanClauses(
				booleanClauses.toArray(new BooleanClause[0]));
		}

		attributes.put("accountKey", accountKey);
		attributes.put("accountName", accountName);
		attributes.put("description", description);
		attributes.put("hostName", hostName);
		attributes.put("ipAddresses", ipAddress);
		attributes.put("key", key);
		attributes.put("licenseEntryId", licenseEntryIds);
		attributes.put("macAddresses", macAddress);
		attributes.put("modifiedUserUuid", modifiedUserUuid);
		attributes.put("owner", owner);
		attributes.put("productId", productId);
		attributes.put("productKey", productKeys);
		attributes.put("productName", productName);
		attributes.put("productPurchaseKey", productPurchaseKey);
		attributes.put("productVersion", productVersions);
		attributes.put("serverId", serverId);
		attributes.put("userUuid", createUserUuid);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setEnd(end);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		searchContext.setStart(start);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return searchContext;
	}

	protected LicenseKey doAddLicenseKey(
			String userName, String userUuid, Date now,
			LicenseEntry licenseEntry, String accountKey,
			String productPurchaseKey, String accountName,
			String licenseEntryName, String licenseEntryType,
			int licenseVersion, String productName, String productId,
			String productVersion, long clusterId, String name, String owner,
			int maxClusterNodes, int maxServers, long maxConcurrentUsers,
			long maxUsers, int maxHttpSessions, String sizing,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, String key, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws Exception {

		long licenseKeyId = counterLocalService.increment();

		LicenseKey licenseKey = licenseKeyPersistence.create(licenseKeyId);

		licenseKey.setUserUuid(userUuid);
		licenseKey.setUserName(userName);
		licenseKey.setCreateDate(now);
		licenseKey.setModifiedUserUuid(userUuid);
		licenseKey.setModifiedUserName(userName);
		licenseKey.setModifiedDate(now);
		licenseKey.setAccountKey(accountKey);
		licenseKey.setProductPurchaseKey(productPurchaseKey);
		licenseKey.setLicenseEntryId(licenseEntry.getLicenseEntryId());
		licenseKey.setProductKey(licenseEntry.getProductKey());
		licenseKey.setAccountName(accountName);
		licenseKey.setLicenseEntryName(licenseEntryName);
		licenseKey.setLicenseEntryType(licenseEntryType);
		licenseKey.setLicenseVersion(licenseVersion);
		licenseKey.setProductName(productName);
		licenseKey.setProductId(productId);
		licenseKey.setProductVersion(productVersion);
		licenseKey.setClusterId(clusterId);
		licenseKey.setName(name);
		licenseKey.setOwner(owner);
		licenseKey.setMaxClusterNodes(maxClusterNodes);
		licenseKey.setMaxServers(maxServers);
		licenseKey.setMaxConcurrentUsers(maxConcurrentUsers);
		licenseKey.setMaxUsers(maxUsers);
		licenseKey.setMaxHttpSessions(maxHttpSessions);
		licenseKey.setSizing(sizing);
		licenseKey.setDescription(description);
		licenseKey.setHostName(hostName);
		licenseKey.setIpAddresses(ipAddresses);
		licenseKey.setMacAddresses(macAddresses);
		licenseKey.setServerId(serverId);
		licenseKey.setKey(key);
		licenseKey.setStartDate(startDate);
		licenseKey.setExpirationDate(expirationDate);
		licenseKey.setAdditionalInfo(additionalInfo);
		licenseKey.setComplimentary(complimentary);
		licenseKey.setActive(active);

		if (!complimentary && active) {
			_addProductConsumption(userName, userUuid, licenseKey);
		}

		return licenseKeyPersistence.update(licenseKey);
	}

	protected LicenseKey doAddLicenseKeyVersion3_4(
			Date now, String userName, String userUuid,
			LicenseEntry licenseEntry, Product product, String accountKey,
			String productPurchaseKey, String accountName,
			String licenseEntryType, int licenseVersion, String productVersion,
			long clusterId, String name, String owner, int maxClusterNodes,
			int maxServers, int maxHttpSessions, long maxConcurrentUsers,
			long maxUsers, String sizing, String description,
			String[] hostNames, String[] ipAddresses, String[] macAddresses,
			String[] serverIds, Date startDate, Date expirationDate,
			String additionalInfo, boolean complimentary, boolean active)
		throws Exception {

		accountName = trimText(accountName);

		String licenseEntryName = trimText(licenseEntry.getName());
		String productName = trimText(product.getName());

		String productId = ProductId.PORTAL;

		if (productName.contains(ProductConstants.NAME_COMMERCE_SUBSCRIPTION)) {
			productId = ProductId.COMMERCE;
		}

		owner = trimText(owner);

		if (!licenseEntryType.equals(LicenseType.VIRTUAL_CLUSTER)) {
			maxClusterNodes = 0;
		}

		if (!licenseEntryType.equals(LicenseType.CLUSTER)) {
			maxServers = 1;
		}

		description = trimText(description);

		if (licenseEntryType.equals(LicenseType.DEVELOPER) ||
			licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

			if (maxHttpSessions == 0) {
				maxHttpSessions = 10;
			}
		}
		else {
			maxHttpSessions = 0;
		}

		if (licenseEntryType.equals(LicenseType.CLUSTER)) {
			if (clusterId <= 0) {
				clusterId = counterLocalService.increment(
					getCounterName(
						accountKey, product.getKey(), productPurchaseKey));
			}
			else {
				List<LicenseKey> clusterLicenseKeys =
					licenseKeyPersistence.findByPPK_CI(
						productPurchaseKey, clusterId);

				if (!clusterLicenseKeys.isEmpty()) {
					LicenseKey clusterLicenseKey = clusterLicenseKeys.get(0);

					maxServers = clusterLicenseKey.getMaxServers();
					startDate = clusterLicenseKey.getStartDate();
					expirationDate = clusterLicenseKey.getExpirationDate();
				}
			}
		}

		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		LicenseKey licenseKey = null;

		int keyCount = 0;

		if (ArrayUtil.isNotEmpty(serverIds)) {
			keyCount = serverIds.length;
		}
		else if (hostNames != null) {
			keyCount = hostNames.length;
		}

		for (int i = 0; i < keyCount; i++) {
			String hostName = StringPool.BLANK;
			String curIpAddresses = StringPool.BLANK;
			String curMacAddresses = StringPool.BLANK;
			String serverId = StringPool.BLANK;

			if ((hostNames != null) && (hostNames.length > i)) {
				hostName = hostNames[i];
				curIpAddresses = ipAddresses[i];
				curMacAddresses = macAddresses[i];
			}

			if ((serverIds != null) && (serverIds.length > i)) {
				serverId = serverIds[i];
			}

			if (!licenseEntryType.equals(LicenseType.CLUSTER)) {
				clusterId = counterLocalService.increment(
					getCounterName(
						accountKey, product.getKey(), productPurchaseKey));
			}

			String key = _keyGenerator.generate(
				accountName, licenseEntryName, licenseEntryType, licenseVersion,
				productName, productId, productVersion, owner, maxClusterNodes,
				maxServers, maxHttpSessions, maxConcurrentUsers, maxUsers,
				sizing, description, hostName, curIpAddresses, curMacAddresses,
				new String[] {serverId}, startDate, expirationDate);

			licenseKey = doAddLicenseKey(
				userName, userUuid, now, licenseEntry, accountKey,
				productPurchaseKey, accountName, licenseEntryName,
				licenseEntryType, licenseVersion, productName, productId,
				productVersion, clusterId, name, owner, maxClusterNodes,
				maxServers, maxConcurrentUsers, maxUsers, maxHttpSessions,
				sizing, description, hostName, curIpAddresses, curMacAddresses,
				serverId, key, startDate, expirationDate, additionalInfo,
				complimentary, active);
		}

		return licenseKey;
	}

	protected void doUpdateLicenseKeyVersion3(
			Date now, LicenseKey licenseKey, String productPurchaseKey,
			List<LicenseKey> clusterLicenseKeys, String userName,
			String userUuid, boolean complimentary, boolean active)
		throws Exception {

		long clusterId = licenseKey.getClusterId();

		if (!productPurchaseKey.equals(licenseKey.getProductPurchaseKey())) {
			Product product = _productWebService.getProduct(
				licenseKey.getProductKey());

			clusterId = counterLocalService.increment(
				getCounterName(
					licenseKey.getAccountKey(), product.getKey(),
					productPurchaseKey));
		}

		for (LicenseKey clusterLicenseKey : clusterLicenseKeys) {
			boolean updateProductPurchaseKey = false;
			boolean updateComplimentary = false;
			boolean updateActive = false;

			if (!productPurchaseKey.equals(
					clusterLicenseKey.getProductPurchaseKey())) {

				updateProductPurchaseKey = true;
			}

			if (complimentary != clusterLicenseKey.isComplimentary()) {
				updateComplimentary = true;
			}

			if (active != clusterLicenseKey.isActive()) {
				updateActive = true;
			}

			if (clusterLicenseKey.getLicenseKeyId() ==
					licenseKey.getLicenseKeyId()) {

				clusterLicenseKey.setActive(active);
			}

			clusterLicenseKey.setModifiedUserUuid(userUuid);
			clusterLicenseKey.setModifiedUserName(userName);
			clusterLicenseKey.setModifiedDate(now);
			clusterLicenseKey.setProductPurchaseKey(productPurchaseKey);
			clusterLicenseKey.setClusterId(clusterId);
			clusterLicenseKey.setComplimentary(complimentary);

			clusterLicenseKey = licenseKeyPersistence.update(clusterLicenseKey);

			if (updateProductPurchaseKey) {
				_deleteProductConsumption(
					userName, userUuid, clusterLicenseKey);

				if (active && !complimentary) {
					_addProductConsumption(
						userName, userUuid, clusterLicenseKey);
				}
			}
			else if (active) {
				if (!complimentary && (updateComplimentary || updateActive)) {
					_addProductConsumption(userName, userUuid, licenseKey);
				}
				else if (complimentary && updateComplimentary) {
					_deleteProductConsumption(userName, userUuid, licenseKey);
				}
			}
			else if (updateActive) {
				_deleteProductConsumption(userName, userUuid, licenseKey);
			}
		}
	}

	protected List<LicenseKey> getClusterLicenseKeys(
		LicenseKey licenseKey, String type) {

		if ((type.equals(LicenseType.CLUSTER) ||
			 type.equals(LicenseType.DEVELOPER_CLUSTER)) &&
			(licenseKey.getLicenseVersion() != 2)) {

			return licenseKeyPersistence.findByPPK_CI(
				licenseKey.getProductPurchaseKey(), licenseKey.getClusterId());
		}

		return Arrays.asList(licenseKey);
	}

	protected String getCounterName(String productPurchaseKey) {
		return LicenseKey.class.getName(
		).concat(
			StringPool.POUND
		).concat(
			String.valueOf(productPurchaseKey)
		);
	}

	protected String getCounterName(
		String accountKey, String productKey, String productPurchaseKey) {

		StringBundler sb = new StringBundler(5);

		sb.append(LicenseKey.class.getName());
		sb.append(StringPool.POUND);

		if (Validator.isNotNull(productPurchaseKey)) {
			sb.append(productPurchaseKey);
		}
		else {
			sb.append(accountKey);
			sb.append(StringPool.POUND);
			sb.append(productKey);
		}

		return sb.toString();
	}

	protected String truncateText(
		String text, String defaultText, int maxLength) {

		if (Validator.isNull(text)) {
			if (Validator.isNull(defaultText)) {
				return null;
			}

			return defaultText.substring(
				0, Math.min(maxLength, defaultText.length()));
		}

		return text.substring(0, Math.min(maxLength, text.length()));
	}

	protected void validate(
			String hostName, String ipAddresses, String macAddresses)
		throws PortalException {

		Set<String> distinctIpAddresses = new HashSet<>();

		String[] curIpAddresses = StringUtil.split(ipAddresses);

		for (String ipAddress : curIpAddresses) {
			validateIpAddress(ipAddress);

			if (distinctIpAddresses.contains(ipAddress)) {
				throw new DuplicateIPAddressException("Duplicate IP addresses");
			}

			distinctIpAddresses.add(ipAddress);
		}

		Set<String> distinctMacAddresses = new HashSet<>();

		String[] curMacAddresses = StringUtil.split(macAddresses);

		for (String macAddress : curMacAddresses) {
			validateMacAddress(macAddress);

			if (distinctMacAddresses.contains(macAddress)) {
				throw new DuplicateMACAddressException(
					"Duplicate MAC addresses");
			}

			distinctMacAddresses.add(macAddress);
		}

		if (Validator.isNull(hostName) && distinctIpAddresses.isEmpty() &&
			distinctMacAddresses.isEmpty()) {

			throw new LicenseKeyServerInfoException("Invalid server details");
		}
	}

	protected void validate(
			String licenseEntryType, String productPurchaseKey, String hostName,
			String ipAddresses, String macAddresses, Date startDate,
			Date expirationDate)
		throws PortalException {

		if ((startDate == null) || (expirationDate == null) ||
			expirationDate.before(startDate)) {

			throw new LicenseKeyDateException(
				"Invalid start date or expiration date");
		}

		if (licenseEntryType.equals(LicenseType.BACKUP) ||
			licenseEntryType.equals(LicenseType.LIMITED) ||
			licenseEntryType.equals(LicenseType.PER_USER) ||
			licenseEntryType.equals(LicenseType.PRODUCTION)) {

			validate(hostName, ipAddresses, macAddresses);
		}
	}

	protected void validate(
			String productVersion, String name, String owner,
			String description, String licenseEntryType, int maxClusterNodes)
		throws PortalException {

		if (Validator.isNull(productVersion)) {
			throw new LicenseKeyProductVersionException(
				"Invalid product version");
		}

		if (Validator.isNull(name) || (name.length() > 75)) {
			throw new LicenseKeyNameException("Invalid license name");
		}

		if (Validator.isNull(owner) || (owner.length() > 75)) {
			throw new LicenseKeyOwnerException("Invalid owner");
		}

		if (Validator.isNull(description) || (description.length() > 255)) {
			throw new LicenseKeyDescriptionException("Invalid description");
		}

		if (licenseEntryType.equals(LicenseType.VIRTUAL_CLUSTER) &&
			(maxClusterNodes <= 0)) {

			throw new LicenseKeyMaxClusterNodesException(
				"Invalid max cluster nodes");
		}
	}

	protected void validate(
			String licenseEntryType, String owner, String description,
			String hostName, String ipAddresses, String macAddresses)
		throws PortalException {

		if (Validator.isNull(owner) || (owner.length() > 75)) {
			throw new LicenseKeyOwnerException("Invalid owner");
		}

		if (Validator.isNull(description) || (description.length() > 255)) {
			throw new LicenseKeyDescriptionException("Invalid description");
		}

		if (licenseEntryType.equals(LicenseType.BACKUP) ||
			licenseEntryType.equals(LicenseType.LIMITED) ||
			licenseEntryType.equals(LicenseType.PER_USER) ||
			licenseEntryType.equals(LicenseType.PRODUCTION)) {

			validate(hostName, ipAddresses, macAddresses);
		}
	}

	protected void validateIpAddress(String ipAddress) throws PortalException {
		if (!Validator.isIPAddress(ipAddress)) {
			throw new LicenseKeyIPAddressException("Invalid IP addresses");
		}
	}

	protected void validateMacAddress(String macAddress)
		throws PortalException {

		String curMacAddress = StringUtil.replace(
			macAddress, CharPool.DASH, CharPool.COLON);

		String[] octets = StringUtil.split(curMacAddress, StringPool.COLON);

		if (octets.length != 6) {
			throw new LicenseKeyMACAddressException("Invalid MAC addresses");
		}

		for (String octet : octets) {
			if (octet.length() > 2) {
				throw new LicenseKeyMACAddressException(
					"Invalid MAC addresses");
			}

			char[] charArray = octet.toCharArray();

			for (char c : charArray) {
				if (!Validator.isDigit(c) &&
					((c < 65) || ((c > 70) && (c < 97)) || (c > 102))) {

					throw new LicenseKeyMACAddressException(
						"Invalid MAC addresses");
				}
			}
		}
	}

	private void _addProductConsumption(
			String userName, String userUuid, LicenseKey licenseKey)
		throws Exception {

		int count = 1;

		String licenseEntryType = licenseKey.getLicenseEntryType();

		if (licenseEntryType.equals(LicenseType.VIRTUAL_CLUSTER)) {
			count = licenseKey.getMaxClusterNodes();
		}

		for (int i = 0; i < count; i++) {
			ProductConsumption productConsumption = new ProductConsumption();

			productConsumption.setEndDate(licenseKey.getExpirationDate());

			Product product = _productWebService.getProduct(
				licenseKey.getProductKey());

			productConsumption.setProductKey(product.getKey());

			if (Validator.isNotNull(licenseKey.getProductPurchaseKey())) {
				productConsumption.setProductPurchaseKey(
					licenseKey.getProductPurchaseKey());
			}

			productConsumption.setStartDate(licenseKey.getStartDate());

			ExternalLink externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.PROVISIONING);
			externalLink.setEntityName(ExternalLinkEntityName.LICENSE_KEY);
			externalLink.setEntityId(
				String.valueOf(licenseKey.getLicenseKeyId()));

			productConsumption.setExternalLinks(
				new ExternalLink[] {externalLink});

			_productConsumptionWebService.addProductConsumption(
				userName, userUuid, licenseKey.getAccountKey(),
				productConsumption);
		}
	}

	private void _deleteProductConsumption(
			String userName, String userUuid, LicenseKey licenseKey)
		throws Exception {

		List<ProductConsumption> productConsumptions =
			_productConsumptionWebService.getProductConsumptions(
				ExternalLinkDomain.PROVISIONING,
				ExternalLinkEntityName.LICENSE_KEY,
				String.valueOf(licenseKey.getLicenseKeyId()), 1, 1000);

		for (ProductConsumption productConsumption : productConsumptions) {
			_productConsumptionWebService.deleteProductConsumption(
				userName, userUuid, productConsumption.getKey());
		}
	}

	private static final String _INDEX_DATE_FORMAT_PATTERN = PropsUtil.get(
		PropsKeys.INDEX_DATE_FORMAT_PATTERN);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactIdentityProvider _contactIdentityProvider;

	private final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			_INDEX_DATE_FORMAT_PATTERN);

	@Reference
	private KeyGenerator _keyGenerator;

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private ProductConsumptionWebService _productConsumptionWebService;

	@Reference
	private ProductWebService _productWebService;

}