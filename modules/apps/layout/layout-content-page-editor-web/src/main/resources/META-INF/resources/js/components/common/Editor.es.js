/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {EventHandler} from 'metal-events';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import useSelector from '../../store/hooks/useSelector.es';

const Editor = props => {
	const editorConfig = useSelector(
		state => state.defaultEditorConfigurations.text.editorConfig
	);
	const portletNamespace = useSelector(state => state.portletNamespace);

	const [editor, setEditor] = useState(null);

	const {autoFocus, initialValue, onChange} = props;
	const wrapperRef = useRef(null);

	useEffect(() => {
		if (editor) {
			const nativeEditor = editor.get('nativeEditor');

			if (!nativeEditor.getData() || !initialValue) {
				nativeEditor.setData(initialValue);
			}
		}
	}, [editor, initialValue]);

	useEffect(() => {
		const editorEventHandler = new EventHandler();

		if (editor && onChange) {
			const nativeEditor = editor.get('nativeEditor');

			editorEventHandler.add(
				nativeEditor.on('change', () =>
					onChange(nativeEditor.getData())
				)
			);

			editorEventHandler.add(
				nativeEditor.on('actionPerformed', () =>
					onChange(nativeEditor.getData())
				)
			);
		}

		return () => {
			editorEventHandler.removeAllListeners();
			editorEventHandler.dispose();
		};
	}, [editor, onChange]);

	useEffect(() => {
		const newEditor = AlloyEditor.editable(wrapperRef.current, {
			...editorConfig,
			enterMode: 1,
			startupFocus: autoFocus
		});

		let ready = false;

		const instanceReadyEventHandler = newEditor
			.get('nativeEditor')
			.once('instanceReady', () => {
				ready = true;

				setEditor(newEditor);
			});

		return () => {
			try {
				if (ready) {
					newEditor.destroy();
					setEditor(null);
				}
				else {
					instanceReadyEventHandler.removeListener();

					newEditor.get('nativeEditor').once('instanceReady', () => {
						newEditor.destroy();
					});
				}
			}
			catch (_err) {
				// https://github.com/liferay/alloy-editor/issues/1306
			}
		};
	}, [autoFocus, editorConfig]);

	return (
		<div
			className="alloy-editor-container"
			id={`${portletNamespace}${props.id}`}
		>
			<div
				className="alloy-editor form-control form-control-sm fragments-editor__editor"
				contentEditable={false}
				data-placeholder={props.placeholder}
				data-required={false}
				id={`${portletNamespace}${props.id}`}
				name={props.id}
				ref={wrapperRef}
			/>
			<div className="alloy-editor-placeholder">{props.placeholder}</div>
		</div>
	);
};

Editor.defaultProps = {
	autoFocus: false
};

Editor.propTypes = {
	autoFocus: PropTypes.bool,
	editorConfig: PropTypes.object,
	id: PropTypes.string.isRequired,
	initialValue: PropTypes.string.isRequired,
	onChange: PropTypes.func.isRequired,
	placeholder: PropTypes.string.isRequired
};

export {Editor};
export default Editor;
