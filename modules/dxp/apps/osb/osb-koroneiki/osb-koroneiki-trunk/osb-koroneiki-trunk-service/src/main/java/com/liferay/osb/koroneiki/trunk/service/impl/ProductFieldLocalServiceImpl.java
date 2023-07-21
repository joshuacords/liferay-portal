/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.base.ProductFieldLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.trunk.model.ProductField",
	service = AopService.class
)
public class ProductFieldLocalServiceImpl
	extends ProductFieldLocalServiceBaseImpl {

	public ProductField addProductField(
			long userId, long classNameId, long classPK, String name,
			String value)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long productFieldId = counterLocalService.increment();

		ProductField productField = productFieldPersistence.create(
			productFieldId);

		productField.setCompanyId(user.getCompanyId());
		productField.setUserId(userId);
		productField.setClassNameId(classNameId);
		productField.setClassPK(classPK);
		productField.setName(name);
		productField.setValue(value);

		return productFieldPersistence.update(productField);
	}

	public ProductField addProductField(
			long userId, String className, long classPK, String name,
			String value)
		throws PortalException {

		return addProductField(
			userId, classNameLocalService.getClassNameId(className), classPK,
			name, value);
	}

	public List<String> getProductFieldNames(long classNameId) {
		return productFieldFinder.findNameByClassNameId(classNameId);
	}

	public List<ProductField> getProductFields(long classNameId, long classPK) {
		return productFieldPersistence.findByC_C(classNameId, classPK);
	}

	public List<ProductField> getProductFields(String className, long classPK) {
		return getProductFields(
			classNameLocalService.getClassNameId(className), classPK);
	}

	public ProductField updateProductField(long productFieldId, String value)
		throws PortalException {

		ProductField productField = productFieldPersistence.findByPrimaryKey(
			productFieldId);

		productField.setValue(value);

		return productFieldPersistence.update(productField);
	}

}