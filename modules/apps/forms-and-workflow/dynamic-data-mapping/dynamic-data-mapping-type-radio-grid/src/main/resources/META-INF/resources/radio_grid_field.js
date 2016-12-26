AUI.add(
	'liferay-ddm-form-field-radio-grid',
	function(A) {
		var RadioGridField = A.Component.create(
			{
				ATTRS: {
					rows: {
						state: true,
						validator: Array.isArray,
						value: []
					},

					// columns: {
					// 	state: true,
					// 	validator: Array.isArray,
					// 	value: []
					// },

					type: {
						value: 'radio_grid'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-radio-grid',

				prototype: {
					getInputNode: function() {
						var instance = this;

						var container = instance.get('container');

						var inputNode = container.one('input[type="radio"]:checked');

						if (inputNode === null) {
							inputNode = container.one('input[type="radio"]');
						}

						return inputNode;
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

						var inputNode = instance.getInputNode();

						var value = '';

						if (inputNode.attr('checked')) {
							value = inputNode.val();
						}

						return value;
					},

					setValue: function(value) {
						var instance = this;

						var container = instance.get('container');

						var radiosNodeList = container.all('input[type="radio"]');

						radiosNodeList.removeAttribute('checked');

						var radioToCheck = radiosNodeList.filter(
							function(node) {
								return node.val() === value;
							}
						).item(0);

						if (radioToCheck) {
							radioToCheck.attr('checked', true);
						}
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