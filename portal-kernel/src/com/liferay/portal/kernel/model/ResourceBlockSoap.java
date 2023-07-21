/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.ResourceBlockServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ResourceBlockSoap implements Serializable {

	public static ResourceBlockSoap toSoapModel(ResourceBlock model) {
		ResourceBlockSoap soapModel = new ResourceBlockSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setResourceBlockId(model.getResourceBlockId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setName(model.getName());
		soapModel.setPermissionsHash(model.getPermissionsHash());
		soapModel.setReferenceCount(model.getReferenceCount());

		return soapModel;
	}

	public static ResourceBlockSoap[] toSoapModels(ResourceBlock[] models) {
		ResourceBlockSoap[] soapModels = new ResourceBlockSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ResourceBlockSoap[][] toSoapModels(ResourceBlock[][] models) {
		ResourceBlockSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ResourceBlockSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ResourceBlockSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ResourceBlockSoap[] toSoapModels(List<ResourceBlock> models) {
		List<ResourceBlockSoap> soapModels = new ArrayList<ResourceBlockSoap>(
			models.size());

		for (ResourceBlock model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ResourceBlockSoap[soapModels.size()]);
	}

	public ResourceBlockSoap() {
	}

	public long getPrimaryKey() {
		return _resourceBlockId;
	}

	public void setPrimaryKey(long pk) {
		setResourceBlockId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	public void setResourceBlockId(long resourceBlockId) {
		_resourceBlockId = resourceBlockId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPermissionsHash() {
		return _permissionsHash;
	}

	public void setPermissionsHash(String permissionsHash) {
		_permissionsHash = permissionsHash;
	}

	public long getReferenceCount() {
		return _referenceCount;
	}

	public void setReferenceCount(long referenceCount) {
		_referenceCount = referenceCount;
	}

	private long _mvccVersion;
	private long _resourceBlockId;
	private long _companyId;
	private long _groupId;
	private String _name;
	private String _permissionsHash;
	private long _referenceCount;

}