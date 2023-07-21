/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.model;

import java.util.Set;

/**
 * @author Kyle Bischof
 */
public class ZendeskOrganization {

	public ZendeskOrganization() {
	}

	public String getAccountCode() {
		return _accountCode;
	}

	public String getDetails() {
		return _details;
	}

	public String getExternalId() {
		return _externalId;
	}

	public String getName() {
		return _name;
	}

	public String getNotes() {
		return _notes;
	}

	public String getPartnerFirstLineSupport() {
		return _partnerFirstLineSupport;
	}

	public String getPartnerJiraProject() {
		return _partnerJiraProject;
	}

	public String getPartnerOrganization() {
		return _partnerOrganization;
	}

	public String getSharedComments() {
		return _sharedComments;
	}

	public String getSharedTickets() {
		return _sharedTickets;
	}

	public String getSLA() {
		return _sla;
	}

	public String getStatus() {
		return _status;
	}

	public String getSupportLanguage() {
		return _supportLanguage;
	}

	public String getSupportRegion() {
		return _supportRegion;
	}

	public Set<String> getTags() {
		return _tags;
	}

	public String getTier() {
		return _tier;
	}

	public long getZendeskOrganizationId() {
		return _zendeskOrganizationId;
	}

	public void setAccountCode(String accountCode) {
		_accountCode = accountCode;
	}

	public void setDetails(String details) {
		_details = details;
	}

	public void setExternalId(String externalId) {
		_externalId = externalId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNotes(String notes) {
		_notes = notes;
	}

	public void setPartnerFirstLineSupport(String partnerFirstLineSupport) {
		_partnerFirstLineSupport = partnerFirstLineSupport;
	}

	public void setPartnerJiraProject(String partnerJiraProject) {
		_partnerJiraProject = partnerJiraProject;
	}

	public void setPartnerOrganization(String partnerOrganization) {
		_partnerOrganization = partnerOrganization;
	}

	public void setSharedComments(String sharedComments) {
		_sharedComments = sharedComments;
	}

	public void setSharedTickets(String sharedTickets) {
		_sharedTickets = sharedTickets;
	}

	public void setSLA(String sla) {
		_sla = sla;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setSupportLanguage(String supportLanguage) {
		_supportLanguage = supportLanguage;
	}

	public void setSupportRegion(String supportRegion) {
		_supportRegion = supportRegion;
	}

	public void setTags(Set<String> tags) {
		_tags = tags;
	}

	public void setTier(String tier) {
		_tier = tier;
	}

	public void setZendeskOrganizationId(long zendeskOrganizationId) {
		_zendeskOrganizationId = zendeskOrganizationId;
	}

	private String _accountCode;
	private String _details;
	private String _externalId;
	private String _name;
	private String _notes;
	private String _partnerFirstLineSupport;
	private String _partnerJiraProject;
	private String _partnerOrganization;
	private String _sharedComments;
	private String _sharedTickets;
	private String _sla;
	private String _status;
	private String _supportLanguage;
	private String _supportRegion;
	private Set<String> _tags;
	private String _tier;
	private long _zendeskOrganizationId;

}