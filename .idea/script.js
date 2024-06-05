let selectedWord = '';
let selectedHint = '';
let guessedLetters = [];
let wrongAttempts = 0;

const wordContainer = document.getElementById('word-container');
const lettersContainer = document.getElementById('letters-container');
const hintContainer = document.getElementById('hint-container');
const messageContainer = document.getElementById('message-container');
const resetButton = document.getElementById('reset-button');

async function fetchRandomWord() {
    try {
        const response = await fetch('/random-word');
        const data = await response.json();
        selectedWord = data.word;
        selectedHint = data.hint;
        guessedLetters = [];
        wrongAttempts = 0;
        messageContainer.textContent = '';
        displayWord();
        displayLetters();
        displayHint();
    } catch (error) {
        console.error('Erro ao buscar palavra:', error);
    }
}

function displayWord() {
    wordContainer.innerHTML = selectedWord.split('').map(letter => 
        guessedLetters.includes(letter) ? letter : '_'
    ).join(' ');
}

function displayLetters() {
    lettersContainer.innerHTML = 'abcdefghijklmnopqrstuvwxyz'.split('').map(letter => 
        `<span class="letter" onclick="guessLetter('${letter}')">${letter}</span>`
    ).join('');
}

function displayHint() {
    hintContainer.textContent = `Dica: ${selectedHint}`;
}

function guessLetter(letter) {
    if (guessedLetters.includes(letter)) return;

    guessedLetters.push(letter);
    if (selectedWord.includes(letter)) {
        displayWord();
        if (!wordContainer.innerHTML.includes('_')) {
            messageContainer.textContent = "Você venceu!";
        }
    } else {
        wrongAttempts++;
        if (wrongAttempts >= 6) {
            messageContainer.textContent = `Você perdeu! A palavra era: ${selectedWord}`;
        }
    }

    const letterElements = document.querySelectorAll('.letter');
    letterElements.forEach(element => {
        if (element.textContent === letter) {
            element.classList.add(selectedWord.includes(letter) ?
