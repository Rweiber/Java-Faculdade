const words = ["javascript", "html", "css", "programacao", "desenvolvimento"];
let selectedWord = '';
let guessedLetters = [];
let maxAttempts = 6;
let attempts = 0;

function startGame() {
    selectedWord = words[Math.floor(Math.random() * words.length)];
    guessedLetters = [];
    attempts = 0;
    document.getElementById('message').innerText = '';
    document.getElementById('restartButton').style.display = 'none';
    updateWordContainer();
    updateLettersContainer();
}

function updateWordContainer() {
    const wordContainer = document.getElementById('wordContainer');
    wordContainer.innerHTML = '';
    for (let i = 0; i < selectedWord.length; i++) {
        const span = document.createElement('span');
        span.classList.add('letter');
        if (guessedLetters.includes(selectedWord[i])) {
            span.innerText = selectedWord[i];
            span.classList.add('correct');
        }
        wordContainer.appendChild(span);
    }
}

function updateLettersContainer() {
    const lettersContainer = document.getElementById('lettersContainer');
    lettersContainer.innerHTML = '';
    for (let i = 0; i < 26; i++) {
        const letter = String.fromCharCode(65 + i);
        const button = document.createElement('button');
        button.innerText = letter;
        button.onclick = () => guessLetter(letter.toLowerCase());
        button.disabled = guessedLetters.includes(letter.toLowerCase());
        lettersContainer.appendChild(button);
    }
}

function guessLetter(letter) {
    if (!guessedLetters.includes(letter)) {
        guessedLetters.push(letter);
        if (!selectedWord.includes(letter)) {
            attempts++;
        }
        updateWordContainer();
        updateLettersContainer();
        checkGameStatus();
    }
}

function checkGameStatus() {
    const wordContainer = document.getElementById('wordContainer');
    const message = document.getElementById('message');
    if (attempts >= maxAttempts) {
        message.innerText = `Você perdeu! A palavra era ${selectedWord}`;
        document.getElementById('restartButton').style.display = 'block';
    } else if (Array.from(wordContainer.children).every(span => span.innerText)) {
        message.innerText = 'Você ganhou!';
        document.getElementById('restartButton').style.display = 'block';
    }
}

window.onload = startGame;
