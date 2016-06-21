/**
 * Manager for controlling notifications.
 */
var notificationManager = (function() {
    var controls = {};

    controls.showNotification = function(title, content) {
        if(window.Notification && Notification.permission !== "denied") {
            Notification.requestPermission(function(status) {
                new Notification(title, {
                    body: content,
                    icon: 'favicon.ico'
                });
            });
        }
    };

    controls.showNotificationWithTimeout = function(title, content, timeoutMs) {
        if(window.Notification && Notification.permission !== "denied") {
            Notification.requestPermission(function(status) {
                var notification = new Notification(title, {
                    body: content,
                    icon: 'favicon.ico'
                });

                setTimeout(function() {
                    notification.close();
                }, timeoutMs);
            });
        }
    };

    return controls;
})();