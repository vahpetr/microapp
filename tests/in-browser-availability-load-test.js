//@ts-check
const threads = 4;
const requestsPerSecond = 1000;
const stopAfterSeconds = 10;
const asyncRequest = () => {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        console.log(request.responseText)
    }
    request.open("GET", "/", true);
    request.send(null);
};
const tasks = Array.from({ length: threads }, () => setInterval(asyncRequest, 1000.0 / requestsPerSecond));
setTimeout(() => tasks.forEach(p => clearInterval(p)), 1000 * stopAfterSeconds)
