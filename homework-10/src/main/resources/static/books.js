function outputCharacter(character) {
    const dataContainer = document.getElementById("dataContainer");
    dataContainer.innerHTML = JSON.stringify(character, undefined, 4);
    dataContainer.style.textAlign = "left"; // Применяем стиль выравнивания по центру
}

function getBookDataByFetch() {
    const textField = document.getElementById('name');

    if (textField.value !== '') {
        const bookName = textField.value;
        fetch('http://localhost:8080/api/v1/book/' + bookName)
            .then(response => response.json())
            .then(json => outputCharacter(json))
            .catch(error => console.error('Ошибка:', error));
    } else {
        console.log('Заполните текстовое поле!');
    }
}

function loadBooks() {
    fetch('/api/v1/book/')
        .then(response => response.json())
        .then(data => {
            displayBooks(data);
        })
        .catch(error => {
            console.error('Ошибка:', error);
        });
}

function displayBooks(books) {
    var table = document.getElementById("booksTable");
    table.innerHTML = "";

    var thead = document.createElement("thead");
    var headerRow = document.createElement("tr");

    var keys = Object.keys(books[0]).slice(0, -1);
    keys.forEach(function (key) {
        var th = document.createElement("th");
        th.textContent = key;
        headerRow.appendChild(th);
    });

    thead.appendChild(headerRow);
    table.appendChild(thead);

    var tbody = document.createElement("tbody");
    books.forEach(function (book) {
        var row = document.createElement("tr");
        keys.forEach(function (key) {
            var cell = document.createElement("td");
            if (key === 'author') {
                cell.textContent = book[key].name + " " + book[key].surname; // Используйте соответствующее свойство вложенного объекта
            } else if (key === 'genre') {
                cell.textContent = book[key].name; // Используйте соответствующее свойство вложенного объекта
            } else {
                cell.textContent = book[key];
            }
            row.appendChild(cell);
        });
        tbody.appendChild(row);
    });

    table.appendChild(tbody);
}
