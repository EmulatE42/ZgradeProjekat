/**
 * Created by djuro on 11/10/2017.
 */
"use strict";
var LoggedUtils = (function () {
    function LoggedUtils() {
    }
    LoggedUtils.getId = function () {
        return JSON.parse(localStorage.getItem("loggedUser")).id;
    };
    LoggedUtils.getToken = function () {
        return JSON.parse(localStorage.getItem("loggedUser")).token;
    };
    LoggedUtils.hasRole = function (role) {
        var roles = JSON.parse(localStorage.getItem("loggedUser")).role;
        return roles.indexOf(role) != -1;
    };
    LoggedUtils.getEmail = function () {
        return JSON.parse(localStorage.getItem("loggedUser")).email;
    };
    LoggedUtils.clearLocalStorage = function () {
        localStorage.clear();
    };
    LoggedUtils.isEmpty = function () {
        return localStorage.getItem("loggedUser") === null;
    };
    LoggedUtils.isLogged = function () {
    };
    return LoggedUtils;
}());
exports.LoggedUtils = LoggedUtils;
//# sourceMappingURL=logged.utils.js.map