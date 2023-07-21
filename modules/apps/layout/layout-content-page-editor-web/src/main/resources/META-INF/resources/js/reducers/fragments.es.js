/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import {updatePageEditorLayoutData} from '../utils/FragmentsEditorFetchUtils.es';
import {
	getColumn,
	getDropRowPosition,
	getFragmentColumn,
	getFragmentRowIndex
} from '../utils/FragmentsEditorGetUtils.es';
import {
	add,
	addRow,
	remove,
	setIn,
	updateIn
} from '../utils/FragmentsEditorUpdateUtils.es';
import {
	EDITABLE_FRAGMENT_ENTRY_PROCESSOR,
	FRAGMENTS_EDITOR_ITEM_BORDERS,
	FRAGMENTS_EDITOR_ITEM_TYPES,
	FRAGMENTS_EDITOR_ROW_TYPES
} from '../utils/constants';

/**
 * Adds a fragment at the corresponding container in the layout
 * @param {string} fragmentEntryLinkId
 * @param {string} dropTargetBorder
 * @param {string} dropTargetItemId
 * @param {string} dropTargetItemType
 * @param {object} layoutData
 * @param {string} fragmentEntryLinkRowType
 * @private
 * @return {object}
 * @review
 */
function addFragment(
	fragmentEntryLinkId,
	dropTargetBorder,
	dropTargetItemId,
	dropTargetItemType,
	layoutData,
	fragmentEntryLinkRowType = FRAGMENTS_EDITOR_ROW_TYPES.componentRow
) {
	let nextData = layoutData;

	if (dropTargetItemType === FRAGMENTS_EDITOR_ITEM_TYPES.column) {
		const fragmentColumn = getColumn(
			layoutData.structure,
			dropTargetItemId
		);

		nextData = _addFragmentToColumn(
			layoutData,
			fragmentEntryLinkId,
			dropTargetItemId,
			fragmentColumn.fragmentEntryLinkIds.length
		);
	}
	else if (dropTargetItemType === FRAGMENTS_EDITOR_ITEM_TYPES.fragment) {
		const fragmentColumn = getFragmentColumn(
			layoutData.structure,
			dropTargetItemId
		);

		const position = _getDropFragmentPosition(
			fragmentColumn.fragmentEntryLinkIds,
			dropTargetItemId,
			dropTargetBorder
		);

		nextData = _addFragmentToColumn(
			layoutData,
			fragmentEntryLinkId,
			fragmentColumn.columnId,
			position
		);
	}
	else if (dropTargetItemType === FRAGMENTS_EDITOR_ITEM_TYPES.row) {
		const position = getDropRowPosition(
			layoutData.structure,
			dropTargetItemId,
			dropTargetBorder
		);

		nextData = _addSingleFragmentRow(
			layoutData,
			fragmentEntryLinkId,
			fragmentEntryLinkRowType,
			position
		);
	}
	else {
		nextData = _addSingleFragmentRow(
			layoutData,
			fragmentEntryLinkId,
			fragmentEntryLinkRowType,
			layoutData.structure.length
		);
	}

	return nextData;
}

/**
 * @param {object} state
 * @param {object} action
 * @param {object} action.comment
 * @param {string} action.fragmentEntryLinkId
 * @param {string} [action.parentCommentId]
 * @param {string} action.type
 * @return {object}
 * @review
 */
function updateFragmentEntryLinkCommentReducer(state, action) {
	const commentId = action.comment.commentId;
	let nextState = state;
	let path = ['fragmentEntryLinks', action.fragmentEntryLinkId, 'comments'];

	if (action.parentCommentId) {
		const parentCommentIndex = nextState.fragmentEntryLinks[
			action.fragmentEntryLinkId
		].comments.findIndex(
			comment => comment.commentId === action.parentCommentId
		);

		path = [...path, parentCommentIndex, 'children'];
	}

	nextState = updateIn(
		nextState,
		path,
		comments => {
			let nextComments;

			if (comments.find(comment => comment.commentId === commentId)) {
				nextComments = comments.map(comment =>
					comment.commentId === commentId
						? {...action.comment, children: comment.children}
						: comment
				);
			}
			else {
				nextComments = [...comments, action.comment];
			}

			return nextComments;
		},
		[]
	);

	const hasResolvedComments = Object.values(
		nextState.fragmentEntryLinks
	).some(fragmentEntryLink =>
		fragmentEntryLink.comments.some(comment => comment.resolved)
	);

	if (!hasResolvedComments) {
		nextState = setIn(nextState, ['showResolvedComments'], false);
	}

	return nextState;
}

