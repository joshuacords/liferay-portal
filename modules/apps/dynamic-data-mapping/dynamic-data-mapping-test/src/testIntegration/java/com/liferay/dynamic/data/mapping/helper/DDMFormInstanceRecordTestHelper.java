/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.helper;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;

/**
 * @author Lino Alves
 */
public class DDMFormInstanceRecordTestHelper {

	public DDMFormInstanceRecordTestHelper(
		Group group, DDMFormInstance ddmFormInstance) {

		_group = group;
		_ddmFormInstance = ddmFormInstance;
	}

	public DDMFormInstanceRecord addDDMFormInstanceRecord() throws Exception {
		DDMForm ddmForm = getDDMForm();

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmForm,
			DDMFormValuesTestUtil.createAvailableLocales(LocaleUtil.US),
			LocaleUtil.US);

		for (DDMFormField ddmFormField : ddmForm.getDDMFormFields()) {
			if (ddmFormField.isLocalizable()) {
				ddmFormValues.addDDMFormFieldValue(
					DDMFormValuesTestUtil.createLocalizedDDMFormFieldValue(
						ddmFormField.getName(), RandomTestUtil.randomString()));
			}
			else {
				ddmFormValues.addDDMFormFieldValue(
					DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
						ddmFormField.getName(), RandomTestUtil.randomString()));
			}
		}

		return addDDMFormInstanceRecord(ddmFormValues);
	}

	public DDMFormInstanceRecord addDDMFormInstanceRecord(
			DDMFormValues ddmFormValues)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		return DDMFormInstanceRecordLocalServiceUtil.addFormInstanceRecord(
			TestPropsValues.getUserId(), _group.getGroupId(),
			_ddmFormInstance.getFormInstanceId(), ddmFormValues,
			serviceContext);
	}

	public DDMFormValues createEmptyDDMFormValues() throws PortalException {
		return DDMFormValuesTestUtil.createDDMFormValues(getDDMForm());
	}

	public DDMFormInstance getDDMFormInstance() {
		return _ddmFormInstance;
	}

	protected DDMForm getDDMForm() throws PortalException {
		DDMStructure ddmStructure = _ddmFormInstance.getStructure();

		return ddmStructure.getDDMForm();
	}

	private final DDMFormInstance _ddmFormInstance;
	private final Group _group;

}