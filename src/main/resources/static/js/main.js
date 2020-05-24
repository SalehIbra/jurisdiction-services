// search from the table with updating row count
function searchTable() {
    var input, filter, found, table, tr, td, i, j;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("jurConfig_list");
    tr = table.getElementsByTagName("tr");
    for (i = 1; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        for (j = 0; j < td.length; j++) {
            if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                found = true;
            }
        }
        if (found) {
            tr[i].style.display = "";
            found = false;
        } else {
            tr[i].style.display = "none";
        }
    }
    var totalRowCount = $('#jurConfig_list >tbody >tr:visible').length;
    document.getElementById("rowcount").innerHTML = "Count : " + totalRowCount;
}

// get the rows count
$(document).ready(function () {
    var totalRowCount = $('#jurConfig_list >tbody >tr:visible').length;
    document.getElementById("rowcount").innerHTML = "Count: " + totalRowCount;
})
