
function drawPyramid(height) {
    console.log("Height : " + height)

    let pyramidRoot = document.getElementById('pyramid-building');
    let brikSymbol = document.getElementById('brickSymbol').value;
    for(var  i = 0; i < height; i++) {
        let p = document.createElement('pre');
        let text = document.createTextNode(' '.repeat(height - i - 1) + brikSymbol.repeat(i + 2));
        p.appendChild(text);
        pyramidRoot.appendChild(p)
    }
}

function clearPyramid() {
    let pyramidRoot = document.getElementById('pyramid-building');
    while(pyramidRoot.firstChild) {
        pyramidRoot.firstChild.remove();
    }
}

function reDrawPyramid(height){
    console.log("Re draw pyramid")
    clearPyramid();
    drawPyramid(height);
}

function updateBackEnd() {
    let height = document.getElementById('pyramidHeigh').value;
    let symbol = document.getElementById('brickSymbol').value;

    return fetch('/update', {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ pyramidHeigh: height, pyramidSymbol: symbol })
        })
        .then(result => result)
        .then(res => {getDataFromBackEnd()})
}


function getDataFromBackEnd ()  {
       fetch('/get')
            .then(result => result.json())
            .then(res => {

            document.getElementById('pyramidHeighLabel').innerHTML = res.pyramidHeigh;
            document.getElementById('pyramidHeigh').value = res.pyramidHeigh;

            reDrawPyramid(res.pyramidHeigh);
            })
}

document.getElementById('pyramidHeigh').addEventListener('change', function changePyramidHigh(){
    console.log("changePyramidHigh")
    updateBackEnd();

})

document.getElementById('brickSymbol').addEventListener('change', function test() {
    console.log("Change brik");
    updateBackEnd();

})

var eventSource = new EventSource("/events/subscribe");
var prevState = undefined;
eventSource.onmessage = function(e) {
    //console.log("New message : " + e.data);
    let curState;
    let data = JSON.parse(e.data);
    if(prevState == undefined) {
        prevState  = data.trigger;
    }

    if(data.trigger != prevState) {
         console.log("Data has been changed : " + e.data);
         getDataFromBackEnd();
         prevState = curState;
    }
};

getDataFromBackEnd();






