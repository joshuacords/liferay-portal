/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function() {
	var STR_FILE_ENTRY_RETURN_TYPE =
		'com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType';

	var TPL_AUDIO_SCRIPT =
		'boundingBox: "#" + mediaId,' + 'oggUrl: "{oggUrl}",' + 'url: "{url}"';

	var TPL_VIDEO_SCRIPT =
		'boundingBox: "#" + mediaId,' +
		'height: {height},' +
		'ogvUrl: "{ogvUrl}",' +
		'poster: "{poster}",' +
		'url: "{url}",' +
		'width: {width}';

	var defaultVideoHeight = 300;
	var defaultVideoWidth = 400;

	CKEDITOR.plugins.add('itemselector', {
		_bindBrowseButton(
			editor,
			dialogDefinition,
			tabName,
			commandName,
			targetField
		) {
			var tab = dialogDefinition.getContents(tabName);

			if (tab) {
				var browseButton = tab.get('browse');

				if (browseButton) {
					browseButton.onClick = function() {
						editor.execCommand(commandName, newVal => {
							dialogDefinition.dialog.setValueOf(
								tabName,
								targetField,
								newVal
							);
						});
					};
				}
			}
		},

		_commitAudioValue(value, node) {
			var instance = this;

			node.setAttribute('data-document-url', value);

			var audioUrl = Liferay.Util.addParams(
				'audioPreview=1&type=mp3',
				value
			);

			node.setAttribute('data-audio-url', audioUrl);

			var audioOggUrl = Liferay.Util.addParams(
				'audioPreview=1&type=ogg',
				value
			);

			node.setAttribute('data-audio-ogg-url', audioOggUrl);

			return instance._audioTPL.output({
				oggUrl: audioOggUrl,
				url: audioUrl
			});
		},

		_commitMediaValue(value, editor, type) {
			var instance = this;

			var mediaPlugin = editor.plugins.media;

			if (mediaPlugin) {
				var eventName = editor.name + 'selectItem';

				Liferay.Util.getWindow(eventName).onceAfter('destroy', () => {
					mediaPlugin.onOkCallback(
						{
							commitContent: instance._getCommitMediaValueFn(
								value,
								editor,
								type
							)
						},
						editor,
						type
					);
				});
			}
		},

		_commitVideoValue(value, node, extraStyles) {
			var instance = this;

			node.setAttribute('data-document-url', value);

			var videoUrl = Liferay.Util.addParams(
				'videoPreview=1&type=mp4',
				value
			);

			node.setAttribute('data-video-url', videoUrl);

			var videoOgvUrl = Liferay.Util.addParams(
				'videoPreview=1&type=ogv',
				value
			);

			node.setAttribute('data-video-ogv-url', videoOgvUrl);

			var videoHeight = defaultVideoHeight;

			node.setAttribute('data-height', videoHeight);

			var videoWidth = defaultVideoWidth;

			node.setAttribute('data-width', videoWidth);

			var poster = Liferay.Util.addParams('videoThumbnail=1', value);

			node.setAttribute('data-poster', poster);

			extraStyles.backgroundImage = 'url(' + poster + ')';
			extraStyles.height = videoHeight + 'px';
			extraStyles.width = videoWidth + 'px';

			return instance._videoTPL.output({
				height: videoHeight,
				ogvUrl: videoOgvUrl,
				poster,
				url: videoUrl,
				width: videoWidth
			});
		},

		_getCommitMediaValueFn(value, editor, type) {
			var instance = this;

			var commitValueFn = function(node, extraStyles) {
				var mediaScript;

				if (type === 'audio') {
					mediaScript = instance._commitAudioValue(
						value,
						node,
						extraStyles
					);
				}
				else if (type === 'video') {
					mediaScript = instance._commitVideoValue(
						value,
						node,
						extraStyles
					);
				}

				var mediaPlugin = editor.plugins.media;

				if (mediaPlugin) {
					mediaPlugin.applyMediaScript(node, type, mediaScript);
				}
			};

			return commitValueFn;
		},

		_getItemSelectorDialog(editor, url, callback) {
			var instance = this;

			var eventName = editor.name + 'selectItem';

			var itemSelectorDialog = instance._itemSelectorDialog;

			if (itemSelectorDialog) {
				itemSelectorDialog.set('eventName', eventName);
				itemSelectorDialog.set('url', url);
				itemSelectorDialog.set('zIndex', CKEDITOR.getNextZIndex());

				callback(itemSelectorDialog);
			}
			else {
				AUI().use('liferay-item-selector-dialog', A => {
					itemSelectorDialog = new A.LiferayItemSelectorDialog({
						eventName,
						url,
						zIndex: CKEDITOR.getNextZIndex()
					});

					itemSelectorDialog.on('disposed', () => {
						instance._itemSelectorDialog = null;
					});

					instance._itemSelectorDialog = itemSelectorDialog;

					callback(itemSelectorDialog);
				});
			}
		},

		_getItemSrc(editor, selectedItem) {
			var itemSrc = selectedItem.value;

			if (selectedItem.returnType === STR_FILE_ENTRY_RETURN_TYPE) {
				try {
					var itemValue = JSON.parse(selectedItem.value);

					itemSrc = editor.config.attachmentURLPrefix
						? editor.config.attachmentURLPrefix +
						  encodeURIComponent(itemValue.title)
						: itemValue.url;
				}
				catch (e) {}
			}

			return itemSrc;
		},

		_isEmptySelection(editor) {
			var selection = editor.getSelection();

			var ranges = selection.getRanges();

			return (
				selection.getType() === CKEDITOR.SELECTION_NONE ||
				(ranges.length === 1 && ranges[0].collapsed)
			);
		},

		_onSelectedAudioChange(editor, callback, event) {
			var instance = this;

			var selectedItem = event.newVal;

			if (selectedItem) {
				var audioSrc = instance._getItemSrc(editor, selectedItem);

				if (audioSrc) {
					if (typeof callback === 'function') {
						callback(audioSrc);
					}
					else {
						instance._commitMediaValue(audioSrc, editor, 'audio');
					}
				}
			}
		},

		_onSelectedImageChange(editor, callback, event) {
			var instance = this;

			var selectedItem = event.newVal;

			if (selectedItem) {
				var eventName = editor.name + 'selectItem';
				var imageSrc = instance._getItemSrc(editor, selectedItem);

				Liferay.Util.getWindow(eventName).onceAfter('destroy', () => {
					if (imageSrc) {
						if (typeof callback === 'function') {
							callback(imageSrc, selectedItem);
						}
						else {
							var elementOuterHtml =
								'<img src="' + imageSrc + '">';

							if (instance._isEmptySelection(editor)) {
								var breakMarkup = '<br />';
								elementOuterHtml =
									elementOuterHtml + breakMarkup;
							}

							editor.insertHtml(elementOuterHtml);

							editor.focus();
						}
					}
				});
			}
		},

		_onSelectedLinkChange(editor, callback, event) {
			var selectedItem = event.newVal;

			if (selectedItem) {
				var eventName = editor.name + 'selectItem';

				var linkUrl = selectedItem.value;

				Liferay.Util.getWindow(eventName).onceAfter('destroy', () => {
					if (typeof callback === 'function') {
						callback(linkUrl, selectedItem);
					}
				});
			}
		},

		_onSelectedVideoChange(editor, callback, event) {
			var instance = this;

			var selectedItem = event.newVal;

			if (selectedItem) {
				var videoSrc = instance._getItemSrc(editor, selectedItem);

				if (videoSrc) {
					if (typeof callback === 'function') {
						callback(videoSrc);
					}
					else {
						instance._commitMediaValue(videoSrc, editor, 'video');
					}
				}
			}
		},

		init(editor) {
			var instance = this;

			instance._audioTPL = new CKEDITOR.template(TPL_AUDIO_SCRIPT);
			instance._videoTPL = new CKEDITOR.template(TPL_VIDEO_SCRIPT);

			editor.addCommand('audioselector', {
				canUndo: false,
				exec(editor, callback) {
					var onSelectedAudioChangeFn = AUI().bind(
						'_onSelectedAudioChange',
						instance,
						editor,
						callback
					);

					instance._getItemSelectorDialog(
						editor,
						editor.config.filebrowserAudioBrowseUrl,
						itemSelectorDialog => {
							itemSelectorDialog.once(
								'selectedItemChange',
								onSelectedAudioChangeFn
							);
							itemSelectorDialog.open();
						}
					);
				}
			});

			editor.addCommand('imageselector', {
				canUndo: false,
				exec(editor, callback) {
					var onSelectedImageChangeFn = AUI().bind(
						'_onSelectedImageChange',
						instance,
						editor,
						callback
					);

					instance._getItemSelectorDialog(
						editor,
						editor.config.filebrowserImageBrowseUrl,
						itemSelectorDialog => {
							itemSelectorDialog.once(
								'selectedItemChange',
								onSelectedImageChangeFn
							);
							itemSelectorDialog.open();
						}
					);
				}
			});

			editor.addCommand('linkselector', {
				canUndo: false,
				exec(editor, callback) {
					var onSelectedLinkChangeFn = AUI().bind(
						'_onSelectedLinkChange',
						instance,
						editor,
						callback
					);

					instance._getItemSelectorDialog(
						editor,
						editor.config.filebrowserBrowseUrl,
						itemSelectorDialog => {
							itemSelectorDialog.once(
								'selectedItemChange',
								onSelectedLinkChangeFn
							);
							itemSelectorDialog.open();
						}
					);
				}
			});

			editor.addCommand('videoselector', {
				canUndo: false,
				exec(editor, callback) {
					var onSelectedVideoChangeFn = AUI().bind(
						'_onSelectedVideoChange',
						instance,
						editor,
						callback
					);

					instance._getItemSelectorDialog(
						editor,
						editor.config.filebrowserVideoBrowseUrl,
						itemSelectorDialog => {
							itemSelectorDialog.once(
								'selectedItemChange',
								onSelectedVideoChangeFn
							);
							itemSelectorDialog.open();
						}
					);
				}
			});

			if (editor.ui.addButton) {
				editor.ui.addButton('ImageSelector', {
					command: 'imageselector',
					icon: instance.path + 'assets/image.png',
					label: editor.lang.common.image
				});

				editor.ui.addButton('AudioSelector', {
					command: 'audioselector',
					icon: instance.path + 'assets/audio.png',
					label: Liferay.Language.get('audio')
				});

				editor.ui.addButton('VideoSelector', {
					command: 'videoselector',
					icon: instance.path + 'assets/video.png',
					label: Liferay.Language.get('video')
				});
			}

			CKEDITOR.on('dialogDefinition', event => {
				var dialogName = event.data.name;

				var dialogDefinition = event.data.definition;

				if (dialogName === 'audio') {
					instance._bindBrowseButton(
						event.editor,
						dialogDefinition,
						'info',
						'audioselector',
						'url'
					);
				}
				else if (dialogName === 'image') {
					instance._bindBrowseButton(
						event.editor,
						dialogDefinition,
						'Link',
						'linkselector',
						'txtUrl'
					);

					dialogDefinition.getContents('info').remove('browse');

					dialogDefinition.onLoad = function() {
						this.getContentElement('info', 'txtUrl')
							.getInputElement()
							.setAttribute('readOnly', true);
					};
				}
				else if (dialogName === 'image2') {
					instance._bindBrowseButton(
						event.editor,
						dialogDefinition,
						'info',
						'imageselector',
						'src'
					);
				}
				else if (dialogName === 'video') {
					instance._bindBrowseButton(
						event.editor,
						dialogDefinition,
						'info',
						'videoselector',
						'poster'
					);
				}
				else if (dialogName === 'link') {
					instance._bindBrowseButton(
						event.editor,
						dialogDefinition,
						'info',
						'linkselector',
						'url'
					);
				}
			});

			editor.once('destroy', () => {
				if (instance._itemSelectorDialog) {
					instance._itemSelectorDialog.destroy();
				}
			});
		}
	});
})();
