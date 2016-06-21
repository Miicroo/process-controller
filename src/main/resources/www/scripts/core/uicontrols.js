/**
 * Generic functionality for UI.
 */
var uiControls = (function UIControls(){

    var controls = {};

    controls.displayText = function(containerId, text) {
        document.getElementById(containerId).innerText = text;
    };

    controls.displayHTML = function(containerId, html) {
        document.getElementById(containerId).innerHTML = html;
    };

    controls.clearText = function(containerId) {
        controls.clearTextAfter(containerId, 0);
    };

    controls.clearTextAfter = function(containerId, msDelay) {
        setTimeout(function(){ controls.displayText(containerId, ''); }, msDelay);
    };

    controls.disable = function(containerId) {
        document.getElementById(containerId).className = "disabled";
        document.getElementById(containerId).onclick = undefined;
    };

    controls.enable = function(containerId, callback) {
        document.getElementById(containerId).className = "clickable";
        document.getElementById(containerId).onclick = callback;
    };

    return controls;
})();