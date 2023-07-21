/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import AudioPreviewer from '../src/main/resources/META-INF/resources/preview/js/AudioPreviewer.es';

let component;

describe('document-library-preview-audio', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('renders an audio player', () => {
		component = new AudioPreviewer({
			audioMaxWidth: 520,
			audioSources: [
				{
					type: 'audio/ogg',
					url: '//audio.ogg'
				},
				{
					type: 'audio/mp3',
					url: '//audio.mp3'
				}
			]
		});

		expect(component).toMatchSnapshot();
	});
});
