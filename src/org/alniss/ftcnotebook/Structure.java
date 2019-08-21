package org.alniss.ftcnotebook;

import java.util.*;

public class Structure {
    public static class Notebook {
        private List<Day> days;

        public Notebook() {
            days = new ArrayList<>();
        }

        public void addDay(Day day) {
            days.add(day);
        }

        public void addDay(int index, Day day) {
            days.add(index, day);
        }

        public void removeDay(int index) {
            days.remove(index);
        }

        public Day getDay(int index) {
            return days.get(index);
        }

        public Day getDay(String date) {
            for (Day day : days) {
                if (day.date.equals(date))
                    return day;
            }
            return null;
        }
    }

    public static class Day {
        private String date;
        private List<Section> sections;

        public Day(String date) {
            this.date = date;
            sections = new ArrayList<>();
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public void addSection(Section section) {
            sections.add(section);
        }

        public void addSection(int index, Section section) {
            sections.add(index, section);
        }

        public void removeSection(int index) {
            sections.remove(index);
        }

        public Section getSection(int index) {
            return sections.get(index);
        }

        public Section getSection(String title) {
            for (Section section : sections) {
                if (section.title.equals(title))
                    return section;
            }
            return null;
        }

        public Collection<Section> getSections() {
            return sections;
        }
    }

    public static class Section {
        private String title;
        private List<Entry> entries;

        public Section(String title) {
            this.title = title;
            entries = new ArrayList<>();
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void addEntry(Entry entry) {
            entries.add(entry);
        }

        public void addEntry(int index, Entry entry) {
            entries.add(index, entry);
        }

        public void removeEntry(int index) {
            entries.remove(index);
        }

        public Entry getEntry(int index) {
            return entries.get(index);
        }
    }

    public static class Entry {
        private List<String> messageIDs;

        public Entry() {
            messageIDs = new ArrayList<>();
        }

        public void addMessage(String messageID) {
            messageIDs.add(messageID);
        }

        public void addMessage(int index, String messageID) {
            messageIDs.add(index, messageID);
        }

        public void removeMessage(int index) {
            messageIDs.remove(index);
        }

        public String getMessageID(int i) {
            return messageIDs.get(i);
        }
    }
}
