scope="global";
function checkscope() {
    scope="local";
    console.log("value of scope: " + scope);
    myscope="local";
    console.log("value of myscope: " + myscope);
    var to = typeof(y);
    console.log("value of to(typeof(y)): " + to, "typeof 'to': " + typeof(to));
    if(typeof(x)=='undefined') {
        console.log("x is undefined");
    }
}

checkscope();
console.log("value of scope out of funciton: " + scope);
console.log("value of myscope out of function: " + myscope);
