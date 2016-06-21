/**
 * System info controller for retrieving system info.
 */
var sysInfoController = (function() {

    var controls = {};

    controls.updateSysInfo = function() {
        backend.sendRequest("/sysInfo",
            function(data) {
                sysInfoUI.updateUI(JSON.parse(data));
            }, function(code, data) {
                alert(code + " " + data);
            });
    };

    return controls;
})();
