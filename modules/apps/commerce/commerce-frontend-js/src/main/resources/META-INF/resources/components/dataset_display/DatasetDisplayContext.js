/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

const DatasetDisplayContext = React.createContext({
	actionLoading: false,
	executeAsyncBulkAction: () => {},
	executeAsyncItemAction: () => {},
	formId: null,
	formRef: null,
	highlightItems: () => {},
	highlightedItemsValue: [],
	itemsActions: () => {},
	loadData: () => {},
	modalId: null,
	namespace: null,
	nestedItemsKey: null,
	nestedItemsReferenceKey: null,
	openModal: () => {},
	openSidePanel: () => {},
	selectItems: () => {},
	selectable: false,
	selectedItemsKey: null,
	selectedItemsValue: [],
	selectionType: null,
	sidePanelId: null,
	sorting: [],
	style: null,
	updateDatasetItems: () => {},
	updateSearchParam: () => {},
	updateSorting: () => {}
});

export default DatasetDisplayContext;
