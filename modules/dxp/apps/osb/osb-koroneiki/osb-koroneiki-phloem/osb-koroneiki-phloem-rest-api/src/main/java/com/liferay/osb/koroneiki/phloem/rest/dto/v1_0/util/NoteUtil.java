/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;

/**
 * @author Amos Fong
 */
public class NoteUtil {

	public static Note toNote(AccountNote accountNote) throws Exception {
		return new Note() {
			{
				content = accountNote.getContent();
				creatorName = accountNote.getCreatorName();
				creatorUID = accountNote.getCreatorUID();
				dateCreated = accountNote.getCreateDate();
				dateModified = accountNote.getModifiedDate();
				format = Format.create(accountNote.getFormat());
				key = accountNote.getAccountNoteKey();
				modifierName = accountNote.getModifierName();
				modifierUID = accountNote.getModifierUID();
				priority = accountNote.getPriority();
				status = Status.create(accountNote.getStatus());
				type = Type.create(accountNote.getType());
			}
		};
	}

}