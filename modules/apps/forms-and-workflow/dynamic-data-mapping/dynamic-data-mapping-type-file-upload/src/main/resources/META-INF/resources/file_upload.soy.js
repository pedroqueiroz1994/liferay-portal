// This file was automatically generated from file_upload.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace ddm.
 * @hassoydeltemplate {ddm.field}
 * @public
 */

if (typeof ddm == 'undefined') { var ddm = {}; }


ddm.__deltemplate_s2_45b9b7c6 = function(opt_data, opt_ignored) {
  return '' + ddm.file_upload(opt_data);
};
if (goog.DEBUG) {
  ddm.__deltemplate_s2_45b9b7c6.soyTemplateName = 'ddm.__deltemplate_s2_45b9b7c6';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ddm.field'), 'file_upload', 0, ddm.__deltemplate_s2_45b9b7c6);


ddm.file_upload = function(opt_data, opt_ignored) {
  return '<div class="form-group' + soy.$$escapeHtmlAttribute(opt_data.visible ? '' : ' hide') + '" data-fieldname="' + soy.$$escapeHtmlAttribute(opt_data.name) + '">' + ((opt_data.showLabel) ? '<label class="control-label">' + soy.$$escapeHtml(opt_data.label) + ((opt_data.required) ? '<span class="icon-asterisk text-warning"></span>' : '') + '</label>' + ((opt_data.tip) ? '<p class="liferay-ddm-form-field-tip">' + soy.$$escapeHtml(opt_data.tip) + '</p>' : '') : '') + '<div class="form-builder-file-upload-field"><label class="sr-only">FILE UPLOAD</label><input dir="' + soy.$$escapeHtmlAttribute(opt_data.dir) + '" ' + ((opt_data.readOnly) ? 'disabled' : '') + ' id="inputFile" name="' + soy.$$escapeHtmlAttribute(opt_data.name) + '" multiple type="file"></div>' + ((opt_data.childElementsHTML) ? soy.$$filterNoAutoescape(opt_data.childElementsHTML) : '') + '</div>';
};
if (goog.DEBUG) {
  ddm.file_upload.soyTemplateName = 'ddm.file_upload';
}
