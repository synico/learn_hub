var com;
if (!com) com = {};
if (!com.davidflanagan) com.davidflanagan = {};
com.davidflanagan.Class = {};

(function() {
    function define(data) { counter++; }
    function provides(o, c) {}

    var counter = 0;

    function getCounter() { return counter; }

    var ns = com.davidflanagan.Class;
    ns.define = define;
    ns.provides = provides;
    ns.getCounter = getCounter;
    console.log("end...");
}) ();
