/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.model.impl;

import com.liferay.data.engine.model.DEDataDefinitionFieldLink;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DEDataDefinitionFieldLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DEDataDefinitionFieldLinkCacheModel
	implements CacheModel<DEDataDefinitionFieldLink>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DEDataDefinitionFieldLinkCacheModel)) {
			return false;
		}

		DEDataDefinitionFieldLinkCacheModel
			deDataDefinitionFieldLinkCacheModel =
				(DEDataDefinitionFieldLinkCacheModel)object;

		if (deDataDefinitionFieldLinkId ==
				deDataDefinitionFieldLinkCacheModel.
					deDataDefinitionFieldLinkId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deDataDefinitionFieldLinkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deDataDefinitionFieldLinkId=");
		sb.append(deDataDefinitionFieldLinkId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", ddmStructureId=");
		sb.append(ddmStructureId);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DEDataDefinitionFieldLink toEntityModel() {
		DEDataDefinitionFieldLinkImpl deDataDefinitionFieldLinkImpl =
			new DEDataDefinitionFieldLinkImpl();

		if (uuid == null) {
			deDataDefinitionFieldLinkImpl.setUuid("");
		}
		else {
			deDataDefinitionFieldLinkImpl.setUuid(uuid);
		}

		deDataDefinitionFieldLinkImpl.setDeDataDefinitionFieldLinkId(
			deDataDefinitionFieldLinkId);
		deDataDefinitionFieldLinkImpl.setGroupId(groupId);
		deDataDefinitionFieldLinkImpl.setClassNameId(classNameId);
		deDataDefinitionFieldLinkImpl.setClassPK(classPK);
		deDataDefinitionFieldLinkImpl.setDdmStructureId(ddmStructureId);

		if (fieldName == null) {
			deDataDefinitionFieldLinkImpl.setFieldName("");
		}
		else {
			deDataDefinitionFieldLinkImpl.setFieldName(fieldName);
		}

		deDataDefinitionFieldLinkImpl.resetOriginalValues();

		return deDataDefinitionFieldLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deDataDefinitionFieldLinkId = objectInput.readLong();

		groupId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		ddmStructureId = objectInput.readLong();
		fieldName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(deDataDefinitionFieldLinkId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(ddmStructureId);

		if (fieldName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fieldName);
		}
	}

	public String uuid;
	public long deDataDefinitionFieldLinkId;
	public long groupId;
	public long classNameId;
	public long classPK;
	public long ddmStructureId;
	public String fieldName;

}