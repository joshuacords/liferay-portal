/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.exception.DuplicateCommerceAccountGroupRelException;
import com.liferay.commerce.account.model.CommerceAccountGroupRel;
import com.liferay.commerce.account.service.base.CommerceAccountGroupRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupRelLocalServiceImpl
	extends CommerceAccountGroupRelLocalServiceBaseImpl {

	@Override
	public CommerceAccountGroupRel addCommerceAccountGroupRel(
			String className, long classPK, long commerceAccountGroupId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		long classNameId = classNameLocalService.getClassNameId(className);

		if (commerceAccountGroupRelPersistence.fetchByC_C_C(
				classNameId, classPK, commerceAccountGroupId) != null) {

			throw new DuplicateCommerceAccountGroupRelException();
		}

		long commerceAccountGroupRelId = counterLocalService.increment();

		CommerceAccountGroupRel commerceAccountGroupRel =
			commerceAccountGroupRelPersistence.create(
				commerceAccountGroupRelId);

		commerceAccountGroupRel.setCompanyId(user.getCompanyId());
		commerceAccountGroupRel.setUserId(user.getUserId());
		commerceAccountGroupRel.setUserName(user.getFullName());
		commerceAccountGroupRel.setClassNameId(classNameId);
		commerceAccountGroupRel.setClassPK(classPK);
		commerceAccountGroupRel.setCommerceAccountGroupId(
			commerceAccountGroupId);

		return commerceAccountGroupRelPersistence.update(
			commerceAccountGroupRel);
	}

	@Override
	public void deleteCommerceAccountGroupRels(long commerceAccountGroupId) {
		commerceAccountGroupRelPersistence.removeByCommerceAccountGroupId(
			commerceAccountGroupId);
	}

	@Override
	public void deleteCommerceAccountGroupRels(String className, long classPK) {
		commerceAccountGroupRelPersistence.removeByC_C(
			classNameLocalService.getClassNameId(className), classPK);
	}

	@Override
	public List<CommerceAccountGroupRel> getCommerceAccountGroupRels(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CommerceAccountGroupRel> orderByComparator) {

		return commerceAccountGroupRelPersistence.findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceAccountGroupRel> getCommerceAccountGroupRels(
		String className, long classPK, int start, int end,
		OrderByComparator<CommerceAccountGroupRel> orderByComparator) {

		return commerceAccountGroupRelPersistence.findByC_C(
			classNameLocalService.getClassNameId(className), classPK, start,
			end, orderByComparator);
	}

	@Override
	public int getCommerceAccountGroupRelsCount(long commerceAccountGroupId) {
		return commerceAccountGroupRelPersistence.countByCommerceAccountGroupId(
			commerceAccountGroupId);
	}

	@Override
	public int getCommerceAccountGroupRelsCount(
		String className, long classPK) {

		return commerceAccountGroupRelPersistence.countByC_C(
			classNameLocalService.getClassNameId(className), classPK);
	}

}