/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {PortletBase} from 'frontend-js-web';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import getConnectedComponent from '../../store/ConnectedComponent.es';
import templates from './CreateContentForm.soy';

/**
 * CreateContentForm
 */
class CreateContentForm extends PortletBase {
	/**
	 * @inheritdoc
	 * @review
	 */
	syncGetContentStructuresURL() {
		if (this.getContentStructuresURL) {
			this._structures = null;

			this.fetch(this.getContentStructuresURL)
				.then(response => response.json())
				.then(response => {
					this._structures = response;
				});
		}
	}

	/**
	 * @param {Event} event
	 */
	_handleFormChange(event) {
		const form = event.delegateTarget;

		const [ddmStructureOption] = form.elements.namedItem(
			'ddmStructure'
		).selectedOptions;

		this._formValid = form.checkValidity();

		this.emit('formChanged', {
			ddmStructure: {
				id: ddmStructureOption.value,
				label: ddmStructureOption.innerText
			},

			title: form.elements.namedItem('title').value,
			valid: this._formValid
		});
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
CreateContentForm.STATE = {
	/**
	 * @default true
	 * @instance
	 * @memberof CreateContentForm
	 * @private
	 * @review
	 * @type {boolean}
	 */
	_formValid: Config.bool()
		.internal()
		.value(true),

	/**
	 * List of available structure types
	 * @default null
	 * @instance
	 * @memberOf CreateContentForm
	 * @private
	 * @review
	 * @type {Array<{
	 *   id: !string,
	 *   label: !string
	 * }>}
	 */
	_structureTypes: Config.arrayOf(
		Config.shapeOf({
			id: Config.string().required(),
			label: Config.string().required()
		})
	)
		.internal()
		.value([
			{
				id: 'existing',
				label: Liferay.Language.get('existing-structure')
			}
		]),

	/**
	 * List of available structures
	 * @default null
	 * @instance
	 * @memberOf CreateContentForm
	 * @private
	 * @review
	 * @type {Array<{
	 *   id: !string,
	 *   label: !string
	 * }>}
	 */
	_structures: Config.arrayOf(
		Config.shapeOf({
			id: Config.string().required(),
			label: Config.string().required()
		})
	)
		.internal()
		.value(null),

	/**
	 * Selected structure ID
	 * @default ''
	 * @instance
	 * @memberOf CreateContentForm
	 * @private
	 * @review
	 * @type {string}
	 */
	selectedStructureId: Config.string().value('')
};

const ConnectedCreateContentForm = getConnectedComponent(CreateContentForm, [
	'getContentStructuresURL',
	'portletNamespace'
]);

Soy.register(ConnectedCreateContentForm, templates);

export {ConnectedCreateContentForm, CreateContentForm};
export default ConnectedCreateContentForm;
