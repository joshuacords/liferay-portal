/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.model.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ExternalLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExternalLinkCacheModel
	implements CacheModel<ExternalLink>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ExternalLinkCacheModel)) {
			return false;
		}

		ExternalLinkCacheModel externalLinkCacheModel =
			(ExternalLinkCacheModel)object;

		if ((externalLinkId == externalLinkCacheModel.externalLinkId) &&
			(mvccVersion == externalLinkCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, externalLinkId);

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
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", externalLinkId=");
		sb.append(externalLinkId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", externalLinkKey=");
		sb.append(externalLinkKey);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", domain=");
		sb.append(domain);
		sb.append(", entityName=");
		sb.append(entityName);
		sb.append(", entityId=");
		sb.append(entityId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ExternalLink toEntityModel() {
		ExternalLinkImpl externalLinkImpl = new ExternalLinkImpl();

		externalLinkImpl.setMvccVersion(mvccVersion);
		externalLinkImpl.setExternalLinkId(externalLinkId);
		externalLinkImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			externalLinkImpl.setCreateDate(null);
		}
		else {
			externalLinkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			externalLinkImpl.setModifiedDate(null);
		}
		else {
			externalLinkImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (externalLinkKey == null) {
			externalLinkImpl.setExternalLinkKey("");
		}
		else {
			externalLinkImpl.setExternalLinkKey(externalLinkKey);
		}

		externalLinkImpl.setClassNameId(classNameId);
		externalLinkImpl.setClassPK(classPK);

		if (domain == null) {
			externalLinkImpl.setDomain("");
		}
		else {
			externalLinkImpl.setDomain(domain);
		}

		if (entityName == null) {
			externalLinkImpl.setEntityName("");
		}
		else {
			externalLinkImpl.setEntityName(entityName);
		}

		if (entityId == null) {
			externalLinkImpl.setEntityId("");
		}
		else {
			externalLinkImpl.setEntityId(entityId);
		}

		externalLinkImpl.resetOriginalValues();

		return externalLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		externalLinkId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		externalLinkKey = objectInput.readUTF();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		domain = objectInput.readUTF();
		entityName = objectInput.readUTF();
		entityId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(externalLinkId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (externalLinkKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalLinkKey);
		}

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (domain == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(domain);
		}

		if (entityName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(entityName);
		}

		if (entityId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(entityId);
		}
	}

	public long mvccVersion;
	public long externalLinkId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String externalLinkKey;
	public long classNameId;
	public long classPK;
	public String domain;
	public String entityName;
	public String entityId;

}