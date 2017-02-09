;
(function() {
    AUI().applyConfig({
        groups: {
            'field-file-upload': {
                base: MODULE_PATH + '/',
                combine: Liferay.AUI.getCombine(),
                modules: {
                    'liferay-ddm-form-field-file-upload': {
                        condition: {
                            trigger: 'liferay-ddm-form-renderer'
                        },
                        path: 'file_upload_field.js',
                        requires: [
                            'liferay-ddm-form-renderer-field'
                        ]
                    },
                    'liferay-ddm-form-field-file-upload-template': {
                        condition: {
                            trigger: 'liferay-ddm-form-renderer'
                        },
                        path: 'file_upload.soy.js',
                        requires: [
                            'soyutils'
                        ]
                    }
                },
                root: MODULE_PATH + '/'
            }
        }
    });
})();