/**
 * @param {object} state
 * @return {object}
 * @review
 */
function toggleShowResolvedCommentsReducer(state) {
	return setIn(state, ['showResolvedComments'], !state.showResolvedComments);
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.type
 * @param {string} action.fragmentEntryLinkId
 * @param {object} action.comment
 * @return {object}
 * @review
 */
function deleteFragmentEntryLinkCommentReducer(state, action) {
	let nextState = state;

	const filterComment = comment =>
		comment.commentId !== action.comment.commentId;

	nextState = updateIn(
		nextState,
		['fragmentEntryLinks', action.fragmentEntryLinkId, 'comments'],
		comments =>
			comments.filter(filterComment).map(comment => ({
				...comment,
				children: (comment.children || []).filter(filterComment)
			})),
		[]
	);

	return nextState;
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.type
 * @param {string} action.fragmentEntryKey
 * @param {string} action.fragmentEntryLinkType
 * @param {string} action.fragmentName
 * @param {string} action.groupId
 * @return {object}
 * @review
 */
function addFragmentEntryLinkReducer(state, action) {
	return new Promise(resolve => {
		let fragmentEntryLink = null;
		let nextData = null;
		let nextState = state;

		_addFragmentEntryLink(
			nextState.addFragmentEntryLinkURL,
			action.fragmentEntryKey,
			action.fragmentName,
			action.groupId,
			nextState.classNameId,
			nextState.classPK,
			nextState.portletNamespace,
			nextState.segmentsExperienceId
		)
			.then(response => {
				fragmentEntryLink = response;

				nextData = addFragment(
					fragmentEntryLink.fragmentEntryLinkId,
					nextState.dropTargetBorder,
					nextState.dropTargetItemId,
					nextState.dropTargetItemType,
					nextState.layoutData,
					action.fragmentEntryLinkRowType
				);

				return updatePageEditorLayoutData(
					nextData,
					nextState.segmentsExperienceId
				);
			})
			.then(() => {
				nextState = setIn(
					nextState,
					[
						'fragmentEntryLinks',
						fragmentEntryLink.fragmentEntryLinkId
					],
					fragmentEntryLink
				);

				nextState = setIn(nextState, ['layoutData'], nextData);

				resolve(nextState);
			})
			.catch(() => {
				resolve(nextState);
			});
	});
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.fragmentEntryLinkId
 * @param {string} action.fragmentEntryLinkRowType
 * @return {object}
 * @review
 */
function duplicateFragmentEntryLinkReducer(state, action) {
	let nextState = state;

	const {fragmentEntryLink} = action;
	let nextData = nextState.layoutData;

	nextData = _duplicateFragment(
		action.fragmentEntryLinkId,
		fragmentEntryLink,
		action.fragmentEntryLinkRowType,
		nextData
	);

	nextState = setIn(nextState, ['layoutData'], nextData);

	nextState = setIn(
		nextState,
		['fragmentEntryLinks', fragmentEntryLink.fragmentEntryLinkId],
		fragmentEntryLink
	);

	return nextState;
}

/**
 * @param {string} renderFragmentEntryURL
 * @param {{fragmentEntryLinkId: string}} fragmentEntryLink
 * @param {string} portletNamespace
 * @param {number} segmentsExperienceId
 * @return {Promise<object>}
 * @review
 */
function getFragmentEntryLinkContent(
	renderFragmentEntryURL,
	fragmentEntryLink,
	portletNamespace,
	segmentsExperienceId
) {
	const formData = new FormData();

	formData.append(
		`${portletNamespace}fragmentEntryLinkId`,
		fragmentEntryLink.fragmentEntryLinkId
	);

	formData.append(
		`${portletNamespace}segmentsExperienceId`,
		segmentsExperienceId
	);

	return fetch(renderFragmentEntryURL, {
		body: formData,
		method: 'POST'
	})
		.then(response => response.json())
		.then(response => {
			if (!response.content) {
				throw new Error();
			}

			fragmentEntryLink = setIn(
				fragmentEntryLink,
				['content'],
				response.content
			);

			fragmentEntryLink = setIn(
				fragmentEntryLink,
				['editableValues'],
				response.editableValues
			);

			return setIn(fragmentEntryLink, ['error'], response.error);
		});
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.fragmentEntryLinkId
 * @param {string} action.fragmentEntryLinkRowType
 * @param {string} action.fragmentEntryLinkType
 * @param {string} action.targetBorder
 * @param {string} action.targetItemId
 * @param {string} action.targetItemType
 * @param {string} action.type
 * @return {object}
 * @review
 */
function moveFragmentEntryLinkReducer(state, action) {
	return new Promise(resolve => {
		let nextData = null;
		let nextState = state;

		nextData = _removeFragment(
			nextState.layoutData,
			action.fragmentEntryLinkId,
			action.fragmentEntryLinkRowType
		);

		nextData = addFragment(
			action.fragmentEntryLinkId,
			action.targetBorder,
			action.targetItemId,
			action.targetItemType,
			nextData,
			action.fragmentEntryLinkRowType
		);

		updatePageEditorLayoutData(nextData, nextState.segmentsExperienceId)
			.then(response => {
				if (response.error) {
					throw response.error;
				}

				nextState = setIn(nextState, ['layoutData'], nextData);

				resolve(nextState);
			})
			.catch(() => {
				resolve(nextState);
			});
	});
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.fragmentEntryLinkId
 * @param {string} action.type
 * @return {object}
 * @review
 */
function removeFragmentEntryLinkReducer(state, action) {
	const {fragmentEntryLinkId} = action;
	let nextState = state;

	if (
		nextState.activeItemType === FRAGMENTS_EDITOR_ITEM_TYPES.fragment &&
		nextState.activeItemId === fragmentEntryLinkId
	) {
		nextState = {
			...nextState,

			activeItemId: null,
			activeItemType: null
		};
	}

	if (
		nextState.hoveredItemType === FRAGMENTS_EDITOR_ITEM_TYPES.fragment &&
		nextState.hoveredItemId === fragmentEntryLinkId
	) {
		nextState = {
			...nextState,

			hoveredItemId: null,
			hoveredItemType: null
		};
	}

	const fragmentEntryLinkRow =
		nextState.layoutData.structure[
			getFragmentRowIndex(
				nextState.layoutData.structure,
				fragmentEntryLinkId
			)
		];

	nextState = setIn(
		nextState,
		['layoutData'],
		_removeFragment(
			nextState.layoutData,
			fragmentEntryLinkId,
			fragmentEntryLinkRow.type || FRAGMENTS_EDITOR_ROW_TYPES.componentRow
		)
	);

	nextState = setIn(
		nextState,
		['layoutData'],
		_removeFragmentFromHiddenContentFromSearch(
			nextState.layoutData,
			fragmentEntryLinkId
		)
	);

	if (!action.fragmentEntryLinkIsUsedInOtherExperience) {
		nextState = updateIn(
			nextState,
			['fragmentEntryLinks'],
			fragmentEntryLinks => {
				const nextFragmentEntryLinks = {
					...fragmentEntryLinks
				};

				delete nextFragmentEntryLinks[fragmentEntryLinkId];

				return nextFragmentEntryLinks;
			},
			{}
		);
	}

	return nextState;
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.type
 * @param {string} action.fragmentEntryLinkId
 * @param {string} action.editableValues
 * @param {Date} action.date
 * @return {object}
 * @review
 */
function updateEditableValueReducer(state, action) {
	return setIn(
		state,
		['fragmentEntryLinks', action.fragmentEntryLinkId, 'editableValues'],
		action.editableValues
	);
}

/**
 * @param {object} state
 * @param {object} action
 * @param {object} action.config
 * @param {string} action.editableId
 * @param {string} action.fragmentEntryLinkId
 * @param {string} action.type
 * @return {object}
 * @review
 */
function updateFragmentEntryLinkConfigReducer(state, action) {
	let nextState = state;

	return new Promise(resolve => {
		const {config, editableId, fragmentEntryLinkId} = action;

		let {editableValues} = nextState.fragmentEntryLinks[
			fragmentEntryLinkId
		];

		Object.entries(config).forEach(entry => {
			const [key, value] = entry;

			const keysTreeArray = [
				EDITABLE_FRAGMENT_ENTRY_PROCESSOR,
				editableId,
				'config',
				key
			];

			editableValues = setIn(editableValues, keysTreeArray, value);
		});

		const formData = new FormData();

		formData.append(
			`${nextState.portletNamespace}fragmentEntryLinkId`,
			fragmentEntryLinkId
		);

		formData.append(
			`${nextState.portletNamespace}editableValues`,
			JSON.stringify(editableValues)
		);

		fetch(nextState.editFragmentEntryLinkURL, {
			body: formData,
			method: 'POST'
		}).then(() => {
			nextState = setIn(
				nextState,
				['fragmentEntryLinks', fragmentEntryLinkId, 'editableValues'],
				editableValues
			);

			resolve(nextState);
		});
	});
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.fragmentEntryLinkContent
 * @param {string} action.fragmentEntryLinkId
 * @return {object}
 * @review
 */
function updateFragmentEntryLinkContentReducer(state, action) {
	const {
		editableValues,
		fragmentEntryLinkContent,
		fragmentEntryLinkId
	} = action;
	let nextState = state;

	const fragmentEntryLink = nextState.fragmentEntryLinks[fragmentEntryLinkId];

	if (fragmentEntryLink) {
		nextState = setIn(
			nextState,
			[
				'fragmentEntryLinks',
				fragmentEntryLink.fragmentEntryLinkId,
				'content'
			],
			fragmentEntryLinkContent
		);

		if (editableValues) {
			nextState = setIn(
				nextState,
				[
					'fragmentEntryLinks',
					fragmentEntryLink.fragmentEntryLinkId,
					'editableValues'
				],
				editableValues
			);
		}
	}

	return nextState;
}

/**
 * @param {object} state
 * @param {object} action
 * @param {string} action.fragmentEntryLinkId
 * @param {boolean} action.nonIndexable
 * @return {object}
 * @review
 */
function updateFragmentEntryLinkSearchOptionsReducer(state, action) {
	const {fragmentEntryLinkId, nonIndexable} = action;
	let nextState = state;

	if (nonIndexable) {
		nextState = setIn(
			nextState,
			['layoutData'],
			_addFragmentToHiddenContentFromSearch(
				nextState.layoutData,
				fragmentEntryLinkId
			)
		);
	}
	else {
		nextState = setIn(
			nextState,
			['layoutData'],
			_removeFragmentFromHiddenContentFromSearch(
				nextState.layoutData,
				fragmentEntryLinkId
			)
		);
	}

	return nextState;
}

/**
 * @param {string} addFragmentEntryLinkURL
 * @param {string} fragmentEntryKey
 * @param {string} fragmentName
 * @param {string} classNameId
 * @param {string} classPK
 * @param {string} portletNamespace
 * @return {object}
 * @review
 */
function _addFragmentEntryLink(
	addFragmentEntryLinkURL,
	fragmentEntryKey,
	fragmentName,
	groupId,
	classNameId,
	classPK,
	portletNamespace,
	segmentsExperienceId
) {
	const formData = new FormData();

	formData.append(`${portletNamespace}fragmentKey`, fragmentEntryKey);
	formData.append(`${portletNamespace}classNameId`, classNameId);
	formData.append(`${portletNamespace}classPK`, classPK);
	formData.append(`${portletNamespace}groupId`, groupId);
	formData.append(
		`${portletNamespace}segmentsExperienceId`,
		segmentsExperienceId
	);

	return fetch(addFragmentEntryLinkURL, {
		body: formData,
		method: 'POST'
	})
		.then(response => response.json())
		.then(response => {
			if (!response.fragmentEntryLinkId) {
				throw new Error();
			}

			return {
				config: {},
				configuration: response.configuration,
				content: response.content,
				defaultConfigurationValues: response.defaultConfigurationValues,
				editableValues: response.editableValues,
				fragmentEntryKey,
				fragmentEntryLinkId: response.fragmentEntryLinkId,
				name: fragmentName
			};
		});
}

/**
 * Returns a new layoutData with the given fragmentEntryLinkId inserted
 * into a given column at the given position
 *
 * @param {object} layoutData
 * @param {string} fragmentEntryLinkId
 * @param {string} targetColumnId
 * @param {number} position
 * @return {object}
 */
function _addFragmentToColumn(
	layoutData,
	fragmentEntryLinkId,
	targetColumnId,
	position
) {
	const {structure} = layoutData;

	const column = getColumn(structure, targetColumnId);
	const row = structure.find(_row =>
		_row.columns.find(_column => column === _column)
	);

	const columnIndex = row.columns.indexOf(column);
	const rowIndex = structure.indexOf(row);

	return updateIn(
		layoutData,
		['structure', rowIndex, 'columns', columnIndex, 'fragmentEntryLinkIds'],
		fragmentEntryLinkIds =>
			add(fragmentEntryLinkIds, fragmentEntryLinkId, position)
	);
}

/**
 * Adds a given fragmentEntryLinkId to hidden content from search.
 *
 * @param {object} layoutData
 * @param {string} fragmentEntryLinkId
 * @return {object} Next layout data
 * @review
 */
function _addFragmentToHiddenContentFromSearch(
	layoutData,
	fragmentEntryLinkId
) {
	const {nonIndexableFragmentEntryLinkIds} = layoutData;

	let fragmentIndex = -1;

	if (nonIndexableFragmentEntryLinkIds) {
		fragmentIndex = nonIndexableFragmentEntryLinkIds.indexOf(
			fragmentEntryLinkId
		);
	}

	let nextData = layoutData;

	if (fragmentIndex === -1) {
		nextData = updateIn(
			layoutData,
			['nonIndexableFragmentEntryLinkIds'],
			nonIndexableFragmentEntryLinkIds =>
				add(nonIndexableFragmentEntryLinkIds, fragmentEntryLinkId, 0),
			[]
		);
	}

	return nextData;
}

/**
 * Returns a new layoutData with the given fragmentEntryLinkId inserted
 * into a single-column new row. The row will be created at the given position.
 *
 * @param {object} layoutData
 * @param {string} fragmentEntryLinkId
 * @param {string} fragmentEntryLinkRowType
 * @param {number} position
 * @return {object}
 */
function _addSingleFragmentRow(
	layoutData,
	fragmentEntryLinkId,
	fragmentEntryLinkRowType,
	position
) {
	return addRow(
		['12'],
		layoutData,
		position,
		[fragmentEntryLinkId],
		fragmentEntryLinkRowType
	);
}

/**
 * Duplicate a fragment inside layoutData
 * @param {string} originalFragmentEntryLinkId
 * @param {object} fragmentEntryLink
 * @param {string} fragmentEntryLinkRowType
 * @param {object} layoutData
 * @private
 * @return {object}
 * @review
 */
function _duplicateFragment(
	originalFragmentEntryLinkId,
	fragmentEntryLink,
	fragmentEntryLinkRowType,
	layoutData
) {
	let nextData = layoutData;

	if (fragmentEntryLinkRowType === FRAGMENTS_EDITOR_ROW_TYPES.componentRow) {
		const fragmentColumn = getFragmentColumn(
			layoutData.structure,
			originalFragmentEntryLinkId
		);

		const position = fragmentColumn.fragmentEntryLinkIds.indexOf(
			originalFragmentEntryLinkId
		);

		nextData = _addFragmentToColumn(
			layoutData,
			fragmentEntryLink.fragmentEntryLinkId,
			fragmentColumn.columnId,
			position + 1
		);
	}
	else {
		const position =
			getFragmentRowIndex(
				layoutData.structure,
				originalFragmentEntryLinkId
			) + 1;

		nextData = _addSingleFragmentRow(
			layoutData,
			fragmentEntryLink.fragmentEntryLinkId,
			fragmentEntryLinkRowType,
			position
		);
	}

	return nextData;
}

/**
 * @param {string[]} fragmentEntryLinkIds
 * @param {string} targetFragmentEntryLinkId
 * @param {FRAGMENTS_EDITOR_ITEM_BORDERS} targetBorder
 * @return {number}
 * @review
 */
function _getDropFragmentPosition(
	fragmentEntryLinkIds,
	targetFragmentEntryLinkId,
	targetBorder
) {
	let position = fragmentEntryLinkIds.length;

	const targetPosition = fragmentEntryLinkIds.indexOf(
		targetFragmentEntryLinkId
	);

	if (targetPosition > -1 && targetBorder) {
		if (targetBorder === FRAGMENTS_EDITOR_ITEM_BORDERS.top) {
			position = targetPosition;
		}
		else {
			position = targetPosition + 1;
		}
	}

	return position;
}

/**
 * Removes a given fragmentEntryLinkId from a given layoutData.
 * It does not remove it from fragmentEntryLinks array.
 *
 * If the fragmentEntryLinkRowType is "section", it will remove the whole
 * row (and columns) too.
 *
 * @param {object} layoutData
 * @param {string} fragmentEntryLinkId
 * @param {string} [fragmentEntryLinkRowType=FRAGMENTS_EDITOR_ROW_TYPES.componentRow]
 * @return {object} Next layout data
 * @review
 */
function _removeFragment(
	layoutData,
	fragmentEntryLinkId,
	fragmentEntryLinkRowType = FRAGMENTS_EDITOR_ROW_TYPES.componentRow
) {
	const {structure} = layoutData;

	const column = getFragmentColumn(structure, fragmentEntryLinkId);
	const row = structure.find(_row =>
		_row.columns.find(_column => column === _column)
	);

	const columnIndex = row.columns.indexOf(column);
	const fragmentIndex = column.fragmentEntryLinkIds.indexOf(
		fragmentEntryLinkId
	);
	const rowIndex = structure.indexOf(row);

	let nextData = null;

	if (fragmentEntryLinkRowType === FRAGMENTS_EDITOR_ROW_TYPES.sectionRow) {
		nextData = updateIn(layoutData, ['structure'], structure =>
			remove(structure, rowIndex)
		);
	}
	else {
		nextData = updateIn(
			layoutData,
			[
				'structure',
				rowIndex,
				'columns',
				columnIndex,
				'fragmentEntryLinkIds'
			],
			fragmentEntryLinkIds => remove(fragmentEntryLinkIds, fragmentIndex)
		);
	}

	return nextData;
}

/**
 * Removes a given fragmentEntryLinkId to hidden content from search.
 *
 * @param {object} layoutData
 * @param {string} fragmentEntryLinkId
 * @return {object} Next layout data
 * @review
 */
function _removeFragmentFromHiddenContentFromSearch(
	layoutData,
	fragmentEntryLinkId
) {
	const {nonIndexableFragmentEntryLinkIds = []} = layoutData;

	const fragmentIndex = nonIndexableFragmentEntryLinkIds.indexOf(
		fragmentEntryLinkId
	);

	if (fragmentIndex !== -1) {
		return updateIn(
			layoutData,
			['nonIndexableFragmentEntryLinkIds'],
			nonIndexableFragmentEntryLinkIds =>
				remove(nonIndexableFragmentEntryLinkIds, fragmentIndex),
			[]
		);
	}

	return layoutData;
}

export {
	addFragment,
	addFragmentEntryLinkReducer,
	deleteFragmentEntryLinkCommentReducer,
	duplicateFragmentEntryLinkReducer,
	getFragmentEntryLinkContent,
	moveFragmentEntryLinkReducer,
	removeFragmentEntryLinkReducer,
	toggleShowResolvedCommentsReducer,
	updateEditableValueReducer,
	updateFragmentEntryLinkConfigReducer,
	updateFragmentEntryLinkCommentReducer,
	updateFragmentEntryLinkContentReducer,
	updateFragmentEntryLinkSearchOptionsReducer
};
