/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClaySticker from '@clayui/sticker';
import PropType from 'prop-types';
import React from 'react';

import {getValueFromItem} from '../../utilities/index';

function ImageRenderer(props) {
	return (
		<div className="row">
			<div className="col-auto">
				{typeof props.value === 'string' ? (
					<ClaySticker
						shape={props.options.shape || 'rounded'}
						size={props.options.size || 'xl'}
					>
						<div className="sticker-overlay">
							<img
								alt={
									props.options.label ||
									(props.options.labelKey
										? getValueFromItem(
												props.itemData,
												props.options.labelKey
										  )
										: Liferay.Language.get('thumbnail'))
								}
								className="sticker-img"
								src={props.value}
							/>
						</div>
					</ClaySticker>
				) : (
					<ClaySticker
						shape={
							props.options.shape ||
							props.value.shape ||
							'rounded'
						}
						size={props.options.size || props.value.size || 'xl'}
					>
						<div className="sticker-overlay">
							<img
								alt={
									props.value.alt ||
									Liferay.Language.get('thumbnail')
								}
								className="sticker-img"
								src={props.value.src}
							/>
						</div>
					</ClaySticker>
				)}
			</div>
		</div>
	);
}

ImageRenderer.propTypes = {
	options: PropType.shape({
		label: PropType.string,
		labelKey: PropType.oneOfType([PropType.array, PropType.string]),
		shape: PropType.string,
		size: PropType.string
	}),
	value: PropType.oneOfType([
		PropType.shape({
			alt: PropType.string,
			shape: PropType.string,
			size: PropType.string,
			src: PropType.string.isRequired
		}),
		PropType.string
	])
};

export default ImageRenderer;
