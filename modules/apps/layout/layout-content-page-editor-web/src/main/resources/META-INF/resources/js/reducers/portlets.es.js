/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import {updatePageEditorLayoutData} from '../utils/FragmentsEditorFetchUtils.es';
import {getWidgetPath} from '../utils/FragmentsEditorGetUtils.es';
import {setIn} from '../utils/FragmentsEditorUpdateUtils.es';
import editableValuesMigrator from '../utils/fragmentMigrator.es';
import {prefixSegmentsExperienceId} from '../utils/prefixSegmentsExperienceId.es';
import {addFragment, getFragmentEntryLinkContent} from './fragments.es';

/**
 * @param {object} state
 * @param {object} action
 * @param {boolean} action.instanceable
 * @param {string} action.portletId
 * @param {string} action.type
 * @return {object}
 * @review
 */
function addPortletReducer(state, action) {
	return new Promise(resolve => {
		let fragmentEntryLink = null;
		let nextData = null;
		let nextState = state;

		_addPortlet(
			nextState.addPortletURL,
			action.portletId,
			nextState.classNameId,
			nextState.classPK,
			nextState.portletNamespace,
			nextState.segmentsExperienceId ||
				nextState.defaultSegmentsExperienceId
		)
			.then(response => {
				fragmentEntryLink = response;

				nextData = addFragment(
					fragmentEntryLink.fragmentEntryLinkId,
					nextState.dropTargetBorder,
					nextState.dropTargetItemId,
					nextState.dropTargetItemType,
					nextState.layoutData
				);

				return updatePageEditorLayoutData(
					nextData,
					nextState.segmentsExperienceId
				);
			})
			.then(() =>
				getFragmentEntryLinkContent(
					nextState.renderFragmentEntryURL,
					fragmentEntryLink,
					nextState.portletNamespace,
					nextState.segmentsExperienceId ||
						nextState.defaultSegmentsExperienceId
				)
			)
			.then(response => {
				fragmentEntryLink = response;

				fragmentEntryLink.portletId = action.portletId;

				nextState = setIn(
					nextState,
					[
						'fragmentEntryLinks',
						fragmentEntryLink.fragmentEntryLinkId
					],
					fragmentEntryLink
				);

				if (!action.instanceable) {
					const widgetPath = getWidgetPath(
						nextState.widgets,
						action.portletId
					);

					nextState = setIn(nextState, [...widgetPath, 'used'], true);
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
 * @param {string} addPortletURL
 * @param {string} portletId
 * @param {string} classNameId
 * @param {string} classPK
 * @param {string} portletNamespace
 * @param {string} segmentsExperienceId
 * @return {object}
 * @review
 */
function _addPortlet(
	addPortletURL,
	portletId,
	classNameId,
	classPK,
	portletNamespace,
	segmentsExperienceId
) {
	const formData = new FormData();

	formData.append(`${portletNamespace}portletId`, portletId);
	formData.append(`${portletNamespace}classNameId`, classNameId);
	formData.append(`${portletNamespace}classPK`, classPK);
	formData.append(
		`${portletNamespace}segmentsExperienceId`,
		segmentsExperienceId
	);

	return fetch(addPortletURL, {
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
				content: response.content,
				editableValues: editableValuesMigrator(
					response.editableValues,
					prefixSegmentsExperienceId(segmentsExperienceId)
				),
				fragmentEntryLinkId: response.fragmentEntryLinkId,
				name: response.name
			};
		});
}

export {addPortletReducer};
