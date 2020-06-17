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

package com.google.sps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class FindMeetingQuery {
  /**Method that get the time ranges where a meeting request's mandatory attendees, and, if posible,
   * optional attendees, are available for the request's duration.
   * @param events: Collection of events that have already been scheduled
   * @param request: Meeting request, which includes its attendees and duration
   * @return A collection of TimeRanges when the meeting can be held
   */
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    final long MEETING_DURATION = request.getDuration();
    final Collection<String> MANDATORY_PEOPLE = request.getAttendees();
    final Collection<String> OPTIONAL_PEOPLE = request.getOptionalAttendees();
    List<TimeRange> options = new ArrayList<TimeRange>();
    List<TimeRange> optionsIncludingOptional = new ArrayList<TimeRange>();

    if (MEETING_DURATION > TimeRange.END_OF_DAY) {
      return options;
    }

    options.add(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, TimeRange.END_OF_DAY, true));

    for (Event event : events) {
      for (String attendee : event.getAttendees()) {
        if (MANDATORY_PEOPLE.contains(attendee)) {
          options = updateAvailableTimes(options, event.getWhen(), MEETING_DURATION);
        }
      }
    }

    optionsIncludingOptional = options;

    for (Event event : events) {
      for (String attendee : event.getAttendees()) {
        if (OPTIONAL_PEOPLE.contains(attendee)) {
          optionsIncludingOptional =
              updateAvailableTimes(optionsIncludingOptional, event.getWhen(), MEETING_DURATION);
        }
      }
    }

    if (optionsIncludingOptional.size() > 0 || MANDATORY_PEOPLE.size() == 0) {
      Collections.sort(optionsIncludingOptional, TimeRange.ORDER_BY_START);

      return optionsIncludingOptional;
    }

    Collections.sort(options, TimeRange.ORDER_BY_START);
    return options;
  }

  /**
   * Helper function that updates the List of available time ranges when a meeting can be scheduled
   * @param currentTimes: The currently available time ranges
   * @param unavailableTime: The time range when there is another event scheduled
   * @param meetingDuration: The length of the meeting request
   * @return A list of updated TimeRanges when a meeting can be scheduled
   */
  public List<TimeRange> updateAvailableTimes(
      List<TimeRange> currentTimes, TimeRange unavailableTime, long meetingDuration) {
    List<TimeRange> proxyCurrentTimes = new ArrayList<TimeRange>();

    for (TimeRange time : currentTimes) {
      if (time.overlaps(unavailableTime)) {
        if (time.equals(unavailableTime) || unavailableTime.contains(time)) {
          break; // don't include this time in our new currentTimes proxy
        } else if (time.contains(unavailableTime)) {
          if (unavailableTime.start() == TimeRange.START_OF_DAY) {
            TimeRange afterTime = TimeRange.fromStartDuration(
                unavailableTime.end(), time.end() - unavailableTime.end());
            if (afterTime.duration() >= meetingDuration) {
              proxyCurrentTimes.add(afterTime);
            }
          } else if (unavailableTime.end() == TimeRange.END_OF_DAY) {
            TimeRange beforeTime =
                TimeRange.fromStartDuration(time.start(), unavailableTime.start() - time.start());
            if (beforeTime.duration() >= meetingDuration) {
              proxyCurrentTimes.add(beforeTime);
            }
          } else {
            TimeRange beforeTime =
                TimeRange.fromStartDuration(time.start(), unavailableTime.start() - time.start());
            TimeRange afterTime = TimeRange.fromStartDuration(
                unavailableTime.end(), time.end() - unavailableTime.end());

            if (afterTime.duration() >= meetingDuration) {
              proxyCurrentTimes.add(afterTime);
            }
            if (beforeTime.duration() >= meetingDuration) {
              proxyCurrentTimes.add(beforeTime);
            }
          }
        } else if (time.start() < unavailableTime.start()) {
          TimeRange newTime =
              TimeRange.fromStartDuration(time.start(), unavailableTime.start() - time.start());
          if (newTime.duration() >= meetingDuration) {
            proxyCurrentTimes.add(newTime);
          }
        } else if (time.start() > unavailableTime.start()) {
          TimeRange newTime = TimeRange.fromStartDuration(
              unavailableTime.end(), time.end() - unavailableTime.end());
          if (newTime.duration() >= meetingDuration) {
            proxyCurrentTimes.add(newTime);
          }
        }
      } else {
        if (time.duration() >= meetingDuration) {
          proxyCurrentTimes.add(time);
        }
      }
    }
    return proxyCurrentTimes;
  }
}
