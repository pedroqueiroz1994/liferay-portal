// This file was automatically generated from radio_grid.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace ddm.
 * @hassoydeltemplate {ddm.field}
 * @public
 */

if (typeof ddm == 'undefined') { var ddm = {}; }


ddm.__deltemplate_s2_bbd84749 = function(opt_data, opt_ignored) {
  return '' + ddm.radio_grid(opt_data);
};
if (goog.DEBUG) {
  ddm.__deltemplate_s2_bbd84749.soyTemplateName = 'ddm.__deltemplate_s2_bbd84749';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ddm.field'), 'radio_grid', 0, ddm.__deltemplate_s2_bbd84749);


ddm.radio_grid = function(opt_data, opt_ignored) {
  var output = '<div class="form-group' + soy.$$escapeHtmlAttribute(opt_data.visible ? '' : ' hide') + '" data-fieldname="' + soy.$$escapeHtmlAttribute(opt_data.name) + '">' + ((opt_data.showLabel) ? '<label class="control-label">' + soy.$$escapeHtml(opt_data.label) + ((opt_data.required) ? '<span class="icon-asterisk text-warning"></span>' : '') + '</label>' + ((opt_data.tip) ? '<p class="liferay-ddm-form-field-tip">' + soy.$$escapeHtml(opt_data.tip) + '</p>' : '') : '') + '<div class="liferay-ddm-form-field-radio-grid table-responsive"><table class="table table-autofit table-list table-striped"><thead><tr><th></th>';
  var columnList27 = opt_data.columns;
  var columnListLen27 = columnList27.length;
  for (var columnIndex27 = 0; columnIndex27 < columnListLen27; columnIndex27++) {
    var columnData27 = columnList27[columnIndex27];
    output += '<th>' + soy.$$escapeHtml(columnData27.label) + '</th>';
  }
  output += '</tr></thead><tbody>';
  var rowList54 = opt_data.rows;
  var rowListLen54 = rowList54.length;
  for (var rowIndex54 = 0; rowIndex54 < rowListLen54; rowIndex54++) {
    var rowData54 = rowList54[rowIndex54];
    output += '<tr name="' + soy.$$escapeHtmlAttribute(rowData54.value) + '"><td>' + soy.$$escapeHtml(rowData54.label) + '</td>';
    var columnList51 = opt_data.columns;
    var columnListLen51 = columnList51.length;
    for (var columnIndex51 = 0; columnIndex51 < columnListLen51; columnIndex51++) {
      var columnData51 = columnList51[columnIndex51];
      output += '<td><input class="field" ' + ((opt_data.readOnly) ? 'disabled' : '') + ' id="' + soy.$$escapeHtmlAttribute(opt_data.name) + '_' + soy.$$escapeHtmlAttribute(rowData54.value) + '_' + soy.$$escapeHtmlAttribute(columnData51.value) + '" name="' + soy.$$escapeHtmlAttribute(opt_data.name) + soy.$$escapeHtmlAttribute(rowData54.value) + '" type="radio" value="' + soy.$$escapeHtmlAttribute(columnData51.value) + '" /></td>';
    }
    output += '</tr>';
  }
  output += '</tbody></table></div>' + ((opt_data.childElementsHTML) ? soy.$$filterNoAutoescape(opt_data.childElementsHTML) : '') + '</div>';
  return output;
};
if (goog.DEBUG) {
  ddm.radio_grid.soyTemplateName = 'ddm.radio_grid';
}
