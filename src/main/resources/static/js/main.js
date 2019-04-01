var res = {pyramidHeigh: undefined, pyramidSymbol: undefined}


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
    updateBackEnd();
}


function clearPyramid() {
    let pyramidRoot = document.getElementById('pyramid-building');
    while(pyramidRoot.firstChild) {
        pyramidRoot.firstChild.remove();
    }
   // updateBackEnd( );

}

function reDrawPyramid(height){
    console.log("Re draw pyramid")
    clearPyramid();
    drawPyramid(height);
    document.getElementById('pyramidHeighLabel').innerHTML = height
}

function updateBackEnd() {
    let height = document.getElementById('pyramidHeigh').value;
    let symbol = document.getElementById('brickSymbol').value;

    fetch('/update', {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ pyramidHeigh: height, pyramidSymbol: symbol })
        })
        .then(result => result.json())
        .then(console.log())
    console.log('updateBackEnd');
}

function updateBackEnd(height, symbol) {
    fetch('/update', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ pyramidHeigh: height, pyramidSymbol: symbol })
    })
        .then(result => result.json())
        .then(console.log())
    console.log('updateBackEnd with values');
}

function getDataFromBackEnd ()  {
       fetchedData =  fetch('/get')
            .then(result => result.json())
            .then(data=> {
                res = data;
                return data;
            })
    return res;


}

document.getElementById('pyramidHeigh').addEventListener('mousemove', function changePyramidHigh(){
    console.log("changePyramidHigh")

    let height = document.getElementById('pyramidHeigh').value;
    reDrawPyramid(getDataFromBackEnd().pyramidHeigh);
})

document.getElementById('brickSymbol').addEventListener('change', function test() {
    console.log("Change brik");
    reDrawPyramid(getDataFromBackEnd().pyramidHeigh);
})

setTimeout(function init () {
    updateBackEnd(5, "$");
    document.getElementById('pyramidHeighLabel').innerHTML = getDataFromBackEnd().pyramidHeigh;
    drawPyramid(getDataFromBackEnd().pyramidHeigh);
}, 100);


