/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.zendesk.model;

import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class ZendeskTicket {

	public ZendeskTicket() {
	}

	public Map<Long, String> getCustomFields() {
		return _customFields;
	}

	public String getDescription() {
		return _description;
	}

	public String[] getEmailCCs() {
		return _emailCCs;
	}

	public long getGroupId() {
		return _groupId;
	}

	public long getRequesterId() {
		return _requesterId;
	}

	public String getStatus() {
		return _status;
	}

	public String getSubject() {
		return _subject;
	}

	public Set<String> getTags() {
		return _tags;
	}

	public long getZendeskOrganizationId() {
		return _zendeskOrganizationId;
	}

	public long getZendeskTicketId() {
		return _zendeskTicketId;
	}

	public void setCustomFields(Map<Long, String> customFields) {
		_customFields = customFields;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setEmailCCs(String[] emailCCs) {
		_emailCCs = emailCCs;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setRequesterId(long requesterId) {
		_requesterId = requesterId;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public void setTags(Set<String> tags) {
		_tags = tags;
	}

	public void setZendeskOrganizationId(long zendeskOrganizationId) {
		_zendeskOrganizationId = zendeskOrganizationId;
	}

	public void setZendeskTicketId(long zendeskTicketId) {
		_zendeskTicketId = zendeskTicketId;
	}

	private Map<Long, String> _customFields;
	private String _description;
	private String[] _emailCCs;
	private long _groupId;
	private long _requesterId;
	private String _status;
	private String _subject;
	private Set<String> _tags;
	private long _zendeskOrganizationId;
	private long _zendeskTicketId;

}