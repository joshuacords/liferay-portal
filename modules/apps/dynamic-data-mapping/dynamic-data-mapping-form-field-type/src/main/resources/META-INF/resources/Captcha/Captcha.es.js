/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '../FieldBase/FieldBase.es';

import './CaptchaRegister.soy';

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './Captcha.soy';

/**
 * Captcha.
 * @extends Component
 */

class Captcha extends Component {
	rendered() {
		if (window.grecaptcha) {
			window.grecaptcha.ready(() => {
				try {
					window.grecaptcha.reset(this._getRecaptchaIndex());
				}
				catch (e) {
					console.warn('Could not reset reCAPTCHA.');
				}
			});
		}

		const DDM_PREFIX = 'ddm$$';

		const namespace = this.name.substring(0, this.name.indexOf(DDM_PREFIX));

		const refreshCaptcha = document.getElementById(
			namespace + 'refreshCaptcha'
		);

		if (refreshCaptcha) {
			const captcha = document.getElementById(namespace + 'captcha');

			if (captcha && captcha.src) {
				let url = captcha.src;

				refreshCaptcha.addEventListener('click', () => {
					const TIMESTAMP_REGEX = /(t=).*?($)/;
					const REGEX_FIRST_MATCH = '$1';

					url = url.replace(
						TIMESTAMP_REGEX,
						REGEX_FIRST_MATCH + Date.now()
					);

					captcha.setAttribute('src', url);
				});
			}
		}
	}

	shouldUpdate() {
		return false;
	}

	_getRecaptchaIndex() {
		const recaptchaElements = document.getElementsByClassName(
			'g-recaptcha'
		);

		for (let index = 0; index < recaptchaElements.length; index++) {
			const parentElement = recaptchaElements[index].parentElement;

			if (
				parentElement &&
				parentElement.getAttribute('data-field-name') === this.name
			) {
				return index;
			}
		}

		return 0;
	}
}

Soy.register(Captcha, templates);

Captcha.STATE = {
	/**
	 * @default false
	 * @memberof FieldBase
	 * @type {?bool}
	 */

	evaluable: Config.bool().value(false),

	/**
	 * @default undefined
	 * @memberof Captcha
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string(),

	/**
	 * @default 'captcha'
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	type: Config.string().value('captcha')
};

export default Captcha;
