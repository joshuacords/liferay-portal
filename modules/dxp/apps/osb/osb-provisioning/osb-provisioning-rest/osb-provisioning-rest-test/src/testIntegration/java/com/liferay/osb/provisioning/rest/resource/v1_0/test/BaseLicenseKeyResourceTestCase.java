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

package com.liferay.osb.provisioning.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.client.dto.v1_0.LicenseKeyGenerateForm;
import com.liferay.osb.provisioning.rest.client.dto.v1_0.Type;
import com.liferay.osb.provisioning.rest.client.http.HttpInvoker;
import com.liferay.osb.provisioning.rest.client.pagination.Page;
import com.liferay.osb.provisioning.rest.client.pagination.Pagination;
import com.liferay.osb.provisioning.rest.client.resource.v1_0.LicenseKeyResource;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.LicenseKeySerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public abstract class BaseLicenseKeyResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_licenseKeyResource.setContextCompany(testCompany);

		LicenseKeyResource.Builder builder = LicenseKeyResource.builder();

		licenseKeyResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		LicenseKey licenseKey1 = randomLicenseKey();

		String json = objectMapper.writeValueAsString(licenseKey1);

		LicenseKey licenseKey2 = LicenseKeySerDes.toDTO(json);

		Assert.assertTrue(equals(licenseKey1, licenseKey2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		LicenseKey licenseKey = randomLicenseKey();

		String json1 = objectMapper.writeValueAsString(licenseKey);
		String json2 = LicenseKeySerDes.toJSON(licenseKey);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		LicenseKey licenseKey = randomLicenseKey();

		licenseKey.setAccountKey(regex);
		licenseKey.setAccountName(regex);
		licenseKey.setAdditionalInfo(regex);
		licenseKey.setAssetReceiptLicenseUuid(regex);
		licenseKey.setDescription(regex);
		licenseKey.setHostName(regex);
		licenseKey.setIpAddresses(regex);
		licenseKey.setKey(regex);
		licenseKey.setMacAddresses(regex);
		licenseKey.setModifiedUserName(regex);
		licenseKey.setModifiedUserUuid(regex);
		licenseKey.setName(regex);
		licenseKey.setOwner(regex);
		licenseKey.setProductId(regex);
		licenseKey.setProductKey(regex);
		licenseKey.setProductName(regex);
		licenseKey.setProductPurchaseKey(regex);
		licenseKey.setProductVersion(regex);
		licenseKey.setServerId(regex);
		licenseKey.setUserName(regex);
		licenseKey.setUserUuid(regex);

		String json = LicenseKeySerDes.toJSON(licenseKey);

		Assert.assertFalse(json.contains(regex));

		licenseKey = LicenseKeySerDes.toDTO(json);

		Assert.assertEquals(regex, licenseKey.getAccountKey());
		Assert.assertEquals(regex, licenseKey.getAccountName());
		Assert.assertEquals(regex, licenseKey.getAdditionalInfo());
		Assert.assertEquals(regex, licenseKey.getAssetReceiptLicenseUuid());
		Assert.assertEquals(regex, licenseKey.getDescription());
		Assert.assertEquals(regex, licenseKey.getHostName());
		Assert.assertEquals(regex, licenseKey.getIpAddresses());
		Assert.assertEquals(regex, licenseKey.getKey());
		Assert.assertEquals(regex, licenseKey.getMacAddresses());
		Assert.assertEquals(regex, licenseKey.getModifiedUserName());
		Assert.assertEquals(regex, licenseKey.getModifiedUserUuid());
		Assert.assertEquals(regex, licenseKey.getName());
		Assert.assertEquals(regex, licenseKey.getOwner());
		Assert.assertEquals(regex, licenseKey.getProductId());
		Assert.assertEquals(regex, licenseKey.getProductKey());
		Assert.assertEquals(regex, licenseKey.getProductName());
		Assert.assertEquals(regex, licenseKey.getProductPurchaseKey());
		Assert.assertEquals(regex, licenseKey.getProductVersion());
		Assert.assertEquals(regex, licenseKey.getServerId());
		Assert.assertEquals(regex, licenseKey.getUserName());
		Assert.assertEquals(regex, licenseKey.getUserUuid());
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPage() throws Exception {
		String accountKey =
			testGetAccountAccountKeyLicenseKeysPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyLicenseKeysPage_getIrrelevantAccountKey();

		Page<LicenseKey> page =
			licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
				accountKey, null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			LicenseKey irrelevantLicenseKey =
				testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
					irrelevantAccountKey, randomIrrelevantLicenseKey());

			page = licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
				irrelevantAccountKey, null, null, Pagination.of(1, 2), null);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantLicenseKey),
				(List<LicenseKey>)page.getItems());
			assertValid(page);
		}

		LicenseKey licenseKey1 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		LicenseKey licenseKey2 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		page = licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
			accountKey, null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(licenseKey1, licenseKey2),
			(List<LicenseKey>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		String accountKey =
			testGetAccountAccountKeyLicenseKeysPage_getAccountKey();

		LicenseKey licenseKey1 = randomLicenseKey();

		licenseKey1 = testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
			accountKey, licenseKey1);

		for (EntityField entityField : entityFields) {
			Page<LicenseKey> page =
				licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
					accountKey, null,
					getFilterString(entityField, "between", licenseKey1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(licenseKey1),
				(List<LicenseKey>)page.getItems());
		}
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithFilterDoubleEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		String accountKey =
			testGetAccountAccountKeyLicenseKeysPage_getAccountKey();

		LicenseKey licenseKey1 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		LicenseKey licenseKey2 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		for (EntityField entityField : entityFields) {
			Page<LicenseKey> page =
				licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
					accountKey, null,
					getFilterString(entityField, "eq", licenseKey1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(licenseKey1),
				(List<LicenseKey>)page.getItems());
		}
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		String accountKey =
			testGetAccountAccountKeyLicenseKeysPage_getAccountKey();

		LicenseKey licenseKey1 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		LicenseKey licenseKey2 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		for (EntityField entityField : entityFields) {
			Page<LicenseKey> page =
				licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
					accountKey, null,
					getFilterString(entityField, "eq", licenseKey1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(licenseKey1),
				(List<LicenseKey>)page.getItems());
		}
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyLicenseKeysPage_getAccountKey();

		LicenseKey licenseKey1 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		LicenseKey licenseKey2 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		LicenseKey licenseKey3 =
			testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
				accountKey, randomLicenseKey());

		Page<LicenseKey> page1 =
			licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
				accountKey, null, null, Pagination.of(1, 2), null);

		List<LicenseKey> licenseKeys1 = (List<LicenseKey>)page1.getItems();

		Assert.assertEquals(licenseKeys1.toString(), 2, licenseKeys1.size());

		Page<LicenseKey> page2 =
			licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
				accountKey, null, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<LicenseKey> licenseKeys2 = (List<LicenseKey>)page2.getItems();

		Assert.assertEquals(licenseKeys2.toString(), 1, licenseKeys2.size());

		Page<LicenseKey> page3 =
			licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
				accountKey, null, null, Pagination.of(1, 3), null);

		assertEqualsIgnoringOrder(
			Arrays.asList(licenseKey1, licenseKey2, licenseKey3),
			(List<LicenseKey>)page3.getItems());
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithSortDateTime()
		throws Exception {

		testGetAccountAccountKeyLicenseKeysPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, licenseKey1, licenseKey2) -> {
				BeanUtils.setProperty(
					licenseKey1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithSortDouble()
		throws Exception {

		testGetAccountAccountKeyLicenseKeysPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, licenseKey1, licenseKey2) -> {
				BeanUtils.setProperty(licenseKey1, entityField.getName(), 0.1);
				BeanUtils.setProperty(licenseKey2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithSortInteger()
		throws Exception {

		testGetAccountAccountKeyLicenseKeysPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, licenseKey1, licenseKey2) -> {
				BeanUtils.setProperty(licenseKey1, entityField.getName(), 0);
				BeanUtils.setProperty(licenseKey2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeysPageWithSortString()
		throws Exception {

		testGetAccountAccountKeyLicenseKeysPageWithSort(
			EntityField.Type.STRING,
			(entityField, licenseKey1, licenseKey2) -> {
				Class<?> clazz = licenseKey1.getClass();

				String entityFieldName = entityField.getName();

				java.lang.reflect.Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						licenseKey1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						licenseKey2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanUtils.setProperty(
						licenseKey1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanUtils.setProperty(
						licenseKey2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanUtils.setProperty(
						licenseKey1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanUtils.setProperty(
						licenseKey2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetAccountAccountKeyLicenseKeysPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, LicenseKey, LicenseKey, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		String accountKey =
			testGetAccountAccountKeyLicenseKeysPage_getAccountKey();

		LicenseKey licenseKey1 = randomLicenseKey();
		LicenseKey licenseKey2 = randomLicenseKey();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, licenseKey1, licenseKey2);
		}

		licenseKey1 = testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
			accountKey, licenseKey1);

		licenseKey2 = testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
			accountKey, licenseKey2);

		for (EntityField entityField : entityFields) {
			Page<LicenseKey> ascPage =
				licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
					accountKey, null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(licenseKey1, licenseKey2),
				(List<LicenseKey>)ascPage.getItems());

			Page<LicenseKey> descPage =
				licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
					accountKey, null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(licenseKey2, licenseKey1),
				(List<LicenseKey>)descPage.getItems());
		}
	}

	protected LicenseKey testGetAccountAccountKeyLicenseKeysPage_addLicenseKey(
			String accountKey, LicenseKey licenseKey)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyLicenseKeysPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyLicenseKeysPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAccountAccountKeyLicenseKeysPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetAccountAccountKeyLicenseKeyExport() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetAccountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutLicenseKeyActivate() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		LicenseKey licenseKey = testPutLicenseKeyActivate_addLicenseKey();

		assertHttpResponseStatusCode(
			204, licenseKeyResource.putLicenseKeyActivateHttpResponse(null));

		assertHttpResponseStatusCode(
			404, licenseKeyResource.putLicenseKeyActivateHttpResponse(null));
	}

	protected LicenseKey testPutLicenseKeyActivate_addLicenseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutLicenseKeyDeactivate() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		LicenseKey licenseKey = testPutLicenseKeyDeactivate_addLicenseKey();

		assertHttpResponseStatusCode(
			204, licenseKeyResource.putLicenseKeyDeactivateHttpResponse(null));

		assertHttpResponseStatusCode(
			404, licenseKeyResource.putLicenseKeyDeactivateHttpResponse(null));
	}

	protected LicenseKey testPutLicenseKeyDeactivate_addLicenseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetLicenseKeyDownload() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetLicenseKeyDownloadZip() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetLicenseKeyExport() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostLicenseKeysExtendPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetLicenseKeyDownload() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetProductGroupProductGroupNameDevelopmentLicenseKey()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	@Test
	public void testGetAccountAccountKeyProductGroupProductGroupNameGenerateForm()
		throws Exception {

		LicenseKey postLicenseKey = testGetLicenseKey_addLicenseKey();

		LicenseKeyGenerateForm postLicenseKeyGenerateForm =
			testGetAccountAccountKeyProductGroupProductGroupNameGenerateForm_addLicenseKeyGenerateForm(
				postLicenseKey.getId(), randomLicenseKeyGenerateForm());

		LicenseKeyGenerateForm getLicenseKeyGenerateForm =
			licenseKeyResource.
				getAccountAccountKeyProductGroupProductGroupNameGenerateForm(
					postLicenseKey.getId());

		assertEquals(postLicenseKeyGenerateForm, getLicenseKeyGenerateForm);
		assertValid(getLicenseKeyGenerateForm);
	}

	protected LicenseKeyGenerateForm
			testGetAccountAccountKeyProductGroupProductGroupNameGenerateForm_addLicenseKeyGenerateForm(
				long licenseKeyId,
				LicenseKeyGenerateForm licenseKeyGenerateForm)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected LicenseKey testGraphQLLicenseKey_addLicenseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		LicenseKey licenseKey, List<LicenseKey> licenseKeys) {

		boolean contains = false;

		for (LicenseKey item : licenseKeys) {
			if (equals(licenseKey, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			licenseKeys + " does not contain " + licenseKey, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		LicenseKey licenseKey1, LicenseKey licenseKey2) {

		Assert.assertTrue(
			licenseKey1 + " does not equal " + licenseKey2,
			equals(licenseKey1, licenseKey2));
	}

	protected void assertEquals(
		List<LicenseKey> licenseKeys1, List<LicenseKey> licenseKeys2) {

		Assert.assertEquals(licenseKeys1.size(), licenseKeys2.size());

		for (int i = 0; i < licenseKeys1.size(); i++) {
			LicenseKey licenseKey1 = licenseKeys1.get(i);
			LicenseKey licenseKey2 = licenseKeys2.get(i);

			assertEquals(licenseKey1, licenseKey2);
		}
	}

	protected void assertEquals(
		LicenseKeyGenerateForm licenseKeyGenerateForm1,
		LicenseKeyGenerateForm licenseKeyGenerateForm2) {

		Assert.assertTrue(
			licenseKeyGenerateForm1 + " does not equal " +
				licenseKeyGenerateForm2,
			equals(licenseKeyGenerateForm1, licenseKeyGenerateForm2));
	}

	protected void assertEqualsIgnoringOrder(
		List<LicenseKey> licenseKeys1, List<LicenseKey> licenseKeys2) {

		Assert.assertEquals(licenseKeys1.size(), licenseKeys2.size());

		for (LicenseKey licenseKey1 : licenseKeys1) {
			boolean contains = false;

			for (LicenseKey licenseKey2 : licenseKeys2) {
				if (equals(licenseKey1, licenseKey2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				licenseKeys2 + " does not contain " + licenseKey1, contains);
		}
	}

	protected void assertValid(LicenseKey licenseKey) throws Exception {
		boolean valid = true;

		if (licenseKey.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (licenseKey.getAccountKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("accountName", additionalAssertFieldName)) {
				if (licenseKey.getAccountName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("active", additionalAssertFieldName)) {
				if (licenseKey.getActive() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("additionalInfo", additionalAssertFieldName)) {
				if (licenseKey.getAdditionalInfo() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"assetReceiptLicenseUuid", additionalAssertFieldName)) {

				if (licenseKey.getAssetReceiptLicenseUuid() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("clusterId", additionalAssertFieldName)) {
				if (licenseKey.getClusterId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("complimentary", additionalAssertFieldName)) {
				if (licenseKey.getComplimentary() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (licenseKey.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (licenseKey.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (licenseKey.getExpirationDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("hostName", additionalAssertFieldName)) {
				if (licenseKey.getHostName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("ipAddresses", additionalAssertFieldName)) {
				if (licenseKey.getIpAddresses() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (licenseKey.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("licenseEntryName", additionalAssertFieldName)) {
				if (licenseKey.getLicenseEntryName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("licenseEntryType", additionalAssertFieldName)) {
				if (licenseKey.getLicenseEntryType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("licenseVersion", additionalAssertFieldName)) {
				if (licenseKey.getLicenseVersion() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("macAddresses", additionalAssertFieldName)) {
				if (licenseKey.getMacAddresses() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("maxClusterNodes", additionalAssertFieldName)) {
				if (licenseKey.getMaxClusterNodes() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("maxHttpSessions", additionalAssertFieldName)) {
				if (licenseKey.getMaxHttpSessions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("maxServers", additionalAssertFieldName)) {
				if (licenseKey.getMaxServers() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (licenseKey.getModifiedDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserName", additionalAssertFieldName)) {
				if (licenseKey.getModifiedUserName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserUuid", additionalAssertFieldName)) {
				if (licenseKey.getModifiedUserUuid() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (licenseKey.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("owner", additionalAssertFieldName)) {
				if (licenseKey.getOwner() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (licenseKey.getProductId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productKey", additionalAssertFieldName)) {
				if (licenseKey.getProductKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productName", additionalAssertFieldName)) {
				if (licenseKey.getProductName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"productPurchaseKey", additionalAssertFieldName)) {

				if (licenseKey.getProductPurchaseKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productVersion", additionalAssertFieldName)) {
				if (licenseKey.getProductVersion() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("serverId", additionalAssertFieldName)) {
				if (licenseKey.getServerId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("sizing", additionalAssertFieldName)) {
				if (licenseKey.getSizing() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (licenseKey.getStartDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userName", additionalAssertFieldName)) {
				if (licenseKey.getUserName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userUuid", additionalAssertFieldName)) {
				if (licenseKey.getUserUuid() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<LicenseKey> page) {
		boolean valid = false;

		java.util.Collection<LicenseKey> licenseKeys = page.getItems();

		int size = licenseKeys.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(LicenseKeyGenerateForm licenseKeyGenerateForm) {
		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalLicenseKeyGenerateFormAssertFieldNames()) {

			if (Objects.equals(
					"allowPermanentLicenses", additionalAssertFieldName)) {

				if (licenseKeyGenerateForm.getAllowPermanentLicenses() ==
						null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"subscriptionTerms", additionalAssertFieldName)) {

				if (licenseKeyGenerateForm.getSubscriptionTerms() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("versions", additionalAssertFieldName)) {
				if (licenseKeyGenerateForm.getVersions() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected String[] getAdditionalLicenseKeyGenerateFormAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey.
						class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(LicenseKey licenseKey1, LicenseKey licenseKey2) {
		if (licenseKey1 == licenseKey2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getAccountKey(),
						licenseKey2.getAccountKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("accountName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getAccountName(),
						licenseKey2.getAccountName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("active", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getActive(), licenseKey2.getActive())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("additionalInfo", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getAdditionalInfo(),
						licenseKey2.getAdditionalInfo())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"assetReceiptLicenseUuid", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						licenseKey1.getAssetReceiptLicenseUuid(),
						licenseKey2.getAssetReceiptLicenseUuid())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("clusterId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getClusterId(),
						licenseKey2.getClusterId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("complimentary", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getComplimentary(),
						licenseKey2.getComplimentary())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getCreateDate(),
						licenseKey2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getDescription(),
						licenseKey2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getExpirationDate(),
						licenseKey2.getExpirationDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("hostName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getHostName(), licenseKey2.getHostName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getId(), licenseKey2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("ipAddresses", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getIpAddresses(),
						licenseKey2.getIpAddresses())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getKey(), licenseKey2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("licenseEntryName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getLicenseEntryName(),
						licenseKey2.getLicenseEntryName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("licenseEntryType", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getLicenseEntryType(),
						licenseKey2.getLicenseEntryType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("licenseVersion", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getLicenseVersion(),
						licenseKey2.getLicenseVersion())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("macAddresses", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getMacAddresses(),
						licenseKey2.getMacAddresses())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("maxClusterNodes", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getMaxClusterNodes(),
						licenseKey2.getMaxClusterNodes())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("maxHttpSessions", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getMaxHttpSessions(),
						licenseKey2.getMaxHttpSessions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("maxServers", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getMaxServers(),
						licenseKey2.getMaxServers())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getModifiedDate(),
						licenseKey2.getModifiedDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getModifiedUserName(),
						licenseKey2.getModifiedUserName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserUuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getModifiedUserUuid(),
						licenseKey2.getModifiedUserUuid())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getName(), licenseKey2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("owner", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getOwner(), licenseKey2.getOwner())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getProductId(),
						licenseKey2.getProductId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getProductKey(),
						licenseKey2.getProductKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getProductName(),
						licenseKey2.getProductName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"productPurchaseKey", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						licenseKey1.getProductPurchaseKey(),
						licenseKey2.getProductPurchaseKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productVersion", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getProductVersion(),
						licenseKey2.getProductVersion())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("serverId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getServerId(), licenseKey2.getServerId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("sizing", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getSizing(), licenseKey2.getSizing())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getStartDate(),
						licenseKey2.getStartDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getUserName(), licenseKey2.getUserName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userUuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKey1.getUserUuid(), licenseKey2.getUserUuid())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected boolean equals(
		LicenseKeyGenerateForm licenseKeyGenerateForm1,
		LicenseKeyGenerateForm licenseKeyGenerateForm2) {

		if (licenseKeyGenerateForm1 == licenseKeyGenerateForm2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalLicenseKeyGenerateFormAssertFieldNames()) {

			if (Objects.equals(
					"allowPermanentLicenses", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						licenseKeyGenerateForm1.getAllowPermanentLicenses(),
						licenseKeyGenerateForm2.getAllowPermanentLicenses())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"subscriptionTerms", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						licenseKeyGenerateForm1.getSubscriptionTerms(),
						licenseKeyGenerateForm2.getSubscriptionTerms())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("versions", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						licenseKeyGenerateForm1.getVersions(),
						licenseKeyGenerateForm2.getVersions())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		Stream<java.lang.reflect.Field> stream = Stream.of(
			ReflectionUtil.getDeclaredFields(clazz));

		return stream.filter(
			field -> !field.isSynthetic()
		).toArray(
			java.lang.reflect.Field[]::new
		);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_licenseKeyResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_licenseKeyResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, LicenseKey licenseKey) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("accountKey")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getAccountKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("accountName")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getAccountName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("active")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("additionalInfo")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getAdditionalInfo()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("assetReceiptLicenseUuid")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getAssetReceiptLicenseUuid()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("clusterId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("complimentary")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("createDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(licenseKey.getCreateDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(licenseKey.getCreateDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(licenseKey.getCreateDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("expirationDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							licenseKey.getExpirationDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							licenseKey.getExpirationDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(licenseKey.getExpirationDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("hostName")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getHostName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("ipAddresses")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getIpAddresses()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("licenseEntryName")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("licenseEntryType")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("licenseVersion")) {
			sb.append(String.valueOf(licenseKey.getLicenseVersion()));

			return sb.toString();
		}

		if (entityFieldName.equals("macAddresses")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getMacAddresses()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("maxClusterNodes")) {
			sb.append(String.valueOf(licenseKey.getMaxClusterNodes()));

			return sb.toString();
		}

		if (entityFieldName.equals("maxHttpSessions")) {
			sb.append(String.valueOf(licenseKey.getMaxHttpSessions()));

			return sb.toString();
		}

		if (entityFieldName.equals("maxServers")) {
			sb.append(String.valueOf(licenseKey.getMaxServers()));

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							licenseKey.getModifiedDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(licenseKey.getModifiedDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(licenseKey.getModifiedDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedUserName")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getModifiedUserName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedUserUuid")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getModifiedUserUuid()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("owner")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getOwner()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productId")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getProductId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productKey")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getProductKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productName")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getProductName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productPurchaseKey")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getProductPurchaseKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productVersion")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getProductVersion()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("serverId")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getServerId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("sizing")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("startDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(licenseKey.getStartDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(licenseKey.getStartDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(licenseKey.getStartDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("userName")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getUserName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("userUuid")) {
			sb.append("'");
			sb.append(String.valueOf(licenseKey.getUserUuid()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected LicenseKey randomLicenseKey() throws Exception {
		return new LicenseKey() {
			{
				accountKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				accountName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				active = RandomTestUtil.randomBoolean();
				additionalInfo = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				assetReceiptLicenseUuid = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				clusterId = RandomTestUtil.randomLong();
				complimentary = RandomTestUtil.randomBoolean();
				createDate = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				expirationDate = RandomTestUtil.nextDate();
				hostName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				ipAddresses = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				licenseVersion = RandomTestUtil.randomInt();
				macAddresses = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				maxClusterNodes = RandomTestUtil.randomInt();
				maxHttpSessions = RandomTestUtil.randomInt();
				maxServers = RandomTestUtil.randomInt();
				modifiedDate = RandomTestUtil.nextDate();
				modifiedUserName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				modifiedUserUuid = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				owner = StringUtil.toLowerCase(RandomTestUtil.randomString());
				productId = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				productKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				productName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				productPurchaseKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				productVersion = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				serverId = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				startDate = RandomTestUtil.nextDate();
				userName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				userUuid = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected LicenseKey randomIrrelevantLicenseKey() throws Exception {
		LicenseKey randomIrrelevantLicenseKey = randomLicenseKey();

		return randomIrrelevantLicenseKey;
	}

	protected LicenseKey randomPatchLicenseKey() throws Exception {
		return randomLicenseKey();
	}

	protected LicenseKeyGenerateForm randomLicenseKeyGenerateForm()
		throws Exception {

		return new LicenseKeyGenerateForm() {
			{
				allowPermanentLicenses = RandomTestUtil.randomBoolean();
			}
		};
	}

	protected LicenseKeyResource licenseKeyResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseLicenseKeyResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource
		_licenseKeyResource;

}