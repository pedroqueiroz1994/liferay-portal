;(function() {
	AUI().applyConfig(
		{
			groups: {
				'field-radio-grid': {
					base: MODULE_PATH + '/',
					combine: Liferay.AUI.getCombine(),
					modules: {
						'liferay-ddm-form-field-radio-grid': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'radio_grid_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-radio-grid-template': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'radio_grid.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: MODULE_PATH + '/'
				}
			}
		}
	);
})();