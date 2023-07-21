/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.util.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.Dimensions;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.commerce.util.CommerceShippingHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class CommerceShippingHelperPricingV2Test {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_company = CommerceTestUtil.addCompany();

		_user = UserTestUtil.addUser(_company);

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_user.getCompanyId());

		_commerceChannel = CommerceTestUtil.addCommerceChannel(
			_company.getGroupId(), _commerceCurrency.getCode());

		_commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			_commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		_commerceOrders = new ArrayList<>();
	}

	@After
	public void tearDown() throws PortalException {
		for (CommerceOrder commerceOrder : _commerceOrders) {
			_commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
		}
	}

	@Test
	public void testGetDimensions() throws Exception {
		frutillaRule.scenario(
			"Verify that the product dimensions are correctly retrieved from " +
				"the order"
		).given(
			"I add some product instances with some dimensions"
		).when(
			"The products are available on a channel"
		).then(
			"The dimensions are correctly retrieved from the order"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		CPInstance cpInstance1 = CPTestUtil.addCPInstanceWithSku();
		CPInstance cpInstance2 = CPTestUtil.addCPInstanceWithSku();
		CPInstance cpInstance3 = CPTestUtil.addCPInstanceWithSku();

		CPTestUtil.addBasePriceEntry(cpInstance1);
		CPTestUtil.addBasePriceEntry(cpInstance2);
		CPTestUtil.addBasePriceEntry(cpInstance3);

		_addCPDefinitionProperties(cpInstance1);
		_addCPDefinitionProperties(cpInstance2);
		_addCPDefinitionProperties(cpInstance3);

		_addAvailability(cpInstance1);
		_addAvailability(cpInstance2);
		_addAvailability(cpInstance3);

		double dimension = 10.5;

		_addDimensions(cpInstance1, dimension);
		_addDimensions(cpInstance2, dimension);
		_addDimensions(cpInstance3, dimension);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance1.getCPInstanceId(),
			1);
		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance2.getCPInstanceId(),
			1);
		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance3.getCPInstanceId(),
			1);

		Dimensions actualDimensions = _commerceShippingHelper.getDimensions(
			commerceOrder.getCommerceOrderItems());

		double volume = dimension * dimension * dimension * 3;

		double dim = Math.cbrt(volume);

		Dimensions expectedDimensions = new Dimensions(dim, dim, dim);

		Assert.assertEquals(
			expectedDimensions.getDepth(), actualDimensions.getDepth(),
			0.00001);
		Assert.assertEquals(
			expectedDimensions.getHeight(), actualDimensions.getHeight(),
			0.00001);
		Assert.assertEquals(
			expectedDimensions.getWidth(), actualDimensions.getWidth(),
			0.00001);
	}

	@Test
	public void testGetWeight() throws Exception {
		frutillaRule.scenario(
			"Verify that the product weights are correctly retrieved from " +
				"the order"
		).given(
			"I add some product instances with some weights"
		).when(
			"The products are available on a channel"
		).then(
			"The weights are correctly retrieved from the order"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		CPInstance cpInstance1 = CPTestUtil.addCPInstanceWithSku();
		CPInstance cpInstance2 = CPTestUtil.addCPInstanceWithSku();
		CPInstance cpInstance3 = CPTestUtil.addCPInstanceWithSku();

		CPTestUtil.addBasePriceEntry(cpInstance1);
		CPTestUtil.addBasePriceEntry(cpInstance2);
		CPTestUtil.addBasePriceEntry(cpInstance3);

		_addCPDefinitionProperties(cpInstance1);
		_addCPDefinitionProperties(cpInstance2);
		_addCPDefinitionProperties(cpInstance3);

		_addAvailability(cpInstance1);
		_addAvailability(cpInstance2);
		_addAvailability(cpInstance3);

		_addWeight(cpInstance1);
		_addWeight(cpInstance2);
		_addWeight(cpInstance3);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance1.getCPInstanceId(),
			1);
		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance2.getCPInstanceId(),
			1);
		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance3.getCPInstanceId(),
			1);

		double actualWeight = _commerceShippingHelper.getWeight(
			commerceOrder.getCommerceOrderItems());

		double expectedWeight =
			cpInstance1.getWeight() + cpInstance2.getWeight() +
				cpInstance3.getWeight();

		Assert.assertEquals(expectedWeight, actualWeight, 0.0001);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private static void _addAvailability(CPInstance cpInstance)
		throws Exception {

		BigDecimal price = BigDecimal.valueOf(RandomTestUtil.randomDouble());

		cpInstance.setPrice(price);

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			cpInstance.getUserId(), _commerceInventoryWarehouse,
			cpInstance.getSku(), 10);
	}

	private static void _addCPDefinitionProperties(CPInstance cpInstance)
		throws PortalException {

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		cpDefinition.setShippable(true);
		cpDefinition.setFreeShipping(false);

		_cpDefinitionLocalService.updateCPDefinition(cpDefinition);
	}

	private static void _addDimensions(
		CPInstance cpInstance, double dimension) {

		cpInstance.setWidth(dimension);
		cpInstance.setHeight(dimension);
		cpInstance.setDepth(dimension);

		_cpInstanceLocalService.updateCPInstance(cpInstance);
	}

	private static void _addWeight(CPInstance cpInstance) {
		cpInstance.setWeight(RandomTestUtil.randomDouble());

		_cpInstanceLocalService.updateCPInstance(cpInstance);
	}

	private static CommerceInventoryWarehouse _commerceInventoryWarehouse;

	@Inject
	private static CPDefinitionLocalService _cpDefinitionLocalService;

	@Inject
	private static CPInstanceLocalService _cpInstanceLocalService;

	private CommerceChannel _commerceChannel;
	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	private List<CommerceOrder> _commerceOrders;

	@Inject
	private CommerceShippingHelper _commerceShippingHelper;

	@DeleteAfterTestRun
	private Company _company;

	private User _user;

}