/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.NoteResource;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = NoteWebService.class
)
public class NoteWebServiceImpl
	extends BaseWebService implements NoteWebService {

	public Note addNote(
			String agentName, String agentUID, String accountKey, Note note)
		throws Exception {

		return _noteResource.postAccountAccountKeyNote(
			agentName, agentUID, accountKey, note);
	}

	public void deleteNote(String agentName, String agentUID, String noteKey)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_noteResource.deleteNoteHttpResponse(agentName, agentUID, noteKey);

		validateResponse(httpResponse);
	}

	public List<Note> getNotes(
			String accountKey, String type, int priority, String status,
			int page, int pageSize)
		throws Exception {

		Page<Note> notesPage = _noteResource.getAccountAccountKeyNotesPage(
			accountKey, priority, status, type, Pagination.of(page, pageSize));

		if ((notesPage != null) && (notesPage.getItems() != null)) {
			return new ArrayList<>(notesPage.getItems());
		}

		return Collections.emptyList();
	}

	public Note updateNote(
			String agentName, String agentUID, String noteKey, Note note)
		throws Exception {

		return _noteResource.putNote(agentName, agentUID, noteKey, note);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		NoteResource.Builder builder = NoteResource.builder();

		_noteResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private NoteResource _noteResource;

}