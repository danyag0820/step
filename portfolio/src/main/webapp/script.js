// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Fetches json string from the server, converts to text, and adds it to the DOM.
 */
 function getMessages() {
     console.log('Fetching json string');

     fetch('/messages').then(response => response.text()).then((message) => {
         document.getElementById('messageContainer').innerText = message;
     });
 }

/**
 * Creates a message board and formats individual messages
 */
function createMessageElt(message) {
    messageElt = document.createElement('ul');
    var curr = 0;

    for (i = 0; i < message.length/3; i++) {
        var singleMessage = document.createElement('li');
        
        var name = message[curr+2];
        var email = message[curr+1];
        var text = message[curr];
        curr += 3

        var messageContent = document.createTextNode(name + "(" + email + '): ' + text);
        singleMessage.appendChild(messageContent);
        messageElt.appendChild(singleMessage);
    };

    return messageElt;
}

/**
 * Fetches messages from the server and adds them to the message board
 */
function addMessages() {
    fetch('/messages').then(response => response.json()).then((message) => {
        var messageBoard = createMessageElt(message);
        document.getElementById('messageContainer').appendChild(messageBoard);      
     });

}

