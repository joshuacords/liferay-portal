/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface NoteWebService {

	public Note addNote(
			String agentName, String agentUID, String accountKey, Note note)
		throws Exception;

	public void deleteNote(String agentName, String agentUID, String noteKey)
		throws Exception;

	public List<Note> getNotes(
			String accountKey, String type, int priority, String status,
			int page, int pageSize)
		throws Exception;

	public Note updateNote(
			String agentName, String agentUID, String noteKey, Note note)
		throws Exception;

}