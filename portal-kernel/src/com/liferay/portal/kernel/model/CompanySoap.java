/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.CompanyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CompanySoap implements Serializable {

	public static CompanySoap toSoapModel(Company model) {
		CompanySoap soapModel = new CompanySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setWebId(model.getWebId());
		soapModel.setKey(model.getKey());
		soapModel.setMx(model.getMx());
		soapModel.setHomeURL(model.getHomeURL());
		soapModel.setLogoId(model.getLogoId());
		soapModel.setSystem(model.isSystem());
		soapModel.setMaxUsers(model.getMaxUsers());
		soapModel.setActive(model.isActive());

		return soapModel;
	}

	public static CompanySoap[] toSoapModels(Company[] models) {
		CompanySoap[] soapModels = new CompanySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanySoap[][] toSoapModels(Company[][] models) {
		CompanySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanySoap[] toSoapModels(List<Company> models) {
		List<CompanySoap> soapModels = new ArrayList<CompanySoap>(
			models.size());

		for (Company model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanySoap[soapModels.size()]);
	}

	public CompanySoap() {
	}

	public long getPrimaryKey() {
		return _companyId;
	}

	public void setPrimaryKey(long pk) {
		setCompanyId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public String getWebId() {
		return _webId;
	}

	public void setWebId(String webId) {
		_webId = webId;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getMx() {
		return _mx;
	}

	public void setMx(String mx) {
		_mx = mx;
	}

	public String getHomeURL() {
		return _homeURL;
	}

	public void setHomeURL(String homeURL) {
		_homeURL = homeURL;
	}

	public long getLogoId() {
		return _logoId;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	public boolean getSystem() {
		return _system;
	}

	public boolean isSystem() {
		return _system;
	}

	public void setSystem(boolean system) {
		_system = system;
	}

	public int getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(int maxUsers) {
		_maxUsers = maxUsers;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _mvccVersion;
	private long _companyId;
	private long _accountId;
	private String _webId;
	private String _key;
	private String _mx;
	private String _homeURL;
	private long _logoId;
	private boolean _system;
	private int _maxUsers;
	private boolean _active;

}