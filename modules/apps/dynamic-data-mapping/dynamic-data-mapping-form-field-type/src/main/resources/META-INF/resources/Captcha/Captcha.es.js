/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
