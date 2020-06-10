$(document).ready(function() {

  // Add Data Table
  addDataTable();
});

function addDataTable() {

  $('table').each(function(i,table) {
    var columns = $(table).find('th').length;
    console.log(columns);

    noSortColumns = $(table).find('th.no-sort').map(function(j,th) {
      return $(th).index();
    });

    $(table).dataTable({
        "bPaginate": true, // This option enable the table pagination
        "bLengthChange": true, // This option disables the select table size option
        "bFilter": true, // This option enable the search
        "bSort": true, // this option enable the sort of contents by columns
        "bAutoWidth": false, // This option enables the auto adjust columns width
        "iDisplayLength": 50,// Number of rows to show on the table
        "dom": "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
        "<'row'<'col-sm-12'tr>>" +
        "<'row mt-1'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
        "language": {
          "emptyTable": "No entries entered into the system yet."
        },
        "order": [
          [
              1, 'desc'
          ]
        ],
        aoColumnDefs: [
            {
                bSortable: false,
                aTargets: noSortColumns
            }, {
                sType: "natural",
                aTargets: [
                  0
                ]
            }
        ]
    });

  });
}