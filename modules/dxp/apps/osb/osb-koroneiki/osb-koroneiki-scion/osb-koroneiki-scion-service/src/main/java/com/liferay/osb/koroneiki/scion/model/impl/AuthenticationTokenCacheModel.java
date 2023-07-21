/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model.impl;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
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
 * The cache model class for representing AuthenticationToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuthenticationTokenCacheModel
	implements CacheModel<AuthenticationToken>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AuthenticationTokenCacheModel)) {
			return false;
		}

		AuthenticationTokenCacheModel authenticationTokenCacheModel =
			(AuthenticationTokenCacheModel)object;

		if ((authenticationTokenId ==
				authenticationTokenCacheModel.authenticationTokenId) &&
			(mvccVersion == authenticationTokenCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, authenticationTokenId);

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
		sb.append(", authenticationTokenId=");
		sb.append(authenticationTokenId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", serviceProducerId=");
		sb.append(serviceProducerId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", prefix=");
		sb.append(prefix);
		sb.append(", digest=");
		sb.append(digest);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuthenticationToken toEntityModel() {
		AuthenticationTokenImpl authenticationTokenImpl =
			new AuthenticationTokenImpl();

		authenticationTokenImpl.setMvccVersion(mvccVersion);
		authenticationTokenImpl.setAuthenticationTokenId(authenticationTokenId);
		authenticationTokenImpl.setCompanyId(companyId);
		authenticationTokenImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			authenticationTokenImpl.setCreateDate(null);
		}
		else {
			authenticationTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			authenticationTokenImpl.setModifiedDate(null);
		}
		else {
			authenticationTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		authenticationTokenImpl.setServiceProducerId(serviceProducerId);

		if (name == null) {
			authenticationTokenImpl.setName("");
		}
		else {
			authenticationTokenImpl.setName(name);
		}

		if (prefix == null) {
			authenticationTokenImpl.setPrefix("");
		}
		else {
			authenticationTokenImpl.setPrefix(prefix);
		}

		if (digest == null) {
			authenticationTokenImpl.setDigest("");
		}
		else {
			authenticationTokenImpl.setDigest(digest);
		}

		authenticationTokenImpl.setStatus(status);

		authenticationTokenImpl.resetOriginalValues();

		return authenticationTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		authenticationTokenId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		serviceProducerId = objectInput.readLong();
		name = objectInput.readUTF();
		prefix = objectInput.readUTF();
		digest = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(authenticationTokenId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(serviceProducerId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (prefix == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(prefix);
		}

		if (digest == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(digest);
		}

		objectOutput.writeInt(status);
	}

	public long mvccVersion;
	public long authenticationTokenId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long serviceProducerId;
	public String name;
	public String prefix;
	public String digest;
	public int status;

}