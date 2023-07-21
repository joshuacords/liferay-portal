/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.resource.v1_0.test.util;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marcela Cunha
 */
public class DataRecordCollectionTestUtil {

	public static DDLRecordSet addRecordSet(
			DDMStructure ddmStructure, Group group,
			ResourceLocalService resourceLocalService)
		throws Exception {

		return addRecordSet(
			ddmStructure, group, resourceLocalService,
			DDLRecordSetConstants.SCOPE_DATA_ENGINE);
	}

	public static DDLRecordSet addRecordSet(
			DDMStructure ddmStructure, Group group,
			ResourceLocalService resourceLocalService, int scope)
		throws Exception {

		Map<Locale, String> nameMap = new HashMap<Locale, String>() {
			{
				put(LocaleUtil.US, RandomTestUtil.randomString());
			}
		};

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil.addRecordSet(
			TestPropsValues.getUserId(), group.getGroupId(),
			ddmStructure.getStructureId(), ddmStructure.getStructureKey(),
			nameMap, null, DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT,
			scope, serviceContext);

		resourceLocalService.addModelResources(
			TestPropsValues.getCompanyId(), ddmStructure.getGroupId(),
			TestPropsValues.getUserId(), _RESOURCE_NAME,
			ddlRecordSet.getRecordSetId(),
			serviceContext.getModelPermissions());

		return ddlRecordSet;
	}

	private static final String _RESOURCE_NAME =
		"com.liferay.data.engine.rest.internal.model." +
			"InternalDataRecordCollection";

}