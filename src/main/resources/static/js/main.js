
function createTable(colSize, rowSize) {
    let table = document.createElement('table');
    for (let i = 0; i < rowSize; i++) {
        let tr = document.createElement('tr')
        for (let j = 0; j < colSize; j++) {
            let td = document.createElement('td');
            let image  = new Image();
            image.src = '/images/QuestionsPicture.jpg';
            td.appendChild(image);
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
    document.getElementById('table-container').appendChild(table);
}

function removeTable() {
    let tableRoot = document.getElementById('table-container');
    while(tableRoot.firstChild) {
        tableRoot.firstChild.remove();
    }
}
//
//function updateBackEnd() {
//    let size = document.getElementById('tableSize').value;
//
//    return fetch('/update', {
//        method: 'PUT',
//        headers: { 'Content-Type': 'application/json' },
//        body: JSON.stringify({ tableSize: size})
//    })
//        .then(result => result)
//        .then(res => {getDataFromBackEnd()})
//}
//
//
//function getDataFromBackEnd ()  {
//    fetch('/get')
//        .then(result => result.json())
//        .then(res => {
//            removeTable();
//            createTable(res.tableSize, res.tableSize);
//        })
//}

//document.getElementById('tableSize').addEventListener('change', function changeTableSize(){
//    console.log("changeSize")
//    updateBackEnd();
//
//})

//getDataFromBackEnd();

// createTable(document.getElementById('answerID').textContent, document.getElementById('answerID').textContent);

var eventSource = new EventSource("/events/subscribe");
var prevState = undefined;
eventSource.onmessage = function(e) {
    console.log("New message : " + e.data);
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

createTable(model.columns, model.rows);



