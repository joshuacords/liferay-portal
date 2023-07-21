/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model.impl;

import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ServiceProducer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ServiceProducerCacheModel
	implements CacheModel<ServiceProducer>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ServiceProducerCacheModel)) {
			return false;
		}

		ServiceProducerCacheModel serviceProducerCacheModel =
			(ServiceProducerCacheModel)object;

		if ((serviceProducerId ==
				serviceProducerCacheModel.serviceProducerId) &&
			(mvccVersion == serviceProducerCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, serviceProducerId);

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
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", serviceProducerId=");
		sb.append(serviceProducerId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", authorizationUserId=");
		sb.append(authorizationUserId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceProducer toEntityModel() {
		ServiceProducerImpl serviceProducerImpl = new ServiceProducerImpl();

		serviceProducerImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			serviceProducerImpl.setUuid("");
		}
		else {
			serviceProducerImpl.setUuid(uuid);
		}

		serviceProducerImpl.setServiceProducerId(serviceProducerId);
		serviceProducerImpl.setCompanyId(companyId);
		serviceProducerImpl.setUserId(userId);
		serviceProducerImpl.setAuthorizationUserId(authorizationUserId);

		if (name == null) {
			serviceProducerImpl.setName("");
		}
		else {
			serviceProducerImpl.setName(name);
		}

		if (description == null) {
			serviceProducerImpl.setDescription("");
		}
		else {
			serviceProducerImpl.setDescription(description);
		}

		serviceProducerImpl.resetOriginalValues();

		return serviceProducerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		serviceProducerId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		authorizationUserId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(serviceProducerId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(authorizationUserId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long serviceProducerId;
	public long companyId;
	public long userId;
	public long authorizationUserId;
	public String name;
	public String description;

}