/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.web.service.internal.util;

import com.liferay.osb.provisioning.zendesk.model.ZendeskOrganization;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.model.ZendeskUser;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskConverter.class)
public class ZendeskConverter {

	public ZendeskOrganization toZendeskOrganization(JSONObject jsonObject) {
		ZendeskOrganization zendeskOrganization = new ZendeskOrganization();

		zendeskOrganization.setDetails(jsonObject.getString("details"));
		zendeskOrganization.setExternalId(jsonObject.getString("external_id"));
		zendeskOrganization.setName(jsonObject.getString("name"));
		zendeskOrganization.setNotes(jsonObject.getString("notes"));

		JSONObject organizationFieldsJSONObject = jsonObject.getJSONObject(
			"organization_fields");

		zendeskOrganization.setPartnerFirstLineSupport(
			organizationFieldsJSONObject.getString(
				"partner_first_line_support"));
		zendeskOrganization.setPartnerJiraProject(
			organizationFieldsJSONObject.getString("partner_jira_project"));
		zendeskOrganization.setPartnerOrganization(
			organizationFieldsJSONObject.getString("partner_organization"));
		zendeskOrganization.setSLA(
			organizationFieldsJSONObject.getString("sla"));
		zendeskOrganization.setStatus(
			organizationFieldsJSONObject.getString("status"));
		zendeskOrganization.setSupportLanguage(
			organizationFieldsJSONObject.getString("support_language"));
		zendeskOrganization.setSupportRegion(
			organizationFieldsJSONObject.getString("support_region"));
		zendeskOrganization.setTier(
			organizationFieldsJSONObject.getString("tier"));

		zendeskOrganization.setZendeskOrganizationId(jsonObject.getLong("id"));

		return zendeskOrganization;
	}

	public ZendeskTicket toZendeskTicket(JSONObject jsonObject) {
		ZendeskTicket zendeskTicket = new ZendeskTicket();

		zendeskTicket.setDescription(jsonObject.getString("description"));
		zendeskTicket.setRequesterId(jsonObject.getLong("requester_id"));
		zendeskTicket.setStatus(jsonObject.getString("status"));
		zendeskTicket.setSubject(jsonObject.getString("subject"));

		JSONArray jsonArray = jsonObject.getJSONArray("tags");

		if (jsonArray != null) {
			Set<String> tags = new HashSet<>();

			for (int i = 0; i < jsonArray.length(); i++) {
				tags.add(jsonArray.getString(i));
			}

			zendeskTicket.setTags(tags);
		}

		zendeskTicket.setZendeskOrganizationId(
			jsonObject.getLong("organization_id"));
		zendeskTicket.setZendeskTicketId(jsonObject.getLong("id"));

		return zendeskTicket;
	}

	public ZendeskUser toZendeskUser(JSONObject jsonObject) {
		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmail(jsonObject.getString("email"));
		zendeskUser.setExternalId(jsonObject.getString("external_id"));
		zendeskUser.setLocale(jsonObject.getString("locale"));
		zendeskUser.setName(jsonObject.getString("name"));

		JSONArray jsonArray = jsonObject.getJSONArray("tags");

		if (jsonArray != null) {
			Set<String> tags = new HashSet<>();

			for (int i = 0; i < jsonArray.length(); i++) {
				tags.add(jsonArray.getString(i));
			}

			zendeskUser.setTags(tags);
		}

		zendeskUser.setZendeskUserId(jsonObject.getLong("id"));

		return zendeskUser;
	}

}