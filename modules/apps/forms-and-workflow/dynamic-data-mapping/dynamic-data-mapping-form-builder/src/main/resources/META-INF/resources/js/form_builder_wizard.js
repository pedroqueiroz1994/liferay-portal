AUI.add(
	'liferay-ddm-form-builder-wizard',
	function(A) {
		var TPL_WIZARD_SUCCESSPAGE = '<li class="{state} multi-step-item multi-step-item-expand" data-success-page="true">' +
				'<div class="multi-step-divider">' +
				'<div class="multi-step-indicator-label">{title}</div>' +
				//falta reescrever aqui em baixo!
				'<div class="progress-bar-success-item divider"></div>' +
				'</div>' +
			'</li>';

		var FormBuilderWizard = A.Component.create(
			{
				ATTRS: {
					successPage: {
						value: false
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Wizard,

				NAME: 'liferay-ddm-form-builder-wizard',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._activeSuccessPage = false;

						instance.after('successPageChange', A.bind(instance._afterSuccessPageChange, instance));
					},

					isSuccessPageSelected: function() {
						var instance = this;

						return instance._activeSuccessPage;
					},

					_afterSuccessPageChange: function(event) {
						var instance = this;

						var contentBox = instance.get('contentBox');

						contentBox.empty();

						var items = instance.get('items');

						contentBox.append(instance._getItemsNodeList(items));
					},

					_getItemsNodeList: function(items) {
						var instance = this;

						var itemsNodeList = FormBuilderWizard.superclass._getItemsNodeList.apply(instance, arguments);

						if (instance.get('successPage')) {
							itemsNodeList.push(
								A.Node.create(
									A.Lang.sub(
										TPL_WIZARD_SUCCESSPAGE,
										{
											state: instance._activeSuccessPage ? 'active' : '',
											title: 'Success Page'
										}
									)
								)
							);
						}

						return itemsNodeList;
					},

					_setState: function(index, state) {
						var instance = this;

						var items = instance.get('items');

						if (items[index]) {
							items[index].state = state;

							instance._activeSuccessPage = false;

							instance.set('items', items);
						}
						else {
							instance._activeSuccessPage = true;

							instance.set('items', items);
						}
					}
				}
			}
		);

		Liferay.namespace('DDM').FormBuilderWizard = FormBuilderWizard;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-wizard']
	}
);