/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default function(nameSpace) {
	const deleteMultipleSynonyms = function() {
		if (
			confirm(
				Liferay.Language.get(
					'are-you-sure-you-want-to-delete-the-selected-entries'
				)
			)
		) {
			const form = document.forms[`${nameSpace}SynonymSetsEntriesFm`];

			form.submit();
		}
	};

	const ACTIONS = {
		deleteMultipleSynonyms
	};

	Liferay.componentReady('synonymSetsEntriesManagementToolbar').then(
		managementToolbar => {
			managementToolbar.on('actionItemClicked', event => {
				const itemData = event.data.item.data;

				if (itemData && itemData.action && ACTIONS[itemData.action]) {
					ACTIONS[itemData.action]();
				}
			});
		}
	);
}
