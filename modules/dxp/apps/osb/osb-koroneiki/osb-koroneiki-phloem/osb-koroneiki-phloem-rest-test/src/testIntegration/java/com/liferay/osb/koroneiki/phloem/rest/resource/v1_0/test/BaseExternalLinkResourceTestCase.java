/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ExternalLinkSerDes;
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
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BaseExternalLinkResourceTestCase {

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

		_externalLinkResource.setContextCompany(testCompany);

		ExternalLinkResource.Builder builder = ExternalLinkResource.builder();

		externalLinkResource = builder.authentication(
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

		ExternalLink externalLink1 = randomExternalLink();

		String json = objectMapper.writeValueAsString(externalLink1);

		ExternalLink externalLink2 = ExternalLinkSerDes.toDTO(json);

		Assert.assertTrue(equals(externalLink1, externalLink2));
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

		ExternalLink externalLink = randomExternalLink();

		String json1 = objectMapper.writeValueAsString(externalLink);
		String json2 = ExternalLinkSerDes.toJSON(externalLink);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ExternalLink externalLink = randomExternalLink();

		externalLink.setDomain(regex);
		externalLink.setEntityId(regex);
		externalLink.setEntityName(regex);
		externalLink.setKey(regex);
		externalLink.setUrl(regex);

		String json = ExternalLinkSerDes.toJSON(externalLink);

		Assert.assertFalse(json.contains(regex));

		externalLink = ExternalLinkSerDes.toDTO(json);

		Assert.assertEquals(regex, externalLink.getDomain());
		Assert.assertEquals(regex, externalLink.getEntityId());
		Assert.assertEquals(regex, externalLink.getEntityName());
		Assert.assertEquals(regex, externalLink.getKey());
		Assert.assertEquals(regex, externalLink.getUrl());
	}

	@Test
	public void testGetAccountAccountKeyExternalLinksPage() throws Exception {
		String accountKey =
			testGetAccountAccountKeyExternalLinksPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyExternalLinksPage_getIrrelevantAccountKey();

		Page<ExternalLink> page =
			externalLinkResource.getAccountAccountKeyExternalLinksPage(
				accountKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			ExternalLink irrelevantExternalLink =
				testGetAccountAccountKeyExternalLinksPage_addExternalLink(
					irrelevantAccountKey, randomIrrelevantExternalLink());

			page = externalLinkResource.getAccountAccountKeyExternalLinksPage(
				irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantExternalLink),
				(List<ExternalLink>)page.getItems());
			assertValid(page);
		}

		ExternalLink externalLink1 =
			testGetAccountAccountKeyExternalLinksPage_addExternalLink(
				accountKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetAccountAccountKeyExternalLinksPage_addExternalLink(
				accountKey, randomExternalLink());

		page = externalLinkResource.getAccountAccountKeyExternalLinksPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2),
			(List<ExternalLink>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyExternalLinksPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyExternalLinksPage_getAccountKey();

		ExternalLink externalLink1 =
			testGetAccountAccountKeyExternalLinksPage_addExternalLink(
				accountKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetAccountAccountKeyExternalLinksPage_addExternalLink(
				accountKey, randomExternalLink());

		ExternalLink externalLink3 =
			testGetAccountAccountKeyExternalLinksPage_addExternalLink(
				accountKey, randomExternalLink());

		Page<ExternalLink> page1 =
			externalLinkResource.getAccountAccountKeyExternalLinksPage(
				accountKey, Pagination.of(1, 2));

		List<ExternalLink> externalLinks1 =
			(List<ExternalLink>)page1.getItems();

		Assert.assertEquals(
			externalLinks1.toString(), 2, externalLinks1.size());

		Page<ExternalLink> page2 =
			externalLinkResource.getAccountAccountKeyExternalLinksPage(
				accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ExternalLink> externalLinks2 =
			(List<ExternalLink>)page2.getItems();

		Assert.assertEquals(
			externalLinks2.toString(), 1, externalLinks2.size());

		Page<ExternalLink> page3 =
			externalLinkResource.getAccountAccountKeyExternalLinksPage(
				accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2, externalLink3),
			(List<ExternalLink>)page3.getItems());
	}

	protected ExternalLink
			testGetAccountAccountKeyExternalLinksPage_addExternalLink(
				String accountKey, ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyExternalLinksPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyExternalLinksPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAccountAccountKeyExternalLink() throws Exception {
		ExternalLink randomExternalLink = randomExternalLink();

		ExternalLink postExternalLink =
			testPostAccountAccountKeyExternalLink_addExternalLink(
				randomExternalLink);

		assertEquals(randomExternalLink, postExternalLink);
		assertValid(postExternalLink);
	}

	protected ExternalLink
			testPostAccountAccountKeyExternalLink_addExternalLink(
				ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactRoleContactRoleKeyExternalLinksPage()
		throws Exception {

		String contactRoleKey =
			testGetContactRoleContactRoleKeyExternalLinksPage_getContactRoleKey();
		String irrelevantContactRoleKey =
			testGetContactRoleContactRoleKeyExternalLinksPage_getIrrelevantContactRoleKey();

		Page<ExternalLink> page =
			externalLinkResource.getContactRoleContactRoleKeyExternalLinksPage(
				contactRoleKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantContactRoleKey != null) {
			ExternalLink irrelevantExternalLink =
				testGetContactRoleContactRoleKeyExternalLinksPage_addExternalLink(
					irrelevantContactRoleKey, randomIrrelevantExternalLink());

			page =
				externalLinkResource.
					getContactRoleContactRoleKeyExternalLinksPage(
						irrelevantContactRoleKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantExternalLink),
				(List<ExternalLink>)page.getItems());
			assertValid(page);
		}

		ExternalLink externalLink1 =
			testGetContactRoleContactRoleKeyExternalLinksPage_addExternalLink(
				contactRoleKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetContactRoleContactRoleKeyExternalLinksPage_addExternalLink(
				contactRoleKey, randomExternalLink());

		page =
			externalLinkResource.getContactRoleContactRoleKeyExternalLinksPage(
				contactRoleKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2),
			(List<ExternalLink>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactRoleContactRoleKeyExternalLinksPageWithPagination()
		throws Exception {

		String contactRoleKey =
			testGetContactRoleContactRoleKeyExternalLinksPage_getContactRoleKey();

		ExternalLink externalLink1 =
			testGetContactRoleContactRoleKeyExternalLinksPage_addExternalLink(
				contactRoleKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetContactRoleContactRoleKeyExternalLinksPage_addExternalLink(
				contactRoleKey, randomExternalLink());

		ExternalLink externalLink3 =
			testGetContactRoleContactRoleKeyExternalLinksPage_addExternalLink(
				contactRoleKey, randomExternalLink());

		Page<ExternalLink> page1 =
			externalLinkResource.getContactRoleContactRoleKeyExternalLinksPage(
				contactRoleKey, Pagination.of(1, 2));

		List<ExternalLink> externalLinks1 =
			(List<ExternalLink>)page1.getItems();

		Assert.assertEquals(
			externalLinks1.toString(), 2, externalLinks1.size());

		Page<ExternalLink> page2 =
			externalLinkResource.getContactRoleContactRoleKeyExternalLinksPage(
				contactRoleKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ExternalLink> externalLinks2 =
			(List<ExternalLink>)page2.getItems();

		Assert.assertEquals(
			externalLinks2.toString(), 1, externalLinks2.size());

		Page<ExternalLink> page3 =
			externalLinkResource.getContactRoleContactRoleKeyExternalLinksPage(
				contactRoleKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2, externalLink3),
			(List<ExternalLink>)page3.getItems());
	}

	protected ExternalLink
			testGetContactRoleContactRoleKeyExternalLinksPage_addExternalLink(
				String contactRoleKey, ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactRoleContactRoleKeyExternalLinksPage_getContactRoleKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactRoleContactRoleKeyExternalLinksPage_getIrrelevantContactRoleKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostContactRoleContactRoleKeyExternalLink()
		throws Exception {

		ExternalLink randomExternalLink = randomExternalLink();

		ExternalLink postExternalLink =
			testPostContactRoleContactRoleKeyExternalLink_addExternalLink(
				randomExternalLink);

		assertEquals(randomExternalLink, postExternalLink);
		assertValid(postExternalLink);
	}

	protected ExternalLink
			testPostContactRoleContactRoleKeyExternalLink_addExternalLink(
				ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactByUuidContactUuidExternalLinksPage()
		throws Exception {

		String contactUuid =
			testGetContactByUuidContactUuidExternalLinksPage_getContactUuid();
		String irrelevantContactUuid =
			testGetContactByUuidContactUuidExternalLinksPage_getIrrelevantContactUuid();

		Page<ExternalLink> page =
			externalLinkResource.getContactByUuidContactUuidExternalLinksPage(
				contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantContactUuid != null) {
			ExternalLink irrelevantExternalLink =
				testGetContactByUuidContactUuidExternalLinksPage_addExternalLink(
					irrelevantContactUuid, randomIrrelevantExternalLink());

			page =
				externalLinkResource.
					getContactByUuidContactUuidExternalLinksPage(
						irrelevantContactUuid, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantExternalLink),
				(List<ExternalLink>)page.getItems());
			assertValid(page);
		}

		ExternalLink externalLink1 =
			testGetContactByUuidContactUuidExternalLinksPage_addExternalLink(
				contactUuid, randomExternalLink());

		ExternalLink externalLink2 =
			testGetContactByUuidContactUuidExternalLinksPage_addExternalLink(
				contactUuid, randomExternalLink());

		page =
			externalLinkResource.getContactByUuidContactUuidExternalLinksPage(
				contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2),
			(List<ExternalLink>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactByUuidContactUuidExternalLinksPageWithPagination()
		throws Exception {

		String contactUuid =
			testGetContactByUuidContactUuidExternalLinksPage_getContactUuid();

		ExternalLink externalLink1 =
			testGetContactByUuidContactUuidExternalLinksPage_addExternalLink(
				contactUuid, randomExternalLink());

		ExternalLink externalLink2 =
			testGetContactByUuidContactUuidExternalLinksPage_addExternalLink(
				contactUuid, randomExternalLink());

		ExternalLink externalLink3 =
			testGetContactByUuidContactUuidExternalLinksPage_addExternalLink(
				contactUuid, randomExternalLink());

		Page<ExternalLink> page1 =
			externalLinkResource.getContactByUuidContactUuidExternalLinksPage(
				contactUuid, Pagination.of(1, 2));

		List<ExternalLink> externalLinks1 =
			(List<ExternalLink>)page1.getItems();

		Assert.assertEquals(
			externalLinks1.toString(), 2, externalLinks1.size());

		Page<ExternalLink> page2 =
			externalLinkResource.getContactByUuidContactUuidExternalLinksPage(
				contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ExternalLink> externalLinks2 =
			(List<ExternalLink>)page2.getItems();

		Assert.assertEquals(
			externalLinks2.toString(), 1, externalLinks2.size());

		Page<ExternalLink> page3 =
			externalLinkResource.getContactByUuidContactUuidExternalLinksPage(
				contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2, externalLink3),
			(List<ExternalLink>)page3.getItems());
	}

	protected ExternalLink
			testGetContactByUuidContactUuidExternalLinksPage_addExternalLink(
				String contactUuid, ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidExternalLinksPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidExternalLinksPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	@Test
	public void testPostContactByUuidContactUuidExternalLink()
		throws Exception {

		ExternalLink randomExternalLink = randomExternalLink();

		ExternalLink postExternalLink =
			testPostContactByUuidContactUuidExternalLink_addExternalLink(
				randomExternalLink);

		assertEquals(randomExternalLink, postExternalLink);
		assertValid(postExternalLink);
	}

	protected ExternalLink
			testPostContactByUuidContactUuidExternalLink_addExternalLink(
				ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteExternalLink() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteExternalLink() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetExternalLink() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetExternalLink() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetExternalLinkNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutExternalLink() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetProductConsumptionProductConsumptionKeyExternalLinksPage()
		throws Exception {

		String productConsumptionKey =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_getProductConsumptionKey();
		String irrelevantProductConsumptionKey =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_getIrrelevantProductConsumptionKey();

		Page<ExternalLink> page =
			externalLinkResource.
				getProductConsumptionProductConsumptionKeyExternalLinksPage(
					productConsumptionKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantProductConsumptionKey != null) {
			ExternalLink irrelevantExternalLink =
				testGetProductConsumptionProductConsumptionKeyExternalLinksPage_addExternalLink(
					irrelevantProductConsumptionKey,
					randomIrrelevantExternalLink());

			page =
				externalLinkResource.
					getProductConsumptionProductConsumptionKeyExternalLinksPage(
						irrelevantProductConsumptionKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantExternalLink),
				(List<ExternalLink>)page.getItems());
			assertValid(page);
		}

		ExternalLink externalLink1 =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_addExternalLink(
				productConsumptionKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_addExternalLink(
				productConsumptionKey, randomExternalLink());

		page =
			externalLinkResource.
				getProductConsumptionProductConsumptionKeyExternalLinksPage(
					productConsumptionKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2),
			(List<ExternalLink>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductConsumptionProductConsumptionKeyExternalLinksPageWithPagination()
		throws Exception {

		String productConsumptionKey =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_getProductConsumptionKey();

		ExternalLink externalLink1 =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_addExternalLink(
				productConsumptionKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_addExternalLink(
				productConsumptionKey, randomExternalLink());

		ExternalLink externalLink3 =
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_addExternalLink(
				productConsumptionKey, randomExternalLink());

		Page<ExternalLink> page1 =
			externalLinkResource.
				getProductConsumptionProductConsumptionKeyExternalLinksPage(
					productConsumptionKey, Pagination.of(1, 2));

		List<ExternalLink> externalLinks1 =
			(List<ExternalLink>)page1.getItems();

		Assert.assertEquals(
			externalLinks1.toString(), 2, externalLinks1.size());

		Page<ExternalLink> page2 =
			externalLinkResource.
				getProductConsumptionProductConsumptionKeyExternalLinksPage(
					productConsumptionKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ExternalLink> externalLinks2 =
			(List<ExternalLink>)page2.getItems();

		Assert.assertEquals(
			externalLinks2.toString(), 1, externalLinks2.size());

		Page<ExternalLink> page3 =
			externalLinkResource.
				getProductConsumptionProductConsumptionKeyExternalLinksPage(
					productConsumptionKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2, externalLink3),
			(List<ExternalLink>)page3.getItems());
	}

	protected ExternalLink
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_addExternalLink(
				String productConsumptionKey, ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_getProductConsumptionKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductConsumptionProductConsumptionKeyExternalLinksPage_getIrrelevantProductConsumptionKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductConsumptionProductConsumptionKeyExternalLink()
		throws Exception {

		ExternalLink randomExternalLink = randomExternalLink();

		ExternalLink postExternalLink =
			testPostProductConsumptionProductConsumptionKeyExternalLink_addExternalLink(
				randomExternalLink);

		assertEquals(randomExternalLink, postExternalLink);
		assertValid(postExternalLink);
	}

	protected ExternalLink
			testPostProductConsumptionProductConsumptionKeyExternalLink_addExternalLink(
				ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetProductPurchaseProductPurchaseKeyExternalLinksPage()
		throws Exception {

		String productPurchaseKey =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_getProductPurchaseKey();
		String irrelevantProductPurchaseKey =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_getIrrelevantProductPurchaseKey();

		Page<ExternalLink> page =
			externalLinkResource.
				getProductPurchaseProductPurchaseKeyExternalLinksPage(
					productPurchaseKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantProductPurchaseKey != null) {
			ExternalLink irrelevantExternalLink =
				testGetProductPurchaseProductPurchaseKeyExternalLinksPage_addExternalLink(
					irrelevantProductPurchaseKey,
					randomIrrelevantExternalLink());

			page =
				externalLinkResource.
					getProductPurchaseProductPurchaseKeyExternalLinksPage(
						irrelevantProductPurchaseKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantExternalLink),
				(List<ExternalLink>)page.getItems());
			assertValid(page);
		}

		ExternalLink externalLink1 =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_addExternalLink(
				productPurchaseKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_addExternalLink(
				productPurchaseKey, randomExternalLink());

		page =
			externalLinkResource.
				getProductPurchaseProductPurchaseKeyExternalLinksPage(
					productPurchaseKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2),
			(List<ExternalLink>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductPurchaseProductPurchaseKeyExternalLinksPageWithPagination()
		throws Exception {

		String productPurchaseKey =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_getProductPurchaseKey();

		ExternalLink externalLink1 =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_addExternalLink(
				productPurchaseKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_addExternalLink(
				productPurchaseKey, randomExternalLink());

		ExternalLink externalLink3 =
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_addExternalLink(
				productPurchaseKey, randomExternalLink());

		Page<ExternalLink> page1 =
			externalLinkResource.
				getProductPurchaseProductPurchaseKeyExternalLinksPage(
					productPurchaseKey, Pagination.of(1, 2));

		List<ExternalLink> externalLinks1 =
			(List<ExternalLink>)page1.getItems();

		Assert.assertEquals(
			externalLinks1.toString(), 2, externalLinks1.size());

		Page<ExternalLink> page2 =
			externalLinkResource.
				getProductPurchaseProductPurchaseKeyExternalLinksPage(
					productPurchaseKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ExternalLink> externalLinks2 =
			(List<ExternalLink>)page2.getItems();

		Assert.assertEquals(
			externalLinks2.toString(), 1, externalLinks2.size());

		Page<ExternalLink> page3 =
			externalLinkResource.
				getProductPurchaseProductPurchaseKeyExternalLinksPage(
					productPurchaseKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2, externalLink3),
			(List<ExternalLink>)page3.getItems());
	}

	protected ExternalLink
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_addExternalLink(
				String productPurchaseKey, ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_getProductPurchaseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductPurchaseProductPurchaseKeyExternalLinksPage_getIrrelevantProductPurchaseKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductPurchaseProductPurchaseKeyExternalLink()
		throws Exception {

		ExternalLink randomExternalLink = randomExternalLink();

		ExternalLink postExternalLink =
			testPostProductPurchaseProductPurchaseKeyExternalLink_addExternalLink(
				randomExternalLink);

		assertEquals(randomExternalLink, postExternalLink);
		assertValid(postExternalLink);
	}

	protected ExternalLink
			testPostProductPurchaseProductPurchaseKeyExternalLink_addExternalLink(
				ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetProductProductKeyExternalLinksPage() throws Exception {
		String productKey =
			testGetProductProductKeyExternalLinksPage_getProductKey();
		String irrelevantProductKey =
			testGetProductProductKeyExternalLinksPage_getIrrelevantProductKey();

		Page<ExternalLink> page =
			externalLinkResource.getProductProductKeyExternalLinksPage(
				productKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantProductKey != null) {
			ExternalLink irrelevantExternalLink =
				testGetProductProductKeyExternalLinksPage_addExternalLink(
					irrelevantProductKey, randomIrrelevantExternalLink());

			page = externalLinkResource.getProductProductKeyExternalLinksPage(
				irrelevantProductKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantExternalLink),
				(List<ExternalLink>)page.getItems());
			assertValid(page);
		}

		ExternalLink externalLink1 =
			testGetProductProductKeyExternalLinksPage_addExternalLink(
				productKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetProductProductKeyExternalLinksPage_addExternalLink(
				productKey, randomExternalLink());

		page = externalLinkResource.getProductProductKeyExternalLinksPage(
			productKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2),
			(List<ExternalLink>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductProductKeyExternalLinksPageWithPagination()
		throws Exception {

		String productKey =
			testGetProductProductKeyExternalLinksPage_getProductKey();

		ExternalLink externalLink1 =
			testGetProductProductKeyExternalLinksPage_addExternalLink(
				productKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetProductProductKeyExternalLinksPage_addExternalLink(
				productKey, randomExternalLink());

		ExternalLink externalLink3 =
			testGetProductProductKeyExternalLinksPage_addExternalLink(
				productKey, randomExternalLink());

		Page<ExternalLink> page1 =
			externalLinkResource.getProductProductKeyExternalLinksPage(
				productKey, Pagination.of(1, 2));

		List<ExternalLink> externalLinks1 =
			(List<ExternalLink>)page1.getItems();

		Assert.assertEquals(
			externalLinks1.toString(), 2, externalLinks1.size());

		Page<ExternalLink> page2 =
			externalLinkResource.getProductProductKeyExternalLinksPage(
				productKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ExternalLink> externalLinks2 =
			(List<ExternalLink>)page2.getItems();

		Assert.assertEquals(
			externalLinks2.toString(), 1, externalLinks2.size());

		Page<ExternalLink> page3 =
			externalLinkResource.getProductProductKeyExternalLinksPage(
				productKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2, externalLink3),
			(List<ExternalLink>)page3.getItems());
	}

	protected ExternalLink
			testGetProductProductKeyExternalLinksPage_addExternalLink(
				String productKey, ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetProductProductKeyExternalLinksPage_getProductKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductProductKeyExternalLinksPage_getIrrelevantProductKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductProductKeyExternalLink() throws Exception {
		ExternalLink randomExternalLink = randomExternalLink();

		ExternalLink postExternalLink =
			testPostProductProductKeyExternalLink_addExternalLink(
				randomExternalLink);

		assertEquals(randomExternalLink, postExternalLink);
		assertValid(postExternalLink);
	}

	protected ExternalLink
			testPostProductProductKeyExternalLink_addExternalLink(
				ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetTeamTeamKeyExternalLinksPage() throws Exception {
		String teamKey = testGetTeamTeamKeyExternalLinksPage_getTeamKey();
		String irrelevantTeamKey =
			testGetTeamTeamKeyExternalLinksPage_getIrrelevantTeamKey();

		Page<ExternalLink> page =
			externalLinkResource.getTeamTeamKeyExternalLinksPage(
				teamKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantTeamKey != null) {
			ExternalLink irrelevantExternalLink =
				testGetTeamTeamKeyExternalLinksPage_addExternalLink(
					irrelevantTeamKey, randomIrrelevantExternalLink());

			page = externalLinkResource.getTeamTeamKeyExternalLinksPage(
				irrelevantTeamKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantExternalLink),
				(List<ExternalLink>)page.getItems());
			assertValid(page);
		}

		ExternalLink externalLink1 =
			testGetTeamTeamKeyExternalLinksPage_addExternalLink(
				teamKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetTeamTeamKeyExternalLinksPage_addExternalLink(
				teamKey, randomExternalLink());

		page = externalLinkResource.getTeamTeamKeyExternalLinksPage(
			teamKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2),
			(List<ExternalLink>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetTeamTeamKeyExternalLinksPageWithPagination()
		throws Exception {

		String teamKey = testGetTeamTeamKeyExternalLinksPage_getTeamKey();

		ExternalLink externalLink1 =
			testGetTeamTeamKeyExternalLinksPage_addExternalLink(
				teamKey, randomExternalLink());

		ExternalLink externalLink2 =
			testGetTeamTeamKeyExternalLinksPage_addExternalLink(
				teamKey, randomExternalLink());

		ExternalLink externalLink3 =
			testGetTeamTeamKeyExternalLinksPage_addExternalLink(
				teamKey, randomExternalLink());

		Page<ExternalLink> page1 =
			externalLinkResource.getTeamTeamKeyExternalLinksPage(
				teamKey, Pagination.of(1, 2));

		List<ExternalLink> externalLinks1 =
			(List<ExternalLink>)page1.getItems();

		Assert.assertEquals(
			externalLinks1.toString(), 2, externalLinks1.size());

		Page<ExternalLink> page2 =
			externalLinkResource.getTeamTeamKeyExternalLinksPage(
				teamKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ExternalLink> externalLinks2 =
			(List<ExternalLink>)page2.getItems();

		Assert.assertEquals(
			externalLinks2.toString(), 1, externalLinks2.size());

		Page<ExternalLink> page3 =
			externalLinkResource.getTeamTeamKeyExternalLinksPage(
				teamKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(externalLink1, externalLink2, externalLink3),
			(List<ExternalLink>)page3.getItems());
	}

	protected ExternalLink testGetTeamTeamKeyExternalLinksPage_addExternalLink(
			String teamKey, ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetTeamTeamKeyExternalLinksPage_getTeamKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetTeamTeamKeyExternalLinksPage_getIrrelevantTeamKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostTeamTeamKeyExternalLink() throws Exception {
		ExternalLink randomExternalLink = randomExternalLink();

		ExternalLink postExternalLink =
			testPostTeamTeamKeyExternalLink_addExternalLink(randomExternalLink);

		assertEquals(randomExternalLink, postExternalLink);
		assertValid(postExternalLink);
	}

	protected ExternalLink testPostTeamTeamKeyExternalLink_addExternalLink(
			ExternalLink externalLink)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		ExternalLink externalLink, List<ExternalLink> externalLinks) {

		boolean contains = false;

		for (ExternalLink item : externalLinks) {
			if (equals(externalLink, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			externalLinks + " does not contain " + externalLink, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ExternalLink externalLink1, ExternalLink externalLink2) {

		Assert.assertTrue(
			externalLink1 + " does not equal " + externalLink2,
			equals(externalLink1, externalLink2));
	}

	protected void assertEquals(
		List<ExternalLink> externalLinks1, List<ExternalLink> externalLinks2) {

		Assert.assertEquals(externalLinks1.size(), externalLinks2.size());

		for (int i = 0; i < externalLinks1.size(); i++) {
			ExternalLink externalLink1 = externalLinks1.get(i);
			ExternalLink externalLink2 = externalLinks2.get(i);

			assertEquals(externalLink1, externalLink2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ExternalLink> externalLinks1, List<ExternalLink> externalLinks2) {

		Assert.assertEquals(externalLinks1.size(), externalLinks2.size());

		for (ExternalLink externalLink1 : externalLinks1) {
			boolean contains = false;

			for (ExternalLink externalLink2 : externalLinks2) {
				if (equals(externalLink1, externalLink2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				externalLinks2 + " does not contain " + externalLink1,
				contains);
		}
	}

	protected void assertValid(ExternalLink externalLink) throws Exception {
		boolean valid = true;

		if (externalLink.getDateCreated() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("domain", additionalAssertFieldName)) {
				if (externalLink.getDomain() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("entityId", additionalAssertFieldName)) {
				if (externalLink.getEntityId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("entityName", additionalAssertFieldName)) {
				if (externalLink.getEntityName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (externalLink.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("url", additionalAssertFieldName)) {
				if (externalLink.getUrl() == null) {
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

	protected void assertValid(Page<ExternalLink> page) {
		boolean valid = false;

		java.util.Collection<ExternalLink> externalLinks = page.getItems();

		int size = externalLinks.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink.
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

	protected boolean equals(
		ExternalLink externalLink1, ExternalLink externalLink2) {

		if (externalLink1 == externalLink2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						externalLink1.getDateCreated(),
						externalLink2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("domain", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						externalLink1.getDomain(), externalLink2.getDomain())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("entityId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						externalLink1.getEntityId(),
						externalLink2.getEntityId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("entityName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						externalLink1.getEntityName(),
						externalLink2.getEntityName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						externalLink1.getKey(), externalLink2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("url", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						externalLink1.getUrl(), externalLink2.getUrl())) {

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

		if (!(_externalLinkResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_externalLinkResource;

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
		EntityField entityField, String operator, ExternalLink externalLink) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							externalLink.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							externalLink.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(externalLink.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("domain")) {
			sb.append("'");
			sb.append(String.valueOf(externalLink.getDomain()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("entityId")) {
			sb.append("'");
			sb.append(String.valueOf(externalLink.getEntityId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("entityName")) {
			sb.append("'");
			sb.append(String.valueOf(externalLink.getEntityName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(externalLink.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("url")) {
			sb.append("'");
			sb.append(String.valueOf(externalLink.getUrl()));
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

	protected ExternalLink randomExternalLink() throws Exception {
		return new ExternalLink() {
			{
				dateCreated = RandomTestUtil.nextDate();
				domain = StringUtil.toLowerCase(RandomTestUtil.randomString());
				entityId = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				entityName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				url = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected ExternalLink randomIrrelevantExternalLink() throws Exception {
		ExternalLink randomIrrelevantExternalLink = randomExternalLink();

		return randomIrrelevantExternalLink;
	}

	protected ExternalLink randomPatchExternalLink() throws Exception {
		return randomExternalLink();
	}

	protected ExternalLinkResource externalLinkResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = _getSuperClass(source.getClass());

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					sourceClass.getDeclaredFields()) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				Method setMethod = _getMethod(
					targetClass, field.getName(), "set",
					getMethod.getReturnType());

				setMethod.invoke(target, getMethod.invoke(source));
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Class<?> _getSuperClass(Class<?> clazz) {
			Class<?> superClass = clazz.getSuperclass();

			if ((superClass == null) || (superClass == Object.class)) {
				return clazz;
			}

			return superClass;
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

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
		LogFactoryUtil.getLog(BaseExternalLinkResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource
			_externalLinkResource;

}