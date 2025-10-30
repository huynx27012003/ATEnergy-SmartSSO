(function($){
    /**
     * jqGrid Chinese Translation → English Translation
     * Translator: Coffee Rabbit (yanhonglei@gmail.com)
     * http://www.kafeitu.me
     * Dual licensed under the MIT and GPL licenses:
     * http://www.opensource.org/licenses/mit-license.php
     * http://www.gnu.org/licenses/gpl.html
     **/
    $.jgrid = $.jgrid || {};
    $.extend($.jgrid,{
        defaults : {
            recordtext: "{0} - {1} of {2} records", // full-width space removed
            emptyrecords: "No data to display",
            loadtext: "Loading...",
            pgtext : "Page {0} of {1}"
        },
        search : {
            caption: "Search...",
            Find: "Find",
            Reset: "Reset",
            odata: [
                { oper:'eq', text:'equal to' },
                { oper:'ne', text:'not equal to' },
                { oper:'lt', text:'less than' },
                { oper:'le', text:'less than or equal to' },
                { oper:'gt', text:'greater than' },
                { oper:'ge', text:'greater than or equal to' },
                { oper:'bw', text:'begins with' },
                { oper:'bn', text:'does not begin with' },
                { oper:'in', text:'in' },
                { oper:'ni', text:'not in' },
                { oper:'ew', text:'ends with' },
                { oper:'en', text:'does not end with' },
                { oper:'cn', text:'contains' },
                { oper:'nc', text:'does not contain' },
                { oper:'nu', text:'is null' },
                { oper:'nn', text:'is not null' }
            ],
            groupOps: [
                { op: "AND", text: "all" },
                { op: "OR",  text: "any" }
            ],
            operandTitle : "Click to select search operation.",
            resetTitle : "Reset Search Value"
        },
        edit : {
            addCaption: "Add Record",
            editCaption: "Edit Record",
            bSubmit: "Submit",
            bCancel: "Cancel",
            bClose: "Close",
            saveData: "Data has been changed, save changes?",
            bYes : "Yes",
            bNo : "No",
            bExit : "Cancel",
            msg: {
                required:"This field is required",
                number:"Please enter a valid number",
                minValue:"Value must be greater than or equal to ",
                maxValue:"Value must be less than or equal to ",
                email: "This is not a valid e-mail address",
                integer: "Please enter a valid integer",
                date: "Please enter a valid date",
                url: "Invalid URL. Prefix must be ('http://' or 'https://')",
                nodefined : " not defined!",
                novalue : " value required!",
                customarray : "Custom function should return an array!",
                customfcheck : "Custom function must exist!"
            }
        },
        view : {
            caption: "View Record",
            bClose: "Close"
        },
        del : {
            caption: "Delete",
            msg: "Delete selected record(s)?",
            bSubmit: "Delete",
            bCancel: "Cancel"
        },
        nav : {
            edittext: "",
            edittitle: "Edit selected record",
            addtext:"",
            addtitle: "Add new record",
            deltext: "",
            deltitle: "Delete selected record",
            searchtext: "",
            searchtitle: "Search",
            refreshtext: "",
            refreshtitle: "Reload Table",
            alertcap: "Warning",
            alerttext: "Please select a record",
            viewtext: "",
            viewtitle: "View selected record"
        },
        col : {
            caption: "Select Columns",
            bSubmit: "OK",
            bCancel: "Cancel"
        },
        errors : {
            errcap : "Error",
            nourl : "No URL is set",
            norecords: "No records to process",
            model : "Length of colNames and colModel do not match!"
        },
        formatter : {
            integer : {thousandsSeparator: ",", defaultValue: '0'},
            number : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0.00'},
            currency : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
            date : {
                dayNames: [
                    "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
                    "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
                ],
                monthNames: [
                    "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                ],
                AmPm : ["am","pm","AM","PM"],
                S: function (j) {
                    return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th';
                },
                srcformat: 'Y-m-d',
                newformat: 'Y-m-d',
                parseRe : /[#%\\\/:_;.,\t\s-]/,
                masks : {
                    ISO8601Long:"Y-m-d H:i:s",
                    ISO8601Short:"Y-m-d",
                    ShortDate: "n/j/Y",
                    LongDate: "l, F d, Y",
                    FullDateTime: "l, F d, Y g:i:s A",
                    MonthDay: "F d",
                    ShortTime: "g:i A",
                    LongTime: "g:i:s A",
                    SortableDateTime: "Y-m-d\\TH:i:s",
                    UniversalSortableDateTime: "Y-m-d H:i:sO",
                    YearMonth: "F, Y"
                },
                reformatAfterEdit : false
            },
            baseLinkUrl: '',
            showAction: '',
            target: '',
            checkbox : {disabled:true},
            idName : 'id'
        }
    });
})(jQuery);
