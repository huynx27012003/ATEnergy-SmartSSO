/**
 * jQuery multiselect custom
 * 
 * Author Joe
 */
jQuery(function($) {
    var def = {
        // Show search box
        enableFiltering: false,
        nonSelectedText: 'Please select',
        nSelectedText: 'items selected',
        allSelectedText: 'All selected',
        // Number displayed
        numberDisplayed: 10,
        // Show select all
        includeSelectAllOption: false,
        // Select all text
        selectAllText: 'Select all',
        onChange: function(option, checked, select) {
            // Validate on change
            if(smart && smart.validate){
                $(option).parent().validate();
            }
        },
        buttonClass: 'btn btn-white',
		templates: {
			button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"></button>',
			ul: '<ul class="multiselect-container dropdown-menu"></ul>',
			filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
			filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
			li: '<li><a href="javascript:void(0);"><label></label></a></li>',
			divider: '<li class="multiselect-item divider"></li>',
			liGroup: '<li class="multiselect-item group"><label class="multiselect-group"></label></li>'
		 }
	};
	
    $.select = function($o, c) {
        // Multiselect
        $o.multiselect($.extend({}, def, c));
    };
	
	$.fn.select = function(c) {
		return $.select($(this), c);
	};
});
