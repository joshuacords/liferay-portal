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

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.permission.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.trunk.permission.ProductPurchasePermission;
import com.liferay.osb.koroneiki.trunk.service.base.ProductFieldServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=ProductField"
	},
	service = AopService.class
)
public class ProductFieldServiceImpl extends ProductFieldServiceBaseImpl {

	public ProductField addProductField(
			long classNameId, long classPK, String name, String value)
		throws PortalException {

		long productConsumptionClassNameId =
			classNameLocalService.getClassNameId(ProductConsumption.class);

		if (classNameId == productConsumptionClassNameId) {
			_productConsumptionPermission.check(
				getPermissionChecker(), classPK, ActionKeys.UPDATE);
		}
		else {
			_productPurchasePermission.check(
				getPermissionChecker(), classPK, ActionKeys.UPDATE);
		}

		return productFieldLocalService.addProductField(
			getUserId(), classNameId, classPK, name, value);
	}

	public ProductField deleteProductField(long productFieldId)
		throws PortalException {

		ProductField productField = productFieldLocalService.getProductField(
			productFieldId);

		if (productField.getClassName() == ProductConsumption.class.getName()) {
			_productConsumptionPermission.check(
				getPermissionChecker(), productField.getClassPK(),
				ActionKeys.UPDATE);
		}
		else {
			_productPurchasePermission.check(
				getPermissionChecker(), productField.getClassPK(),
				ActionKeys.UPDATE);
		}

		return productFieldLocalService.deleteProductField(productFieldId);
	}

	public ProductField updateProductField(long productFieldId, String value)
		throws PortalException {

		ProductField productField = productFieldLocalService.getProductField(
			productFieldId);

		if (productField.getClassName() == ProductConsumption.class.getName()) {
			_productConsumptionPermission.check(
				getPermissionChecker(), productField.getClassPK(),
				ActionKeys.UPDATE);
		}
		else {
			_productPurchasePermission.check(
				getPermissionChecker(), productField.getClassPK(),
				ActionKeys.UPDATE);
		}

		return productFieldLocalService.updateProductField(
			productFieldId, value);
	}

	@Reference
	private ProductConsumptionPermission _productConsumptionPermission;

	@Reference
	private ProductPurchasePermission _productPurchasePermission;

}