/**
 * UI functionality for lsController.
 */
var lsUI = (function() {
    var controls = {};

    controls.updateUI = function(lsInfo) {
        uiControls.displayText('lsInfoDisplay', lsInfo.lsInfo);
    };

    return controls;
})();