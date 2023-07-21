/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React from 'react';

import {ENTER} from '../../utils/key-constants.es';

class LocalizedDropdown extends React.Component {
	static defaultProps = {
		availableLanguages: []
	};

	static propTypes = {
		availableLanguages: PropTypes.array,
		initialLang: PropTypes.string,
		initialOpen: PropTypes.bool
	};

	constructor(props) {
		super(props);
		const {initialLang, initialOpen} = props;
		this.state = {
			currentLangKey: keyLangToLanguageTag(initialLang),
			currentLangTag: keyLangToLanguageTag(initialLang, false),
			open: initialOpen
		};
	}

	_handleButtonClick = () => {
		this.setState(prevState => ({
			open: !prevState.open
		}));
	};

	_handleButtonBlur = () => {
		if (this.state.open) {
			this.timer = setTimeout(() => {
				this.setState(() => ({
					open: false
				}));
			}, 200);
		}
	};

	_changeLanguage = langKey => {
		this.setState(
			{
				currentLangKey: keyLangToLanguageTag(langKey),
				currentLangTag: keyLangToLanguageTag(langKey, false),
				open: false
			},
			() => this.props.onLanguageChange(langKey)
		);
	};

	_handleItemFocus = () => {
		clearTimeout(this.timer);
	};

	_handleLanguageClick = langKey => () => this._changeLanguage(langKey);

	_handleLanguageKeyboard = langKey => e => {
		if (e.keyCode === ENTER) {
			this._changeLanguage(langKey);
		}
	};

	render() {
		const {currentLangKey, currentLangTag, open} = this.state;
		const {availableLanguages, defaultLang} = this.props;

		return (
			<div
				className={`dropdown postion-relative lfr-icon-menu ${
					open ? 'open' : ''
				}`}
			>
				<button
					aria-expanded="false"
					aria-haspopup="true"
					className="btn btn-monospaced btn-secondary dropdown-toggle"
					data-testid="localized-dropdown-button"
					onBlur={this._handleButtonBlur}
					onClick={this._handleButtonClick}
					role="button"
					title=""
					type="button"
				>
					<span className="inline-item">
						<ClayIcon
							key={currentLangKey}
							symbol={currentLangKey}
						/>
					</span>
					<span className="btn-section">{currentLangTag}</span>
				</button>

				{open && (
					<ul className="d-block dropdown-menu" role="menu">
						{availableLanguages.map(entry => {
							const {hasValue, key} = entry;

							return (
								<li
									key={key}
									onBlur={this._handleButtonBlur}
									onClick={this._handleLanguageClick(key)}
									onFocus={this._handleItemFocus}
									onKeyDown={this._handleLanguageKeyboard(
										key
									)}
									role="presentation"
								>
									<span
										className="dropdown-item lfr-icon-item palette-item taglib-icon"
										role="menuitem"
										tabIndex="0"
										target="_self"
									>
										<span className="inline-item inline-item-before">
											<ClayIcon
												symbol={keyLangToLanguageTag(
													key
												)}
											/>
										</span>
										<span className="taglib-text-icon">
											{keyLangToLanguageTag(key, false)}
											{defaultLang === key && (
												<span className="label label-info ml-1">
													{Liferay.Language.get(
														'default-value'
													)}
												</span>
											)}
											{defaultLang !== key &&
												(hasValue ? (
													<span className="label label-success ml-1">
														{Liferay.Language.get(
															'translated'
														)}
													</span>
												) : (
													<span className="label label-warning ml-1">
														{Liferay.Language.get(
															'untranslated'
														)}
													</span>
												))}
										</span>
									</span>
								</li>
							);
						})}
					</ul>
				)}
			</div>
		);
	}
}

/**
 * Helper to deal with the differnce in language keys for
 * human reading, svg consumption and keys
 *
 * @param {string} [keyLang='']
 * @param {boolean} [lowercase=true]
 * @returns {string}
 */
function keyLangToLanguageTag(keyLang = '', lowercase = true) {
	let langTag = keyLang.replace(/_/g, '-');
	if (lowercase) {
		langTag = langTag.toLowerCase();
	}

	return langTag;
}

export default LocalizedDropdown;
