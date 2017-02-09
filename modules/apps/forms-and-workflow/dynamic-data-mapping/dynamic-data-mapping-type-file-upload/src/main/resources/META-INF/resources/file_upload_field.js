AUI.add(
    'liferay-ddm-form-field-file-upload',
    function(A) {
        var FileUploadField = A.Component.create({
            ATTRS: {
                type: {
                    value: 'file-upload'
                }
            },

            EXTENDS: Liferay.DDM.Renderer.Field,

            NAME: 'liferay-ddm-form-field-file-upload',

            prototype: {
                initializer: function() {
                    var instance = this;

                    instance._eventHandlers.push(
                        instance.bindContainerEvent('change', instance._onSelectFile, '.form-builder-file-upload-field')
                    );
                },

                getInputNode: function() {
                    var instance = this;

                    var container = instance.get('container');

                    var inputNode = container.one('input[type="file"]');

                    return inputNode;
                },

                getTemplateContext: function() {
                    var instance = this;

                    return A.merge(
                        FileUploadField.superclass.getTemplateContext.apply(instance, arguments), {

                        }
                    );
                },

                getValue: function() {

                },

                setValue: function(value) {

                },

                showErrorMessage: function() {
                    var instance = this;

                    var container = instance.get('container');

                    FileUploadField.superclass.showErrorMessage.apply(instance, arguments);

                    container.all('.help-block').appendTo(container.one('.form-group'));
                },

                _onSelectFile: function(event) {


                }
            }
        });

        Liferay.namespace('DDM.Field').FileUpload = FileUploadField;
    },
    '', {
        requires: ['liferay-ddm-form-renderer-field']
    }
);