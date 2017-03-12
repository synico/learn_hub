var add = function (a, b) {
    return a + b;
}

var myObj = {
    value: 0,
    increment: function (inc) {
        this.value += typeof inc === 'number' ? inc : 1;
    }
}

myObj.increment();
console.log(myObj.value);

myObj.increment(2);
console.log(myObj.value);

myObj.double = function () {
    var that = this;
    var helper = function () {
        that.value = add(that.value, that.value);
        console.log("value in helper(): " + this.myObj.value);
    };

    helper();
}

myObj.double();
console.log(myObj.value);
