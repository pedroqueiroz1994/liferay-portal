AUI.add(
	'liferay-ddm-form-renderer-wizard',
	function(A) {
		var Lang = A.Lang;

		var TPL_WIZARD_ITEM = '<li class="{state} multi-step-item multi-step-item-expand">' +
		'<div class="multi-step-divider"></div>' +
		'<div class="multi-step-indicator">' +
		'<div class="multi-step-indicator-label">{title}</div>' +
		'<a class="multi-step-icon" data-multi-step-icon="{number}" href="#1"></a>' +
		'</div>' +
		'</li>';

		var TPL_DROPDOWN_ACTIVE_ELEMENT = '<a class="{state} complete dropdown-item" href="#1">' +
										  '{number}. {title}' +
										  '<span aria-hidden="true" class="dropdown-item-indicator">' +
										  '<svg aria-hidden="true" class="lexicon-icon lexicon-icon-check">' +
										  '<use xlink:href="/vendor/lexicon/icons.svg#check" /> ' +
										  '</svg>' +
										  '</span>' +
										  '</a>';

		var Wizard = A.Component.create(
			{
				ATTRS: {
					allowNavigation: {
						value: false
					},

					items: {
						value: []
					},

					itemsNodeList: {
						valueFn: '_valueItemsNodeList'
					},

					selected: {
						value: 0
					}
				},

				EXTENDS: A.Widget,

				HTML_PARSER: {
					items: function(boundingBox) {
						var instance = this;

						var items = [];

						// if (boundingBox.all('li') > 7) {
						// 	//put logic to restrict to 7 nodes at the screen. <3
						// }
						boundingBox.all('li').each(
							function(itemNode) {
								var title = itemNode.one('.multi-step-indicator-label').text();

								var state = itemNode.attr('class');

								items.push(
									{
										state: state,
										title: title
									}
								);
							}
						);

						return items;
					},

					itemsNodeList: function(boundingBox) {
						var instance = this;

						return boundingBox.all('li');
					}
				},

				NAME: 'liferay-ddm-form-renderer-wizard',

				UI_ATTRS: ['items'],

				prototype: {
					CONTENT_TEMPLATE: '<ol class="multi-step-nav multi-step-nav-collapse-sm multi-step-indicator-label-top"></ol>',

					renderUI: function() {
						var instance = this;

						if (instance.get('allowNavigation')) {
							instance.get('contentBox').addClass('liferay-ddm-form-renderer-wizard-navigation-allowed');
						}
					},

					bindUI: function() {
						var instance = this;

						if (instance.get('allowNavigation')) {
							this._eventHandles = [
								instance.get('boundingBox').delegate('click', A.bind(instance._onClickItem, instance), 'li')
							];
						}

						instance.after('disabledChange', A.bind(instance._afterDisabledChange, instance));
						instance.on('selectedChange', A.bind(instance._afterSelectionChange, instance));
					},

					activate: function(index) {
						var instance = this;

						var itemNumber = index + 1;

						instance._setState(index, 'active');

						//provide a way to create a dropdown node to active item
						//`item` is the name of text element which i will use to type in checkboxes.

						// if (itemNumber > 7) {
						// 	A.Node.create(
						// 		Lang.sub(
						// 			TPL_DROPDOWN_ACTIVE_ELEMENT,
						// 			{
						// 				number: itemNumber,
						// 				state: item.state,
						// 				title: item.title
						// 			}
						// 		)
						// 	);
						// }
					},

					clear: function(index) {
						var instance = this;

						instance._setState(index, '');
					},

					clearAll: function() {
						var instance = this;

						var items = instance.get('items');

						items.forEach(
							function(item, index) {
								instance._setState(index, '');
							}
						);
					},

					complete: function(index) {
						var instance = this;

						instance._setState(index, 'complete');
					},

					_addItem: function(item) {
						var instance = this;

						var items = instance.get('items');

						items.push(item);

						instance.set('items', items);
					},

					_afterDisabledChange: function() {
						var instance = this;

						var itemsNode = instance.get('itemsNodeList');

						itemsNode.addClass('disabled');
					},

					_afterSelectionChange: function(event) {
						var instance = this;

						instance.clearAll();

						if (event.newVal > -1) {
							instance.activate(event.newVal);
						}
					},

					_getItemsNodeList: function(items) {
						var instance = this;

						return new A.NodeList(
							items.map(
								function(item, index) {
									return A.Node.create(
										Lang.sub(
											TPL_WIZARD_ITEM,
											{
												number: index + 1,
												state: item.state,
												title: item.title
											}
										)
									);
								}
							)
						);
					},

					_onClickItem: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						var items = instance.get('contentBox').all('li');

						var index = items.indexOf(currentTarget);

						if (instance.get('disabled')) {
							event.stopPropagation();
							event.preventDefault();
						}
						else {
							instance.set('selected', index);
						}
					},

					_removeItem: function(index) {
						var instance = this;

						var items = instance.get('items');

						items.splice(index, 1);

						instance.set('items', items);
					},

					_setState: function(index, state) {
						var instance = this;

						var items = instance.get('items');

						if (items[index]) {
							items[index].state = state;

							instance.set('items', items);
						}
					},

					_uiSetItems: function(val) {
						var instance = this;

						var contentBox = instance.get('contentBox');

						contentBox.empty();

						contentBox.append(instance._getItemsNodeList(val));
					},

					_valueItemsNodeList: function() {
						var instance = this;

						return instance._getItemsNodeList(instance.get('items'));
					}
				}
			}
		);

		Liferay.namespace('DDM.Renderer').Wizard = Wizard;
	},
	'',
	{
		requires: ['aui-component', 'aui-node', 'widget']
	}
);