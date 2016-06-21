/**
 * Generic backend functionality to communicate with the REST API.
 */
var backend = (function () {

    var controls = {};

    controls.sendRequest = function(url, okFunc, errorFunc) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4) {
                if (xhttp.status == 200) {
                    okFunc(xhttp.responseText);
                } else {
                    errorFunc(xhttp.status, xhttp.responseText);
                }
            }
        };
        xhttp.open("GET", url, true);
        xhttp.send();
    };

    return controls;
})();
