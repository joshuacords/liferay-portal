/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated
 * @generated
 */
@Deprecated
public class ClusterGroupSoap implements Serializable {

	public static ClusterGroupSoap toSoapModel(ClusterGroup model) {
		ClusterGroupSoap soapModel = new ClusterGroupSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setClusterGroupId(model.getClusterGroupId());
		soapModel.setName(model.getName());
		soapModel.setClusterNodeIds(model.getClusterNodeIds());
		soapModel.setWholeCluster(model.isWholeCluster());

		return soapModel;
	}

	public static ClusterGroupSoap[] toSoapModels(ClusterGroup[] models) {
		ClusterGroupSoap[] soapModels = new ClusterGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ClusterGroupSoap[][] toSoapModels(ClusterGroup[][] models) {
		ClusterGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ClusterGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ClusterGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ClusterGroupSoap[] toSoapModels(List<ClusterGroup> models) {
		List<ClusterGroupSoap> soapModels = new ArrayList<ClusterGroupSoap>(
			models.size());

		for (ClusterGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ClusterGroupSoap[soapModels.size()]);
	}

	public ClusterGroupSoap() {
	}

	public long getPrimaryKey() {
		return _clusterGroupId;
	}

	public void setPrimaryKey(long pk) {
		setClusterGroupId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getClusterGroupId() {
		return _clusterGroupId;
	}

	public void setClusterGroupId(long clusterGroupId) {
		_clusterGroupId = clusterGroupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getClusterNodeIds() {
		return _clusterNodeIds;
	}

	public void setClusterNodeIds(String clusterNodeIds) {
		_clusterNodeIds = clusterNodeIds;
	}

	public boolean getWholeCluster() {
		return _wholeCluster;
	}

	public boolean isWholeCluster() {
		return _wholeCluster;
	}

	public void setWholeCluster(boolean wholeCluster) {
		_wholeCluster = wholeCluster;
	}

	private long _mvccVersion;
	private long _clusterGroupId;
	private String _name;
	private String _clusterNodeIds;
	private boolean _wholeCluster;

}