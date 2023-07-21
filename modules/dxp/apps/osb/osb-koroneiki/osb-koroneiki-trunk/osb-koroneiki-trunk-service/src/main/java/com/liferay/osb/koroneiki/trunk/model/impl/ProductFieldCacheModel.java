/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProductField in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductFieldCacheModel
	implements CacheModel<ProductField>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductFieldCacheModel)) {
			return false;
		}

		ProductFieldCacheModel productFieldCacheModel =
			(ProductFieldCacheModel)object;

		if ((productFieldId == productFieldCacheModel.productFieldId) &&
			(mvccVersion == productFieldCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, productFieldId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", productFieldId=");
		sb.append(productFieldId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductField toEntityModel() {
		ProductFieldImpl productFieldImpl = new ProductFieldImpl();

		productFieldImpl.setMvccVersion(mvccVersion);
		productFieldImpl.setProductFieldId(productFieldId);
		productFieldImpl.setCompanyId(companyId);
		productFieldImpl.setUserId(userId);
		productFieldImpl.setClassNameId(classNameId);
		productFieldImpl.setClassPK(classPK);

		if (name == null) {
			productFieldImpl.setName("");
		}
		else {
			productFieldImpl.setName(name);
		}

		if (value == null) {
			productFieldImpl.setValue("");
		}
		else {
			productFieldImpl.setValue(value);
		}

		productFieldImpl.resetOriginalValues();

		return productFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		productFieldId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		name = objectInput.readUTF();
		value = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(productFieldId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(value);
		}
	}

	public long mvccVersion;
	public long productFieldId;
	public long companyId;
	public long userId;
	public long classNameId;
	public long classPK;
	public String name;
	public String value;

}