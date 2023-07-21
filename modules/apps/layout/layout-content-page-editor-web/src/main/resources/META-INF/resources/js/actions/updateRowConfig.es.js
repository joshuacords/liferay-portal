/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {UPDATE_ROW_CONFIG} from './actions.es';
import {
	disableSavingChangesStatusAction,
	enableSavingChangesStatusAction,
	updateLastSaveDateAction
} from './saveChanges.es';
import {updatePageContentsAction} from './updatePageContents.es';
import {updatePageEditorLayoutDataAction} from './updatePageEditorLayoutData.es';

function updateRowConfigAction(rowId, configContent) {
	return function(dispatch) {
		dispatch(enableSavingChangesStatusAction());

		dispatch(updateRowConfig(rowId, configContent));

		dispatch(updatePageEditorLayoutDataAction());

		dispatch(updateLastSaveDateAction());
		dispatch(disableSavingChangesStatusAction());

		const {backgroundImage} = configContent;

		if (
			backgroundImage &&
			(backgroundImage.fieldId !== undefined ||
				backgroundImage.mappedField !== undefined)
		) {
			dispatch(updatePageContentsAction());
		}
	};
}

function updateRowConfig(rowId, configContent) {
	return {
		config: {...configContent},
		rowId,
		type: UPDATE_ROW_CONFIG
	};
}

export {updateRowConfigAction};
