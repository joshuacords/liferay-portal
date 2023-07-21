/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.tag.stats.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetTagStatsSoap implements Serializable {

	public static AssetTagStatsSoap toSoapModel(AssetTagStats model) {
		AssetTagStatsSoap soapModel = new AssetTagStatsSoap();

		soapModel.setTagStatsId(model.getTagStatsId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setTagId(model.getTagId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setAssetCount(model.getAssetCount());

		return soapModel;
	}

	public static AssetTagStatsSoap[] toSoapModels(AssetTagStats[] models) {
		AssetTagStatsSoap[] soapModels = new AssetTagStatsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetTagStatsSoap[][] toSoapModels(AssetTagStats[][] models) {
		AssetTagStatsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetTagStatsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetTagStatsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetTagStatsSoap[] toSoapModels(List<AssetTagStats> models) {
		List<AssetTagStatsSoap> soapModels = new ArrayList<AssetTagStatsSoap>(
			models.size());

		for (AssetTagStats model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetTagStatsSoap[soapModels.size()]);
	}

	public AssetTagStatsSoap() {
	}

	public long getPrimaryKey() {
		return _tagStatsId;
	}

	public void setPrimaryKey(long pk) {
		setTagStatsId(pk);
	}

	public long getTagStatsId() {
		return _tagStatsId;
	}

	public void setTagStatsId(long tagStatsId) {
		_tagStatsId = tagStatsId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getTagId() {
		return _tagId;
	}

	public void setTagId(long tagId) {
		_tagId = tagId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public int getAssetCount() {
		return _assetCount;
	}

	public void setAssetCount(int assetCount) {
		_assetCount = assetCount;
	}

	private long _tagStatsId;
	private long _companyId;
	private long _tagId;
	private long _classNameId;
	private int _assetCount;

}