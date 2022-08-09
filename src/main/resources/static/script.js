window.onload = function () {
    let inputFields = document.getElementsByTagName('input');

    for (let i = 0; i < inputFields.length; i++) {
        if (inputFields[i].value === '0') {
            inputFields[i].value = '';
        }
    }
}

function check(correctNumber, inputField) {
    let userNumber = inputField.value;

    if (userNumber >= '1' && userNumber <= '9') {
        inputField.style.color = (userNumber === correctNumber ? 'blue' : 'red');
    } else  {
        inputField.value = '';
        inputField.style.color = 'black';
    }
}
