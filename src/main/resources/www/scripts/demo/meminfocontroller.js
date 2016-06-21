/**
 * Memory info controller for retrieving memory info.
 */
var memInfoController = (function() {

    var controls = {};

    controls.updateMemInfo = function() {
        backend.sendRequest("/memInfo",
            function(data) {
                memInfoUI.updateUI(JSON.parse(data));
            }, function(code, data) {
                alert(code + " " + data);
            });
    };

    return controls;
})();
