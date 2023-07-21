/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from 'clay-button';
import Component from 'metal-jsx';
import {Config} from 'metal-state';

import Notifications from '../../util/Notifications.es';

class PreviewButton extends Component {
	preview() {
		const {resolvePreviewURL} = this.props;

		return resolvePreviewURL()
			.then(previewURL => {
				window.open(previewURL, '_blank');

				return previewURL;
			})
			.catch(() => {
				Notifications.showError(
					Liferay.Language.get('your-request-failed-to-complete')
				);
			});
	}

	render() {
		const {spritemap} = this.props;

		return (
			<ClayButton
				elementClasses={'btn-default'}
				events={{
					click: this._handleButtonClicked.bind(this)
				}}
				label={Liferay.Language.get('preview-form')}
				ref={'button'}
				spritemap={spritemap}
				style={'link'}
			/>
		);
	}

	_handleButtonClicked() {
		this.preview();
	}
}

PreviewButton.PROPS = {
	resolvePreviewURL: Config.func().required(),
	spritemap: Config.string().required()
};

export default PreviewButton;
