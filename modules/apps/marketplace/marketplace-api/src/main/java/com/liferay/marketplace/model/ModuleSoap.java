/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ryan Park
 * @generated
 */
public class ModuleSoap implements Serializable {

	public static ModuleSoap toSoapModel(Module model) {
		ModuleSoap soapModel = new ModuleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setModuleId(model.getModuleId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setAppId(model.getAppId());
		soapModel.setBundleSymbolicName(model.getBundleSymbolicName());
		soapModel.setBundleVersion(model.getBundleVersion());
		soapModel.setContextName(model.getContextName());

		return soapModel;
	}

	public static ModuleSoap[] toSoapModels(Module[] models) {
		ModuleSoap[] soapModels = new ModuleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ModuleSoap[][] toSoapModels(Module[][] models) {
		ModuleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ModuleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ModuleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ModuleSoap[] toSoapModels(List<Module> models) {
		List<ModuleSoap> soapModels = new ArrayList<ModuleSoap>(models.size());

		for (Module model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ModuleSoap[soapModels.size()]);
	}

	public ModuleSoap() {
	}

	public long getPrimaryKey() {
		return _moduleId;
	}

	public void setPrimaryKey(long pk) {
		setModuleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getModuleId() {
		return _moduleId;
	}

	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getAppId() {
		return _appId;
	}

	public void setAppId(long appId) {
		_appId = appId;
	}

	public String getBundleSymbolicName() {
		return _bundleSymbolicName;
	}

	public void setBundleSymbolicName(String bundleSymbolicName) {
		_bundleSymbolicName = bundleSymbolicName;
	}

	public String getBundleVersion() {
		return _bundleVersion;
	}

	public void setBundleVersion(String bundleVersion) {
		_bundleVersion = bundleVersion;
	}

	public String getContextName() {
		return _contextName;
	}

	public void setContextName(String contextName) {
		_contextName = contextName;
	}

	private String _uuid;
	private long _moduleId;
	private long _companyId;
	private long _appId;
	private String _bundleSymbolicName;
	private String _bundleVersion;
	private String _contextName;

}