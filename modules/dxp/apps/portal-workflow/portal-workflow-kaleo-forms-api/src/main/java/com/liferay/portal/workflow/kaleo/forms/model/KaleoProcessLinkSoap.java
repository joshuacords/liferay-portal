/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Marcellus Tavares
 * @generated
 */
public class KaleoProcessLinkSoap implements Serializable {

	public static KaleoProcessLinkSoap toSoapModel(KaleoProcessLink model) {
		KaleoProcessLinkSoap soapModel = new KaleoProcessLinkSoap();

		soapModel.setKaleoProcessLinkId(model.getKaleoProcessLinkId());
		soapModel.setKaleoProcessId(model.getKaleoProcessId());
		soapModel.setWorkflowTaskName(model.getWorkflowTaskName());
		soapModel.setDDMTemplateId(model.getDDMTemplateId());

		return soapModel;
	}

	public static KaleoProcessLinkSoap[] toSoapModels(
		KaleoProcessLink[] models) {

		KaleoProcessLinkSoap[] soapModels =
			new KaleoProcessLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoProcessLinkSoap[][] toSoapModels(
		KaleoProcessLink[][] models) {

		KaleoProcessLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new KaleoProcessLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoProcessLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoProcessLinkSoap[] toSoapModels(
		List<KaleoProcessLink> models) {

		List<KaleoProcessLinkSoap> soapModels =
			new ArrayList<KaleoProcessLinkSoap>(models.size());

		for (KaleoProcessLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoProcessLinkSoap[soapModels.size()]);
	}

	public KaleoProcessLinkSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoProcessLinkId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoProcessLinkId(pk);
	}

	public long getKaleoProcessLinkId() {
		return _kaleoProcessLinkId;
	}

	public void setKaleoProcessLinkId(long kaleoProcessLinkId) {
		_kaleoProcessLinkId = kaleoProcessLinkId;
	}

	public long getKaleoProcessId() {
		return _kaleoProcessId;
	}

	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcessId = kaleoProcessId;
	}

	public String getWorkflowTaskName() {
		return _workflowTaskName;
	}

	public void setWorkflowTaskName(String workflowTaskName) {
		_workflowTaskName = workflowTaskName;
	}

	public long getDDMTemplateId() {
		return _DDMTemplateId;
	}

	public void setDDMTemplateId(long DDMTemplateId) {
		_DDMTemplateId = DDMTemplateId;
	}

	private long _kaleoProcessLinkId;
	private long _kaleoProcessId;
	private String _workflowTaskName;
	private long _DDMTemplateId;

}