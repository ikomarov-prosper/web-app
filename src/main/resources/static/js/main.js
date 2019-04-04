
function createTable(colSize, rowSize) {
    for (let i = 0; i < rowSize; i++) {
        let tr = document.createElement('tr')
        for (let j = 0; j < colSize; j++) {
            let td = document.createElement('td');
            let image  = new Image();
            image.src = '/images/QuestionsPicture.jpg';
            td.appendChild(image);
            tr.appendChild(td);
        }
        document.getElementById('table').appendChild(tr);
    }
}


createTable(3,3);






