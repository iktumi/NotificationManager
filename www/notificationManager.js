// Empty constructor
function NotificationManager() {}

NotificationManager.prototype.cancelAll = function( successCallback, errorCallback) {
  var options = {}; 
  cordova.exec(successCallback, errorCallback, 'NotificationManager', 'cancelAll', [options]);
}

// Installation constructor that binds NotificationManager to window
NotificationManager.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.notificationManager = new NotificationManager();
  return window.plugins.notificationManager;
};
cordova.addConstructor(NotificationManager.install);