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

/** Creates a map and adds it to the page. */
function createMap() {
    const map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 37.422, lng: -122.084}, zoom: 16});
    map.setCenter({lat: 40.241335, lng: -99.204881}); 
    map.setZoom(4);

    const grandCanyon = new google.maps.Marker({
    position: {lat: 36.103727, lng: -112.107690},
    map: map,
    title: 'Clue 1'
    });

    const orlandoAirport = new google.maps.Marker({
    position: {lat: 28.431117, lng: -81.307999},
    map: map,
    title: 'Clue 2'
    });

    const odeToJoyFarm = new google.maps.Marker({
    position: {lat: 47.186577, lng: -122.031466},
    map: map,
    title: 'Clue 3'
    });

    const goldenGate = new google.maps.Marker({
    position: {lat: 37.819787, lng: -122.478486},
    map: map,
    title: 'Clue 4'
    });

    const lakeMichigan = new google.maps.Marker({
    position: {lat: 43.890377, lng: -87.004874},
    map: map,
    title: 'Clue 5'
    });

    const eastRiver = new google.maps.Marker({
    position: {lat: 40.786958, lng: -73.917803},
    map: map,
    title: 'Clue 6'
    });
}
