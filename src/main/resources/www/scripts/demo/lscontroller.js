/**
 * Ls controller for retrieving dir info.
 */
var lsController = (function () {
    var controls = {};

    controls.getDirContent = function (dir) {
        backend.sendRequest("/ls?dir=" + encodeURI(dir),
            function (data) {
                lsUI.updateUI(JSON.parse(data));
            }, function (code, data) {
                var infoText = 'Error (' + code + '): ' + data + '. No such directory ' + dir;

                /*
                 * ./x/y/z does not work, should be x/y/z
                 */
                var startPattern = "./";
                if (dir.substr(0, startPattern.length) == startPattern) {
                    var newDir = dir.substr(startPattern.length, dir.length);
                    infoText += '. Did you mean <a onclick="lsController.getDirContent(\'' + newDir + '\');" class="clickable link">' + newDir + '</a>?';
                }

                uiControls.displayHTML('lsInfoDisplay', infoText);
            });
    };

    return controls;
})();
