/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import {setValue} from '../../util/i18n.es';
import templates from './SuccessPage.soy';

class SuccessPage extends Component {
	prepareStateForRender(state) {
		const {editingLanguageId, successPageSettings} = this;
		const {body, title} = successPageSettings;

		return {
			...state,
			body: (body && body[editingLanguageId]) || '',
			title: (title && title[editingLanguageId]) || ''
		};
	}

	shouldUpdate(changes) {
		const {editingLanguageId} = changes;

		return (
			editingLanguageId &&
			editingLanguageId.newVal !== editingLanguageId.prevVal
		);
	}

	_handleSuccessPageUpdated(event) {
		const {dispatch, store} = this.context;
		const {editingLanguageId} = store.props;
		const {delegateTarget} = event;
		const {
			dataset: {setting},
			value
		} = delegateTarget;
		const {successPageSettings} = this;

		dispatch(
			'successPageChanged',
			setValue(successPageSettings, editingLanguageId, setting, value)
		);
	}
}

SuccessPage.STATE = {
	editingLanguageId: Config.string(),

	/**
	 * @instance
	 * @memberof SuccessPage
	 * @type {?object}
	 */

	successPageSettings: Config.object().value({
		body: {},
		title: {}
	})
};

Soy.register(SuccessPage, templates);

export default SuccessPage;
