package grokking;

import java.util.*;

public class Part_05_MergeIntervals {

    static class MyInterval {
        int start;
        int end;

        public MyInterval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.
     */
    public static List<MyInterval> mergeIntervals(List<MyInterval> intervals) {
        Collections.sort(intervals, Comparator.comparingInt(o -> o.start));
        List<MyInterval> mergedIntervals = new LinkedList<>();

        Iterator<MyInterval> iterator = intervals.iterator();
        MyInterval interval = iterator.next();
        int start = interval.start;
        int end = interval.end;

        while (iterator.hasNext()) {
            interval = iterator.next();

            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                mergedIntervals.add(new MyInterval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        mergedIntervals.add(new MyInterval(start, end));

        return mergedIntervals;
    }

    /**
     * Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct position
     * and merge all necessary intervals to produce a list that has only mutually exclusive intervals.
     * https://leetcode.com/problems/insert-interval/submissions/
     */
    public static List<MyInterval> insertInterval(List<MyInterval> intervals, MyInterval newInterval) {
        List<MyInterval> mergedIntervals = new ArrayList<>();

        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            mergedIntervals.add(intervals.get(i++));
        }

        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }

        mergedIntervals.add(newInterval);

        while (i < intervals.size()) {
            mergedIntervals.add(intervals.get(i++));
        }

        return mergedIntervals;
    }

    /**
     * Given two lists of intervals, find the intersection of these two lists.
     * Each list consists of disjoint intervals sorted on their start time.
     */
    public static MyInterval[] mergeIntervalsIntersection(MyInterval[] arr1, MyInterval[] arr2) {
        List<MyInterval> intervalsIntersection = new ArrayList<MyInterval>();

        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].start <= arr2[j].start && arr1[i].end >= arr2[j].start || arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end) {
                intervalsIntersection.add(new MyInterval(Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
            }

            if (arr1[i].end < arr2[j].end) {
                i++;
            } else {
                j++;
            }
        }

        return intervalsIntersection.toArray(new MyInterval[intervalsIntersection.size()]);
    }

    /**
     * Given an array of intervals representing ‘N’ appointments, find out if a person can attend all the appointments.
     */
    public static boolean canAttendAllAppointments(MyInterval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o.start));

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start)
                return false;
        }

        return true;
    }

    static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Given a list of intervals representing the start and end time of ‘N’ meetings,
     * find the minimum number of rooms required to hold all the meetings.
     */
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        if (meetings == null || meetings.isEmpty())
            return 0;

        if (meetings.size() == 1)
            return 1;

        // time - O(N*logN)
        // space - O(N)
        meetings.sort(Comparator.comparingInt(o -> o.start));
        // space - O(N)
        PriorityQueue<Integer> meetingEndingsQueue = new PriorityQueue<>();

        for (Meeting meeting : meetings) {
            if (!meetingEndingsQueue.isEmpty() && meetingEndingsQueue.peek() <= meeting.start) {
                // time - O(logN)
                meetingEndingsQueue.poll();
            }
            // time - O(logN)
            meetingEndingsQueue.add(meeting.end);
        }

        return meetingEndingsQueue.size();
    }

    static class Job {
        int start;
        int end;
        int cpuLoad;

        public Job(int start, int end, int cpuLoad) {
            this.start = start;
            this.end = end;
            this.cpuLoad = cpuLoad;
        }
    }

    /**
     * We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running.
     * Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.
     */
    public static int findMaxCPULoad(List<Job> jobs) {
        jobs.sort(Comparator.comparingInt(o -> o.start));
        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
        int maxLoad = 0;
        int currentLoad = 0;

        for (Job job : jobs) {
            while (!pq.isEmpty() && pq.peek().end < job.start) {
                Job irrelevantJob = pq.poll();
                currentLoad -= irrelevantJob.cpuLoad;
            }

            currentLoad += job.cpuLoad;
            maxLoad = Math.max(maxLoad, currentLoad);
            pq.add(job);
        }

        return maxLoad;
    }

    /**
     * For ‘K’ employees, we are given a list of intervals representing the working hours of each employee.
     * Our goal is to find out if there is a free interval that is common to all employees.
     * You can assume that each list of employee working hours is sorted on the start time.
     */
    public static List<MyInterval> findEmployeeFreeTime(List<List<MyInterval>> schedule) {
        List<MyInterval> result = new ArrayList<>();

        // space complexity O(N)
        List<MyInterval> sched = new LinkedList<>();
        for (List<MyInterval> intervals : schedule) {
            for (MyInterval interval : intervals) {
                sched.add(interval);
            }
        }
        // space complexity O(N)
        sched.sort(Comparator.comparingInt(o -> o.start));

        // space complexity O(N)
        PriorityQueue<MyInterval> pq = new PriorityQueue<>(Comparator.comparing(o1 -> o1.end, Comparator.reverseOrder()));

        for (MyInterval interval : sched) {
            if (!pq.isEmpty() && interval.start > pq.peek().end) {
                result.add(new MyInterval(pq.peek().end, interval.start));
            }
            // time complexity O(logN) N times
            pq.add(interval);
        }

        // overall time complexity O(N*logN)
        // space complexity O(N)

        return result;
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int covered = 0;
        int maxRight = -1;
        int left = -1;

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] <= maxRight || (left == intervals[i][0] && maxRight <= intervals[i][1])) {
                covered++;
            }
            left = intervals[i][0];
            maxRight = Math.max(maxRight, intervals[i][1]);
        }

        return intervals.length - covered;
    }

    public static void main(String[] args) {
        // [[1,2],[1,4],[3,4]]
        removeCoveredIntervals(new int[][]{{1,6},{1,4}});
    }
}
