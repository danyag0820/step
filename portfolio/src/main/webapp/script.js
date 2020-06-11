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

/** Creates a map game with markers and adds it to the page. */
function createGameMap() {
    const gameMap = new google.maps.Map(
      document.getElementById('gameMap'),
      {center: {lat: 40.241335, lng: -99.204881}, zoom: 3});

    //marker location correspond to game clues
    const grandCanyon = new google.maps.Marker({
        position: {lat: 36.103727, lng: -112.107690},
        map: gameMap,
        title: 'Clue 1'
    }); 
    const orlandoAirport = new google.maps.Marker({
        position: {lat: 28.431117, lng: -81.307999},
        map: gameMap,
        title: 'Clue 2'
    });
    const odeToJoyFarm = new google.maps.Marker({
        position: {lat: 47.186577, lng: -122.031466},
        map: gameMap,
        title: 'Clue 3'
    });
    const goldenGate = new google.maps.Marker({
        position: {lat: 37.819787, lng: -122.478486},
        map: gameMap,
        title: 'Clue 4'
    });
    const lakeMichigan = new google.maps.Marker({
        position: {lat: 43.890377, lng: -87.004874},
        map: gameMap,
        title: 'Clue 5'
    });
    const eastRiver = new google.maps.Marker({
        position: {lat: 40.786958, lng: -73.917803},
        map: gameMap,
        title: 'Clue 6'
    });
}

/** Fetches iceberg sightings data from the server and displays it in a map. */
function createIcebergSightingsMap() {
  fetch('/iceberg-data').then(response => response.json()).then((icebergSightings) => {
    const icebergMap = new google.maps.Map(
        document.getElementById('icebergMap'),
        {center: {lat: 54.0783, lng: -55.1583}, zoom: 4});
    icebergSightings.forEach((iceberg) => {
        var marker = new google.maps.Marker(
            {position: {lat: iceberg.lat, lng: iceberg.lng}, 
            map: icebergMap,
            animation: google.maps.Animation.DROP,});
        var contentString = '<div id="content">'+
            '<p>iceberg size: </p>'+ iceberg.size + 
            '</div>'+
            '</div>';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });
        marker.addListener('click', function() {
            infowindow.open(icebergMap, marker);
        });          
    });
  });
}

/* loads images asynchronously*/
function loadImages() {
    var images = ['/images/friendImages/IMG-0490.JPG','/images/natureImages/IMG-0516.JPG','/images/natureImages/IMG-3893.JPG',
    '/images/friendImages/snowman.JPG','/images/natureImages/IMG-4799.JPG','/images/friendImages/IMG-2559.JPG','/images/natureImages/IMG-4901.JPG',
    '/images/natureImages/IMG-5025.JPG','/images/friendImages/IMG-7364.JPG','/images/natureImages/IMG-6283.JPG',
    '/images/friendImages/IMG-8269.jpg','/images/natureImages/IMG-7379.JPG','/images/natureImages/IMG-7383.JPG',
    '/images/friendImages/IMG-1539.JPG','/images/natureImages/IMG-7621.JPG','/images/natureImages/IMG-9385.jpg']
    for(i = 0; i < images.length; i++) {
        var img = new Image();
        img.onload = document.getElementById('photos').appendChild(img);
        img.src = images[i];
    }
}

