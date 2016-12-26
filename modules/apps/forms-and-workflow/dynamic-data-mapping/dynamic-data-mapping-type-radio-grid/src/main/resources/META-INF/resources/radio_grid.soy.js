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
  var output = '<div class="form-group' + soy.$$escapeHtmlAttribute(opt_data.visible ? '' : ' hide') + '" data-fieldname="' + soy.$$escapeHtmlAttribute(opt_data.name) + '">' + ((opt_data.showLabel) ? '<label class="control-label">' + soy.$$escapeHtml(opt_data.label) + ((opt_data.required) ? '<span class="icon-asterisk text-warning"></span>' : '') + '</label>' + ((opt_data.tip) ? '<p class="liferay-ddm-form-field-tip">' + soy.$$escapeHtml(opt_data.tip) + '</p>' : '') : '') + '<div class="radio-grid-table"><div class="radio-grid-row radio-grid-row-header"><label class="radio-grid-cell"></label>';
  var columnList27 = opt_data.columns;
  var columnListLen27 = columnList27.length;
  for (var columnIndex27 = 0; columnIndex27 < columnListLen27; columnIndex27++) {
    var columnData27 = columnList27[columnIndex27];
    output += '<label class="radio-grid-cell">' + soy.$$escapeHtml(columnData27.label) + '</label>';
  }
  output += '</div>';
  var rowList51 = opt_data.rows;
  var rowListLen51 = rowList51.length;
  for (var rowIndex51 = 0; rowIndex51 < rowListLen51; rowIndex51++) {
    var rowData51 = rowList51[rowIndex51];
    output += '<div class="radio-grid-row"><div class="clearfix radio radio-options"><label class="radio-grid-cell">' + soy.$$escapeHtml(rowData51.label) + '</label>';
    var columnList48 = opt_data.columns;
    var columnListLen48 = columnList48.length;
    for (var columnIndex48 = 0; columnIndex48 < columnListLen48; columnIndex48++) {
      var columnData48 = columnList48[columnIndex48];
      output += '<div class="radio-grid-cell"><input class="field" dir="' + soy.$$escapeHtmlAttribute(opt_data.dir || '') + '" ' + ((opt_data.readOnly) ? 'disabled' : '') + ' id="' + soy.$$escapeHtmlAttribute(opt_data.name) + '_' + soy.$$escapeHtmlAttribute(columnData48.value) + '" name="' + soy.$$escapeHtmlAttribute(opt_data.name) + '" type="radio" value="' + soy.$$escapeHtmlAttribute(columnData48.value) + '" /></div>';
    }
    output += '</div></div>';
  }
  output += '</div>' + ((opt_data.childElementsHTML) ? soy.$$filterNoAutoescape(opt_data.childElementsHTML) : '') + '</div>';
  return output;
};
if (goog.DEBUG) {
  ddm.radio_grid.soyTemplateName = 'ddm.radio_grid';
}
