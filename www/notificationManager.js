// Empty constructor
function NotificationManager() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
NotificationManager.prototype.show = function(message, duration, successCallback, errorCallback) {
  var options = {};
  options.message = message;
  options.duration = duration;
  cordova.exec(successCallback, errorCallback, 'NotificationManager', 'show', [options]);
}

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