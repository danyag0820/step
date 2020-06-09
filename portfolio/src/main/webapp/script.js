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
 * Takes an ArrayList of messages and returns an message list element
 * with formatted individual messages
 */
function createMessageListElt(messageList) {
    var messageListElt = document.createElement('ul');
    for (i = 0; i < messageList.length; i++) {
        var singleMessage = document.createElement('li');
        var messageContent = document.createTextNode(messageList[i].name + "(" + 
            messageList[i].email + "): " + messageList[i].text);
        singleMessage.appendChild(messageContent);
        messageListElt.appendChild(singleMessage);
    }
    return messageListElt;
}

/**
 * Fetches messages from the server and adds them to the message board
 */
function addMessages() {
    fetch('/messages').then(response => response.json()).then((messageList) => {
        var messageBoard = createMessageListElt(messageList);
        document.getElementById('messageContainer').appendChild(messageBoard);      
     });

}

/** Creates a map and adds it to the page. */
function createMap() {
  const map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 37.422, lng: -122.084}, zoom: 16});
}
