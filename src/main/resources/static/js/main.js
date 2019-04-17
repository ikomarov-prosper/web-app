
function createTable(colSize, rowSize, tableData) {
    let table = document.createElement('table');
    table.setAttribute("background", "images/background.jpg");
    for (let i = 0; i < rowSize; i++) {
        let tr = document.createElement('tr')
        for (let j = 0; j < colSize; j++) {
            if(tableData == undefined) {
                let td = document.createElement('td');
                let image  = new Image();
                image.src = '/images/QuestionsPicture.jpg';
                td.appendChild(image);
                tr.appendChild(td);
            }
            else{

                if(getCell(i,j, tableData).status == "NOT_STARTED") {
                        let td = document.createElement('td');
                        let image  = new Image();
                        image.src = '/images/QuestionsPicture.jpg';
                        td.appendChild(image);
                        tr.appendChild(td);
                }
                else if(getCell(i,j, tableData).status == "IN_PROGRESS") {
                                        let td = document.createElement('td');
                                        td.setAttribute("opacity", 0)
                                        let question = getCell(i,j, tableData).question;
                                        td.innerHTML = question;
                                        tr.appendChild(td);
                                }
                else if(getCell(i,j, tableData).status == "RESOLVED") {
                                    let td = document.createElement('td');
                                    // let image  = new Image();
                                    // image.src = '/images/resolved.png';
                                    // td.appendChild(image);
                                    td.setAttribute("opacity", 1)
                                    tr.appendChild(td);
                            }
                 else if(getCell(i,j, tableData).status == "FAILED") {
                                         let td = document.createElement('td');
                                         let image  = new Image();
                                         image.src = '/images/failed.jpg';
                                         td.appendChild(image);
                                         tr.appendChild(td);
                 }

            }
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

function getCell(row, col, tableData) {
    let size = model.columns * model.rows;
    for(let i = 0; i < size; i++) {
     if(tableData.cell[i].col == col && tableData.cell[i].row == row) {
      return tableData.cell[i];
     }
    }
    return undefined;
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
    //console.log("New message : " + e.data);
    let curState;
    let data = JSON.parse(e.data);
    if(prevState === undefined) {
        prevState  = data.table.activeCell;
        createTable(model.columns, model.rows, data.table);
    }

    curState = data.table.activeCell;
    if(JSON.stringify(curState) != JSON.stringify(prevState)) {
        console.log("Active cell has been changed : " + e.data);
        prevState = curState;

        removeTable();
        createTable(model.columns, model.rows, data.table);
    }
};





