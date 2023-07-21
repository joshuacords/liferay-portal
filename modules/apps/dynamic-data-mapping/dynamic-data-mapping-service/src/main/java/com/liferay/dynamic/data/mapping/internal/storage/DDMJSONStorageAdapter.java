/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.storage;

import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapter;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveResponse;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true, property = "ddm.storage.adapter.type=json",
	service = DDMStorageAdapter.class
)
public class DDMJSONStorageAdapter implements DDMStorageAdapter {

	@Override
	public DDMStorageAdapterDeleteResponse delete(
			DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
		throws StorageException {

		try {
			DDMContent ddmContent = ddmContentLocalService.fetchDDMContent(
				ddmStorageAdapterDeleteRequest.getPrimaryKey());

			if (ddmContent != null) {
				ddmContentLocalService.deleteDDMContent(
					ddmContent.getPrimaryKey());
			}

			DDMStorageAdapterDeleteResponse.Builder builder =
				DDMStorageAdapterDeleteResponse.Builder.newBuilder();

			return builder.build();
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public DDMStorageAdapterGetResponse get(
			DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
		throws StorageException {

		try {
			DDMContent ddmContent = ddmContentLocalService.getContent(
				ddmStorageAdapterGetRequest.getPrimaryKey());

			DDMFormValues ddmFormValues = deserialize(
				ddmContent.getData(), ddmStorageAdapterGetRequest.getDDMForm());

			DDMStorageAdapterGetResponse.Builder builder =
				DDMStorageAdapterGetResponse.Builder.newBuilder(ddmFormValues);

			return builder.build();
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public DDMStorageAdapterSaveResponse save(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		if (ddmStorageAdapterSaveRequest.isInsert()) {
			return insert(ddmStorageAdapterSaveRequest);
		}

		return update(ddmStorageAdapterSaveRequest);
	}

	protected DDMFormValues deserialize(String content, DDMForm ddmForm) {
		DDMFormValuesDeserializerDeserializeRequest.Builder builder =
			DDMFormValuesDeserializerDeserializeRequest.Builder.newBuilder(
				content, ddmForm);

		DDMFormValuesDeserializerDeserializeResponse
			ddmFormValuesDeserializerDeserializeResponse =
				jsonDDMFormValuesDeserializer.deserialize(builder.build());

		return ddmFormValuesDeserializerDeserializeResponse.getDDMFormValues();
	}

	protected DDMStorageAdapterSaveResponse insert(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		DDMFormValues ddmFormValues =
			ddmStorageAdapterSaveRequest.getDDMFormValues();

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(
				ddmStorageAdapterSaveRequest.getScopeGroupId());
			serviceContext.setUserId(ddmStorageAdapterSaveRequest.getUserId());
			serviceContext.setUuid(ddmStorageAdapterSaveRequest.getUuid());

			DDMContent ddmContent = ddmContentLocalService.addContent(
				ddmStorageAdapterSaveRequest.getUserId(),
				ddmStorageAdapterSaveRequest.getScopeGroupId(),
				ddmStorageAdapterSaveRequest.getClassName(), null,
				serialize(ddmFormValues), serviceContext);

			DDMStorageAdapterSaveResponse.Builder builder =
				DDMStorageAdapterSaveResponse.Builder.newBuilder(
					ddmContent.getPrimaryKey());

			return builder.build();
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	protected String serialize(DDMFormValues ddmFormValues) {
		DDMFormValuesSerializerSerializeRequest.Builder builder =
			DDMFormValuesSerializerSerializeRequest.Builder.newBuilder(
				ddmFormValues);

		DDMFormValuesSerializerSerializeResponse
			ddmFormValuesSerializerSerializeResponse =
				jsonDDMFormValuesSerializer.serialize(builder.build());

		return ddmFormValuesSerializerSerializeResponse.getContent();
	}

	protected DDMStorageAdapterSaveResponse update(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		try {
			DDMContent ddmContent = ddmContentLocalService.getContent(
				ddmStorageAdapterSaveRequest.getPrimaryKey());

			ddmContent.setModifiedDate(new Date());

			ddmContent.setData(
				serialize(ddmStorageAdapterSaveRequest.getDDMFormValues()));

			ddmContentLocalService.updateContent(
				ddmContent.getPrimaryKey(), ddmContent.getName(),
				ddmContent.getDescription(), ddmContent.getData(),
				new ServiceContext());

			DDMStorageAdapterSaveResponse.Builder builder =
				DDMStorageAdapterSaveResponse.Builder.newBuilder(
					ddmContent.getPrimaryKey());

			return builder.build();
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Reference
	protected DDMContentLocalService ddmContentLocalService;

	@Reference(target = "(ddm.form.values.deserializer.type=json)")
	protected DDMFormValuesDeserializer jsonDDMFormValuesDeserializer;

	@Reference(target = "(ddm.form.values.serializer.type=json)")
	protected DDMFormValuesSerializer jsonDDMFormValuesSerializer;

}