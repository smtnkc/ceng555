let personalId = document.getElementById("personalId");
let name = document.getElementById("name");
let alesScore = document.getElementById("alesScore");
let ydsScore = document.getElementById("ydsScore");
let gpaScore = document.getElementById("gpaScore");
let email = document.getElementById("email");

resetForm();

function validateForm() {
    let validCount = 0
    if(personalId.value==""||!/^\d{11}$/.test(personalId.value)) {
        personalId.className = "invalidTextBox";
    }
    else {
        personalId.className = "";
        validCount++;
    }
    if(name.value==""||!/^[a-zA-ZığüşöçĞÜŞÖÇİ]+( ([a-zA-ZığüşöçĞÜŞÖÇİ]+))+$/.test(name.value)) {
        name.className = "invalidTextBox";
    }
    else {
        name.className = "";
        validCount++;
    }
    if(alesScore.value==""||!/^100(\.[0]{1,4})?|([7-9][0-9])(\.[0-9]{1,4})?$/.test(alesScore.value)) {
        alesScore.className = "invalidTextBox";
    }
    else {
        alesScore.className = "";
        validCount++;
    }
    if(ydsScore.value==""||!/^100(\.[0]{1,4})?|([7-9][0-9])(\.[0-9]{1,4})?$/.test(ydsScore.value)) {
        ydsScore.className = "invalidTextBox";
    }
    else {
        ydsScore.className = "";
        validCount++;
    }
    if(gpaScore.value==""||!/^100(\.[0]{1,4})?|([5-9][0-9])(\.[0-9]{1,4})?$/.test(gpaScore.value)) {
        gpaScore.className = "invalidTextBox";
    }
    else {
        gpaScore.className = "";
        validCount++;
    }
    if(email.value==""||!/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(email.value)) {
        email.className = "invalidTextBox";
    }
    else {
        email.className = "";
        validCount++;
    }
    console.log("validCount = " + validCount);
    return validCount == 6;
}
function fillForm() {
    personalId.value = getRandomPersonalId();  // 11 digits
    name.value = getRandomName(); // 2 words
    alesScore.value = Math.round((Math.random()*30+70)*10)/10; // [70.0, 100.0]
    ydsScore.value = Math.round((Math.random()*30+70)*10)/10;  // [70.0, 100.0]
    gpaScore.value = Math.round((Math.random()*50+50)*10)/10;  // [50.0, 100.0]
    email.value = "samet@janmail.org";
}
function getRandomName() {
    let result = "";
    let upplerAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZĞÜŞÖÇİ";
    let lowerAlphabet = "abcdefghijklmnopqrstuvwxyzığüşöç";
    result += upplerAlphabet.charAt(Math.floor(Math.random() * upplerAlphabet.length));
    for (let i = 0; i < 4; i++) {
        result += lowerAlphabet.charAt(Math.floor(Math.random() * lowerAlphabet.length));
    }
    result += " " + upplerAlphabet.charAt(Math.floor(Math.random() * upplerAlphabet.length));
    for (let i = 0; i < 4; i++) {
        result += lowerAlphabet.charAt(Math.floor(Math.random() * lowerAlphabet.length));
    }
    return result;
}
function getRandomPersonalId() {
    let result = "";
    let digits = "0123456789";
    for (let i = 0; i < 11; i++) {
        result += digits.charAt(Math.floor(Math.random() * digits.length));
    }
    return result;
}
function resetForm() {
    personalId.value="";
    name.value="";
    alesScore.value="";
    ydsScore.value="";
    gpaScore.value="";
    email.value="";
}