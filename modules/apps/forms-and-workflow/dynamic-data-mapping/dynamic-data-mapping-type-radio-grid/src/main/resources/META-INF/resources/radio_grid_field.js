AUI.add(
	'liferay-ddm-form-field-radio-grid',
	function(A) {
		var RadioGridField = A.Component.create(
			{
				ATTRS: {
					rows: {
						setter: '_setRows',
						state: true,
						validator: Array.isArray,
						value: []
					},

					columns: {
						setter: '_setColumns',
						state: true,
						validator: Array.isArray,
						value: []
					},

					type: {
						value: 'radio_grid'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-radio-grid',

				prototype: {
					getGridRowsNodes: function() {
						var instance = this;

						var container = instance.get('container');

						var gridRowsNodes = container.all('tbody tr').get(0);

						return gridRowsNodes;
					},

					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							RadioGridField.superclass.getTemplateContext.apply(instance, arguments),
							{
								rows: instance.get('rows'),
								columns: instance.get('columns')
							}
						);
					},

					getValue: function() {
						var instance = this;

						var gridRowsNodes = instance.getGridRowsNodes();

						var value = new Array();

						gridRowsNodes.
							forEach(
								function(gridRowNode) {
									colValue = gridRowNode.all('td').one('input[type="radio"]:checked');
									if (colValue) {
										rowValue = gridRowNode.attr('name');
										value.push({
											"row": rowValue,
											"colValue": colValue.val()
										});
									}
								}
							);
						return value;
					},

					setValue: function(value) {
						var instance = this;

						// var container = instance.get('container');
						//
						// var radiosNodeList = container.all('input[type="radio"]');
						//
						// radiosNodeList.removeAttribute('checked');
						//
						// var radioToCheck = radiosNodeList.filter(
						// 	function(node) {
						// 		return node.val() === value;
						// 	}
						// ).item(0);
						//
						// if (radioToCheck) {
						// 	radioToCheck.attr('checked', true);
						// }
						var gridRowsNodes = instance.getGridRowsNodes();

						gridRowsNodes.
						forEach(
							function(gridRowNode) {
								radiosNodeList = gridRowNode.all('td').all('input[type="radio"]');
								radiosNodeList.attr('checked', true); //removeAttribute('checked');

						// 		var radioToCheck = radiosNodeList.filter(
						// 			function(node) {
						// 				return node.val() === value[];
						// 			}
						// 		).item(0);
						// 		checkedInput = gridRowNode.all('td').one('input[type="radio"]:checked');
						// 		answer = checkedInput ? checkedInput.val() : '';
							}
						);
					},

					_setColumns: function(columns) {
						var instance = this;

						instance._mapItemsLabels(columns);
					},

					_setRows: function(rows) {
						var instance = this;

						instance._mapItemsLabels(rows);
					},

					_mapItemsLabels: function(items) {
						var instance = this;

						items.forEach(
							function(item) {
								item.label = instance._getLocalizedLabel(item);
							}
						);
					},

					_getLocalizedLabel: function(option) {
						var defaultLanguageId = themeDisplay.getDefaultLanguageId();

						return option.label[defaultLanguageId] ? option.label[defaultLanguageId] : option.label;
					},

					showErrorMessage: function() {
						var instance = this;

						var container = instance.get('container');

						RadioGridField.superclass.showErrorMessage.apply(instance, arguments);

						container.all('.help-block').appendTo(container.one('.form-group'));
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').RadioGrid = RadioGridField;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-field']
	}
);