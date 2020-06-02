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

// function getRandomQuoteUsingArrowFunctions() {
//   fetch('/random-quote').then(response => response.text()).then((quote) => {
//     document.getElementById('quote-container').innerText = quote;
//   });
// }

/**
 * Fetches json string from the server, converts to text, and adds it to the DOM.
 */
function getComments() {
    console.log('Fetching json string');
    fetch('/data').then(response => response.text()).then((comment) => {
        document.getElementById('commentsContainer').innerText = comment;
    });

}

