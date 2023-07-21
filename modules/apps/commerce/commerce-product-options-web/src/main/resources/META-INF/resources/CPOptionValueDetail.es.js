/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';
import Component from 'metal-component';
import {globalEval} from 'metal-dom';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './CPOptionValueDetail.soy';

/**
 * CPOptionValueDetail
 *
 */

class CPOptionValueDetail extends Component {
	constructor(opt_config, opt_parentElement) {
		super(opt_config, opt_parentElement);

		this.on('cpOptionValueIdChanged', this._handleCPOptionValueChange);
	}

	rendered() {
		this.loadOptionValueDetail(this.cpOptionValueId);
	}

	loadOptionValueDetail(cpOptionValueId) {
		var instance = this;

		const optionValueDetail = this.element.querySelector(
			'.option-value-detail'
		);

		var url = new URL(this.optionValueURL);

		url.searchParams.append(
			this.namespace + 'cpOptionValueId',
			cpOptionValueId
		);
		url.searchParams.set('p_auth', Liferay.authToken);

		fetch(url, {
			credentials: 'include',
			headers: new Headers({'x-csrf-token': Liferay.authToken}),
			method: 'GET'
		})
			.then(response => response.text())
			.then(text => {
				optionValueDetail.innerHTML = text;

				globalEval.runScriptsInElement(optionValueDetail);

				var name = optionValueDetail.querySelector(
					'#' + instance.namespace + 'optionValueName'
				);

				if (name) {
					name.addEventListener('keyup', event => {
						var target = event.target;

						instance.emit('nameChange', target.value);
					});
				}
			});
	}

	_handleCPOptionValueChange(event) {
		this.loadOptionValueDetail(event.newVal);
	}

	_handleSaveOptionValue() {
		var instance = this;

		AUI().use('aui-base', 'aui-form-validator', 'liferay-form', A => {
			var hasErrors = false;

			const form = instance.element.querySelector(
				'.option-value-detail form'
			);

			var liferayForm = Liferay.Form.get(form.getAttribute('id'));

			if (liferayForm) {
				var validator = liferayForm.formValidator;

				if (A.instanceOf(validator, A.FormValidator)) {
					validator.validate();

					hasErrors = validator.hasErrors();

					if (hasErrors) {
						validator.focusInvalidField();
					}
				}
			}

			if (!hasErrors) {
				instance._saveOptionValue();
			}
		});
	}

	_handleCancel() {
		this.emit('cancel');
	}

	_handleDeleteOptionValue() {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			this._deleteOptionValue();
		}
	}

	_deleteOptionValue() {
		const form = this.element.querySelector('.option-value-detail form');

		form.querySelector('[name=' + this.namespace + 'cmd]').value = 'delete';

		var formData = new FormData(form);

		formData.set('p_auth', Liferay.authToken);

		fetch(form.action, {
			body: formData,
			credentials: 'include',
			headers: new Headers({'x-csrf-token': Liferay.authToken}),
			method: 'POST'
		})
			.then(response => response.json())
			.then(jsonResponse => {
				this.emit('optionValueDeleted', jsonResponse);
			});
	}

	_saveOptionValue() {
		const form = this.element.querySelector('.option-value-detail form');

		form.querySelector(
			'[name=' + this.namespace + 'cpOptionId]'
		).value = this.cpOptionId;

		var formData = new FormData(form);

		formData.set('p_auth', Liferay.authToken);

		fetch(form.action, {
			body: formData,
			credentials: 'include',
			headers: new Headers({'x-csrf-token': Liferay.authToken}),
			method: 'POST'
		})
			.then(response => response.json())
			.then(jsonResponse => {
				this.emit('optionValueSaved', jsonResponse);
			});
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

CPOptionValueDetail.STATE = {
	cpOptionId: Config.string().required(),
	cpOptionValueId: Config.string().required(),
	namespace: Config.string().required(),
	optionValueURL: Config.string().required(),
	pathThemeImages: Config.string().required()
};

// Register component

Soy.register(CPOptionValueDetail, templates);

export default CPOptionValueDetail;
