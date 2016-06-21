/**
 * UI functionality for memInfoController.
 */
var memInfoUI = (function() {
    var controls = {};

    controls.updateUI = function(memInfo) {
        uiControls.displayText("memInfoDisplay", memInfo.memInfo);
    };

    return controls;
})();