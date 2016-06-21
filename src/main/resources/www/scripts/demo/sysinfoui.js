/**
 * UI functionality for sysInfoController.
 */
var sysInfoUI = (function() {
    var controls = {};

    controls.updateUI = function(sysInfo) {
        document.getElementById('sysInfoDisplay').className = 'sysInfo';
        console.log(sysInfo.sysInfo);

        var output = infoMapToStr('General', sysInfo.sysInfo.sysMap);
        output += infoMapToStr('User', sysInfo.sysInfo.userMap);
        output += infoMapToStr('Network', sysInfo.sysInfo.networkMap);

        uiControls.displayHTML("sysInfoDisplay", output);
    };

    function infoMapToStr(title, infoMap) {
        var output = '<h3>'+title+'</h3><table>';

        var keys = Object.keys(infoMap);
        keys.sort();

        for (var i = 0; i < keys.length; i++) {
            var key = keys[i];
            output += '<tr valign="top"><td><b>'+key+'</b></td><td>'+infoMap[key]+'</td></tr>';
        }

        output += '</table>';

        return output;
    }

    return controls;
})();