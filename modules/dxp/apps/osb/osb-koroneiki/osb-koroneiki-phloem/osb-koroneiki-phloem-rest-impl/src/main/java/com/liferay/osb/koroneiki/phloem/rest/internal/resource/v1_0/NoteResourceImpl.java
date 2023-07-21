/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.NoteUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.NoteResource;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteService;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/note.properties",
	scope = ServiceScope.PROTOTYPE, service = NoteResource.class
)
public class NoteResourceImpl extends BaseNoteResourceImpl {

	@Override
	public void deleteNote(String agentName, String agentUID, String noteKey)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_accountNoteService.deleteAccountNote(noteKey);
	}

	@Override
	public Page<Note> getAccountAccountKeyNotesPage(
			String accountKey, Integer priority, String status, String type,
			Pagination pagination)
		throws Exception {

		Account account = _accountLocalService.getAccount(accountKey);

		String[] types = new String[0];

		if (Validator.isNotNull(type)) {
			types = new String[] {type};
		}

		int[] priorities = new int[0];

		if ((priority != null) && (priority > 0)) {
			priorities = new int[] {priority.intValue()};
		}

		String[] statuses = new String[0];

		if (Validator.isNotNull(status)) {
			statuses = new String[] {status};
		}

		return Page.of(
			transform(
				_accountNoteService.getAccountNotes(
					account.getAccountId(), types, priorities, statuses,
					pagination.getStartPosition(), pagination.getEndPosition()),
				acountNote -> NoteUtil.toNote(acountNote)),
			pagination,
			_accountNoteService.getAccountNotesCount(
				account.getAccountId(), types, priorities, statuses));
	}

	@Override
	public Note getNote(String noteKey) throws Exception {
		return NoteUtil.toNote(_accountNoteService.getAccountNote(noteKey));
	}

	@Override
	public Note postAccountAccountKeyNote(
			String agentName, String agentUID, String accountKey, Note note)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		Account account = _accountLocalService.getAccount(accountKey);

		String type = StringPool.BLANK;

		Note.Type noteType = note.getType();

		if (noteType != null) {
			type = noteType.toString();
		}

		int priority = 1;

		if (note.getPriority() != null) {
			priority = note.getPriority();
		}

		String format = StringPool.BLANK;

		Note.Format noteFormat = note.getFormat();

		if (noteFormat != null) {
			format = noteFormat.toString();
		}

		String status = StringPool.BLANK;

		Note.Status noteStatus = note.getStatus();

		if (noteStatus != null) {
			status = noteStatus.toString();
		}

		return NoteUtil.toNote(
			_accountNoteService.addAccountNote(
				agentUID, agentName, account.getAccountId(), type, priority,
				note.getContent(), format, status));
	}

	@Override
	public Note putNote(
			String agentName, String agentUID, String noteKey, Note note)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		AccountNote accountNote = _accountNoteLocalService.getAccountNote(
			noteKey);

		int priority = GetterUtil.getInteger(
			note.getPriority(), accountNote.getPriority());
		String content = GetterUtil.getString(
			note.getContent(), accountNote.getContent());

		String format = accountNote.getFormat();

		Note.Format noteFormat = note.getFormat();

		if (noteFormat != null) {
			format = noteFormat.toString();
		}

		String status = accountNote.getStatus();

		Note.Status noteStatus = note.getStatus();

		if (noteStatus != null) {
			status = noteStatus.toString();
		}

		return NoteUtil.toNote(
			_accountNoteService.updateAccountNote(
				accountNote.getAccountNoteId(), agentUID, agentName, priority,
				content, format, status));
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

	@Reference
	private AccountNoteService _accountNoteService;

	@Reference
	private AccountService _accountService;

}